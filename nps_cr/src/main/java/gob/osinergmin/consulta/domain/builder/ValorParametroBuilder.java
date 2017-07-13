/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain.builder;

import gob.osinergmin.consulta.domain.PghValorParametro;
import gob.osinergmin.consulta.dto.ValorParametroDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 *
 * @author lchancayauri
 */
public class ValorParametroBuilder {

    public static List<ValorParametroDTO> getListaValorParametro(List<PghValorParametro> listaValorParametro) {
        List<ValorParametroDTO> listaValorParametroDTO = new ArrayList<ValorParametroDTO>();
        if (!CollectionUtils.isEmpty(listaValorParametro)) {
            ValorParametroDTO valorParametroDTO = new ValorParametroDTO();
            for (PghValorParametro valorParametro : listaValorParametro) {
                valorParametroDTO = getValorParametro(valorParametro);
                listaValorParametroDTO.add(valorParametroDTO);
            }
        }
        return listaValorParametroDTO;
    }

    public static ValorParametroDTO getValorParametro(PghValorParametro valorParametro) {
        ValorParametroDTO valorParametroDTO = new ValorParametroDTO();
        if (valorParametro != null) {
            valorParametroDTO.setIdValorParametro(valorParametro.getIdValorParametro());
            valorParametroDTO.setDescripcion(valorParametro.getDescripcion());
            valorParametroDTO.setComentario(valorParametro.getComentario());
            valorParametroDTO.setEstado(valorParametro.getEstado());
            valorParametroDTO.setIdParametroDinamico(valorParametro.getIdParametroDinamico().getIdParametroDinamico());
            valorParametroDTO.setValor(valorParametro.getValor());
            valorParametroDTO.setValorDefecto(valorParametro.getValorDefecto());
        }
        return valorParametroDTO;
    }
}
