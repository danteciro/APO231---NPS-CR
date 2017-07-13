package gob.osinergmin.consulta.service;

/**
*
* @author l_garcia_r
*/


import gob.osinergmin.consulta.domain.ui.MaestroColumnaFilter;
import gob.osinergmin.consulta.dto.MaestroColumnaDTO;

import java.util.List;

public interface MaestroColumnaService {

	public Long obtenerIdMaestroColumna(String dominio, String aplicacion);

	public List<MaestroColumnaDTO> findValorUit(MaestroColumnaFilter filtro);

}
