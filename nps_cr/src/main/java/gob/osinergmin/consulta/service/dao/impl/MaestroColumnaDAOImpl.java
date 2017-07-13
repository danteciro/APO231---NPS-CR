package gob.osinergmin.consulta.service.dao.impl;
/**
*
* @author l_garcia_r
*/
import gob.osinergmin.consulta.domain.MdiMaestroColumna;
import gob.osinergmin.consulta.domain.builder.MaestroColumnaBuilder;
import gob.osinergmin.consulta.domain.ui.MaestroColumnaFilter;
import gob.osinergmin.consulta.dto.MaestroColumnaDTO;
import gob.osinergmin.consulta.service.dao.CrudDAO;
import gob.osinergmin.consulta.service.dao.MaestroColumnaDAO;
import gob.osinergmin.consulta.util.Constantes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("maestroColumnaDAO")
public class MaestroColumnaDAOImpl implements MaestroColumnaDAO{
	
	private static final Logger LOG = LoggerFactory.getLogger(MaestroColumnaDAOImpl.class);
    @Inject
    private CrudDAO crud;
		
        @Override
        @Transactional(readOnly=true)
        public List<MaestroColumnaDTO> findMaestroColumna(String dominio, String aplicacion){
            LOG.info("findMaestroColumna - MaestroColumnaDAOImpl");
            List<MaestroColumnaDTO> listaMaestroColumnaDTO = null;
            try {                	
                    List<MdiMaestroColumna> listaMaestroColumna = null;
                    Map<String, Object> parameters = new HashMap<String, Object>();
                    parameters.put("dominio", dominio);
                    parameters.put("aplicacion", aplicacion);
                    parameters.put("estado", Constantes.ACTIVO);
                    listaMaestroColumna = crud.findByNamedQuery("MdiMaestroColumna.findByDominio", parameters);       	
                    listaMaestroColumnaDTO = MaestroColumnaBuilder.getMaestroColumnaDTOList(listaMaestroColumna);
            } catch (Exception e) {  
                    LOG.error("error", e);
            }
            return listaMaestroColumnaDTO;
        }

		@Override
		@Transactional(readOnly=true)
		public Long obtenerIdMaestroColumna(String dominio, String aplicacion) {
			List<MaestroColumnaDTO> listaMaestroColumnaDTO = findMaestroColumna(dominio, aplicacion);
			Long idMaestroColumna = (listaMaestroColumnaDTO.get(0)).getIdMaestroColumna();
			return idMaestroColumna;
		}

		@Override
		public List<MaestroColumnaDTO> findValorUit(MaestroColumnaFilter filtro) {

			List<MaestroColumnaDTO> listado=null;
			
			StringBuilder jpql = new StringBuilder();
			
			jpql.append("select mc.id_maestro_columna, mc.dominio, mc.descripcion "); 
			jpql.append("from mdi_maestro_columna mc ");
			jpql.append("where mc.id_maestro_columna=167 ");
						
				if(filtro.getIdMaestroColumna()!=null){
					jpql.append("where p.id_maestro_columna =:idMaestroColumna ");
				}
				
				Query query = crud.getEm().createNativeQuery(jpql.toString());
		        if(filtro.getIdMaestroColumna()!=null){
		        	query.setParameter("idMaestroColumna", filtro.getIdMaestroColumna());
		        }
		        
		        LOG.info("query findValorUit: " + jpql.toString());
		        
		        List<Object[]>  resultados = query.getResultList();   
		        System.out.println(" Lista de Resultados "+resultados);
		        
		        listado=MaestroColumnaBuilder.toListValorUitRefDto(resultados);
				
			return listado;
				
		}
        
}
