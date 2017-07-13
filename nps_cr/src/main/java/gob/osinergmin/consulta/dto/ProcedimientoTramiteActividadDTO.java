/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.dto;

/**
 *
 * @author lchancayauri
 */
public class ProcedimientoTramiteActividadDTO {

    private Long idProcedimientoTramiteActividad;
    private Long idProcedimientoTramite;
    private Long idActividad;

    public Long getIdProcedimientoTramiteActividad() {
        return idProcedimientoTramiteActividad;
    }

    public void setIdProcedimientoTramiteActividad(Long idProcedimientoTramiteActividad) {
        this.idProcedimientoTramiteActividad = idProcedimientoTramiteActividad;
    }

    public Long getIdProcedimientoTramite() {
        return idProcedimientoTramite;
    }

    public void setIdProcedimientoTramite(Long idProcedimientoTramite) {
        this.idProcedimientoTramite = idProcedimientoTramite;
    }

    public Long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }
    
}
