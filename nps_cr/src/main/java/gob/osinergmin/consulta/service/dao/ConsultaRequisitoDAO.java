package gob.osinergmin.consulta.service.dao;

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

import java.util.List;

public interface ConsultaRequisitoDAO {

    public List<ActividadDTO> listarActividadesPadre();
    public List<ActividadDTO> listarActividades(ActividadDTO actividad);
    public List<EtapaDTO> listarEtapas(Long idActividad);
    public List<TramiteDTO> listarTramites(Long idActividad, Long idEtapa);
    public List<ProcedimientoDTO> listarProcedimiento(Long idActividad, Long idTramite);
    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoGenericos(Long idProcedimiento);
    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoGenericosVisualizacion(Long idProcedimiento, Long tipoParametroDinamicoVisualizacion);
    
    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoTramite(Long idProcedimiento, Long idTramite);
    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoTramiteVisualizacion(Long idProcedimiento, Long idTramite, Long tipoParametroDinamico);
    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoTramiteActividad(Long idProcedimiento, Long idTramite, Long idActividad);
    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoTramiteActividadVisualizacion(Long idProcedimiento, Long idTramite, Long idActividad, Long tipoParametroDinamico);
    
    public List<RequisitoProcedimientoDTO> listarZonificacion(Long idProcedimiento);
    public List<RequisitoProcedimientoDTO> listarZonificacionVisualizacion(Long idProcedimiento, Long tipoParametroDinamicoVisualizacion);
    
    public List<RequisitoProcedimientoDTO> listarRequisitoProcedimientoZonificacion(Long idProcedimiento, Long idZonificacionDetalle);
    public List<RequisitoProcedimientoDTO> listarRequisitoProcedimientoZonificacionVisualizacion(Long idProcedimiento, Long idZonificacionDetalle, Long tipoParametroDinamicoVisualizacion);
    
    public List<RequisitoProcedimientoDTO> listarActividadZonificacion(Long idProcedimiento, Long idActividad, Long idTramite);
    public List<RequisitoProcedimientoDTO> listarActividadZonificacionVisualizacion(Long idProcedimiento, Long idActividad, Long idTramite, Long tipoParametroDinamicoVisualizacion);
    
    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoActividadZonificacion(Long idProcedimiento, Long idActividad, Long idTramite, Long idZonificacionDetalle);
    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoActividadZonificacionVisualizacion(Long idProcedimiento, Long idActividad, Long idTramite, Long idZonificacionDetalle, Long tipoParametroDinamicoVisualizacion);
    
    public List<RequisitoProcedimientoDTO> listarTramiteMotivoTramite(Long idProcedimiento, Long idTramite);
    public List<RequisitoProcedimientoDTO> listarTramiteMotivoTramiteVisualizacion(Long idProcedimiento, Long idTramite, Long tipoParametroDinamicoVisualizacion);
    
    public List<RequisitoProcedimientoDTO> listarRequisitoProcedimientoTramiteMotivoTramite(Long idProcedimiento, Long idTramite, Long idMotivoTramite);
    public List<RequisitoProcedimientoDTO> listarRequisitoProcedimientoTramiteMotivoTramiteVisualizacion(Long idProcedimiento, Long idTramite, Long idMotivoTramite, Long tipoParametroDinamicoVisualizacion);

    public List<ValorParametroDTO> listarValorParametros(Long idParametroDinamico);
    public List<MotivoTramiteDTO> listarMotivoTramite(Long idTramite);
    public List<ZonificacionDetalleDTO> listarZonificacionDetalle(String idDepartamento, String idProvincia, String idDistrito);
    
    public List<ParametroDinamicoDTO> listarPreguntasParametrosDinamicos(Long idProcedimiento, Long tipoParametroDinamico);
//    public List<ParametroDinamicoDTO> listarPreguntasParametrosDinamicosVisualizacion(Long idProcedimiento, Long tipoParametroDinamico, Long tipoParametroDinamicoVisualizacion);
    
    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoParametroDinamico(Long idProcedimiento, Long idParametroDinamico, Long idValorParametroDinamico);
//    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoParametroDinamicoVisualizacion(Long idProcedimiento, Long idParametroDinamico, Long idValorParametroDinamico, Long tipoParametroDinamicoVisualizacion);

    public List<ParametroDinamicoDTO> listarTramiteParametrosDinamicos(Long idProcedimiento, Long idTramite, Long tipoParametroDinamico);
//    public List<ParametroDinamicoDTO> listarTramiteParametrosDinamicosVisualizacion(Long idProcedimiento, Long idTramite, Long tipoParametroDinamico, Long tipoParametroDinamicoVisualizacion);
    
    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoTramiteParametrosDinamicos(Long idProcedimiento, Long idTramite, Long idParametroDinamico, Long idValorParametroDinamico);
//    public List<RequisitoProcedimientoDTO> listarRequisitosProcedimientoTramiteParametrosDinamicosVisualizacion(Long idProcedimiento, Long idTramite, Long idParametroDinamico, Long idValorParametroDinamico, Long tipoParametroDinamicoVisualizacion);
    
    public RequisitoDTO obtenerRequisito(Long idRequisitoProcedimiento);
    public List<RequisitoProcedimientoDTO> listarRequisitoRequerimiento(Long idRequisitoProcedimiento);
    
    public List<ValorParametroDTO> listarValorParametrosVisualizacionEtapa(Long idProcedimiento, Long tipoParametroDinamico);
    public List<ValorParametroDTO> listarValorParametrosRequisitoParametro(Long idProcedimiento, Long idRequisitoProcedimiento, Long tipoParametroDinamico);
    
    public ProcedimientoDTO obtenerProcedimiento(Long idProcedimiento);
    
    public MaestroColumnaDTO obtenerMaestroColumna(Long idMaestroColumna);
    public MaestroColumnaDTO obtenerMaestroColumna(String aplicacion, String dominio, String codigo);
    
    public List<DepartamentoDTO> listarDepartamentos();
    public List<ProvinciaDTO> listarProvincias(String idDepartamento);
    public List<DistritoDTO> listarDistritos(String idDepartamento, String idProvincia);
    
    public ActividadDTO obtenerActividad(Long idActividad);
    public TramiteDTO obtenerTramite(Long idTramite);
    public EtapaDTO obtenerEtapa(Long idEtapa);
    public ParametroDinamicoDTO obtenerParametroDinamico(Long idParametroDinamico);
    public ValorParametroDTO obtenerValorParametroDinamico(Long idValorParametroDinamico);
    public MotivoTramiteDTO obtenerMotivoTramite(Long idMotivoTramite);
    public UbigeoDTO obtenerUbigeo(String idDepartamento, String idProvincia, String idDistrito);
    public DocumentoAdjuntoDTO obtenerDocumentoAdjunto(Long idDocumentoAdjunto);
    
}
