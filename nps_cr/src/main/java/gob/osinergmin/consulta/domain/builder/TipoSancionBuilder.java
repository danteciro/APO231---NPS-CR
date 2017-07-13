package gob.osinergmin.consulta.domain.builder;

import gob.osinergmin.consulta.dto.TipoSancionDTO;

import java.util.ArrayList;
import java.util.List;

public class TipoSancionBuilder {

	public static List<TipoSancionDTO> toListTipoSancionRefDto(List<Object[]> lista) {

		List<TipoSancionDTO> retorno = new ArrayList<TipoSancionDTO>();
		if (lista != null) {
			TipoSancionDTO registroDTO;
            for (Object[] maestro : lista) {
            	registroDTO = new TipoSancionDTO();
            	if(maestro[0]!=null){
            		registroDTO.setIdTipoSancion(new Long(maestro[0].toString())); 
            	} 
            	if(maestro[1]!=null){
            		registroDTO.setDescripcion(maestro[1].toString());
            	}             	
            	if(maestro[2]!=null){
            		registroDTO.setAbreviatura(maestro[2].toString());
            	}	
            	if(maestro[3]!=null){
            		registroDTO.setAbreviaturaDescripcion(maestro[3].toString());
            	}	
                retorno.add(registroDTO);
            }
		}
		return retorno;
	
	}

}
