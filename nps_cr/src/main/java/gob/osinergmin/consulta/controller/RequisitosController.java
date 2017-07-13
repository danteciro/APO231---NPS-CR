/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.controller;

import gob.osinergmin.consulta.dto.ActividadDTO;
import gob.osinergmin.consulta.dto.ActividadEtapaDTO;
import gob.osinergmin.consulta.dto.ActividadTramiteDTO;
import gob.osinergmin.consulta.dto.EtapaDTO;
import gob.osinergmin.consulta.dto.ProcedimientoDTO;
import gob.osinergmin.consulta.dto.ProcedimientoTramiteActividadDTO;
import gob.osinergmin.consulta.dto.ProcedimientoTramiteDTO;
import gob.osinergmin.consulta.dto.RequisitoDTO;
import gob.osinergmin.consulta.dto.RequisitoProcedimientoDTO;
import gob.osinergmin.consulta.dto.TramiteDTO;
import gob.osinergmin.consulta.service.ConsultaRequisitosService;
import gob.osinergmin.consulta.util.ConstantesWeb;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author lchancayauri
 */
@Scope("session")
@Controller
@RequestMapping("/consulta/requisitos")
public class RequisitosController {

    private static final Logger log = LoggerFactory.getLogger(RequisitosController.class);
    
    @Value("${error.exception.parametro2}")    
    private String errorParametro2;
    @Autowired
    ServletContext servletContext;
    
    @Inject
    private ConsultaRequisitosService requisitoService;
    
    private List<ActividadDTO> listaGrupoActividad;
    private List<ActividadDTO> listaActividad;
    private List<EtapaDTO> listaEtapa;
    private List<TramiteDTO> listaTramite;
    private List<ActividadEtapaDTO> listaActividadEtapa;
    private List<ActividadTramiteDTO> listaActividadTramite;
    private List<ProcedimientoDTO> listaProcedimiento;
    private List<ProcedimientoTramiteDTO> listaProcedimientoTramite;
    private List<ProcedimientoTramiteActividadDTO> listaProcedimientoTramiteActividad;
    private List<RequisitoDTO> listaRequisito;
    private List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento;

    @RequestMapping(method = RequestMethod.GET)
    public String inicio() {
        return ConstantesWeb.Navegacion.PAGE_GENERAL_INICIO;
    }

    @RequestMapping(value = "/menu")
    public String mantenimiento() {
        return ConstantesWeb.Navegacion.PAGE_GENERAL_MENU_REQUISITOS;

    }

    @RequestMapping(value = "/opcion1")
    public String opcion1(HttpServletRequest request) {
        System.out.println("-- RequisitosController - opcion1 --");
        return ConstantesWeb.Navegacion.PAGE_MENU_REQUISITOS_OPCION_1;

    }

    @RequestMapping(value = "/opcion2")
    public String opcion2(HttpServletRequest request) {
        System.out.println("-- RequisitosController - opcion2 --");
        String parametro = (String) request.getSession().getServletContext().getAttribute("ParametroApp");
        System.out.println("ParametroApp = " + parametro);
        String parametroApp11 = (String) servletContext.getAttribute("ParametroApp11");
        System.out.println("ParametroApp11 = " + parametroApp11);
        return ConstantesWeb.Navegacion.PAGE_MENU_REQUISITOS_OPCION_2;

    }

    @RequestMapping(value = "/opcion3")
    public String opcion3(HttpServletRequest request) throws Exception{
        System.out.println("-- RequisitosController - opcion3 --");
//        try{
//            System.out.println("errorParametro2 = "+errorParametro2);
//            requisitoService.obtenerActitividad();
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        request.getSession().getServletContext().setAttribute("ParametroApp", "1");
//        if(errorParametro2 != null){
//            throw new Exception("Esta es una excepcion");
//        }
//        servletContext.setAttribute("ParametroApp11", "11");
        
        
        
        return ConstantesWeb.Navegacion.PAGE_MENU_REQUISITOS_OPCION_3;

    }

    @RequestMapping(value = "/obtenerGrupoActividades", method = RequestMethod.GET)
    public @ResponseBody
    List<ActividadDTO> obtenerGrupoActividades() {
        log.info("procesando GET para RequestMapping ConsultaRequisitos/obtenerGrupoActividades");
        return listaGrupoActividad;
    }

    @RequestMapping(value = "/obtenerActividades", method = RequestMethod.GET)
    public @ResponseBody
    List<ActividadDTO> obtenerActividades(Long idGrupoActividad) throws Exception{
        log.info("procesando GET para RequestMapping ConsultaRequisitos/obtenerActividades");
        List<ActividadDTO> listActividad = new ArrayList<ActividadDTO>();
        for (ActividadDTO actividadDTO : listaActividad) {
            if (actividadDTO.getIdActividadPadre().equals(idGrupoActividad)) {
                listActividad.add(actividadDTO);
            }
        }
//        if(errorParametro2 != null){
//            throw new Exception("Esta es una excepcion");
//        }
        return listActividad;
    }

    @RequestMapping(value = "/obtenerEtapas", method = RequestMethod.GET)
    public @ResponseBody
    List<EtapaDTO> obtenerEtapas(Long idActividad) {
        log.info("procesando GET para RequestMapping ConsultaRequisitos/obtenerEtapas");
        System.out.println("idActividad = " + idActividad);
        List<EtapaDTO> lstEtapa = new ArrayList<EtapaDTO>();
        for (ActividadEtapaDTO actividadEtapaDTO : listaActividadEtapa) {
            if (actividadEtapaDTO.getIdActividad().equals(idActividad)) {
                for (EtapaDTO etapaDTO : listaEtapa) {
                    if (etapaDTO.getIdEtapa().equals(actividadEtapaDTO.getIdEtapa())) {
                        lstEtapa.add(etapaDTO);
                    }
                }
            }
        }
        return lstEtapa;
    }

    @RequestMapping(value = "/obtenerTramites", method = RequestMethod.GET)
    public @ResponseBody
    List<TramiteDTO> obtenerTramites(Long idActividad, Long idEtapa) {
        log.info("procesando GET para RequestMapping ConsultaRequisitos/obtenerTramites");
        List<TramiteDTO> listTramite = new ArrayList<TramiteDTO>();
        for (ActividadTramiteDTO actividadTramiteDTO : listaActividadTramite) {
            if (actividadTramiteDTO.getIdActividad().equals(idActividad) && actividadTramiteDTO.getIdEtapa().equals(idEtapa)) {
                for (TramiteDTO tramiteDTO : listaTramite) {
                    if (tramiteDTO.getIdTramite().equals(actividadTramiteDTO.getIdTramite())) {
                        listTramite.add(tramiteDTO);
                    }
                }
            }
        }
        return listTramite;
    }

