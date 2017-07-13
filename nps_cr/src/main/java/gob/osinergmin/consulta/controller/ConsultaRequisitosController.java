/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.controller;

import gob.osinergmin.consulta.dto.ActividadDTO;
import gob.osinergmin.consulta.dto.ComentarioDTO;
import gob.osinergmin.consulta.dto.DepartamentoDTO;
import gob.osinergmin.consulta.dto.DistritoDTO;
import gob.osinergmin.consulta.dto.EtapaDTO;
import gob.osinergmin.consulta.dto.PreguntaRequisitoDTO;
import gob.osinergmin.consulta.dto.PreguntasRequisitosDTO;
import gob.osinergmin.consulta.dto.ProcedimientoDTO;
import gob.osinergmin.consulta.dto.ProvinciaDTO;
import gob.osinergmin.consulta.dto.RequisitoDTO;
import gob.osinergmin.consulta.dto.TramiteDTO;
import gob.osinergmin.consulta.service.ConsultaRequisitosService;
import gob.osinergmin.consulta.service.exception.ConsultaRequisitoException;
import gob.osinergmin.consulta.util.Constantes;
import gob.osinergmin.consulta.util.ConstantesWeb;
import gob.osinergmin.consulta.util.JasperUtil;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.IOUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author lchancayauri
 */
@Controller
@RequestMapping("/consultaRequisitos")
public class ConsultaRequisitosController {

    private static final Logger log = LoggerFactory.getLogger(ConsultaRequisitosController.class);
    @Value("${ayuda.actividad}")
    private String ayudaActividad;
    @Value("${ayuda.rubro}")
    private String ayudaRubro;
    @Value("${ayuda.etapa}")
    private String ayudaEtapa;
    @Value("${ayuda.tramite}")
    private String ayudaTramite;
    @Value("${ayuda.multioperador}")
    private String multioperador;
    @Value("${nombrereporte.requisitos}")
    private String nombreReporteRequisito;
    
    @Inject
    private ConsultaRequisitosService consultaRequisitosService;

    @RequestMapping
    public String consultarRequisitos(Model model, HttpSession session, HttpServletRequest request) throws Exception {
        log.info("-- ConsultaRequisitosController - consultarRequisitos --");
        model.addAttribute(Constantes.P_ACTIVIDAD, ayudaActividad);
        model.addAttribute(Constantes.P_RUBRO, ayudaRubro);
        model.addAttribute(Constantes.P_ETAPA, ayudaEtapa);
        model.addAttribute(Constantes.P_TRAMITE, ayudaTramite);
        model.addAttribute(Constantes.P_MULTIOPERADOR, multioperador);
        session.setAttribute(Constantes.LOGIN_USUARIO, Constantes.LOGIN_USUARIO_ADMINISTADO);
        if (request.getSession().isNew() == true) {
            Long contador = (Long) request.getSession().getServletContext().getAttribute(Constantes.P_CONTADOR_REQUISITOS);
            log.info("-- ConsultaRequisitosController - contador == "+contador);
            if(contador == null){
                contador = new Long(0);
            }
            request.getSession().getServletContext().setAttribute(Constantes.P_CONTADOR_REQUISITOS, new Long(contador.intValue() + 1));
        }
        return ConstantesWeb.Navegacion.PAGE_MENU_CONSULTA_REQUISITOS;
    }

    @RequestMapping(value = "/obtenerActividades", method = RequestMethod.GET)
    public @ResponseBody
    List<ActividadDTO> obtenerActividades() {
        log.info("procesando GET para RequestMapping ConsultaRequisitosController/obtenerActividades");
        List<ActividadDTO> listaActividad = consultaRequisitosService.listarActitividades();
        return listaActividad;
    }

    @RequestMapping(value = "/obtenerRubros", method = RequestMethod.GET)
    public @ResponseBody
    List<ActividadDTO> obtenerRubros(Long idActividad) {
        log.info("procesando GET para RequestMapping ConsultaRequisitosController/obtenerRubros");
        log.info("idActividad = " + idActividad);
        List<ActividadDTO> listaActividad = consultaRequisitosService.listarRubros(idActividad);
        return listaActividad;
    }

    @RequestMapping(value = "/obtenerEtapas", method = RequestMethod.GET)
    public @ResponseBody
    List<EtapaDTO> obtenerEtapas(Long idActividad) {
        log.info("procesando GET para RequestMapping ConsultaRequisitosController/obtenerEtapas");
        log.info("idActividad = " + idActividad);
        List<EtapaDTO> lstEtapa = consultaRequisitosService.listarEtapas(idActividad);
        return lstEtapa;
    }

