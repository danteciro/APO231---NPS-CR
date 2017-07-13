package gob.osinergmin.consulta.service.dao.impl;

import gob.osinergmin.consulta.domain.MdiActividad;
import gob.osinergmin.consulta.domain.MdiDocumentoAdjunto;
import gob.osinergmin.consulta.domain.MdiMaestroColumna;
import gob.osinergmin.consulta.domain.MdiUbigeo;
import gob.osinergmin.consulta.domain.PghCnfRequProcedimiento;
import gob.osinergmin.consulta.domain.PghEtapa;
import gob.osinergmin.consulta.domain.PghMotivoTramite;
import gob.osinergmin.consulta.domain.PghParametroDinamico;
import gob.osinergmin.consulta.domain.PghProcedimiento;
import gob.osinergmin.consulta.domain.PghRequisito;
import gob.osinergmin.consulta.domain.PghTramite;
import gob.osinergmin.consulta.domain.PghValorParametro;
import gob.osinergmin.consulta.domain.PghZonificacionDetalle;
import gob.osinergmin.consulta.domain.builder.ActividadBuilder;
import gob.osinergmin.consulta.domain.builder.DocumentoAdjuntoBuilder;
import gob.osinergmin.consulta.domain.builder.EtapaBuilder;
import gob.osinergmin.consulta.domain.builder.MaestroColumnaBuilder;
import gob.osinergmin.consulta.domain.builder.MotivoTramiteBuilder;
import gob.osinergmin.consulta.domain.builder.ParametroDinamicoBuilder;
import gob.osinergmin.consulta.domain.builder.ProcedimientoBuilder;
import gob.osinergmin.consulta.domain.builder.RequisitoBuilder;
import gob.osinergmin.consulta.domain.builder.RequisitoProcedimientoBuilder;
import gob.osinergmin.consulta.domain.builder.TramiteBuilder;
import gob.osinergmin.consulta.domain.builder.UbigeoBuilder;
import gob.osinergmin.consulta.domain.builder.ValorParametroBuilder;
import gob.osinergmin.consulta.domain.builder.ZonificacionDetalleBuilder;
import gob.osinergmin.consulta.dto.ActividadDTO;
import gob.osinergmin.consulta.dto.DepartamentoDTO;
import gob.osinergmin.consulta.dto.DistritoDTO;
import gob.osinergmin.consulta.dto.DocumentoAdjuntoDTO;
import gob.osinergmin.consulta.dto.EtapaDTO;
import gob.osinergmin.consulta.dto.MaestroColumnaDTO;
import gob.osinergmin.consulta.dto.MotivoTramiteDTO;
import gob.osinergmin.consulta.dto.ParametroDinamicoDTO;
import gob.osinergmin.consulta.dto.ProcedimientoDTO;
import gob.osinergmin.consulta.dto.ProvinciaDTO;
import gob.osinergmin.consulta.dto.RequisitoDTO;
import gob.osinergmin.consulta.dto.RequisitoProcedimientoDTO;
import gob.osinergmin.consulta.dto.TramiteDTO;
import gob.osinergmin.consulta.dto.UbigeoDTO;
import gob.osinergmin.consulta.dto.ValorParametroDTO;
import gob.osinergmin.consulta.dto.ZonificacionDetalleDTO;
import gob.osinergmin.consulta.service.dao.CrudDAO;
import gob.osinergmin.consulta.service.dao.ConsultaRequisitoDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("ConsultaRequisitoDAO")
public class ConsultaRequisitoDAOImpl implements ConsultaRequisitoDAO {

    private static final Logger log = LoggerFactory.getLogger(ConsultaRequisitoDAOImpl.class);
    @Inject
    private CrudDAO crudDAO;

    @Override
    public List<ActividadDTO> listarActividadesPadre() {
        log.info("-- RequisitoDAO - listarActividadesPadre --");
        List<ActividadDTO> listaActividad = new ArrayList<ActividadDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select a ");
        jpql.append(" from MdiActividad a ");
        jpql.append("  where 1=1 ");
        jpql.append("  and a.estado = 1 ");
        jpql.append("  and a.idActividadPadre is null ");        
        jpql.append("  order by a.ordenNps, a.nombre asc ");

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<MdiActividad> lstActividad = query.getResultList();
        listaActividad = ActividadBuilder.getListaActividad(lstActividad);
        log.info("lstActividad = " + listaActividad.size());
        return listaActividad;
    }

    @Override
    public List<ActividadDTO> listarActividades(ActividadDTO actividad) {
        log.info("-- RequisitoDAO - listarActividadesPadre --");
        log.info("-- parametros : " + actividad.toString());
        List<ActividadDTO> listaActividad = new ArrayList<ActividadDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select a ");
        jpql.append(" from MdiActividad a ");
        jpql.append(" where 1=1 ");
        jpql.append(" and a.estado = 1 ");
        if (actividad.getIdActividadPadre() != null) {
            jpql.append(" and a.idActividadPadre = ").append(actividad.getIdActividadPadre());
        }
        jpql.append("  order by a.ordenNps, a.nombre asc ");
        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<MdiActividad> lstActividad = query.getResultList();
        listaActividad = ActividadBuilder.getListaActividad(lstActividad);
        log.info("lstActividad = " + listaActividad.size());
        return listaActividad;
    }

    @Override
    public List<EtapaDTO> listarEtapas(Long idActividad) {
        log.info("-- RequisitoDAO - listarEtapas --");
        log.info("-- parametros idActividad : " + idActividad);
        List<EtapaDTO> listaEtapa = new ArrayList<EtapaDTO>();
        StringBuilder jpql = new StringBuilder();
            jpql.append(" select distinct e ");
        jpql.append(" from PghEtapa e "
                + "inner join e.pghTramiteList t "
                + "inner join t.pghCnfTramiteActividadList ta ");
        jpql.append(" where 1=1 ");
        jpql.append(" and e.estado = 1 ");
        jpql.append(" and t.estado = 1 ");
        jpql.append(" and ta.estado = 1 ");
        jpql.append(" and ta.idActividad.idActividad = ").append(idActividad);
        jpql.append(" and e.idProceso.idProceso = 1");
        jpql.append(" order by e.ordenEtapa ");

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghEtapa> lstEtapa = query.getResultList();
        listaEtapa = EtapaBuilder.getListaEtapa(lstEtapa);
        log.info("listaEtapa = " + listaEtapa.size());
        return listaEtapa;
    }

    @Override
    public List<TramiteDTO> listarTramites(Long idActividad, Long idEtapa) {
        log.info("-- RequisitoDAO - listarTramites --");
        log.info("-- parametros idActividad : " + idActividad + " idEtapa : " + idEtapa);
        List<TramiteDTO> listaTramite = new ArrayList<TramiteDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select t ");
        jpql.append(" from PghTramite t "
                + "inner join t.pghCnfTramiteActividadList ta ");
        jpql.append(" where 1=1 ");
        jpql.append(" and t.estado = 1 ");
        jpql.append(" and ta.estado = 1 ");
        jpql.append(" and ta.idActividad.idActividad = ").append(idActividad);
        jpql.append(" and t.idEtapa.idEtapa = ").append(idEtapa);
        jpql.append(" order by t.descripcion asc ");

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghTramite> lstTramite = query.getResultList();
        listaTramite = TramiteBuilder.getListaTramite(lstTramite);
        log.info("listaTramite = " + listaTramite.size());
        return listaTramite;
    }

