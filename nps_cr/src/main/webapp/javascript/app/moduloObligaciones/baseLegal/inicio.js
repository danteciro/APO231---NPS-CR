//inicializa todas las funciones ...(1)
$(function() {
    procesarGridProcedimiento();
    initInicio();
    gridBasesLegales();
    procesarGrid();
    procesarGrid1();
    changeCombo();
    initArbolActividadesAsignar(); 
    initArbolProductosAsignar();
    $('#checkboxAplica').change(busquedaAvanzadaBaseLegal);
        
});


//inicializa grid table/div
function procesarGrid1(){
    jQuery("#sg3").jqGrid({ 
        url:baseURL + "pages/baseLegal/find",
        datatype: "json", 
        postData:{
			codigoBaseLegal : $("txtCodigoBaseLegal").val(),
			descBaseLegal : $("txtDescBaseLegal").val() 
		},
        hidegrid: false,
        height: 140, 
        colNames:['ID BASE LEGAL','CODIGO BASE LEGAL', 'DESCRIPCION BASE LEGAL', 'Amount','Tax','Total','Notes'], 
        colModel:[ 
            {name:'idBaseLegal',index:'id', width:55,hidden:true}, 
            {name:'codigoBaseLegal',index:'invdate', width:90}, 
            {name:'descBaseLegal',index:'name', width:100}, 
            {name:'amount',index:'amount', width:80, align:"right",hidden:true}, 
            {name:'tax',index:'tax', width:80, align:"right",hidden:true}, 
            {name:'total',index:'total', width:80,align:"right",hidden:true}, 
            {name:'note',index:'note', width:150, sortable:false,hidden:true} ], 
        rowNum:8,
        rowList:[8,16,24,32], 
        pager: '#psg3', 
        sortname: 'id', 
        viewrecords: true, 
        sortorder: "desc", 
        multiselect: false, 
        subGrid: true, 
        caption: "Bases Legales",
        width: $("#busquedaBaseLegal").width(),
        jsonReader : {
            root : "filas", //la lista de registros a mostrar en el grid
            page : "pagina", //la pagina actual
            total : "total", //total de paginas
            records : "registros", //numero total de registros
            
            repeatitems : false,
            id : "idBaseLegal"
        },
        loadComplete: function(data) {
                        
            $('#gridContextBaseLegal').parent().remove();
            $('#gridDetalleBaseLegal').html("<ul id='gridContextBaseLegal'>"
                    + "<li> <a id='linkAtenderUnidad' data-icon='ui-icon-search' title='Ver Detalle'>Ver Detalle</a> </li>"
                    //+ "<li> <a id='linkEditarProcedimiento' data-icon='ui-icon-pencil' title='Editar'>Modificar</a></li>"
                    //+ "<li> <a id='linkEliminarProcedimiento' data-icon='ui-icon-trash' title='Editar'>Eliminar</a></li>"
                    //+ "<li> <a id='linkAgregarActProcedimiento' data-icon='ui-icon-note' title='Editar'>Agregar Tramite-Actividad</a></li>"
                    //+ "<li> <a id='linkAgregarActProcedimiento' data-icon='ui-icon-clipboard' title='Editar'>Gestionar Requisitos</a></li>"
                    + "</ul>");
            $('#gridContextBaseLegal').puicontextmenu({
                target: $('#psg3').find('tr').not('.ui-subgrid,.ui-subgrid tr')
            });
        },
        // define the icons in subgrid 
        subGridOptions: { 
        "plusicon" : "ui-icon-circle-plus",
        "minusicon" : "ui-icon-circle-minus", 
        "openicon" : "ui-icon-arrowreturn-1-e",
        // load the subgrid data only once 
        // and the just show/hide 
        "reloadOnExpand" : false, 
        // select the row when the expand column is clicked 
        "selectOnExpand" : true }, 
    subGridRowExpanded: function(subgrid_id, row_id) { 
        var subgrid_table_id, pager_id; 
        subgrid_table_id = subgrid_id+"_t"; 
        pager_id = "p_"+subgrid_table_id; 
        $("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
        jQuery("#"+subgrid_table_id).jqGrid({ 
            url:baseURL + "pages/baseLegal/find"+row_id,
            datatype: "json", 
            colNames: ['No','Item','Qty','Unit','Line Total'], 
            colModel: [ 
                {name:"num",index:"num",width:80,key:true}, 
                {name:"item",index:"item",width:130}, 
                {name:"qty",index:"qty",width:70,align:"right"}, 
                {name:"unit",index:"unit",width:70,align:"right"}, 
                {name:"total",index:"total",width:70,align:"right",sortable:false} ], 
            rowNum:10, 
            pager: pager_id, 
            sortname: 'num', 
            sortorder: "asc", 
            height: '100%' ,
            width:'100%'
        }); 
        jQuery("#"+subgrid_table_id).jqGrid('navGrid',"#"+pager_id,{edit:false,add:false,del:false}) 
        
    } 
}); 
jQuery("#sg3").jqGrid('navGrid','#psg3',{add:false,edit:false,del:false});
}
//inicializa grid div(jimmy)
function procesarGrid(){
	$("#gridBaseLegal1").html("");
	var grid = $("<table>",{"id" : "grid" });
	var pager = $("<div>",{"id":"paginacionBaseLegal"});
	$("#gridBaseLegal1").append(grid).append(pager);
	var nombres = ["ID BASE LEGAL","CODIGO BASE LEGAL","DESCRIPCION BASE LEGAL"];
	var columnas = [
	                {   name : "idBaseLegal",
	                    width : 150,
	                    sortable : false,
	                    hidden: false,
	                    align : "center"
	                    },
	                    {
	                    name : "codigoBaseLegal",
	                    width : 150,
	                    sortable : false,
	                    hidden: false,
	                    align : "center"
	                    },
	                    {
	                    name : "descBaseLegal",
	                    width : 500,
	                    sortable : false,
	                    hidden: false,
	                    align : "center"
	                    }	                    
	                    ];
	grid.jqGrid({
		url : baseURL + "pages/baseLegal/find",
		datatype:"json",
		postData:{
			codigoBaseLegal : $("txtCodigoBaseLegal").val(),
			descBaseLegal : $("txtDescBaseLegal").val() 
		},
		hidegrid : false,
        rowNum : 8,
        rownumbers:true,
        pager : "#paginacionBaseLegal",
        emptyrecords : "No se encontraron registros para los criterios ingresados",
        loadtext : "Cargando",
        colNames : nombres,
        colModel : columnas,
        height: 140,
        viewrecords : true,
        caption : "Bases Legales",
        width: $("#busquedaBaseLegal").width(),
        jsonReader : {
            root : "filas", //la lista de registros a mostrar en el grid
            page : "pagina", //la pagina actual
            total : "total", //total de paginas
            records : "registros", //numero total de registros
            repeatitems : false,
            id : "idBaseLegal"
        }, 
        
    	loadComplete: function(data){}          
    });
    
}
//funcion combo change show/hide
function changeCombo(){
       $("#cmbNormaBaseLegal").change(function() {
            var num = $('#cmbNormaBaseLegal option:selected').text();
            if (num == '--Seleccione--') {
                $("#text3").hide();
            } else if (num == 'Norma Técnica Peruana') {
            $("#text3").show();
            }else if (num == 'NFPA') {
            $("#text3").show();
            } 
       });
       $("#cmbbuNormaBaseLegal").change(function() {
             var num = $('#cmbbuNormaBaseLegal option:selected').text();
             
             
             if (num == '--Seleccione--') {
                $("#text4").hide();
            
             } else if (num == 'Norma Técnica Peruana') {
                 $("#text4").show();
             }else if (num == 'NFPA') {
               $("#text4").show();
             } 
       });
}
//inicializa componentes y variables
function initInicio(){
    
    $("#generarDescripcion").click(concatenarDescripcionBaseLegal);
    $("#btnNuevoBaseLegal").click(function(){
        $('#checkboxAplica').attr({checked:false});
        $('#textDescripcionBaseLegal').val("");
    });
    
    $("#options").puipanel({
            toggleable: true,
            collapsed:true
                    });
    $("#cmbAnoBaseLegal").spinner({max:2014},{min:1930});
    $("#cmbbuAnoBaseLegal").spinner({max:2014},{min:1930});
    $("#datepickervig").datepicker();
    $("#datepickerpub").datepicker();
    $("#datepickervig1").datepicker();
    $("#datepickerpub1").datepicker();
    $("#datepickerart1").datepicker();
    $("#datepickerart").datepicker();
    
    
    $("#btnClose").click(function(){
        $("#dialogBusquedaBaseLegal").dialog("close");
    });
    $("#btnClose1").click(function(){
        $("#dialogAgregarBaseLegal").dialog("close");
    });
    //regresa a la URL indicada
    $('#btnRegresar').click(function() {
        window.location.href = baseURL + 'pages/indice';
    });
    
    $("#dialogArbolBusqueda").dialog({
        resizable: false,
        autoOpen: false,
        height: "auto",
        width: "auto",
        dialogClass: 'dialog',
        modal: true
    });
    $("#dialogUpload").dialog({
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
    $("#dialogAgregarBaseLegal").dialog({
        resizable: false,
        autoOpen: false,
        height: "auto",
        width: "auto",
        dialogClass: 'dialog',
        modal: true
    });
    $("#dialogBusquedaBaseLegal").dialog({
        resizable: false,
        autoOpen: false,
        height: "auto",
        width: "auto",
        dialogClass: 'dialog',
        modal: true
    });
    $('#btnActividadClave1').click(function(){
        $("#dialogArbolBusqueda").dialog("open");
    });
    $('#btnProductoClave').click(function(){
        $("#dialogArbolBusquedaProducto").dialog("open");
    });
    $('#btnProductoClave1').click(function(){
        $("#dialogArbolBusquedaProducto").dialog("open");
    });
    $('#btnActividadClave').click(function(){
        $("#dialogArbolBusqueda").dialog("open");
    });
    $('#btnNuevoBaseLegal').click(function(){
        $("#dialogAgregarBaseLegal").dialog("open");
    });
    
    $('#imgUpload').click(function(){
        $("#dialogUpload").dialog("open");
    });
    
    $('#guardarUpload').click(function(){
        
        $("#imgcargar").show();
        $("#dialogUpload").dialog("close");
        
        $("#file_name").text($("#fileArchivo").val());
        $("#txtImgUpload").text($("#fileupload").val());
        
       
    });
   
  
    
   $('#btnBuscarAvan').click(function() {
        busquedaAvanzadaBaseLegal("BAP");
    });
       
}

function busquedaAvanzadaBaseLegal(tipo) {
    
    $.ajax({
        url: baseURL + "pages/baseLegal/busquedaAvanzadaBaseLegal",
        type: 'get',
        async: false,
        data: {
        },
        beforeSend: muestraLoading,
        success: function(data) {
            ocultaLoading();
            $("#dialogBusquedaAvanzadaBaseLegal").html(data);
            $("#dialogBusquedaAvanzadaBaseLegal").dialog({
                resizable: false,
                draggable: true,
                autoOpen: true,
                height: "auto",
                width: "auto",
                modal: true,
                dialogClass: 'dialog',
                title: "BUSQUEDA AVANZADA BASE LEGAL"
            });
        if(tipo=="BAP"){
            $("#divResultadoBusquedaAvanzadaBaseLegal").hide();
        }
        if($("#dialogBusquedaAvanzadaBaseLegal").dialog("open")){
            $('#checkboxAplica').attr({checked:false});
        }
        
        },
        error: errorAjax
    });

}
//inicializa grid(piro) ..(grid1)
function procesarGridProcedimiento() {
    var mydata = [
//        {Codigo_Base_Legal:"BL001",Desc_Base_Legal:"Art. 58º de Reglamento aprobado por D.S. Nº 030-1998-EM",Img_Legal:"a",tieneAct:1},
//        {Codigo_Base_Legal:"BL002",Desc_Base_Legal:"Anexo 2.3 C de Reglamento del Registro de Hidrocarburos aprobado por RCD N° 191-2011-OS/CD.",Img_Legal:"a",tieneAct:1},
//        {Codigo_Base_Legal:"BL003",Desc_Base_Legal:"Art. 94° de Reglamento aprobado por D.S. N° 054-1993-EM",Img_Legal:"a",tieneAct:1},
//        {Codigo_Base_Legal:"BL004",Desc_Base_Legal:"Art. 3º de D.S. N° 005-2012-EM.",Img_Legal:"a",tieneAct:1},
//        {Codigo_Base_Legal:"BL005",Desc_Base_Legal:"Art. 36° de Reglamento aprobado por D.S. N° 054-1993-EM.",Img_Legal:"a",tieneAct:1},
//        {Codigo_Base_Legal:"BL006",Desc_Base_Legal:"Art. 99° de reglamento aprobado por D.S. N° 019-1997-EM.",Img_Legal:"a",tieneAct:1},
//        {Codigo_Base_Legal:"BL007",Desc_Base_Legal:"Art. 43° Lit f) de Reglamento aprobado por D.S. Nº 015-2006-EM. ",Img_Legal:"a",tieneAct:1},
//        {Codigo_Base_Legal:"BL008",Desc_Base_Legal:"Art. 67° del reglamento aprobado por D.S. Nº 054-1993-EM.",Img_Legal:"a",tieneAct:1},
        {Codigo_Base_Legal:"BL009",Desc_Base_Legal:"Art. 11° Num. 3 de Reglamento Aprobado por D.S. N° 054-1993-EM y Modificatorias",tieneAct:1,Img_Legal:0},
        {Codigo_Base_Legal:"BL010",Desc_Base_Legal:"Art. 13° Num. 5 de Reglamento Aprobado por D.S. N° 054-1993-EM",tieneAct:1,Img_Legal:0},
        {Codigo_Base_Legal:"BL011",Desc_Base_Legal:"Art. 47° de Reglamento Aprobado por D.S. N° 054-1993-EM y Modificatorias",tieneAct:1,Img_Legal:0},
        {Codigo_Base_Legal:"BL012",Desc_Base_Legal:"Art. 9° de Reglamento Aprobado por D.S. N° 027-1994-EM",tieneAct:1,Img_Legal:0},
        {Codigo_Base_Legal:"BL013",Desc_Base_Legal:"Art. 11° de Reglamento Aprobado por D.S. N° 027-1994-EM",tieneAct:1,Img_Legal:0},
        {Codigo_Base_Legal:"BL014",Desc_Base_Legal:"Art. 12° de Reglamento Aprobado por D.S. N° 027-1994-EM",tieneAct:1,Img_Legal:0},
        {Codigo_Base_Legal:"BL015",Desc_Base_Legal:"D.S. N° 054-1993-EM",tieneAct:0,Img_Legal:1}
        
    ];
    var mydataSubGrid = {
//        1: [
//        {codigoSub:"OB100",descripcionSub:"Deberá contar con una póliza de Seguro Responsabilidad Civil Extracontractual vigente que cumpla con el monto requerido por la normativa.."}
//            ],
//        2:[
//          {codigoSub:"OB100",descripcionSub:"Deberá contar con una póliza de Seguro Responsabilidad Civil Extracontractual vigente que cumpla con el monto requerido por la normativa.."}
//        ],
//        3:[
//          {codigoSub:"OB101",descripcionSub:" Los Grifos Flotantes deberán contar como mínimo con dos (02) extintores portátiles, cuyo agente extintor sea de múltiple propósito tipo ABC, con una capacidad de extinción certificada no menor a 4A:80B:C Los extintores deberán estar certificados por Underwriters Laboratories - UL o entidad similar acreditada, por el INDECOPI o por un organismo extranjero de acreditación signatario de alguno de los Acuerdos de Reconocimiento Mutuo de la Internacional Accreditation Forum - IAF o la Inter American Accreditation Cooperation - IAAC., de acuerdo a la NTP 350.026, así como de las NTP 350.062-1, 350.062-2 y 350-062-3. Alternativamente, se aceptará extintores aprobados por Factory Mutual - FM que cumplan con la ANSI/UL 299 y cuya capacidad de extinción cumpla con la ANSI/UL 711."}
//           ],
//        4:[ 
//         {codigoSub:"OB101",descripcionSub:" Los Grifos Flotantes deberán contar como mínimo con dos (02) extintores portátiles, cuyo agente extintor sea de múltiple propósito tipo ABC, con una capacidad de extinción certificada no menor a 4A:80B:C Los extintores deberán estar certificados por Underwriters Laboratories - UL o entidad similar acreditada, por el INDECOPI o por un organismo extranjero de acreditación signatario de alguno de los Acuerdos de Reconocimiento Mutuo de la Internacional Accreditation Forum - IAF o la Inter American Accreditation Cooperation - IAAC., de acuerdo a la NTP 350.026, así como de las NTP 350.062-1, 350.062-2 y 350-062-3. Alternativamente, se aceptará extintores aprobados por Factory Mutual - FM que cumplan con la ANSI/UL 299 y cuya capacidad de extinción cumpla con la ANSI/UL 711."}
//           ],
//        5:[
//            {codigoSub:"OB101",descripcionSub:" Los Grifos Flotantes deberán contar como mínimo con dos (02) extintores portátiles, cuyo agente extintor sea de múltiple propósito tipo ABC, con una capacidad de extinción certificada no menor a 4A:80B:C Los extintores deberán estar certificados por Underwriters Laboratories - UL o entidad similar acreditada, por el INDECOPI o por un organismo extranjero de acreditación signatario de alguno de los Acuerdos de Reconocimiento Mutuo de la Internacional Accreditation Forum - IAF o la Inter American Accreditation Cooperation - IAAC., de acuerdo a la NTP 350.026, así como de las NTP 350.062-1, 350.062-2 y 350-062-3. Alternativamente, se aceptará extintores aprobados por Factory Mutual - FM que cumplan con la ANSI/UL 299 y cuya capacidad de extinción cumpla con la ANSI/UL 711."},
//            {codigoSub:"OB102",descripcionSub:"De acuerdo al resultado del Estudio de Riesgos, deberá disponer de extintores portátiles y rodantes, en número, calidad y tipo, de acuerdo a lo que indique la Norma Técnica Peruana Nº 350.043. Como mínimo deberá contar con dos (02) extintores portátiles de 12 kilogramos de capacidad, cuyo agente extintor sea de múltiple propósito ABC (polvo químico seco a base de monofosfato de amonio y con rating de extinción certificada -U.L. o NTP 350.062- no menor a 20:A:80 BC)."}
//       ],
//        6:[
//        {codigoSub:"OB102",descripcionSub:"De acuerdo al resultado del Estudio de Riesgos, deberá disponer de extintores portátiles y rodantes, en número, calidad y tipo, de acuerdo a lo que indique la Norma Técnica Peruana Nº 350.043. Como mínimo deberá contar con dos (02) extintores portátiles de 12 kilogramos de capacidad, cuyo agente extintor sea de múltiple propósito ABC (polvo químico seco a base de monofosfato de amonio y con rating de extinción certificada -U.L. o NTP 350.062- no menor a 20:A:80 BC)."},
//        {codigoSub:"OB103",descripcionSub:"Adicionalmente, deberá contar con un (1) extintor rodante de cincuenta kilogramos (50k) de capacidad, cuyo agente extintor sea de múltiple propósito ABC (polvo químico seco a base de monofosfato de amonio y con rating de extinción certificado-U.L. o NTP 350.043- no menor  a 40:A:240BC), que será colocado en el patio de maniobras."}
//          ],
//        7:[
//        {codigoSub:"OB104",descripcionSub:"Si el establecimiento está ubicado en una zona donde pueden ocurrir tormentas eléctricas deberá instalar un sistema pararrayos para proteger las instalaciones."}
//           ],
//        8:[
//        {codigoSub:"OB104",descripcionSub:"Si el establecimiento está ubicado en una zona donde pueden ocurrir tormentas eléctricas deberá instalar un sistema pararrayos para proteger las instalaciones."}
//           ],
           1:[
        {codigoSub:"OB105",descripcionSub:"¿Se encuentran los equipos de despacho, conexiones de entrada de los tanques y ventilaciones  más cercanas a una distancia mínima de  cincuenta metros (50.00 m) de los límites de propiedad de las construcciones o proyectos aprobados por la municipalidad para centros educativos, mercados, supermercados, hospitales, clínicas, iglesias, cines, teatros, cuarteles, zonas militares, comisarias o zonas policiales, establecimientos penitenciarios y lugares de espectáculos públicos que tengan Licencia Municipal o autorización equivalente para su funcionamiento?"}
           ],
           2:[
        {codigoSub:"OB106",descripcionSub:"Si el establecimiento está ubicado en carretera y existen intersecciones a nivel: ¿El lindero más próximo del establecimiento se ubica a una distancia del centro de intersección no menor de doscientos metros (200.00 m) para las carreteras de primera clase y cien metros (100.00 m) en las de segunda y tercera clase?"}
           ],
           3:[
        {codigoSub:"OB107",descripcionSub:"¿Se ubican los equipos de despacho o tanques de combustibles líquidos cumpliendo con la distancia mínima con respecto a la proyección horizontal de las líneas áreas que conduzcan electricidad según el siguiente cuadro?: "}
           ],
           4:[
        {codigoSub:"OB108",descripcionSub:"Para Planta Envasadora con capacidad de almacenamiento de GLP menor a 40000 kg: ¿Se ha colocado una puerta para ingreso y salida de vehículos, con un ancho no menor de cuatro (4) metros, además de una puerta independiente para uso del personal?"},
        {codigoSub:"OB109",descripcionSub:"Para Planta Envasadora con capacidad de almacenamiento de GLP mayor o igual a 40000 kg:¿Se ha colocado dos puertas, una para ingreso y otra para salida de vehículos, con un ancho no menor de cuatro (4) metros, además de una puerta independiente para uso del personal?"}
            ],
            5:[
        {codigoSub:"OB110",descripcionSub:"¿Las zonas de estacionamiento, circulación, protección y almacenamiento de la Planta Envasadora están despejadas y libres de pasto, plantas, desechos y cualquier otro material fácilmente combustible?"}
           ],
           6:[
        {codigoSub:"OB111",descripcionSub:"¿Los lugares destinados a estacionamientos de vehículos y las zonas de circulación en el interior de la Planta Envasadora se encuentran señalizados y libres de basura, materiales fácilmente combustible y de cualquier objeto que constituya estorbo para la circulación y/o estacionamiento?"}
           ]
        
        
    };
    var nombres = ['Código Base Legal','Descripción Base Legal','tieneAct','Descargar'];
    var columnas = [
        {name: "Codigo_Base_Legal",width: 130,sortable: false,align: "center"},
        {name: "Desc_Base_Legal",width: 800,sortable: false,align: "center"},
        {name: "tieneAct",width: 30,sortable: false,hidden: true,align: "center"},
        {name: "Img_Legal",width: 70,sortable: false,align: "center",formatter:img}
    ];
    var nombresSubGrid = ['Codigo Obligaciones','Descripción Obligaciones Relacionadas'];
    var columnasSubGrid = [
        {name: "codigoSub",width: 80,sortable: false,align: "center"},
        {name: "descripcionSub",width: 400,sortable: false,align: "justify"}];
    
    $("#gridMargin").html("");
    var grid = $("<table>", {
        "id": "gridMargin"
    });
    var pager = $("<div>", {
        "id": "paginacionProcedimiento"
    });
    $("#gridContenedorProcedimiento").append(grid).append(pager);

    grid.jqGrid({
        // url: baseURL + "pages/busquedaController/find",
        datatype: "local",
        /*postData: {
         codigoOsinerg: $("#txtCodigoOsinerg").val()
        },*/
        hidegrid: false,
        rowNum: 8,
        
        pager: "#paginacionProcedimiento",
        emptyrecords: "No se encontraron resultados",
        loadtext: "Cargando",
        colNames: nombres,
        colModel: columnas,
        height: "auto",
        viewrecords: true,
        caption: "Búsqueda Bases Legales",
        autowidth: true,
        onSelectRow: function(rowid, status) {
            
        },
        onRightClickRow: function(rowid, iRow, iCol, e) {
            var row = grid.jqGrid('getRowData', rowid);
            $('#linkVerProcedimiento').attr('onClick', 'verProcedimiento("")');
            $('#linkEditarProcedimiento').attr('onClick', 'editarProcedimiento("")');
            $('#linkEliminarProcedimiento').attr('onClick', 'eliminarProcedimiento("'+rowid+'")');
            $('#linkAgregarActProcedimiento').attr('onClick', 'agregarActProcedimiento("'+rowid+'","'+row.codigo+'","'+row.descripcion+'")');
        },
        loadComplete: function(data) {
            for (var i = 0; i <= mydata.length; i++) {
                jQuery("#gridMargin").jqGrid('addRowData', i + 1, mydata[i]);
            }
            
            $('#contextMenuProcedimiento').parent().remove();
            $('#divContextMenuProcedimiento').html("<ul id='contextMenuProcedimiento'>"
                    + "<li> <a id='linkAtenderUnidad' data-icon='ui-icon-search' title='Ver Detalle'>Ver Detalle</a> </li>"
                    //+ "<li> <a id='linkEditarProcedimiento' data-icon='ui-icon-pencil' title='Editar'>Modificar</a></li>"
                    //+ "<li> <a id='linkEliminarProcedimiento' data-icon='ui-icon-trash' title='Editar'>Eliminar</a></li>"
                    //+ "<li> <a id='linkAgregarActProcedimiento' data-icon='ui-icon-note' title='Editar'>Agregar Tramite-Actividad</a></li>"
                    //+ "<li> <a id='linkAgregarActProcedimiento' data-icon='ui-icon-clipboard' title='Editar'>Gestionar Requisitos</a></li>"
                    + "</ul>");
            $('#contextMenuProcedimiento').puicontextmenu({
                target: $('#gridMargin').find('tr').not('.ui-subgrid,.ui-subgrid tr')
            });
        },
        //SUBGRID
        subGrid: true, 
        afterInsertRow: function(rowid, aData, rowelem) {
            var rowData = grid.getRowData(rowid);
            if( rowData["tieneAct"]==0){
                $('tr#'+rowid, grid)
                .children("td.sgcollapsed")
                .html("")
                .removeClass('ui-sgcollapsed sgcollapsed');
            }
        },
        subGridOptions: { 
            "plusicon"  : "ui-icon-circle-plus",
            "minusicon" : "ui-icon-circle-minus",
            "openicon" : "ui-icon-arrowreturn-1-e", 
            "reloadOnExpand" : false, 
            "selectOnExpand" : true 
        }, 
        subGridRowExpanded: function(subgrid_id, row_id) { 
            var subgrid_table_id, pager_id; 
            subgrid_table_id = subgrid_id+"_t"; 
            pager_id = "p_"+subgrid_table_id; 
            $("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>"); 
            jQuery("#"+subgrid_table_id).jqGrid({ 
                //url:"subgrid.php?q=2&id="+row_id, 
                datatype: "local", 
                data: mydataSubGrid[row_id],
                colNames: nombresSubGrid,
                colModel: columnasSubGrid,
                rowNum:global.rowNum, 
                pager: pager_id, 
                sortname: 'num', 
                sortorder: "asc", 
                height: '100%',
                autowidth: true,
                onSelectRow: function(row_id, status) {
                    grid.resetSelection();
                },
                loadComplete: function(data) {
                    for (var i = 0; i <= mydataSubGrid.length; i++) {
                        jQuery("#"+subgrid_table_id).jqGrid('addRowData', i + 1, mydataSubGrid[i]);
                    }
                },
            }); 
        }
    });
}
//funcion imagen dentro del campo (grid1)
function img(imgs){
    
    if(imgs==1){
    var detalle="";
       detalle="<img src=\""+baseURL+"/images/stickers.png\" onclick=\"redirectPagina('/mgo/pages/baseLegal')\"  style=\"cursor: pointer;\" alt=\"detalle\" title=\"Detalle\" height=\"20\"  width=\"18\" />";   
       return detalle;
   }else{
       detalle="";
       return detalle;
   }

}

//Grid Bases Legales -- testing
function gridBasesLegales(){
    var mydata = [
        {Codigo_BaseLegal:"DBL001",Desc_BaseLegal:"Decreto Supremo N° 054-93-EM Reglamento Artículo 42°",tieneAct:0},
        {Codigo_BaseLegal:"DBL002",Desc_BaseLegal:"Decreto Supremo N° 054-93-EM Reglamento Artículo 43°",tieneAct:0},
        {Codigo_BaseLegal:"DBL003",Desc_BaseLegal:"Decreto Supremo N° 054-93-EM Reglamento Artículo 57°",tieneAct:0}
        
    ];
    
    var nombres = ['Código Base Legal','Descripción Base Legal','tieneAct'];
    var columnas = [
        {name: "Codigo_BaseLegal",width: 130,sortable: false,align: "center"},
        {name: "Desc_BaseLegal",width: 800,sortable: false,align: "center"},
        {name: "tieneAct",width: 30,sortable: false,hidden: true,align: "center"}
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
            $('#linkEliminarProcedimiento').attr('onClick', 'eliminarProcedimiento("'+rowid+'")');
            $('#linkAgregarActProcedimiento').attr('onClick', 'agregarActProcedimiento("'+rowid+'","'+row.codigo+'","'+row.descripcion+'")');
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
            if( rowData["tieneAct"]==0){
                $('tr#'+rowid, grid)
                .children("td.sgcollapsed")
                .html("")
                .removeClass('ui-sgcollapsed sgcollapsed');
            }
        },
        subGridOptions: { 
            "plusicon"  : "ui-icon-circle-plus",
            "minusicon" : "ui-icon-circle-minus",
            "openicon" : "ui-icon-arrowreturn-1-e", 
            "reloadOnExpand" : false, 
            "selectOnExpand" : true 
        }, 
        subGridRowExpanded: function(subgrid_id, row_id) { 
            var subgrid_table_id, pager_id; 
            subgrid_table_id = subgrid_id+"_t"; 
            pager_id = "p_"+subgrid_table_id; 
            $("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>"); 
            jQuery("#"+subgrid_table_id).jqGrid({ 
                //url:"subgrid.php?q=2&id="+row_id, 
                datatype: "local", 
                colNames: nombresSubGrid,
                colModel: columnasSubGrid,
                rowNum:global.rowNum, 
                pager: pager_id, 
                sortname: 'num', 
                sortorder: "asc", 
                height: '100%',
                autowidth: true,
                loadComplete: function(data) {
                    for (var i = 0; i <= mydataSubGrid.length; i++) {
                        jQuery("#"+subgrid_table_id).jqGrid('addRowData', i + 1, mydataSubGrid[i]);
                   }
                },
            }); 
        }
    });
}
//inicializa arbol
function initArbolActividadesAsignar(){
    $("#arbolActividadesAgregar").fancytree({
        checkbox: true,
        selectMode: 3,
        //source:treeData,
        select: function(event, data) {
            var selKeys = $.map(data.tree.getSelectedNodes(), function(node){
                if(!node.folder){
                    return "{id:'"+node.key+"',nombre:'"+node.title+"'}";
                }
            });
            
        },
        keydown: function(event, data) {
            if( event.which === 32 ) {
                data.node.toggleSelected();
                return false;
            }
        },
        //las sgtes lineas opcionales, son requeridas si existe mas de un arbol en una pagina:
        //initId: "treeData",
        cookieId: "fancytree-Cb1",
        idPrefix: "fancytree-Cb1-"
    });
    
    $("#arbolActividadesAgregar").fancytree("getRootNode").visit(function(node){
        node.setExpanded(true);
    });
    
}
function initArbolProductosAsignar(){
    $("#arbolProductosAgregar").fancytree({
        checkbox: true,
        selectMode: 3,
        //source:treeData,
        select: function(event, data) {
            var selKeys = $.map(data.tree.getSelectedNodes(), function(node){
                if(!node.folder){
                    return "{id:'"+node.key+"',nombre:'"+node.title+"'}";
                }
            });
            
        },
        keydown: function(event, data) {
            if( event.which === 32 ) {
                data.node.toggleSelected();
                return false;
            }
        },
        //las sgtes lineas opcionales, son requeridas si existe mas de un arbol en una pagina:
        //initId: "treeData",
        cookieId: "fancytree-Cb1",
        idPrefix: "fancytree-Cb1-"
    });
    
    $("#arbolProductosAgregar").fancytree("getRootNode").visit(function(node){
        node.setExpanded(true);
    });
    
}
//funcion concatenar

function json(){
    
}

function concatenarDescripcionBaseLegal(concatenado){
    
    var NormaLegal=$("#cmbTipoBaseLegal option:selected").text();
    var SiglaNormaLegal;
    if (NormaLegal == '--Seleccione--') {
        SiglaNormaLegal="";
    }else if (NormaLegal == 'Decreto Legislativo - DLeg.') {
        SiglaNormaLegal="DLeg.";
    }else if (NormaLegal === 'Decreto Supremo - DS') {
        SiglaNormaLegal="DS.";
    }else if (NormaLegal == 'Ley - Ley') {
        SiglaNormaLegal="Ley";
    }else if (NormaLegal == 'Resolución Directoral - RD') {
        SiglaNormaLegal="R.D.";
    }else if (NormaLegal == 'Resolución de Consejo Directivo - RCD') {
        SiglaNormaLegal="RCD.";
    }else if (NormaLegal == 'Resolución de Gerencia General - RGG') {
        SiglaNormaLegal="RGG.";
    }else if (NormaLegal == 'Resolución de Gerencia de Fiscalización de Hidrocarburos Líquidos - RGFHL') {
        SiglaNormaLegal="RGFHL.";
    }else if (NormaLegal == 'Resolución Ministerial - RM') {
        SiglaNormaLegal="RM.";
    }else if (NormaLegal == 'Resolución SubDirectoral - RSDIR') {
        SiglaNormaLegal="RSDIR.";
    } 
    
    var Numero=$("#txtseNumeroBaseLegal").val();
    var NumeroValidado;
    if(Numero==null){NumeroValidado="";}else{NumeroValidado=" N° "+Numero;}
    
    var Ano=$("#cmbAnoBaseLegal").spinner("value");
    var AnoValidado;
    if(Ano==null){AnoValidado="";}else{AnoValidado=" - "+Ano;}
    
    var Sigla=$("#cmbSiglaBaseLegal option:selected").text();
    var SiglaValidado;
    if (Sigla =='--Seleccione--') {SiglaValidado="";}else{SiglaValidado=" - "+Sigla;}
           
    var Articulo=$("#txtArticuloBaseLegal").val();
    var ArticuloValidado;
    if(Articulo==""){ArticuloValidado="";}else{ArticuloValidado=" " + "Art. "+Articulo;}
    
    var Inciso1=$("#txtIndice1BaseLegal").val();
    var Inciso1Validado;
    var Letra1;
    Letra1=Inciso1.charAt(0);
    if(Inciso1==""){
        Inciso1Validado="";
    }else{    
    if(isNaN(Letra1)){Inciso1Validado=" Lit. "+Inciso1;}else{Inciso1Validado=" Num. "+Inciso1;}
    }
    
    var Inciso2=$("#txtIndice2BaseLegal").val();
    var Inciso2Validado;
    var Letra2;
    Letra2=Inciso2.charAt(0);
    if(Inciso2==""){
        Inciso2Validado="";
    }else{
    if(isNaN(Letra2)){Inciso2Validado=" Lit. "+Inciso2;}else{Inciso2Validado=" Num. "+Inciso2;}
    }
    var TipoAnexo=$("#cmbBaseBaseLegal option:selected").text();
    var TipoAnexoValidado;
    if (TipoAnexo =='--Seleccione--') {TipoAnexoValidado="";}else{TipoAnexoValidado=" "+TipoAnexo +" Aprobado Por ";}
    
    var ArticuloAnexo=$("#txtArtDispBaseLegal").val();
    var ArticuloAnexoValidado;
    var Letra5=ArticuloAnexo.charAt(0);
    if(ArticuloAnexo==""){ArticuloAnexoValidado="";}else{ArticuloAnexoValidado="Art. N° "+ArticuloAnexo;}
    
    
    var Inciso3=$("#txtIndice3BaseLegal").val();
    var Inciso3Validado;
    var Letra3;
    Letra3=Inciso3.charAt(0);
    if(Inciso3==""){
        Inciso3Validado="";
    }else{
    if(isNaN(Letra3)){Inciso3Validado=" Lit. "+Inciso3;}else{Inciso3Validado=" Num. "+Inciso3;}
    }
    var Inciso4=$("#txtIndice4BaseLegal").val();
    var Inciso4Validado;
    var Letra4;
    Letra4=Inciso4.charAt(0);
    if(Inciso4==""){
        Inciso4Validado="";
    }else{
    if(isNaN(Letra4)){Inciso4Validado=" Lit. "+Inciso4;}else{Inciso4Validado=" Num. "+Inciso4;}
    }
    var NormaTecnica=$("#cmbbuNormaBaseLegal option:selected").text();
    var NormaTecnicaValidada;
    if (NormaTecnica ==='--Seleccione--') {NormaTecnicaValidada="";}else{NormaTecnicaValidada=" En Concordancia con "+NormaTecnica + " ";}
    
    var DescripcionNormaTecnica=$("#text4").val();
    
    var ModificatoriasValidado;
    if($("#checkboxMO").is(":checked")){ModificatoriasValidado=" y Modificatorias";}else{ModificatoriasValidado="";}
    
    var DescripcionModificatorias=$("#checkboxMO").val();
    
    var Concordancia=$("#checkboxAplica").val();
    
    var DescripcionConcordancia=$("#textDescripcionRegistrarBaseLegal").val();
    var concatenado;
    if(Articulo=="" && TipoAnexo=="--Seleccione--" && NormaTecnica=="--Seleccione--"){
      concatenado = SiglaNormaLegal + NumeroValidado + AnoValidado +  SiglaValidado;
    
   }else if(Articulo!="" && TipoAnexo=="--Seleccione--" && NormaTecnica=="--Seleccione--"){
        concatenado =   ArticuloValidado + Inciso1Validado + Inciso2Validado + " de " +
                        SiglaNormaLegal + NumeroValidado + AnoValidado + SiglaValidado + ModificatoriasValidado;
                
    }else if(Articulo=="" && TipoAnexo!="--Seleccione--" && NormaTecnica=="--Seleccione--"){
        concatenado = ArticuloAnexoValidado + Inciso3Validado + Inciso4Validado + " de " +  TipoAnexoValidado +SiglaNormaLegal + 
                        NumeroValidado + AnoValidado + SiglaValidado + ModificatoriasValidado;
    }else if(Articulo=="" && TipoAnexo=="--Seleccione--" && NormaTecnica!="--Seleccione--"){
        concatenado =   SiglaNormaLegal + NumeroValidado + AnoValidado + SiglaValidado+ ModificatoriasValidado 
                        + NormaTecnicaValidada + DescripcionNormaTecnica;
        
        
    }else if(Articulo=="" && TipoAnexo!="--Seleccione--" && NormaTecnica!="--Seleccione--"){
        concatenado =   ArticuloAnexoValidado + Inciso3Validado + Inciso4Validado +  TipoAnexoValidado +
                        SiglaNormaLegal + NumeroValidado + AnoValidado + SiglaValidado+ ModificatoriasValidado 
                        + NormaTecnicaValidada + DescripcionNormaTecnica;
        
    }else if(Articulo!="" && TipoAnexo!="--Seleccione--" && NormaTecnica!="--Seleccione--"){
        concatenado =   ArticuloAnexoValidado + Inciso3Validado + Inciso4Validado + " de "  + TipoAnexoValidado +
                        ArticuloValidado + Inciso1Validado + Inciso2Validado + " de " +
                        SiglaNormaLegal + NumeroValidado + AnoValidado + SiglaValidado+ ModificatoriasValidado 
                        + NormaTecnicaValidada + DescripcionNormaTecnica;
        
        
    }else if(Articulo!="" && TipoAnexo!="--Seleccione--" && NormaTecnica=="--Seleccione--"){
        concatenado =   ArticuloAnexoValidado + Inciso3Validado + Inciso4Validado + " de " +TipoAnexoValidado +
                        ArticuloValidado + Inciso1Validado + Inciso2Validado + " de " +
                        SiglaNormaLegal + NumeroValidado + AnoValidado + SiglaValidado+ ModificatoriasValidado;
        
        
    }else if(Articulo!="" && TipoAnexo=="--Seleccione--" && NormaTecnica!="--Seleccione--"){
        concatenado =   ArticuloValidado + Inciso1Validado + Inciso2Validado + " de " +
                        SiglaNormaLegal + NumeroValidado + AnoValidado + SiglaValidado+ ModificatoriasValidado+
                        NormaTecnicaValidada + DescripcionNormaTecnica;;
        
        
         
    }else{
        concatenado = "";
        
    }
    
    $("#textDescripcionBaseLegal").val(concatenado);
       
}