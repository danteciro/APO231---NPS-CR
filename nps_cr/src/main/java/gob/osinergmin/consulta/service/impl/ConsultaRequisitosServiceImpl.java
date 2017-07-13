package gob.osinergmin.consulta.service.impl;

import gob.osinergmin.alfresco.rest.util.AlfrescoInvoker;
import gob.osinergmin.consulta.dto.ActividadDTO;
import gob.osinergmin.consulta.dto.ComentarioDTO;
import gob.osinergmin.consulta.dto.DepartamentoDTO;
import gob.osinergmin.consulta.dto.DistritoDTO;
import gob.osinergmin.consulta.dto.DocumentoAdjuntoDTO;
import gob.osinergmin.consulta.dto.EtapaDTO;
import gob.osinergmin.consulta.dto.MaestroColumnaDTO;
import gob.osinergmin.consulta.dto.MotivoTramiteDTO;
import gob.osinergmin.consulta.dto.ParametroDinamicoDTO;
import gob.osinergmin.consulta.dto.PreguntaRequisitoDTO;
import gob.osinergmin.consulta.dto.PreguntaRequisitoValorDTO;
import gob.osinergmin.consulta.dto.ProcedimientoDTO;
import gob.osinergmin.consulta.dto.ProvinciaDTO;
import gob.osinergmin.consulta.dto.RequisitoDTO;
import gob.osinergmin.consulta.dto.RequisitoProcedimientoDTO;
import gob.osinergmin.consulta.dto.TramiteDTO;
import gob.osinergmin.consulta.dto.ValorParametroDTO;
import gob.osinergmin.consulta.dto.ZonificacionDetalleDTO;
import gob.osinergmin.consulta.service.ConsultaRequisitosService;
import gob.osinergmin.consulta.service.dao.ConsultaRequisitoDAO;
import gob.osinergmin.consulta.service.exception.ConsultaRequisitoException;
import gob.osinergmin.consulta.util.Constantes;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import sun.misc.Sort;

@Service("ConsultaRequisitosService")
public class ConsultaRequisitosServiceImpl implements ConsultaRequisitosService {

    private static final Logger log = LoggerFactory.getLogger(ConsultaRequisitosServiceImpl.class);
    
    @Value("${alfresco.host}")
    private String alfrescoHost;
    @Value("${alfresco.download.dir}")
    private String alfrescoDownload;
    @Value("${alfresco.user}")
    private String alfrescoUser;
    @Value("${alfresco.base}")
    private String alfrescoBase;
    @Value("${alfresco.space.dir.requisitos}")
    private String alfrescoRuta;
    
    @Value("${silencioadministrativo.positivo}")
    private String silencioAdministrativoPositivo;
    @Value("${silencioadministrativo.negativo}")
    private String silencioAdministrativoNegativo;
    @Value("${silencioadministrativo.positivo.mensaje}")
    private String silencioAdministrativoPositivoMensaje;
    @Value("${silencioadministrativo.negativo.mensaje}")
    private String silencioAdministrativoNegativoMensaje;
    @Value("${silencioadministrativo.noaplica}")
    private String silencioAdministrativoNoAplica;
    @Value("${indicacion.legible}")
    private String indicacionLegible;
    @Value("${indicacion.plazo}")
    private String indicacionPlazo;
            
    @Inject
    private ConsultaRequisitoDAO requisitoDAO;

    public ConsultaRequisitosServiceImpl() {
        System.out.println("-- ConsultaRequisitosServiceImpl --");
        System.out.println("-- alfrescoHost = "+alfrescoHost);
    }

    @Override
    public List<ActividadDTO> listarActitividades() {
        log.info("-- RequisitoService - listarActitividades --");
        return requisitoDAO.listarActividadesPadre();
    }

    @Override
    public List<ActividadDTO> listarRubros(Long idActividadPadre) {
        log.info("-- RequisitoService - listarRubros --");
        ActividadDTO actividad = new ActividadDTO();
        actividad.setIdActividadPadre(idActividadPadre);
        return requisitoDAO.listarActividades(actividad);
    }

