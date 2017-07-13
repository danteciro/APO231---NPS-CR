var baseURL;
var id;
var caracteres = "abcdefghijklmnopqrstuvwxyzñÑABCDEFGHIJKLMNOPQRSTUVWXYZáéíóú ";
var numeros = "0123456789";
var numeros_caracteres = numeros + caracteres;
var moneda = numeros + ".";
var global = {
    rowNum:5
};

$(function() {
    baseURL = "/nps/";
    $.ajaxSetup({
        cache: false,
        data: {
            wtf: $("#idSesion").val()
        }
    });
    validarCampo();
    anularEnter();
    anularCopyPasteInput();
    notify();
    $.datepicker.regional['es'] = {
        closeText: 'Cerrar',
        prevText: '&#x3c;Ant',
        nextText: 'Sig&#x3e;',
        currentText: 'Hoy',
        monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
            'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre',
            'Diciembre'],
        monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul',
            'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
        dayNames: ['Domingo', 'Lunes', 'Martes', 'Mi&eacute;rcoles',
            'Jueves', 'Viernes', 'S&aacute;bado'],
        dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mi&eacute;', 'Juv', 'Vie',
            'S&aacute;b'],
        dayNamesMin: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'S&aacute;'],
        weekHeader: 'Sm',
        dateFormat: 'dd/mm/yy',
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: ''
    };

    $.datepicker.setDefaults($.datepicker.regional['es']);

    validateFormActive();
    
    /*/oculta "div" que tenga boton con atributo css "btnClose"
    $('.btnClose').click(function(){
        $(this).parent().fadeOut();
    });*/
    //cierra dialogs, que tengas boton cerrar con atributo css "btnCloseDialog"
//    boton.closeDialog();
});

/*
 * funciones para botones con usos comun
 */
var boton={
    closeDialog:function(){
        $('.btnCloseDialog').click(function(){
            var idP=$(this).parent().parent().attr('id');
            $('#'+idP).dialog('close');
        });
    }
};

/*
 * funciones para popups de confirmacion.
 */
var confirm={
    start:function(){
        $(document.body).append('<div id="dialogComunConf" class="dialogComunConf" style="display:none;" title="Confirmaci&oacute;n"><div><span class="ui-icon ui-icon-alert" id="icon"></span><span id="textDialogComunConf"></span></div><div id="botones"><button id="btnConfDialogComunConf">Aceptar</button><button onclick="confirm.close()">Cancelar</button></div></div>');    
        $("#dialogComunConf").dialog({resizable: false,autoOpen: false,height: "auto",width: "auto",dialogClass: 'dialog',modal: true,show: {effect: "fade",duration: 400},hide:{effect: "fade",duration: 400}});
    },
    open:function(msj,evtClick,objText){
        //Valores Default
        var textAceptar="Aceptar";
        var textCancelar="Cancelar";
        //Nuevos Valores
        if(objText!=undefined && objText!=''){
            textAceptar=(objText.textAceptar!=undefined)?objText.textAceptar:textAceptar;
            textCancelar=(objText.textCancelar!=undefined)?objText.textCancelar:textCancelar;
        }
        //Texto Botones
        $('#dialogComunConf #botones').find('button:eq(0)').html(textAceptar);
        $('#dialogComunConf #botones').find('button:eq(1)').html(textCancelar);
                
        $('#dialogComunConf #textDialogComunConf').html(msj);
        $('#dialogComunConf #btnConfDialogComunConf').attr('onclick',"confirm.close(); "+evtClick);
        $('#dialogComunConf').dialog('open');
    },
    close:function(){
        $("#dialogComunConf").dialog("close");
        $('#dialogComunConf #textDialogComunConf').html('');
        $('#dialogComunConf #btnConfDialogComunConf').attr('onclick','');
    }
};

/*
 * funciones de complemento para grillas.
 */
var fxGrilla={
    divPto:'<div class="ptoGrilla">...</div>',
    setPtosSuspensivos:function(idGrilla, campo){
        $('td[aria-describedby="'+idGrilla+'_'+campo+'"]').css('white-space','normal');
        $('td[aria-describedby="'+idGrilla+'_'+campo+'"]').map(function(){
            var html=fxGrilla.limpiaPtos($(this).html());
            if(parseInt($(this).css('height').replace('px',''))>26){$(this).css('position','relative');html=html+fxGrilla.divPto;}
            $(this).html(html);});
        $('td[aria-describedby="'+idGrilla+'_'+campo+'"]').css('white-space','pre');
    },
    limpiaPtos:function(valor){return valor.replace(fxGrilla.divPto,'');}
};

