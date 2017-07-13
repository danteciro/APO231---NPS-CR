package gob.osinergmin.consulta.service.dao.impl;

/**
*
* @author l_garcia_r
*/

import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;

import gob.osinergmin.consulta.controller.TipoSancionController;
import gob.osinergmin.consulta.domain.builder.TipoSancionBuilder;
import gob.osinergmin.consulta.domain.ui.TipoSancionFilter;
import gob.osinergmin.consulta.dto.TipoSancionDTO;
import gob.osinergmin.consulta.service.dao.CrudDAO;
import gob.osinergmin.consulta.service.dao.TipoSancionDAO;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("TipoSancionDAO")
public class TipoSancionDAOImpl implements TipoSancionDAO{
	
	@Inject
    private CrudDAO crud;
	
	private static final Logger LOG = LoggerFactory.getLogger(TipoSancionController.class);
	@Override
	public List<TipoSancionDTO> findTipoSancion(TipoSancionFilter filtro) {

		List<TipoSancionDTO> listado=null;
		
		StringBuilder jpql = new StringBuilder();
		
			jpql.append("SELECT ts.id_tipo_sancion, ts.descripcion, ts.abreviatura, CONCAT(CONCAT(ts.abreviatura, ' : '),ts.descripcion) as abreviaturaDescripcion ");
			jpql.append("from pgh_tipo_sancion ts ");
					
			if(filtro.getIdTipoSancion()!=null){
				jpql.append("where p.id_tipoSancion =:idTipoSancion ");
			}
			
			Query query = crud.getEm().createNativeQuery(jpql.toString());
	        if(filtro.getIdTipoSancion()!=null){
	        	query.setParameter("idTipoSancion", filtro.getIdTipoSancion());
	        }
	        
	        LOG.info("query findTipoSancion: " + jpql.toString());
	        
	        List<Object[]>  resultados = query.getResultList();   
	        System.out.println(" Lista de Resultados "+resultados);
	        
	        listado=TipoSancionBuilder.toListTipoSancionRefDto(resultados);
			
		return listado;
			
	}
	
}