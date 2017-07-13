<%-- 
    Document   : popupMensaje
    Created on : 22/10/2014, 06:00:00 PM
    Author     : cflorian
--%>
<%@ include file="/common/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src='<c:url value="/javascript/app/obligaciones/popupMensaje.js" />' charset="utf-8"></script>
        <script type="text/javascript">
            $(function(){
                $("#refrescaImagenCaptcha").click(function() {
                    var nCaptcha = new Image();
                    nCaptcha.src = "/nps/pages/captcha?date=" + (new Date().getTime());
                    $('#captchaImagen').html('<img alt="Loading..." src="<c:url value="../../../images/pagina_login/ajax-loader2.gif"/>">');
                    nCaptcha.onload = function() {
                        $('#captchaImagen').html(nCaptcha);
                    };
                });                
            });
        </script>
    </head>
    <body>
        <form id="frmMensaje">
        	<input type="hidden" id="hdnTipoConsulta" value="${TIPO_CONSULTA}"/>
        	<div id="divMensajeValidacionObl" class="errorMensaje" tabindex='1' style="display: none" ></div>
        	<div>        	
	           <table class="tableCenter" width="100%">
	               <tr>
	               		<td><label>Sugerencia (*)</label></td>	               		
	               </tr>
	               <tr>
	               		<td><textarea id="txtSugerencia" style="width: 450px;" cols="8" validate="[O]" maxlength="400"></textarea></td>
	               </tr>	               
	               <tr>
	               		<td><label>Nombres y Apellidos (*)</label></td>	               		
	               </tr>
	               <tr>
	               		<td><input id="txtNombresYApellidos" class="txtMayus" type="text" style="width: 80%;" validate="[SL][O]" maxlength="60"/></td>
	               </tr>
	               <tr>
	               		<td><label>Email (*) </label></td>	               		
	               </tr>
	               <tr>
	               		<td><input id="txtEmail" type="text" style="width: 80%;" validate="[CORREO][O]" maxlength="40"/></td>
	               </tr>
	               <tr>
	               		<td><label>Tel&eacutefono</label></td>	               		
	               </tr>
	               <tr>
	               		<td><input id="txtTelefono" type="text" style="width: 80%;" maxlength="11"/></td>
	               </tr>
	               <tr>
	               		<td>
	               			<div class="captchaDiv" style="height: 44px; margin-bottom: 30px;">
		                        <a id="refrescaImagenCaptcha" href="#"></a>
		                       
		                        <div class="captchaImagen" id="captchaImagen" >
		                            <img src="/nps/pages/captcha" alt="Loading..." style="margin-right: 2px;"/>
		                        </div>
		                        <div id=fondoTextoCaptchaDiv style="background-color: white; float: left; height: 44px; width: 46%">
		                            <label>Texto de Verificaci&oacuten (*)</label>
		                            <input type="text" name="textoCaptchaSugerencia" id="textoCaptchaSugerencia" validate="[O]" maxlength="5"/>
		                        </div>
		                    </div>
	               		</td>
	               </tr>
	               <tr>
	               		<td colspan="2"><br/><br/>
	               			<div align="center">
		               			<button id="btnEnviar" title="Enviar" type="button">Enviar</button>
		               			<button id="btnCerrar" title="Cerrar" type="button">Cerrar</button>
	               			</div>
	               		</td>
	               </tr>
	            </table>  
	            <div class="lble"><label id="obligatorio">(*) Campos Obligatorios</label></div>    
           </div>       
        </form>
    </body>
</html>