/*function limpiaMultiSelect(id){
    $('option', $(id)).each(function(element) {
        $(this).removeAttr('selected').prop('selected', false);
    });
    $(id).multiselect('refresh');
}*/
function quitaSlashDir(dir){
    var sep=dir.split("\\");
    dir=sep[sep.length-1];
    return dir;
}
function reverseFormatoLink(sel){
    sel=sel.replace(sel.substring(sel.indexOf('<'),sel.indexOf('>')+1),"");//para <a>
    sel=sel.replace(sel.substring(sel.indexOf('<'),sel.indexOf('>')+1),"");//para </a>
    return sel;
}
function reverseFormatoDocumento(sel){
    if(sel!=""){
        var y=sel.split(' - ');
        if(y.length>0){
            sel=y[1];
        }else{
            sel="";
        }
    }
    return sel;
}
function errorAjax(){
    ocultaLoading();
    mensajeGrowl('error', "El servicio no se encuentra disponible", 'Intente mas tarde');
}
/*
 * @param {type} rowid, id del registro de la grilla, Ej. idLocador
 * @param {type} idGrilla, id de Grilla
 * @returns {unresolved}
 */
function searchInJqGridByID(rowid,idGrilla){
    var retorno=null;
    $.each($('#'+idGrilla).jqGrid('getDataIDs'),function(key,val){
        if(rowid==val){
            retorno=$('#'+idGrilla).jqGrid('getRowData', val);
        }
    });
    return retorno;
}
/*
 * @param {type} idDivMsje, id del div que mostrara el mensaje
 * @param {type} Msje, texto a mostrar
 * @param {type} ElementError, elementos del Formulario que generan error, Ej. #txtNombre
 * @returns {undefined}*
 */
function muestraDivError(idDivMsje,Msje,ElementError){
    $('#'+idDivMsje).show();
    $('#'+idDivMsje).html(Msje);
    $(ElementError).addClass("error");
}
function limpiaValidacionesMostradas(idDivMsje,idForm){
    $('#'+idDivMsje).html("").hide();
    $('#'+idForm).find('input,select,textarea').removeClass('error');
}
function muestraLoading(){
    $('#overlay_loading').css('display','');
}
function ocultaLoading(){
    $('#overlay_loading').css('display','none');
}
function trim(stringToTrim) {
    return stringToTrim.replace(/^\s+|\s+$/g, "");
}

function mensajeGrowl0(type, title, detail) {
    if (type == "success")
        type = "info"

    $('#default').puigrowl('show', [{severity: type, summary: title, detail: detail}]);
}
function notify() {
    $('#notifynormal').puinotify({
        easing: 'easeInOutCirc'
    });
    $("#notifynormal").click(function(e) {
        $('#notifynormal').puinotify('hide');
    });
}
function mensajeGrowl(type, title, detail) {
if ($('#notifynormal').hasClass('error') ) {
   $('#notifynormal').removeClass('error');
} else if ( $('#notifynormal').hasClass('warn') ) {
    $('#notifynormal').removeClass('warn');
} else if ( $('#notifynormal').hasClass('success') ) {
    $('#notifynormal').removeClass('success');
}          
       $('#notifynormal').addClass(type);
        $('#notifynormal').puinotify('show', '<h1>' + title + '</h1> <p>' + detail + '</p>');
        id = '#notifynormal';
        
    window.setTimeout(closeMessage, 3500);
}
function cerrarMessage() {
    if ($('#notify').is(":visible")) {
        $('#notify').puinotify('hide');
    }
}

function closeMessage() {
    if ($(id).is(":visible")) {
        $(id).puinotify('hide');
    }
}
window.history.forward();

function noBack() {
    window.history.forward();
}

function validarCampo() {
    (function($) {
        $.fn.validCampoNumero = function(cadena) {
            $(this).on({
                keypress: function(e) {
                    var key = e.which,
                            keye = e.keyCode,
                            tecla = String.fromCharCode(key).toLowerCase(),
                            letras = cadena;
                    if (letras.indexOf(tecla) == -1 && keye != 9 && (key == 37 || keye != 37) && (keye != 39 || key == 39) && keye != 8 && (keye != 46 || key == 46) || key == 161) {
                        e.preventDefault();
                    }
                }
            });
        };
    })(jQuery);
}

