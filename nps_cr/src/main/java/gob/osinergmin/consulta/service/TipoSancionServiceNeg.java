/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.service;

import gob.osinergmin.consulta.domain.ui.TipoSancionFilter;
import gob.osinergmin.consulta.dto.TipoSancionDTO;

import java.util.List;

/**
 *
 * @author l_garcia_r
 */
public interface TipoSancionServiceNeg {

	public List<TipoSancionDTO> findTipoSancion(TipoSancionFilter filtro);
    
}