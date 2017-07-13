package gob.osinergmin.consulta.domain.builder;

import gob.osinergmin.consulta.dto.ObligacionTipoDTO;
import gob.osinergmin.consulta.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;


/**
 * 
 * @author cflorian
 *
 */
public class ObligacionTipoBuilder {
	
	public static List<ObligacionTipoDTO> getListaOlibacionTipo(List<Object[]> listaObligacionTipo) {
        List<ObligacionTipoDTO> listaObligacionTipoDTO = new ArrayList<ObligacionTipoDTO>();
        if (!CollectionUtils.isEmpty(listaObligacionTipo)) {
        	ObligacionTipoDTO obligacionTipoDTO = new ObligacionTipoDTO();
            for (Object[] objObligacionTipo : listaObligacionTipo) {
            	obligacionTipoDTO = getObligacionTipo(objObligacionTipo);
            	listaObligacionTipoDTO.add(obligacionTipoDTO);
            }
        }
        return listaObligacionTipoDTO;
    }
	
	public static ObligacionTipoDTO getObligacionTipo(Object[] objObligacionTipo) {
		ObligacionTipoDTO obligacionTipoDTO = new ObligacionTipoDTO();
        if (objObligacionTipo != null) {
            	obligacionTipoDTO.setIdObligacionTipo(new Long(StringUtil.nullToBlank(objObligacionTipo[0])));
            	obligacionTipoDTO.setNombre(StringUtil.nullToBlank(objObligacionTipo[1]));
        }
        return obligacionTipoDTO;
    }

}