function validateFormActive() {
    var html = "<a  data-title='?' class='tooltip'><img src='/sglss/images/error_small.png'  style='float: left;'/></a>";

    (function($) {
        $.fn.validateAllForm = function(div) {

            $(div).hide();
            var validar = true;
            var form = $(this);
            var id = "#" + form.attr("id");
            var errorTotal = "";
            $(id + ' input, ' + id + ' select, ' + id + ' textarea').each(
                    function(index) {
                        var texto = "";
                        var input = $(this);
                        var error = validateInput(input, html)
                        if (error != "") {
                            validar = false;

                            if (error != "[O]") {
                                var label = $("label[for='" + $(this).attr('id') + "']");
                                if (label.length != 0)
                                    texto = label.html().replace('(*)','');//+"<br/>";
                                if (errorTotal != "")
                                    errorTotal += "<//>" + texto + error;
                                else {
                                    errorTotal += texto + error;
                                }
                            }else{
                                if($(this).attr('class').indexOf('hasDatepicker')==-1 && this.type!="select-one"){//TODO evitar se abra datepicker
                                    //$(this).focus();  //jpiro, focus cambia estilo css y no se ve rojo, confunde usuario
                                }
                            }
                        }

                        $(this).on({
                            blur: function(e) {
                                var input = $(this);
                                validateInput(input, html);

                            }});

                    }
            );
            if (errorTotal != "") {

                var array = errorTotal.split('<//>');
                $(div).show();
                $(div).focus();
                $(div).html(array[0]);
            }
            return validar;
        };
    })(jQuery);

    (function($) {
        $.fn.validarForm = function(data) {
            var form = $(this);
            var id = "#" + form.attr("id");
            $(id + ' input,' + id + ' textarea').each(
                    function(index) {
                        var cadena = "";
                        var input = $(this);

                        var validacion = input.attr('validate');
                        if (validacion !== null && typeof validacion !== "undefined")
                            if (validacion.indexOf("[SL]") !== -1 || validacion.indexOf("[SN]") !== -1 || validacion.indexOf("[MONEDA]") !== -1 ) {//TODO para otros casos

                                if (validacion.indexOf("[SL]") !== -1){
                                    cadena = caracteres;
                                }else if (validacion.indexOf("[SN]") !== -1){
                                    cadena = numeros;
                                }else if (validacion.indexOf("[MONEDA]") !== -1){
                                    cadena = moneda;
                                }
                                  
                                $(this).on({
                                    keypress: function(e) {
                                        var key = e.which,
                                                keye = e.keyCode,
                                                tecla = String.fromCharCode(key).toLowerCase(),
                                                letras = cadena;
                                        if (letras.indexOf(tecla) == -1 && keye != 9 && (key == 37 || keye != 37) && (keye != 39 || key == 39) && keye != 8 && (keye != 46 || key == 46) || key == 161) {
                                            e.preventDefault();
                                        }
                                    }
                                });
                            }
                    }
            );

        };
    })(jQuery);
}
function validateInput(input, html) {
    var validaciones = input.attr('validate');
    var error = "";
    if (validaciones !== null && typeof validaciones !== "undefined")
        error = validaCampos(input.val(), validaciones);
    if (error !== "") {
        input.addClass("error");
    } else {
        input.removeClass("error")
    }
    return error;
}
function validaCampos(value, tipo) {
    var mensaje = "";
    var error;
    if (tipo.indexOf("[O]") !== -1) { //para los obligatorios
        if (value === "") {
            error = true;
            mensaje = "[O]";
            return mensaje;
        }
    }
    if (tipo.indexOf("[SL]") !== -1) {
        var expreg = new RegExp("^$|^([á-úÁ-Ú]|[a-zA-Z ])*$");
        if (!expreg.test(value)) {
            mensaje = " Formato de solo letras.";
            return mensaje;
        }
    }
    if (tipo.indexOf("[SN]") !== -1) {
        var expreg = new RegExp("^$|[1-9][0-9]*");
        if (!expreg.test(value)) {
            mensaje = " Formato de solo Números.";
            return mensaje;
        }
    }

    if (tipo.indexOf("[CORREO]") !== -1) {
        var expreg = new RegExp("(^$|^.*@.*\..*$)");//("/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/");  
        if (!expreg.test(value)) {
            mensaje = " Ingrese un formato valido de correo.";
            return mensaje;
        }
    }
    
    if (tipo.indexOf("[MONEDA]") !== -1) {
        var expreg = new RegExp("^$|[0-9]+(\.[0-9]+)?$");
        if (!expreg.test(value)) {
            mensaje = " Ingrese un formato valido de moneda.";
            return mensaje;
        }
    }
    return mensaje;
}


