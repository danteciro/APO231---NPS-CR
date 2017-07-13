$(function() {
	$('#btnEnviar').click(function() {
		
		var validarSugerencia;
		validarSugerencia = $('#frmMensaje').validateAllForm('#divMensajeValidacionObl');
		
        if (validarSugerencia) {
			$.post(
					baseURL + "pages/consultaObligaciones/enviarSugerencia", 
			        {
			            sugerencia: $("#txtSugerencia").val(),
			            nombreCompleto: $("#txtNombresYApellidos").val(),
			            email: $("#txtEmail").val(),
			            telefono: $("#txtTelefono").val(),
			            textoValidacion: $("#textoCaptchaSugerencia").val(),
			            tipoConsulta: $("#hdnTipoConsulta").val()
			        }, 
			        function(data) {
			            if (data.resultado == 0) {
			                mensajeGrowl("success",data.mensaje, "");
			                $("#dialogMensaje").dialog("close");
			            }else if(data.resultado == 2){
			            	mensajeGrowl("warning", data.mensaje, "");
			            	var nCaptcha = new Image();
		                    nCaptcha.src = "/nps/pages/captcha?date=" + (new Date().getTime());
		                    $('#captchaImagen').html('<img alt="Loading..." src="<c:url value="../../../images/pagina_login/ajax-loader2.gif"/>">');
		                    nCaptcha.onload = function() {
		                        $('#captchaImagen').html(nCaptcha);
		                    };
		               }else{
		                   mensajeGrowl('error', data.mensaje, 'Intente de nuevo');
		                   var nCaptcha = new Image();
		                    nCaptcha.src = "/nps/pages/captcha?date=" + (new Date().getTime());
		                    $('#captchaImagen').html('<img alt="Loading..." src="<c:url value="../../../images/pagina_login/ajax-loader2.gif"/>">');
		                    nCaptcha.onload = function() {
		                        $('#captchaImagen').html(nCaptcha);
		                    };
		               }
			        }
			    );
        }
		
    });	
	$('#btnCerrar').click(function() {  
		$("#dialogMensaje").dialog("close");
    });
});


var alphaOptionsSugerencias = {
		allowNumeric  : true,
		allowLatin    : true,
		allowUpper    : true,
		allowLower    : true,
		allowCaseless : true,
		allowOtherCharSets : false,
		allowSpace    : true,
		allow		  : 'Ññóáéíóú'
			
	 };

var alphaOptions = {
		allowNumeric  : false,
		allowLatin    : true,
		allowUpper    : true,
		allowLower    : true,
		allowCaseless : true,
		allowOtherCharSets : false,
		allowSpace    : true,
		allow		  : 'Ññóáéíóú'
			
	 };

var alphaOptionsEmail = {
		allowNumeric  : true,
		allowLatin    : true,
		allowUpper    : true,
		allowLower    : true,
		allowCaseless : true,
		allowOtherCharSets : false,
		allowSpace    : true,
		allow		  : '@._'
			
	 };

var alphaOptionsNum = {
		allowNumeric  : true,
		allowLatin    : false,
		allowUpper    : false,
		allowLower    : false,
		allowCaseless : true,
		allowOtherCharSets : false,
		allowSpace    : true
			
	 };
$("#txtSugerencia").alphanum(alphaOptionsSugerencias);
$("#txtNombresYApellidos").alphanum(alphaOptions);
$("#txtEmail").alphanum(alphaOptionsEmail);
$("#txtTelefono").alphanum(alphaOptionsNum);