/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.dto;

/**
*
* @author l_garcia_r
*/

public class TipoSancionDTO {
    private Long idTipoSancion;
    private String descripcion;
    private String estado;
// 06-11-2015    
    private String abreviatura;
    private String abreviaturaDescripcion;
//    
    public TipoSancionDTO(){
    	
    }
// 05-11-2015      
    public TipoSancionDTO(Long idTipoSancion,String descripcion,String abreviatura){
        this.idTipoSancion=idTipoSancion;
        this.descripcion=descripcion;
        this.abreviatura=abreviatura;
    }
//    
    public TipoSancionDTO(Long idTipoSancion,String descripcion){
        this.idTipoSancion=idTipoSancion;
        this.descripcion=descripcion;
    }
    
    public Long getIdTipoSancion() {
        return idTipoSancion;
    }

    public void setIdTipoSancion(Long idTipoSancion) {
        this.idTipoSancion = idTipoSancion;
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
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	public String getAbreviaturaDescripcion() {
		return abreviaturaDescripcion;
	}
	public void setAbreviaturaDescripcion(String abreviaturaDescripcion) {
		this.abreviaturaDescripcion = abreviaturaDescripcion;
	}
}
