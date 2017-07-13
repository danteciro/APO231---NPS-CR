/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.dto;

import java.io.Serializable;

/**
 *
 * @author lchancayauri
 */
public class ZonificacionDetalleDTO implements Serializable{

    private Long idZonificacionDetalle;
    private String estado;
    private Long idMacroRegion;
    private Long idRegion;
    private Long idZonificacion;
    private String idDepartamento;
    private String idProvincia;
    private String idDistrito;

    public Long getIdZonificacionDetalle() {
        return idZonificacionDetalle;
    }

    public void setIdZonificacionDetalle(Long idZonificacionDetalle) {
        this.idZonificacionDetalle = idZonificacionDetalle;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdMacroRegion() {
        return idMacroRegion;
    }

    public void setIdMacroRegion(Long idMacroRegion) {
        this.idMacroRegion = idMacroRegion;
    }

    public Long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Long idRegion) {
        this.idRegion = idRegion;
    }

    public Long getIdZonificacion() {
        return idZonificacion;
    }

    public void setIdZonificacion(Long idZonificacion) {
        this.idZonificacion = idZonificacion;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(String idDistrito) {
        this.idDistrito = idDistrito;
    }
    
}
