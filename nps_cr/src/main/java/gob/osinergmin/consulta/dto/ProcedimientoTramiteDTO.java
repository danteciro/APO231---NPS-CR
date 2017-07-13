/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.dto;

/**
 *
 * @author lchancayauri
 */
public class ProcedimientoTramiteDTO {
    
    private Long idProcedimientoTramite;
    private Long idProcedimiento;
    private Long idTramite;

    public Long getIdProcedimientoTramite() {
        return idProcedimientoTramite;
    }

    public void setIdProcedimientoTramite(Long idProcedimientoTramite) {
        this.idProcedimientoTramite = idProcedimientoTramite;
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
    
}