function anularEnter() {
    $("input[type=text]").keypress(function(e) {
        if (e.which == 13) {
            return false;
        }
    });
}

function anularCopyPasteInput() {
    $('input').bind('copy paste', function(e) {

        e.preventDefault();
    });
}

function validaEnteros(obj, nDec) {
    var expreg = /^\d*$/;
    if (!isNaN(nDec) && (Number(nDec) > 0))
        expreg = new RegExp("^\\d*(\\.\\d{1," + nDec + "})?$");
    if (expreg.test(obj))
        return true
    else
        return false;
}
function comparingDates(fromDate, toDate, fromDateName, toDateName) {

    if (fromDate != '' && toDate != '') {

        var datefrom = new Date();
        var dateto = new Date();

        try {
            var arrayfrom = fromDate.split('/');
            datefrom.setFullYear(arrayfrom[2]);
            datefrom.setMonth(parseFloat(arrayfrom[1]) - 1);
            datefrom.setDate(parseFloat(arrayfrom[0]));
            datefrom.setHours('0');
            datefrom.setMinutes('0');
            datefrom.setSeconds('0');

        } catch (ex) {
            alert('El campo' + fromDateName + ' es una fecha invalida.');
            return false;
        }

        try {
            var arrayto = toDate.split('/');
            dateto.setFullYear(arrayto[2]);
            dateto.setMonth(parseFloat(arrayto[1]) - 1);
            dateto.setDate(parseFloat(arrayto[0]));
            dateto.setHours('0');
            dateto.setMinutes('0');
            dateto.setSeconds('0');

        } catch (ex) {
            alert('El campo' + toDateName + ' es una fecha invalida.');
            return false;
        }

        if (datefrom > dateto) {
            return false;
        }
    }
    return true;
}

function dias_entre(date1, date2) {
    var resultado = comparaFecha(date1, date2);
    if (isNaN(resultado))
        return resultado;

    var f1 = date1.split("/");
    var f2 = date2.split("/");
    // The number of milliseconds in one day
    var ONE_DAY = 1000 * 60 * 60 * 24;

    // Convert both dates to milliseconds
    var date1_ms = new Date(f1[2], f1[1], f1[0]).getTime();
    var date2_ms = new Date(f2[2], f2[1], f2[0]).getTime();

    // Calculate the difference in milliseconds
    var difference_ms = date2_ms - date1_ms;
    // Convert back to days and return
    return Math.round(difference_ms / ONE_DAY);

}
Date.daysBetween = function(date1, date2) {
    //Get 1 day in milliseconds
    var one_day = 1000 * 60 * 60 * 24;

    // Convert both dates to milliseconds
    var date1_ms = date1.getTime();
    var date2_ms = date2.getTime();

    // Calculate the difference in milliseconds
    var difference_ms = date2_ms - date1_ms;

    // Convert back to days and return
    return Math.round(difference_ms / one_day);
}

//devuelve 1 si fecha1>fecha2, -1 si fecha1<fecha2 y 0 si fecha1=fecha2
function comparaFecha(fecha1, fecha2)
{
    if (!esFecha(fecha1)) {
        return fecha1 + " no es una fecha correcta";
    }
    if (!esFecha(fecha2)) {
        return fecha2 + " no es una fecha correcta";
    }
    if (fecha1 == fecha2)
        return 0;
    else {
        var f1 = fecha1.split("/");
        var f2 = fecha2.split("/");
        if ((for1 = f1[2] + f1[1] + f1[0]) > (for2 = f2[2] + f2[1] + f2[0]))
            return 1;
        else if (for1 < for2)
            return -1;
        else
            return 0;
    }
}

