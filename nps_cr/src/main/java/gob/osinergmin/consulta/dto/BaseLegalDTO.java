package gob.osinergmin.consulta.dto;

import java.io.Serializable;

/**
*
* @author cflorian
*/
public class BaseLegalDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long idBaseLegal;
	private String descripcionBaseLegal;
	
	public BaseLegalDTO(){}
	public BaseLegalDTO(Long idBaseLegal, String descripcionBaseLegal){
		this.idBaseLegal = idBaseLegal;
		this.descripcionBaseLegal = descripcionBaseLegal;
	}
	
	
	public Long getIdBaseLegal() {
		return idBaseLegal;
	}
	public void setIdBaseLegal(Long idBaseLegal) {
		this.idBaseLegal = idBaseLegal;
	}
	public String getDescripcionBaseLegal() {
		return descripcionBaseLegal;
	}
	public void setDescripcionBaseLegal(String descripcionBaseLegal) {
		this.descripcionBaseLegal = descripcionBaseLegal;
	}
}