    @RequestMapping(value = "/obtenerRequisitosEspecificos", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> obtenerRequisitosEspecificos(Long idActividad, Long idTramite) {
        log.info("procesando GET para RequestMapping ConsultaRequisitos/obtenerRequisitosEspecificos");
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("-- idActividad = " + idActividad);
        System.out.println("-- idTramite = " + idTramite);
        ////////////////////////////////////////////////////////////////////////
        // Obtencion de Procedimiento en base Actividad - Tramite
        List<Long> lstIdProcedimientoTramite = new ArrayList<Long>();
        for (ProcedimientoTramiteActividadDTO procedimientoTramiteActividadDTO : listaProcedimientoTramiteActividad) {
            if (procedimientoTramiteActividadDTO.getIdActividad().equals(idActividad)) {
                lstIdProcedimientoTramite.add(procedimientoTramiteActividadDTO.getIdProcedimientoTramite());
                System.out.println("IdProcedimientoTramite = " + procedimientoTramiteActividadDTO.getIdProcedimientoTramite());
            }
        }

        List<Long> lstIdProcedimiento = new ArrayList<Long>();
        Map<Long, Long> mapIdProcedimiento = new HashMap<Long, Long>();
        for (Long idProcedimientoTramite : lstIdProcedimientoTramite) {
            for (ProcedimientoTramiteDTO procedimientoTramite : listaProcedimientoTramite) {
                if (procedimientoTramite.getIdProcedimientoTramite().equals(idProcedimientoTramite)
                        && procedimientoTramite.getIdTramite().equals(idTramite)) {
//                    lstIdProcedimiento.add(procedimientoTramite.getIdProcedimiento());
                    Long id = procedimientoTramite.getIdProcedimiento();
                    mapIdProcedimiento.put(id, id);
                    System.out.println("IdProcedimiento = " + procedimientoTramite.getIdProcedimiento());
                }
            }
        }
        lstIdProcedimiento.addAll(mapIdProcedimiento.values());
        ////////////////////////////////////////////////////////////////////////
        List<Long> lstIdRequisitoZonificacion = new ArrayList<Long>();
        List<Long> lstIdRequisitoActividad = new ArrayList<Long>();
        List<Long> lstIdRequisitoTramtie = new ArrayList<Long>();
//        
//        Map<Long,Long> mapZonificacion = new HashMap<Long, Long>();
//        Map<Long,Long> mapActividad = new HashMap<Long, Long>();
//        Map<Long,Long> mapTramite = new HashMap<Long, Long>();
        if (lstIdProcedimiento.size() == 1) {
            ////////////////////////////////////////////////////////////////////
            // 
            Long idProcedimiento = lstIdProcedimiento.get(0);
            map.put("idProcedimiento", idProcedimiento);
            if (idProcedimiento.equals(new Long(1))) {
                lstIdRequisitoZonificacion = new ArrayList<Long>();
                lstIdRequisitoActividad = new ArrayList<Long>();
                lstIdRequisitoTramtie = new ArrayList<Long>();

            } else if (idProcedimiento.equals(new Long(2))) {
                lstIdRequisitoZonificacion = new ArrayList<Long>();
                lstIdRequisitoActividad = new ArrayList<Long>();
                lstIdRequisitoTramtie = new ArrayList<Long>();
                lstIdRequisitoZonificacion.add(new Long(1));
            } else if (idProcedimiento.equals(new Long(3))) {
            } else if (idProcedimiento.equals(new Long(4))) {
                lstIdRequisitoZonificacion = new ArrayList<Long>();
                lstIdRequisitoActividad = new ArrayList<Long>();
                lstIdRequisitoTramtie = new ArrayList<Long>();
                lstIdRequisitoZonificacion.add(new Long(1));
            }
//            System.out.println("lstIdRequisitoZonificacion = " + lstIdRequisitoZonificacion.size());
//            System.out.println("lstIdRequisitoActividad = " + lstIdRequisitoActividad.size());
//            System.out.println("lstIdRequisitoTramite = " + lstIdRequisitoTramtie.size());

            ////////////////////////////////////////////////////////////////////
//            Long idProcedimiento = lstIdProcedimiento.get(0);
//            
//            for (RequisitoProcedimientoDTO requisitoProcedimiento : listaRequisitoProcedimiento) {
//                //Caso Requisitos Especifico - Zonificacion
//                if(requisitoProcedimiento.getIdProcedimiento().equals(idProcedimiento) && 
//                        requisitoProcedimiento.getIdTramite() == null && 
//                        requisitoProcedimiento.getIdActividad() == null &&
//                        requisitoProcedimiento.getIdZonificacion() != null ){
//                    Long idZonificacion = requisitoProcedimiento.getIdZonificacion();
//                    mapZonificacion.put(idZonificacion, idZonificacion);
//                    //lstIdRequisitoZonificacion.add(requisitoProcedimiento.getIdRequisito());
//                    System.out.println("idRequisitoZonificacion = "+requisitoProcedimiento.getIdRequisito());
//                }
//                //Caso Requisitos Especifico - Actividad
//                if(requisitoProcedimiento.getIdProcedimiento().equals(idProcedimiento) && 
//                        requisitoProcedimiento.getIdTramite() != null && 
//                        requisitoProcedimiento.getIdActividad() != null &&
//                        requisitoProcedimiento.getIdZonificacion() == null){
//                    Long idActividadEspecifica = requisitoProcedimiento.getIdActividad();
//                    mapActividad.put(idActividadEspecifica, idActividadEspecifica);
////                    lstIdRequisitoZonificacion.add(requisitoProcedimiento.getIdRequisito());
//                    System.out.println("idActividadEspecifica = "+idActividadEspecifica);
//                }
//                //Caso Requisitos Especifico - Tramite
//                if(requisitoProcedimiento.getIdProcedimiento().equals(idProcedimiento) && 
//                        requisitoProcedimiento.getIdTramite() != null && 
//                        requisitoProcedimiento.getIdActividad() == null && 
//                        requisitoProcedimiento.getIdZonificacion() == null){
////                    lstIdRequisitoZonificacion.add(requisitoProcedimiento.getIdRequisito());
//                    Long idTramiteEspecifico = requisitoProcedimiento.getIdTramite();
//                    mapTramite.put(idTramiteEspecifico, idTramiteEspecifico);
//                    System.out.println("idTramiteEspecifico = "+idTramiteEspecifico);
////                    mapTramite.values()
//                }
//            }

//            lstIdRequisitoZonificacion.addAll(mapZonificacion.values());
//            lstIdRequisitoActividad.addAll(mapActividad.values());
//            lstIdRequisitoTramtie.addAll(mapTramite.values());
//            System.out.println("*******************************************************");

//            System.out.println("lstIdRequisitoZonificacion = " + lstIdRequisitoZonificacion.size());
//            System.out.println("lstIdRequisitoActividad = " + lstIdRequisitoActividad.size());
//            System.out.println("lstIdRequisitoTramite = " + lstIdRequisitoTramtie.size());
            map.put("zonificacion", lstIdRequisitoZonificacion);
            map.put("actividad", lstIdRequisitoActividad);
            map.put("tramite", lstIdRequisitoTramtie);
            map.put(ConstantesWeb.VV_RESULTADO, ConstantesWeb.VV_EXITO);
        } else {
            String mensaje = "";
            if (lstIdProcedimiento.size() > 1) {
                mensaje = "Se esta rompiendo la regla de negocio: Actividad - Tramite solo puede estar en un Procedimiento";
                log.info(mensaje);
                System.out.println(mensaje);
                String strIdProcedimiento = "";
                for (Long idProcedimiento : lstIdProcedimiento) {
                    strIdProcedimiento += idProcedimiento + ", ";
                }
                strIdProcedimiento = strIdProcedimiento.substring(0, strIdProcedimiento.length() - 2);
                log.info("Procedimientos relacionados a la Actividad: " + idActividad + " y Tramite: " + idTramite + " son : " + strIdProcedimiento);
                System.out.println("Procedimientos relacionados a la Actividad: " + idActividad + " y Tramite: " + idTramite + " son : " + strIdProcedimiento);
                
            }else{
                mensaje = "No existe Procedimiento Configurado";
                log.info(mensaje);
                System.out.println(mensaje);
            }
            map.put(ConstantesWeb.VV_MENSAJE, mensaje);
            map.put(ConstantesWeb.VV_RESULTADO, ConstantesWeb.VV_ERROR);
        }
        return map;
    }

    @RequestMapping(value = "/obtenerRequisitos", method = RequestMethod.GET)
    public @ResponseBody
    List<RequisitoDTO> obtenerRequisitos(Long idActividad, Long idTramite) {
        List<RequisitoDTO> lstRequisito = new ArrayList<RequisitoDTO>();
        List<Long> lstIdProcedimientoTramite = new ArrayList<Long>();
        log.info("procesando GET para RequestMapping ConsultaRequisitos/obtenerRequisitos");
        System.out.println("-- idActividad = " + idActividad);
        System.out.println("-- idTramite = " + idTramite);
        ////////////////////////////////////////////////////////////////////////
        // Obtencion de Procedimiento en base Actividad - Tramite
        for (ProcedimientoTramiteActividadDTO procedimientoTramiteActividadDTO : listaProcedimientoTramiteActividad) {
            if (procedimientoTramiteActividadDTO.getIdActividad().equals(idActividad)) {
                lstIdProcedimientoTramite.add(procedimientoTramiteActividadDTO.getIdProcedimientoTramite());
                System.out.println("IdProcedimientoTramite = " + procedimientoTramiteActividadDTO.getIdProcedimientoTramite());
            }
        }

        List<Long> lstIdProcedimiento = new ArrayList<Long>();
        for (Long idProcedimientoTramite : lstIdProcedimientoTramite) {
            for (ProcedimientoTramiteDTO procedimientoTramite : listaProcedimientoTramite) {
                if (procedimientoTramite.getIdProcedimientoTramite().equals(idProcedimientoTramite)
                        && procedimientoTramite.getIdTramite().equals(idTramite)) {
                    lstIdProcedimiento.add(procedimientoTramite.getIdProcedimiento());
                    System.out.println("IdProcedimiento = " + procedimientoTramite.getIdProcedimiento());
                }
            }
        }
        ////////////////////////////////////////////////////////////////////////
        List<Long> lstIdRequisito = new ArrayList<Long>();
        List<Long> lstIdRequisitoZonificacion = new ArrayList<Long>();
        if (lstIdProcedimiento.size() == 1) {
            Long idProcedimiento = lstIdProcedimiento.get(0);

            //Caso Requisitos Generales
            for (RequisitoProcedimientoDTO requisitoProcedimiento : listaRequisitoProcedimiento) {
                if (requisitoProcedimiento.getIdProcedimiento().equals(idProcedimiento)
                        && requisitoProcedimiento.getIdTramite() == null
                        && requisitoProcedimiento.getIdActividad() == null
                        && requisitoProcedimiento.getIdZonificacion()== null) {
                    lstIdRequisito.add(requisitoProcedimiento.getIdRequisito());
                    System.out.println("idRequisitoProcedimiento = " + requisitoProcedimiento.getIdRequisitoProcedimiento());
                    System.out.println("idRequisito = " + requisitoProcedimiento.getIdRequisito());
                    System.out.println("IdZonificacionDetalle = " + requisitoProcedimiento.getIdZonificacion());
                }
            }
            //Caso Requisitos Especifico - Zonificacion
            for (RequisitoProcedimientoDTO requisitoProcedimiento : listaRequisitoProcedimiento) {
                if (requisitoProcedimiento.getIdProcedimiento().equals(idProcedimiento)
                        && requisitoProcedimiento.getIdTramite() == null
                        && requisitoProcedimiento.getIdActividad() == null
                        && requisitoProcedimiento.getIdZonificacion()!= null) {
                    lstIdRequisitoZonificacion.add(requisitoProcedimiento.getIdRequisito());
                    System.out.println("idRequisitoZonificacion = " + requisitoProcedimiento.getIdRequisito());
                }
            }

            System.out.println("*******************************************************");
            System.out.println("lstIdRequisitoZonificacion = " + lstIdRequisitoZonificacion.size());
            System.out.println("lstIdRequisito = " + lstIdRequisito.size());

            for (Long idRequisito : lstIdRequisito) {
                for (RequisitoDTO riquisito : listaRequisito) {
                    if (riquisito.getIdRequisito().equals(idRequisito)) {
                        lstRequisito.add(riquisito);
                    }
                }
            }
            System.out.println("lstRequisito = " + lstRequisito.size());
        } else {
            log.info("Se esta rompiendo la regla de negocio: Actividad - Tramite solo puede estar en un Procedimiento");
            System.out.println("Se esta rompiendo la regla de negocio: Actividad - Tramite solo puede estar en un Procedimiento");
            String strIdProcedimiento = "";
            for (Long idProcedimiento : lstIdProcedimiento) {
                strIdProcedimiento += idProcedimiento + ", ";
            }
            strIdProcedimiento = strIdProcedimiento.substring(0, strIdProcedimiento.length() - 2);
            log.info("Procedimientos relacionados a la Actividad: " + idActividad + " y Tramite: " + idTramite + " son : " + strIdProcedimiento);
            System.out.println("Procedimientos relacionados a la Actividad: " + idActividad + " y Tramite: " + idTramite + " son : " + strIdProcedimiento);
        }


        return lstRequisito;
    }
    
    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public ModelAndView downloadFile(HttpServletRequest request,
            HttpServletResponse response, String tipo) throws Exception {

        try {
            String nombreArchivo;
            String contentType;
            if (tipo.equals("pdf")) {
                nombreArchivo = "proceso.pdf";
                contentType = "application/pdf";
            } else if (tipo.equals("excel")) {
//                nombreArchivo = "listado_requisitos.xlsx";
                nombreArchivo = "listado_requisitos_final.xlsx";
                contentType = "application/vnd.ms-excel";
            } else {
                nombreArchivo = "prueba.txt";
                contentType = "Content-Type:text/plain";
            }
            String path = "/WEB-INF/views/consulta/requisitos/archivos/" + nombreArchivo;
            System.out.println("path = " + path);
            InputStream file = request.getSession().getServletContext().getResourceAsStream(path);
            System.out.println("file = " + file);

            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

            IOUtils.copy(file, response.getOutputStream());
            response.flushBuffer();

        } catch (IOException ex) {
            ex.printStackTrace();
            // Sacar log de error.
//            throw ex;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/abrirPopupMensaje", method = RequestMethod.GET)
    public String abrirPopupMensaje (HttpSession sesion) {
    	log.debug("abrirPopupMensaje");
		return ConstantesWeb.Navegacion.PAGE_MENU_OBLIGACIONES_MENSAJE;
    }
    
    public RequisitosController() {
        System.out.println("-- RequisitosController --");
        definicionGrupoActividad();
        definicionActividad();

        definicionEtapas();
        definicionTramite();
        definicionActivdadEtapa();
        definicionActividadTramite();
        definicionProcedimintos();
        definicionProcedimientoTramite();
        definicionProcedimientoTramiteActividad();
        definicionRequisito();
        definicionRequisitoProcedimiento();
    }

    private void definicionRequisitoProcedimiento() {
        listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        ////////////////////////////////////////////////////////////////////////
        //P1
        RequisitoProcedimientoDTO requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(1));
        requisitoProcedimiento.setIdProcedimiento(new Long(1));
        requisitoProcedimiento.setIdRequisito(new Long(21));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(2));
        requisitoProcedimiento.setIdProcedimiento(new Long(1));
        requisitoProcedimiento.setIdRequisito(new Long(10));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(3));
        requisitoProcedimiento.setIdProcedimiento(new Long(1));
        requisitoProcedimiento.setIdRequisito(new Long(8));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(4));
        requisitoProcedimiento.setIdProcedimiento(new Long(1));
        requisitoProcedimiento.setIdRequisito(new Long(7));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(5));
        requisitoProcedimiento.setIdProcedimiento(new Long(1));
        requisitoProcedimiento.setIdRequisito(new Long(15));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(6));
        requisitoProcedimiento.setIdProcedimiento(new Long(1));
        requisitoProcedimiento.setIdRequisito(new Long(13));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(7));
        requisitoProcedimiento.setIdProcedimiento(new Long(1));
        requisitoProcedimiento.setIdRequisito(new Long(19));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(8));
        requisitoProcedimiento.setIdProcedimiento(new Long(1));
        requisitoProcedimiento.setIdRequisito(new Long(17));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(9));
        requisitoProcedimiento.setIdProcedimiento(new Long(1));
        requisitoProcedimiento.setIdRequisito(new Long(25));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(10));
        requisitoProcedimiento.setIdProcedimiento(new Long(1));
        requisitoProcedimiento.setIdRequisito(new Long(16));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(11));
        requisitoProcedimiento.setIdProcedimiento(new Long(1));
        requisitoProcedimiento.setIdRequisito(new Long(14));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(12));
        requisitoProcedimiento.setIdProcedimiento(new Long(1));
        requisitoProcedimiento.setIdRequisito(new Long(4));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(13));
        requisitoProcedimiento.setIdProcedimiento(new Long(1));
        requisitoProcedimiento.setIdRequisito(new Long(27));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(14));
        requisitoProcedimiento.setIdProcedimiento(new Long(1));
        requisitoProcedimiento.setIdRequisito(new Long(28));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(15));
        requisitoProcedimiento.setIdProcedimiento(new Long(1));
        requisitoProcedimiento.setIdRequisito(new Long(29));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(16));
        requisitoProcedimiento.setIdProcedimiento(new Long(1));
        requisitoProcedimiento.setIdRequisito(new Long(30));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(17));
        requisitoProcedimiento.setIdProcedimiento(new Long(1));
        requisitoProcedimiento.setIdRequisito(new Long(31));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(18));
        requisitoProcedimiento.setIdProcedimiento(new Long(1));
        requisitoProcedimiento.setIdRequisito(new Long(32));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(19));
        requisitoProcedimiento.setIdProcedimiento(new Long(1));
        requisitoProcedimiento.setIdRequisito(new Long(33));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(20));
        requisitoProcedimiento.setIdProcedimiento(new Long(1));
        requisitoProcedimiento.setIdRequisito(new Long(34));
        requisitoProcedimiento.setIdZonificacion(new Long(1));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        ////////////////////////////////////////////////////////////////////////
        //P2
        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(21));
        requisitoProcedimiento.setIdProcedimiento(new Long(2));
        requisitoProcedimiento.setIdRequisito(new Long(21));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(22));
        requisitoProcedimiento.setIdProcedimiento(new Long(2));
        requisitoProcedimiento.setIdRequisito(new Long(10));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(23));
        requisitoProcedimiento.setIdProcedimiento(new Long(2));
        requisitoProcedimiento.setIdRequisito(new Long(9));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(24));
        requisitoProcedimiento.setIdProcedimiento(new Long(2));
        requisitoProcedimiento.setIdRequisito(new Long(7));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(25));
        requisitoProcedimiento.setIdProcedimiento(new Long(2));
        requisitoProcedimiento.setIdRequisito(new Long(12));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(26));
        requisitoProcedimiento.setIdProcedimiento(new Long(2));
        requisitoProcedimiento.setIdRequisito(new Long(18));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(27));
        requisitoProcedimiento.setIdProcedimiento(new Long(2));
        requisitoProcedimiento.setIdRequisito(new Long(11));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(28));
        requisitoProcedimiento.setIdProcedimiento(new Long(2));
        requisitoProcedimiento.setIdRequisito(new Long(3));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(29));
        requisitoProcedimiento.setIdProcedimiento(new Long(2));
        requisitoProcedimiento.setIdRequisito(new Long(24));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(30));
        requisitoProcedimiento.setIdProcedimiento(new Long(2));
        requisitoProcedimiento.setIdRequisito(new Long(35));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(31));
        requisitoProcedimiento.setIdProcedimiento(new Long(2));
        requisitoProcedimiento.setIdRequisito(new Long(36));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(32));
        requisitoProcedimiento.setIdProcedimiento(new Long(2));
        requisitoProcedimiento.setIdRequisito(new Long(37));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(33));
        requisitoProcedimiento.setIdProcedimiento(new Long(2));
        requisitoProcedimiento.setIdRequisito(new Long(38));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(34));
        requisitoProcedimiento.setIdProcedimiento(new Long(2));
        requisitoProcedimiento.setIdRequisito(new Long(39));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(35));
        requisitoProcedimiento.setIdProcedimiento(new Long(2));
        requisitoProcedimiento.setIdRequisito(new Long(40));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(36));
        requisitoProcedimiento.setIdProcedimiento(new Long(2));
        requisitoProcedimiento.setIdRequisito(new Long(41));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(37));
        requisitoProcedimiento.setIdProcedimiento(new Long(2));
        requisitoProcedimiento.setIdRequisito(new Long(42));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(38));
        requisitoProcedimiento.setIdProcedimiento(new Long(2));
        requisitoProcedimiento.setIdRequisito(new Long(43));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(60));
        requisitoProcedimiento.setIdProcedimiento(new Long(2));
        requisitoProcedimiento.setIdRequisito(new Long(44));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        ////////////////////////////////////////////////////////////////////////
        //P3
        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(39));
        requisitoProcedimiento.setIdProcedimiento(new Long(3));
        requisitoProcedimiento.setIdRequisito(new Long(21));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(40));
        requisitoProcedimiento.setIdProcedimiento(new Long(3));
        requisitoProcedimiento.setIdRequisito(new Long(10));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(41));
        requisitoProcedimiento.setIdProcedimiento(new Long(3));
        requisitoProcedimiento.setIdRequisito(new Long(8));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(42));
        requisitoProcedimiento.setIdProcedimiento(new Long(3));
        requisitoProcedimiento.setIdRequisito(new Long(7));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(43));
        requisitoProcedimiento.setIdProcedimiento(new Long(3));
        requisitoProcedimiento.setIdRequisito(new Long(1));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(44));
        requisitoProcedimiento.setIdProcedimiento(new Long(3));
        requisitoProcedimiento.setIdRequisito(new Long(2));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(62));
        requisitoProcedimiento.setIdProcedimiento(new Long(3));
        requisitoProcedimiento.setIdRequisito(new Long(60));
        requisitoProcedimiento.setIdActividad(new Long(15));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        requisitoProcedimiento = new RequisitoProcedimientoDTO();
        requisitoProcedimiento.setIdRequisitoProcedimiento(new Long(63));
        requisitoProcedimiento.setIdProcedimiento(new Long(3));
        requisitoProcedimiento.setIdRequisito(new Long(61));
        requisitoProcedimiento.setIdActividad(new Long(17));
        listaRequisitoProcedimiento.add(requisitoProcedimiento);

        ////////////////////////////////////////////////////////////////////////
        // P4
    }

    private void definicionRequisito() {
        listaRequisito = new ArrayList<RequisitoDTO>();

        RequisitoDTO requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(1));
        requisito.setDescripcion("Copia de las actas de verificación de pruebas y las actas de verificación de conformidad final de acuerdo al Anexo 2.2 A de la RCD Nº 191-2011-OS/CD.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(2));
        requisito.setDescripcion("Copia simple de la póliza de seguros de responsabilidad civil extracontractual vigente. (p)");
        requisito.setComentarioPredeterminado("(p)  Los montos y las coberturas de las pólizas de seguro de responsabilidad civil extracontractual deberán estar en concordancia con el tipo de instalación, establecimiento o medio utilizado.");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(3));
        requisito.setDescripcion("Copia simple de la sección vial vigente, emitida por la municipalidad provincial o la autoridad competente. (m)");
        requisito.setComentarioPredeterminado("(m) Debe corresponder a las vías colindantes que cuenten con algún acceso vehicular  al establecimiento.");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(4));
        requisito.setDescripcion("Copia simple de las autorizaciones otorgadas por el administrador del aeropuerto, Dirección General de Aviación Civil (DGA), Dirección General de Capitanía de Puertos (DICAPI) o Autoridad Portuaria Nacional (APN), según corresponda.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(5));
        requisito.setDescripcion("Copia simple del acta de verificación de pruebas y del acta de verificación de conformidad (n*), con resultados satisfactorios (para el caso de grifos flotantes solo es aplicable cuando los tanques y/o tuberías se encuentren en tierra).");
        requisito.setComentarioPredeterminado("(n*) Las actas de verificación de pruebas y las actas de verificación de la conformidad deberán estar suscritas por el solicitante, por el profesional responsable y un supervisor o representante designado por OSINERGMIN.");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(6));
        requisito.setDescripcion("Copia simple del certificado de pruebas en maestranza de cada tanque (ñ) (requisito sólo aplicable a instalaciones de tanques y/o modificaciones de tanques para almacenamiento de combustibles líquidos según corresponda). Para el caso de grifo flotante, éste documento deberá ser emitido por el astillero correspondiente.");
        requisito.setComentarioPredeterminado("(ñ)  De contar el tanque con dos o más compartimientos, el certificado deberá ser emitido por cada compartimento.");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(7));
        requisito.setDescripcion("Copia simple del certificado de vigencia de poderes del representante Legal o apoderado (d), expedido dentro de los seis (6) meses previos a la presentación de la solicitud ante el OSINERGMIN.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(8));
        requisito.setDescripcion("Copia simple del documento de identidad vigente del representante legal o apoderado, de ser el caso.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(9));
        requisito.setDescripcion("Copia simple del documento de identidad vigente del representante legal o apoderado.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(10));
        requisito.setDescripcion("Copia simple del documento de identidad vigente.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(11));
        requisito.setDescripcion("Copia simple del documento emitido por la municipalidad provincial en que se indique que el predio no cuenta con habilitación urbana (requisito únicamente aplicable aestablecimientos ubicados en Red Vial Nacional fuera de la zona urbana o de expansión Urbana).");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(12));
        requisito.setDescripcion("Copia simple del Estudio Ambiental aprobado que corresponda según la naturaleza del proyecto, incluyendo las observaciones y subsanaciones, en caso las hubiera, así como la resolución que lo aprueba emitida por la autoridad competente. Si no fuese obligatorio contar con un Estudio Ambiental aprobado, bastará con presentar un documento emitido por la correspondiente autoridad competente, donde conste tal situación. (k)");
        requisito.setComentarioPredeterminado("(k) Si al iniciar el trámite, el administrado no cuenta con este documento, podrá presentar el Estudio Ambiental sin aprobar y el documento que acredite haber iniciado el  ite  ara su aprobación o de la consulta ante la autoridad competente sobre la necesidad de realizar el Estudio Ambiental; debiendo subsanar, antes de la emisión del ITF,   la  ión del Estudio Ambiental, la resolución que lo aprueba y las observaciones y subsanaciones en caso las hubiere, o del  documento de respuesta emitida a la consulta presentada ");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(13));
        requisito.setDescripcion("Copia simple del Estudio Ambiental aprobado que corresponda, según la naturaleza del proyecto. Si no fuese obligatorio contar con un Estudio Ambiental aprobado, bastará con presentar un documento emitido por la correspondiente autoridad competente, donde conste tal situación. (l)");
        requisito.setComentarioPredeterminado("(j)  El formulario de solicitud se obtiene de la página web de OSINERGMIN http://www.osinerg.gob.pe/newweb/pages/GFH/RegHidro_Anexo2.htm?86");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(14));
        requisito.setDescripcion("Diagramas de Tuberías e Instrumentación, incluyendo los sistemas de transferencia de combustibles, recuperación de combustibles, recuperación, quemado o procesamiento de gases o vapores, protección contra incendios, automatización, de ser el caso.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(15));
        requisito.setDescripcion("En caso el proyecto incluya instalaciones que requieran contar con derecho de vía o servidumbre, deberá acreditar dicho derecho con la documentación correspondiente.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(16));
        requisito.setDescripcion("Especificaciones técnicas de los equipos principales del proyecto.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(17));
        requisito.setDescripcion("Estudio de Riesgos aprobado (y). ");
        requisito.setComentarioPredeterminado("(y) La aprobación no es aplicable a Plantas Envasadoras.");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(18));
        requisito.setDescripcion("Estudio de riesgos, salvo lo señalado para los supuestos del caso A.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(19));
        requisito.setDescripcion("Estudio de suelos.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(20));
        requisito.setDescripcion("Formulario de declaración jurada de cumplimiento de la normativa técnico-legal aplicable (t).");
        requisito.setComentarioPredeterminado("(t)   El formulario de declaración jurada deberá estar completamente llenado y firmado por el solicitante o representante legal, a fin de ser admitido para trámite. Dicho formulario se obtiene de la página web de OSINERGMIN: http://www.osinerg.gob.pe/newweb/pages/GFH/RegHidro_Anexo3.htm?333");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(21));
        requisito.setDescripcion("Formulario de solicitud. (b)");
        requisito.setComentarioPredeterminado("(b)  El formulario de solicitud deberá estar completamente llenado y firmado en todas sus páginas por el solicitante o representante legal, a fin de ser admitido para trámite. ");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(22));
        requisito.setDescripcion("Fotografías panorámicas nítidas a color con medidas mínimas de 15 x 10 cm, mostrando las instalaciones culminadas del establecimiento y fotografías nítidas en primer plano de las placas de identificación del fabricante de cada uno de los tanques instalados. (ñ1)");
        requisito.setComentarioPredeterminado("(ñ1) De contar el tanque con dos o más compartimientos, las fotografías serán por cada compartimiento. Todas las fotografías deberán contar necesariamente con leyenda informativa.");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(23));
        requisito.setDescripcion("Indice de Riesgos del Sistema de Tanques Enterrados de Combustibles Líquidos.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(24));
        requisito.setDescripcion("Memoria descriptiva que incluya las especificaciones técnicas de las instalaciones de hidrocarburos.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(25));
        requisito.setDescripcion("Memoria descriptiva.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(26));
        requisito.setDescripcion("Plan de contingencias, elaborado y suscrito por un Ingeniero colegiado y habilitado.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(27));
        requisito.setDescripcion("Documentos de Ingeniería siguientes (g1):");
        requisito.setComentarioPredeterminado("(g1) Los planos deben ser presentados en escalas normalizadas adecuadas, con excepción de las indicadas expresamente. Asimismo la presentación de los planos será de manera física y magnética. ");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(28));
        requisito.setDescripcion("- Plano de ubicación.C72");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(29));
        requisito.setDescripcion("- Planos de distribución con arreglo de planta y equipos.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(30));
        requisito.setDescripcion("- Planos de circulación; requisito sólo aplicable cuando el proyecto contempla la instalación de equipos de despacho a vehículos.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(31));
        requisito.setDescripcion("- Planos de obras metalmecánicas, instalación de tanques, tuberías y accesorios.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(32));
        requisito.setDescripcion("- Planos de instalaciones para atraque de naves, líneas submarinas, brazos de carga, muelles y facilidades para la atención de naves y barcazas, de ser el caso.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(33));
        requisito.setDescripcion("- Planos de instalaciones eléctricas e instrumentación, que contenga la clasificación de áreas peligrosas.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(34));
        requisito.setDescripcion("- Planos de obras civiles.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(35));
        requisito.setDescripcion("Documentos de ingenieria correspondiente a la obra siguientes (g2):");
        requisito.setComentarioPredeterminado("(g2) Los planos deben ser presentados en escalas normalizadas adecuadas, con excepción de las indicadas expresamente. Asimismo la presentación de los planos será de manera física y magnética. Por cada plano solicitado se deberá adjuntar 01 archivo magnético, en formato legible en autocad.");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(36));
        requisito.setDescripcion("- Plano de situación (escala 1:5000)");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(37));
        requisito.setDescripcion("- Plano de ubicación (escala 1:500).");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(38));
        requisito.setDescripcion("- Plano de distribución incluyendo circulación y radios de giro (escala 1:100).");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(39));
        requisito.setDescripcion("- Plano de instalaciones mecánicas de tanques, surtidores, dispensadores, tuberías y accesorios,según corresponda.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(40));
        requisito.setDescripcion("- Plano de instalaciones eléctricas e instrumentación que contenga la clasificación de áreas peligrosas.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(41));
        requisito.setDescripcion("- Plano de obras civiles de instalación de tanques, tuberías e islas de despacho.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(42));
        requisito.setDescripcion("- Plano de equipos o sistema de seguridad contra incendio, según corresponda.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(43));
        requisito.setDescripcion("- Plano de instalaciones sanitarias. En caso el proyecto incluya facilidades para el lavado y engrase de vehículos, deberá presentar adicionalmente el detalle de la trampa de aceites y grasas.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(60));
        requisito.setDescripcion("Estudio de Riesgo elaborado con método HAZOP (Hazard and Operability analysis) y suscrito por profesional(es) calificado(s).");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(61));
        requisito.setDescripcion("Copia simple  del Certificado de Servicios Especializados Aeroportuarios habilitado para el suministro de combustibles.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(62));
        requisito.setDescripcion("Copia simple de la póliza de seguros por siniestro derivados de las fallas de válvulas reguladoras o cilindros de su responsabilidad. ");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(63));
        requisito.setDescripcion("Copia simple de los certificados de conformidad de los tanques de almacenamiento de GLP,  ");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(64));
        requisito.setDescripcion("Copia simple de los documentos que demuestren la instalación, según los cálculos realizados, del sistema de protección catódica de tanques y tuberías metálicos monticulados o soterrados, suscritas por el profesional responsable.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(65));
        requisito.setDescripcion("En caso de establecimientos ubicados en zonas urbanas que cuenten con hidrantes o grifos contra incendios, deberán presentar un documento emitido por la empresa de saneamiento de la localidad que acredite el abastecimiento constante de la red pública de agua (requisito sólo aplicable, si se han realizado instalaciones que incluyan el expendio de GLP para uso automotor).");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(66));
        requisito.setDescripcion("r66 Planos conforme a obra, según corresponda. (g) (f1)");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(67));
        requisito.setDescripcion("Copia simple del Estudio Ambiental aprobado que corresponda según la naturaleza del proyecto, incluyendo las observaciones y subsanaciones, en caso las hubiere; así como la resolución que lo aprueba, emitida por la autoridad competente, si corresponde. Si, por la naturaleza del proyecto, no fuese obligatorio contar con un Estudio Ambiental aprobado, bastará con presentar un documento emitido por la correspondiente autoridad competente, donde conste tal situación.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(68));
        requisito.setDescripcion("Copia simple de la constancia de ubicación y conformidad de la embarcación o balsa flotante; y de las líneas submarinas, de ser el caso, emitido por la capitanía de puerto respectiva, otorgada a favor del solicitante de la Inscripción o modificación en el registro de hidrocarburos; en donde se acredite que cuenta con el correspondiente derecho de uso de áreas acuáticas.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(69));
        requisito.setDescripcion("Copia simple del certificado de matrícula vigente de la embarcación o balsa flotante emitido por la Dirección General de Capitanía de Puertos (DICAPI).");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(70));
        requisito.setDescripcion("Para el caso de instalaciones de líneas submarinas deberá presentar copia simple de la Autorización de construcción, otorgada por la autoridad competente de conformidad con la normativa vigente.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(71));
        requisito.setDescripcion("Estudios de riesgos, incluyendo el sistema de recepción de combustible (desde la ribera, litoral, tierra o zona acuática según corresponda).");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(72));
        requisito.setDescripcion("Copia simple de los documentos que demuestren la instalación, según los cálculos realizados, del sistema de protección catódica de tanques y tuberías metálicos soterrados, suscritas por el profesional responsable.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(73));
        requisito.setDescripcion("Memoria descriptiva que incluya las especificaciones técnicas de las instalaciones de hidrocarburos.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(74));
        requisito.setDescripcion("Documentos de Ingeniería correspondiente a la obra, siguientes:");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(75));
        requisito.setDescripcion("- Plano de situación (escala 1:500). (g)");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(76));
        requisito.setDescripcion("- Plano de ubicación indicando las coordenadas UTM del área autorizada (escala 1:500). (g)");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(77));
        requisito.setDescripcion("- Plano de distribución, señalando los sistemas de recepción (desde la ribera, litoral, tierra o zona acuática, según corresponda), almacenamiento, ventilación y despacho (escala 1:100). (g)");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(78));
        requisito.setDescripcion("- Plano de las instalaciones mecánicas correspondiente a los sistemas de recepción de combustible (desde la ribera, litoral, tierra o zona acuática, según corresponda), almacenamiento, despacho, ventilación y recuperación de vapores (éste último, sólo aplicable para combustibles líquidos Clase I). (g)");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(79));
        requisito.setDescripcion("- Plano de instalaciones eléctricas e instrumentación que contenga la clasificación de áreas peligrosas. (g)");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(80));
        requisito.setDescripcion("");
        requisito.setComentarioPredeterminado("- Plano del sistema de seguridad contra incendio. (g)");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(81));
        requisito.setDescripcion("- Plano de la embarcación aprobados por la Dirección General de Capitanía de Puertos (DICAPI).");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(82));
        requisito.setDescripcion("Copia simple del Estudio Ambiental aprobado que corresponda según la naturaleza del proyecto, incluyendo las observaciones y subsanaciones, en caso las hubiere; así como la resolución que lo aprueba, emitida por la autoridad competente, si corresponde. Si, por la naturaleza del proyecto, no fuese obligatorio contar con un Estudio Ambiental aprobado, bastará con presentar un documento emitido por la correspondiente autoridad competente, donde conste tal situación.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(83));
        requisito.setDescripcion("Copia simple de la clasificación de la zona otorgada por la municipalidad provincial. Para el caso de un proyecto de modificación de la inscriipción del registro de hidrocarburos, el requisito sólo aplica a establecimientos que amplían la capacidad de almacenamiento y/o amplían el área del establecimiento.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(84));
        requisito.setDescripcion("Memoria descriptiva que incluya las especificaciones técnicas de las instalaciones de hidrocarburos.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(85));
        requisito.setDescripcion("Documentos de Ingeniería correspondiente a la obra, siguientes:	");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(86));
        requisito.setDescripcion("- Plano de situación (escala 1:5000). (g)");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(87));
        requisito.setDescripcion("- Plano de localización o croquis del establecimiento firmado por el solicitante o su representante legal (g).");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(88));
        requisito.setDescripcion("- Plano de distribución, señalando la ubicación de los cilindros, equipos contra incendio y pozo(s) a tierra a utilizarse durante el proceso de descarga. (g)");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(89));
        requisito.setDescripcion("- Plano de instalaciones eléctricas e instrumentación que contenga la clasificación de áreas peligrosas (de ser el caso). (g)'");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(90));
        requisito.setDescripcion("Estudio que asegure la factibilidad del transporte de combustible desde la  Planta hasta el punto de descarga del establecimiento. Para el presente caso se exige que el transporte sea contínuo y que en todo el trayecto se cumpla con la normativa vigente.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(91));
        requisito.setDescripcion("Instalación en el establecimiento de sistema de video vigilancia que opere permanentemente.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(92));
        requisito.setDescripcion("Instalación en el establecimiento de un sistema de medición automática de tanques (ATG) que opere permanentemente.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

        requisito = new RequisitoDTO();
        requisito.setIdRequisito(new Long(93));
        requisito.setDescripcion("Presentar conjuntamente con la solicitud una declaración jurada a través de la cual se declare el o los operadores que son solidariamente responsables por operar en el mismo establecimiento; distinguiendo los espacios comunes de los propios de cada uno de los operadores.");
        requisito.setComentarioPredeterminado("");
        listaRequisito.add(requisito);

    }

    private void definicionProcedimientoTramiteActividad() {
        listaProcedimientoTramiteActividad = new ArrayList<ProcedimientoTramiteActividadDTO>();

        ////////////////////////////////////////////////////////////////////////
        //PT1
        ProcedimientoTramiteActividadDTO procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(1));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(1));
        procedimientoTramiteActividad.setIdActividad(new Long(17));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(2));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(1));
        procedimientoTramiteActividad.setIdActividad(new Long(16));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(3));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(1));
        procedimientoTramiteActividad.setIdActividad(new Long(13));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(4));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(1));
        procedimientoTramiteActividad.setIdActividad(new Long(15));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(5));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(1));
        procedimientoTramiteActividad.setIdActividad(new Long(14));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(6));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(1));
        procedimientoTramiteActividad.setIdActividad(new Long(18));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);


        ////////////////////////////////////////////////////////////////////////
        //PT2
        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(7));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(2));
        procedimientoTramiteActividad.setIdActividad(new Long(17));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(8));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(2));
        procedimientoTramiteActividad.setIdActividad(new Long(16));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(9));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(2));
        procedimientoTramiteActividad.setIdActividad(new Long(13));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(10));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(2));
        procedimientoTramiteActividad.setIdActividad(new Long(15));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(11));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(2));
        procedimientoTramiteActividad.setIdActividad(new Long(14));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(12));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(2));
        procedimientoTramiteActividad.setIdActividad(new Long(18));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        ////////////////////////////////////////////////////////////////////////
        //PT3
        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(13));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(3));
        procedimientoTramiteActividad.setIdActividad(new Long(1));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(14));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(3));
        procedimientoTramiteActividad.setIdActividad(new Long(4));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(15));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(3));
        procedimientoTramiteActividad.setIdActividad(new Long(9));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(16));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(3));
        procedimientoTramiteActividad.setIdActividad(new Long(3));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(17));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(3));
        procedimientoTramiteActividad.setIdActividad(new Long(2));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(18));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(3));
        procedimientoTramiteActividad.setIdActividad(new Long(7));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        ////////////////////////////////////////////////////////////////////////
        //PT4
        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(19));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(4));
        procedimientoTramiteActividad.setIdActividad(new Long(1));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(20));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(4));
        procedimientoTramiteActividad.setIdActividad(new Long(4));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(21));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(4));
        procedimientoTramiteActividad.setIdActividad(new Long(9));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(22));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(4));
        procedimientoTramiteActividad.setIdActividad(new Long(3));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(23));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(4));
        procedimientoTramiteActividad.setIdActividad(new Long(2));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(24));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(4));
        procedimientoTramiteActividad.setIdActividad(new Long(7));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        ////////////////////////////////////////////////////////////////////////
        //PT5
        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(25));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(5));
        procedimientoTramiteActividad.setIdActividad(new Long(16));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(26));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(5));
        procedimientoTramiteActividad.setIdActividad(new Long(13));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(27));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(5));
        procedimientoTramiteActividad.setIdActividad(new Long(15));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(28));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(5));
        procedimientoTramiteActividad.setIdActividad(new Long(14));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(29));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(5));
        procedimientoTramiteActividad.setIdActividad(new Long(17));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(30));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(5));
        procedimientoTramiteActividad.setIdActividad(new Long(19));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        ////////////////////////////////////////////////////////////////////////
        //PT6
        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(31));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(6));
        procedimientoTramiteActividad.setIdActividad(new Long(16));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(32));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(6));
        procedimientoTramiteActividad.setIdActividad(new Long(13));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(33));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(6));
        procedimientoTramiteActividad.setIdActividad(new Long(15));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(34));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(6));
        procedimientoTramiteActividad.setIdActividad(new Long(14));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(35));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(6));
        procedimientoTramiteActividad.setIdActividad(new Long(17));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(36));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(6));
        procedimientoTramiteActividad.setIdActividad(new Long(19));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        ////////////////////////////////////////////////////////////////////////
        //PT5 - 2
