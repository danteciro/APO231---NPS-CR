package gob.osinergmin.consulta.domain.builder;

import gob.osinergmin.consulta.domain.MdiActividad;
import gob.osinergmin.consulta.domain.PghAutoayuda;
import gob.osinergmin.consulta.dto.ActividadDTO;
import gob.osinergmin.consulta.dto.AutoayudaDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class AutoayudaBuilder {
    
	public static List<AutoayudaDTO> getListaAutoayuda(List<PghAutoayuda> listaAutoayuda) {
        List<AutoayudaDTO> listaAutoayudaDTO = new ArrayList<AutoayudaDTO>();
        if (!CollectionUtils.isEmpty(listaAutoayuda)) {
            AutoayudaDTO actividadDTO = new AutoayudaDTO();
            for (PghAutoayuda autoayuda : listaAutoayuda) {
                actividadDTO = getAutoayuda(autoayuda);
                listaAutoayudaDTO.add(actividadDTO);
            }
        }
        return listaAutoayudaDTO;
    }
	public static AutoayudaDTO getAutoayuda(PghAutoayuda autoayuda) {
        AutoayudaDTO autoayudaDTO = new AutoayudaDTO();
        if (autoayuda != null) {
            if (autoayuda.getIdAutoayuda() != null) {
                autoayudaDTO.setIdAutoayuda(autoayuda.getIdAutoayuda());
            }
            autoayudaDTO.setIdAutoayuda(autoayuda.getIdAutoayuda());
            autoayudaDTO.setNombre(autoayuda.getNombreAutoayuda());
            autoayudaDTO.setDescripcion(autoayuda.getDescripcionAutoayuda());
        }
        return autoayudaDTO;
    }
}
