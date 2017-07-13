var obligacionOpcion1 = (function() {
	var path = "/nps/pages/consultaObligaciones";
    // Public Methods
    function iniciarPagina() {
        
        inicializarComponentes();
        configuracionAyuda();
        $('td[role="gridcell"]').css('white-space','normal');
    }
    
    // Private Methods
    function inicializarComponentes(){
        
        $("#dvRubro").hide();
        $("#dvObligacionTipo").hide();
        $("#dvClasificacion").hide();
        $("#dvCriticidad").hide();
        $("#cmbGrupoActividad").val("");
        
        $('#cmbGrupoActividad').change(function() {
        	$("#gridContenedorParametroDinamico").html("");
            if (this.value !== "") {
                $("#dvRubro").show();
                cargarRubro(this.value);
                $("#cmbObligacionTipo").val("");
            } else {
                $("#dvRubro").hide();
                $("#cmbRubro").val("");
                $("#dvObligacionTipo").hide();
                $("#dvClasificacion").hide();
                $("#cmbObligacionTipo").val("");
                $("#dvCriticidad").hide();
                $("#cmbCriticidad").val("");
                $("#divTipificacion").hide();
            }
        });
        
        $('#cmbRubro').change(function() {
            if (this.value !== "") {
                $("#dvObligacionTipo").show();
                $("#dvClasificacion").show();
                $("#dvCriticidad").show();
                cargarObligacionTipo(this.value);                
                cargarCriticidad();                
                procesarGridObligaciones();                               
            } else {
                $("#dvObligacionTipo").hide();
                $("#dvClasificacion").hide();
                $("#cmbObligacionTipo").val("");
            }
        });
        
        $('#cmbObligacionTipo').change(function() {
            if (this.value !== "") {
                procesarGridObligaciones();
                $('td[role="gridcell"]').css('white-space','normal');
                cargarClasificacion($('#cmbRubro').val(),this.value);
            } 
        });
        
        $('#cmbClasificacion').change(function() {
            if (this.value !== "") {
                $("#dvClasificacion").show();                
                procesarGridObligaciones();
                $('td[role="gridcell"]').css('white-space','normal');
            } else {
                $("#dvClasificacion").hide();
            }
        }); 
        
        $('#cmbCriticidad').change(function() {
            if (this.value !== "") {
                $("#dvCriticidad").show();                
                procesarGridObligaciones();
                $('td[role="gridcell"]').css('white-space','normal');
            } else {
                $("#dvCriticidad").hide();
            }
        }); 
    }
    
    function cargarRubro(idGrupoActividad) {
        $.getJSON(path + "/obtenerActividades", {
            idGrupoActividad: idGrupoActividad,
            ajax: 'true',
            async: false
        }, function(data) {
            var html = '<option value="0">--Seleccione--</option>';        	
        	if(data == null){
        		$('#cmbRubro').html(html);
        		return;
        	}
        	
            var len = data.length;
            for (var i = 0; i < len; i++) {
                html += '<option value="' + data[i].idActividad + '">'
                        + data[i].nombre + '</option>';
            }
            html += '</option>';
            $('#cmbRubro').html(html);
        });
    }
    
    function cargarObligacionTipo(idActividad) {
        $.getJSON(path + "/obtenerObligacionTipo", {
            idActividad: idActividad,
            ajax: 'true',
            async: false
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
            cargarClasificacion(idActividad,$('#cmbObligacionTipo').val());
            
        });
    }
    
    function cargarClasificacion(idActividad,idObligacionTipo){
    	$.getJSON(path + "/obtenerClasificacionFiltro", {
    		idActividad: idActividad,
    		idObligacionTipo: idObligacionTipo,
            ajax: 'true',
            async : false
        }, function(data) {
        	var html = '<option value="0">--Todos--</option>';        	
        	if(data == null){
        		$('#cmbClasificacion').html(html);
        		return;
        	}
        	
            var len = data.length;
            for (var i = 0; i < len; i++) {
                html += '<option value="' + data[i].idMaestroColumna + '">'
                        + data[i].descripcion + '</option>';
            }
            $('#cmbClasificacion').html(html);
        });
    }
    
    function cargarCriticidad(){
    	$.getJSON(path + "/obtenerCriticidad", {
            ajax: 'true'
        }, function(data) {
        	var html = '<option value="0">--Todos--</option>';        	
        	if(data == null){
        		$('#cmbCriticidad').html(html);
        		return;
        	}
        	
            var len = data.length;
            for (var i = 0; i < len; i++) {
                html += '<option value="' + data[i].idMaestroColumna + '">'
                        + data[i].descripcion + '</option>';
            }
            $('#cmbCriticidad').html(html);
        });
    }
    
    function configuracionAyuda() {        
        $("#divAyudaRubro").hide();
        $("#divAyudaActividad").hide();
        $("#divAyudaObligacionTipo").hide();
        $("#divAyudaClasificacion").hide();
        $("#divAyudaCriticidad").hide();
        
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
        $("#divAyudaClasificacion").css({
            position: "fixed",
            top: "15%",
            left:"90%"
        });
        $("#divAyudaCriticidad").css({
            position: "fixed",
            top: "15%",
            left:"90%"
        });
    
        $(".btnAyudaRubro").click(function() {
            buscarTipoAyuda(2);
         	$("#divAyudaActividad").hide();
            $("#divAyudaObligacionTipo").hide();
            $("#divAyudaClasificacion").hide();
            $("#divAyudaCriticidad").hide();
            $("#divAyudaRubro").show();
            $('#divAyudaRubro').animate({
                'margin-left':'-295px'
            },1500);
        });
        $(".btnAyudaActividad").click(function() {
            buscarTipoAyuda(1);
        	$("#divAyudaRubro").hide();
            $("#divAyudaObligacionTipo").hide();
            $("#divAyudaClasificacion").hide();
            $("#divAyudaCriticidad").hide();
            
            $("#divAyudaActividad").show();
            $('#divAyudaActividad').animate({
                'margin-left':'-295px'
            },1500);
        });
        $(".btnAyudaObligacionTipo").click(function() {
            buscarTipoAyuda(5);
        	$("#divAyudaRubro").hide();
            $("#divAyudaActividad").hide();
            $("#divAyudaClasificacion").hide();
            $("#divAyudaCriticidad").hide();
            
            $("#divAyudaObligacionTipo").show();
            $('#divAyudaObligacionTipo').animate({
                'margin-left':'-295px'
            },1500);
        });
        $(".btnAyudaClasificacion").click(function() {
            buscarTipoAyuda(6);
        	$("#divAyudaRubro").hide();
            $("#divAyudaActividad").hide();
            $("#divAyudaObligacionTipo").hide();
            $("#divAyudaCriticidad").hide();
            
            $("#divAyudaClasificacion").show();
            $('#divAyudaClasificacion').animate({
            	'margin-left':'-490px'
            },1500);
        });
        $(".btnAyudaCriticidad").click(function() {
        	buscarTipoAyuda(7);
        	$("#divAyudaRubro").hide();
            $("#divAyudaActividad").hide();
            $("#divAyudaObligacionTipo").hide();
            $("#divAyudaClasificacion").hide();
            
            $("#divAyudaCriticidad").show();
            $('#divAyudaCriticidad').animate({
            	'margin-left':'-295px'
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
        $("#btnCerrarAyudaClasificacion").click(function() {
            $('#divAyudaClasificacion').animate({
                'margin-left':'355px'
             },1500);
       });
        $("#btnCerrarAyudaCriticidad").click(function() {
            $('#divAyudaCriticidad').animate({
                'margin-left':'355px'
             },1500);
       });
    }
    function loadComplete(data){    	
    	$(".ui-jqgrid-view th span.expandirHeader").parent().attr("rowspan",2);
    	$(".ui-jqgrid-view th span.ocultarHeader").parent().parent().hide();
    	$(".ui-jqgrid-view th span").addClass("cabeceraTabla");
    	afterLoadGrid(data); //lchancayauri
    }
    
    /* OSINE_SFS-600 - REQF-0015 - Inicio */
    function mostraIconoAjunto(idAdjunto){
    	var link;
    	if(idAdjunto==0){
    		return "";
    	}else{
    		link = "<a onclick='obligacionOpcion1.descargaArchivoAdjunto(this,"+idAdjunto+")'><img style='cursor: pointer;' src='"+baseURL+"images/download-adj.png' height='25' width='25'></a>";
    		return link;
    		//return '<img src="'+baseURL+'images/download-adj.png" alt="Descargar adjunto" style="cursor: pointer;" onclick="obligacionOpcion1.descargaArchivoAdjunto('+idAdjunto+');" height="25" width="25"  />';
    	}
    }
    function descargaArchivoAdjunto(obj, idDocumentoAdjunto){    	
    	console.log("download -- idDocumentoAdjunto =" + idDocumentoAdjunto);
        obj.href = baseURL + 'pages/consultaObligaciones/descargaArchivoAlfresco?idDocumentoAdjunto=' + idDocumentoAdjunto;
    }
    /* OSINE_SFS-600 - REQF-0015 - Fin */
    
    /*SRAMOS 10112015*/
    function procesarGridObligaciones() {
    	$("#divTipificacion").hide();
        var idRubro = $("#cmbRubro").val();
        var idObligacionTipo = $('#cmbObligacionTipo').val();
        var idClasificacion = $('#cmbClasificacion').val();
        var idCriticidad = $('#cmbCriticidad').val();
        var desRubro = $("#cmbRubro option:selected").html();
        var desObligacionTipo = $("#cmbObligacionTipo option:selected").html();
        var desClasificacion = $("#cmbClasificacion option:selected").html();
        var desCriticidad = $("#cmbCriticidad option:selected").html();
        if (idObligacionTipo == null){ idObligacionTipo  = "0";}
        if (idClasificacion == null){ idClasificacion  = "0";}
        if (idCriticidad == null){ idCriticidad  = "0";}
        
        if (desRubro == null){ desRubro  = "";}
        if (desObligacionTipo == null){ desObligacionTipo  = "";}
        if (desClasificacion == null){ desClasificacion  = "";}
        if (desCriticidad == null){ desCriticidad  = "";}
        
        var colNames;
        var colModel;
       	colNames = ['<span class="ocultarHeader">Item</span>','mostrarItem','rowSpanItem',
        	            '<span class="ocultarHeader">Obligación<br /> Normativa</span>','mostrarObligacionNormativa', 'rowSpanObligacionNormativa',
        	            '<span class="ocultarHeader">Base Legal</span>', 'mostrarBaseLegal', 'rowSpanBaseLegal',
        	            '<span class="ocultarHeader">Tipificación (*)</span>','mostrarTipificacion','rowSpanTipificacion',
        	            '<span class="ocultarHeader">Sanción</span>','mostrarSancion','rowSpanSancion',
        	            'Incumplimiento','mostrarIncumplimiento','rowSpanIncumplimiento',
        	            'Sanción <br /> Específica','mostrarsancionEspecifica','rowSpanSancionEspecifica',
        	            'Base Legal <br /> del Criterio <br />Específico','mostrarBaseLegalCriterioEspecifico','rowSpanBaseLegalCriterioEspecifico',
        	            '<span class="ocultarHeader">Criticidad</span>','mostrarCriticidad','rowSpanCriticidad',
       					'<span class="ocultarHeader">Adjunto</span>','mostrarAdjunto','rowSpanAdjunto'];
        	          
        	colModel = [ 
                        {name:"item", width:10, sortable:false, align:"center", hidden:false, 
                        	cellattr : function (rowId, val, rawObject, cm) {
                        					if(rawObject.mostrarItem==1){
                        						result = ' rowspan=' + '"' + rawObject.rowSpanItem + '"';
                        					}else{
                        						result = ' style="display: none;"';
                        					}
        					                return result;
        					           }
                        },
                        {name:"mostrarItem", width:30, sortable:false, align:"center", hidden:true},
                        {name:"rowSpanItem", width:30, sortable:false, align:"center", hidden:true},
                        
                        {name:"obligacionNormativa", width:75, sortable:false, align:"left", hidden:false,
                        	cellattr : function (rowId, val, rawObject, cm) {
            					if(rawObject.mostrarObligacionNormativa==1){
            						result = ' rowspan=' + '"' + rawObject.rowSpanObligacionNormativa + '"';
            					}else{
            						result = ' style="display: none;"';
            					}
        		                return result;
        		           }
                        },
                        {name:"mostrarObligacionNormativa", width:30, sortable:false, align:"center", hidden:true},
                        {name:"rowSpanObligacionNormativa", width:30, sortable:false, align:"center", hidden:true},
                        
                        {name:"baseLegal", width:35, sortable:false, align:"left", hidden:false,
                        	cellattr : function (rowId, val, rawObject, cm) {
        								if(rawObject.mostrarBaseLegal==1){
        									result = ' rowspan=' + '"' + rawObject.rowSpanBaseLegal + '"';
        								}else{
        									result = ' style="display: none;"';
        								}
        				                return result;
        		                	}
                        },
                        {name:"mostrarBaseLegal", width:30, sortable:false, align:"center", hidden:true},
                        {name:"rowSpanBaseLegal", width:30, sortable:false, align:"center", hidden:true},
                        
                        {name:"tipificacion", width:15, sortable:false, align:"center", hidden:false,
                        	cellattr : function (rowId, val, rawObject, cm) {
        						if(rawObject.mostrarTipificacion==1){
        							
        							if(rawObject.rowSpanTipificacion==0)
        							{
        								result = ' rowspan="1"';// + '"' + rawObject.rowSpanTipificacion + '"';
        							}
        							else
        							{
        								result = ' rowspan=' + '"' + rawObject.rowSpanTipificacion + '"';
        								
        							}
        						}else{
        							result = ' style="display: none;"';
        						}
        		                return result;
        	            	}
                        },
                        {name:"mostrarTipificacion", width:30, sortable:false, align:"center", hidden:true},                
                        {name:"rowSpanTipificacion", width:30, sortable:false, align:"center", hidden:true},
                        
                        {name:"sancion", width:20, sortable:false, align:"center", hidden:false,
                        	cellattr : function (rowId, val, rawObject, cm) {
        						if(rawObject.mostrarSancion==1){
        							if(rawObject.rowSpanSancion==0)
        							{
        								result = ' rowspan="1"';
        							}
        							else
        							{
        								result = ' rowspan=' + '"' + rawObject.rowSpanSancion + '"';
        							}
        						}else{
        							result = ' style="display: none;"';
        						}
        		                return result;
        	            	}
                        },
                        {name:"mostrarSancion", width:30, sortable:false, align:"center", hidden:true},
                        {name:"rowSpanSancion", width:30, sortable:false, align:"center", hidden:true},
                        
                        {name:"incumplimiento", width:30, sortable:false, align:"center", hidden:false,
                        	cellattr : function (rowId, val, rawObject, cm) {
        						if(rawObject.mostrarIncumplimiento==1)
        						{
        							if(rawObject.rowSpanIncumplimiento==0)
        							{
        								result = ' rowspan="1"';
        							}
        							else
        							{        								
        								result = ' rowspan=' + '"' + rawObject.rowSpanIncumplimiento + '"';
        							}
        							
        						}else{
        							result = ' style="display: none;"';
        						}
        		                return result;
        	            	}
                        },
                        {name:"mostrarIncumplimiento", width:30, sortable:false, align:"center", hidden:true},
                        {name:"rowSpanIncumplimiento", width:30, sortable:false, align:"center", hidden:true},
                        
                        {name:"sancionEspecifica", width:20, sortable:false, align:"center", hidden:false,
                        	cellattr : function (rowId, val, rawObject, cm) {
        						if(rawObject.mostrarsancionEspecifica==1)
        						{
        							
        							if(rawObject.rowSpanSancionEspecifica==0)
        							{
        								result = ' rowspan="1"';
        							}
        							else
        							{        								
        								result = ' rowspan=' + '"' + rawObject.rowSpanSancionEspecifica + '"';
        							}        							        							
        							
        						}else{
        							result = ' style="display: none;"';
        						}
        		                return result;
        	            	}
                        },
                        {name:"mostrarsancionEspecifica", width:30, sortable:false, align:"center", hidden:true},
                        {name:"rowSpanSancionEspecifica", width:30, sortable:false, align:"center", hidden:true},
                        
                        {name:"baseLegalCriterioEspecifico", width:30, sortable:false, align:"center", hidden:false,
                        	cellattr : function (rowId, val, rawObject, cm) {
        						if(rawObject.mostrarBaseLegalCriterioEspecifico==1)
        						{
        							if(rawObject.rowSpanBaseLegalCriterioEspecifico==0)
        							{
        								result = ' rowspan="1"';
        							}
        							else
        							{        								
        								result = ' rowspan=' + '"' + rawObject.rowSpanBaseLegalCriterioEspecifico + '"';
        							} 
        							        						
        						}else{
        							result = ' style="display: none;"';
        						}
        		                return result;
        	            	}
                        },
                        {name:"mostrarBaseLegalCriterioEspecifico", width:30, sortable:false, align:"center", hidden:true},
                        {name:"rowSpanBaseLegalCriterioEspecifico", width:30, sortable:false, align:"center", hidden:true},
                        
                        {name:"criticidad", width:15, sortable:false, align:"center", hidden:false,
                        	cellattr : function (rowId, val, rawObject, cm) {
        						if(rawObject.mostrarCriticidad==1)
        						{
        							
        							
        							if(rawObject.rowSpanCriticidad==0)
        							{
        								result = ' rowspan="1"';
        							}
        							else
        							{        								
        								result = ' rowspan=' + '"' + rawObject.rowSpanCriticidad + '"';
        							} 
        							
        						}else{
        							result = ' style="display: none;"';
        						}
        		                return result;
        	            	}
                        },
                        {name:"mostrarCriticidad", width:30, sortable:false, align:"center", hidden:true},
                        {name:"rowSpanCriticidad", width:30, sortable:false, align:"center", hidden:true},
                        /* OSINE_SFS-600 - REQF-0015 - Inicio */
                        {name:"adjunto", width:15, sortable:false, align:"center", hidden:false,
                        	formatter: mostraIconoAjunto,
                        	cellattr : function (rowId, val, rawObject, cm) {
        						if(rawObject.mostrarAdjunto==1)
        						{        							        					
        							if(rawObject.rowSpanAdjunto==0)
        							{
        								result = ' rowspan="1"';
        							}
        							else
        							{        								
        								result = ' rowspan=' + '"' + rawObject.rowSpanAdjunto + '"';
        							} 
        							
        						}else{
        							result = ' style="display: none;"';
        						}
        		                return result;
        	            	}
                        },
                        {name:"mostrarAdjunto", width:30, sortable:false, align:"center", hidden:true},
                        {name:"rowSpanAdjunto", width:30, sortable:false, align:"center", hidden:true}
                        /* OSINE_SFS-600 - REQF-0015 - Fin */
                    ];
        
        $("#gridContenedorParametroDinamico").html("");
        var toolBar = "<div align='right'>";
        toolBar += "<label style='vertical-align: top; color: #002c53;'>Para descargar la información mostrada en la tabla, haga click sobre alguno de los siguientes botones:</label> <a href='consultaObligaciones/downloadFile?tipo=excel' ><img id='btnExcel' title='Descargar en Excel' src='/nps/images/excel.png' height='20' width='19'></a>";
        toolBar += "&nbsp;&nbsp;&nbsp;";
        toolBar += "<a href='consultaObligaciones/downloadFile?tipo=pdf' ><img id='btnPdf' src='/nps/images/pdf.png' title='Descargar en Pdf' height='20' width='18'></a>";
        toolBar += "&nbsp;&nbsp;&nbsp;";
        toolBar += "<a href='consultaObligaciones/downloadFile?tipo=html' target='blank'><img id='btnPrinter' src='/nps/images/printer.png' height='20' width='18'></a>";
        toolBar += "</div>";       
        $("#gridContenedorParametroDinamico").append(toolBar);
        
        var grid = $("<table>", {
            "id": "gridObligacion"
        });
        $("#gridContenedorParametroDinamico").append(grid);
   
        var url = baseURL + "pages/consultaObligaciones/obtenerGridObligaciones2";	
	    var postData = {loadonce:false, subGrid:false, rownumbers:false, idRubro:idRubro,idObligacionTipo:idObligacionTipo,idClasificacion:idClasificacion,idCriticidad:idCriticidad,desRubro:desRubro,desObligacionTipo:desObligacionTipo,desClasificacion:desClasificacion,desCriticidad:desCriticidad};     
	    var grid = jqgridRemote("gridObligacion", true, 500, colNames, colModel, url, postData, "index", "Listado de Obligaciones", loadComplete,null);
	 grid.jqGrid('setGroupHeaders', {
		  useColSpanStyle: false, 
		  groupHeaders:[
		    {startColumnName: 'item', numberOfColumns: 1, titleText: '<span class="expandirHeader">Item</span>'},
		    {startColumnName: 'obligacionNormativa', numberOfColumns: 1, titleText: '<span class="expandirHeader">Obligación<br /> Normativa</span>'},
		    {startColumnName: 'baseLegal', numberOfColumns: 1, titleText: '<span class="expandirHeader">Base Legal</span>'},
		    {startColumnName: 'tipificacion', numberOfColumns: 1, titleText: '<span class="expandirHeader">Tipificación (*)</span>'},
		    {startColumnName: 'sancion', numberOfColumns: 1, titleText: '<span class="expandirHeader">Sanción</span>'},				    
			{startColumnName: 'incumplimiento', numberOfColumns: 9, titleText: '<span>Criterios Específicos</span>'},
			{startColumnName: 'criticidad', numberOfColumns: 1, titleText: '<span class="expandirHeader">Criticidad</span>'},
			/* OSINE_SFS-600 - REQF-0015 - Inicio */
			{startColumnName: 'adjunto', numberOfColumns: 1, titleText: '<span class="expandirHeader">Adjunto</span>'},
			/* OSINE_SFS-600 - REQF-0015 - Fin */
		  ]
		});
	
    }
    
   
    // Public API
    return {
        procesarGridObligaciones:procesarGridObligaciones,
        iniciarPagina: iniciarPagina,
        descargaArchivoAdjunto: descargaArchivoAdjunto
    };
})();

///joel borrar
function buscarTipoAyuda(ayuda){
	var retorno=null;
	 ayuda=""+ayuda;
	 descripcion="";
    $.ajax({
    	
        url: baseURL + "pages/consultaObligaciones/cargar",
        type: 'post',
        async: false,
        data: {ayuda: ayuda,
        	descripcion: descripcion},
        beforeSend: muestraLoading,
        success: function(data) {
            if (data.ok == 1) {
            	if (ayuda==1) {$('#textActividad').html(data.descripcion);}
            	if (ayuda==2) {$('#textRubro').html(data.descripcion);}
            	if (ayuda==5) {$('#textObligacion').html(data.descripcion);}
            	if (ayuda==6) {$('#textClasificacion').html(data.descripcion);}
            	if (ayuda==7) {$('#textCriticidad').html(data.descripcion);}
            }else{
            	$('#textRubro').text("error");	
            }
            ocultaLoading();
        },
        //error: errorAjax
       
    });
}


$(function() {
	$("#divTipificacion").hide();
    obligacionOpcion1.iniciarPagina();
});
function afterLoadGrid(data){
	var registro;
	var alturaDivBaseLegal = 0;
	$("#tbBasesLegales").html("");
	$('#pager_gridObligacion_center').css('display','none');
	$("#divTipificacion").show();
	alturaDivBaseLegal = 20;
	for(var j=0;j<data.lstBasesLegales.length;j++){

		alturaDivBaseLegal = alturaDivBaseLegal + 40;
		registro = {
		          
					indice : (j+1),
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
	$("#lblUIT").html(data.valorUIT);
	
	filasGridObligaciones = $("#gridObligacion").getRowData();
    if(filasGridObligaciones.length != undefined && filasGridObligaciones.length != 0){
    	$('#leyendaBusqObligaciones').css('display','');
    }else{
    	$('#leyendaBusqObligaciones').css('display','none');
    } 
    $('.ui-paging-info').css('display','none');
}
jQuery.extend($.fn.fmatter, {
	imageDescripcion: function(cellvalue, options, rowdata) {
		if(rowdata.idDocAdjunto == 0){
			return rowdata.descripcion;
		}
		else{
			return rowdata.descripcion;// + "<img id='" + rowdata.idObligacion + "' src='"+ rowdata.archivo64 +"'>";
		}        
    }
});

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

//Busqueda_findTipoSancion_lgarcia
function cargarTipoSancion(){
	
	$.ajax({
		//ReferenciaTipoSancionController
        url:baseURL + "pages/consultaObligaciones/findTipoSancion",
        type:'POST',
        async:false,        
        success:
        	function(data){
	        	$('#descTipSanc1').html(data.descTipSanc1);
	        	$('#descTipSanc2').html(data.descTipSanc2);
	        	$('#descTipSanc3').html(data.descTipSanc3);
	        	$('#descTipSanc4').html(data.descTipSanc4);
	        	$('#descTipSanc5').html(data.descTipSanc5);
	        	$('#descTipSanc6').html(data.descTipSanc6);
	        	$('#descTipSanc7').html(data.descTipSanc7);
	        	$('#descTipSanc8').html(data.descTipSanc8);
        	},
        error:errorAjax
	});
}

//Busqueda_findValorUit_lgarcia
function cargarValorUIT(){
	
	$.ajax({
		//ReferenciaTipoSancionController
        url:baseURL + "pages/consultaObligaciones/findValorUit",
        type:'POST',
        async:false,        
        success:
        	function(data){
	        	$('#valorUIT').html(data.valorUIT);
        	},
        error:errorAjax
	});
}

//inicializacion
$(function() {
	cargarTipoSancion();
	cargarValorUIT();
});
