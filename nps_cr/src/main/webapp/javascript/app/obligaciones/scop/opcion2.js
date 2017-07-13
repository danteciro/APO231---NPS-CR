var obligacionOpcion2 = (function() {
	var path = "/nps/pages/consultaObligaciones";
	
    $(document).ready(function() {
    	var idRubro = $("#cmbRubro").val();
    	cargarObligacionTipo(idRubro);
    	procesarGridObligaciones();
	});

    // Public Methods
    function iniciarPagina() {
        inicializarComponentes();        
        configuracionAyuda();
    }
    
    // Private Methods
    function inicializarComponentes(){
        
    	$("#cmbGrupoActividad").prop("disabled", true );
        $("#cmbRubro").prop("disabled", true );

        $('#cmbGrupoActividad').change(function() {
            if (this.value !== "") {
                $("#dvRubro").show();
            } else {
                $("#dvRubro").hide();
                $("#cmbRubro").val("");
                $("#dvObligacionTipo").hide();
                $("#cmbObligacionTipo").val("");
                $("#dvObligaciones").hide();
            }
        });

        $('#cmbRubro').change(function() {
            if (this.value !== "") {
                $("#dvEtapa").show();
            } else {
                $("#dvEtapa").hide();
                $("#cmbObligacionTipo").val("");
                $("#dvObligaciones").hide();
            }
        });
        
        $('#cmbObligacionTipo').change(function() {
            if (this.value !== "") {
                $("#dvObligaciones").show();                
                procesarGridObligaciones();
                $('td[role="gridcell"]').css('white-space','normal');
            } else {
                $("#dvObligaciones").hide();
            }
        });
        
    }
    
    function cargarObligacionTipo(idActividad) {
        $.getJSON(path + "/obtenerObligacionTipo", {
            idActividad: idActividad,
            ajax: 'true'
        }, function(data) {
        	var html = '<option value="0">--Todos--</option>';        	
        	if(data == null){
        		$('#cmbObligacionTipo').html(html);
        		return;
        	}
        	
            var len = data.length;
            for (var i = 0; i < len; i++) {
                html += '<option value="' + data[i].idObligacionTipo + '">'
                        + data[i].nombre + '</option>';
            }
            $('#cmbObligacionTipo').html(html);
        });
        
    }
    
    function procesarGridObligaciones() {    	
        var idRubro = $("#cmbRubro").val();
        var idObligacionTipo = $('#cmbObligacionTipo').val();
        var desRubro = $("#cmbRubro option:selected").html();
        var desObligacionTipo = $("#cmbObligacionTipo option:selected").html();
        
        if (idObligacionTipo == null){ idObligacionTipo  = "0";}
        if (desRubro == null){ desRubro  = "";}
        if (desObligacionTipo == null){ desObligacionTipo  = "";}
        
        var colNames;
        var colModel;
        if(idObligacionTipo === null || idObligacionTipo == 0){
            idObligacionTipo = 0;
            colNames = ['N°','OBLIGACIÓN NORMATIVA','BASE LEGAL','TIPIFICACION</BR>(*)','MONTO EN UIT</BR>HASTA', 'OBLIGACION TIPO'];
            colModel = [
                {name:"idObligacion", width:30, sortable:false, align:"center", hidden:true},
                {name:"descripcion", width:600, sortable:false, align:"center", formatter:"imageDescripcion"},
                {name:"descripcionBaseLegal", width:210, sortable:false, align:"center"},
                {name:"tipificacion", width:110, sortable:false, align:"center"},
                {name:"monto", width:100, sortable:false, align:"center"}, 
                {name:"descripcionOligacionTipo", width:135, sortable:false, align:"center"}
            ];
        }else{
            colNames = ['N°','OBLIGACIÓN NORMATIVA','BASE LEGAL','TIPIFICACION</BR>(*)','MONTO EN UIT</BR>HASTA'];
            colModel = [ 
                {name:"idObligacion", width:30, sortable:false, align:"center", hidden:true},
                {name:"descripcion", width:600, sortable:false, align:"center", formatter:"imageDescripcion"},
                {name:"descripcionBaseLegal", width:210, sortable:false, align:"center"},
                {name:"tipificacion", width:110, sortable:false, align:"center"},
                {name:"monto", width:100, sortable:false, align:"center"}
            ];
        }
        
        $("#gridContenedorParametroDinamico").html("");
        var toolBar = "<div align='right'>";
        toolBar += "<label style='vertical-align: top; color: #002c53;'>Para descargar la información mostrada en la tabla, haga click sobre alguno de los siguientes botones:</label> <a href='consultaObligaciones/downloadFile?tipo=excel' ><img id='btnExcel' title='Descargar en Excel' src='/nps/images/excel.png' height='20' width='19'></a>";
        toolBar += "&nbsp;&nbsp;&nbsp;";
        toolBar += "<a href='consultaObligaciones/downloadFile?tipo=pdf'><img id='btnPdf' src='/nps/images/pdf.png' title='Descargar en Pdf' height='20' width='18'></a>";
        toolBar += "&nbsp;&nbsp;&nbsp;";
        toolBar += "<a href='consultaObligaciones/downloadFile?tipo=html' target='blank'><img id='btnPrinter' src='/nps/images/printer.png' height='20' width='18'></a>";
        toolBar += "</div>";
        $("#gridContenedorParametroDinamico").append(toolBar);

        var grid = $("<table>", {
            "id": "gridObligacion"
        });
        $("#gridContenedorParametroDinamico").append(grid);
        
        var url = baseURL + "pages/consultaObligaciones/obtenerGridObligaciones";        
	var postData = {loadonce:false, subGrid:false, rownumbers:false, idRubro:idRubro,idObligacionTipo:idObligacionTipo,idClasificacion:'0',idCriticidad:'0',desRubro:desRubro,desObligacionTipo:desObligacionTipo,desClasificacion:'',desCriticidad:''};
        var grid = jqgridRemote("gridObligacion", true, 30, colNames, colModel, url, postData, "index", "Listado de Obligaciones", afterLoadGrid,null);
        
        $("#divTipificacion").show();
    }
    
    
    jQuery.extend($.fn.fmatter, {
    	imageDescripcion: function(cellvalue, options, rowdata) {
    		if(rowdata.nombreDocAdjunto == ''){
    			return rowdata.descripcion;
    		}
    		else{
    			return rowdata.descripcion;// + "<img id='" + rowdata.idObligacion + "' src='"+ rowdata.archivo64 +"'>";
    		}        
        }
    });
    
    function configuracionAyuda() {
        $("#divAyudaRubro").hide();
        $("#divAyudaActividad").hide();
        $("#divAyudaObligacionTipo").hide();
        
        $("#divAyudaRubro").css({
            position: "fixed",
            top: "15%",
            left:"90%"
        });        
        $("#divAyudaActividad").css({
            position: "fixed",
            top: "15%",
            left:"90%"
        });        
        $("#divAyudaObligacionTipo").css({
            position: "fixed",
            top: "15%",
            left:"90%"
        });
    
        $(".btnAyudaRubro").click(function() {
        	$("#divAyudaActividad").hide();
            $("#divAyudaObligacionTipo").hide();
            
            $("#divAyudaRubro").show();
            $('#divAyudaRubro').animate({
            	'margin-left':'-304px'
            },1500);
        });
        
        $(".btnAyudaActividad").click(function() {
        	$("#divAyudaRubro").hide();
            $("#divAyudaObligacionTipo").hide();
            
            $("#divAyudaActividad").show();
            $('#divAyudaActividad').animate({
                'margin-left':'-304px'
            },1500);
        });
        
        $(".btnAyudaObligacionTipo").click(function() {
        	$("#divAyudaRubro").hide();
            $("#divAyudaActividad").hide();
            
            $("#divAyudaObligacionTipo").show();
            $('#divAyudaObligacionTipo').animate({
                'margin-left':'-304px'
            },1500);
        });
        
        $("#btnCerrarAyudaRubro").click(function() {
            $('#divAyudaRubro').animate({
               'margin-left':'355px'
            },1500);
       });
       $("#btnCerrarAyudaActividad").click(function() {
           $('#divAyudaActividad').animate({
               'margin-left':'355px'
            },1500);
      });
       $("#btnCerrarAyudaObligacionTipo").click(function() {
           $('#divAyudaObligacionTipo').animate({
               'margin-left':'355px'
            },1500);
      });
    }

    // Public API
    return {
        iniciarPagina: iniciarPagina
    };
})();

