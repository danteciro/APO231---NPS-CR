/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain.builder;

import gob.osinergmin.consulta.domain.PghProcedimiento;
import gob.osinergmin.consulta.dto.ProcedimientoDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 *
 * @author lchancayauri
 */
public class ProcedimientoBuilder {

    public static List<ProcedimientoDTO> getListaProcedimiento(List<PghProcedimiento> listaProcedimiento) {
        List<ProcedimientoDTO> listaProcedimientoDTO = new ArrayList<ProcedimientoDTO>();
        if (!CollectionUtils.isEmpty(listaProcedimiento)) {
            ProcedimientoDTO procedimientoDTO = new ProcedimientoDTO();
            for (PghProcedimiento procedimiento : listaProcedimiento) {
                procedimientoDTO = getProcedimiento(procedimiento);
                listaProcedimientoDTO.add(procedimientoDTO);
            }
        }
        return listaProcedimientoDTO;
    }

    public static ProcedimientoDTO getProcedimiento(PghProcedimiento procedimiento) {
        ProcedimientoDTO procedimientoDTO = new ProcedimientoDTO();
        if (procedimiento != null) {
            procedimientoDTO.setIdProcedimiento(procedimiento.getIdProcedimiento());
            procedimientoDTO.setBaseLegal(procedimiento.getBaseLegal());
            procedimientoDTO.setDenominacion(procedimiento.getDenominacion());
            procedimientoDTO.setEstado(procedimiento.getEstado());
            procedimientoDTO.setIdAnexoRrh(procedimiento.getIdAnexoRrh());
            procedimientoDTO.setIdApelacion(procedimiento.getIdApelacion());
            procedimientoDTO.setIdAutoridadCompetente(procedimiento.getIdAutoridadCompetente());
            procedimientoDTO.setIdCalificacion(procedimiento.getIdCalificacion());
            procedimientoDTO.setIdInicioProcedimiento(procedimiento.getIdInicioProcedimiento());
            procedimientoDTO.setIdProcedimiento(procedimiento.getIdProcedimiento());
            procedimientoDTO.setIdReconsideracion(procedimiento.getIdReconsideracion());
            procedimientoDTO.setIdSilencioAdministrativo(procedimiento.getIdSilencioAdministrativo());
            procedimientoDTO.setIdValorUit(procedimiento.getIdValorUit());
            procedimientoDTO.setItem(procedimiento.getItem());
            procedimientoDTO.setNotaProcedimiento(procedimiento.getNotaProcedimiento());
            procedimientoDTO.setPlazoResolver(procedimiento.getPlazoResolver());
            procedimientoDTO.setDerechoTramitacion(procedimiento.getDerechoTramitacion());
        }
        return procedimientoDTO;
    }
}
