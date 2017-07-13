package gob.osinergmin.consulta.service;

import gob.osinergmin.consulta.domain.PghContadorVisita;
import gob.osinergmin.consulta.dto.AutoayudaDTO;
import gob.osinergmin.consulta.dto.MaestroColumnaDTO;
import gob.osinergmin.consulta.dto.ObligacionTipoDTO;
import gob.osinergmin.consulta.dto.ObligacionesDTO;
import gob.osinergmin.consulta.dto.ReporteObligacionesDTO;
import gob.osinergmin.consulta.dto.SugerenciaDTO;

import java.util.List;
import java.util.Map;


public interface ObligacionesService {

	public List<ObligacionTipoDTO> listarObligacionesTipo(Long idActividad);
	public List<MaestroColumnaDTO> listarClasificacion(String dominio, String aplicacion);
	public List<ObligacionesDTO> listarObligaciones(Long rubro, Long obligacionTipo, Long clasificacion, Long criticidad);	
	public boolean registrarSugerencia(SugerenciaDTO sugerenciaDTO);
	public void registrarContador(Long idTipoVisita, Long contador);
	public PghContadorVisita consultarContador(Long idTipoVisita);
	public Map obtenerTipoUsuario(String login);
	public Map obtenerActividadYRubro(String login);
	public AutoayudaDTO obtenerAutoayuda(Long idAutoayuda);
	/*SRAMOS 08112015*/
	//public List<ReporteObligacionesDTO> consultaReporteWeb(Long rubro,
	//		Long obligacionTipo, Long clasificacion, Long criticidad);
	public Object[] consultaReporteWeb(Long rubro,						//lchancayauri
			Long obligacionTipo, Long clasificacion, Long criticidad);	//lchancayauri
	public List<MaestroColumnaDTO> listarClasificacionFiltro(Long idActividad,Long idObligacionTipo);
	/* OSINE_SFS-600 - REQF-0015 - Inicio */
	/**
	 * Permite descargar un archivo adjunto
	 * @param idDocumentoAdjunto id documento adjunto
	 * @return archivo descargado
	 */
	public Map<String, Object> descargarDatosAlfresco(Long idDocumentoAdjunto) ;
	/* OSINE_SFS-600 - REQF-0015 - Fin */
}
