/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain.builder;

import gob.osinergmin.consulta.domain.PghRequisito;
import gob.osinergmin.consulta.dto.RequisitoDTO;
import gob.osinergmin.consulta.dto.RequisitoProcedimientoDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 *
 * @author lchancayauri
 */
public class RequisitoBuilder {

    public static List<RequisitoDTO> getListaRequisito(List<PghRequisito> listaRequisito) {
        List<RequisitoDTO> listaRequisitoDTO = new ArrayList<RequisitoDTO>();
        if (!CollectionUtils.isEmpty(listaRequisito)) {
            RequisitoDTO requisitoDTO = new RequisitoDTO();
            for (PghRequisito requisito : listaRequisito) {
                requisitoDTO = getRequisito(requisito);
                listaRequisitoDTO.add(requisitoDTO);
            }
        }
        return listaRequisitoDTO;
    }

    public static RequisitoDTO getRequisito(PghRequisito requisito) {
        RequisitoDTO requisitoDTO = new RequisitoDTO();
        if (requisito != null) {
            requisitoDTO.setIdRequisito(requisito.getIdRequisito());
            requisitoDTO.setDescripcion(requisito.getDescripcion());
            requisitoDTO.setComentarioPredeterminado(requisito.getComentarioPredeterminado());
            requisitoDTO.setEstado(requisito.getEstado());
            if(requisito.getIdDocumentoAdjunto() != null){
                requisitoDTO.setIdDocumentoAdjunto(requisito.getIdDocumentoAdjunto().getIdDocumentoAdjunto());
            }
            if(requisito.getRequisitoProcedimiento()!=null){
                requisitoDTO.setRequisitoProcedimiento(new RequisitoProcedimientoDTO(requisito.getRequisitoProcedimiento().getIdRequisitoProcedimiento(),requisito.getRequisitoProcedimiento().getComentario()));
            }
        }
        return requisitoDTO;
    }
}
