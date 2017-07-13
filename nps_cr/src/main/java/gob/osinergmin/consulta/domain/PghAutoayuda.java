package gob.osinergmin.consulta.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PGH_AUTOAYUDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PghAutoayuda.findAll", query = "SELECT p FROM PghAutoayuda p"),
    @NamedQuery(name = "PghAutoayuda.findByIdAutoayuda", query = "SELECT p FROM PghAutoayuda p WHERE p.idAutoayuda = :idTAutoayuda"),
    @NamedQuery(name = "PghAutoayuda.findByNombreAutoayuda", query = "SELECT p FROM PghAutoayuda p WHERE p.nombreAutoayuda = :nombreAutoayuda"),
    @NamedQuery(name = "PghAutoayuda.findByDescripcionAutoayuda", query = "SELECT p FROM PghAutoayuda p WHERE p.descripcionAutoayuda = :descripcionAutoayuda"),
    @NamedQuery(name = "PghAutoayuda.findByIdentificadorAutoayuda", query = "SELECT p FROM PghAutoayuda p WHERE p.identificadorAutoayuda = :identificadorAutoayuda"),
    @NamedQuery(name = "PghAutoayuda.findByEstado", query = "SELECT p FROM PghAutoayuda p WHERE p.estado = :estado")})
	
public class PghAutoayuda implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_AUTOAYUDA")
    private Long idAutoayuda;
    @Column(name = "NOMBRE_AUTOAYUDA")
    private String nombreAutoayuda;
    @Column(name = "DESCRIPCION_AUTOAYUDA")
    private String descripcionAutoayuda;
    @Column(name = "IDENTIFICADOR_AUTOAYUDA")
    private String identificadorAutoayuda;
    @Column(name = "ESTADO")
    private String estado;
    
    public PghAutoayuda(){
    	
    }
	public Long getIdAutoayuda() {
		return idAutoayuda;
	}
	public void setIdAutoayuda(Long idAutoayuda) {
		this.idAutoayuda = idAutoayuda;
	}
	public String getNombreAutoayuda() {
		return nombreAutoayuda;
	}
	public void setNombreAutoayuda(String nombreAutoayuda) {
		this.nombreAutoayuda = nombreAutoayuda;
	}
	public String getDescripcionAutoayuda() {
		return descripcionAutoayuda;
	}
	public void setDescripcionAutoayuda(String descripcionAutoayuda) {
		this.descripcionAutoayuda = descripcionAutoayuda;
	}
	public String getIdentificadorAutoayuda() {
		return identificadorAutoayuda;
	}
	public void setIdentificadorAutoayuda(String identificadorAutoayuda) {
		this.identificadorAutoayuda = identificadorAutoayuda;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
