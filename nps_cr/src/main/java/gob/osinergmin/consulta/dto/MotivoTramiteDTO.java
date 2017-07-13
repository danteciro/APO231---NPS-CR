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
public class MotivoTramiteDTO implements Serializable {

    private Long idMotivoTramite;
    private String descripcion;
    private String estado;
    private Long idTramite;

    public Long getIdMotivoTramite() {
        return idMotivoTramite;
    }

    public void setIdMotivoTramite(Long idMotivoTramite) {
        this.idMotivoTramite = idMotivoTramite;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdTramite() {
        return idTramite;
    }

    public void setIdTramite(Long idTramite) {
        this.idTramite = idTramite;
    }
}