function parseDate(fecha) {
    var curr_date = fecha.getDate();
    var curr_month = fecha.getMonth();
    curr_month++;   // need to add 1 – as it’s zero based !
    var curr_year = fecha.getFullYear();
    var formattedDate = (('' + curr_date).length < 2 ? '0' : '') + curr_date + "/" + (('' + curr_month).length < 2 ? '0' : '') + curr_month + "/" + curr_year;
    return formattedDate;
}
function esFecha(fecha)
{
    //Se verifica que la fecha sólo tenga caracteres numéricos y el caracter "/"
    for (var i = 0; i < fecha.length; i++) {
        //var carac = fecha.substring(i,i+1);
        var carac = fecha.charAt(i);
        if ((carac < "0" || carac > "9") && carac != "/") {
            return false;
        }
    }

    //Obtenemos el dia de la fecha
    var pos1 = fecha.indexOf("/");
    aux = fecha.substring(0, pos1);
    if (aux.length != 2)
        return false;  //verificamos que la parte del dia tenga dos caracteres
    if (aux.charAt(0) == "0") {
        aux = aux.substr(1, 1);
    }
    var dia = parseInt(aux);

    //Obtenemos el mes de la fecha
    var pos2 = fecha.indexOf("/", pos1 + 1);
    var aux = fecha.substring(pos1 + 1, pos2);
    if (aux.length != 2)
        return false;  //verificamos que la parte del mes tenga dos caracteres
    if (aux.charAt(0) == "0") {
        aux = aux.substr(1, 1);
    }
    var mes = parseInt(aux);

    //Obtenemos el año de la fecha
    aux = fecha.substring(pos2 + 1, fecha.length);
    if (aux.length != 4)
        return false;  //verificamos que la parte del año tenga cuatro caracteres
    var anno = parseInt(aux);


    if (mes < 1 || mes > 12)
        return false;  //el mes debe estar entre 1 y 12
    if (dia < 1 || dia > 31)
        return false;  //el día debe estar entre 1 y 31
    if (anno < 1754 || anno > 9999)
        return false;  //el año debe estar entre 1754 y 9999

    if (mes == 2 && dia > 29)
        return false;  //valida Febrero: el día debe estar entre 1 y 29

    if ((anno % 4) != 0 && dia == 29 && (mes == 2))
        return false; //Año Bisiesto: se verifica que febrero tenga 29 días

    if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) & dia > 30)
        return false;  //Meses de 30 dias.

    return true;
}


function validatecla(event, tipo, textbox) {

    var tecla;
    if (navigator.appName.indexOf("Netscape") != -1) {
        tecla = event.which;
    } else {
        tecla = event.keyCode;
    }

    var key = String.fromCharCode(tecla);

    /*---Mayuscula---*/
    key = key.toUpperCase();

    var telefonos = "-/#";
    var numeros = "0123456789";
    var especiales = "'#ï¿½()_;:ï¿½[]{}!ï¿½/?ï¿½``ï¿½ï¿½+ï¿½=&%$*";
    var letras = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnÃ‘n??OoPpQqRrSsTtUuVvWwXxYyZz??????????";

    if (tecla == 8)
        return true;
    if (tecla == 127)
        return true;
    if (tecla == 0)
        return true;

    if (tipo == 'letras') {
        window.event.keyCode = window.event.keyCode - 32;
        if (tecla == 32)
            return true;
        if (letras.indexOf(key) != -1)
            return true;
        return false;
    }

    if (tipo == 'enteros') {

        if (numeros.indexOf(key) != -1)
            return true;
        return false;
    }

    if (tipo == 'ruc') {
        if (numeros.indexOf(key) != -1) {
            return true;
        }

        return false;
    }

    if (tipo == 'decimales') {
        if (numeros.indexOf(key) != -1)
            return true;
        var cadena = textbox.value;
        if (cadena.indexOf('.') != -1)
            return false;
        if (tecla == 46)
            return true;
        return false;
    }

    if (tipo == 'decimales2') {

        if (numeros.indexOf(key) != -1) {
            if (textbox.value.length == 5) {
                if (textbox.value.indexOf('.') == -1) {
                    textbox.value = textbox.value + '.';
                }
            }

            return true;
        }

        var cadena = textbox.value;
        if (cadena.indexOf('.') != -1)
            return false;
        if (tecla == 46)
            return true;

        return false;
    }

    if (tipo == 'decimales3') {

        if (numeros.indexOf(key) != -1) {
            if (textbox.value.length == 6) {
                if (textbox.value.indexOf('.') == -1) {
                    textbox.value = textbox.value + '.';
                }
            }

            return true;
        }

        var cadena = textbox.value;
        if (cadena.indexOf('.') != -1)
            return false;
        if (tecla == 46)
            return true;

        return false;
    }

    if (tipo == 'NoNumeros') {
        if (tecla == 32)
            return true;
        if (numeros.indexOf(key) != -1 || especiales.indexOf(key) != -1)
            return false;
        return true;
    }

    if (tipo == 'especiales') {
        if (tecla == 32)
            return true;
        if (especiales.indexOf(key) != -1)
            return false;
        return true;
    }

    if (tipo == 'num_letras') {
        if (tecla == 32)
            return true;
        if (numeros.indexOf(key) != -1 || letras.indexOf(key) != -1)
            return true;
        return false;
    }

    if (tipo == 'telefonos') {
        if (tecla == 32)
            return true;
        if (numeros.indexOf(key) != -1 || telefonos.indexOf(key) != -1)
            return true;
        return false;
    }

    if (tipo == 'todo') {
        if (tecla == 32)
            return true;
        if (numeros.indexOf(key) != -1 || especiales.indexOf(key) != -1
                || letras.indexOf(key) != -1)
            return false;
        return true;
    }

}

