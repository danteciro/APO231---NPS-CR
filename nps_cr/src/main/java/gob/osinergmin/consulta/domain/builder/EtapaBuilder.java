/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain.builder;

import gob.osinergmin.consulta.domain.PghEtapa;
import gob.osinergmin.consulta.dto.EtapaDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 *
 * @author lchancayauri
 */
public class EtapaBuilder {

    public static List<EtapaDTO> getListaEtapa(List<PghEtapa> listaEtapa) {
        List<EtapaDTO> listaEtapaDTO = new ArrayList<EtapaDTO>();
        if (!CollectionUtils.isEmpty(listaEtapa)) {
            EtapaDTO etapaDTO = new EtapaDTO();
            for (PghEtapa etapa : listaEtapa) {
                etapaDTO = getEtapa(etapa);
                listaEtapaDTO.add(etapaDTO);
            }
        }
        return listaEtapaDTO;
    }

    public static EtapaDTO getEtapa(PghEtapa etapa) {
        EtapaDTO etapaDTO = new EtapaDTO();
        if (etapa != null) {
            etapaDTO.setIdEtapa(etapa.getIdEtapa());
            etapaDTO.setDescripcion(etapa.getDescripcion());
        }
        return etapaDTO;
    }
}
