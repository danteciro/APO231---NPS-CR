package gob.osinergmin.consulta.service.impl;
/**
*
* @author l_garcia_r
*/
import java.util.List;

import gob.osinergmin.consulta.domain.ui.MaestroColumnaFilter;
import gob.osinergmin.consulta.dto.MaestroColumnaDTO;
import gob.osinergmin.consulta.service.MaestroColumnaService;
import gob.osinergmin.consulta.service.dao.MaestroColumnaDAO;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("MaestroColumnaService")
public class MaestroColumnaServiceImpl  implements MaestroColumnaService{

	private static final Logger log = LoggerFactory.getLogger(MaestroColumnaServiceImpl.class);
	@Inject
    private MaestroColumnaDAO maestroColumnaDAO;
    
	@Override
	public Long obtenerIdMaestroColumna(String dominio, String aplicacion) {
		log.info("obtenerIdMaestroColumna - MaestroColumnaServiceImpl");
        return maestroColumnaDAO.obtenerIdMaestroColumna(dominio, aplicacion);
	}

	@Override
	public List<MaestroColumnaDTO> findValorUit(MaestroColumnaFilter filtro) {
		log.info("Funcion: Buscar ValorUit -- Service Impl -- Clase: findValorUit");
		List<MaestroColumnaDTO> listado=null;
        try{
        	listado = maestroColumnaDAO.findValorUit(filtro);             	
        }catch(Exception ex){
            log.error("",ex);
        }
        return listado;		
	}

}
