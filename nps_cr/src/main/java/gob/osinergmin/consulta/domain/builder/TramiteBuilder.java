/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain.builder;

import gob.osinergmin.consulta.domain.PghTramite;
import gob.osinergmin.consulta.dto.TramiteDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 *
 * @author lchancayauri
 */
public class TramiteBuilder {

    public static List<TramiteDTO> getListaTramite(List<PghTramite> listaTramite) {
        List<TramiteDTO> listaTramiteDTO = new ArrayList<TramiteDTO>();
        if (!CollectionUtils.isEmpty(listaTramite)) {
            TramiteDTO tramiteDTO = new TramiteDTO();
            for (PghTramite tramite : listaTramite) {
                tramiteDTO = getTramite(tramite);
                listaTramiteDTO.add(tramiteDTO);
            }
        }
        return listaTramiteDTO;
    }

    public static TramiteDTO getTramite(PghTramite tramite) {
        TramiteDTO tramiteDTO = new TramiteDTO();
        if (tramite != null) {
            tramiteDTO.setIdTramite(tramite.getIdTramite());
            tramiteDTO.setDescripcion(tramite.getDescripcion());
            tramiteDTO.setIdEtapa(tramite.getIdEtapa().getIdEtapa());
        }
        return tramiteDTO;
    }
}
