/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain.builder;

import gob.osinergmin.consulta.domain.MdiActividad;
import gob.osinergmin.consulta.dto.ActividadDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 *
 * @author lchancayauri
 */
public class ActividadBuilder {

    public static List<ActividadDTO> getListaActividad(List<MdiActividad> listaActividad) {
        List<ActividadDTO> listaActividadDTO = new ArrayList<ActividadDTO>();
        if (!CollectionUtils.isEmpty(listaActividad)) {
            ActividadDTO actividadDTO = new ActividadDTO();
            for (MdiActividad actividad : listaActividad) {
                actividadDTO = getActividad(actividad);
                listaActividadDTO.add(actividadDTO);
            }
        }
        return listaActividadDTO;
    }

    public static ActividadDTO getActividad(MdiActividad actividad) {
        ActividadDTO actividadDTO = new ActividadDTO();
        if (actividad != null) {
            if (actividad.getIdActividadPadre() != null) {
                actividadDTO.setIdActividadPadre(actividad.getIdActividadPadre());
            }
            actividadDTO.setIdActividad(actividad.getIdActividad());
            actividadDTO.setCodigo(actividad.getCodigo());
            actividadDTO.setNombre(actividad.getNombre());
        }
        return actividadDTO;
    }
}
