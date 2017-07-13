<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page session="false" %>  

<%@ include file="/common/taglibs.jsp"%>
<% String mensaje = ""; %>

<%
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd ' de ' MMMMM ' del ' yyyy", new Locale("ES"));
String headerTime = "";
String date = formatter.format(new java.util.Date());
headerTime = "Lima, " + date; 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>:: Login Seguridad ::</title>

        <link href="<c:url value="/stylesheets/login/login.css"/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/stylesheets/login/global.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="/nps/javascript/third-party/primeui/jquery-1.7.js"></script>

        <script type="text/javascript">
            $(function(){
                $("#refrescaImagenCaptcha").click(function() {
                    var nCaptcha = new Image();
                    nCaptcha.src = "/nps/pages/captcha?date=" + (new Date().getTime());
                    $('#captchaImagen').html('<img alt="Loading..." src="<c:url value="/images/pagina_login/ajax-loader2.gif"/>">');
                    nCaptcha.onload = function() {
                        $('#captchaImagen').html(nCaptcha);
                    };
                });
                iniciar();
            });
            function iniciar() {
                $("#j_username").val('');
                $("#j_password").val('');
                $('#j_username').focus();
                
            }

        </script>
        
    </head>
    <body>

        <div id="fondoBody">
            <img src='<c:url value="/images/pagina_login/collageOsinergmin2.jpg"/>' />
        </div>
        <div id="mainHeader">
            <a  id="homeLogo">
                <img src='<c:url value="/images/pagina_login/osinergminLogo.png"/>' />
            </a>
        </div>
        <div class="section" id="mainBody">

            <div id="misionVisionArticle">

                <div class="firstDiv">
                    <h1>
                        misi&oacute;n
                    </h1>
                    <p>
                        Regular y supervisar los sectores de energ&iacute;a y miner&iacute;a con autonom&iacute;a y transparencia para generar confianza a la inversi&oacute;n y proteger a la poblaci&oacute;n.
                    </p>
                </div>

                <div>
                    <h1>
                        visi&oacute;n
                    </h1>
                    <p>
                        Que la sociedad reciba un adecuado abastecimiento de energ&iacute;a y que las actividades supervisadas por OSINERGMIN se realicen en forma segura y con cuidado del medio ambiente.
                    </p>
                </div>

            </div>

            <div id="loginArticle">
                <form name="frmLogin" id="frmLogin" action="/nps/pages/consultaObligaciones/scop" method="" autocomplete="off" >
                    <h1>

                    </h1>


                    <div class="loginField usuario">
                        <div></div>
                        
                        <input type="text" autofocus="autofocus" name="j_username" id="j_username" minlength="3" maxlength="50" class="required"/>

                    </div>

                    <div class="loginField password">
                        <div></div>
                        <input type="password" name="j_password" id="j_password" maxlength="50" size="30" class="required"/>
                    </div>

                    <span>
                        Ingresa el c&oacute;digo de la imagen:
                    </span>

                    <div class="captchaDiv">
                        <div id=fondoTextoCaptchaDiv>
                            <input type="text" name="textoCaptcha" id="textoCaptcha"  style="text-transform: uppercase;border:none;" />
                        </div>
                        <div class="captchaImagen" id="captchaImagen"  >
                            <img src="/nps/pages/captcha" alt="Loading..." style="margin-right: 2px;"/>
                        </div>
                        </br>
                        </br>
                        </br>
                        <a id = "refrescaImagenCaptcha" href="#"></a>
                    </div>

                    <div class="errores">
                    </div>
                    <div class="errores">
                        <a href="/nps/pages/consultaObligaciones/opcion1" style="font-size: 12px"  >Si Ud. no cuenta con usuario, haga click aqui</a>    
                        <a href="/nps/pages/consultaObligaciones/sfh" style="font-size: 12px"  >SFH</a>
                    </div>
                    <a href="#" class="actionButton" >
                        <input type="image" id="btnIngresar" value="Ingresar"  src="<c:url value="/images/pagina_login/Ingresar.png"/>"/>
                    </a>

                </form>
            </div>

        </div>

    </body>
</html>
