$(function() {
    initInicio();
    boton.closeDialog();
    gridBusquedaAvanzadaBaseLegal();
    //changeCombo();
  
});
function initInicio(){
    //inicializa los spinner
    $("#textAnoBusquedaAvanzadaBaseLegal").spinner({max:2014},{min:1930});
    //inicializa Datepickers
    $("#dateFechaEntradaVigenciaBusquedaAvanzadaBaseLegal").datepicker();
    $("#dateFechaPublicacionBusquedaAvanzadaBaseLegal").datepicker();
    $("#dateFechaEntradaVigenciaDetalleBusquedaAvanzadaBaseLegal").datepicker();
    //inicializa toggles
    $("#toggBusquedaAvanzadaBaseLegal").puipanel({
            toggleable: true,
            collapsed:true
    });
    //inicializa botones para los arboles
    $('#botoActividadesBusquedaAvanzadaBaseLegal').click(function() {
        abrirArbolActividades();
    });
    $('#botoProductosBusquedaAvanzadaBaseLegal').click(function() {
        abrirArbolProductos();
    });
}
//Arboles
//muestra el Arbol de Productos
function abrirArbolProductos() {

    $.ajax({
        url: baseURL + "pages/baseLegal/arbolProductos",
        type: 'get',
        async: false,
        data: {
        },
        beforeSend: muestraLoading,
        success: function(data) {
            ocultaLoading();
            $("#dialogArbolProductos").html(data);
            $("#dialogArbolProductos").dialog({
                resizable: false,
                draggable: true,
                autoOpen: true,
                height: "auto",
                width: "auto",
                modal: true,
                dialogClass: 'dialog',
                title: "ARBOL DE PRODUCTOS"
            });

        },
        error: errorAjax
    });

}
//muestra el Arbol de Actividades
function abrirArbolActividades() {

    $.ajax({
        url: baseURL + "pages/baseLegal/arbolActividades",
        type: 'get',
        async: false,
        data: {
        },
        beforeSend: muestraLoading,
        success: function(data) {
            ocultaLoading();
            $("#dialogArbolActividades").html(data);
            $("#dialogArbolActividades").dialog({
                resizable: false,
                draggable: true,
                autoOpen: true,
                height: "auto",
                width: "auto",
                modal: true,
                dialogClass: 'dialog',
                title: "ARBOL DE ACTIVIDADES"
            });

        },
        error: errorAjax
    });

}
//grid búsqueda avanzada base legal
function gridBusquedaAvanzadaBaseLegal(){
    
    var nombres = ['idBaseLegal','Código Base Legal','Descripción Base Legal'];
    var columnas = [
        {name:'idBaseLegal',index:'id', width:55,hidden:true}, 
        {name: "codigoBaseLegal",width: 130,sortable: false,align: "center"},
        {name: "descBaseLegal",width: 800,sortable: false,align: "center"},
        
    ];
        
    $("#gridBusquedaAvanzadaBaseLegal").html("");
    var grid = $("<table>", {
        "id": "gridBusquedaAvanzadaBaseLegal"
    });
    var pager = $("<div>", {
        "id": "paginacionProcedimiento"
    });
    $("#gridContenedorBusquedaAvanzadaBaseLegal").append(grid).append(pager);

    grid.jqGrid({
        url: baseURL + "pages/baseLegal/resultadoBusquedaAvanzadaBaseLegal",
        datatype: "json",
        postData: {
         codigoOsinerg: $("#txtCodigoOsinerg").val()
        },
        hidegrid: false,
        rowNum: 3,
        pager: "#paginacionProcedimiento",
        emptyrecords: "No se encontraron resultados",
        loadtext: "Cargando",
        colNames: nombres,
        colModel: columnas,
        height: "auto",
        viewrecords: true,
        caption: "Busqueda Avanzada Base Legal",
        autowidth: true,
        jsonReader : {
            root : "filas", //la lista de registros a mostrar en el grid
            page : "pagina", //la pagina actual
            total : "total", //total de paginas
            records : "registros", //numero total de registros
            repeatitems : false,
            id : "idBaseLegal"
        },
        onSelectRow: function(rowid, status) {
            //grid.resetSelection();
           // grid.setSelection(rowid,true);
        },
        onRightClickRow: function(rowid, iRow, iCol, e) {
            
            var row = grid.jqGrid('getRowData', rowid);
            //$('#linkVerProcedimiento').attr('onClick', 'verProcedimiento("")');
            //$('#linkEditarProcedimiento').attr('onClick', 'editarProcedimiento("")');
            $('#linkSeleccionarBaseLegal').attr('onClick', 'seleccionarBaseLegal("'+rowid+'")');
            //$('#linkAgregarActProcedimiento').attr('onClick', 'agregarActProcedimiento("'+rowid+'","'+row.codigo+'","'+row.descripcion+'")');
        },
        loadComplete: function(data) {
                        
            $('#ContextMenuBusquedaAvanzadaBaseLegal').parent().remove();
            $('#divContextMenuBusquedaAvanzadaBaseLegal').html("<ul id='ContextMenuBusquedaAvanzadaBaseLegal'>"
                    + "<li> <a id='linkSeleccionarBaseLegal' data-icon='ui-icon-extlink' title='Seleccionar'>Seleccionar</a> </li>"
                    + "</ul>");
            $('#ContextMenuBusquedaAvanzadaBaseLegal').puicontextmenu({
                target: $('#gridBusquedaAvanzadaBaseLegal').find('tr').not('.ui-subgrid,.ui-subgrid tr')
            });
        }
              
    });
    }
//capturar el id de la fila del grid
function seleccionarBaseLegal(rowid){
        
}
//funcion show/hide Norma Técnica
//function changeCombo(){
//       $("#combNormaTecnicaBusquedaAvanzadaBaseLegal").change(function() {
//            var num = $('#combNormaTecnicaBusquedaAvanzadaBaseLegal option:selected').text();
//            if (num === '--Seleccione--') {
//                $("#txtaNormaTecnicaBusquedaAvanzadaBaseLegal").hide();
//            } else if (num === 'Norma Técnica Peruana') {
//            $("#txtaNormaTecnicaBusquedaAvanzadaBaseLegal").show();
//            }else if (num ==='NFPA') {
//            $("#txtaNormaTecnicaBusquedaAvanzadaBaseLegal").show();
//            } 
//       });
//       
//}