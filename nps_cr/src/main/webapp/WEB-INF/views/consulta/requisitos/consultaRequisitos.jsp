<%-- 
    Document   : consultaRequisitos
    Created on : 30/10/2014, 02:55:34 PM
    Author     : lchancayauri
--%>

<%@ include file="/common/taglibs.jsp"%>
<t:template pageTitle="Consulta Requisitos" scrollPanelTitle="">

    <jsp:attribute name="headArea">
        <script type="text/javascript" src='<c:url value="/javascript/app/requisitos/consultaRequisitos.js" />' charset="utf-8"></script>
        <style>
            #gridRequisitos td[aria-describedby="gridRequisitos_descripcion"]{
                white-space: normal !important;
                text-align: justify !important;
            }
        </style> 
        <script type="text/javascript">
            var ayudaActividad = '${P_ACTIVIDAD}';
            var ayudaRubro = '${P_RUBRO}';
            var ayudaEtapa = '${P_ETAPA}';
            var ayudaTramite = '${P_TRAMITE}';
            var ayudaMultiOperador = '${P_MULTIOPERADOR}';
        </script>
    </jsp:attribute>

    <jsp:attribute name="bodyArea">
        <div id="form_registro">
            <input type="hidden" id="txtIdProcedimiento" value="" />
            <div class="container">
                <div class="pui-panel ui-widget-content">
                    <div class="pui-panel-titlebar ui-corner-all">
                        <span class="ui-panel-title">Consulta Requisitos</span>
                        <span style="margin-left: 700px">Visita N°: ${P_CONTADOR_REQUISITOS}</span>
                    </div>
                    <div class="titua" align="center" style="color: #002c53;"><fmt:message key="consulta.requisito.titulo" /></div>
                    <div class="filaForm" style="margin-top: 5px;"></div>
                    <div style="color: #002c53;"><fmt:message key="consulta.requisito.bienvenido" /></div>
                    <div style="color: #002c53; text-align: justify"><fmt:message key="consulta.requisito.mensaje.bienvenida" /></div>
                    <br/>
                    <div align="right" style="color: #002c53;"><fmt:message key="consulta.requisito.mensaje.sugerencia" /> <a onclick="consultaRequisitos.abrirPopupMensaje()" ><img id='linkConsulta' style="cursor: pointer;" src='/nps/images/file_doc.png' height='20' width='18'></a></div>
                    <div align="right" style="color: #002c53;"><fmt:message key="consulta.requisito.mensaje.listaoficinas" /><a id="" href="http://www.osinergminorienta.gob.pe/web/ciudadano/encuentranos/oficinas" target="_blank" ><img id='linkOficinas' style="cursor: pointer;" src='/nps/images/localizacion.jpg' height='20' width='18'></a></div>
                    <div class="pui-panel-content ui-widget-content">
                        <fieldset>
                            <div class="tac">
                                <div class="form">
                                    <div class="filaForm">
                                        <div class=""><label>Seleccione la Actividad y Rubro a Consultar.</label></div>
                                    </div>
                                    <div class="filaForm">
                                        <div class="lble vam"><label>Actividades : </label></div>
                                        <div>
                                            <select id="cmbActividad" name="actividad" >
                                            </select>
                                            <img id="imgHelpActividad" src="/nps/images/pregunta.png" alt="Ayuda" height="16" width="16" class="btnAyuda" />
                                        </div>
                                    </div>

                                    <div class="filaForm" style="margin-top: 5px;"></div>
                                    <div id="dvRubro"class="filaForm">
                                        <div class="lble vam"><label>Agente y/o Instalaci&oacute;n : </label></div>
                                        <div>
                                            <select id="cmbRubro" name="rubro" >
                                            </select>
                                            <img id="imgHelpRubro" src="/nps/images/pregunta.png" alt="Ayuda" height="16" width="16" class="btnAyuda" />
                                        </div>
                                    </div>
                                </div>
                                </br>
                                <div class="form">
                                    <div class="filaForm" style="margin-top: 5px;"></div>
                                    <div id="dvTituloEtapa" class="filaForm">
                                        <div class=""><label>Seleccione la Etapa y trámite que desea Consultar.</label></div>
                                    </div>
                                    <div class="filaForm" style="margin-top: 5px;"></div>
                                    <div id="dvEtapa" class="filaForm">
                                        <div>
                                            <div style="float:left;" class="lble" ><label>Etapa : </label></div>
                                        </div>
                                        <div id="divEtapa">
                                        </div>
                                    </div>
                                </div>
                                </br>
                                <div class="form">
                                    <div class="filaForm" style="margin-top: 5px;"></div>
                                    <div id="dvTramite" class="filaForm">
                                        <div class="lble vam"><label>Tramite : </label></div>
                                        <div>
                                            <select id="cmbTramite" name="tramite" >
                                            </select>
                                            <img id="imgHelpTramite" src="/nps/images/pregunta.png" alt="Ayuda" height="16" width="16" class="btnAyuda" />
                                        </div>
                                    </div>
                                </div>
                                </br>
                                <div class="form">
                                    <div id="divPreguntas" >
                                    </div>
                                </div>
                                <div class="form tac">
                                    <div id="dvProcedimiento" class="filaForm tal ilb" style="width:630px;">
                                        <fieldset>
                                            <div class='filaForm' >
                                                <div class="lblc vat" style="width: 150px"><label>Costo del Trámite: </label></div>
                                                <div class="lblc">
                                                    <label id="lblCostoTramite"></label>
                                                </div>
                                                <div class="vat" style="width: 150px"><label>Plazo máximo de Atencion : </label></div>
                                                <div class="lblc">
                                                    <label id="lblPlazoAtencion"></label><label > días hábiles</label>
                                                </div>
                                            </div> 
                                            <div class='filaForm'>
                                                <div class="vat" style="width: 150px" ><label>Silencio Administrativo : </label></div>
                                                <div class="lblc">
                                                    <label id="lblSilencioAdministrativo" ></label>
                                                </div>
                                            </div> 
                                            <div class='filaForm'>
                                                <div  style="width: 600px" >
                                                    <p id="lblNotaIndicacionLegible" style="font-size: 10px;margin-bottom: 5px;"></p>
                                                    <p id="lblNotaIndicacionPlazo" style="font-size: 10px;margin-bottom: 5px;"></p>
                                                    <p id="lblNotaSilencioAdministrativo" style="font-size: 10px"></p>
                                                </div>
                                            </div> 
                                        </fieldset>                                                
                                    </div>

                                    <div id="dvRequisitos" class="ilb">
                                        <div align='right'>
                                            <label style='vertical-align: top; color: #002c53;'>Para descargar la información mostrada en la tabla, haga click sobre alguno de los siguientes botones:</label> 
                                            &nbsp;&nbsp;&nbsp;
                                            <a onclick="consultaRequisitos.downloadReporte(this,'excel')" ><img id='btnExcel' src='/nps/images/excel.png' title='Descargar en Excel' height='20' width='19'></a>
                                            &nbsp;&nbsp;&nbsp;
                                            <a onclick="consultaRequisitos.downloadReporte(this,'pdf')" ><img id='btnPdf' src='/nps/images/pdf.png'  title='Descargar en PDF' height='20' width='18'></a>
                                            &nbsp;&nbsp;&nbsp;
                                            <a onclick="consultaRequisitos.downloadReporte(this,'html')" ><img id='btnPrinter' src='/nps/images/printer.png' title='Imprimir' height='20' width='18'></a>
                                        </div>
                                        <table id="gridRequisitos"></table>
                                        </br>
                                        <div id="divComentarioProcedimiento">
                                            <table id="gridComentarioProcedimiento"></table>        
                                        </div>
                                    </div>
                                    
                                </div>
                                <div align="right" style="font-weight: bold;">
                                    (*)Valor UIT referencial actual :<label id="lblValorUIT"></label>                	
		                </div>					
                            </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
