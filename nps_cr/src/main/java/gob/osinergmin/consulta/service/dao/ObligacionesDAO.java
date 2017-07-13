package gob.osinergmin.consulta.service.dao;

import gob.osinergmin.consulta.domain.PghContadorVisita;
import gob.osinergmin.consulta.dto.AutoayudaDTO;
import gob.osinergmin.consulta.dto.MaestroColumnaDTO;
import gob.osinergmin.consulta.dto.ObligacionTipoDTO;
import gob.osinergmin.consulta.dto.ObligacionesDTO;
import gob.osinergmin.consulta.dto.ReporteObligacionesDTO;
import gob.osinergmin.consulta.dto.SugerenciaDTO;

import java.util.List;
import java.util.Map;

public interface ObligacionesDAO {

	public List<ObligacionTipoDTO> listarObligacionesTipo(Long idActividad);

	public List<ObligacionesDTO> listarObligaciones(Long rubro,
			Long obligacionTipo, Long clasificacion, Long criticidad);

	public boolean registrarSugerencia(SugerenciaDTO sugerenciaDTO);

	public void registrarContador(Long idTipoVisita, Long contador);

	public PghContadorVisita consultarContador(Long idTipoVisita);

	public Map obtenerTipoUsuario(String login);

	public Map obtenerActividadYRubro(String login);
	public AutoayudaDTO obtenerAutoayuda(Long idAutoayuda);
	/*SRAMOS 11082015*/
	//public List<ReporteObligacionesDTO> consultaReporteWebObligaciones(Long rubro,
	//		Long obligacionTipo, Long clasificacion, Long criticidad);
	// lchancayauri
	public Object[] consultaReporteWebObligaciones(Long rubro,			// lchancayauri
			Long obligacionTipo, Long clasificacion, Long criticidad);  // lchancayauri

	public List<MaestroColumnaDTO> listarClasificacionFiltro(Long idActividad,Long idObligacionTipo);

}