    @Override
    public List<ProcedimientoDTO> listarProcedimiento(Long idActividad, Long idTramite) {
        log.info("-- RequisitoDAO - listarProcedimiento --");
        log.info("-- parametros idActividad : " + idActividad + " idTramite : " + idTramite);
        List<ProcedimientoDTO> listaProcedimiento = new ArrayList<ProcedimientoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select p ");
        jpql.append(" from PghProcedimiento p "
                + "inner join p.pghProcedimientoTramiteList pt "
                + "inner join pt.pghProcTramActividadList pta ");
        jpql.append(" where 1=1 ");
        jpql.append(" and p.estado = 1 ");
        jpql.append(" and pt.estado = 1 ");
        jpql.append(" and pta.estado = 1 ");
        jpql.append(" and pta.idActividad.idActividad = ").append(idActividad);
        jpql.append(" and pt.idTramite.idTramite = ").append(idTramite);

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghProcedimiento> lstProcedimiento = query.getResultList();
        listaProcedimiento = ProcedimientoBuilder.getListaProcedimiento(lstProcedimiento);
        log.info("listaProcedimiento = " + listaProcedimiento.size());
        return listaProcedimiento;
    }

    //Caso 9 Requisitos Generales -- requisitos
    @Override
    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoGenericos(Long idProcedimiento) {
        log.info("-- RequisitoDAO - listarRequisitosProcedimientoGenericos --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select rp ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " left outer join rp.pghRequProcParaDinaList rppd ");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rp.idTramite.idTramite is null ");
        jpql.append(" and rp.idActividad.idActividad is null ");
        jpql.append(" and rp.idZonificacion.idZonificacion is null ");
        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
        jpql.append(" and rppd.idRequProcParaDina is null ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
        jpql.append(" and rp.idRequisitoProcedimientoPad is null ");


        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
        log.info("listaRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
        return listaRequisitoProcedimiento;
    }

    @Override
    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoGenericosVisualizacion(Long idProcedimiento, Long tipoParametroDinamicoVisualizacion) {
        log.info("-- RequisitoDAO - listarRequisitosProcedimientoGenericosVisualizacion --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        log.info("-- parametros tipoParametroDinamicoVisualizacion : " + tipoParametroDinamicoVisualizacion);
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select rp ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " inner join rp.pghRequProcParaDinaList rppd "
                + " inner join rppd.idValorParametro vp "
                + " inner join vp.idParametroDinamico pd");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rppd.estado = 1 ");
        jpql.append(" and rp.idTramite.idTramite is null ");
        jpql.append(" and rp.idActividad.idActividad is null ");
        jpql.append(" and rp.idZonificacion.idZonificacion is null ");
        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
        jpql.append(" and pd.tipoConsulta.idMaestroColumna = ").append(tipoParametroDinamicoVisualizacion);
        jpql.append(" and rp.idRequisitoProcedimiento not in ( "
                + " select rp2 from PghCnfRequProcedimiento rp2 "
                + "     inner join rp2.pghRequProcParaDinaList rppd2 "
                + "     inner join rppd2.idValorParametro vp2 "
                + "     inner join vp2.idParametroDinamico pd2"
                + "     where 1=1 "
                + "     and rp2.estado = 1 " );
        jpql.append("   and rp2.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
        jpql.append("   and pd2.tipoConsulta.idMaestroColumna in ( ").append(200).append(")");
        jpql.append(" ) ");
        jpql.append(" and rp.idRequisitoProcedimientoPad is null ");

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
        log.info("listaRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
        return listaRequisitoProcedimiento;
    }

    //Caso 2 Tramite -- obtener requisitos
    @Override
    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoTramite(Long idProcedimiento, Long idTramite) {
        log.info("-- RequisitoDAO - listarRequisitosTramite --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        log.info("-- parametros idTramite : " + idTramite);
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select rp ");
        jpql.append(" from PghCnfRequProcedimiento  rp "
                + " left outer join rp.pghRequProcParaDinaList rppd ");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rp.idTramite.idTramite = ").append(idTramite);
        jpql.append(" and rp.idActividad.idActividad is null ");
        jpql.append(" and rp.idZonificacion.idZonificacion is null ");
        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
        jpql.append(" and rppd.idRequProcParaDina is null ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
        jpql.append(" and rp.idRequisitoProcedimientoPad is null ");

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
        log.info("listaRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
        return listaRequisitoProcedimiento;
    }

    //Caso 2 Tramite -- obtener requisitos visualizacion
    @Override
    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoTramiteVisualizacion(Long idProcedimiento, Long idTramite, Long tipoParametroDinamico) {
        log.info("-- RequisitoDAO - listarRequisitosProcedimientoGenericosVisualizacion --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        log.info("-- parametros idTramite : " + idTramite);
        log.info("-- parametros tipoParametroDinamico : " + tipoParametroDinamico);
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select rp ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " inner join rp.pghRequProcParaDinaList rppd "
                + " inner join rppd.idValorParametro vp "
                + " inner join vp.idParametroDinamico pd");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rppd.estado = 1 ");
        jpql.append(" and rp.idTramite.idTramite = ").append(idTramite);
        jpql.append(" and rp.idActividad.idActividad is null ");
        jpql.append(" and rp.idZonificacion.idZonificacion is null ");
        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
        jpql.append(" and pd.tipoConsulta.idMaestroColumna = ").append(tipoParametroDinamico);
        jpql.append(" and rp.idRequisitoProcedimientoPad is null ");

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
        log.info("listaRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
        return listaRequisitoProcedimiento;
    }

    //Caso 3 Tramite - Actividad -- obtener requisitos
    @Override
    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoTramiteActividad(Long idProcedimiento, Long idTramite, Long idActividad) {
        log.info("-- RequisitoDAO - listarRequisitosProcedimientoTramiteActividad --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        log.info("-- parametros idTramite : " + idTramite);
        log.info("-- parametros idActividad : " + idActividad);
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select rp ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " left outer join rp.pghRequProcParaDinaList rppd ");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rp.idTramite.idTramite = ").append(idTramite);
        jpql.append(" and rp.idActividad.idActividad = ").append(idActividad);
        jpql.append(" and rp.idZonificacion.idZonificacion is null ");
        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
        jpql.append(" and rppd.idRequProcParaDina is null ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
        jpql.append(" and rp.idRequisitoProcedimientoPad is null ");

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
        log.info("listaRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
        return listaRequisitoProcedimiento;
    }

    //Caso 3 Tramite - Actividad -- obtener requisitos visualizacion
    @Override
    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoTramiteActividadVisualizacion(Long idProcedimiento, Long idTramite, Long idActividad, Long tipoParametroDinamico) {
        log.info("-- RequisitoDAO - listarRequisitosProcedimientoTramiteActividad --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        log.info("-- parametros idTramite : " + idTramite);
        log.info("-- parametros idActividad : " + idActividad);
        log.info("-- parametros tipoParametroDinamico : " + tipoParametroDinamico);
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select rp ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " inner join rp.pghRequProcParaDinaList rppd "
                + " inner join rppd.idValorParametro vp "
                + " inner join vp.idParametroDinamico pd");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rppd.estado = 1 ");
        jpql.append(" and rp.idTramite.idTramite = ").append(idTramite);
        jpql.append(" and rp.idActividad.idActividad = ").append(idActividad);
        jpql.append(" and rp.idZonificacion.idZonificacion is null ");
        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
        jpql.append(" and pd.tipoConsulta.idMaestroColumna = ").append(tipoParametroDinamico);
        jpql.append(" and rp.idRequisitoProcedimientoPad is null ");

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
        log.info("listaRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
        return listaRequisitoProcedimiento;
    }

    //Caso 1 pregunta Tipo Persona
    //Caso 4 pregunta Parametros Dinamicos
    @Override
    public List<ParametroDinamicoDTO> listarPreguntasParametrosDinamicos(Long idProcedimiento, Long tipoParametroDinamico) {
        log.info("-- RequisitoDAO - listarPreguntasParametrosDinamicos --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        log.info("-- parametros tipoParametroDinamico : " + tipoParametroDinamico);
        List<ParametroDinamicoDTO> listaTipoPersona = new ArrayList<ParametroDinamicoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select pd ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " inner join rp.pghRequProcParaDinaList rppd "
                + " inner join rppd.idValorParametro vp "
                + " inner join vp.idParametroDinamico pd");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rppd.estado = 1 ");
        jpql.append(" and vp.estado = 1 ");
        jpql.append(" and pd.estado = 1 ");
        jpql.append(" and rp.idTramite.idTramite is null ");
        jpql.append(" and rp.idActividad.idActividad is null ");
        jpql.append(" and rp.idZonificacion.idZonificacion is null ");
        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
        jpql.append(" and pd.tipoConsulta.idMaestroColumna = ").append(tipoParametroDinamico);

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghParametroDinamico> lstParametroDinamico = query.getResultList();
        listaTipoPersona = ParametroDinamicoBuilder.getListaParametroDinamico(lstParametroDinamico);
        log.info("listaTipoPersona = " + listaTipoPersona.size());
        return listaTipoPersona;
    }

//    //Caso 1 pregunta Tipo Persona
//    //Caso 4 pregunta Parametros Dinamicos
//    @Override
//    public List<ParametroDinamicoDTO> listarPreguntasParametrosDinamicosVisualizacion(Long idProcedimiento, Long tipoParametroDinamico, Long tipoParametroDinamicoVisualizacion) {
//        log.info("-- RequisitoDAO - listarPreguntasParametrosDinamicosVisualizacion --");
//        log.info("-- parametros idProcedimiento : " + idProcedimiento);
//        log.info("-- parametros tipoParametroDinamico : " + tipoParametroDinamico);
//        List<ParametroDinamicoDTO> listaTipoPersona = new ArrayList<ParametroDinamicoDTO>();
//        StringBuilder jpql = new StringBuilder();
//        jpql.append(" select pd ");
//        jpql.append(" from PghCnfRequProcedimiento rp "
//                + " inner join rp.pghRequProcParaDinaList rppd "
//                + " inner join rppd.idValorParametro vp "
//                + " inner join vp.idParametroDinamico pd"
//                + " inner join rp.pghRequProcParaDinaList rppd_2 "
//                + " inner join rppd_2.idValorParametro vp_2 "
//                + " inner join vp_2.idParametroDinamico pd_2");
//        jpql.append(" where 1=1 ");
//        jpql.append(" and rp.estado = 1 ");
//        jpql.append(" and rppd.estado = 1 ");
//        jpql.append(" and vp.estado = 1 ");
//        jpql.append(" and pd.estado = 1 ");
//        jpql.append(" and rp.idTramite.idTramite is null ");
//        jpql.append(" and rp.idActividad.idActividad is null ");
//        jpql.append(" and rp.idZonificacion.idZonificacion is null ");
//        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
//        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
//        jpql.append(" and pd.tipoConsulta.idMaestroColumna = ").append(tipoParametroDinamico);
//        jpql.append(" and pd_2.tipoConsulta.idMaestroColumna = ").append(tipoParametroDinamicoVisualizacion);
//
//        String queryString = jpql.toString();
//        Query query = crudDAO.getEm().createQuery(queryString);
//        List<PghParametroDinamico> lstParametroDinamico = query.getResultList();
//        listaTipoPersona = ParametroDinamicoBuilder.getListaParametroDinamico(lstParametroDinamico);
//        log.info("listaTipoPersona = " + listaTipoPersona.size());
//        return listaTipoPersona;
//    }

    //Caso 1 pregunta Tipo Persona - Requisitos
    //Caso 4 pregunta Parametros Dinamicos - Requisitos
    @Override
    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoParametroDinamico(Long idProcedimiento, Long idParametroDinamico, Long idValorParametroDinamico) {
        log.info("-- RequisitoDAO - listarRequisitosParametroDinamico --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        log.info("-- parametros idParametroDinamico : " + idParametroDinamico);
        log.info("-- parametros idValorParametroDinamico : " + idValorParametroDinamico);
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select rp ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " inner join rp.pghRequProcParaDinaList rppd "
                + " inner join rppd.idValorParametro vp "
                + " inner join vp.idParametroDinamico pd");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rppd.estado = 1 ");
        jpql.append(" and vp.estado = 1 ");
        jpql.append(" and pd.estado = 1 ");
        jpql.append(" and rp.idTramite.idTramite is null ");
        jpql.append(" and rp.idActividad.idActividad is null ");
        jpql.append(" and rp.idZonificacion.idZonificacion is null ");
        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
        jpql.append(" and pd.idParametroDinamico = ").append(idParametroDinamico);
        jpql.append(" and vp.idValorParametro = ").append(idValorParametroDinamico);
        jpql.append(" and rp.idRequisitoProcedimientoPad is null ");

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
        log.info("listaRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
        return listaRequisitoProcedimiento;
    }

//    //Caso 1 pregunta Tipo Persona - Requisitos
//    //Caso 4 pregunta Parametros Dinamicos - Requisitos
//    @Override
//    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoParametroDinamicoVisualizacion(Long idProcedimiento, Long idParametroDinamico, Long idValorParametroDinamico, Long tipoParametroDinamicoVisualizacion) {
//        log.info("-- RequisitoDAO - listarRequisitosParametroDinamico --");
//        log.info("-- parametros idProcedimiento : " + idProcedimiento);
//        log.info("-- parametros idParametroDinamico : " + idParametroDinamico);
//        log.info("-- parametros idValorParametroDinamico : " + idValorParametroDinamico);
//        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
//        StringBuilder jpql = new StringBuilder();
//        jpql.append(" select rp ");
//        jpql.append(" from PghCnfRequProcedimiento rp "
//                + " inner join rp.pghRequProcParaDinaList rppd "
//                + " inner join rppd.idValorParametro vp "
//                + " inner join vp.idParametroDinamico pd"
//                + " inner join rp.pghRequProcParaDinaList rppd_2 "
//                + " inner join rppd_2.idValorParametro vp_2 "
//                + " inner join vp_2.idParametroDinamico pd_2");
//        jpql.append(" where 1=1 ");
//        jpql.append(" and rp.estado = 1 ");
//        jpql.append(" and rppd.estado = 1 ");
//        jpql.append(" and vp.estado = 1 ");
//        jpql.append(" and pd.estado = 1 ");
//        jpql.append(" and rp.idTramite.idTramite is null ");
//        jpql.append(" and rp.idActividad.idActividad is null ");
//        jpql.append(" and rp.idZonificacion.idZonificacion is null ");
//        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
//        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
//        jpql.append(" and pd.idParametroDinamico = ").append(idParametroDinamico);
//        jpql.append(" and vp.idValorParametro = ").append(idValorParametroDinamico);
//        jpql.append(" and pd_2.tipoConsulta.idMaestroColumna = ").append(tipoParametroDinamicoVisualizacion);
//
//        String queryString = jpql.toString();
//        Query query = crudDAO.getEm().createQuery(queryString);
//        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
//        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
//        log.info("listaRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
//        return listaRequisitoProcedimiento;
//    }

    //Caso 6 pregunta Tramite - Parametros Dinamicos
    @Override
    public List<ParametroDinamicoDTO> listarTramiteParametrosDinamicos(Long idProcedimiento, Long idTramite, Long tipoParametroDinamico) {
        log.info("-- RequisitoDAO - listarParametrosDinamicos --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        log.info("-- parametros idTramite : " + idTramite);
        log.info("-- parametros tipoParametroDinamico : " + tipoParametroDinamico);
        List<ParametroDinamicoDTO> listaParametroDinamico = new ArrayList<ParametroDinamicoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select pd ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " inner join rp.pghRequProcParaDinaList rppd "
                + " inner join rppd.idValorParametro vp "
                + " inner join vp.idParametroDinamico pd");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rppd.estado = 1 ");
        jpql.append(" and vp.estado = 1 ");
        jpql.append(" and pd.estado = 1 ");
        jpql.append(" and rp.idTramite.idTramite = ").append(idTramite);
        jpql.append(" and rp.idActividad.idActividad is null ");
        jpql.append(" and rp.idZonificacion.idZonificacion is null ");
        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
        jpql.append(" and pd.tipoConsulta.idMaestroColumna = ").append(tipoParametroDinamico);

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghParametroDinamico> lstParametroDinamico = query.getResultList();
        listaParametroDinamico = ParametroDinamicoBuilder.getListaParametroDinamico(lstParametroDinamico);
        log.info("listarParametrosDinamicos = " + listaParametroDinamico.size());
        return listaParametroDinamico;
    }

//    //Caso 6 pregunta Tramite - Parametros Dinamicos
//    @Override
//    public List<ParametroDinamicoDTO> listarTramiteParametrosDinamicosVisualizacion(Long idProcedimiento, Long idTramite, Long tipoParametroDinamico, Long tipoParametroDinamicoVisualizacion) {
//        log.info("-- RequisitoDAO - listarTramiteParametrosDinamicosVisualizacion --");
//        log.info("-- parametros idProcedimiento : " + idProcedimiento);
//        log.info("-- parametros idTramite : " + idTramite);
//        log.info("-- parametros tipoParametroDinamico : " + tipoParametroDinamico);
//        log.info("-- parametros tipoParametroDinamicoVisualizacion : " + tipoParametroDinamicoVisualizacion);
//        List<ParametroDinamicoDTO> listaParametroDinamico = new ArrayList<ParametroDinamicoDTO>();
//        StringBuilder jpql = new StringBuilder();
//        jpql.append(" select pd ");
//        jpql.append(" from PghCnfRequProcedimiento rp "
//                + " inner join rp.pghRequProcParaDinaList rppd "
//                + " inner join rppd.idValorParametro vp "
//                + " inner join vp.idParametroDinamico pd"
//                + " inner join rp.pghRequProcParaDinaList rppd_2 "
//                + " inner join rppd_2.idValorParametro vp_2 "
//                + " inner join vp_2.idParametroDinamico pd_2");
//        jpql.append(" where 1=1 ");
//        jpql.append(" and rp.estado = 1 ");
//        jpql.append(" and rppd.estado = 1 ");
//        jpql.append(" and vp.estado = 1 ");
//        jpql.append(" and pd.estado = 1 ");
//        jpql.append(" and rp.idTramite.idTramite = ").append(idTramite);
//        jpql.append(" and rp.idActividad.idActividad is null ");
//        jpql.append(" and rp.idZonificacion.idZonificacion is null ");
//        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
//        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
//        jpql.append(" and pd.tipoConsulta.idMaestroColumna = ").append(tipoParametroDinamico);
//        jpql.append(" and pd_2.tipoConsulta.idMaestroColumna = ").append(tipoParametroDinamicoVisualizacion);
//
//        String queryString = jpql.toString();
//        Query query = crudDAO.getEm().createQuery(queryString);
//        List<PghParametroDinamico> lstParametroDinamico = query.getResultList();
//        listaParametroDinamico = ParametroDinamicoBuilder.getListaParametroDinamico(lstParametroDinamico);
//        log.info("listarParametrosDinamicos = " + listaParametroDinamico.size());
//        return listaParametroDinamico;
//    }

    //Caso 6 pregunta Tramite - Parametros Dinamicos -- Requisitos
    @Override
    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoTramiteParametrosDinamicos(Long idProcedimiento, Long idTramite, Long idParametroDinamico, Long idValorParametroDinamico) {
        log.info("-- RequisitoDAO - listarRequisitosProcedimientoTramiteParametrosDinamicos --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        log.info("-- parametros idTramite : " + idTramite);
        log.info("-- parametros idParametroDinamico : " + idParametroDinamico);
        log.info("-- parametros idValorParametroDinamico : " + idValorParametroDinamico);
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select rp ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " inner join rp.pghRequProcParaDinaList rppd "
                + " inner join rppd.idValorParametro vp "
                + " inner join vp.idParametroDinamico pd");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rppd.estado = 1 ");
        jpql.append(" and vp.estado = 1 ");
        jpql.append(" and pd.estado = 1 ");
        jpql.append(" and rp.idTramite.idTramite = ").append(idTramite);
        jpql.append(" and rp.idActividad.idActividad is null ");
        jpql.append(" and rp.idZonificacion.idZonificacion is null ");
        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
        jpql.append(" and pd.idParametroDinamico = ").append(idParametroDinamico);
        jpql.append(" and vp.idValorParametro = ").append(idValorParametroDinamico);
        jpql.append(" and rp.idRequisitoProcedimientoPad is null ");

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
        log.info("listaRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
        return listaRequisitoProcedimiento;
    }

//    //Caso 6 pregunta Tramite - Parametros Dinamicos -- Requisitos
//    @Override
//    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoTramiteParametrosDinamicosVisualizacion(Long idProcedimiento, Long idTramite, Long idParametroDinamico, Long idValorParametroDinamico, Long tipoParametroDinamicoVisualizacion) {
//        log.info("-- RequisitoDAO - listarRequisitosProcedimientoTramiteParametrosDinamicosVisualizacion --");
//        log.info("-- parametros idProcedimiento : " + idProcedimiento);
//        log.info("-- parametros idTramite : " + idTramite);
//        log.info("-- parametros idParametroDinamico : " + idParametroDinamico);
//        log.info("-- parametros idValorParametroDinamico : " + idValorParametroDinamico);
//        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
//        StringBuilder jpql = new StringBuilder();
//        jpql.append(" select rp ");
//        jpql.append(" from PghCnfRequProcedimiento rp "
//                + " inner join rp.pghRequProcParaDinaList rppd "
//                + " inner join rppd.idValorParametro vp "
//                + " inner join vp.idParametroDinamico pd"
//                + " inner join rp.pghRequProcParaDinaList rppd_2 "
//                + " inner join rppd_2.idValorParametro vp_2 "
//                + " inner join vp_2.idParametroDinamico pd_2");
//        jpql.append(" where 1=1 ");
//        jpql.append(" and rp.estado = 1 ");
//        jpql.append(" and rppd.estado = 1 ");
//        jpql.append(" and vp.estado = 1 ");
//        jpql.append(" and pd.estado = 1 ");
//        jpql.append(" and rp.idTramite.idTramite = ").append(idTramite);
//        jpql.append(" and rp.idActividad.idActividad is null ");
//        jpql.append(" and rp.idZonificacion.idZonificacion is null ");
//        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
//        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
//        jpql.append(" and pd.idParametroDinamico = ").append(idParametroDinamico);
//        jpql.append(" and vp.idValorParametro = ").append(idValorParametroDinamico);
//        jpql.append(" and pd_2.tipoConsulta.idMaestroColumna = ").append(tipoParametroDinamicoVisualizacion);
//
//        String queryString = jpql.toString();
//        Query query = crudDAO.getEm().createQuery(queryString);
//        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
//        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
//        log.info("listaRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
//        return listaRequisitoProcedimiento;
//    }

    //Caso 5 Zonificacion
    @Override
    public List<RequisitoProcedimientoDTO> listarZonificacion(Long idProcedimiento) {
        log.info("-- RequisitoDAO - listarZonificacion --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select rp ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " left outer join rp.pghRequProcParaDinaList rppd ");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rp.idTramite.idTramite is null ");
        jpql.append(" and rp.idActividad.idActividad is null ");
        jpql.append(" and rp.idZonificacion.idZonificacion is not null ");
        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
        jpql.append(" and rppd.idRequProcParaDina is null ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
        log.info("listaRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
        return listaRequisitoProcedimiento;
    }

    //Caso 5 Zonificacion
    @Override
    public List<RequisitoProcedimientoDTO> listarZonificacionVisualizacion(Long idProcedimiento, Long tipoParametroDinamicoVisualizacion) {
        log.info("-- RequisitoDAO - listarZonificacionVisualizacion --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select rp ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " inner join rp.pghRequProcParaDinaList rppd "
                + " inner join rppd.idValorParametro vp "
                + " inner join vp.idParametroDinamico pd");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rppd.estado = 1 ");
        jpql.append(" and rp.idTramite.idTramite is null ");
        jpql.append(" and rp.idActividad.idActividad is null ");
        jpql.append(" and rp.idZonificacion.idZonificacion is not null ");
        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
        jpql.append(" and pd.tipoConsulta.idMaestroColumna = ").append(tipoParametroDinamicoVisualizacion);

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
        log.info("listaRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
        return listaRequisitoProcedimiento;
    }

    //Caso 5 Zonificacion - Requisitos
    @Override
    public List<RequisitoProcedimientoDTO> listarRequisitoProcedimientoZonificacion(Long idProcedimiento, Long idZonificacion) {
        log.info("-- RequisitoDAO - listarRequisitoProcedimientoZonificacion --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        log.info("-- parametros idZonificacion : " + idZonificacion);
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select rp ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " left outer join rp.pghRequProcParaDinaList rppd ");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rp.idTramite.idTramite is null ");
        jpql.append(" and rp.idActividad.idActividad is null ");
        jpql.append(" and rp.idZonificacion.idZonificacion = ").append(idZonificacion);
        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
        jpql.append(" and rppd.idRequProcParaDina is null ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
        jpql.append(" and rp.idRequisitoProcedimientoPad is null ");

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
        log.info("listaRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
        return listaRequisitoProcedimiento;
    }

    //Caso 5 Zonificacion - Requisitos
    @Override
    public List<RequisitoProcedimientoDTO> listarRequisitoProcedimientoZonificacionVisualizacion(Long idProcedimiento, Long idZonificacion, Long tipoParametroDinamicoVisualizacion) {
        log.info("-- RequisitoDAO - listarRequisitoProcedimientoZonificacionVisualizacion --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        log.info("-- parametros idZonificacion : " + idZonificacion);
        log.info("-- parametros tipoParametroDinamicoVisualizacion : " + tipoParametroDinamicoVisualizacion);
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select rp ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " inner join rp.pghRequProcParaDinaList rppd "
                + " inner join rppd.idValorParametro vp "
                + " inner join vp.idParametroDinamico pd");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rppd.estado = 1 ");
        jpql.append(" and rp.idTramite.idTramite is null ");
        jpql.append(" and rp.idActividad.idActividad is null ");
        jpql.append(" and rp.idZonificacion.idZonificacion = ").append(idZonificacion);
        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
        jpql.append(" and pd.tipoConsulta.idMaestroColumna = ").append(tipoParametroDinamicoVisualizacion);
        jpql.append(" and rp.idRequisitoProcedimientoPad is null ");

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
        log.info("listaRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
        return listaRequisitoProcedimiento;
    }

    //Caso 8 Actividad - Zonificacion
    @Override
    public List<RequisitoProcedimientoDTO> listarActividadZonificacion(Long idProcedimiento, Long idActividad, Long idTramite) {
        log.info("-- RequisitoDAO - listarActividadZonificacion --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        log.info("-- parametros idActividad : " + idActividad);
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select rp ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " left outer join rp.pghRequProcParaDinaList rppd ");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rp.idTramite.idTramite = ").append(idTramite);
        jpql.append(" and rp.idActividad.idActividad = ").append(idActividad);
        jpql.append(" and rp.idZonificacion.idZonificacion is not null ");
        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
        jpql.append(" and rppd.idRequProcParaDina is null ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
        log.info("listaRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
        return listaRequisitoProcedimiento;
    }

    //Caso 8 Actividad - Zonificacion
    @Override
    public List<RequisitoProcedimientoDTO> listarActividadZonificacionVisualizacion(Long idProcedimiento, Long idActividad, Long idTramite, Long tipoParametroDinamicoVisualizacion) {
        log.info("-- RequisitoDAO - listarActividadZonificacionVisualizacion --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        log.info("-- parametros idActividad : " + idActividad);
        log.info("-- parametros idTramite : " + idTramite);
        log.info("-- parametros tipoParametroDinamicoVisualizacion : " + tipoParametroDinamicoVisualizacion);
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select rp ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " inner join rp.pghRequProcParaDinaList rppd "
                + " inner join rppd.idValorParametro vp "
                + " inner join vp.idParametroDinamico pd");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rppd.estado = 1 ");
        jpql.append(" and rp.idTramite.idTramite = ").append(idTramite);
        jpql.append(" and rp.idActividad.idActividad = ").append(idActividad);
        jpql.append(" and rp.idZonificacion.idZonificacion is not null ");
        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
        jpql.append(" and pd.tipoConsulta.idMaestroColumna = ").append(tipoParametroDinamicoVisualizacion);

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
        log.info("listaRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
        return listaRequisitoProcedimiento;
    }

    //Caso 8 Actividad - Zonificacion - Requisitos
    @Override
    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoActividadZonificacion(Long idProcedimiento, Long idActividad, Long idTramite, Long idZonificacion) {
        log.info("-- RequisitoDAO - listarRequisitosProcedimientoActividadZonificacion --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        log.info("-- parametros idActividad : " + idActividad);
        log.info("-- parametros idTramite : " + idTramite);
        log.info("-- parametros idZonificacion : " + idZonificacion);
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select rp ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " left outer join rp.pghRequProcParaDinaList rppd ");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rp.idTramite.idTramite = ").append(idTramite);
        jpql.append(" and rp.idActividad.idActividad = ").append(idActividad);
        jpql.append(" and rp.idZonificacion.idZonificacion = ").append(idZonificacion);
        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
        jpql.append(" and rppd.idRequProcParaDina is null ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
        jpql.append(" and rp.idRequisitoProcedimientoPad is null ");

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
        log.info("listaRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
        return listaRequisitoProcedimiento;
    }

    //Caso 8 Actividad - Zonificacion - Requisitos
    @Override
    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoActividadZonificacionVisualizacion(Long idProcedimiento, Long idActividad, Long idTramite, Long idZonificacion, Long tipoParametroDinamicoVisualizacion) {
        log.info("-- RequisitoDAO - listarRequisitosProcedimientoActividadZonificacion --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        log.info("-- parametros idActividad : " + idActividad);
        log.info("-- parametros idTramite : " + idTramite);
        log.info("-- parametros idZonificacion : " + idZonificacion);
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select rp ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " inner join rp.pghRequProcParaDinaList rppd "
                + " inner join rppd.idValorParametro vp "
                + " inner join vp.idParametroDinamico pd");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rppd.estado = 1 ");
        jpql.append(" and rp.idTramite.idTramite = ").append(idTramite);
        jpql.append(" and rp.idActividad.idActividad = ").append(idActividad);
        jpql.append(" and rp.idZonificacion.idZonificacion = ").append(idZonificacion);
        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
        jpql.append(" and pd.tipoConsulta.idMaestroColumna = ").append(tipoParametroDinamicoVisualizacion);
        jpql.append(" and rp.idRequisitoProcedimientoPad is null ");

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
        log.info("listaRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
        return listaRequisitoProcedimiento;
    }

    //Caso 7 Tramite - Motivo Tramite
    @Override
    public List<RequisitoProcedimientoDTO> listarTramiteMotivoTramite(Long idProcedimiento, Long idTramite) {
        log.info("-- RequisitoDAO - listarTramiteMotivoTramite --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        log.info("-- parametros idTramite : " + idTramite);
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select rp ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " left outer join rp.pghRequProcParaDinaList rppd ");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rp.idTramite.idTramite = ").append(idTramite);
        jpql.append(" and rp.idActividad.idActividad is null ");
        jpql.append(" and rp.idZonificacion.idZonificacion is null ");
        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is not null ");
        jpql.append(" and rppd.idRequProcParaDina is null ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
        log.info("listaRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
        return listaRequisitoProcedimiento;
    }

    //Caso 7 Tramite - Motivo Tramite
    @Override
    public List<RequisitoProcedimientoDTO> listarTramiteMotivoTramiteVisualizacion(Long idProcedimiento, Long idTramite, Long tipoParametroDinamicoVisualizacion) {
        log.info("-- RequisitoDAO - listarTramiteMotivoTramite --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        log.info("-- parametros idTramite : " + idTramite);
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select rp ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " inner join rp.pghRequProcParaDinaList rppd "
                + " inner join rppd.idValorParametro vp "
                + " inner join vp.idParametroDinamico pd");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rppd.estado = 1 ");
        jpql.append(" and rp.idTramite.idTramite = ").append(idTramite);
        jpql.append(" and rp.idActividad.idActividad is null ");
        jpql.append(" and rp.idZonificacion.idZonificacion is null ");
        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is not null ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
        jpql.append(" and pd.tipoConsulta.idMaestroColumna = ").append(tipoParametroDinamicoVisualizacion);

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
        log.info("listaRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
        return listaRequisitoProcedimiento;
    }

    //Caso 7 Tramite - Motivo Tramite - Requisito
    @Override
    public List<RequisitoProcedimientoDTO> listarRequisitoProcedimientoTramiteMotivoTramite(Long idProcedimiento, Long idTramite, Long idMotivoTramite) {
        log.info("-- RequisitoDAO - listarRequisitoProcedimientoTramiteMotivoTramite --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        log.info("-- parametros idTramite : " + idTramite);
        log.info("-- parametros idMotivoTramite : " + idMotivoTramite);
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select rp ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " left outer join rp.pghRequProcParaDinaList rppd ");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rp.idTramite.idTramite = ").append(idTramite);
        jpql.append(" and rp.idActividad.idActividad is null ");
        jpql.append(" and rp.idZonificacion.idZonificacion is null ");
        jpql.append(" and rp.idMotivoTramite.idMotivoTramite = ").append(idMotivoTramite);;
        jpql.append(" and rppd.idRequProcParaDina is null ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
        jpql.append(" and rp.idRequisitoProcedimientoPad is null ");

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
        log.info("listaRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
        return listaRequisitoProcedimiento;
    }

    //Caso 7 Tramite - Motivo Tramite - Requisito
    @Override
    public List<RequisitoProcedimientoDTO> listarRequisitoProcedimientoTramiteMotivoTramiteVisualizacion(Long idProcedimiento, Long idTramite, Long idMotivoTramite, Long tipoParametroDinamicoVisualizacion) {
        log.info("-- RequisitoDAO - listarRequisitoProcedimientoTramiteMotivoTramiteVisualizacion --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        log.info("-- parametros idTramite : " + idTramite);
        log.info("-- parametros idMotivoTramite : " + idMotivoTramite);
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select rp ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " inner join rp.pghRequProcParaDinaList rppd "
                + " inner join rppd.idValorParametro vp "
                + " inner join vp.idParametroDinamico pd");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rppd.estado = 1 ");
        jpql.append(" and rp.idTramite.idTramite = ").append(idTramite);
        jpql.append(" and rp.idActividad.idActividad is null ");
        jpql.append(" and rp.idZonificacion.idZonificacion is null ");
        jpql.append(" and rp.idMotivoTramite.idMotivoTramite = ").append(idMotivoTramite);;
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
        jpql.append(" and pd.tipoConsulta.idMaestroColumna = ").append(tipoParametroDinamicoVisualizacion);
        jpql.append(" and rp.idRequisitoProcedimientoPad is null ");

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
        log.info("listaRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
        return listaRequisitoProcedimiento;
    }

    @Override
    public List<ValorParametroDTO> listarValorParametros(Long idParametroDinamico) {
        log.info("-- RequisitoDAO - listarValorParametros --");
        log.info("-- parametros idParametroDinamico : " + idParametroDinamico);
        String query = "PghValorParametro.findByIdParametroDinamico";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idParametroDinamico", idParametroDinamico);
        List<PghValorParametro> lstValorParametro = crudDAO.findByNamedQuery(query, parameters);
        List<ValorParametroDTO> listaValorParametro = ValorParametroBuilder.getListaValorParametro(lstValorParametro);
        log.info("listaValorParametro = " + listaValorParametro.size());
        return listaValorParametro;
    }

    @Override
    public List<MotivoTramiteDTO> listarMotivoTramite(Long idTramite) {
        log.info("-- RequisitoDAO - listarMotivoTramite --");
        log.info("-- parametros idTramite : " + idTramite);
        String query = "PghMotivoTramite.findByIdTramite";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idTramite", idTramite);
        List<PghMotivoTramite> lstMotivoTramite = crudDAO.findByNamedQuery(query, parameters);
        List<MotivoTramiteDTO> listaMotivoTramite = MotivoTramiteBuilder.getListaMotivoTramite(lstMotivoTramite);
        log.info("listaMotivoTramite = " + listaMotivoTramite.size());
        return listaMotivoTramite;
    }

    @Override
    public List<ZonificacionDetalleDTO> listarZonificacionDetalle(String idDepartamento, String idProvincia, String idDistrito) {
        log.info("-- RequisitoDAO - listarZonificacionDetalle --");
        log.info("-- parametros idDepartamento : " + idDepartamento);
        log.info("-- parametros idProvincia : " + idProvincia);
        log.info("-- parametros distrito : " + idDistrito);
        List<ZonificacionDetalleDTO> listaZonificacionDetalle = new ArrayList<ZonificacionDetalleDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select zd ");
        jpql.append(" from PghZonificacionDetalle zd  ");
        jpql.append(" where 1=1 ");
        jpql.append(" and zd.estado = 1 ");
        jpql.append(" and zd.idMacroRegion is null ");
        jpql.append(" and zd.idRegion is null ");
        jpql.append(" and zd.mdiUbigeo.mdiUbigeoPK.idDepartamento = '").append(idDepartamento).append("'");
        jpql.append(" and zd.mdiUbigeo.mdiUbigeoPK.idProvincia = '").append(idProvincia).append("'");
        jpql.append(" and zd.mdiUbigeo.mdiUbigeoPK.idDistrito = '").append(idDistrito).append("'");

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghZonificacionDetalle> lstZonificacionDetalle = query.getResultList();
        listaZonificacionDetalle = ZonificacionDetalleBuilder.getListaZonificacionDetalle(lstZonificacionDetalle);
        log.info("listaZonificacionDetalle = " + listaZonificacionDetalle.size());
        return listaZonificacionDetalle;
    }

    @Override
    public RequisitoDTO obtenerRequisito(Long idRequisitoProcedimiento) {
        log.info("-- RequisitoDAO - obtenerRequisito --");
        log.info("-- parametros idRequisitoProcedimiento : " + idRequisitoProcedimiento);
        RequisitoDTO requisitoDTO = new RequisitoDTO();
        StringBuilder jpql = new StringBuilder();
        //jpql.append(" select r ");
        jpql.append(" select new PghRequisito(r.idRequisito,r.descripcion,r.comentarioPredeterminado,r.estado,r.idDocumentoAdjunto.idDocumentoAdjunto,rp.idRequisitoProcedimiento,rp.comentario) ");
        jpql.append(" from PghRequisito r inner join r.pghCnfRequProcedimientoList rp ");
        jpql.append(" where 1=1 ");
        jpql.append(" and r.estado = 1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rp.idRequisitoProcedimiento = ").append(idRequisitoProcedimiento);

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        PghRequisito requisito = (PghRequisito) query.getSingleResult();
        requisitoDTO = RequisitoBuilder.getRequisito(requisito);
        log.info("requisitoDTO = " + requisitoDTO.getIdRequisito());
        return requisitoDTO;
    }

    @Override
    public List<RequisitoProcedimientoDTO> listarRequisitoRequerimiento(Long idRequisitoProcedimiento) {
        log.info("-- RequisitoDAO - listarRequisitoTramiteMotivoTramite --");
        log.info("-- parametros idRequisitoProcedimiento : " + idRequisitoProcedimiento);
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select rp ");
        jpql.append(" from PghCnfRequProcedimiento rp ");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rp.idRequisitoProcedimiento = ").append(idRequisitoProcedimiento);

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghCnfRequProcedimiento> lstRequisitoProcedimiento = query.getResultList();
        listaRequisitoProcedimiento = RequisitoProcedimientoBuilder.getListaRequisitoProcedimientoSubRequisitos(lstRequisitoProcedimiento);
        log.info("lstRequisitoProcedimiento = " + listaRequisitoProcedimiento.size());
        return listaRequisitoProcedimiento;
    }

    @Override
    public List<ValorParametroDTO> listarValorParametrosVisualizacionEtapa(Long idProcedimiento, Long tipoParametroDinamico) {
        log.info("-- RequisitoDAO - listarValorParametrosVisualizacionEtapa --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        log.info("-- parametros tipoParametroDinamico : " + tipoParametroDinamico);
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select distinct vp ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " inner join rp.pghRequProcParaDinaList rppd "
                + " inner join rppd.idValorParametro vp "
                + " inner join vp.idParametroDinamico pd");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rppd.estado = 1 ");
        jpql.append(" and vp.estado = 1 ");
        jpql.append(" and pd.estado = 1 ");
//        jpql.append(" and rp.idTramite.idTramite is null ");
//        jpql.append(" and rp.idActividad.idActividad is null ");
//        jpql.append(" and rp.idZonificacion.idZonificacion is null ");
//        jpql.append(" and rp.idMotivoTramite.idMotivoTramite is null ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
//        jpql.append(" and pd.idParametroDinamico = ").append(idParametroDinamico);
//        jpql.append(" and vp.idValorParametro = ").append(idValorParametroDinamico);
        jpql.append(" and pd.tipoConsulta.idMaestroColumna = ").append(tipoParametroDinamico);
        jpql.append(" order by vp.valor asc ");

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghValorParametro> lstValorParametro = query.getResultList();
        List<ValorParametroDTO> listaValorParametro = ValorParametroBuilder.getListaValorParametro(lstValorParametro);
        log.info("listaValorParametro = " + listaValorParametro.size());
        return listaValorParametro;
    }

    @Override
    public List<ValorParametroDTO> listarValorParametrosRequisitoParametro(Long idProcedimiento, Long idRequisitoProcedimiento, Long tipoParametroDinamico) {
        log.info("-- RequisitoDAO - listarValorParametrosRequisitoParametro --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        log.info("-- parametros idRequisitoProcedimiento : " + idRequisitoProcedimiento);
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select vp ");
        jpql.append(" from PghCnfRequProcedimiento rp "
                + " inner join rp.pghRequProcParaDinaList rppd "
                + " inner join rppd.idValorParametro vp "
                + " inner join vp.idParametroDinamico pd");
        jpql.append(" where 1=1 ");
        jpql.append(" and rp.estado = 1 ");
        jpql.append(" and rppd.estado = 1 ");
        jpql.append(" and vp.estado = 1 ");
        jpql.append(" and pd.estado = 1 ");
        jpql.append(" and rp.idProcedimiento.idProcedimiento = ").append(idProcedimiento);
        jpql.append(" and rp.idRequisitoProcedimiento = ").append(idRequisitoProcedimiento);
        jpql.append(" and pd.tipoConsulta.idMaestroColumna = ").append(tipoParametroDinamico);
        jpql.append(" order by vp.valor asc ");

        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        List<PghValorParametro> lstValorParametro = query.getResultList();
        List<ValorParametroDTO> listaValorParametro = ValorParametroBuilder.getListaValorParametro(lstValorParametro);
        log.info("listaValorParametro = " + listaValorParametro.size());
        return listaValorParametro;
    }

    @Override
    public ProcedimientoDTO obtenerProcedimiento(Long idProcedimiento) {
        log.info("-- RequisitoDAO - obtenerProcedimiento --");
        log.info("-- parametros idProcedimiento : " + idProcedimiento);
        PghProcedimiento procedimiento = crudDAO.find(idProcedimiento, PghProcedimiento.class);
        ProcedimientoDTO procedimientoDTO = ProcedimientoBuilder.getProcedimiento(procedimiento);
        return procedimientoDTO;
    }

    @Override
    public MaestroColumnaDTO obtenerMaestroColumna(Long idMaestroColumna) {
        log.info("-- RequisitoDAO - obtenerMaestroColumna --");
        log.info("-- parametros idMaestroColumna : " + idMaestroColumna);
        MdiMaestroColumna maestroColumna = crudDAO.find(idMaestroColumna, MdiMaestroColumna.class);
        MaestroColumnaDTO maestriColumnaDTO = MaestroColumnaBuilder.getMaestroColumnaDTO(maestroColumna);
        log.info("maestriColumnaDTO = " + maestriColumnaDTO.getIdMaestroColumna());
        return maestriColumnaDTO;
    }

    @Override
    public MaestroColumnaDTO obtenerMaestroColumna(String aplicacion, String dominio, String codigo) {
        log.info("-- RequisitoDAO - obtenerMaestroColumna --");
        log.info("-- parametros aplicacion : " + aplicacion);
        log.info("-- parametros dominio : " + dominio);
        log.info("-- parametros codigo : " + codigo);
        String query = "MdiMaestroColumna.findByCodigoDominio";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("dominio", dominio);
        parameters.put("aplicacion", aplicacion);
        parameters.put("codigo", codigo);
        List<MdiMaestroColumna> lstMaestroColumna = crudDAO.findByNamedQuery(query, parameters);
        MdiMaestroColumna maestroColumna = lstMaestroColumna.get(0);
        MaestroColumnaDTO maestriColumnaDTO = MaestroColumnaBuilder.getMaestroColumnaDTO(maestroColumna);
        log.info("maestriColumnaDTO = " + maestriColumnaDTO.getIdMaestroColumna());
        return maestriColumnaDTO;
    }

    @Override
    public List<DepartamentoDTO> listarDepartamentos() {
        log.info("-- RequisitoDAO - listarDepartamentos --");
        List<MdiUbigeo> listaUbigeo = crudDAO.findByNamedQuery("MdiUbigeo.findDepartamento");
        List<DepartamentoDTO> listaDepartamento = UbigeoBuilder.getListaDepartamento(listaUbigeo);
        return listaDepartamento;
    }

    @Override
    public List<ProvinciaDTO> listarProvincias(String idDepartamento) {
        log.info("-- RequisitoDAO - listarProvincias --");
        log.info("-- parametros idDepartamento : " + idDepartamento);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idDepartamento", idDepartamento);
        List<MdiUbigeo> listaUbigeo = crudDAO.findByNamedQuery("MdiUbigeo.findProvinciaByIdDepartamento", parameters);;
        List<ProvinciaDTO> listaProvincia = UbigeoBuilder.getListaProvincia(listaUbigeo);
        return listaProvincia;
    }

    @Override
    public List<DistritoDTO> listarDistritos(String idDepartamento, String idProvincia) {
        log.info("-- RequisitoDAO - listarDistritos --");
        log.info("-- parametros idDepartamento : " + idDepartamento);
        log.info("-- parametros idProvincia : " + idProvincia);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idDepartamento", idDepartamento);
        parameters.put("idProvincia", idProvincia);
        List<MdiUbigeo> listaUbigeo = crudDAO.findByNamedQuery("MdiUbigeo.findDistritoByParametros", parameters);
        List<DistritoDTO> listaDistrito = UbigeoBuilder.getListaDistrito(listaUbigeo);
        return listaDistrito;
    }

    @Override
    public ActividadDTO obtenerActividad(Long idActividad) {
        log.info("-- RequisitoDAO - obtenerActividad --");
        log.info("-- parametros idActividad : " + idActividad);
        MdiActividad actividad = crudDAO.find(idActividad, MdiActividad.class);
        ActividadDTO actividadDTO = ActividadBuilder.getActividad(actividad);
        return actividadDTO;
    }

    @Override
    public TramiteDTO obtenerTramite(Long idTramite) {
        log.info("-- RequisitoDAO - obtenerTramite --");
        log.info("-- parametros idTramite : " + idTramite);
        PghTramite tramite = crudDAO.find(idTramite, PghTramite.class);
        TramiteDTO tramiteDTO = TramiteBuilder.getTramite(tramite);
        return tramiteDTO;
    }

    @Override
    public EtapaDTO obtenerEtapa(Long idEtapa) {
        log.info("-- RequisitoDAO - obtenerEtapa --");
        log.info("-- parametros idEtapa : " + idEtapa);
        PghEtapa etapa = crudDAO.find(idEtapa, PghEtapa.class);
        EtapaDTO etapaDTO = EtapaBuilder.getEtapa(etapa);
        return etapaDTO;
    }

    @Override
    public ParametroDinamicoDTO obtenerParametroDinamico(Long idParametroDinamico) {
        log.info("-- RequisitoDAO - obtenerParametroDinamico --");
        log.info("-- parametros idParametroDinamico : " + idParametroDinamico);
        PghParametroDinamico parametroDinamico = crudDAO.find(idParametroDinamico, PghParametroDinamico.class);
        ParametroDinamicoDTO parametroDinamicoDTO = ParametroDinamicoBuilder.getParametroDinamico(parametroDinamico);
        return parametroDinamicoDTO;
    }

    @Override
    public ValorParametroDTO obtenerValorParametroDinamico(Long idValorParametroDinamico) {
        log.info("-- RequisitoDAO - obtenerValorParametroDinamico --");
        log.info("-- parametros idValorParametroDinamico : " + idValorParametroDinamico);
        PghValorParametro valorParametro = crudDAO.find(idValorParametroDinamico, PghValorParametro.class);
        ValorParametroDTO valorParametroDTO = ValorParametroBuilder.getValorParametro(valorParametro);
        return valorParametroDTO;
    }

    @Override
    public MotivoTramiteDTO obtenerMotivoTramite(Long idMotivoTramite) {
        log.info("-- RequisitoDAO - obtenerMotivoTramite --");
        log.info("-- parametros idMotivoTramite : " + idMotivoTramite);
        PghMotivoTramite motivoTramite = crudDAO.find(idMotivoTramite, PghMotivoTramite.class);
        MotivoTramiteDTO motivoTramiteDTO = MotivoTramiteBuilder.getMotivoTramite(motivoTramite);
        return motivoTramiteDTO;
    }

    @Override
    public UbigeoDTO obtenerUbigeo(String idDepartamento, String idProvincia, String idDistrito) {
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select u ");
        jpql.append(" FROM MdiUbigeo u ");
        jpql.append(" where ");
        jpql.append(" u.mdiUbigeoPK.idDepartamento = '").append(idDepartamento).append("'");
        jpql.append(" and u.mdiUbigeoPK.idProvincia = '").append(idProvincia).append("'");
        jpql.append(" and u.mdiUbigeoPK.idDistrito = '").append(idDistrito).append("'");
        String queryString = jpql.toString();
        Query query = crudDAO.getEm().createQuery(queryString);
        MdiUbigeo ubigeo = (MdiUbigeo) query.getSingleResult();
        UbigeoDTO ubigeoDTO = UbigeoBuilder.getUbigeo(ubigeo);
        return ubigeoDTO;
    }
    
    @Override
    public DocumentoAdjuntoDTO obtenerDocumentoAdjunto(Long idDocumentoAdjunto) {
        log.info("-- RequisitoDAO - obtenerDocumentoAdjunto --");
        log.info("-- parametros idDocumentoAdjunto : " + idDocumentoAdjunto);
        MdiDocumentoAdjunto documentoAdjunto = crudDAO.find(idDocumentoAdjunto, MdiDocumentoAdjunto.class);
        DocumentoAdjuntoDTO documentoAdjuntoDTO = DocumentoAdjuntoBuilder.getDocumentoAdjunto(documentoAdjunto);
        return documentoAdjuntoDTO;
    }
}
