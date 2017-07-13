/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain.builder;

import gob.osinergmin.consulta.domain.PghCnfRequProcedimiento;
import gob.osinergmin.consulta.dto.RequisitoProcedimientoDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 *
 * @author lchancayauri
 */
public class RequisitoProcedimientoBuilder {

    public static List<RequisitoProcedimientoDTO> getListaRequisitoProcedimiento(List<PghCnfRequProcedimiento> listaRequisitoProcedimiento) {
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimientoDTO = new ArrayList<RequisitoProcedimientoDTO>();
        if (!CollectionUtils.isEmpty(listaRequisitoProcedimiento)) {
            RequisitoProcedimientoDTO requisitoProcedimientoDTO = new RequisitoProcedimientoDTO();
            for (PghCnfRequProcedimiento requisitoProcedimiento : listaRequisitoProcedimiento) {
                requisitoProcedimientoDTO = getRequisitoProcedimiento(requisitoProcedimiento);
                listaRequisitoProcedimientoDTO.add(requisitoProcedimientoDTO);
            }
        }
        return listaRequisitoProcedimientoDTO;
    }

    public static List<RequisitoProcedimientoDTO> getListaRequisitoProcedimientoSubRequisitos(List<PghCnfRequProcedimiento> listaRequisitoProcedimiento) {
        List<RequisitoProcedimientoDTO> listaRequisitoProcedimientoDTO = new ArrayList<RequisitoProcedimientoDTO>();
        if (!CollectionUtils.isEmpty(listaRequisitoProcedimiento)) {
            RequisitoProcedimientoDTO requisitoProcedimientoDTO = new RequisitoProcedimientoDTO();
            for (PghCnfRequProcedimiento requisitoProcedimiento : listaRequisitoProcedimiento) {
                requisitoProcedimientoDTO = getRequisitoProcedimiento(requisitoProcedimiento);

//                System.out.println(" listaSubRequisitoProcedimiento = " + requisitoProcedimiento.getPghRequProcParaProcedimientoList().size());
                List<RequisitoProcedimientoDTO> listaSubRequisitoProcedimiento = new ArrayList<RequisitoProcedimientoDTO>();
                List<PghCnfRequProcedimiento> lstSubRequisitoProcedimiento = requisitoProcedimiento.getPghRequProcParaProcedimientoList();
                for (PghCnfRequProcedimiento pghCnfRequProcedimiento : lstSubRequisitoProcedimiento) {
                    RequisitoProcedimientoDTO subRequisitoProcedimiento = getRequisitoProcedimiento(pghCnfRequProcedimiento);
                    if(subRequisitoProcedimiento.getEstado()!=null && subRequisitoProcedimiento.getEstado().equals("1")){
                        listaSubRequisitoProcedimiento.add(subRequisitoProcedimiento);
                        System.out.println("******************************");
                    }
//                    System.out.println("pghCnfRequProcedimiento padre = " + pghCnfRequProcedimiento.getIdRequisitoProcedimientoPad());
//                    System.out.println("pghCnfRequProcedimiento hijo = " + pghCnfRequProcedimiento.getIdRequisitoProcedimiento());
//                    System.out.println("pghCnfRequProcedimiento padre = " + subRequisitoProcedimiento.getIdRequisitoProcedimientoPad());
//                    System.out.println("pghCnfRequProcedimiento hijo = " + subRequisitoProcedimiento.getIdRequisitoProcedimiento());
//                    System.out.println("pghCnfRequProcedimiento hijo = " + subRequisitoProcedimiento.get());
                }
                requisitoProcedimientoDTO.setListaSubRequisitoProcedimiento(listaSubRequisitoProcedimiento);
                listaRequisitoProcedimientoDTO.add(requisitoProcedimientoDTO);
            }
        }
        return listaRequisitoProcedimientoDTO;
    }

    public static RequisitoProcedimientoDTO getRequisitoProcedimiento(PghCnfRequProcedimiento requisitoProcedimiento) {
        RequisitoProcedimientoDTO requisitoProcedimientoDTO = new RequisitoProcedimientoDTO();
        if (requisitoProcedimiento != null) {
            requisitoProcedimientoDTO.setIdRequisitoProcedimiento(requisitoProcedimiento.getIdRequisitoProcedimiento());
            requisitoProcedimientoDTO.setComentario(requisitoProcedimiento.getComentario());
            requisitoProcedimientoDTO.setEstado(requisitoProcedimiento.getEstado());
//            requisitoProcedimientoDTO.setFechaEliminacion(requisitoProcedimiento.getFechaEliminacion());
            requisitoProcedimientoDTO.setFlgGeneral(requisitoProcedimiento.getFlgGeneral());
            if (requisitoProcedimiento.getIdActividad() != null) {
                requisitoProcedimientoDTO.setIdActividad(requisitoProcedimiento.getIdActividad().getIdActividad());
            }
            if (requisitoProcedimiento.getIdMotivoTramite() != null) {
                requisitoProcedimientoDTO.setIdMotivoTramite(requisitoProcedimiento.getIdMotivoTramite().getIdMotivoTramite());
            }
            if (requisitoProcedimiento.getIdProcedimiento() != null) {
                requisitoProcedimientoDTO.setIdProcedimiento(requisitoProcedimiento.getIdProcedimiento().getIdProcedimiento());
            }
            if (requisitoProcedimiento.getIdRequisito() != null) {
                requisitoProcedimientoDTO.setIdRequisito(requisitoProcedimiento.getIdRequisito().getIdRequisito());
            }
            if (requisitoProcedimiento.getIdTramite() != null) {
                requisitoProcedimientoDTO.setIdTramite(requisitoProcedimiento.getIdTramite().getIdTramite());
            }
            if (requisitoProcedimiento.getIdZonificacion() != null) {
                requisitoProcedimientoDTO.setIdZonificacion(requisitoProcedimiento.getIdZonificacion().getIdZonificacion());
            }
            requisitoProcedimientoDTO.setIdRequisitoProcedimientoPad(requisitoProcedimiento.getIdRequisitoProcedimientoPad());
            requisitoProcedimientoDTO.setNroOrden(requisitoProcedimiento.getNroOrden());
        }
        return requisitoProcedimientoDTO;
    }
}
