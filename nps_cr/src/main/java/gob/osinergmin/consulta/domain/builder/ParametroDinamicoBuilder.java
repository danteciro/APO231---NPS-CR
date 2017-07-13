/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain.builder;

import gob.osinergmin.consulta.domain.PghParametroDinamico;
import gob.osinergmin.consulta.dto.ParametroDinamicoDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 *
 * @author lchancayauri
 */
public class ParametroDinamicoBuilder {

    public static List<ParametroDinamicoDTO> getListaParametroDinamico(List<PghParametroDinamico> listaParametroDinamico) {
        List<ParametroDinamicoDTO> listaParametroDinamicoDTO = new ArrayList<ParametroDinamicoDTO>();
        if (!CollectionUtils.isEmpty(listaParametroDinamico)) {
            ParametroDinamicoDTO parametroDinamicoDTO = new ParametroDinamicoDTO();
            for (PghParametroDinamico parametroDinamico : listaParametroDinamico) {
                parametroDinamicoDTO = getParametroDinamico(parametroDinamico);
                listaParametroDinamicoDTO.add(parametroDinamicoDTO);
            }
        }
        return listaParametroDinamicoDTO;
    }

    public static ParametroDinamicoDTO getParametroDinamico(PghParametroDinamico parametroDinamico) {
        ParametroDinamicoDTO parametroDinamicoDTO = new ParametroDinamicoDTO();
        if (parametroDinamico != null) {
            parametroDinamicoDTO.setIdParametroDinamico(parametroDinamico.getIdParametroDinamico());
            parametroDinamicoDTO.setNombre(parametroDinamico.getNombre());
            parametroDinamicoDTO.setDescripcion(parametroDinamico.getDescripcion());
            parametroDinamicoDTO.setComentario(parametroDinamico.getComentario());
            parametroDinamicoDTO.setEstado(parametroDinamico.getEstado());
            parametroDinamicoDTO.setPregunta(parametroDinamico.getPregunta());
            if(parametroDinamico.getTipoConsulta() != null){
                parametroDinamicoDTO.setTipoConsulta(parametroDinamico.getTipoConsulta().getIdMaestroColumna());
            }
            if (parametroDinamico.getIdAmbitoParametrico() != null) {
                parametroDinamicoDTO.setIdAmbitoParametrico(parametroDinamico.getIdAmbitoParametrico().getIdMaestroColumna());
            }
        }
        return parametroDinamicoDTO;
    }
}
