/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain.builder;

import gob.osinergmin.consulta.domain.PghMotivoTramite;
import gob.osinergmin.consulta.dto.MotivoTramiteDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 *
 * @author lchancayauri
 */
public class MotivoTramiteBuilder {

    public static List<MotivoTramiteDTO> getListaMotivoTramite(List<PghMotivoTramite> listaMotivoTramite) {
        List<MotivoTramiteDTO> listaMotivoTramiteDTO = new ArrayList<MotivoTramiteDTO>();
        if (!CollectionUtils.isEmpty(listaMotivoTramite)) {
            MotivoTramiteDTO motivoTramiteDTO = new MotivoTramiteDTO();
            for (PghMotivoTramite motivoTramite : listaMotivoTramite) {
                motivoTramiteDTO = getMotivoTramite(motivoTramite);
                listaMotivoTramiteDTO.add(motivoTramiteDTO);
            }
        }
        return listaMotivoTramiteDTO;
    }

    public static MotivoTramiteDTO getMotivoTramite(PghMotivoTramite motivoTramite) {
        MotivoTramiteDTO motivoTramiteDTO = new MotivoTramiteDTO();
        if (motivoTramite != null) {
            motivoTramiteDTO.setIdMotivoTramite(motivoTramite.getIdMotivoTramite());
            motivoTramiteDTO.setDescripcion(motivoTramite.getDescripcion());
            motivoTramiteDTO.setEstado(motivoTramite.getEstado());
            motivoTramiteDTO.setIdTramite(motivoTramite.getIdTramite().getIdTramite());
        }
        return motivoTramiteDTO;
    }
}