$(function() {
    obligacionOpcion2.iniciarPagina();
});

function afterLoadGrid(data){
	var registro;
	var alturaDivBaseLegal = 0;
	
	$("#tbBasesLegales").html("");
	
	for(var j=0;j<data.lstBasesLegales.length;j++){
		$("#divTipificacion").show();
		alturaDivBaseLegal = alturaDivBaseLegal + 40;
		 registro = {
		            indice : data.lstBasesLegales[j].idBaseLegal, 
		            baseLegal: data.lstBasesLegales[j].descripcionBaseLegal
		            }; 
		 var table = document.getElementById("tbBasesLegales");
		 var row = table.insertRow(j);
		 var cell1 = row.insertCell(0);
		 var cell2 = row.insertCell(1);
	
		cell1.innerHTML =registro.indice+".",
		cell2.innerHTML =registro.baseLegal;
	}
	
	document.getElementById("divTipificacion").style.height = alturaDivBaseLegal + "px";
}

function abrirPopupMensaje(){
var title = "Sugerencia";
    
    $.ajax({
        url:baseURL + "pages/consultaObligaciones/abrirPopupMensaje",
        type:'get',
        async:false,
        beforeSend:muestraLoading,
        success:function(data){
            ocultaLoading();
            $("#dialogMensaje").html(data);
            $("#dialogMensaje").dialog({
                resizable: false,
                draggable: false,
                autoOpen: true,
                height:"auto",
                width: "auto",
                modal: true,
                dialogClass: 'dialog',
                title: title
            });
        },
        error:errorAjax
    });
}