function redir(url) {
    //IE 7 detectado
    if (document.all && (!document.documentMode || (document.documentMode && document.documentMode < 8))) {
        window.location.replace(url);
    } else {
        window.location = url;
    }
}

function formateafecha(fecha)
{
    var long = fecha.length;
    var dia;
    var mes;
    var ano;

    if ((long >= 2) && (primerslap == false)) {
        dia = fecha.substr(0, 2);
        if ((IsNumeric(dia) == true) && (dia <= 31) && (dia != "00")) {
            fecha = fecha.substr(0, 2) + "/" + fecha.substr(3, 7);
            primerslap = true;
        }
        else {
            fecha = "";
            primerslap = false;
        }
    }
    else
    {
        dia = fecha.substr(0, 1);
        if (IsNumeric(dia) == false)
        {
            fecha = "";
        }
        if ((long <= 2) && (primerslap = true)) {
            fecha = fecha.substr(0, 1);
            primerslap = false;
        }
    }
    if ((long >= 5) && (segundoslap == false))
    {
        mes = fecha.substr(3, 2);
        if ((IsNumeric(mes) == true) && (mes <= 12) && (mes != "00")) {
            fecha = fecha.substr(0, 5) + "/" + fecha.substr(6, 4);
            segundoslap = true;
        }
        else {
            fecha = fecha.substr(0, 3);
            ;
            segundoslap = false;
        }
    }
    else {
        if ((long <= 5) && (segundoslap = true)) {
            fecha = fecha.substr(0, 4);
            segundoslap = false;
        }
    }
    if (long >= 7)
    {
        ano = fecha.substr(6, 4);
        if (IsNumeric(ano) == false) {
            fecha = fecha.substr(0, 6);
        }
        else {
            if (long == 10) {
                if ((ano == 0) || (ano < 1900) || (ano > 2100)) {
                    fecha = fecha.substr(0, 6);
                }
            }
        }
    }

    if (long >= 10)
    {
        fecha = fecha.substr(0, 10);
        dia = fecha.substr(0, 2);
        mes = fecha.substr(3, 2);
        ano = fecha.substr(6, 4);
// Año no viciesto y es febrero y el dia es mayor a 28 
        if ((ano % 4 != 0) && (mes == 02) && (dia > 28)) {
            fecha = fecha.substr(0, 2) + "/";
        }
        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            if (dia == 31)
                fecha = fecha.substr(0, 2) + "/";
        }
    }
    return (fecha);
}

