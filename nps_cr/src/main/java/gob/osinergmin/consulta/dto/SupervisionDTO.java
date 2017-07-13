/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.dto;

/**
 *
 * @author lchancayauri
 */
public class SupervisionDTO {
 
    private Long idSupervision;
    private String descripcion;
    private Long idActividad;

    public Long getIdSupervision() {
        return idSupervision;
    }

    public void setIdSupervision(Long idSupervision) {
        this.idSupervision = idSupervision;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }
    
}
