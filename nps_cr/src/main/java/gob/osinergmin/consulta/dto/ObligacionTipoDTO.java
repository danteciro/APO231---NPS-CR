package gob.osinergmin.consulta.dto;

import java.io.Serializable;

public class ObligacionTipoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long idObligacionTipo;
	private String nombre;
	
	public Long getIdObligacionTipo() {
		return idObligacionTipo;
	}
	public void setIdObligacionTipo(Long idObligacionTipo) {
		this.idObligacionTipo = idObligacionTipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
