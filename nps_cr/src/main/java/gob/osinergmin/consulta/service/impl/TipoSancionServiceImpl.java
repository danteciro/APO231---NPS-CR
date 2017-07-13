package gob.osinergmin.consulta.service.impl;

/**
*
* @author l_garcia_r
*/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import gob.osinergmin.consulta.controller.TipoSancionController;
import gob.osinergmin.consulta.domain.ui.TipoSancionFilter;
import gob.osinergmin.consulta.dto.TipoSancionDTO;
import gob.osinergmin.consulta.service.TipoSancionServiceNeg;
import gob.osinergmin.consulta.service.dao.TipoSancionDAO;

import java.util.List;

import javax.inject.Inject;

@Service("tipoSancionServiceNeg")
public class TipoSancionServiceImpl implements TipoSancionServiceNeg{
	
	@Inject
	private TipoSancionDAO tipoSancionDAO;
	
	private static final Logger LOG = LoggerFactory.getLogger(TipoSancionController.class);
	
	@Override
	public List<TipoSancionDTO> findTipoSancion(TipoSancionFilter filtro) {
		LOG.info("Funcion: Listar TipoSancion -- Service Impl -- Clase: findTipoSancion");
		List<TipoSancionDTO> listado=null;
        try{
        	listado = tipoSancionDAO.findTipoSancion(filtro);             	
        }catch(Exception ex){
            LOG.error("",ex);
        }
        return listado;		
	}

}
