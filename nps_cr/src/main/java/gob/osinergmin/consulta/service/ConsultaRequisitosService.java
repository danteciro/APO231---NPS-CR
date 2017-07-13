package gob.osinergmin.consulta.service;

import gob.osinergmin.consulta.dto.ActividadDTO;
import gob.osinergmin.consulta.dto.DepartamentoDTO;
import gob.osinergmin.consulta.dto.DistritoDTO;
import gob.osinergmin.consulta.dto.EtapaDTO;
import gob.osinergmin.consulta.dto.PreguntaRequisitoDTO;
import gob.osinergmin.consulta.dto.ProvinciaDTO;
import gob.osinergmin.consulta.dto.TramiteDTO;

import java.util.List;
import java.util.Map;

public interface ConsultaRequisitosService {

    public List<ActividadDTO> listarActitividades();
    public List<ActividadDTO> listarRubros(Long idActividadPadre);
    public List<EtapaDTO> listarEtapas(Long idActividad);
    public List<TramiteDTO> listarTramites(Long idActividad, Long idEtapa);
    public Map<String,Object> listarPreguntasEspecificas(Long idActividad, Long idTramite);
    public Map<String,Object> listarRequisitos(Long idActividad, Long idTramite, Long idProcedimiento,List<PreguntaRequisitoDTO> listaPreguntasEspecificas);
    
    public List<DepartamentoDTO> obtenerDepartamentos();
    public List<ProvinciaDTO> obtenerProvincias(String idDepartamento);
    public List<DistritoDTO> obtenerDistritos(String idDepartamento, String idProvincia);
    
    public Map<String, Object> obtenerDatosReporte(Long idActividad, Long idTramite, Long idProcedimiento, List<PreguntaRequisitoDTO> listaPreguntasEspecificas);
    public Map<String, Object> descargarDatosAlfresco(Long idDocumentoAdjunto);
    public String obtenerValorUIT();
}
