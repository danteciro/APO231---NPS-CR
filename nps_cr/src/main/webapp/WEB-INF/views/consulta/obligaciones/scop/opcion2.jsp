<%-- 
    Document   : opcion3
    Created on : 17/09/2014, 10:21:03 AM
    Author     : cflorian
--%>

<%@ include file="/common/taglibs.jsp"%>
<t:template pageTitle="Obligaciones" scrollPanelTitle="">

    <jsp:attribute name="headArea">
        <script type="text/javascript" src='<c:url value="/javascript/app/obligaciones/scop/opcion2.js" />' charset="utf-8"></script>
        <style>
            #gridObligacion td[aria-describedby="gridObligacion_descripcion"]{
                white-space: normal !important;
                text-align: justify !important;
            }
            
            #gridObligacion td[aria-describedby="gridObligacion_descripcionBaseLegal"]{
                white-space: normal !important;
                text-align: justify !important;
            }
            
            #gridObligacion td[aria-describedby="gridObligacion_descripcionSupervision"]{
                white-space: normal !important;
                text-align: justify !important;
            }
            
            #gridObligacion td[aria-describedby="gridObligacion_descripcionOligacionTipo"]{
                white-space: normal !important;
                text-align: justify !important;
            }

        </style> 
    </jsp:attribute>

    <jsp:attribute name="bodyArea">
        <div id="form_registro">
            <div class="container">
                <div class="pui-panel ui-widget-content">
                    <div class="pui-panel-titlebar ui-corner-all">
                        <span class="ui-panel-title">Consulta Obligaciones</span>
                        <span style="margin-left: 700px">Visita N°: ${P_CONTADOR}</span>
                    </div>
                    <div class="titua" align="center" style="color: #002c53;">Listado de Obligaciones Normativas</div>
                    <div style="color: #002c53;">Bienvenido, aquí usted podrá encontrar la normativa vigente que se encuentra obligado a cumplir. Para poder listar estas obligaciones, deberá seleccionar Actividad, Rubro y Obligaci&oacuten Tipo</div>
                    <br/>
                    <div align="right" style="color: #002c53;">Si desea dejarnos sus sugerencias o reportarnos alg&uacuten inconveniente, presione aqu&iacute: <a onclick="abrirPopupMensaje()"><img id='btnPdf' style="cursor: pointer;" src='/nps/images/file_doc.png' height='20' width='18'></a></div>
                    <div class="pui-panel-content ui-widget-content">
                        <fieldset>
                            <div class="tac">
                                <div class="form">
                                    <div id="divGrupoActividad" class="filaForm">
                                        <div class="lble vam"><label>Actividad : </label></div>
                                        <div>
                                            <select id="cmbGrupoActividad" name="grupoActividad">
	                                                <option value="">--Seleccione--</option>
                                            	<c:forEach items="${lstActividades}" var="actividad">
	                                                <option value="${actividad.idActividad}"
													<c:if test="${actividad.idActividad eq ACTIVIDAD}">selected="selected"</c:if>>${actividad.nombre}</option>
	                                            </c:forEach>
                                            </select>
                                            <img src="/nps/images/pregunta.png" alt="Ayuda" height="16" width="16" class="btnAyudaActividad" />
                                        </div>
                                    </div>
                                    <div class="filaForm" style="margin-top: 5px;"></div>
                                    <div id="dvRubro" class="filaForm">
                                        <div class="lble vam"><label>Rubro : </label></div>                                        
                                        <div>
                                            <select id="cmbRubro" name="actividad">
                                                <option value="">--Seleccione--</option>
                                                <c:forEach items="${lstRubros}" var="rubro">
	                                                <option value="${rubro.idActividad}"
													<c:if test="${rubro.idActividad eq RUBRO}">selected="selected"</c:if>>${rubro.nombre}</option>
	                                            </c:forEach>
                                            </select>
                                            <img src="/nps/images/pregunta.png" alt="Ayuda" height="16" width="16" class="btnAyudaRubro" />
                                        </div>
                                    </div>
                                    <div class="filaForm" style="margin-top: 5px;"></div>
                                    <div id="dvObligacionTipo" class="filaForm">
                                        <div class="lble vam"><label>Obligaci&oacuten Tipo : </label></div>
                                        <div>
                                            <select id="cmbObligacionTipo" name="tramite">
                                            </select>
                                            <img src="/nps/images/pregunta.png" alt="Ayuda" height="16" width="16" class="btnAyudaObligacionTipo" />
                                        </div>
                                    </div>                                                              
                                </div>
                                <br/>
                            </div>
                            <div align="right">
                            	Si desea ver las obligaciones normativas de otro rubro, haga clic <a href="/nps/pages/consultaObligaciones/opcion1" style="font-weight: bold;">aqu&iacute</a>
                            </div>
                    </fieldset>
                    </div>
                    <div id="botones">
                        <!--<button id="btnBuscarPara" title="Buscar Parametro Dinamico" class="btnSimple">Buscar</button>-->
                    </div>
                    <div id="botonesDerecha">
                        <!--<button id="btnNuevoPara" title="Nuevo Parámetro Dinámico">Nuevo</button>-->
                    </div>

                    <div class="gridMargin">
                        <div id="gridContenedorParametroDinamico"></div>
                        <div id="divContextMenuParametroDinamico"></div>
                    </div>
					<div id="divTipificacion">
						<div align="right" style="font-weight: bold;">
		                	(*) Valor UIT referencial actual : ${valorUIT}	                	
		                </div>
	                    <div align="right" style="font-weight: bold;">
		                	(*)La información de tipificación es conforme a lo establecido en:
		                </div>					
						<table id="tbBasesLegales" align="right"></table>
                	</div>
                </div>

            </div>
        </div>
    
    <div id="divAyudaActividad" style="width: 500px; height: 300px; background-color: white; border: solid; border-radius: 10px;  ">
        <img src="/nps/images/ayuda.png" alt="Ayuda" height="45%" width="100%"  />
        <table height="43%">
        	<tr>
        		<td>${P_ACTIVIDAD}</td>
        	</tr>
        </table>
        <button type="button" id="btnCerrarAyudaActividad" >Cerrar</button>
    </div>
    <div id="divAyudaRubro" style="width: 500px; height: 300px; background-color: white; border: solid; border-radius: 10px;  ">
        <img src="/nps/images/ayuda.png" alt="Ayuda" height="45%" width="100%"  />
        <table height="43%">
        	<tr>
        		<td>${P_RUBRO}</td>
        	</tr>
        </table>
        <button type="button" id="btnCerrarAyudaRubro" >Cerrar</button>
    </div>
    <div id="divAyudaObligacionTipo" style="width: 500px; height: 310px; background-color: white; border: solid; border-radius: 10px;  ">
        <img src="/nps/images/ayuda.png" alt="Ayuda" height="40%" width="100%"  />
        <table height="43%">
        	<tr>
        		<td>${P_OBLIGACION_TIPO}</td>
        	</tr>
        </table>
        <button type="button" id="btnCerrarAyudaObligacionTipo" >Cerrar</button>
    </div>
    <div id="dialogMensaje" class="dialog"></div>
</jsp:attribute>

</t:template>