    @RequestMapping(value = "/obtenerTramites", method = RequestMethod.GET)
    public @ResponseBody
    List<TramiteDTO> obtenerTramites(Long idActividad, Long idEtapa) {
        log.info("procesando GET para RequestMapping ConsultaRequisitosController/obtenerTramites");
        log.info("idActividad = " + idActividad);
        log.info("idEtapa = " + idEtapa);
        List<TramiteDTO> listTramite = consultaRequisitosService.listarTramites(idActividad, idEtapa);
        return listTramite;
    }

    @RequestMapping(value = "/obtenerPreguntasEspecificas", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> obtenerPreguntasEspecificas(Long idActividad, Long idTramite) {
        log.info("procesando GET para RequestMapping ConsultaRequisitosController/obtenerPreguntasEspecificas");
        Map<String, Object> map = new HashMap<String, Object>();
        log.info("-- idActividad = " + idActividad);
        log.info("-- idTramite = " + idTramite);
        try {
            Map<String, Object> datos = consultaRequisitosService.listarPreguntasEspecificas(idActividad, idTramite);
            Long idProcedimiento = (Long) datos.get("idProcedimiento");
            List<PreguntaRequisitoDTO> listaPreguntasEspecificas = (List<PreguntaRequisitoDTO>) datos.get("listaPreguntaRequisito");
            log.info("listaPreguntasEspecificas = " + listaPreguntasEspecificas.size());
            log.info("****************************************************");
            for (PreguntaRequisitoDTO preguntaRequisitoDTO : listaPreguntasEspecificas) {
                log.info("idPreguntaRequisito = " + preguntaRequisitoDTO.getIdPreguntaRequisito());
                log.info("idPregunta = " + preguntaRequisitoDTO.getIdPregunta());
                log.info("tipoPregunta = " + preguntaRequisitoDTO.getTipoPregunta());
                log.info("Pregunta = " + preguntaRequisitoDTO.getPregunta());
                log.info("ListaPreguntaRequisitoValor = " + preguntaRequisitoDTO.getListaPreguntaRequisitoValor().size());
                log.info("------------------------------------------------");
            }
            map.put("idProcedimiento", idProcedimiento);
            map.put("preguntasEspecificas", listaPreguntasEspecificas);
            map.put(ConstantesWeb.VV_RESULTADO, ConstantesWeb.VV_EXITO);
        } catch (ConsultaRequisitoException ex) {
            String mensaje = ex.getMessage();
            map.put(ConstantesWeb.VV_MENSAJE, mensaje);
            map.put(ConstantesWeb.VV_RESULTADO, ConstantesWeb.VV_ERROR);
        } catch (Exception ex) {
            String mensaje = ex.getMessage();
            map.put(ConstantesWeb.VV_MENSAJE, mensaje);
            map.put(ConstantesWeb.VV_RESULTADO, ConstantesWeb.VV_ERROR);
        }
        return map;
    }

    @RequestMapping(value = "/obtenerRequisitos", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> obtenerRequisitos(PreguntasRequisitosDTO preguntasRequisitos, Long idActividad, Long idTramite, Long idProcedimiento, HttpServletRequest request, HttpSession session, Model model) {
        log.info("procesando GET para RequestMapping ConsultaRequisitosController/obtenerRequisitos");
        log.info("-- idActividad = " + idActividad);
        log.info("-- idTramite = " + idTramite);
        log.info("-- idProcedimiento = " + idProcedimiento);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<PreguntaRequisitoDTO> listaProductos = preguntasRequisitos.getPreguntasRequisitos();
            for (PreguntaRequisitoDTO preguntaRequisitoDTO : listaProductos) {
                log.info("********************************************");
                log.info("IdPregunta = " + preguntaRequisitoDTO.getIdPregunta());
                log.info("TipoPregunta = " + preguntaRequisitoDTO.getTipoPregunta());
                log.info("PreguntaRespuesta = " + preguntaRequisitoDTO.getPreguntaRespuesta());
            }
            Map data = consultaRequisitosService.listarRequisitos(idActividad, idTramite, idProcedimiento, listaProductos);
            List<RequisitoDTO> listaRequisito = (List<RequisitoDTO>) data.get("listaRequisito");
            List<ComentarioDTO> listaComentarioProcedimiento = (List<ComentarioDTO>) data.get("listaComentarioProcedimiento");
            ProcedimientoDTO procedimientoDTO = (ProcedimientoDTO) data.get("procedimiento");
            map.put("listaRequisito", listaRequisito);
            map.put("listaComentarioProcedimiento", listaComentarioProcedimiento);
            map.put("procedimiento", procedimientoDTO);
            map.put(ConstantesWeb.VV_RESULTADO, ConstantesWeb.VV_EXITO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            map.put(ConstantesWeb.VV_MENSAJE, e.getMessage());
            map.put(ConstantesWeb.VV_RESULTADO, ConstantesWeb.VV_ERROR);
        }
        return map;
    }

    @RequestMapping(value = "/buscarDepartamentos", method = RequestMethod.GET)
    public @ResponseBody
    List<DepartamentoDTO> buscarDepartamentos() {
        log.info("procesando GET para RequestMapping ConsultaRequisitosController/buscarDepartamentos");
        List<DepartamentoDTO> lstDepartamento = consultaRequisitosService.obtenerDepartamentos();
        return lstDepartamento;
    }

    @RequestMapping(value = "/buscarProvincias", method = RequestMethod.GET)
    public @ResponseBody
    List<ProvinciaDTO> buscarProvincias(String idDepartamento) {
        log.info("procesando GET para RequestMapping ConsultaRequisitosController/buscarProvincias");
        log.info("idDepartamento = " + idDepartamento);
        List<ProvinciaDTO> lstProvincia = consultaRequisitosService.obtenerProvincias(idDepartamento);
        return lstProvincia;
    }

    @RequestMapping(value = "/buscarDistritos", method = RequestMethod.GET)
    public @ResponseBody
    List<DistritoDTO> buscarDistritos(String idDepartamento, String idProvincia) {
        log.info("procesando GET para RequestMapping ConsultaRequisitosController/buscarDistritos");
        log.info("idDepartamento = " + idDepartamento);
        log.info("idProvincia = " + idProvincia);
        List<DistritoDTO> lstDistrito = consultaRequisitosService.obtenerDistritos(idDepartamento, idProvincia);
        return lstDistrito;
    }

    @RequestMapping(value = "/abrirPopupMensaje", method = RequestMethod.GET)
    public String abrirPopupMensaje(HttpSession sesion, Model model) {
        log.info("abrirPopupMensaje - ConsultaRequisitosController");
        model.addAttribute(Constantes.TIPO_CONSULTA,
                Constantes.APLICACION_REQUISITOS);
        return ConstantesWeb.Navegacion.PAGE_MENU_OBLIGACIONES_MENSAJE;
    }

    /**
     * Metodo que descarga el contenido del listado de obligaciones en formato
     * pdf, excel y html
     *
     * @param request
     * @param sesion
     * @param response
     * @param tipo
     * @throws Exception
     */
    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public void downloadFile(HttpServletRequest request, HttpSession sesion,
            HttpServletResponse response, String tipo, PreguntasRequisitosDTO preguntasRequisitos,
            Long idActividad, Long idTramite, Long idProcedimiento) throws Exception {
        log.info("downloadFile - ConsultaRequisitosController");
        try {
            log.info("** tipo = " + tipo);
            Map<String, Object> datos = consultaRequisitosService.obtenerDatosReporte(idActividad, idTramite, idProcedimiento, preguntasRequisitos.getPreguntasRequisitos());
            ProcedimientoDTO procedimiento = (ProcedimientoDTO) datos.get("procedimiento");

            Collection reportData = new ArrayList();
            reportData.add(procedimiento);

            String rutaImagen = "/images/cabecera.png";
            String rutaReportePrincipal = "/WEB-INF/reports/reporteRequisitosProcedimiento.jasper";
            String rutaReportePrincipalHtml = "/WEB-INF/reports/reporteRequisitosProcedimientoHtml.jasper";
            String rutaReportePrincipalExcel = "/WEB-INF/reports/reporteRequisitosProcedimientoExcel.jasper";
            String rutaReporteRequisitos = "/WEB-INF/reports/reporteRequisitos.jasper";
            String rutaReporteRequisitosExcel = "/WEB-INF/reports/reporteRequisitosExcel.jasper";
            String rutaReporteNotasRequisitos = "/WEB-INF/reports/reporteNotaRequisito.jasper";
            String rutaReporteNotasProcedimiento = "/WEB-INF/reports/reporteNotaProcedimiento.jasper";
            String rutaReportePreguntas = "/WEB-INF/reports/reportePreguntas.jasper";

            ServletContext context = sesion.getServletContext();
            InputStream reportStreamImage = context.getResourceAsStream(rutaImagen);
            InputStream reportJasper = context.getResourceAsStream(rutaReportePrincipal);
            InputStream fileJasperRequisitos = context.getResourceAsStream(rutaReporteRequisitos);
            InputStream fileJasperRequisitosExcel = context.getResourceAsStream(rutaReporteRequisitosExcel);
            InputStream fileJasperNotasRequisito = context.getResourceAsStream(rutaReporteNotasRequisitos);
            InputStream fileJasperNotaProcedimiento = context.getResourceAsStream(rutaReporteNotasProcedimiento);
            InputStream fileJasperPreguntas = context.getResourceAsStream(rutaReportePreguntas);

            HashMap reportParams = new HashMap();
            reportParams.put("valorUIT", datos.get("valorUIT"));
            reportParams.put("actividad", datos.get("actividad"));
            reportParams.put("rubro", datos.get("rubro"));
            reportParams.put("etapa", datos.get("etapa"));
            reportParams.put("tramite", datos.get("tramite"));
            
            reportParams.put("requisitos", datos.get("listaRequisito"));
            
//            List<RequisitoDTO> listaRequisito = (List<RequisitoDTO>)datos.get("listaRequisito");
//            List<RequisitoDTO> listaRequisitoTemp = new ArrayList<RequisitoDTO>();
//            listaRequisitoTemp.addAll(listaRequisito);
//            listaRequisitoTemp.addAll(listaRequisito);
//            listaRequisitoTemp.addAll(listaRequisito);
//            reportParams.put("requisitos", listaRequisitoTemp);
            reportParams.put("notasRequisitos", datos.get("listaComentariosRequisitos"));
            reportParams.put("notasProcedimiento", datos.get("listaComentariosProcedimiento"));
            reportParams.put("preguntas", datos.get("listaPreguntasRequisito"));
            reportParams.put("ruta_imagen", reportStreamImage);
            if (tipo.equals("excel")) {
                reportParams.put("jasperRequisitos", fileJasperRequisitosExcel);
            }else{
                reportParams.put("jasperRequisitos", fileJasperRequisitos);
            }
            reportParams.put("jasperNotasRequisito", fileJasperNotasRequisito);
            reportParams.put("jasperNotasProcedimiento", fileJasperNotaProcedimiento);
            reportParams.put("jasperPreguntas", fileJasperPreguntas);

            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(reportData);
            /////////////////////////////////////////////////////////////////////////
            String strNombre = nombreReporteRequisito;

//            String nombreArchivo;
//            String contentType;
            if (tipo.equals("pdf")) {
                JasperUtil.exportarPDF(reportJasper, reportParams, ds, response,
                        strNombre);
            } else if (tipo.equals("excel")) {
        	reportJasper = context.getResourceAsStream(rutaReportePrincipalExcel);
                JasperUtil.exportarExcel(reportJasper, reportParams, ds,
                        response, strNombre);
            } else if (tipo.equals("html")) {
        	reportJasper = context.getResourceAsStream(rutaReportePrincipalHtml);
                JasperUtil.exportHtml(reportJasper, reportParams, ds, response,
                        strNombre, request);
            } else {
                JasperUtil.exportarPDF(reportJasper, reportParams, ds, response,
                        strNombre);
            }
        } catch (Exception ex) {
//            ex.printStackTrace();
        }

    }

    @RequestMapping(value = "/descargaArchivoAlfresco", method = RequestMethod.GET)
    public void descargaArchivoAlfresco(Long idDocumentoAdjunto, HttpServletResponse response) {
        log.info("procesando descargaArchivoAlfresco--->" + idDocumentoAdjunto);
        Map<String, Object> data = consultaRequisitosService.descargarDatosAlfresco(idDocumentoAdjunto);
        InputStream is = (InputStream)data.get("inputStream");
        String nombreArchivo = (String)data.get("nombreArchivo");
        try {
            if (is == null) {
                response.getWriter().write("ERROR: NO SE ADJUNT&Oacute; DOCUMENTO, VOLVER A INTENTAR..!!");
                return;
            }
            String nombreFichero = nombreArchivo;
            response.setHeader("Content-Disposition", "attachment; filename=\""
                    + nombreFichero + "\"");
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();

        } catch (Exception ex) {
//            ex.printStackTrace();
            log.info("--->" + ex.getMessage());
            log.info("Error writing file to output stream. Filename was '" + nombreArchivo + "'");
            throw new RuntimeException("IOError writing file to output stream");
        }
    }
    
    @RequestMapping(value = "/obtenerValorUIT", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> obtenerValorUIT(HttpServletRequest request, HttpSession session, Model model) {
        log.info("procesando GET para RequestMapping ConsultaRequisitosController/obtenerValorUIT");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String valorUIT = consultaRequisitosService.obtenerValorUIT();
            map.put("valorUIT", valorUIT);
            map.put(ConstantesWeb.VV_RESULTADO, ConstantesWeb.VV_EXITO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            map.put(ConstantesWeb.VV_MENSAJE, e.getMessage());
            map.put(ConstantesWeb.VV_RESULTADO, ConstantesWeb.VV_ERROR);
        }
        return map;
    }
}
