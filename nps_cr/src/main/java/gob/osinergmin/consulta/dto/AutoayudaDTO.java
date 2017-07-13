package gob.osinergmin.consulta.dto;

import java.io.Serializable;

public class AutoayudaDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long idAutoayuda;
	private String nombre;
	private String descripcion;
	private String estado;
	
	public AutoayudaDTO(){}
	public AutoayudaDTO(Long idAutoayuda,String nombre,String descripcion,String estado){
		this.idAutoayuda=idAutoayuda;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.estado=estado;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getIdAutoayuda() {
		return idAutoayuda;
	}
	public void setIdAutoayuda(Long idAutoayuda) {
		this.idAutoayuda = idAutoayuda;
	}
	

}
