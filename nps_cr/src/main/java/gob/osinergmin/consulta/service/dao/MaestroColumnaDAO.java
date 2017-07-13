package gob.osinergmin.consulta.service.dao;

import gob.osinergmin.consulta.domain.ui.MaestroColumnaFilter;
import gob.osinergmin.consulta.dto.MaestroColumnaDTO;

import java.util.List;

public interface MaestroColumnaDAO {

	public List<MaestroColumnaDTO> findMaestroColumna(String dominio,
			String aplicacion);

	public Long obtenerIdMaestroColumna(String dominio, String aplicacion);

	public List<MaestroColumnaDTO> findValorUit(MaestroColumnaFilter filtro);
}
