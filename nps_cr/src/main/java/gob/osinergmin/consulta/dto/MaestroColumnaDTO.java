package gob.osinergmin.consulta.dto;

import java.io.Serializable;
/**
 *
 * @author cflorian
 */
public class MaestroColumnaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long idMaestroColumna;
    private String codigo;
    private String descripcion;
    private String estado;    
    private String dominio;
    private String aplicacion;
	public Long getIdMaestroColumna() {
		return idMaestroColumna;
	}
	public void setIdMaestroColumna(Long idMaestroColumna) {
		this.idMaestroColumna = idMaestroColumna;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	public String getDominio() {
		return dominio;
	}
	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	public String getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}
    
}