//        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
//        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(37));
//        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(5));
//        procedimientoTramiteActividad.setIdActividad(new Long(10));
//        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);
//
//        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
//        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(38));
//        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(5));
//        procedimientoTramiteActividad.setIdActividad(new Long(11));
//        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

//        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
//        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(39));
//        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(5));
//        procedimientoTramiteActividad.setIdActividad(new Long(1));
//        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);
//
//        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
//        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(40));
//        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(5));
//        procedimientoTramiteActividad.setIdActividad(new Long(4));
//        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);
//
//        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
//        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(41));
//        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(5));
//        procedimientoTramiteActividad.setIdActividad(new Long(9));
//        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);
//
//        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
//        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(42));
//        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(5));
//        procedimientoTramiteActividad.setIdActividad(new Long(5));
//        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

//        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
//        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(43));
//        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(5));
//        procedimientoTramiteActividad.setIdActividad(new Long(2));
//        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

//        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
//        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(44));
//        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(5));
//        procedimientoTramiteActividad.setIdActividad(new Long(7));
//        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        ////////////////////////////////////////////////////////////////////////
        //PT6 - 2
//        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
//        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(45));
//        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(6));
//        procedimientoTramiteActividad.setIdActividad(new Long(10));
//        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);
//
//        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
//        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(45));
//        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(6));
//        procedimientoTramiteActividad.setIdActividad(new Long(11));
//        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);
//
//        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
//        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(45));
//        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(6));
//        procedimientoTramiteActividad.setIdActividad(new Long(1));
//        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);
//
//        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
//        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(45));
//        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(6));
//        procedimientoTramiteActividad.setIdActividad(new Long(4));
//        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);
//
//        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
//        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(45));
//        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(6));
//        procedimientoTramiteActividad.setIdActividad(new Long(9));
//        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);
//
//        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
//        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(45));
//        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(6));
//        procedimientoTramiteActividad.setIdActividad(new Long(5));
//        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);
//
//        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
//        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(45));
//        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(6));
//        procedimientoTramiteActividad.setIdActividad(new Long(2));
//        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);
//
//        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
//        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(45));
//        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(6));
//        procedimientoTramiteActividad.setIdActividad(new Long(7));
//        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        ////////////////////////////////////////////////////////////////////////
        //PTR 7
        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(53));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(7));
        procedimientoTramiteActividad.setIdActividad(new Long(4));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(54));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(7));
        procedimientoTramiteActividad.setIdActividad(new Long(6));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(55));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(7));
        procedimientoTramiteActividad.setIdActividad(new Long(9));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(56));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(7));
        procedimientoTramiteActividad.setIdActividad(new Long(10));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(57));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(7));
        procedimientoTramiteActividad.setIdActividad(new Long(11));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        ////////////////////////////////////////////////////////////////////////
        //PTR 8
        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(58));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(8));
        procedimientoTramiteActividad.setIdActividad(new Long(4));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(59));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(8));
        procedimientoTramiteActividad.setIdActividad(new Long(6));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(60));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(8));
        procedimientoTramiteActividad.setIdActividad(new Long(9));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(61));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(8));
        procedimientoTramiteActividad.setIdActividad(new Long(10));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);

        procedimientoTramiteActividad = new ProcedimientoTramiteActividadDTO();
        procedimientoTramiteActividad.setIdProcedimientoTramiteActividad(new Long(62));
        procedimientoTramiteActividad.setIdProcedimientoTramite(new Long(8));
        procedimientoTramiteActividad.setIdActividad(new Long(11));
        listaProcedimientoTramiteActividad.add(procedimientoTramiteActividad);
    }

    private void definicionProcedimientoTramite() {
        listaProcedimientoTramite = new ArrayList<ProcedimientoTramiteDTO>();

        ProcedimientoTramiteDTO procedimientoTramite = new ProcedimientoTramiteDTO();
        procedimientoTramite.setIdProcedimientoTramite(new Long(1));
        procedimientoTramite.setIdProcedimiento(new Long(1));
        procedimientoTramite.setIdTramite(new Long(1));
        listaProcedimientoTramite.add(procedimientoTramite);

        procedimientoTramite = new ProcedimientoTramiteDTO();
        procedimientoTramite.setIdProcedimientoTramite(new Long(2));
        procedimientoTramite.setIdProcedimiento(new Long(1));
        procedimientoTramite.setIdTramite(new Long(2));
        listaProcedimientoTramite.add(procedimientoTramite);

//        procedimientoTramite = new ProcedimientoTramiteDTO();
//        procedimientoTramite.setIdProcedimientoTramite(new Long(3));
//        procedimientoTramite.setIdProcedimiento(new Long(2));
//        procedimientoTramite.setIdTramite(new Long(1));
//        listaProcedimientoTramite.add(procedimientoTramite);
        
//        procedimientoTramite = new ProcedimientoTramiteDTO();
//        procedimientoTramite.setIdProcedimientoTramite(new Long(4));
//        procedimientoTramite.setIdProcedimiento(new Long(2));
//        procedimientoTramite.setIdTramite(new Long(2));
//        listaProcedimientoTramite.add(procedimientoTramite);


//        procedimientoTramite = new ProcedimientoTramiteDTO();
//        procedimientoTramite.setIdProcedimientoTramite(new Long(5));
//        procedimientoTramite.setIdProcedimiento(new Long(3));
//        procedimientoTramite.setIdTramite(new Long(11));
//        listaProcedimientoTramite.add(procedimientoTramite);
//
//
//        procedimientoTramite = new ProcedimientoTramiteDTO();
//        procedimientoTramite.setIdProcedimientoTramite(new Long(6));
//        procedimientoTramite.setIdProcedimiento(new Long(3));
//        procedimientoTramite.setIdTramite(new Long(12));
//        listaProcedimientoTramite.add(procedimientoTramite);

        procedimientoTramite = new ProcedimientoTramiteDTO();
        procedimientoTramite.setIdProcedimientoTramite(new Long(7));
        procedimientoTramite.setIdProcedimiento(new Long(4));
        procedimientoTramite.setIdTramite(new Long(11));
        listaProcedimientoTramite.add(procedimientoTramite);

        procedimientoTramite = new ProcedimientoTramiteDTO();
        procedimientoTramite.setIdProcedimientoTramite(new Long(8));
        procedimientoTramite.setIdProcedimiento(new Long(4));
        procedimientoTramite.setIdTramite(new Long(12));
        listaProcedimientoTramite.add(procedimientoTramite);
        
        ////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////
        procedimientoTramite = new ProcedimientoTramiteDTO();
        procedimientoTramite.setIdProcedimientoTramite(new Long(4));
        procedimientoTramite.setIdProcedimiento(new Long(4));
        procedimientoTramite.setIdTramite(new Long(11));
        listaProcedimientoTramite.add(procedimientoTramite);
    }

    private void definicionProcedimintos() {
        listaProcedimiento = new ArrayList<ProcedimientoDTO>();

        ProcedimientoDTO procedimiento = new ProcedimientoDTO();
        procedimiento.setIdProcedimiento(new Long(1));
        procedimiento.setItem("H15");
        procedimiento.setDenominacion("INFORME TÉCNICO FAVORABLE PARA INSTALACIÓN O MODIFICACIÓN DE: PLANTAS DE LUBRICANTES, PLANTAS DE ABASTECIMIENTO, PLANTAS ENVASADORAS Y TERMINALES");
        listaProcedimiento.add(procedimiento);

        procedimiento = new ProcedimientoDTO();
        procedimiento.setIdProcedimiento(new Long(2));
        procedimiento.setItem("H17");
        procedimiento.setDenominacion("INFORME TÉCNICO FAVORABLE PARA INSTALACIÓN O MODIFICACIÓN DE: GRIFO, ESTACIÓN DE SERVICIOS Y GASOCENTRO DE GLP");
        listaProcedimiento.add(procedimiento);

        procedimiento = new ProcedimientoDTO();
        procedimiento.setIdProcedimiento(new Long(3));
        procedimiento.setItem("H21");
        procedimiento.setDenominacion("INSCRIPCIÓN O MODIFICACION DEL REGISTRO DE HIDROCARBUROS DE: PLANTAS DE LUBRICANTES, PLANTAS DE ABASTECIMIENTO, PLANTAS ENVASADORAS Y TERMINALES");
        listaProcedimiento.add(procedimiento);

        procedimiento = new ProcedimientoDTO();
        procedimiento.setIdProcedimiento(new Long(4));
        procedimiento.setItem("H23");
        procedimiento.setDenominacion("INSCRIPCIÓN O MODIFICACION DEL REGISTRO DE HIDROCARBUROS DE: GRIFO, ESTACIÓN DE SERVICIOS Y GASOCENTRO DE GLP");
        listaProcedimiento.add(procedimiento);

    }

    private void definicionActividadTramite() {

        listaActividadTramite = new ArrayList<ActividadTramiteDTO>();

        ActividadTramiteDTO actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(1));
        actividadTramite.setIdActividad(new Long(1));
        actividadTramite.setIdEtapa(new Long(1));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(1));
        actividadTramite.setIdActividad(new Long(4));
        actividadTramite.setIdEtapa(new Long(1));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(1));
        actividadTramite.setIdActividad(new Long(9));
        actividadTramite.setIdEtapa(new Long(1));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(1));
        actividadTramite.setIdActividad(new Long(10));
        actividadTramite.setIdEtapa(new Long(1));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(1));
        actividadTramite.setIdActividad(new Long(17));
        actividadTramite.setIdEtapa(new Long(1));
        listaActividadTramite.add(actividadTramite);

        ////////////////////////////////////////////////////////////////////////
        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(2));
        actividadTramite.setIdActividad(new Long(1));
        actividadTramite.setIdEtapa(new Long(1));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(2));
        actividadTramite.setIdActividad(new Long(4));
        actividadTramite.setIdEtapa(new Long(1));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(2));
        actividadTramite.setIdActividad(new Long(9));
        actividadTramite.setIdEtapa(new Long(1));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(2));
        actividadTramite.setIdActividad(new Long(10));
        actividadTramite.setIdEtapa(new Long(1));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(2));
        actividadTramite.setIdActividad(new Long(17));
        actividadTramite.setIdEtapa(new Long(1));
        listaActividadTramite.add(actividadTramite);

        ////////////////////////////////////////////////////////////////////////
        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(8));
        actividadTramite.setIdActividad(new Long(1));
        actividadTramite.setIdEtapa(new Long(2));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(8));
        actividadTramite.setIdActividad(new Long(4));
        actividadTramite.setIdEtapa(new Long(2));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(8));
        actividadTramite.setIdActividad(new Long(9));
        actividadTramite.setIdEtapa(new Long(2));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(8));
        actividadTramite.setIdActividad(new Long(10));
        actividadTramite.setIdEtapa(new Long(2));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(8));
        actividadTramite.setIdActividad(new Long(17));
        actividadTramite.setIdEtapa(new Long(2));
        listaActividadTramite.add(actividadTramite);

        ////////////////////////////////////////////////////////////////////////
        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(9));
        actividadTramite.setIdActividad(new Long(1));
        actividadTramite.setIdEtapa(new Long(2));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(9));
        actividadTramite.setIdActividad(new Long(4));
        actividadTramite.setIdEtapa(new Long(2));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(9));
        actividadTramite.setIdActividad(new Long(9));
        actividadTramite.setIdEtapa(new Long(2));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(9));
        actividadTramite.setIdActividad(new Long(10));
        actividadTramite.setIdEtapa(new Long(2));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(9));
        actividadTramite.setIdActividad(new Long(17));
        actividadTramite.setIdEtapa(new Long(2));
        listaActividadTramite.add(actividadTramite);

        ////////////////////////////////////////////////////////////////////////
        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(10));
        actividadTramite.setIdActividad(new Long(1));
        actividadTramite.setIdEtapa(new Long(2));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(10));
        actividadTramite.setIdActividad(new Long(4));
        actividadTramite.setIdEtapa(new Long(2));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(10));
        actividadTramite.setIdActividad(new Long(9));
        actividadTramite.setIdEtapa(new Long(2));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(10));
        actividadTramite.setIdActividad(new Long(10));
        actividadTramite.setIdEtapa(new Long(2));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(10));
        actividadTramite.setIdActividad(new Long(17));
        actividadTramite.setIdEtapa(new Long(2));
        listaActividadTramite.add(actividadTramite);

        ////////////////////////////////////////////////////////////////////////
        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(11));
        actividadTramite.setIdActividad(new Long(1));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(11));
        actividadTramite.setIdActividad(new Long(4));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(11));
        actividadTramite.setIdActividad(new Long(9));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(11));
        actividadTramite.setIdActividad(new Long(10));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(11));
        actividadTramite.setIdActividad(new Long(11));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(11));
        actividadTramite.setIdActividad(new Long(17));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        ////////////////////////////////////////////////////////////////////////
        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(12));
        actividadTramite.setIdActividad(new Long(1));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(12));
        actividadTramite.setIdActividad(new Long(4));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(12));
        actividadTramite.setIdActividad(new Long(9));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(12));
        actividadTramite.setIdActividad(new Long(10));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(12));
        actividadTramite.setIdActividad(new Long(11));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(12));
        actividadTramite.setIdActividad(new Long(17));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        ////////////////////////////////////////////////////////////////////////
        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(13));
        actividadTramite.setIdActividad(new Long(1));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(13));
        actividadTramite.setIdActividad(new Long(4));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(13));
        actividadTramite.setIdActividad(new Long(9));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(13));
        actividadTramite.setIdActividad(new Long(10));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(13));
        actividadTramite.setIdActividad(new Long(11));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(13));
        actividadTramite.setIdActividad(new Long(17));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        ////////////////////////////////////////////////////////////////////////
        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(14));
        actividadTramite.setIdActividad(new Long(1));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(14));
        actividadTramite.setIdActividad(new Long(4));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(14));
        actividadTramite.setIdActividad(new Long(9));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(14));
        actividadTramite.setIdActividad(new Long(10));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(14));
        actividadTramite.setIdActividad(new Long(11));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

        actividadTramite = new ActividadTramiteDTO();
        actividadTramite.setIdTramite(new Long(14));
        actividadTramite.setIdActividad(new Long(17));
        actividadTramite.setIdEtapa(new Long(3));
        listaActividadTramite.add(actividadTramite);

    }

    private void definicionActivdadEtapa() {
        listaActividadEtapa = new ArrayList<ActividadEtapaDTO>();

        ActividadEtapaDTO actividadEtapa = new ActividadEtapaDTO();
        actividadEtapa.setIdActividad(new Long(1));
        actividadEtapa.setIdEtapa(new Long(1));
        listaActividadEtapa.add(actividadEtapa);

        actividadEtapa = new ActividadEtapaDTO();
        actividadEtapa.setIdActividad(new Long(1));
        actividadEtapa.setIdEtapa(new Long(2));
        listaActividadEtapa.add(actividadEtapa);

        actividadEtapa = new ActividadEtapaDTO();
        actividadEtapa.setIdActividad(new Long(1));
        actividadEtapa.setIdEtapa(new Long(3));
        listaActividadEtapa.add(actividadEtapa);
        ///////////////////////////////////////////
        actividadEtapa = new ActividadEtapaDTO();
        actividadEtapa.setIdActividad(new Long(4));
        actividadEtapa.setIdEtapa(new Long(1));
        listaActividadEtapa.add(actividadEtapa);

        actividadEtapa = new ActividadEtapaDTO();
        actividadEtapa.setIdActividad(new Long(4));
        actividadEtapa.setIdEtapa(new Long(2));
        listaActividadEtapa.add(actividadEtapa);

        actividadEtapa = new ActividadEtapaDTO();
        actividadEtapa.setIdActividad(new Long(4));
        actividadEtapa.setIdEtapa(new Long(3));
        listaActividadEtapa.add(actividadEtapa);
        ///////////////////////////////////////////////
        actividadEtapa = new ActividadEtapaDTO();
        actividadEtapa.setIdActividad(new Long(11));
        actividadEtapa.setIdEtapa(new Long(3));
        listaActividadEtapa.add(actividadEtapa);
        ///////////////////////////////////////////////////
        actividadEtapa = new ActividadEtapaDTO();
        actividadEtapa.setIdActividad(new Long(10));
        actividadEtapa.setIdEtapa(new Long(1));
        listaActividadEtapa.add(actividadEtapa);

        actividadEtapa = new ActividadEtapaDTO();
        actividadEtapa.setIdActividad(new Long(10));
        actividadEtapa.setIdEtapa(new Long(2));
        listaActividadEtapa.add(actividadEtapa);

        actividadEtapa = new ActividadEtapaDTO();
        actividadEtapa.setIdActividad(new Long(10));
        actividadEtapa.setIdEtapa(new Long(3));
        listaActividadEtapa.add(actividadEtapa);
        ///////////////////////////////////////////////////

        actividadEtapa = new ActividadEtapaDTO();
        actividadEtapa.setIdActividad(new Long(9));
        actividadEtapa.setIdEtapa(new Long(1));
        listaActividadEtapa.add(actividadEtapa);

        actividadEtapa = new ActividadEtapaDTO();
        actividadEtapa.setIdActividad(new Long(9));
        actividadEtapa.setIdEtapa(new Long(2));
        listaActividadEtapa.add(actividadEtapa);

        actividadEtapa = new ActividadEtapaDTO();
        actividadEtapa.setIdActividad(new Long(9));
        actividadEtapa.setIdEtapa(new Long(3));
        listaActividadEtapa.add(actividadEtapa);
        ///////////////////////////////////////////////////////

        actividadEtapa = new ActividadEtapaDTO();
        actividadEtapa.setIdActividad(new Long(12));
        actividadEtapa.setIdEtapa(new Long(1));
        listaActividadEtapa.add(actividadEtapa);

        actividadEtapa = new ActividadEtapaDTO();
        actividadEtapa.setIdActividad(new Long(12));
        actividadEtapa.setIdEtapa(new Long(2));
        listaActividadEtapa.add(actividadEtapa);

        actividadEtapa = new ActividadEtapaDTO();
        actividadEtapa.setIdActividad(new Long(12));
        actividadEtapa.setIdEtapa(new Long(3));
        listaActividadEtapa.add(actividadEtapa);
        ///////////////////////////////////////////////////////

        actividadEtapa = new ActividadEtapaDTO();
        actividadEtapa.setIdActividad(new Long(17));
        actividadEtapa.setIdEtapa(new Long(1));
        listaActividadEtapa.add(actividadEtapa);

        actividadEtapa = new ActividadEtapaDTO();
        actividadEtapa.setIdActividad(new Long(17));
        actividadEtapa.setIdEtapa(new Long(2));
        listaActividadEtapa.add(actividadEtapa);

        actividadEtapa = new ActividadEtapaDTO();
        actividadEtapa.setIdActividad(new Long(17));
        actividadEtapa.setIdEtapa(new Long(3));
        listaActividadEtapa.add(actividadEtapa);
        ///////////////////////////////////////////////////////
    }

    private void definicionEtapas() {
        listaEtapa = new ArrayList<EtapaDTO>();
        EtapaDTO etapa = new EtapaDTO();
        etapa.setIdEtapa(new Long(1));
        etapa.setDescripcion("INSTALACION");
        listaEtapa.add(etapa);

        etapa = new EtapaDTO();
        etapa.setIdEtapa(new Long(2));
        etapa.setDescripcion("PRUEBAS");
        listaEtapa.add(etapa);

        etapa = new EtapaDTO();
        etapa.setIdEtapa(new Long(3));
        etapa.setDescripcion("RHO");
        listaEtapa.add(etapa);

        etapa = new EtapaDTO();
        etapa.setIdEtapa(new Long(4));
        etapa.setDescripcion("INSTRUMENTOS DE GESTION DE SEGURIDAD (IGS)");
        listaEtapa.add(etapa);

        etapa = new EtapaDTO();
        etapa.setIdEtapa(new Long(5));
        etapa.setDescripcion("OPINION FAVORABLE");
        listaEtapa.add(etapa);
    }

    private void definicionTramite() {
        listaTramite = new ArrayList<TramiteDTO>();
        TramiteDTO tramite = new TramiteDTO();
        tramite.setIdTramite(new Long(1));
//        tramite.setDescripcion("Instalacion");
        tramite.setDescripcion("Solicitud de ITF  para Instalaci\u00F3n");
        tramite.setIdEtapa(new Long(1));
        listaTramite.add(tramite);

        tramite = new TramiteDTO();
        tramite.setIdTramite(new Long(2));
//        tramite.setDescripcion("Modificacion de Instalacion");
        tramite.setDescripcion("Solicitud de ITF para Modificaci\u00F3n");
        tramite.setIdEtapa(new Long(1));
        listaTramite.add(tramite);

        tramite = new TramiteDTO();
        tramite.setIdTramite(new Long(3));
//        tramite.setDescripcion("Aprobacion del Manual de diseño de ductos");
        tramite.setDescripcion("Solicitud de ITF para Aprobaci\u00F3n del Manual de diseño de ductos");
        tramite.setIdEtapa(new Long(1));
        listaTramite.add(tramite);

        tramite = new TramiteDTO(); /// No estaba anteriormente
        tramite.setIdTramite(new Long(50));
        tramite.setDescripcion("Solicitud de ITF para Modificaci\u00F3n del Manual de diseño de ductos");
        tramite.setIdEtapa(new Long(1));
        listaTramite.add(tramite);
        
        tramite = new TramiteDTO();
        tramite.setIdTramite(new Long(4));
//        tramite.setDescripcion("Autorizacion para la construccion de Obras");
        tramite.setDescripcion("Autorizaci\u00F3n para la construcci\u00F3n de Obras");
        tramite.setIdEtapa(new Long(1));
        listaTramite.add(tramite);

        tramite = new TramiteDTO();
        tramite.setIdTramite(new Long(5));
//        tramite.setDescripcion("Inicio de Operacion del Ducto");
        tramite.setDescripcion("Inicio de Operaci\u00F3n del Ducto");
        tramite.setIdEtapa(new Long(1));
        listaTramite.add(tramite);

        tramite = new TramiteDTO();
        tramite.setIdTramite(new Long(6));
//        tramite.setDescripcion("Reconsideracion");
        tramite.setDescripcion("Recurso de Reconsideraci\u00F3n");
        tramite.setIdEtapa(new Long(1));
        listaTramite.add(tramite);

        tramite = new TramiteDTO();
        tramite.setIdTramite(new Long(7));
//        tramite.setDescripcion("Apelacion");
        tramite.setDescripcion("Recurso de Apelaci\u00F3n");
        tramite.setIdEtapa(new Long(1));
        listaTramite.add(tramite);

        ////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////

//        tramite = new TramiteDTO();
//        tramite.setIdTramite(new Long(3));
//        tramite.setDescripcion("Estudio de Riesgo y Plan de Contingencia");
//        tramite.setIdEtapa(new Long(1));
//        listaTramite.add(tramite);

        tramite = new TramiteDTO();
        tramite.setIdTramite(new Long(8));
        tramite.setDescripcion("Actas de Verificaci\u00F3n de Pruebas");
        tramite.setIdEtapa(new Long(2));
        listaTramite.add(tramite);

        tramite = new TramiteDTO();
        tramite.setIdTramite(new Long(9));
        tramite.setDescripcion("Actas de Verificaci\u00F3n de Conformidad");
        tramite.setIdEtapa(new Long(2));
        listaTramite.add(tramite);

        tramite = new TramiteDTO();
        tramite.setIdTramite(new Long(10));
        tramite.setDescripcion("Acta de Verificaci\u00F3n de Pruebas y Conformidad");
        tramite.setIdEtapa(new Long(2));
        listaTramite.add(tramite);

        ////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////

        tramite = new TramiteDTO();
        tramite.setIdTramite(new Long(11));
//        tramite.setDescripcion("Inscripcion");
        tramite.setDescripcion("Inscripci\u00F3n en el Registro de Hidrocarburos");
        tramite.setIdEtapa(new Long(3));
        listaTramite.add(tramite);

        tramite = new TramiteDTO();
        tramite.setIdTramite(new Long(12));
//        tramite.setDescripcion("Modificacion de Registro");
        tramite.setDescripcion("Modificaci\u00F3n del Registro de Hidrocarburos");
        tramite.setIdEtapa(new Long(3));
        listaTramite.add(tramite);

        tramite = new TramiteDTO();
        tramite.setIdTramite(new Long(13));
//        tramite.setDescripcion("Reconsideracion");
        tramite.setDescripcion("Recurso de Reconsideraci\u00F3n");
        tramite.setIdEtapa(new Long(3));
        listaTramite.add(tramite);

        tramite = new TramiteDTO();
        tramite.setIdTramite(new Long(14));
//        tramite.setDescripcion("Apelacion");
        tramite.setDescripcion("Recurso de Apelaci\u00F3n");
        tramite.setIdEtapa(new Long(3));
        listaTramite.add(tramite);

        ////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////

        tramite = new TramiteDTO();
        tramite.setIdTramite(new Long(15));
//        tramite.setDescripcion("Estudio de Riesgo");
        tramite.setDescripcion("Aprobaci\u00F3n de Estudio de Riesgo");
        tramite.setIdEtapa(new Long(4));
        listaTramite.add(tramite);

        tramite = new TramiteDTO();
        tramite.setIdTramite(new Long(16));
//        tramite.setDescripcion("Plan de Contingencia");
        tramite.setDescripcion("Aprobaci\u00F3n de Plan de Contingencia");
        tramite.setIdEtapa(new Long(4));
        listaTramite.add(tramite);

        tramite = new TramiteDTO();
        tramite.setIdTramite(new Long(17));
//        tramite.setDescripcion("Reconsideracion");
        tramite.setDescripcion("Recurso de Reconsideraci\u00F3n");
        tramite.setIdEtapa(new Long(4));
        listaTramite.add(tramite);

        tramite = new TramiteDTO();
        tramite.setIdTramite(new Long(18));
//        tramite.setDescripcion("Apelacion");
        tramite.setDescripcion("Recurso de Apelaci\u00F3n");
        tramite.setIdEtapa(new Long(4));
        listaTramite.add(tramite);

        ////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////

        tramite = new TramiteDTO();
        tramite.setIdTramite(new Long(19));
//        tramite.setDescripcion("Opinion Favorable");
        tramite.setDescripcion("Si no afecta capacidad de producción.");
        tramite.setIdEtapa(new Long(5));
        listaTramite.add(tramite);
        
        tramite = new TramiteDTO();
        tramite.setIdTramite(new Long(20));
        tramite.setDescripcion("Por cambio de producto almacenado en tanque"); //NEW 
        tramite.setIdEtapa(new Long(5));
        listaTramite.add(tramite);
        
        tramite = new TramiteDTO();
        tramite.setIdTramite(new Long(21));
        tramite.setDescripcion("Por reducción del área de terreno"); // Nuevo
        tramite.setIdEtapa(new Long(5));
        listaTramite.add(tramite);
        
        tramite = new TramiteDTO();
        tramite.setIdTramite(new Long(22));
        tramite.setDescripcion("Por retiro de tanques de almacenamiento");
        tramite.setIdEtapa(new Long(5));
        listaTramite.add(tramite);

    }

    private void definicionGrupoActividad() {

        listaGrupoActividad = new ArrayList<ActividadDTO>();
        ActividadDTO grupoActividad = new ActividadDTO();
        grupoActividad.setIdActividad(new Long(1));
        grupoActividad.setNombre("Establecimiento de Venta al Publico");
        listaGrupoActividad.add(grupoActividad);

        grupoActividad = new ActividadDTO();
        grupoActividad.setIdActividad(new Long(2));
        grupoActividad.setNombre("Plantas Envasadoras");
        listaGrupoActividad.add(grupoActividad);

    }

    private void definicionActividad() {

        listaActividad = new ArrayList<ActividadDTO>();
        ActividadDTO actividad = new ActividadDTO();
        actividad.setIdActividad(new Long(1));
        actividad.setNombre("Estaciones de Servicio/Grifo");
        actividad.setIdActividadPadre(new Long(1));
        listaActividad.add(actividad);

        actividad = new ActividadDTO();
        actividad.setIdActividad(new Long(4));
        actividad.setNombre("Estaciones de Servicio con Gasocentro de GLP");
        actividad.setIdActividadPadre(new Long(1));
        listaActividad.add(actividad);

        actividad = new ActividadDTO();
        actividad.setIdActividad(new Long(11));
        actividad.setNombre("Grifo Rural");
        actividad.setIdActividadPadre(new Long(1));
        listaActividad.add(actividad);

        actividad = new ActividadDTO();
        actividad.setIdActividad(new Long(10));
        actividad.setNombre("Grifo Flotante");
        actividad.setIdActividadPadre(new Long(1));
        listaActividad.add(actividad);

        actividad = new ActividadDTO();
        actividad.setIdActividad(new Long(9));
        actividad.setNombre("Gasocentro de GLP");
        actividad.setIdActividadPadre(new Long(1));
        listaActividad.add(actividad);

//        actividad = new ActividadDTO();
//        actividad.setIdActividad(new Long(6));
//        actividad.setNombre("Local de Venta de GLP en Cilindros con Capacidad menor o igual a 5,000 Kg.");
//        actividad.setIdActividadPadre(new Long(1));
//        listaActividad.add(actividad);

//        actividad = new ActividadDTO();
//        actividad.setIdActividad(new Long(7));
//        actividad.setNombre("Local de Venta de GLP en Cilindros con Capacidad mayor a 5,000 Kg.");
//        actividad.setIdActividadPadre(new Long(1));
//        listaActividad.add(actividad);

//        actividad = new ActividadDTO();
//        actividad.setIdActividad(new Long(8));
//        actividad.setNombre("Estacion de Servicio con GNV");
//        actividad.setIdActividadPadre(new Long(1));
//        listaActividad.add(actividad);
//
//        actividad = new ActividadDTO();
//        actividad.setIdActividad(new Long(9));
//        actividad.setNombre("Estacion de Servicio con GLP y GNV");
//        actividad.setIdActividadPadre(new Long(1));
//        listaActividad.add(actividad);

//        actividad = new ActividadDTO();
//        actividad.setIdActividad(new Long(10));
//        actividad.setNombre("Gasocentro GNV y GLP");
//        actividad.setIdActividadPadre(new Long(1));
//        listaActividad.add(actividad);

//        actividad = new ActividadDTO();
//        actividad.setIdActividad(new Long(12));
//        actividad.setNombre("Grifo");
//        actividad.setIdActividadPadre(new Long(1));
//        listaActividad.add(actividad);

        actividad = new ActividadDTO();
        actividad.setIdActividad(new Long(17));
        actividad.setNombre("Plantas envasadoras de GLP");
        actividad.setIdActividadPadre(new Long(2));
        listaActividad.add(actividad);
    }
}
