<%-- 
    Document   : opcion1
    Created on : 11/09/2014, 11:22:00 AM
    Author     : lchancayauri
--%>

<%@ include file="/common/taglibs.jsp"%>
<t:templatePublic pageTitle="Obligaciones" scrollPanelTitle="">

    <jsp:attribute name="headArea">
        <script type="text/javascript" src='<c:url value="/javascript/app/obligaciones/opcion1.js" />' charset="utf-8"></script>
        <style>
            #gridObligacion td[aria-describedby="gridObligacion_descripcion"]{
                white-space: normal !important;
                text-align: justify !important;
            }
            
            #gridObligacion td[aria-describedby="gridObligax	cion_descripcionBaseLegal"]{
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
            #gridContenedorParametroDinamico .ui-jqgrid tr.jqgrow td{
                white-space: inherit;
/*                 text-align :justify !important; */
            }
            
        </style> 
    </jsp:attribute>

    <jsp:attribute name="bodyArea">
        <div id="form_registro">
            <div class="container">
                <div class="pui-panel ui-widget-content">
                    <div class="pui-panel-titlebar ui-corner-all">
                        <span class="ui-panel-title">Consulta Obligaciones</span>
                        <span style="margin-left: 700px">Visita N°: ${P_CONTADOR_OBLIGACIONES}</span>
                    </div>
                    <div class="titua" align="center" style="color: #002c53;">Listado de Obligaciones Normativas de Hidrocarburos L&iacute;quidos</div>
                    <div style="color: #002c53;text-align:center;">Bienvenido, aqu&iacute usted podr&aacute encontrar un listado con las principales disposiciones normativas que se encuentra obligado a cumplir.</div>
                    <br/>
                    <div align="right" style="color: #002c53;">Si desea dejarnos sus sugerencias o reportarnos alg&uacuten inconveniente, presione aqu&iacute: <a onclick="abrirPopupMensaje()"><img id='btnPdf' style="cursor: pointer;" src='/nps/images/file_doc.png' height='20' width='18'></a></div>
                    <div class="pui-panel-content ui-widget-content">
                        <fieldset>
                            <div class="tac">
                                <div class="form">
                                    <div id="divGrupoActividad" class="filaForm">
                                        <div class="lble vam"><label>Actividades : </label></div>
                                        <div>
                                            <select id="cmbGrupoActividad" name="grupoActividad">
                                            	<option value="">--Seleccione--</option>
                                            	<c:forEach items="${lstActividades}" var="actividad">
	                                                <option value="${actividad.idActividad}"
													<c:if test="${actividad.idActividad eq ACTIVIDAD}">selected="selected"</c:if>>${actividad.nombre}</option>
	                                            </c:forEach>
                                            </select>
                                            <img src="/nps/images/pregunta.png" alt="Ayuda" height="16" width="16" class="btnAyudaActividad" name="1"/>
                                            
                                        </div>
                                    </div>
                                    <div class="filaForm" style="margin-top: 5px;"></div>
                                    <div class="filaForm" style="margin-top: 5px;"></div>
                                    <div id="dvRubro" class="filaForm">
                                        <div class="lble vam"><label>Agente y/o instalaci&oacute;n : </label></div>
                                        <div>
                                            <select id="cmbRubro" name="actividad">
                                            	<option value="">--Seleccione--</option>
                                                <c:forEach items="${lstRubros}" var="rubro">
	                                                <option value="${rubro.idActividad}"
													<c:if test="${rubro.idActividad eq RUBRO}">selected="selected"</c:if>>${rubro.nombre}</option>
	                                            </c:forEach>
                                            </select>
                                            <img src="/nps/images/pregunta.png" alt="Ayuda" height="16" width="16" class="btnAyudaRubro" name="2"/>
                                        </div>
                                    </div>
                                    <div class="filaForm" style="margin-top: 5px;"></div>
                                    <div id="dvObligacionTipo" class="filaForm">
                                        <div class="lble vam"><label>Obligaci&oacuten Tipo : </label></div>
                                        <div>
                                            <select id="cmbObligacionTipo" name="tramite">
                                            </select>
                                            <img src="/nps/images/pregunta.png" alt="Ayuda" height="16" width="16" class="btnAyudaObligacionTipo" name="5"/>
                                        </div>
                                    </div>  
                                    <div class="filaForm" style="margin-top: 5px;"></div>
                                    <div id="dvClasificacion" class="filaForm">
                                        <div class="lble vam"><label>Clasificaci&oacuten : </label></div>
                                        <div>
                                            <select id="cmbClasificacion" name="tramite">
                                            </select>
                                            <img src="/nps/images/pregunta.png" alt="Ayuda" height="16" width="16" class="btnAyudaClasificacion" name="6"/>
                                        </div>
                                    </div>
                                    <div class="filaForm" style="margin-top: 5px;"></div>
                                    <div id="dvCriticidad" class="filaForm">
                                        <div class="lble vam"><label>Criticidad : </label></div>
                                        <div>
                                            <select id="cmbCriticidad" name="tramite">
                                            </select>
                                            <img src="/nps/images/pregunta.png" alt="Ayuda" height="16" width="16" class="btnAyudaCriticidad" />
                                        </div>
                                    </div>                                
                                </div>
                                <br/>                                
                            </div>
                    </fieldset>
                    </div>
                    <div id="botones">
                        <!--<div style="float: bottom;" ><img src='/nps/images/enlace_desc.png'  height='20' width='90'> </div>-->       
                        <!--<button id="btnBuscarPara" title="Buscar Parametro Dinamico" class="btnSimple">Buscar</button>-->
                    </div>
                    <div >
                        <!--<button id="btnNuevoPara" title="Nuevo Parámetro Dinámico">Nuevo</button>-->
                    </div>
                    <div class="gridMargin">
                        <div id="gridContenedorParametroDinamico"></div>
                        <div id="divContextMenuParametroDinamico"></div>
                    </div>
                    
                    <div id="divTipificacion" class="pui-panel-content ui-widget-content">

						<div class="filaForm" align="right" style="font-weight: bold;">
		                	(*) Valor UIT referencial actual : <label id="lblUIT"></label>	                	
		                </div>
		            <!-- lchancayauri -->    
	                    <div class="filaForm" align="right" style="font-weight: bold;">
		                	(*)La información de tipificación es conforme a lo establecido en la:		                	
		                </div>		
		                
		              
		                
		                			
						 <table id="tbBasesLegales" align="right"></table>
						 					 
						 
                	</div>
                	<br>
                	<div>                	               	
                	
                	<div id="leyendaBusqObligaciones" style="display:none;">
                    	 <fieldset style="width:970px;">
                        	<legend>Leyenda</legend>                        	                        	
	                        <div class="filaForm">    
	                            <div style="vertical-align: top;width:232px;;">	                                
	                                <div class="ilb vam" style="width: 5px;height: 5px;background: #d5e6f7;border:1px solid #999;"></div>
	                                <div id="descTipSanc1"></div>
	                            </div>
	                           
	                            <div style="vertical-align: top;width:205px;;">	                                
	                                <div class="ilb vam" style="width: 5px;height: 5px;background: #d5e6f7;border:1px solid #999;"></div>
	                                <div id="descTipSanc2"></div>
	                            </div>
	                         
	                            <div style="vertical-align: top;width:245px;;">	                                
	                                <div class="ilb vam" style="width: 5px;height: 5px;background: #d5e6f7;border:1px solid #999;"></div>
	                                <div id="descTipSanc3"></div>
	                            </div>
	                         
	                            <div style="vertical-align: top;width:235px;;">	
	                            	<div class="ilb vam" style="width: 5px;height: 5px;background: #d5e6f7;border:1px solid #999;"></div>                                
	                                <div id="descTipSanc4"></div>
	                            </div>
	                         </div>
	                         <div class="filaForm"> 
	                            <div style="vertical-align: top;width:232px;;">	                                
	                                <div class="ilb vam" style="width: 5px;height: 5px;background: #d5e6f7;border:1px solid #999;"></div>
	                                <div id="descTipSanc5"></div>
	                            </div>	
	                        
	                            <div style="vertical-align: top;width:205px;;">	                                
	                                <div class="ilb vam" style="width: 5px;height: 5px;background: #d5e6f7;border:1px solid #999;"></div>
	                                <div id="descTipSanc6"></div>
	                            </div>
	                       
	                            <div style="vertical-align: top;width:245px;;">	                                
	                                <div class="ilb vam" style="width: 5px;height: 5px;background: #d5e6f7;border:1px solid #999;"></div>
	                                <div id="descTipSanc7"></div>
	                            </div>
	                            <div style="vertical-align: top;width:255px;;">	                                
	                                <div class="ilb vam" style="width: 5px;height: 5px;background: #d5e6f7;border:1px solid #999;"></div>
	                                <div id="descTipSanc8"></div>
	                            </div>
	                        </div>
                        </fieldset>
                    </div>
                	
                	
                </div>
            </div>
        </div>
        
        
