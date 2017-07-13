/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.service.dao;

/**
*
* @author l_garcia_r
*/

import gob.osinergmin.consulta.domain.ui.TipoSancionFilter;
import gob.osinergmin.consulta.dto.TipoSancionDTO;

import java.util.List;

public interface TipoSancionDAO {

	List<TipoSancionDTO> findTipoSancion(TipoSancionFilter filtro);
   
}