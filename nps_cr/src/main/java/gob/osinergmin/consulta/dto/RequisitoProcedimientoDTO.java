/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lchancayauri
 */
public class RequisitoProcedimientoDTO implements Serializable{
    
    private Long idRequisitoProcedimiento;
    private Long idRequisito;
    private Long idProcedimiento;
    private Long idTramite;
    private Long idActividad;
    private Long idZonificacion;    
    private Long idMotivoTramite;
    private String comentario;
    private String estado;
    private Long idRequisitoProcedimientoPad;
    private Date fechaEliminacion;
    private String flgGeneral;
    private Long nroOrden;
    
    List<RequisitoProcedimientoDTO> listaSubRequisitoProcedimiento;

    public RequisitoProcedimientoDTO() {
    }

    public RequisitoProcedimientoDTO(Long idRequisitoProcedimiento, String comentario) {
        this.idRequisitoProcedimiento = idRequisitoProcedimiento;
        this.comentario = comentario;
    }

    public Long getIdRequisitoProcedimiento() {
        return idRequisitoProcedimiento;
    }

    public void setIdRequisitoProcedimiento(Long idRequisitoProcedimiento) {
        this.idRequisitoProcedimiento = idRequisitoProcedimiento;
    }

    public Long getIdRequisito() {
        return idRequisito;
    }

    public void setIdRequisito(Long idRequisito) {
        this.idRequisito = idRequisito;
    }

    public Long getIdProcedimiento() {
        return idProcedimiento;
    }

    public void setIdProcedimiento(Long idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
    }

    public Long getIdTramite() {
        return idTramite;
    }

    public void setIdTramite(Long idTramite) {
        this.idTramite = idTramite;
    }

    public Long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }

    public Long getIdZonificacion() {
        return idZonificacion;
    }

    public void setIdZonificacion(Long idZonificacion) {
        this.idZonificacion = idZonificacion;
    }

    public Long getIdMotivoTramite() {
        return idMotivoTramite;
    }

    public void setIdMotivoTramite(Long idMotivoTramite) {
        this.idMotivoTramite = idMotivoTramite;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdRequisitoProcedimientoPad() {
        return idRequisitoProcedimientoPad;
    }

    public void setIdRequisitoProcedimientoPad(Long idRequisitoProcedimientoPad) {
        this.idRequisitoProcedimientoPad = idRequisitoProcedimientoPad;
    }

    public Date getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(Date fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }

    public String getFlgGeneral() {
        return flgGeneral;
    }

    public void setFlgGeneral(String flgGeneral) {
        this.flgGeneral = flgGeneral;
    }

    public Long getNroOrden() {
        return nroOrden;
    }

    public void setNroOrden(Long nroOrden) {
        this.nroOrden = nroOrden;
    }
    
    public List<RequisitoProcedimientoDTO> getListaSubRequisitoProcedimiento() {
        return listaSubRequisitoProcedimiento;
    }

    public void setListaSubRequisitoProcedimiento(List<RequisitoProcedimientoDTO> listaSubRequisitoProcedimiento) {
        this.listaSubRequisitoProcedimiento = listaSubRequisitoProcedimiento;
    }
    
}
