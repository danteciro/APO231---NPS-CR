package gob.osinergmin.consulta.dto;

/**
 * 
 * @author cflorian
 *
 */
public class SugerenciaDTO {
	
	public String sugerencia;
	public String nombreCompleto;
	public String email;
	public String telefono;
	public Long idTipoConsulta;
	public String login;
	
	public SugerenciaDTO(String sugerencia, String nombreCompleto, String email, String telefono, Long idTipoConsulta, String login){
		this.sugerencia = sugerencia;
		this.nombreCompleto = nombreCompleto;
		this.email = email;
		this.telefono = telefono;
		this.idTipoConsulta = idTipoConsulta;
		this.login = login;
	}
	
	public String getSugerencia() {
		return sugerencia;
	}
	public void setSugerencia(String sugerencia) {
		this.sugerencia = sugerencia;
	}	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public Long getIdTipoConsulta() {
		return idTipoConsulta;
	}

	public void setIdTipoConsulta(Long idTipoConsulta) {
		this.idTipoConsulta = idTipoConsulta;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