<!--    <div id="stack" class="ui-stack">
        <a title="Regresar" href="/consultas"></a>
    </div> -->
    
    <div id="divAyuda" style="width: 500px; height: 310px; background-color: white; border: solid; border-radius: 10px;  ">
        <!--<img id="imgAyuda" src="/nps/images/ayuda_descripcion.png" alt="Ayuda" height="93%" width="100%"  />-->
        <!--<br/>-->
        <img id="imgAyuda" src="/nps/images/ayuda.png" alt="Ayuda" height="196px" width="100%"  />
        <table height="22%">
            <tr>
                <td id="lblMensajeAyuda"></td>
            </tr>
        </table>
        <button style="margin-left: 3px" type="button" id="btnCerrarAyuda" >Cerrar</button>
    </div>

    <div id="dialogMensaje" class="dialog"></div>
    <div id="dialogOficinas" class="dialog">
        <div>        	
            <table class="tableCenter" width="95%">
                <tr>
                    <td><label>Listado de Oficinas de Atención</label></td>	               		
                </tr>
                <tr>
                    <td>
                        <img  src='/nps/images/listado_oficinas.png' >
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button id="btnCerrarOfinas" title="Cerrar" type="button">Cerrar</button>
                    </td>
                </tr>
            </table>  
        </div>
    </jsp:attribute>

</t:template>
