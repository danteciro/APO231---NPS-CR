/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.dto;

/**
 *
 * @author cflorian
 */
public class ClasificacionDTO {
 
    private Long idClasificacion;
    private String descripcion;
    private Long idObligacionTipo;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Long getIdObligacionTipo() {
		return idObligacionTipo;
	}
	public void setIdObligacionTipo(Long idObligacionTipo) {
		this.idObligacionTipo = idObligacionTipo;
	}
	public Long getIdClasificacion() {
		return idClasificacion;
	}
	public void setIdClasificacion(Long idClasificacion) {
		this.idClasificacion = idClasificacion;
	}

   
}
