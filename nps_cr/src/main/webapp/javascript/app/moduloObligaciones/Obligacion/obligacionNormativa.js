$(function() {
    procesarGridProcedimiento();
    initInicio();
    gridBasesLegales();
    procesarGridProcedimientotab1();
    procesarGridProcedimientotab2();
    initArbolActividadesAsignar();
    changeCombo();
    initArbolProductosAsignar();
    initArbolProductos();
    initArbolActividades();
    //$('#checkboxAplica').change(changeCheckBox);
    $('#btnAsignar').click(btnGuardarAsignacion);
});
function btnGuardarAsignacion(){
    
        confirm.open("Ud est&aacute; seguro de guardar la asignaci&oacuten?");

}

function changeCheckBox() {
    if ($("#checkboxAplica").attr("checked")) {
        $("#aplicaCategorias").show();
    } else {
        $("#aplicaCategorias").hide();
    }
}
function changeCombo() {

    $("#cmbbuNormaBaseLegal").change(function() {
        var num = $('#cmbbuNormaBaseLegal option:selected').text();

        if (num == '--Seleccione--') {
            $("#text4").hide();

        } else if (num == 'Norma Técnica Peruana') {
            $("#text4").show();
        } else if (num == 'NFPA') {
            $("#text4").show();
        }
    });
}
function initInicio() {

    confirm.start();    
    $("#options").puipanel({
        toggleable: true,
        collapsed: true

    });
    $("#cmbbuAnoBaseLegal").spinner({max: 2014}, {min: 1930});
    $("#datepickerart1").datepicker();
    $("#datepickervig1").datepicker();
    $("#datepickerpub1").datepicker();
    $("#dialogArbolBusqueda").dialog({
        resizable: false,
        autoOpen: false,
        height: "auto",
        width: "auto",
        dialogClass: 'dialog',
        modal: true
    });
    $("#dialogArbolBusquedaProducto").dialog({
        resizable: false,
        autoOpen: false,
        height: "auto",
        width: "auto",
        dialogClass: 'dialog',
        modal: true
    });
    $("#dialogArbolBusquedaProducto1").dialog({
        resizable: false,
        autoOpen: false,
        height: "auto",
        width: "auto",
        dialogClass: 'dialog',
        modal: true
    });
    $('#btnProductos').click(function() {
        $("#dialogArbolBusquedaProducto1").dialog("open");
    });
    $("#dialogArbolBusquedaTransporte").dialog({
        resizable: false,
        autoOpen: false,
        height: "auto",
        width: "auto",
        dialogClass: 'dialog',
        modal: true
    });
    $('#btnTransportes').click(function() {
        $("#dialogArbolBusquedaTransporte").dialog("open");
    });


    $('#btnProductoClave1').click(function() {
        $("#dialogArbolBusquedaProducto").dialog("open");
    });
    $('#btnActividadClave1').click(function() {
        $("#dialogArbolBusqueda").dialog("open");
    });

    $("#tabs").tabs();
    $("#tabsob").tabs();
    $("#btnClose").click(function() {
        $("#dialogAgregarObligacionNormativa").dialog("close");
    });
    //regresa a la URL indicada
    $("#spinnerdesde").spinner({min: 0}, {max: 300}, {disabled: true});
    $("#spinnerhasta").spinner({min: 0}, {max: 300}, {disabled: true});

    $('#btnRegresar').click(function() {
        window.location.href = baseURL + 'pages/indice';
    });
    $("#dialogAgregarObligacionNormativa").dialog({
        resizable: false,
        autoOpen: false,
        height: "auto",
        width: "auto",
        dialogClass: 'dialog',
        modal: true
    });
    $("#dialogGenerarRelaciones").dialog({
        resizable: false,
        autoOpen: false,
        height: "auto",
        width: "auto",
        dialogClass: 'dialog',
        modal: true
    });

    //$('#btnAsignar').click(function(){
    //$("#dialogGenerarRelaciones").dialog("open");
    //});
    $('#btnNuevoObligacionNormativa').click(function() {
        $("#dialogAgregarObligacionNormativa").dialog("open");
    });
    $("#dialogUpload").dialog({
        resizable: false,
        autoOpen: false,
        height: "auto",
        width: "auto",
        dialogClass: 'dialog',
        modal: true
    });
    $("#dialogUpload1").dialog({
        resizable: false,
        autoOpen: false,
        height: "auto",
        width: "auto",
        dialogClass: 'dialog',
        modal: true
    });
    $('#imgUpload').click(function() {
        $("#dialogUpload").dialog("open");
    });
    $('#imgUpload1').click(function() {
        $("#dialogUpload1").dialog("open");
    });


    $('#guardarUpload').click(function() {

        $("#imgcargar").show();
        $("#dialogUpload").dialog("close");

        $("#file_name").text($("#fileArchivo").val());
        $("#txtImgUpload").text($("#fileupload").val());


    });
    $('#guardarUpload1').click(function() {

        $("#imgcargar1").show();
        $("#dialogUpload1").dialog("close");

        $("#file_name").text($("#fileArchivo").val());
        $("#txtImgUpload1").text($("#fileupload1").val());


    });

    $('#btnFormActividad').click(function() {
        abrirArbolActividades();
    });
}
function abrirArbolActividades() {

    $.ajax({
        url: baseURL + "pages/obligacionNormativa/abrirArbolActividad",
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
//funciones ...(3)
//funcion para llenar el grid ...inicializada en (1)
function procesarGridProcedimientotab1() {

    var mydata = [
        {id: "BL001", invdate: "Art. 42° de Reglamento Aprobado por DS. N° 054-1993-EM", tieneAct: 0},
        {id: "BL002", invdate: "Art. 43° de Reglamento Aprobado por DS. N° 054-1993-EM", tieneAct: 0},
        {id: "BL003", invdate: "Art. 44° de Reglamento Aprobado por DS. N° 054-1993-EM", tieneAct: 0},
        {id: "BL004", invdate: "Art. 45° de Reglamento Aprobado por DS. N° 054-1993-EM", tieneAct: 0},
        {id: "BL005", invdate: "Art. 46° de Reglamento Aprobado por DS. N° 054-1993-EM", tieneAct: 0},
        {id: "BL006", invdate: "Art. 47° de Reglamento Aprobado por DS. N° 054-1993-EM", tieneAct: 0},
        {id: "BL007", invdate: "Art. 48° de Reglamento Aprobado por DS. N° 054-1993-EM", tieneAct: 0},
        {id: "BL008", invdate: "Art. 49° de Reglamento Aprobado por DS. N° 054-1993-EM", tieneAct: 0}

    ];


    jQuery("#list9").jqGrid({
        //url:'server.php?q=2&nd='+new Date().getTime(), 
        datatype: "local",
        colNames: ['CODIGO BASE LEGAL', 'DESCRIPCION BASE LEGAL'],
        colModel: [{name: 'id', index: 'id', width: 150},
            {name: 'invdate', index: 'invdate', width: 750}],
        rowNum: 10,
        pager: '#pager9',
        sortname: 'id',
        recordpos: 'left',
        viewrecords: true,
        sortorder: "desc",
        multiselect: true,
        hidegrid: false,
        caption: "Busqueda Avanzada Base Legal",
        loadComplete: function(data) {
            for (var i = 0; i <= mydata.length; i++) {
                jQuery("#list9").jqGrid('addRowData', i + 1, mydata[i]);
            }
        }

    }

    );
    jQuery("#list9").jqGrid('navGrid', '#pager9', {add: false, del: false, edit: false, position: 'center'});
}
function procesarGridProcedimientotab2() {

    var mydata = [
        {id: "BL001", invdate: "Art. 42° de Reglamento Aprobado por DS. N° 054-1993-EM", tieneAct: 0},
        {id: "BL005", invdate: "Art. 46° de Reglamento Aprobado por DS. N° 054-1993-EM", tieneAct: 0},
        {id: "BL006", invdate: "Art. 47° de Reglamento Aprobado por DS. N° 054-1993-EM", tieneAct: 0},
        {id: "BL007", invdate: "Art. 48° de Reglamento Aprobado por DS. N° 054-1993-EM", tieneAct: 0}


    ];


    jQuery("#list10").jqGrid({
        //url:'server.php?q=2&nd='+new Date().getTime(), 
        datatype: "local",
        colNames: ['CODIGO BASE LEGAL', 'DESCRIPCION BASE LEGAL'],
        colModel: [{name: 'id', index: 'id', width: 150},
            {name: 'invdate', index: 'invdate', width: 750}],
        rowNum: 10,
        pager: '#pager10',
        sortname: 'id',
        recordpos: 'left',
        viewrecords: true,
        sortorder: "desc",
        multiselect: false,
        hidegrid: false,
        caption: "Asignado Base Legal",
        loadComplete: function(data) {
            for (var i = 0; i <= mydata.length; i++) {
                jQuery("#list10").jqGrid('addRowData', i + 1, mydata[i]);
            }
        },
    }

    );
    jQuery("#list10").jqGrid('navGrid', '#pager10', {add: false, del: false, edit: false, position: 'center'});
}
function asignarBaseLegal(rowid) {
    var row = $('#gridObligacionNormativa').jqGrid('getRowData', rowid);
    $("#dialogGenerarRelaciones").dialog("open");

}

function procesarGridProcedimiento() {



    var mydata = [
//        {codigoSub:"OB100",descripcionSub:"Deberá contar con una póliza de Seguro Responsabilidad Civil Extracontractual vigente que cumpla con el monto requerido por la normativa..",tieneAct:1},
//         {codigoSub:"OB101",descripcionSub:" Los Grifos Flotantes deberán contar como mínimo con dos (02) extintores portátiles, cuyo agente extintor sea de múltiple propósito tipo ABC, con una capacidad de extinción certificada no menor a 4A:80B:C Los extintores deberán estar certificados por Underwriters Laboratories - UL o entidad similar acreditada, por el INDECOPI o por un organismo extranjero de acreditación signatario de alguno de los Acuerdos de Reconocimiento Mutuo de la Internacional Accreditation Forum - IAF o la Inter American Accreditation Cooperation - IAAC., de acuerdo a la NTP 350.026, así como de las NTP 350.062-1, 350.062-2 y 350-062-3. Alternativamente, se aceptará extintores aprobados por Factory Mutual - FM que cumplan con la ANSI/UL 299 y cuya capacidad de extinción cumpla con la ANSI/UL 711.",tieneAct:1},
//         {codigoSub:"OB102",descripcionSub:"De acuerdo al resultado del Estudio de Riesgos, deberá disponer de extintores portátiles y rodantes, en número, calidad y tipo, de acuerdo a lo que indique la Norma Técnica Peruana Nº 350.043. Como mínimo deberá contar con dos (02) extintores portátiles de 12 kilogramos de capacidad, cuyo agente extintor sea de múltiple propósito ABC (polvo químico seco a base de monofosfato de amonio y con rating de extinción certificada -U.L. o NTP 350.062- no menor a 20:A:80 BC).",tieneAct:1},
//         {codigoSub:"OB103",descripcionSub:"Adicionalmente, deberá contar con un (1) extintor rodante de cincuenta kilogramos (50k) de capacidad, cuyo agente extintor sea de múltiple propósito ABC (polvo químico seco a base de monofosfato de amonio y con rating de extinción certificado-U.L. o NTP 350.043- no menor  a 40:A:240BC), que será colocado en el patio de maniobras.",tieneAct:1},
//         {codigoSub:"OB104",descripcionSub:"Si el establecimiento está ubicado en una zona donde pueden ocurrir tormentas eléctricas deberá instalar un sistema pararrayos para proteger las instalaciones.",tieneAct:1},
        {codigoSub: "OB105", descripcionSub: "¿Se encuentran los equipos de despacho, conexiones de entrada de los tanques y ventilaciones  más cercanas a una distancia mínima de  cincuenta metros (50.00 m) de los límites de propiedad de las construcciones o proyectos aprobados por la municipalidad para centros educativos, mercados, supermercados, hospitales, clínicas, iglesias, cines, teatros, cuarteles, zonas militares, comisarias o zonas policiales, establecimientos penitenciarios y lugares de espectáculos públicos que tengan Licencia Municipal o autorización equivalente para su funcionamiento?", tieneAct: 1},
        {codigoSub: "OB106", descripcionSub: "Si el establecimiento está ubicado en carretera y existen intersecciones a nivel: ¿El lindero más próximo del establecimiento se ubica a una distancia del centro de intersección no menor de doscientos metros (200.00 m) para las carreteras de primera clase y cien metros (100.00 m) en las de segunda y tercera clase?", tieneAct: 1},
        {codigoSub: "OB107", descripcionSub: "¿Se ubican los equipos de despacho o tanques de combustibles líquidos cumpliendo con la distancia mínima con respecto a la proyección horizontal de las líneas áreas que conduzcan electricidad según el siguiente cuadro?: ", tieneAct: 1},
        {codigoSub: "OB108", descripcionSub: "Para Planta Envasadora con capacidad de almacenamiento de GLP menor a 40000 kg: ¿Se ha colocado una puerta para ingreso y salida de vehículos, con un ancho no menor de cuatro (4) metros, además de una puerta independiente para uso del personal?", tieneAct: 1},
        {codigoSub: "OB109", descripcionSub: "Para Planta Envasadora con capacidad de almacenamiento de GLP mayor o igual a 40000 kg:¿Se ha colocado dos puertas, una para ingreso y otra para salida de vehículos, con un ancho no menor de cuatro (4) metros, además de una puerta independiente para uso del personal?", tieneAct: 1},
        {codigoSub: "OB110", descripcionSub: "¿Las zonas de estacionamiento, circulación, protección y almacenamiento de la Planta Envasadora están despejadas y libres de pasto, plantas, desechos y cualquier otro material fácilmente combustible?", tieneAct: 1},
        {codigoSub: "OB111", descripcionSub: "¿Los lugares destinados a estacionamientos de vehículos y las zonas de circulación en el interior de la Planta Envasadora se encuentran señalizados y libres de basura, materiales fácilmente combustible y de cualquier objeto que constituya estorbo para la circulación y/o estacionamiento?", tieneAct: 1}

    ];
    var mydataSubGrid = {
//        1: [
//        
//       {Codigo_Base_Legal:"BL001",Desc_Base_Legal:"Art. 58º de Reglamento aprobado por D.S. Nº 030-98-EM"},
//        {Codigo_Base_Legal:"BL002",Desc_Base_Legal:"Anexo 2.3 C de Reglamento del Registro de Hidrocarburos aprobado por RCD N° 191-2011-OS/CD."}
//       
//         ],
//         2:[
//             {Codigo_Base_Legal:"BL003",Desc_Base_Legal:"Art. 94° de Reglamento aprobado por D.S. N° 054-93-EM"},
//        {Codigo_Base_Legal:"BL004",Desc_Base_Legal:"Art. 3º de D.S. N° 005-2012-EM."},
//        {Codigo_Base_Legal:"BL005",Desc_Base_Legal:"Art. 36° de Reglamento aprobado por D.S. N° 054-93-EM."}
//        
//         ],
//         3:[
//              {Codigo_Base_Legal:"BL005",Desc_Base_Legal:"Art. 36° de Reglamento aprobado por D.S. N° 054-93-EM."},
//        {Codigo_Base_Legal:"BL006",Desc_Base_Legal:"Art. 99° de reglamento aprobado por D.S. N° 019-97-EM"}
//       
//         ],
//         4:[
//              {Codigo_Base_Legal:"BL006",Desc_Base_Legal:"Art. 99° de reglamento aprobado por D.S. N° 019-97-EM"}
//            ],
//            5:[
//                {Codigo_Base_Legal:"BL007",Desc_Base_Legal:"Art. 43° Lit f) de Reglamento aprobado por D.S. Nº 015-2006-EM "},
//        {Codigo_Base_Legal:"BL008",Desc_Base_Legal:"Art. 67° del reglamento aprobado por D.S. Nº 054-93-EM."}
//    
//            ],
        1: [
            {Codigo_Base_Legal: "BL009", Desc_Base_Legal: "Art. 11° Num. 3 de Reglamento Aprobado por D.S. N° 054-1993-EM y Modificatorias"}
        ],
        2: [
            {Codigo_Base_Legal: "BL010", Desc_Base_Legal: "Art. 13° Num. 5 de Reglamento Aprobado por D.S. N° 054-1993-EM "}
        ],
        3: [
            {Codigo_Base_Legal: "BL011", Desc_Base_Legal: "Art. 47° de Reglamento Aprobado por D.S. N° 054-1993-EM y Modificatorias"}
        ],
        4: [
            {Codigo_Base_Legal: "BL012", Desc_Base_Legal: "Art. 9° de Reglamento Aprobado por D.S. N° 027-1994-EM"}
        ],
        5: [
            {Codigo_Base_Legal: "BL012", Desc_Base_Legal: "Art. 9° de Reglamento Aprobado por D.S. N° 027-1994-EM"}
        ],
        6: [
            {Codigo_Base_Legal: "BL013", Desc_Base_Legal: "Art. 11° de Reglamento Aprobado por D.S. N° 027-1994-EM"}
        ],
        7: [
            {Codigo_Base_Legal: "BL014", Desc_Base_Legal: "Art. 12° de Reglamento Aprobado por D.S. N° 027-1994-EM"}
        ]
    };
    var nombres = ['Codigo Obligaciones', 'Descripción Obligaciones', 'tieneAct'];
    var columnas = [
        {name: "codigoSub", width: 130, sortable: false, align: "center"},
        {name: "descripcionSub", width: 800, sortable: false, align: "justify"},
        {name: "tieneAct", width: 30, sortable: false, hidden: true, align: "center"}

    ];
    var nombresSubGrid = ['Código Base Legal', 'Descripción Base Legal'];
    var columnasSubGrid = [
        {name: "Codigo_Base_Legal", width: 80, sortable: false, align: "center"},
        {name: "Desc_Base_Legal", width: 400, sortable: false, align: "center"}];

    $("#gridContenedorObligacionNormativa").html("");
    var grid = $("<table>", {
        "id": "gridObligacionNormativa"
    });
    var pager = $("<div>", {
        "id": "paginacionObligacionNormativa"
    });
    $("#gridContenedorObligacionNormativa").append(grid).append(pager);

    grid.jqGrid({
        // url: baseURL + "pages/busquedaController/find",
        datatype: "local",
        /*postData: {
         codigoOsinerg: $("#txtCodigoOsinerg").val()
         },*/
        hidegrid: false,
        rowNum: global.rowNum,
        pager: "#paginacionObligacionNormativa",
        emptyrecords: "No se encontraron resultados",
        loadtext: "Cargando",
        colNames: nombres,
        colModel: columnas,
        height: "auto",
        viewrecords: true,
        caption: "Listado de Obligaciones Normativas",
        autowidth: true,
        onSelectRow: function(rowid, status) {
            grid.resetSelection();

        },
        onRightClickRow: function(rowid, iRow, iCol, e) {
            var row = grid.jqGrid('getRowData', rowid);
            //$('#linkVerProcedimiento').attr('onClick', 'verProcedimiento("")');
            $('#linkAsignarBasesLegales').attr('onClick', 'asignarBaseLegal("' + rowid + '")');
            //$('#linkEliminarProcedimiento').attr('onClick', 'eliminarProcedimiento("'+rowid+'")');
            // $('#linkAgregarActProcedimiento').attr('onClick', 'agregarActProcedimiento("'+rowid+'","'+row.codigo+'","'+row.descripcion+'")');
        },
        loadComplete: function(data) {
            for (var i = 0; i <= mydata.length; i++) {
                jQuery("#gridObligacionNormativa").jqGrid('addRowData', i + 1, mydata[i]);
            }

            $('#contextMenuObligacionNormativa').parent().remove();
            $('#divContextMenuObligacionNormativa').html("<ul id='contextMenuObligacionNormativa'>"
                    + "<li> <a id='linkAtenderUnidad' data-icon='ui-icon-search' title='Ver Detalle'>Ver Detalle</a> </li>"
                    //+ "<li> <a id='linkEditarProcedimiento' data-icon='ui-icon-pencil' title='Editar'>Modificar</a></li>"
                    //+ "<li> <a id='linkEliminarProcedimiento' data-icon='ui-icon-trash' title='Editar'>Eliminar</a></li>"
                    + "<li> <a id='linkAsignarBasesLegales' data-icon='ui-icon-note' title='Asignar Bases Legales'>Asignar Bases Legales</a></li>"
                    //+ "<li> <a id='linkAgregarActProcedimiento' data-icon='ui-icon-clipboard' title='Editar'>Gestionar Requisitos</a></li>"
                    + "</ul>");
            $('#contextMenuObligacionNormativa').puicontextmenu({
                //target: $('#gridObligacionNormativa').find('tr').not('.ui-subgrid,.ui-subgrid tr')
                target: $('#gridObligacionNormativa')
            });
        },
        //SUBGRID
        subGrid: true,
        afterInsertRow: function(rowid, aData, rowelem) {
            var rowData = grid.getRowData(rowid);
            if (rowData["tieneAct"] == 0) {
                $('tr#' + rowid, grid)
                        .children("td.sgcollapsed")
                        .html("")
                        .removeClass('ui-sgcollapsed sgcollapsed');
            }
        },
        subGridOptions: {
            "plusicon": "ui-icon-circle-plus",
            "minusicon": "ui-icon-circle-minus",
            "openicon": "ui-icon-arrowreturn-1-e",
            "reloadOnExpand": false,
            "selectOnExpand": true
        },
        subGridRowExpanded: function(subgrid_id, row_id) {
            var subgrid_table_id, pager_id;
            subgrid_table_id = subgrid_id + "_t";
            pager_id = "p_" + subgrid_table_id;
            $("#" + subgrid_id).html("<table id='" + subgrid_table_id + "' class='scroll'></table><div id='" + pager_id + "' class='scroll'></div>");
            jQuery("#" + subgrid_table_id).jqGrid({
                //url:"subgrid.php?q=2&id="+row_id, 
                datatype: "local",
                data: mydataSubGrid[row_id],
                colNames: nombresSubGrid,
                colModel: columnasSubGrid,
                rowNum: global.rowNum,
                pager: pager_id,
                sortname: 'num',
                sortorder: "asc",
                height: '100%',
                autowidth: true,
                loadComplete: function(data) {
                    for (var i = 0; i <= mydataSubGrid.length; i++) {
                        jQuery("#" + subgrid_table_id).jqGrid('addRowData', i + 1, mydataSubGrid[i]);
                    }
                },
            });
        }
    });
}
//Grid Bases Legales
function gridBasesLegales() {
    var mydata = [
        {Codigo_BaseLegal: "DBL001", Desc_BaseLegal: "Decreto Supremo N° 054-93-EM Reglamento Artículo 42°", tieneAct: 0},
        {Codigo_BaseLegal: "DBL002", Desc_BaseLegal: "Decreto Supremo N° 054-93-EM Reglamento Artículo 43°", tieneAct: 0},
        {Codigo_BaseLegal: "DBL003", Desc_BaseLegal: "Decreto Supremo N° 054-93-EM Reglamento Artículo 57°", tieneAct: 0}

    ];

    var nombres = ['Código Base Legal', 'Descripción Base Legal', 'tieneAct'];
    var columnas = [
        {name: "Codigo_BaseLegal", width: 130, sortable: false, align: "center"},
        {name: "Desc_BaseLegal", width: 800, sortable: false, align: "center"},
        {name: "tieneAct", width: 30, sortable: false, hidden: true, align: "center"}
    ];


    $("#gridBasesLegales").html("");
    var grid = $("<table>", {
        "id": "gridBasesLegales"
    });
    var pager = $("<div>", {
        "id": "paginacionBasesLegales"
    });
    $("#gridContenedorBaseLegal").append(grid).append(pager);

    grid.jqGrid({
        // url: baseURL + "pages/busquedaController/find",
        datatype: "local",
        /*postData: {
         codigoOsinerg: $("#txtCodigoOsinerg").val()
         },*/
        hidegrid: false,
        rowNum: global.rowNum,
        pager: "#paginacionBasesLegales",
        emptyrecords: "No se encontraron resultados",
        loadtext: "Cargando",
        colNames: nombres,
        colModel: columnas,
        height: "auto",
        viewrecords: true,
        caption: "Listado de Bases Legales",
        autowidth: true,
        onSelectRow: function(rowid, status) {
            grid.resetSelection();
        },
        onRightClickRow: function(rowid, iRow, iCol, e) {
            var row = grid.jqGrid('getRowData', rowid);
            $('#linkVerProcedimiento').attr('onClick', 'verProcedimiento("")');
            $('#linkEditarProcedimiento').attr('onClick', 'editarProcedimiento("")');
            $('#linkEliminarProcedimiento').attr('onClick', 'eliminarProcedimiento("' + rowid + '")');
            $('#linkAgregarActProcedimiento').attr('onClick', 'agregarActProcedimiento("' + rowid + '","' + row.codigo + '","' + row.descripcion + '")');
        },
        loadComplete: function(data) {
            for (var i = 0; i <= mydata.length; i++) {
                jQuery("#gridBasesLegales").jqGrid('addRowData', i + 1, mydata[i]);
            }

            $('#contextMenuBaseLegal').parent().remove();
            //$('#divContextMenuBaseLegal').html("<ul id='contextMenuBaseLegal'>"
            // + "<li> <a id='linkAtenderUnidad' data-icon='ui-icon-search' title='Ver Detalle'>Ver Detalle</a> </li>"
            //+ "<li> <a id='linkEditarProcedimiento' data-icon='ui-icon-pencil' title='Editar'>Modificar</a></li>"
            //+ "<li> <a id='linkEliminarProcedimiento' data-icon='ui-icon-trash' title='Editar'>Eliminar</a></li>"
            //+ "<li> <a id='linkAgregarActProcedimiento' data-icon='ui-icon-note' title='Editar'>Agregar Tramite-Actividad</a></li>"
            //+ "<li> <a id='linkAgregarActProcedimiento' data-icon='ui-icon-clipboard' title='Editar'>Gestionar Requisitos</a></li>"
            //       + "</ul>");
            $('#contextMenuBaseLegal').puicontextmenu({
                target: $('#gridBasesLegales').find('tr').not('.ui-subgrid,.ui-subgrid tr')
            });
        },
        //SUBGRID
        subGrid: true,
        afterInsertRow: function(rowid, aData, rowelem) {
            var rowData = grid.getRowData(rowid);
            if (rowData["tieneAct"] == 0) {
                $('tr#' + rowid, grid)
                        .children("td.sgcollapsed")
                        .html("")
                        .removeClass('ui-sgcollapsed sgcollapsed');
            }
        },
        subGridOptions: {
            "plusicon": "ui-icon-circle-plus",
            "minusicon": "ui-icon-circle-minus",
            "openicon": "ui-icon-arrowreturn-1-e",
            "reloadOnExpand": false,
            "selectOnExpand": true
        },
        subGridRowExpanded: function(subgrid_id, row_id) {
            var subgrid_table_id, pager_id;
            subgrid_table_id = subgrid_id + "_t";
            pager_id = "p_" + subgrid_table_id;
            $("#" + subgrid_id).html("<table id='" + subgrid_table_id + "' class='scroll'></table><div id='" + pager_id + "' class='scroll'></div>");
            jQuery("#" + subgrid_table_id).jqGrid({
                //url:"subgrid.php?q=2&id="+row_id, 
                datatype: "local",
                colNames: nombresSubGrid,
                colModel: columnasSubGrid,
                rowNum: global.rowNum,
                pager: pager_id,
                sortname: 'num',
                sortorder: "asc",
                height: '100%',
                autowidth: true,
                loadComplete: function(data) {
                    for (var i = 0; i <= mydataSubGrid.length; i++) {
                        jQuery("#" + subgrid_table_id).jqGrid('addRowData', i + 1, mydataSubGrid[i]);
                    }
                },
            });
        }
    });
}
function initArbolActividadesAsignar() {
    $("#arbolActividadesAgregar").fancytree({
        checkbox: true,
        selectMode: 3,
        //source:treeData,
        select: function(event, data) {
            var selKeys = $.map(data.tree.getSelectedNodes(), function(node) {
                if (!node.folder) {
                    return "{id:'" + node.key + "',nombre:'" + node.title + "'}";
                }
            });

        },
        keydown: function(event, data) {
            if (event.which === 32) {
                data.node.toggleSelected();
                return false;
            }
        },
        //las sgtes lineas opcionales, son requeridas si existe mas de un arbol en una pagina:
        //initId: "treeData",
        cookieId: "fancytree-Cb1",
        idPrefix: "fancytree-Cb1-"
    });

    $("#arbolActividadesAgregar").fancytree("getRootNode").visit(function(node) {
        node.setExpanded(true);
    });

}
function initArbolActividades() {
    $("#arbolActividades").fancytree({
        checkbox: true,
        selectMode: 3,
        //source:treeData,
        select: function(event, data) {
            var selKeys = $.map(data.tree.getSelectedNodes(), function(node) {
                if (!node.folder) {
                    return "{id:'" + node.key + "',nombre:'" + node.title + "'}";
                }
            });

        },
        keydown: function(event, data) {
            if (event.which === 32) {
                data.node.toggleSelected();
                return false;
            }
        },
        //las sgtes lineas opcionales, son requeridas si existe mas de un arbol en una pagina:
        //initId: "treeData",
        cookieId: "fancytree-Cb1",
        idPrefix: "fancytree-Cb1-"
    });

    $("#arbolActividades").fancytree("getRootNode").visit(function(node) {
        node.setExpanded(true);
    });

}
function initArbolProductosAsignar() {
    $("#arbolProductosAgregar").fancytree({
        checkbox: true,
        selectMode: 3,
        //source:treeData,
        select: function(event, data) {
            var selKeys = $.map(data.tree.getSelectedNodes(), function(node) {
                if (!node.folder) {
                    return "{id:'" + node.key + "',nombre:'" + node.title + "'}";
                }
            });

        },
        keydown: function(event, data) {
            if (event.which === 32) {
                data.node.toggleSelected();
                return false;
            }
        },
        //las sgtes lineas opcionales, son requeridas si existe mas de un arbol en una pagina:
        //initId: "treeData",
        cookieId: "fancytree-Cb1",
        idPrefix: "fancytree-Cb1-"
    });

    $("#arbolProductosAgregar").fancytree("getRootNode").visit(function(node) {
        node.setExpanded(true);
    });

}
function initArbolProductos() {
    $("#arbolProductos").fancytree({
        checkbox: true,
        selectMode: 3,
        //source:treeData,
        select: function(event, data) {
            var selKeys = $.map(data.tree.getSelectedNodes(), function(node) {
                if (!node.folder) {
                    return "{id:'" + node.key + "',nombre:'" + node.title + "'}";
                }
            });

        },
        keydown: function(event, data) {
            if (event.which === 32) {
                data.node.toggleSelected();
                return false;
            }
        },
        //las sgtes lineas opcionales, son requeridas si existe mas de un arbol en una pagina:
        //initId: "treeData",
        cookieId: "fancytree-Cb1",
        idPrefix: "fancytree-Cb1-"
    });

    $("#arbolProductos").fancytree("getRootNode").visit(function(node) {
        node.setExpanded(true);
    });

}