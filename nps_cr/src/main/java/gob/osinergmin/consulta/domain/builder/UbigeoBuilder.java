/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain.builder;

import gob.osinergmin.consulta.domain.MdiUbigeo;
import gob.osinergmin.consulta.dto.DepartamentoDTO;
import gob.osinergmin.consulta.dto.DistritoDTO;
import gob.osinergmin.consulta.dto.ProvinciaDTO;
import gob.osinergmin.consulta.dto.UbigeoDTO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lchancayauri
 */
public class UbigeoBuilder {
    
    public static UbigeoDTO getUbigeo(MdiUbigeo ubigeo){
        UbigeoDTO ubigeoDTO = new UbigeoDTO();
        ubigeoDTO.setIdDepartamento(ubigeo.getMdiUbigeoPK().getIdDepartamento());
        ubigeoDTO.setIdProvincia(ubigeo.getMdiUbigeoPK().getIdProvincia());
        ubigeoDTO.setIdDistrito(ubigeo.getMdiUbigeoPK().getIdDistrito());
        ubigeoDTO.setNombre(ubigeo.getNombre());
        return ubigeoDTO;
    }

    public static List<DepartamentoDTO> getListaDepartamento(List<MdiUbigeo> listaUbigeo) {
        List<DepartamentoDTO> listaDepartamentosDTO = new ArrayList<DepartamentoDTO>();
        DepartamentoDTO departamentoDTO = new DepartamentoDTO();
        for (MdiUbigeo ubigeo : listaUbigeo) {
            departamentoDTO = new DepartamentoDTO();
            departamentoDTO.setIdDepartamento(ubigeo.getMdiUbigeoPK().getIdDepartamento());
            departamentoDTO.setNombre(ubigeo.getNombre());
            listaDepartamentosDTO.add(departamentoDTO);
        }
        return listaDepartamentosDTO;
    }

    public static List<ProvinciaDTO> getListaProvincia(List<MdiUbigeo> listaUbigeo) {
        List<ProvinciaDTO> listaProvinciaDTO = new ArrayList<ProvinciaDTO>();
        ProvinciaDTO provinciaDTO = new ProvinciaDTO();
        for (MdiUbigeo ubigeo : listaUbigeo) {
            provinciaDTO = new ProvinciaDTO();
            provinciaDTO.setIdDepartamento(ubigeo.getMdiUbigeoPK().getIdDepartamento());
            provinciaDTO.setIdProvincia(ubigeo.getMdiUbigeoPK().getIdProvincia());
            provinciaDTO.setNombre(ubigeo.getNombre());
            listaProvinciaDTO.add(provinciaDTO);
        }
        return listaProvinciaDTO;
    }

    public static List<DistritoDTO> getListaDistrito(List<MdiUbigeo> listaUbigeo) {
        List<DistritoDTO> listaDistritoDTO = new ArrayList<DistritoDTO>();
        DistritoDTO distritoDTO = new DistritoDTO();
        for (MdiUbigeo ubigeo : listaUbigeo) {
            distritoDTO = new DistritoDTO();
            distritoDTO.setIdDepartamento(ubigeo.getMdiUbigeoPK().getIdDepartamento());
            distritoDTO.setIdProvincia(ubigeo.getMdiUbigeoPK().getIdProvincia());
            distritoDTO.setIdDistrito(ubigeo.getMdiUbigeoPK().getIdDistrito());
            distritoDTO.setNombre(ubigeo.getNombre());
            listaDistritoDTO.add(distritoDTO);
        }
        return listaDistritoDTO;
    }
}
