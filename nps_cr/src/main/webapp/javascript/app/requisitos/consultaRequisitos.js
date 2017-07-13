var consultaRequisitos = (function() {
    var path = "/nps/pages/consultaRequisitos";
    var listadoRequisitos = [];
    var listadoComentarioProcedimiento = [];
    // Public Methods
    function iniciarPagina() {
        inicializarComponentes();
        procesarGridRequisitos();
        procesarGridComentarioProcedimiento();
        configuracionAyuda();
    }

    // Private Methods
    function inicializarComponentes() {
        $("#dvRubro").hide();
        $("#dvTituloEtapa").hide();
        $("#dvEtapa").hide();
        $("#dvTramite").hide();
        ocultarRequisitos();

        $('#cmbActividad').change(function() {
            if (this.value !== "") {
                cargarRubro(this.value);
                $("#dvRubro").show();
                $("#dvTituloEtapa").hide();
                $("#dvEtapa").hide();
                $("#dvTramite").hide();
                $("#cmbTramite").val("");
                ocultarRequisitos();
            } else {
                $("#dvRubro").hide();
                $("#cmbActividad").val("");
                $("#dvTituloEtapa").hide();
                $("#dvEtapa").hide();
                $("#dvTramite").hide();
                $("#cmbTramite").val("");
                ocultarRequisitos();
            }
        });

        $('#cmbRubro').change(function() {
            if (this.value != "0") {
                cargarEtapas();

                $("#dvTramite").hide();
                $("#cmbTramite").val("");
                ocultarRequisitos();
            } else {
                $("#dvTituloEtapa").hide();
                $("#dvEtapa").hide();
                $("#dvTramite").hide();
                $("#cmbTramite").val("");
                ocultarRequisitos();
            }
        });

        $('#cmbTramite').change(function() {
            ocultarRequisitos();
            if (this.value != "0") {
                obtenerPreguntasEspecificas();
            }
        });

        $('#linkConsulta').click(function() {
            abrirPopupMensaje();
        });

        $("#dialogOficinas").dialog({
            resizable: false,
            draggable: false,
            autoOpen: false,
            height: "auto",
            width: "auto",
            modal: true,
            dialogClass: 'dialog',
            title: "Oficinas de Atención"
        });

        $('#btnCerrarOfinas').click(function() {
            $("#dialogOficinas").dialog("close");
        });
        cargarActividad();
        obtenerValorUIT();
    }
    
    function obtenerValorUIT() {
        $.get(path + "/obtenerValorUIT?", null, function(data) {
            if (data.resultado === 0) {
                $("#lblValorUIT").html(data.valorUIT);
            } else {
                mensajeGrowl("error", "Error", data.mensaje);
            }
        });
    }

    function ocultarRequisitos() {
        indicePregunta = -1;
        $("#dvProcedimiento").hide();
        $("#dvRequisitos").hide();
        $('#divPreguntas').empty();
    }

    function descargaFormat(cellvalue, options, rowObject) {
        var idDocumentoAdjunto = rowObject['idDocumentoAdjunto'];
        var link = "";
        if (idDocumentoAdjunto != null) {
            link = "<a onclick='consultaRequisitos.download(this,"+idDocumentoAdjunto+")'><img style='cursor: pointer;' src='/nps/images/download.png' height='19' width='18'></a>";
        }
        return link;
    }
    
    function download(obj, idDocumentoAdjunto) {
        console.log("download -- idDocumentoAdjunto =" + idDocumentoAdjunto);
        obj.href = path + '/descargaArchivoAlfresco?idDocumentoAdjunto=' + idDocumentoAdjunto;
    }

    function procesarGridRequisitos() {

        var colNames = ['ID', 'REQUISITOS', 'COMENTARIO', 'TIENE_ARCHIVO', 'DESCARGUE AQUÍ','comentarioRequisito'];
        var colModel = [
            {name: "idRequisito", width: 150, sortable: false, hidden: true, align: "center"},
            {name: "descripcion", width: 700, sortable: false, hidden: false, align: "left"},
            {name: "comentarioPredeterminado", width: 10, sortable: false, hidden: true, align: "left"},
            {name: "idDocumentoAdjunto", width: 10, sortable: false, hidden: true, align: "left"},
            {name: 'descarga', width: 100, fixed: true, sortable: false, resize: false, align: "center",formatter: descargaFormat},
            {name: "requisitoProcedimiento.comentario", width: 10, sortable: false, align: "left",hidden:true}

        ];
        var postData = {loadonce: false, subGrid: true, rownumbers: true};
        var grid = jqgridLocal("gridRequisitos", true, 30, colNames, colModel, listadoRequisitos, null, "", postData, "requisitoProcedimiento.comentario", colocarNumeracionRequisitosConsulta);
        grid.jqGrid('setGridParam', {
            subGridRowExpanded: function(subgrid_id, row_id) {
                var subgrid_table_id;
                subgrid_table_id = subgrid_id + "_t";
                var fila = grid.getRowData(row_id);
                //var comentario = fila.comentarioPredeterminado;
                var comentario = fila['requisitoProcedimiento.comentario'];
                var htmlComentario = "<table id='" + subgrid_table_id + "' class='scroll' width='80%'>";
                htmlComentario += "<tr><td align='justify'>" + comentario + "</td></tr>";
                htmlComentario += "</table>";
                $("#" + subgrid_id).html(htmlComentario);
            }
        });

    }
    
    function colocarNumeracionRequisitosConsulta(data){
        var contador=0;
        $('#gridRequisitos').find('tr.ui-row-ltr').map(function(){
            var nro = data[contador].nroParaConsulta!=null?data[contador].nroParaConsulta:'';
            if(nro.indexOf(".")!==-1){nro="";}
            $(this).children('td').eq(0).html(nro);
            $(this).children('td').eq(0).attr('title',nro);            
            contador++;           
        });
    }

    function procesarGridComentarioProcedimiento() {
        listadoComentarioProcedimiento = [];
        var colNames = [ 'NOTA DEL PROCEDIMIENTO'];
        var colModel = [
            {name: "comentario", width: 830, sortable: false, hidden: false, align: "left"}
        ];
        var postData = {loadonce: false, subGrid: false, rownumbers: true};
        var grid = jqgridLocal("gridComentarioProcedimiento", true, 30, colNames, colModel, listadoComentarioProcedimiento, null, "", postData);
    }

    function cargarActividad() {
        $.getJSON(path + "/obtenerActividades", null, function(data) {
            var html = '<option value="">--Seleccione--</option>';
            var len = data.length;
            for (var i = 0; i < len; i++) {
                html += '<option value="' + data[i].idActividad + '">'
                        + data[i].nombre + '</option>';
            }
            $('#cmbActividad').html(html);
        });
    }

    function cargarRubro(idActividad) {
        $.getJSON(path + "/obtenerRubros", {
            idActividad: idActividad,
            ajax: 'true'
        }, function(data) {
            var html = '<option value="0">--Seleccione--</option>';
            var len = data.length;
            for (var i = 0; i < len; i++) {
                html += '<option value="' + data[i].idActividad + '">'
                        + data[i].nombre + '</option>';
            }
            html += '</option>';
            $('#cmbRubro').html(html);
        });
    }

    function cargarEtapas() {
        var idActividad = $('#cmbRubro').val();
        $.getJSON(path + "/obtenerEtapas", {idActividad: idActividad, ajax: 'true'}, function(data) {
            var html = '<div style="float:right;" ><img id="imgHelpEtapa" src="/nps/images/pregunta.png" alt="Ayuda" height="16" width="16" class="btnAyuda" onClick="consultaRequisitos.mostrarAyuda(this)" /></div>';
            var len = data.length;
            if (len > 0) {
                for (var i = 0; i < len; i++) {
                    html += '<div style="float:left; margin-right:10px;">';
                    html += '<input id="rdEtapa' + (i + 1) + '" type="radio" name="etapa" value="' + data[i].idEtapa + '" class="rdEtapa" />';
                    html += '<label for="rdEtapa' + (i + 1) + '" class="radio">' + data[i].descripcion + '</label>';
                    html += '</div>';
                }
                $('#divEtapa').html(html);
                if (len == 1) {
                    $('.rdEtapa').attr("checked", true);
                    var idActividad = $('#cmbRubro').val();
                    cargarTramite(idActividad, data[0].idEtapa);
                }
                $('.rdEtapa').click(function() {
                    var idActividad = $('#cmbRubro').val();
                    cargarTramite(idActividad, this.value);
                    ocultarRequisitos();
                });
                $("#dvEtapa").show();
                $("#dvTituloEtapa").show();
            } else {
                mensajeGrowl("error", "Error", "No se ha configurado Tramites para este Rubro");
            }
        });
    }

    function cargarTramite(idActividad, idEtapa) {
        $.getJSON(path + "/obtenerTramites", {
            idActividad: idActividad,
            idEtapa: idEtapa,
            ajax: 'true'
        }, function(data) {
            var html = '<option value="0">--Seleccione--</option>';
            var len = data.length;
            for (var i = 0; i < len; i++) {
                html += '<option value="' + data[i].idTramite + '">'
                        + data[i].descripcion + '</option>';
            }
            html += '</option>';
            $('#cmbTramite').html(html);
            $("#dvTramite").show();
        });
    }

    var arrayPreguntas = [];
    var indicePregunta = -1;
    function obtenerPreguntasEspecificas() {
        var idActividad = $("#cmbRubro").val();
        var idTramite = $("#cmbTramite").val();
        $.getJSON(path + "/obtenerPreguntasEspecificas", {
            idActividad: idActividad,
            idTramite: idTramite,
            ajax: 'true'
        }, function(data) {
            if (data.resultado == 0) {
                $("#txtIdProcedimiento").val(data.idProcedimiento);
                if (data.preguntasEspecificas.length != 0) {
                    arrayPreguntas = [];
                    indicePregunta = 0;
                    for (var x = 0; x < data.preguntasEspecificas.length; x++) {
                        arrayPreguntas.push(data.preguntasEspecificas[x]);
                    }
                    showPreguntaParametroDinamico(arrayPreguntas[indicePregunta]);
                } else {
                    obtenerRequisitos();
                }
            } else {
                mensajeGrowl("error", "Error", data.mensaje);
            }
        });
    }

    function showPreguntaParametroDinamico(preguntasEspecificas) {
        if (preguntasEspecificas.tipoPregunta != 4) {// Si es diferente a Pregunta Tipo Zonificacion
            if (preguntasEspecificas.listaPreguntaRequisitoValor.length > 1) {
                var html = "<div id='divPregunta_" + indicePregunta + "' tipopregunta='" + preguntasEspecificas.tipoPregunta + "' idpregunta='" + preguntasEspecificas.idPregunta + "' elementos='" + preguntasEspecificas.listaPreguntaRequisitoValor.length + "' >";
                html += "    <div class='filaForm'>";
                html += "        <div class='lble vam'><label>" + preguntasEspecificas.pregunta + "</label></div>";
                html += "        <div>";
                html += "            <select id='cmbPregunta_" + indicePregunta + "' >";
                html += "                <option value='0'>--Seleccione--</option>";
                for (var x = 0; x < preguntasEspecificas.listaPreguntaRequisitoValor.length; x++) {
                    html += "                <option value='" + preguntasEspecificas.listaPreguntaRequisitoValor[x].idPreguntaRequisitoValor + "'>" + preguntasEspecificas.listaPreguntaRequisitoValor[x].preguntaValor + "</option>";
                }
                html += "            </select>";
                html += "        <img src='/nps/images/pregunta.png' alt='Ayuda' height='16' width='16' class='btnAyuda' style='visibility: hidden' />";
                html += "        </div>";
                html += "    </div>";
                html += "</div>";
                $('#divPreguntas').append(html);

                $("#cmbPregunta_" + indicePregunta).change(function() {
                    $("#dvProcedimiento").hide();
                    $("#dvRequisitos").hide();
                    indicePregunta = this.id.split("_")[1];
                    removeChildren("divPregunta_" + this.id.split("_")[1]);
                    if (this.value != 0) {
                        if (indicePregunta < (arrayPreguntas.length - 1)) {
                            indicePregunta++;
                            showPreguntaParametroDinamico(arrayPreguntas[indicePregunta]);
                        } else {
                            obtenerRequisitos();
                        }
                    }
                });
            } else {
                var html = "<div id='divPregunta_" + indicePregunta + "' tipopregunta='" + preguntasEspecificas.tipoPregunta + "' idpregunta='" + preguntasEspecificas.idPregunta + "' elementos='" + preguntasEspecificas.listaPreguntaRequisitoValor.length + "' >";
                html += "    <div class='filaForm'>";
                html += "        <div class='lble' style='float:left; margin-right:30px;'><label>" + preguntasEspecificas.pregunta + "</label></div>";
                html += "        <div style='float:left; margin-right:30px;'>";
                html += "            <input id='rdPregunta_" + indicePregunta + "_1' type='radio' name='pregunta_" + indicePregunta + "' value='" + preguntasEspecificas.listaPreguntaRequisitoValor[0].idPreguntaRequisitoValor + "' class='rdPregunta_" + indicePregunta + "'/>";
                html += "            <label for='rdPregunta_" + indicePregunta + "_1' class='radio'>SI</label>";
                html += "        </div>";
                html += "        <div style='float:left; margin-right:30px;'>";
                html += "            <input id='rdPregunta_" + indicePregunta + "_2' type='radio' name='pregunta_" + indicePregunta + "' value='0' class='rdPregunta_" + indicePregunta + "'/>";
                html += "            <label for='rdPregunta_" + indicePregunta + "_2' class='radio'>NO</label>";
                html += "        </div>";
                if (preguntasEspecificas.pregunta == '¿Es usted un Operador Múltiple?') {
                    html += '        <div style="float:left; margin-right:30px;" ><img id="imgHelpMultiOperador" src="/nps/images/pregunta.png" alt="Ayuda" height="16" width="16" class="btnAyuda" onClick="consultaRequisitos.mostrarAyuda(this)" /></div>';
                }
                html += "    </div>";
                html += "</div>";
                html += "<br>";
                html += "<br>";
                $('#divPreguntas').append(html);

                $(".rdPregunta_" + indicePregunta).click(function() {
                    if (indicePregunta < (arrayPreguntas.length - 1)) {
                        indicePregunta++;
                        showPreguntaParametroDinamico(arrayPreguntas[indicePregunta]);
                    } else {
                        obtenerRequisitos();
                    }
                });
            }
        } else { //Es Pregunta Tipo Zonificacion
            var html = "<div id='divPregunta_" + indicePregunta + "' tipopregunta='" + preguntasEspecificas.tipoPregunta + "' idpregunta='" + preguntasEspecificas.idPregunta + "'>";
            html += "<div  class='filaForm'>";
            html += "        <div ><label>" + preguntasEspecificas.pregunta + "</label></div>";
            html += "</div>";
            html += "<div  class='filaForm'>";
            html += " <div class='lble'><label>Departamento : </label></div>"
            html += "<div>";
            html += " <select id='cmbDepartamento_" + indicePregunta + "' class='cmbUbigeo' style='width: 280px' >";
            html += "     <option value='0'>--Seleccione--</option>";
            html += " </select>";
            html += "</div>";
            html += "</div>";

            html += "<div class='filaForm'>";
            html += " <div class='lble'><label>Provincia : </label></div>";
            html += "<div>";
            html += " <select id='cmbProvincia_" + indicePregunta + "' class='cmbUbigeo' style='width: 280px'>";
            html += "     <option value='0'>--Seleccione--</option>";
            html += " </select>";
            html += "</div>";
            html += "</div>";

            html += "<div  class='filaForm'>";
            html += "<div class='lble'><label>Distrito : </label></div>";
            html += "<div>";
            html += " <select id='cmbDistrito_" + indicePregunta + "' class='cmbUbigeo' style='width: 280px'>";
            html += "     <option value='0'>--Seleccione--</option>";
            html += " </select>";
            html += "</div>";
            html += "</div>";
            html += "</div>";

            $('#divPreguntas').append(html);

            $.getJSON(path + "/buscarDepartamentos", null, function(data) {
                var html = '<option value="0">--Seleccione--</option>';
                var len = data.length;
                for (var i = 0; i < len; i++) {
                    html += '<option value="' + data[i].idDepartamento + '">'
                            + data[i].nombre + '</option>';
                }
                html += '</option>';
                $("#cmbDepartamento_" + indicePregunta).html(html);
                html = '<option value="0">--Seleccione--</option>';
                $("#cmbProvincia_" + indicePregunta).html(html);
                $("#cmbDistrito_" + indicePregunta).html(html);
            });

            $('#cmbDepartamento_' + indicePregunta).change(function() {
                var indice = this.id.split("_");
                $data = {
                    idDepartamento: this.value
                };
                $.getJSON(path + "/buscarProvincias", $data, function(data) {
                    var html = '<option value="0">--Seleccione--</option>';
                    var len = data.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + data[i].idProvincia + '">'
                                + data[i].nombre + '</option>';
                    }
                    html += '</option>';
                    $("#cmbProvincia_" + indice[1]).html(html);
                    html = '<option value="0">--Seleccione--</option>';
                    $("#cmbDistrito_" + indice[1]).html(html);
                    ubigeo(indice[1]);
                });
            });

            $('#cmbProvincia_' + indicePregunta).change(function() {
                var indice = this.id.split("_");
                $data = {
                    idDepartamento: $("#cmbDepartamento_" + indice[1]).val(),
                    idProvincia: this.value
                };
                $.getJSON(path + "/buscarDistritos", $data, function(data) {
                    var html = '<option value="0">--Seleccione--</option>';
                    var len = data.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + data[i].idDistrito + '">'
                                + data[i].nombre + '</option>';
                    }
                    html += '</option>';
                    $("#cmbDistrito_" + indice[1]).html(html);
                    ubigeo(indice[1]);
                });
            });

            $('#cmbDistrito_' + indicePregunta).change(function() {
                var indice = this.id.split("_");
                ubigeo(indice[1]);
            });

        }
    }

    function ubigeo(indice) {
        $("#dvProcedimiento").hide();
        $("#dvRequisitos").hide();
        var dep = $('#cmbDepartamento_' + indice).val();
        var pro = $('#cmbProvincia_' + indice).val();
        var dis = $('#cmbDistrito_' + indice).val();
        var id = "divPregunta_" + indice;
        if ((dep !== "0" && pro !== "0" && dis !== "0")) {
            if (indicePregunta < (arrayPreguntas.length - 1)) {
                indicePregunta++;
                showPreguntaParametroDinamico(arrayPreguntas[indicePregunta]);
            }
            else {
                obtenerRequisitos();
            }
        } else {
            removeChildren(id);
        }
    }

    function obtenerRequisitos() {
        var preguntas = obtenerPreguntas();
        $data = {idTramite: $("#cmbTramite").val(),
            idActividad: $("#cmbRubro").val(),
            idProcedimiento: $("#txtIdProcedimiento").val()
        };
        $.get(path + "/obtenerRequisitos?" + preguntas, $data, function(data) {
            if (data.resultado === 0) {
                $("#gridRequisitos").jqGrid("clearGridData", true);
                $("#gridRequisitos").setGridParam({
                    data: data.listaRequisito,
                    datatype: "local" // !!! reset datatype
                }).trigger("reloadGrid");
                $("#divComentarioProcedimiento").hide();
                if (data.listaComentarioProcedimiento.length > 0) {
                    $("#gridComentarioProcedimiento").jqGrid("clearGridData", true);
                    $("#gridComentarioProcedimiento").setGridParam({
                        data: data.listaComentarioProcedimiento,
                        datatype: "local" // !!! reset datatype
                    }).trigger("reloadGrid");
                    $("#divComentarioProcedimiento").show();
                }


                if (data.procedimiento.costoTramite == 0) {
                    $("#lblCostoTramite").html("Gratuito");
                } else {
                    $("#lblCostoTramite").html("S/. " + data.procedimiento.costoTramite);
                }
                $("#lblPlazoAtencion").html(data.procedimiento.plazoResolver);
                $("#lblSilencioAdministrativo").html(data.procedimiento.silencioAdministrativo);
                $("#lblNotaIndicacionLegible").html(data.procedimiento.indicacionLegible);
                $("#lblNotaIndicacionPlazo").html(data.procedimiento.indicacionPlazo);
                $("#lblNotaSilencioAdministrativo").html(data.procedimiento.silencioAdministrativoMensaje);
                $("#dvProcedimiento").show();
                $("#dvRequisitos").show();
            } else {
                mensajeGrowl("error", "Error", data.mensaje);
            }
        });
    }

    function obtenerPreguntas() {
        var preguntas = "";
        if (arrayPreguntas.length > 0) {
            var indicePreguntaValida = 0;
            for (var x = 0; x <= indicePregunta; x++) {
                var tipoPregunta = $("#divPregunta_" + x).attr("tipopregunta");
                if (tipoPregunta != 4) {
                    if ($("#divPregunta_" + x).attr("elementos") === "1") {
                        if ($("#rdPregunta_" + x + "_1").attr("checked") == "checked") {
                            preguntas += "preguntasRequisitos[" + indicePreguntaValida + "].idPregunta=" + $("#divPregunta_" + x).attr("idpregunta") + "&";
                            preguntas += "preguntasRequisitos[" + indicePreguntaValida + "].tipoPregunta=" + tipoPregunta + "&";
                            preguntas += "preguntasRequisitos[" + indicePreguntaValida + "].preguntaRespuesta=" + $("#rdPregunta_" + x + "_1").val() + "&";
                            indicePreguntaValida++;
                        }
                    } else {
                        if ($("#cmbPregunta_" + x).val() != 0) {
                            preguntas += "preguntasRequisitos[" + indicePreguntaValida + "].idPregunta=" + $("#divPregunta_" + x).attr("idpregunta") + "&";
                            preguntas += "preguntasRequisitos[" + indicePreguntaValida + "].tipoPregunta=" + tipoPregunta + "&";
                            preguntas += "preguntasRequisitos[" + indicePreguntaValida + "].preguntaRespuesta=" + $("#cmbPregunta_" + x).val() + "&";
                            indicePreguntaValida++;
                        }
                    }
                } else {
                    var respuesta = $("#cmbDepartamento_" + x).val() + "_" + $("#cmbProvincia_" + x).val() + "_" + $("#cmbDistrito_" + x).val();
                    preguntas += "preguntasRequisitos[" + indicePreguntaValida + "].idPregunta=" + $("#divPregunta_" + x).attr("idpregunta") + "&";
                    preguntas += "preguntasRequisitos[" + indicePreguntaValida + "].tipoPregunta=" + tipoPregunta + "&";
                    preguntas += "preguntasRequisitos[" + indicePreguntaValida + "].preguntaRespuesta=" + respuesta + "&";
                    indicePreguntaValida++;
                }
            }
            if (preguntas != "") {
                preguntas = preguntas.substring(0, preguntas.length - 1);
            }
        }
        return preguntas;
    }

    function removeChildren(id) {
        while (id != $('#divPreguntas').children().last().attr('id')) {
            $('#divPreguntas').children().last().remove();
        }
    }

    function downloadReporte(obj, tipo) {
        var parametros = obtenerPreguntas();
        parametros += "&idTramite=" + $("#cmbTramite").val();
        parametros += "&idActividad=" + $("#cmbRubro").val();
        parametros += "&idProcedimiento=" + $("#txtIdProcedimiento").val();
        parametros += "&tipo=" + tipo;
        obj.href = path + '/downloadFile?' + parametros;
        if(tipo == 'html'){
            obj.target = "_blank";
        }else{
            obj.target = "";
        }
    }

    function abrirPopupMensaje() {
        var title = "Sugerencia o Reportes de Inconvenientes";
        $.ajax({
            url: path + '/abrirPopupMensaje',
            type: 'get',
            async: false,
            //        beforeSend:muestraLoading,
            success: function(data) {
//                ocultaLoading();
                $("#dialogMensaje").html(data);
                $("#dialogMensaje").dialog({
                    resizable: false,
                    draggable: false,
                    autoOpen: true,
                    height: "auto",
                    width: "auto",
                    modal: true,
                    dialogClass: 'dialog',
                    title: title
                });
            },
            error: errorAjax
        });
    }

    function configuracionAyuda() {
        $("#divAyuda").hide();
        $("#divAyuda").css({
            position: "fixed",
            top: "10%",
            left: "90%"
        });

        $(".btnAyuda").click(function() {

            if (this.id == 'imgHelpActividad') {
            	buscarTipoAyuda(1);
            }
            if (this.id == 'imgHelpRubro') {
            	buscarTipoAyuda(2);
            }
            if (this.id == 'imgHelpEtapa') {
            	buscarTipoAyuda(3);
            }
            if (this.id == 'imgHelpTramite') {
            	buscarTipoAyuda(4);
            }


            $("#divAyuda").show();
            $('#divAyuda').animate({
                'margin-left': '-370px'
            }, 1500);
        });

        $("#btnCerrarAyuda").click(function() {
            $('#divAyuda').animate({
                'margin-left': '370px'
            }, 1500);
        });
    }

    function mostrarAyuda(obj) {
        if (obj.id == 'imgHelpEtapa') {
        	buscarTipoAyuda(3);
        }
        if (obj.id == 'imgHelpMultiOperador') {
        	buscarTipoAyuda(8);
            //$("#lblMensajeAyuda").html(ayudaMultiOperador);
        }
        $("#divAyuda").show();
        $('#divAyuda').animate({
            'margin-left': '-370px'
        }, 1500);
    }
    function buscarTipoAyuda(ayuda){
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
               	if (ayuda==1) {$('#lblMensajeAyuda').html(data.descripcion);}
               	if (ayuda==2) {$('#lblMensajeAyuda').html(data.descripcion);}
               	if (ayuda==3) {$('#lblMensajeAyuda').html(data.descripcion);}
               	if (ayuda==4) {$('#lblMensajeAyuda').html(data.descripcion);}
               }else{
               	$('#lblMensajeAyuda').text("error");	
               }
               ocultaLoading();
           },
           //error: errorAjax
          
       });
   }

    // Public API
    return {
        iniciarPagina: iniciarPagina,
        mostrarAyuda: mostrarAyuda,
        abrirPopupMensaje: abrirPopupMensaje,
        downloadReporte: downloadReporte,
        download:download
    };
})();

$(function() {
    consultaRequisitos.iniciarPagina();

});