<!--    <div id="stack" class="ui-stack">
        <a title="Regresar" href="/consultas"></a>
    </div>    -->
    <div id="divAyudaActividad" style="width: 424px; height: 327px; background-color: white; border: solid; border-radius: 10px;  ">
        <img src="/nps/images/ayuda.png" alt="Ayuda" height="196px" width="100%"  />
        <table height="1%">
        	<tr>
        	    <td id="textActividad"></td>
        	</tr>
        </table>
        <button style="margin-left: 3px" type="button" id="btnCerrarAyudaActividad" >Cerrar</button>
    </div>
    <div id="divAyudaRubro" style="width: 424px; height: 310px; background-color: white; border: solid; border-radius: 10px;  ">
        <img src="/nps/images/ayuda.png" alt="Ayuda" height="196px" width="100%"  />
        <table height="1%">
        	<tr>
        		<td id="textRubro"></td>
        	</tr>
        </table>
        <button style="margin-left: 3px" type="button" id="btnCerrarAyudaRubro" >Cerrar</button>
    </div>
    <div id="divAyudaObligacionTipo" style="width: 424px; height: 395px; background-color: white; border: solid; border-radius: 10px;  ">
        <img src="/nps/images/ayuda.png" alt="Ayuda" height="196px" width="100%"  />
        <table height="1%">
        	<tr>        	    
        		<td id="textObligacion"></td>
       	
        	</tr>
        </table>
        <button style="margin-left: 3px" type="button" id="btnCerrarAyudaObligacionTipo" >Cerrar</button>
    </div>
    <div id="divAyudaClasificacion" style="width: 620px; height: 530px; background-color: white; border: solid; border-radius: 10px;  ">
        <img src="/nps/images/ayuda.png" alt="Ayuda" height="196px" width="100%"  />
        <table height="1%">
        	<tr>        	    
        		<td id="textClasificacion"></td>
        	
        	</tr>
        </table>
        <button style="margin-left: 5px" type="button" id="btnCerrarAyudaClasificacion" >Cerrar</button>
    </div>
    <div id="divAyudaCriticidad" style="width: 424px; height: 293px; background-color: white; border: solid; border-radius: 10px;  ">
        <img src="/nps/images/ayuda.png" alt="Ayuda" height="196px" width="100%"  />
        <table height="1%">
        	<tr>
        		<td id="textCriticidad"></td>
<%--         		<td>${P_CRITICIDAD}</td> --%>
        	</tr>
        </table>
        <button style="margin-left: 3px" type="button" id="btnCerrarAyudaCriticidad" >Cerrar</button>
    </div>
    <div id="dialogMensaje" class="dialog"></div>
</jsp:attribute>

</t:templatePublic>