function valida_numero(xinput, tipval)
{
    var xkey = event.keyCode;
    if (tipval == "int")
        if ((xkey < 48) || (xkey > 57))
            event.returnValue = false;
    if (tipval == "dec")
    {
        if (xkey < 46 || xkey > 57 || xkey == 47)
            event.returnValue = false;
    }
    if (tipval == "stn") {
        if (((xkey != 46) && (xkey < 48)) || ((xkey > 57) && (xkey < 65)) || ((xkey > 90) && (xkey != 95) && (xkey < 97)) || (xkey > 122))
            event.returnValue = false;
    }
    if (tipval == "str") {
        if (((xkey != 32) && (xkey < 65)) || ((xkey > 90) && (xkey < 97)))
            event.returnValue = false;
    }
    if (tipval == "stn2") {
        if (((xkey != 32) && (xkey < 46)) || ((xkey > 57) && (xkey < 65)) || ((xkey > 90) && (xkey != 95) && (xkey < 97)) || (xkey > 122))
            event.returnValue = false;
    }
    if (tipval == "tlf")
        if (((xkey != 32) && (xkey < 45)) || (xkey > 57))
            event.returnValue = false;

    if (tipval == "afn")
    {
        if (((xkey != 32) && (xkey < 45)) || (xkey > 57) ||
                ((xkey < 48) || (xkey > 57)))
            event.returnValue = false;
    }

}
function validarTextarea() {
    $('textarea').bind('keyup change', function() {
        var texto = $(this).val();
        var longitud = $(this).val().length;
        var maximo = parseInt($(this).attr("maxlength"));
        if (longitud > maximo) {
            $(this).val(texto.substr(0, maximo));
        }
    });
    $('textarea').bind('copy paste', function(e) {

        e.preventDefault();
    });
    $("textarea[maxlength]").keypress(function(event) {
        var key = event.which;

        //all keys including return.
        if (key >= 33 || key == 13 || key == 32) {
            var maxLength = $(this).attr("maxlength");
            var length = this.value.length;
            if (length >= maxLength) {
                event.preventDefault();
            }
        }
    });

}
function validateDouble(objeto, cantEnt, cantDec) {
    var encontrado = '0';
    var valor = objeto.value;
    if (valor != '') {
        ind = valor.indexOf('.');
        if (ind != -1)
            encontrado = 1;
        if (encontrado == '1') {
            if (valor.charAt(valor.length - 1) == '.' && (valor.length - 1) > ind)
                valor = valor.substring(0, valor.length - 1);
            dec = valor.substring(ind + 1, valor.length);
            if (dec.length > cantDec)
                valor = valor.substring(0, ind + cantDec + 1);
        } else {
            if (valor.length > cantEnt)
                valor = valor.substring(0, cantEnt);
        }
    }
    objeto.value = valor;
}

/*EOG*/
function redir(url) {
    //IE 7 detectado
    if (document.all && (!document.documentMode || (document.documentMode && document.documentMode < 8))) {
        window.location.replace(url);
    } else {
        window.location = url;
    }
}

function fecha(data){
    if(data == null){
          return "&nbsp;";
    }else{
          if(data == '-62135751600000'){
                 return '01/01/0001';
          }else{
                 if(typeof data == "string"){
                        return data;
                 }else{
                        return $.datepicker.formatDate('dd/mm/yy',new Date(data));
                 }
          }
    }
}


function jqgridLocal(idTable, pager, rownum, colNames, colModel, data, idRow, caption, postData, colSubgridCollapserName, loadCompleteFunction){
	var idGrid = idTable;
	var grid = $("#"+idGrid);
	var idPager = null;
	var selectorPager = null;
        var loadonce = false;
	var subGrid = false;
	var rownumbers = false;
	if (pager === true){
		idPager = "pager_"+idGrid;
		grid.after("<div id="+idPager+"></div>");
		selectorPager = "#"+idPager;
		if (rownum == null){
			rownum = 10;
		}
	}else{
		rownum = 20;
	}
        if (postData == null){
		postData = {};
	}else{
		if(postData.loadonce){
			loadonce = true;	
		}
		if(postData.subGrid){
			subGrid = true;	
		}
		if(postData.rownumbers){
			rownumbers = true;	
		}
	}
	grid.jqGrid({
		data: data,
		datatype: "local",
		rownumbers : rownumbers,
		hidegrid : false,
		rowNum : rownum,
		pager : selectorPager,
//		toppager: true,
//		toppagerOnly: true,
//		emptyrecords : "No se encontraron resultados",
		emptyrecords : "",
		loadtext : "Cargando",
        colNames : colNames,
        colModel : colModel,
        cmTemplate : {title:false, resizable:false},
        height : 'auto',
        scrollOffset: 0,
        viewrecords : true,
        caption : caption,
        autowidth: false,
        loadonce: loadonce,
        subGrid:subGrid,
        subGridOptions: {
            "plusicon"  : "ui-icon-circle-plus",
            "minusicon" : "ui-icon-circle-minus",
//            "openicon"  : "ui-icon-circle-triangle-e",
            "openicon": "ui-helper-hidden",
    		// load the subgrid data only once
    		// and the just show/hide
    		"reloadOnExpand" : false,
    		// select the row when the expand column is clicked
    		"selectOnExpand" : true
    	},
        rowattr: function (row) {
            if (row.activo === "NO") {
            	return {"class": "disabled-row"};
            } 
        },
        onSelectRow: function(rowid, status) {
            grid.resetSelection();
        },
        loadComplete: function(data){
            if (typeof(loadCompleteFunction) != 'undefined' && loadCompleteFunction != null) 
        		loadCompleteFunction(data.rows);
        }
	});
        if (typeof(colSubgridCollapserName) !== 'undefined' && colSubgridCollapserName !== null){
            grid.jqGrid('setGridParam',{
                afterInsertRow: function(rowid, aData, rowelem) {
                    var rowData = grid.getRowData(rowid);               	
                    if( rowData[colSubgridCollapserName]==0){
                              $('tr#'+rowid, grid)
                              .children("td.sgcollapsed")
                              .html("")
                              .removeClass('ui-sgcollapsed sgcollapsed');
                }
            }
        });
	}
        
	return grid;
}

