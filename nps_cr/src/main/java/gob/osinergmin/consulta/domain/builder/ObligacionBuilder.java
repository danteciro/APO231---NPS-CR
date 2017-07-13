package gob.osinergmin.consulta.domain.builder;

import gob.osinergmin.consulta.dto.ObligacionesDTO;
import gob.osinergmin.consulta.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;


/**
 * 
 * @author cflorian
 *
 */
public class ObligacionBuilder {
	
	public static List<ObligacionesDTO> getListaOlibacion(List<Object[]> listaObligaciones) {
        List<ObligacionesDTO> listaObligacionesDTO = new ArrayList<ObligacionesDTO>();
        if (!CollectionUtils.isEmpty(listaObligaciones)) {
        	ObligacionesDTO obligacionesDTO = new ObligacionesDTO();
            for (Object[] objObligacion : listaObligaciones) {
            	obligacionesDTO = getObligacion(objObligacion);
            	listaObligacionesDTO.add(obligacionesDTO);
            }
        }
        return listaObligacionesDTO;
    }
	
	public static ObligacionesDTO getObligacion(Object[] objObligacion) {
		ObligacionesDTO obligacionesDTO = new ObligacionesDTO();
        if (objObligacion != null) {
        	
        	obligacionesDTO.setDescripcion(StringUtil.nullToBlank(objObligacion[0]));
        	obligacionesDTO.setDescripcionBaseLegal(StringUtil.nullToBlank(objObligacion[1]));        	
        	obligacionesDTO.setTipificacion(StringUtil.nullToBlank(objObligacion[2]));
        	obligacionesDTO.setMonto(StringUtil.nullToBlank(objObligacion[3]));
        	obligacionesDTO.setDescripcionOligacionTipo(StringUtil.nullToBlank(objObligacion[4]));
        	obligacionesDTO.setIdActividad(new Long(StringUtil.nullToBlank(objObligacion[5])));
        	obligacionesDTO.setIdObligacion(new Long(StringUtil.nullToBlank(objObligacion[6])));
        	obligacionesDTO.setIdSupervision(new Long(StringUtil.nullToBlank(objObligacion[7])));
        	obligacionesDTO.setBaseLegal(StringUtil.nullToBlank(objObligacion[8]));
        	obligacionesDTO.setNombreDocAdjunto(StringUtil.nullToBlank(objObligacion[9]));
        	if(objObligacion.length > 10)
        		obligacionesDTO.setIdClasificacion(new Long(StringUtil.nullToBlank(objObligacion[10])));
        }
        return obligacionesDTO;
    }

}