    @Override
    public List<EtapaDTO> listarEtapas(Long idActividad) {
        log.info("-- RequisitoService - listarEtapas --");
        return requisitoDAO.listarEtapas(idActividad);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TramiteDTO> listarTramites(Long idActividad, Long idEtapa) {
        log.info("-- RequisitoService - listarTramites --");
        return requisitoDAO.listarTramites(idActividad, idEtapa);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> listarPreguntasEspecificas(Long idActividad, Long idTramite) {
        log.info("-- RequisitoService - listarPreguntasEspecificas --");
        Map<String, Object> datos = new HashMap<String, Object>();
        List<PreguntaRequisitoDTO> listaPreguntaRequisito = new ArrayList<PreguntaRequisitoDTO>();
        List<ProcedimientoDTO> listaProcedimiento = requisitoDAO.listarProcedimiento(idActividad, idTramite);
        //Obtencion del Procedimiento
        if (listaProcedimiento.size() == 1) {
            MaestroColumnaDTO tipoConsultaPregunta = requisitoDAO.obtenerMaestroColumna(Constantes.MAESTRO_COLUMNA_APLICACION_MYC, Constantes.MAESTRO_COLUMNA_DOMINIO_TIPO_PARAMETRO, Constantes.PARAMETRO_DINAMICO_TIPO_CONSULTA_PREGUNTA);
            MaestroColumnaDTO tipoConsultaVisualizacion = requisitoDAO.obtenerMaestroColumna(Constantes.MAESTRO_COLUMNA_APLICACION_MYC, Constantes.MAESTRO_COLUMNA_DOMINIO_TIPO_PARAMETRO, Constantes.PARAMETRO_DINAMICO_TIPO_CONSULTA_VISUALIZACION);
            ProcedimientoDTO procedimientoDTO = listaProcedimiento.get(0);
            log.info("*************************************************");
            log.info("IdProcedimiento = " + procedimientoDTO.getIdProcedimiento());
            log.info("Item = " + procedimientoDTO.getItem());
            log.info("Denominacion = " + procedimientoDTO.getDenominacion());

            int idPreguntaRequisito = 0;
            PreguntaRequisitoDTO preguntaRequisito;

            Map<Long, PreguntaRequisitoDTO> mapPreguntaParametroDinamico = new HashMap<Long, PreguntaRequisitoDTO>();
            log.info("*************************************************");
            log.info("** Caso 1 Tipo Persona");
            log.info("** Caso 4 Multioperador");
            List<ParametroDinamicoDTO> listaParametrosDinamicos = requisitoDAO.listarPreguntasParametrosDinamicos(procedimientoDTO.getIdProcedimiento(), tipoConsultaPregunta.getIdMaestroColumna());
//            log.info("listaParametrosDinamicos = " + listaParametrosDinamicos.size());
//            List<ParametroDinamicoDTO> listaPreguntasParametrosDinamicosVisualizacion = requisitoDAO.listarPreguntasParametrosDinamicosVisualizacion(procedimientoDTO.getIdProcedimiento(), tipoConsultaPregunta.getIdMaestroColumna(), tipoConsultaVisualizacion.getIdMaestroColumna());
//            log.info("listaPreguntasParametrosDinamCicosVisualizacion = " + listaPreguntasParametrosDinamicosVisualizacion.size());
//            listaParametrosDinamicos.addAll(listaPreguntasParametrosDinamicosVisualizacion);
            log.info("listaParametrosDinamicos Total = " + listaParametrosDinamicos.size());
            for (ParametroDinamicoDTO parametroDinamicoDTO : listaParametrosDinamicos) {
                preguntaRequisito = new PreguntaRequisitoDTO();
                preguntaRequisito.setIdPreguntaRequisito(new Long(++idPreguntaRequisito));
                preguntaRequisito.setTipoPregunta(Constantes.PREGUNTA_REQUISITO_TIPO_PARAMETRO_DINAMICO);
                preguntaRequisito.setIdPregunta(parametroDinamicoDTO.getIdParametroDinamico());
                preguntaRequisito.setPregunta(parametroDinamicoDTO.getPregunta().trim());
                List<PreguntaRequisitoValorDTO> listaPreguntaRequisitoValor = new ArrayList<PreguntaRequisitoValorDTO>();
                List<ValorParametroDTO> listaValorParametro = requisitoDAO.listarValorParametros(parametroDinamicoDTO.getIdParametroDinamico());
                for (ValorParametroDTO valorParametroDTO : listaValorParametro) {
                    PreguntaRequisitoValorDTO preguntaValor = new PreguntaRequisitoValorDTO();
                    preguntaValor.setIdPreguntaRequisitoValor(valorParametroDTO.getIdValorParametro());
                    preguntaValor.setPreguntaValor(valorParametroDTO.getValor());
                    listaPreguntaRequisitoValor.add(preguntaValor);
                }
                preguntaRequisito.setListaPreguntaRequisitoValor(listaPreguntaRequisitoValor);
                mapPreguntaParametroDinamico.put(parametroDinamicoDTO.getIdParametroDinamico(), preguntaRequisito);
            }
            //Caso 6 Tramite MultiOperador
            log.info("*************************************************");
            log.info("**Caso 6 Tramite MultiOperador");
            List<ParametroDinamicoDTO> listaTramiteParametrosDinamicos = requisitoDAO.listarTramiteParametrosDinamicos(procedimientoDTO.getIdProcedimiento(), idTramite, tipoConsultaPregunta.getIdMaestroColumna());
//            log.info("listaTramiteParametrosDinamicos = " + listaTramiteParametrosDinamicos.size());
//            List<ParametroDinamicoDTO> listaTramiteParametrosDinamicosVisualizacion = requisitoDAO.listarTramiteParametrosDinamicosVisualizacion(procedimientoDTO.getIdProcedimiento(), idTramite, tipoConsultaPregunta.getIdMaestroColumna(), tipoConsultaVisualizacion.getIdMaestroColumna());
//            log.info("listaTramiteParametrosDinamicosVisualizacion = " + listaTramiteParametrosDinamicosVisualizacion.size());
//            listaTramiteParametrosDinamicos.addAll(listaTramiteParametrosDinamicosVisualizacion);
            log.info("listaTramiteParametrosDinamicos total = " + listaTramiteParametrosDinamicos.size());
            for (ParametroDinamicoDTO parametroDinamicoDTO : listaTramiteParametrosDinamicos) {
                preguntaRequisito = new PreguntaRequisitoDTO();
                preguntaRequisito.setIdPreguntaRequisito(new Long(++idPreguntaRequisito));
                preguntaRequisito.setTipoPregunta(Constantes.PREGUNTA_REQUISITO_TIPO_TRAMITE_PARAMETRO_DINAMICO);
                preguntaRequisito.setIdPregunta(parametroDinamicoDTO.getIdParametroDinamico());
                preguntaRequisito.setPregunta(parametroDinamicoDTO.getPregunta().trim());
                List<PreguntaRequisitoValorDTO> listaPreguntaRequisitoValor = new ArrayList<PreguntaRequisitoValorDTO>();
                List<ValorParametroDTO> listaValorParametro = requisitoDAO.listarValorParametros(parametroDinamicoDTO.getIdParametroDinamico());
                for (ValorParametroDTO valorParametroDTO : listaValorParametro) {
                    PreguntaRequisitoValorDTO preguntaValor = new PreguntaRequisitoValorDTO();
                    preguntaValor.setIdPreguntaRequisitoValor(valorParametroDTO.getIdValorParametro());
                    preguntaValor.setPreguntaValor(valorParametroDTO.getValor());
                    listaPreguntaRequisitoValor.add(preguntaValor);
                }
                preguntaRequisito.setListaPreguntaRequisitoValor(listaPreguntaRequisitoValor);
                mapPreguntaParametroDinamico.put(parametroDinamicoDTO.getIdParametroDinamico(), preguntaRequisito);
            }
            listaPreguntaRequisito.addAll(mapPreguntaParametroDinamico.values());

            //Caso 7 Tramite Motivo_Tramite
            log.info("*************************************************");
            log.info("**Caso 7 Tramite Motivo_Tramite");
            List<RequisitoProcedimientoDTO> listaTramiteMotivoTramite = requisitoDAO.listarTramiteMotivoTramite(procedimientoDTO.getIdProcedimiento(), idTramite);
            log.info("listaTramiteMotivoTramite = " + listaTramiteMotivoTramite.size());
            List<RequisitoProcedimientoDTO> listaTramiteMotivoTramiteVisualizacion = requisitoDAO.listarTramiteMotivoTramiteVisualizacion(procedimientoDTO.getIdProcedimiento(), idTramite, tipoConsultaVisualizacion.getIdMaestroColumna());
            log.info("listaTramiteMotivoTramiteVisualizacion = " + listaTramiteMotivoTramiteVisualizacion.size());
            listaTramiteMotivoTramite.addAll(listaTramiteMotivoTramiteVisualizacion);
            log.info("listaTramiteMotivoTramite Total = " + listaTramiteMotivoTramite.size());
            if (!listaTramiteMotivoTramite.isEmpty()) {
                MaestroColumnaDTO maestroColumna = requisitoDAO.obtenerMaestroColumna(Constantes.MAESTRO_COLUMNA_APLICACION_MYC, Constantes.MAESTRO_COLUMNA_DOMINIO_PREGUNTA_REQUISITO, Constantes.PREGUNTA_REQUISITO_MOTIVO_TRAMITE);
//                MaestroColumnaDTO maestroColumna = requisitoDAO.obtenerMaestroColumna(Constantes.PREGUNTA_REQUISITO_MOTIVO_TRAMITE);
                preguntaRequisito = new PreguntaRequisitoDTO();
                preguntaRequisito.setIdPreguntaRequisito(new Long(++idPreguntaRequisito));
                preguntaRequisito.setTipoPregunta(Constantes.PREGUNTA_REQUISITO_TIPO_TRAMITE_MOTIVO_TRAMITE);
                preguntaRequisito.setIdPregunta(idTramite);
                preguntaRequisito.setPregunta(maestroColumna.getDescripcion().trim());
//                preguntaRequisito.setPregunta("¿Como fue la suspencion de su registro?");

                List<PreguntaRequisitoValorDTO> listaPreguntaRequisitoValor = new ArrayList<PreguntaRequisitoValorDTO>();
                List<MotivoTramiteDTO> listaMotivoTramite = requisitoDAO.listarMotivoTramite(idTramite);
                for (MotivoTramiteDTO motivoTramiteDTO : listaMotivoTramite) {
                    PreguntaRequisitoValorDTO preguntaValor = new PreguntaRequisitoValorDTO();
                    preguntaValor.setIdPreguntaRequisitoValor(motivoTramiteDTO.getIdMotivoTramite());
                    preguntaValor.setPreguntaValor(motivoTramiteDTO.getDescripcion());
                    listaPreguntaRequisitoValor.add(preguntaValor);
                }
                preguntaRequisito.setListaPreguntaRequisitoValor(listaPreguntaRequisitoValor);
                listaPreguntaRequisito.add(preguntaRequisito);
            }

            Map<Long, PreguntaRequisitoDTO> mapPreguntaZonificacion = new HashMap<Long, PreguntaRequisitoDTO>();
            //Caso 5 Zonificacion
            log.info("*************************************************");
            log.info("**Caso 5 Zonificacion");
            List<RequisitoProcedimientoDTO> listaZonificacion = requisitoDAO.listarZonificacion(procedimientoDTO.getIdProcedimiento());
            log.info("listaZonificacion = " + listaZonificacion.size());
            List<RequisitoProcedimientoDTO> listaZonificacionVisualizacion = requisitoDAO.listarZonificacionVisualizacion(procedimientoDTO.getIdProcedimiento(), tipoConsultaVisualizacion.getIdMaestroColumna());
            log.info("listaZonificacionVisualizacion = " + listaZonificacionVisualizacion.size());
            listaZonificacion.addAll(listaZonificacionVisualizacion);
            log.info("listaZonificacion total = " + listaZonificacion.size());
            if (!listaZonificacion.isEmpty()) {
                MaestroColumnaDTO maestroColumna = requisitoDAO.obtenerMaestroColumna(Constantes.MAESTRO_COLUMNA_APLICACION_MYC, Constantes.MAESTRO_COLUMNA_DOMINIO_PREGUNTA_REQUISITO, Constantes.PREGUNTA_REQUISITO_ZONIFICACION);
                preguntaRequisito = new PreguntaRequisitoDTO();
                preguntaRequisito.setIdPreguntaRequisito(new Long(++idPreguntaRequisito));
                preguntaRequisito.setTipoPregunta(Constantes.PREGUNTA_REQUISITO_TIPO_ZONIFICACION);
                preguntaRequisito.setIdPregunta(Constantes.PREGUNTA_REQUISITO_TIPO_ZONIFICACION);
                preguntaRequisito.setPregunta(maestroColumna.getDescripcion().trim());
                List<PreguntaRequisitoValorDTO> listaPreguntaRequisitoValor = new ArrayList<PreguntaRequisitoValorDTO>();
                preguntaRequisito.setListaPreguntaRequisitoValor(listaPreguntaRequisitoValor);
                mapPreguntaZonificacion.put(Constantes.PREGUNTA_REQUISITO_TIPO_ZONIFICACION, preguntaRequisito);
            }
            //Caso 8 Actividad Zonificacion
            log.info("*************************************************");
            log.info("**Caso 8 Actividad Zonificacion");
            List<RequisitoProcedimientoDTO> listaActividadZonificacion = requisitoDAO.listarActividadZonificacion(procedimientoDTO.getIdProcedimiento(), idActividad, idTramite);
            log.info("listaActividadZonificacion = " + listaActividadZonificacion.size());
            List<RequisitoProcedimientoDTO> listaActividadZonificacionVisualizacion = requisitoDAO.listarActividadZonificacionVisualizacion(procedimientoDTO.getIdProcedimiento(), idActividad, idTramite, tipoConsultaVisualizacion.getIdMaestroColumna());
            log.info("listaActividadZonificacionVisualizacion = " + listaActividadZonificacionVisualizacion.size());
            listaActividadZonificacion.addAll(listaActividadZonificacionVisualizacion);;
            log.info("listaActividadZonificacion total = " + listaActividadZonificacion.size());
            if (!listaActividadZonificacion.isEmpty()) {
                MaestroColumnaDTO maestroColumna = requisitoDAO.obtenerMaestroColumna(Constantes.MAESTRO_COLUMNA_APLICACION_MYC, Constantes.MAESTRO_COLUMNA_DOMINIO_PREGUNTA_REQUISITO, Constantes.PREGUNTA_REQUISITO_ZONIFICACION);
                preguntaRequisito = new PreguntaRequisitoDTO();
                preguntaRequisito.setIdPreguntaRequisito(new Long(++idPreguntaRequisito));
                preguntaRequisito.setTipoPregunta(Constantes.PREGUNTA_REQUISITO_TIPO_ZONIFICACION);
                preguntaRequisito.setIdPregunta(Constantes.PREGUNTA_REQUISITO_TIPO_ZONIFICACION);
                preguntaRequisito.setPregunta(maestroColumna.getDescripcion().trim());
//                preguntaRequisito.setPregunta("¿Sus instalaciones se encuentran ubicado en? :");
                List<PreguntaRequisitoValorDTO> listaPreguntaRequisitoValor = new ArrayList<PreguntaRequisitoValorDTO>();
                preguntaRequisito.setListaPreguntaRequisitoValor(listaPreguntaRequisitoValor);
                mapPreguntaZonificacion.put(Constantes.PREGUNTA_REQUISITO_TIPO_ZONIFICACION, preguntaRequisito);
            }
            listaPreguntaRequisito.addAll(mapPreguntaZonificacion.values());
            log.info("** Total de Preguntas = " + listaPreguntaRequisito.size());
            datos.put("idProcedimiento", procedimientoDTO.getIdProcedimiento());
            datos.put("listaPreguntaRequisito", listaPreguntaRequisito);
        } else if (listaProcedimiento.size() > 1) {
            String mensaje = "Se esta rompiendo la regla de negocio: Actividad - Tramite solo puede estar en un Procedimiento, ";
            log.info(mensaje);
            String strIdProcedimiento = "";
            for (ProcedimientoDTO procedimientoDTO : listaProcedimiento) {
                strIdProcedimiento += procedimientoDTO.getIdProcedimiento() + ", ";
            }
            strIdProcedimiento = strIdProcedimiento.substring(0, strIdProcedimiento.length() - 2);
            mensaje += "Procedimientos relacionados a la Actividad: " + idActividad + " y Tramite: " + idTramite + " son : " + strIdProcedimiento;
            log.info(mensaje);
            throw new ConsultaRequisitoException(mensaje);
        } else if (listaProcedimiento.isEmpty()) {
            String mensaje = "No existe Procedimiento Configurado";
            log.info(mensaje);
            throw new ConsultaRequisitoException(mensaje);
        }
        return datos;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> listarRequisitos(Long idActividad, Long idTramite, Long idProcedimiento, List<PreguntaRequisitoDTO> listaPreguntasEspecificas) {
        log.info("-- RequisitoService - listarPreguntasEspecificas --");
        Map<String, Object> datos = new HashMap<String, Object>();

        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = obtenerRequisitosProcedimientos(idActividad, idTramite, idProcedimiento, listaPreguntasEspecificas);
        log.info("*** RequisitosProcedimientos Encontrados = " + listaRequisitoProcedimiento.size());
       
        listaRequisitoProcedimiento=ordenarListRequisitoProc(listaRequisitoProcedimiento);
        //eliminar duplicados jpiro
        HashSet<RequisitoProcedimientoDTO> hs = new HashSet<RequisitoProcedimientoDTO>(listaRequisitoProcedimiento);
        listaRequisitoProcedimiento.clear();
        listaRequisitoProcedimiento.addAll(hs);
        
        listaRequisitoProcedimiento=ordenarListRequisitoProc(listaRequisitoProcedimiento);
        //ordenando subrequisitos
        for(RequisitoProcedimientoDTO reg : listaRequisitoProcedimiento){
            if(reg.getListaSubRequisitoProcedimiento()!=null && reg.getListaSubRequisitoProcedimiento().size()>0){
                List<RequisitoProcedimientoDTO> listSubReqOrdenada=ordenarListRequisitoProc(reg.getListaSubRequisitoProcedimiento());
                reg.setListaSubRequisitoProcedimiento(listSubReqOrdenada);
            }
        }
        
        //Obtencion de Requisitos
        List<RequisitoDTO> listaRequisito = obtenerRequisitos(listaRequisitoProcedimiento);
        log.info("listaRequisito = " + listaRequisito.size());

        //Obtencion de Datos del Procedimiento
        ProcedimientoDTO procedimientoDTO = obtenerProcedemiento(idProcedimiento);
        //Obtener Comentarios Procedimiento
        List<ComentarioDTO> listaComentariosProcedimiento = obtenerComentariosProcedimiento(idProcedimiento);
        datos.put("procedimiento", procedimientoDTO);
        datos.put("listaRequisito", listaRequisito);
        datos.put("listaComentarioProcedimiento", listaComentariosProcedimiento);
        return datos;
    }

    public List<RequisitoProcedimientoDTO> ordenarListRequisitoProc(List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento){
        //lista contenedor de DTO 
        Map<Long, RequisitoProcedimientoDTO> mapRequisitoProcedimientoDTO = new HashMap<Long, RequisitoProcedimientoDTO>();
        //lista contenedor de id
        List<Long> listaIdRequisitoProcedimiento = new ArrayList<Long>();
        for (RequisitoProcedimientoDTO requisitoProcedimientoDTO : listaRequisitoProcedimiento) {
            mapRequisitoProcedimientoDTO.put(requisitoProcedimientoDTO.getIdRequisitoProcedimiento(), requisitoProcedimientoDTO);
            listaIdRequisitoProcedimiento.add(requisitoProcedimientoDTO.getIdRequisitoProcedimiento());
        }
        //Ordenando por nroOrden
        Object[] listaIdRP=listaIdRequisitoProcedimiento.toArray();
        if(listaIdRP.length>0){
            for(int i=0;i<listaIdRP.length-1;i++){
                for(int j=0;j<listaIdRP.length-1;j++){
                    if(mapRequisitoProcedimientoDTO.get(listaIdRP[j]).getNroOrden()!=null && mapRequisitoProcedimientoDTO.get(listaIdRP[j+1]).getNroOrden()!=null){
                        if(mapRequisitoProcedimientoDTO.get(listaIdRP[j]).getNroOrden()>mapRequisitoProcedimientoDTO.get(listaIdRP[j+1]).getNroOrden()){
                            Object tmp=listaIdRP[j];
                            listaIdRP[j]=listaIdRP[j+1];
                            listaIdRP[j+1]=tmp;
                        }
                    }
                }                
            }
        }
        //llenando lista ya ordenada
        listaRequisitoProcedimiento.clear();
        for(int i=0;i<listaIdRP.length;i++){
            RequisitoProcedimientoDTO requisitoProcedimientoDTO = mapRequisitoProcedimientoDTO.get(listaIdRP[i]);
            listaRequisitoProcedimiento.add(requisitoProcedimientoDTO);
        }
        //ordenar por generales y especificos
        List<RequisitoProcedimientoDTO> listaTmp=new ArrayList<RequisitoProcedimientoDTO>();
        listaTmp.addAll(listaRequisitoProcedimiento);
        listaRequisitoProcedimiento.clear();
        for (RequisitoProcedimientoDTO reg : listaTmp) {
            if(reg.getFlgGeneral().equals("1")){listaRequisitoProcedimiento.add(reg);}
        }
        for (RequisitoProcedimientoDTO reg : listaTmp) {
            if(reg.getFlgGeneral().equals("0")){listaRequisitoProcedimiento.add(reg);}
        }
        return listaRequisitoProcedimiento;
    }
   
    
    private List<RequisitoProcedimientoDTO> obtenerRequisitosProcedimientos(Long idActividad, Long idTramite, Long idProcedimiento, List<PreguntaRequisitoDTO> listaPreguntasEspecificas) {
        log.info("-- obtenerRequisitosProcedimientos --");
        log.info("-- idActividad = "+idActividad);
        log.info("-- idTramite = "+idTramite);
        log.info("-- idProcedimiento = "+idProcedimiento);
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        MaestroColumnaDTO tipoConsultaVisualizacion = requisitoDAO.obtenerMaestroColumna(Constantes.MAESTRO_COLUMNA_APLICACION_MYC, Constantes.MAESTRO_COLUMNA_DOMINIO_TIPO_PARAMETRO, Constantes.PARAMETRO_DINAMICO_TIPO_CONSULTA_VISUALIZACION);
        //Caso 9 - Obtencion Requisitos Generales
        log.info("** Requistos Generales");
//        Map<Long, RequisitoProcedimientoDTO> map = new HashMap<Long, RequisitoProcedimientoDTO>();
        List<RequisitoProcedimientoDTO> listaRequisitosProcedimientoGenerales = requisitoDAO.listarRequisitosProcedimientoGenericos(idProcedimiento);
        listaRequisitoProcedimiento.addAll(listaRequisitosProcedimientoGenerales);
        log.info("** Requisitos encontrados encontrados = " + listaRequisitosProcedimientoGenerales.size());
        List<RequisitoProcedimientoDTO> listaRequisitosProcedimientoGeneralesVisualizacion = requisitoDAO.listarRequisitosProcedimientoGenericosVisualizacion(idProcedimiento, tipoConsultaVisualizacion.getIdMaestroColumna());
        log.info("** Requisitos encontrados visualizacion encontrados = " + listaRequisitosProcedimientoGeneralesVisualizacion.size());
        listaRequisitoProcedimiento.addAll(listaRequisitosProcedimientoGeneralesVisualizacion);

        //Caso 2 Tramite -- obtener requisitos
        log.info("** Requistos Tramite");
        List<RequisitoProcedimientoDTO> listaRequisitosProcedimientoTramite = requisitoDAO.listarRequisitosProcedimientoTramite(idProcedimiento, idTramite);
        log.info("** Requisitos encontrados = " + listaRequisitosProcedimientoTramite.size());
        listaRequisitoProcedimiento.addAll(listaRequisitosProcedimientoTramite);
        List<RequisitoProcedimientoDTO> listaRequisitosProcedimientoTramiteVisualizacion = requisitoDAO.listarRequisitosProcedimientoTramiteVisualizacion(idProcedimiento, idTramite, tipoConsultaVisualizacion.getIdMaestroColumna());
        log.info("** Requisitos encontrados visualizacion encontrados = " + listaRequisitosProcedimientoTramiteVisualizacion.size());
        listaRequisitoProcedimiento.addAll(listaRequisitosProcedimientoTramiteVisualizacion);

        //Caso 3 Tramite - Actividad -- obtener requisitos
        log.info("** Requistos Actividad");
        List<RequisitoProcedimientoDTO> listaRequisitosProcedimientoTramiteActividad = requisitoDAO.listarRequisitosProcedimientoTramiteActividad(idProcedimiento, idTramite, idActividad);
        log.info("** Requisitos encontrados = " + listaRequisitosProcedimientoTramiteActividad.size());
        listaRequisitoProcedimiento.addAll(listaRequisitosProcedimientoTramiteActividad);
        List<RequisitoProcedimientoDTO> listaRequisitosProcedimientoTramiteActividadVisualizacion = requisitoDAO.listarRequisitosProcedimientoTramiteActividadVisualizacion(idProcedimiento, idTramite, idActividad, tipoConsultaVisualizacion.getIdMaestroColumna());
        log.info("** Requisitos encontrados visualizacion encontrados = " + listaRequisitosProcedimientoTramiteActividadVisualizacion.size());
        listaRequisitoProcedimiento.addAll(listaRequisitosProcedimientoTramiteActividadVisualizacion);
        log.info("****************************************************");

        /////////////////////////////////////////////////////////////////////////
        log.info("*********** Preguntas Especificas ***************");
        for (PreguntaRequisitoDTO preguntaRequisitoDTO : listaPreguntasEspecificas) {
            log.info("************************************************");
            log.info("************************************************");
            log.info("IdPregunta = " + preguntaRequisitoDTO.getIdPregunta());
            log.info("TipoPregunta = " + preguntaRequisitoDTO.getTipoPregunta());
            log.info("RespuestaPregunta = " + preguntaRequisitoDTO.getPreguntaRespuesta());

            //Caso 1 pregunta Tipo Persona - Requisitos
            //Caso 4 pregunta Parametros Dinamicos - Requisitos
            if (preguntaRequisitoDTO.getTipoPregunta().equals(Constantes.PREGUNTA_REQUISITO_TIPO_PARAMETRO_DINAMICO)) {
                log.info("** Requistos Parametros Dinamicos");
                List<RequisitoProcedimientoDTO> listaRequisitosProcedimientoParametrosDinamicos = requisitoDAO.listarRequisitosProcedimientoParametroDinamico(idProcedimiento, preguntaRequisitoDTO.getIdPregunta(), new Long(preguntaRequisitoDTO.getPreguntaRespuesta()));
                log.info("** Requisitos encontrados = " + listaRequisitosProcedimientoParametrosDinamicos.size());
                listaRequisitoProcedimiento.addAll(listaRequisitosProcedimientoParametrosDinamicos);
//                List<RequisitoProcedimientoDTO> listaRequisitosProcedimientoParametrosDinamicosVisualizacion = requisitoDAO.listarRequisitosProcedimientoParametroDinamicoVisualizacion(idProcedimiento, preguntaRequisitoDTO.getIdPregunta(), new Long(preguntaRequisitoDTO.getPreguntaRespuesta()), tipoConsultaVisualizacion.getIdMaestroColumna());
//                log.info("** Requisitos encontrados visualizacion encontrados = " + listaRequisitosProcedimientoParametrosDinamicosVisualizacion.size());
//                listaRequisitoProcedimiento.addAll(listaRequisitosProcedimientoParametrosDinamicosVisualizacion);
            }

            //Caso 6 pregunta Tramite - Parametros Dinamicos 
            if (preguntaRequisitoDTO.getTipoPregunta().equals(Constantes.PREGUNTA_REQUISITO_TIPO_TRAMITE_PARAMETRO_DINAMICO)) {
//                List<RequisitoDTO> listaRequisitosTramiteParametroDinamico = requisitoDAO.listarRequisitosParametrosDinamicosTramite(idProcedimiento, idTramite, preguntaRequisitoDTO.getIdPregunta(), new Long(preguntaRequisitoDTO.getPreguntaRespuesta()));
                log.info("** Requistos Tramite - Parametros Dinamicos");
                List<RequisitoProcedimientoDTO> listaRequisitosProcedimientiTramiteParametroDinamico = requisitoDAO.listarRequisitosProcedimientoTramiteParametrosDinamicos(idProcedimiento, idTramite, preguntaRequisitoDTO.getIdPregunta(), new Long(preguntaRequisitoDTO.getPreguntaRespuesta()));
                log.info("** Requisitos encontrados = " + listaRequisitosProcedimientiTramiteParametroDinamico.size());
                listaRequisitoProcedimiento.addAll(listaRequisitosProcedimientiTramiteParametroDinamico);
//                List<RequisitoProcedimientoDTO> listaRequisitosProcedimientiTramiteParametroDinamicoVisualizacion = requisitoDAO.listarRequisitosProcedimientoTramiteParametrosDinamicosVisualizacion(idProcedimiento, idTramite, preguntaRequisitoDTO.getIdPregunta(), new Long(preguntaRequisitoDTO.getPreguntaRespuesta()), tipoConsultaVisualizacion.getIdMaestroColumna());
//                log.info("** Requisitos encontrados visualizacion encontrados = " + listaRequisitosProcedimientiTramiteParametroDinamicoVisualizacion.size());
//                listaRequisitoProcedimiento.addAll(listaRequisitosProcedimientiTramiteParametroDinamicoVisualizacion);
            }
            //Caso 7 Tramite - Motivo Tramite 
            if (preguntaRequisitoDTO.getTipoPregunta().equals(Constantes.PREGUNTA_REQUISITO_TIPO_TRAMITE_MOTIVO_TRAMITE)) {
                log.info("** Requistos Tramite - Motivo Tramite");
                List<RequisitoProcedimientoDTO> listaRequisitosProcedimientoTramiteMotivoTramite = requisitoDAO.listarRequisitoProcedimientoTramiteMotivoTramite(idProcedimiento, idTramite, new Long(preguntaRequisitoDTO.getPreguntaRespuesta()));
                log.info("** Requisitos encontrados = " + listaRequisitosProcedimientoTramiteMotivoTramite.size());
                listaRequisitoProcedimiento.addAll(listaRequisitosProcedimientoTramiteMotivoTramite);
                List<RequisitoProcedimientoDTO> listaRequisitosProcedimientoTramiteMotivoTramiteVisualizacion = requisitoDAO.listarRequisitoProcedimientoTramiteMotivoTramiteVisualizacion(idProcedimiento, idTramite, new Long(preguntaRequisitoDTO.getPreguntaRespuesta()), tipoConsultaVisualizacion.getIdMaestroColumna());
                log.info("** Requisitos encontrados visualizacion encontrados = " + listaRequisitosProcedimientoTramiteMotivoTramiteVisualizacion.size());
                listaRequisitoProcedimiento.addAll(listaRequisitosProcedimientoTramiteMotivoTramiteVisualizacion);
            }
//            
            if (preguntaRequisitoDTO.getTipoPregunta().equals(Constantes.PREGUNTA_REQUISITO_TIPO_ZONIFICACION)) {
                log.info("** Requistos - Zonificacion");
                String respuesta = preguntaRequisitoDTO.getPreguntaRespuesta();
                log.info("** Ubigeo = " + respuesta);
                String[] ubigeo = respuesta.split("_");
                List<ZonificacionDetalleDTO> listaZonificacionDetalle = requisitoDAO.listarZonificacionDetalle(ubigeo[0], ubigeo[1], ubigeo[2]);
                log.info("listaZonificacionDetalle = " + listaZonificacionDetalle.size());
                if (!listaZonificacionDetalle.isEmpty()) {
                    //Caso 5 Zonificacion
                    log.info("***** Requistos - Zonificacion");
                    ZonificacionDetalleDTO zonificacionDetalle = listaZonificacionDetalle.get(0);

                    List<RequisitoProcedimientoDTO> listaRequisitosProcedimientoZonificacion = requisitoDAO.listarRequisitoProcedimientoZonificacion(idProcedimiento, zonificacionDetalle.getIdZonificacion());
                    log.info("** Requisitos encontrados = " + listaRequisitosProcedimientoZonificacion.size());
                    listaRequisitoProcedimiento.addAll(listaRequisitosProcedimientoZonificacion);
                    List<RequisitoProcedimientoDTO> listaRequisitosProcedimientoZonificacionVisualizacion = requisitoDAO.listarRequisitoProcedimientoZonificacionVisualizacion(idProcedimiento, zonificacionDetalle.getIdZonificacionDetalle(), tipoConsultaVisualizacion.getIdMaestroColumna());
                    log.info("** Requisitos encontrados visualizacion encontrados = " + listaRequisitosProcedimientoZonificacionVisualizacion.size());
                    listaRequisitoProcedimiento.addAll(listaRequisitosProcedimientoZonificacionVisualizacion);

                    //Caso 8 Actividad - Zonificacion 
                    log.info("***** Requistos Tramite - Actividad - Zonificacion");
                    List<RequisitoProcedimientoDTO> listaRequisitosProcedimientoActividadZonificacion = requisitoDAO.listarRequisitosProcedimientoActividadZonificacion(idProcedimiento, idActividad, idTramite, zonificacionDetalle.getIdZonificacionDetalle());
                    log.info("** Requisitos encontrados = " + listaRequisitosProcedimientoActividadZonificacion.size());
                    listaRequisitoProcedimiento.addAll(listaRequisitosProcedimientoActividadZonificacion);
                    List<RequisitoProcedimientoDTO> listaRequisitosProcedimientoActividadZonificacionVisualizacion = requisitoDAO.listarRequisitosProcedimientoActividadZonificacionVisualizacion(idProcedimiento, idActividad, idTramite, zonificacionDetalle.getIdZonificacionDetalle(), tipoConsultaVisualizacion.getIdMaestroColumna());
                    log.info("** Requisitos encontrados visualizacion encontrados = " + listaRequisitosProcedimientoActividadZonificacionVisualizacion.size());
                    listaRequisitoProcedimiento.addAll(listaRequisitosProcedimientoActividadZonificacionVisualizacion);
                }
            }
        }
//        List<RequisitoProcedimientoDTO> listaRequisitoProcedimientoVerificacion = new ArrayList<RequisitoProcedimientoDTO>();
//        Map<Long,RequisitoProcedimientoDTO> mapRequisitos = new HashMap<Long,RequisitoProcedimientoDTO>();
//        for (RequisitoProcedimientoDTO requisitoProcedimientoDTO : listaRequisitoProcedimiento) {
//            mapRequisitos.put(requisitoProcedimientoDTO.getIdRequisitoProcedimiento(), requisitoProcedimientoDTO);
//        }
//        listaRequisitoProcedimientoVerificacion.addAll(mapRequisitos.values());
//        System.out.println("listaRequisitoProcedimiento = "+listaRequisitoProcedimiento.size());
//        System.out.println("listaRequisitoProcedimientoVerificacion = "+listaRequisitoProcedimientoVerificacion.size());
//        listaRequisitoProcedimiento.clear();
//        listaRequisitoProcedimiento.addAll(listaRequisitoProcedimientoVerificacion);
        return listaRequisitoProcedimiento;
    }

    private List<RequisitoProcedimientoDTO> orderRequisitosProcedimientoVisualizacion(Long idProcedimiento, List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento) {
        log.info("-- orderRequisitosProcedimientoVisualizacion --");
        log.info("****************************************************");
        log.info("** Parametros de Visualizacion");
        MaestroColumnaDTO tipoConsultaVisualizacion = requisitoDAO.obtenerMaestroColumna(Constantes.MAESTRO_COLUMNA_APLICACION_MYC, Constantes.MAESTRO_COLUMNA_DOMINIO_TIPO_PARAMETRO, Constantes.PARAMETRO_DINAMICO_TIPO_CONSULTA_VISUALIZACION);
        List<ValorParametroDTO> listaValorParametro = requisitoDAO.listarValorParametrosVisualizacionEtapa(idProcedimiento, tipoConsultaVisualizacion.getIdMaestroColumna());
        log.info("** listaValorParametro = " + listaValorParametro.size());
        if (!listaValorParametro.isEmpty()) { // Si hay Visualizacion
            Map<Long, List<RequisitoProcedimientoDTO>> mapListaRequisitoProcedimiento = new HashMap<Long, List<RequisitoProcedimientoDTO>>();
            mapListaRequisitoProcedimiento.put(new Long(0), new ArrayList<RequisitoProcedimientoDTO>());
            for (ValorParametroDTO valorParametroDTO : listaValorParametro) {
                mapListaRequisitoProcedimiento.put(valorParametroDTO.getIdValorParametro(), new ArrayList<RequisitoProcedimientoDTO>());
            }
            for (RequisitoProcedimientoDTO requisitoProcedimientoDTO : listaRequisitoProcedimiento) {
                List<ValorParametroDTO> listaValorParametroRequerimientoProcedimiento = requisitoDAO.listarValorParametrosRequisitoParametro(idProcedimiento, requisitoProcedimientoDTO.getIdRequisitoProcedimiento(), tipoConsultaVisualizacion.getIdMaestroColumna());
                if (listaValorParametroRequerimientoProcedimiento.isEmpty()) {
                    mapListaRequisitoProcedimiento.get(new Long(0)).add(requisitoProcedimientoDTO);
                } else {
                    ValorParametroDTO valorParametro = listaValorParametroRequerimientoProcedimiento.get(0);
                    mapListaRequisitoProcedimiento.get(valorParametro.getIdValorParametro()).add(requisitoProcedimientoDTO);
                }
            }
            log.info("** Juntar RequisitoProcedimiento segun Parametros de Visualizacion");
            List<RequisitoProcedimientoDTO> listaVisualizacionOrdenada = new ArrayList<RequisitoProcedimientoDTO>();
            log.info("-- Sin visualizacion");
            List<RequisitoProcedimientoDTO> listaSinVisualizacion = mapListaRequisitoProcedimiento.get(new Long(0));
            listaVisualizacionOrdenada.addAll(listaSinVisualizacion);
            log.info("listaSinVisualizacion = " + listaSinVisualizacion.size());
            for (ValorParametroDTO valorParametroDTO : listaValorParametro) {
                log.info("-- visualizacion = " + valorParametroDTO.getIdValorParametro());
                RequisitoProcedimientoDTO cabeceraVisualizacion = new RequisitoProcedimientoDTO();
                cabeceraVisualizacion.setIdRequisitoProcedimiento(new Long(-1));
                cabeceraVisualizacion.setComentario(valorParametroDTO.getDescripcion());
                listaVisualizacionOrdenada.add(cabeceraVisualizacion);
                List<RequisitoProcedimientoDTO> listaVisualizacion = mapListaRequisitoProcedimiento.get(valorParametroDTO.getIdValorParametro());
                log.info("listaVisualizacion = " + listaVisualizacion.size());
                listaVisualizacionOrdenada.addAll(listaVisualizacion);
            }
            listaRequisitoProcedimiento.clear();
            listaRequisitoProcedimiento.addAll(listaVisualizacionOrdenada);
        }
        return listaRequisitoProcedimiento;
    }

    private List<RequisitoDTO> obtenerRequisitos(List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento) {
        log.info("-- obtenerRequisitos --");
        log.info("****************************************************");
        log.info("****************************************************");
        log.info("** Obtencion de Subrequisitos");
        List<RequisitoDTO> listaRequisito = new ArrayList<RequisitoDTO>();
        //Obtencion de SubRequisitos
        //jpiro
        Long enumeradorRequisitos=(long) 0;
        for (RequisitoProcedimientoDTO requisitoProcedimientoDTO : listaRequisitoProcedimiento) {
            RequisitoDTO requisitoDTO = new RequisitoDTO();
            log.info("IdRequisitoProcedimiento = " + requisitoProcedimientoDTO.getIdRequisitoProcedimiento());
            if (!requisitoProcedimientoDTO.getIdRequisitoProcedimiento().equals(new Long(-1))) {
                enumeradorRequisitos++;
                requisitoDTO = requisitoDAO.obtenerRequisito(requisitoProcedimientoDTO.getIdRequisitoProcedimiento());
                log.info("id Requisito = " + requisitoDTO.getIdRequisito());
                requisitoDTO.setNroParaConsulta(enumeradorRequisitos.toString());
                listaRequisito.add(requisitoDTO);
                
                List<RequisitoProcedimientoDTO> listaSubRequisitosProcedimiento = requisitoProcedimientoDTO.getListaSubRequisitoProcedimiento();
                log.info("listaSubRequisitosProcedimiento = " + listaSubRequisitosProcedimiento.size());
                if (!listaSubRequisitosProcedimiento.isEmpty()) {
                    Long enumeradorSubrequisitos=(long) 0;
                    for (RequisitoProcedimientoDTO subRequisitoProcedimientoDTO : listaSubRequisitosProcedimiento) {
                        enumeradorSubrequisitos++;
                        log.info("IdSubRequisitoProcedimiento = " + subRequisitoProcedimientoDTO.getIdRequisitoProcedimiento());
                        requisitoDTO = requisitoDAO.obtenerRequisito(subRequisitoProcedimientoDTO.getIdRequisitoProcedimiento());
                        String descripcionRequisito = requisitoDTO.getDescripcion();
                        requisitoDTO.setDescripcion(enumeradorRequisitos+"."+enumeradorSubrequisitos+" - "+descripcionRequisito);
                        requisitoDTO.setNroParaConsulta(enumeradorRequisitos+"."+enumeradorSubrequisitos);
                        log.info("sub requisito = "+requisitoDTO.getDescripcion());
                        log.info("idSubRequisito = " + requisitoDTO.getIdRequisito());
                        listaRequisito.add(requisitoDTO);
                    }
                }
            } else {
                requisitoDTO.setDescripcion(requisitoProcedimientoDTO.getComentario());
                listaRequisito.add(requisitoDTO);
            }

        }
        return listaRequisito;
    }

    private ProcedimientoDTO obtenerProcedemiento(Long idProcedimiento) {
        log.info("-- obtenerProcedemiento --");
        ProcedimientoDTO procedimientoDTO = requisitoDAO.obtenerProcedimiento(idProcedimiento);
        if (procedimientoDTO != null) {
            MaestroColumnaDTO calificacion = requisitoDAO.obtenerMaestroColumna(Constantes.MAESTRO_COLUMNA_APLICACION_MYC, Constantes.MAESTRO_COLUMNA_DOMINIO_CALIFICACION, Constantes.PROCEDIMIENTO_CALIFICACION_EVALUACION_PREVIA);
            MaestroColumnaDTO evaluacionPrevia = requisitoDAO.obtenerMaestroColumna(Constantes.MAESTRO_COLUMNA_APLICACION_MYC, Constantes.MAESTRO_COLUMNA_DOMINIO_EVALUACION_PREVIA, Constantes.PROCEDIMIENTO_EVALUACION_PREVIA_POSITIVO);
            if (procedimientoDTO.getIdCalificacion().equals(calificacion.getIdMaestroColumna())) {
                if (procedimientoDTO.getIdSilencioAdministrativo().equals(evaluacionPrevia.getIdMaestroColumna())) {
                    procedimientoDTO.setSilencioAdministrativo(silencioAdministrativoPositivo);
                    procedimientoDTO.setSilencioAdministrativoMensaje(silencioAdministrativoPositivoMensaje);
                } else {
                    procedimientoDTO.setSilencioAdministrativo(silencioAdministrativoNegativo);
                    procedimientoDTO.setSilencioAdministrativoMensaje(silencioAdministrativoNegativoMensaje);
                }
            } else {
                procedimientoDTO.setSilencioAdministrativo(silencioAdministrativoNoAplica);
            }
            Double costoTramite;
            if (procedimientoDTO.getDerechoTramitacion() != null) {
                log.info("procedimientoDTO.getIdValorUit() = " + procedimientoDTO.getIdValorUit());
                MaestroColumnaDTO maestroColumna = requisitoDAO.obtenerMaestroColumna(procedimientoDTO.getIdValorUit());
//                log.info("maestroColumna = " + maestroColumna);
                int valorUIT = Integer.parseInt(maestroColumna.getDescripcion());
                log.info("procedimientoDTO.getDerechoTramitacion() = " + procedimientoDTO.getDerechoTramitacion());
                costoTramite = new Double(procedimientoDTO.getDerechoTramitacion().floatValue() / 100 * valorUIT);
            } else {
                costoTramite = new Double(0);
            }
            procedimientoDTO.setCostoTramite(costoTramite);
            procedimientoDTO.setIndicacionLegible(indicacionLegible);
            procedimientoDTO.setIndicacionPlazo(indicacionPlazo);

            log.info("Costo Tramite : " + procedimientoDTO.getCostoTramite());
            log.info("Plazo maximo de atencion :" + procedimientoDTO.getPlazoResolver());
            log.info("Silencio Administrativo : " + procedimientoDTO.getSilencioAdministrativo());
        }
        return procedimientoDTO;
    }

    @Override
    public List<DepartamentoDTO> obtenerDepartamentos() {
        log.info("-- RequisitoService - obtenerDepartamentos --");
        List<DepartamentoDTO> listaDepartamento = requisitoDAO.listarDepartamentos();
        return listaDepartamento;
    }

    @Override
    public List<ProvinciaDTO> obtenerProvincias(String idDepartamento) {
        log.info("-- RequisitoService - obtenerProvincias --");
        List<ProvinciaDTO> listaProvincia = requisitoDAO.listarProvincias(idDepartamento);
        return listaProvincia;
    }

    @Override
    public List<DistritoDTO> obtenerDistritos(String idDepartamento, String idProvincia) {
        log.info("-- RequisitoService - obtenerDistritos --");
        List<DistritoDTO> listaDistrito = requisitoDAO.listarDistritos(idDepartamento, idProvincia);
        return listaDistrito;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> obtenerDatosReporte(Long idActividad, Long idTramite, Long idProcedimiento, List<PreguntaRequisitoDTO> listaPreguntasEspecificas) {
        Map<String, Object> datos = new HashMap<String, Object>();
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = obtenerRequisitosProcedimientos(idActividad, idTramite, idProcedimiento, listaPreguntasEspecificas);

        listaRequisitoProcedimiento=ordenarListRequisitoProc(listaRequisitoProcedimiento);
        //eliminar duplicados jpiro
        HashSet<RequisitoProcedimientoDTO> hs = new HashSet<RequisitoProcedimientoDTO>(listaRequisitoProcedimiento);
        listaRequisitoProcedimiento.clear();
        listaRequisitoProcedimiento.addAll(hs);
        
        listaRequisitoProcedimiento=ordenarListRequisitoProc(listaRequisitoProcedimiento);
        //ordenando subrequisitos
        for(RequisitoProcedimientoDTO reg : listaRequisitoProcedimiento){
            if(reg.getListaSubRequisitoProcedimiento()!=null && reg.getListaSubRequisitoProcedimiento().size()>0){
                List<RequisitoProcedimientoDTO> listSubReqOrdenada=ordenarListRequisitoProc(reg.getListaSubRequisitoProcedimiento());
                reg.setListaSubRequisitoProcedimiento(listSubReqOrdenada);
            }
        }
        
        log.info("*** RequisitosProcedimientos Encontrados = " + listaRequisitoProcedimiento.size());

        //Obtencion de Requisitos
        List<RequisitoDTO> listaRequisito = obtenerRequisitos(listaRequisitoProcedimiento);
        log.info("*** listaRequisito = " + listaRequisito.size());
        //Obtener Comentarios Requisitos
        List<ComentarioDTO> listaComentariosRequisitos = new ArrayList<ComentarioDTO>();
        for (RequisitoDTO requisitoDTO : listaRequisito) {
            if (requisitoDTO.getRequisitoProcedimiento() != null && requisitoDTO.getRequisitoProcedimiento().getComentario()!=null) {
                listaComentariosRequisitos.add(new ComentarioDTO("("+requisitoDTO.getNroParaConsulta()+") "+requisitoDTO.getRequisitoProcedimiento().getComentario()));
            }
        }
        //quitando numeracion a los subrequisitos, porque ya esta concatenado en la descripcion y comentario
        //subrequisitos son los que en su numeracion incluyen un punto ".", ejem: 11.1,11.2,15.1,15.2,15.3
        for (RequisitoDTO requisitoDTO : listaRequisito) {
            if(requisitoDTO.getNroParaConsulta()!=null){
                String nroParaConsulta = requisitoDTO.getNroParaConsulta();
                if(nroParaConsulta.contains(".")){requisitoDTO.setNroParaConsulta("");}
            }else{
                requisitoDTO.setNroParaConsulta("");
            }
        }     

        List<PreguntaRequisitoDTO> listaPreguntasRequisito = new ArrayList<PreguntaRequisitoDTO>();
        for (PreguntaRequisitoDTO preguntaRequisitoDTO : listaPreguntasEspecificas) {
            if (preguntaRequisitoDTO.getTipoPregunta().equals(Constantes.PREGUNTA_REQUISITO_TIPO_PARAMETRO_DINAMICO)) {
                ParametroDinamicoDTO parametroDinamico = requisitoDAO.obtenerParametroDinamico(preguntaRequisitoDTO.getIdPregunta());
                ValorParametroDTO valorDinamico = requisitoDAO.obtenerValorParametroDinamico(new Long(preguntaRequisitoDTO.getPreguntaRespuesta()));
                PreguntaRequisitoDTO pregunta = new PreguntaRequisitoDTO();
                pregunta.setPregunta(parametroDinamico.getPregunta());
                pregunta.setPreguntaRespuesta(valorDinamico.getDescripcion());
                listaPreguntasRequisito.add(pregunta);
            }
            if (preguntaRequisitoDTO.getTipoPregunta().equals(Constantes.PREGUNTA_REQUISITO_TIPO_TRAMITE_PARAMETRO_DINAMICO)) {
                ParametroDinamicoDTO parametroDinamico = requisitoDAO.obtenerParametroDinamico(preguntaRequisitoDTO.getIdPregunta());
                ValorParametroDTO valorDinamico = requisitoDAO.obtenerValorParametroDinamico(new Long(preguntaRequisitoDTO.getPreguntaRespuesta()));
                PreguntaRequisitoDTO pregunta = new PreguntaRequisitoDTO();
                pregunta.setPregunta(parametroDinamico.getPregunta());
                pregunta.setPreguntaRespuesta(valorDinamico.getDescripcion());
                listaPreguntasRequisito.add(pregunta);
            }
            if (preguntaRequisitoDTO.getTipoPregunta().equals(Constantes.PREGUNTA_REQUISITO_TIPO_TRAMITE_MOTIVO_TRAMITE)) {
                MaestroColumnaDTO maestroColumna = requisitoDAO.obtenerMaestroColumna(Constantes.MAESTRO_COLUMNA_APLICACION_MYC, Constantes.MAESTRO_COLUMNA_DOMINIO_PREGUNTA_REQUISITO, Constantes.PREGUNTA_REQUISITO_MOTIVO_TRAMITE);
//                MaestroColumnaDTO maestroColumna = requisitoDAO.obtenerMaestroColumna(Constantes.PREGUNTA_REQUISITO_MOTIVO_TRAMITE);
                MotivoTramiteDTO motivoTramite = requisitoDAO.obtenerMotivoTramite(new Long(preguntaRequisitoDTO.getPreguntaRespuesta()));
                PreguntaRequisitoDTO pregunta = new PreguntaRequisitoDTO();
                pregunta.setPregunta(maestroColumna.getDescripcion());
                pregunta.setPreguntaRespuesta(motivoTramite.getDescripcion());
                listaPreguntasRequisito.add(pregunta);
            }
            if (preguntaRequisitoDTO.getTipoPregunta().equals(Constantes.PREGUNTA_REQUISITO_TIPO_ZONIFICACION)) {
                MaestroColumnaDTO maestroColumna = requisitoDAO.obtenerMaestroColumna(Constantes.MAESTRO_COLUMNA_APLICACION_MYC, Constantes.MAESTRO_COLUMNA_DOMINIO_PREGUNTA_REQUISITO, Constantes.PREGUNTA_REQUISITO_ZONIFICACION);
//                MaestroColumnaDTO maestroColumna = requisitoDAO.obtenerMaestroColumna(Constantes.PREGUNTA_REQUISITO_ZONIFICACION);
                String[] respuesta = preguntaRequisitoDTO.getPreguntaRespuesta().split("_");
                String departamento = requisitoDAO.obtenerUbigeo(respuesta[0], "00", "00").getNombre();
                String provincia = requisitoDAO.obtenerUbigeo(respuesta[0], respuesta[1], "00").getNombre();
                String distrito = requisitoDAO.obtenerUbigeo(respuesta[0], respuesta[1], respuesta[2]).getNombre();
                PreguntaRequisitoDTO pregunta = new PreguntaRequisitoDTO();
                pregunta.setPregunta(maestroColumna.getDescripcion());
                pregunta.setPreguntaRespuesta("Dpto.:" + departamento + " Prov.:" + provincia + " Dist.:" + distrito);
                listaPreguntasRequisito.add(pregunta);
            }
        }

        //Obtencion de Datos del Procedimiento
        ProcedimientoDTO procedimientoDTO = obtenerProcedemiento(idProcedimiento);
        //Obtener Comentarios Procedimiento
        List<ComentarioDTO> listaComentariosProcedimiento = obtenerComentariosProcedimiento(idProcedimiento);

        ActividadDTO rubro = requisitoDAO.obtenerActividad(idActividad);
        ActividadDTO actividad = requisitoDAO.obtenerActividad(rubro.getIdActividadPadre());
        TramiteDTO tramite = requisitoDAO.obtenerTramite(idTramite);
        EtapaDTO etapa = requisitoDAO.obtenerEtapa(tramite.getIdEtapa());
        datos.put("valorUIT", obtenerValorUIT());
        datos.put("actividad", actividad.getNombre());
        datos.put("rubro", rubro.getNombre());
        datos.put("tramite", tramite.getDescripcion());
        datos.put("etapa", etapa.getDescripcion());
        datos.put("procedimiento", procedimientoDTO);
        datos.put("listaRequisito", listaRequisito);
        datos.put("listaComentariosRequisitos", listaComentariosRequisitos);
        datos.put("listaComentariosProcedimiento", listaComentariosProcedimiento);
        datos.put("listaPreguntasRequisito", listaPreguntasRequisito);
        return datos;
    }

    private List<ComentarioDTO> obtenerComentariosProcedimiento(Long idProcedimiento) {
        log.info("-- obtenerComentariosProcedimiento --");
        ProcedimientoDTO procedimientoDTO = obtenerProcedemiento(idProcedimiento);
        List<ComentarioDTO> listaComentariosProcedimiento = new ArrayList<ComentarioDTO>();
        if (procedimientoDTO.getNotaProcedimiento() != null) {
            ComentarioDTO comentarioProcedimiento = new ComentarioDTO(procedimientoDTO.getNotaProcedimiento());
            listaComentariosProcedimiento.add(comentarioProcedimiento);
        }
        return listaComentariosProcedimiento;
    }

    @Override
    public Map<String, Object> descargarDatosAlfresco(Long idDocumentoAdjunto) {
        Map<String, Object> data = new HashMap<String, Object>();
        InputStream in = null;
        try {
            DocumentoAdjuntoDTO documentoAdjuntoDTO = requisitoDAO.obtenerDocumentoAdjunto(idDocumentoAdjunto);
            in = AlfrescoInvoker.download(alfrescoHost + alfrescoDownload, alfrescoUser, alfrescoBase, null, null, null, alfrescoRuta, documentoAdjuntoDTO.getRutaAlfresco());
            data.put("inputStream", in);
            data.put("nombreArchivo", documentoAdjuntoDTO.getNombre());
        } catch (Exception ex) {
//            ex.printStackTrace();
            log.error("error", ex.getMessage());
        }
        return data;
    }
    
    @Override
    @Transactional(readOnly = true)
    public String obtenerValorUIT(){
        String valorUIT = "";
        MaestroColumnaDTO maestroColumna = requisitoDAO.obtenerMaestroColumna(Constantes.MAESTRO_COLUMNA_APLICACION_MYC, Constantes.MAESTRO_COLUMNA_DOMINIO_VALOR_UIT, Constantes.PARAMETRO_DINAMICO_VALOR_UIT);
        if(maestroColumna != null){
            valorUIT = maestroColumna.getDescripcion();
        }
        return valorUIT;
    }
}