/**
 * 
 * @param idTable identificador del table donde se renderiza el jqgrid
 * @param pager booleano que indica si habra paginado (true o false)
 * @param rownum numero de filas a mostrar por pagina
 * @param colNames array con el nombre de las columnas
 * @param colModel array con el column model de las columnas
 * @param url URL que devuelve el JSON con los datos
 * @param postData array con los parametros de la busqueda
 * @param idRow nombre del atributo identificador de la clase DTO
 * @param caption titulo del jqgrid
 * @param loadCompleteFunction funcion que se ejecuta en el evento loadComplete
 * @param colSubgridCollapserName nombre de columna que indica cuando ocultar boton de subgrid debe ser numerico (0 para ocultar)
 * @returns objeto jqgrid inicializado
 */
function jqgridRemote(idTable, pager, rownum, colNames, colModel, url, postData, idRow, caption, loadCompleteFunction, colSubgridCollapserName){
	var idGrid = idTable;
	var grid = $("#"+idGrid);
	var idPager = null;
	var selectorPager = null;
	var loadonce = false;
	var subGrid = false;
	var rownumbers = false;
	if (pager === true){
		idPager = "pager_"+idGrid;
		grid.after("<div id="+idPager+"></div>");
		selectorPager = "#"+idPager;
		if (rownum == null){
			rownum = 10;
		}
	}else{
		rownum = 20;
	}
	
	if (postData == null){
		postData = {};
	}else{
		if(postData.loadonce){
			loadonce = true;	
		}
		if(postData.subGrid){
			subGrid = true;	
		}
		if(postData.rownumbers){
			rownumbers = true;	
		}
	}
	grid.jqGrid({
		url:url,
		datatype: "json",
		postData: postData,
		rownumbers : rownumbers,
		hidegrid : false,
		rowNum : rownum,
		pager : selectorPager,
//		toppager: true,
//		toppagerOnly: true,
//		emptyrecords : "No se encontraron resultados",
		emptyrecords : "",
		loadtext : "Cargando",
        colNames : colNames,
        colModel : colModel,
        cmTemplate : {title:false, resizable:false},
        height : 'auto',
        scrollOffset: 0,
        viewrecords : true,
        caption : caption,
        autowidth: true,
        loadonce: loadonce,
        subGrid:subGrid,
        subGridOptions: {
            "plusicon"  : "ui-icon-circle-plus",
            "minusicon" : "ui-icon-circle-minus",
//            "openicon"  : "ui-icon-circle-triangle-e",
            "openicon": "ui-helper-hidden",
    		// load the subgrid data only once
    		// and the just show/hide
    		"reloadOnExpand" : false,
    		// select the row when the expand column is clicked
    		"selectOnExpand" : true
    	},
//    	subGridWidth : '90%',
        jsonReader : {
            root : "filas", //la lista de registros a mostrar en el grid
            page : "pagina", //la pagina actual
            total : "total", //total de paginas
            records : "registros", //numero total de registros
            repeatitems : false,
            id : idRow
        },
        rowattr: function (row) {
            if (row.activo === "NO") {
            	return {"class": "disabled-row"};
            } 
        },
        onSelectRow: function(rowid, status) {
//            grid.resetSelection();
        },
        loadComplete: function(data){
        	if (typeof(loadCompleteFunction) != 'undefined' && loadCompleteFunction != null) 
        		loadCompleteFunction(data);
        }
	});
	
	if (typeof(colSubgridCollapserName) != 'undefined' && colSubgridCollapserName != null){
		grid.jqGrid('setGridParam',{
            afterInsertRow: function(rowid, aData, rowelem) {
            	var rowData = grid.getRowData(rowid);               	
                if( rowData[colSubgridCollapserName]==0){
	                  $('tr#'+rowid, grid)
	                  .children("td.sgcollapsed")
	                  .html("")
	                  .removeClass('ui-sgcollapsed sgcollapsed');
                }
            }
        });
	}
	return grid;
}