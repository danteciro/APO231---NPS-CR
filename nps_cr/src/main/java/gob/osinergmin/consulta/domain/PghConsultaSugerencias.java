/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cflorian
 */
@Entity
@Table(name = "PGH_CONSULTA_SUGERENCIAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PghConsultaSugerencias.findAll", query = "SELECT p FROM PghConsultaSugerencias p"),
    @NamedQuery(name = "PghConsultaSugerencias.findByIdSugerencia", query = "SELECT p FROM PghConsultaSugerencias p WHERE p.idSugerencia = :idSugerencia"),
    @NamedQuery(name = "PghConsultaSugerencias.findByDescSugerencia", query = "SELECT p FROM PghConsultaSugerencias p WHERE p.descSugerencia = :descSugerencia"),
    @NamedQuery(name = "PghConsultaSugerencias.findByNombreCompleto", query = "SELECT p FROM PghConsultaSugerencias p WHERE p.nombreCompleto = :nombreCompleto"),
    @NamedQuery(name = "PghConsultaSugerencias.findByCorreo", query = "SELECT p FROM PghConsultaSugerencias p WHERE p.correo = :correo"),
    @NamedQuery(name = "PghConsultaSugerencias.findByTelefono", query = "SELECT p FROM PghConsultaSugerencias p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "PghConsultaSugerencias.findByEstado", query = "SELECT p FROM PghConsultaSugerencias p WHERE p.estado = :estado"),
    @NamedQuery(name = "PghConsultaSugerencias.findByUsuarioCreacion", query = "SELECT p FROM PghConsultaSugerencias p WHERE p.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "PghConsultaSugerencias.findByUsuarioActualizacion", query = "SELECT p FROM PghConsultaSugerencias p WHERE p.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "PghConsultaSugerencias.findByTerminalCreacion", query = "SELECT p FROM PghConsultaSugerencias p WHERE p.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "PghConsultaSugerencias.findByTerminalActualizacion", query = "SELECT p FROM PghConsultaSugerencias p WHERE p.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "PghConsultaSugerencias.findByFechaCreacion", query = "SELECT p FROM PghConsultaSugerencias p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PghConsultaSugerencias.findByFechaActualizacion", query = "SELECT p FROM PghConsultaSugerencias p WHERE p.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "PghConsultaSugerencias.findByTipoConsulta", query = "SELECT p FROM PghConsultaSugerencias p WHERE p.tipoConsulta = :tipoConsulta")})
public class PghConsultaSugerencias extends Auditoria {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(generator = "PGH_CONSULTA_SUGERENCIA")
    @SequenceGenerator(name = "PGH_CONSULTA_SUGERENCIA", sequenceName = "PGH_CONSULTA_SUGERENCIA_SEQ", allocationSize = 1)
    @Column(name = "ID_SUGERENCIA")
    private Long idSugerencia;
    @Basic(optional = false)
    @Column(name = "DESC_SUGERENCIA")
    private String descSugerencia;
    @Basic(optional = false)
    @Column(name = "NOMBRE_COMPLETO")
    private String nombreCompleto;
    @Basic(optional = false)
    @Column(name = "CORREO")
    private String correo;
    @Column(name = "TELEFONO")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private String estado;    
    @Basic(optional = false)
    @Column(name = "TIPO_CONSULTA")
    private Long tipoConsulta;

    public PghConsultaSugerencias() {
    }

    public PghConsultaSugerencias(Long idSugerencia) {
        this.idSugerencia = idSugerencia;
    }

    public PghConsultaSugerencias(Long idSugerencia, String descSugerencia, String nombreCompleto, String correo, String estado, String usuarioCreacion, String terminalCreacion, Date fechaCreacion, Long tipoConsulta) {
        this.idSugerencia = idSugerencia;
        this.descSugerencia = descSugerencia;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.terminalCreacion = terminalCreacion;
        this.fechaCreacion = fechaCreacion;
        this.tipoConsulta = tipoConsulta;
    }

    public Long getIdSugerencia() {
        return idSugerencia;
    }

    public void setIdSugerencia(Long idSugerencia) {
        this.idSugerencia = idSugerencia;
    }

    public String getDescSugerencia() {
        return descSugerencia;
    }

    public void setDescSugerencia(String descSugerencia) {
        this.descSugerencia = descSugerencia;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(Long tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSugerencia != null ? idSugerencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghConsultaSugerencias)) {
            return false;
        }
        PghConsultaSugerencias other = (PghConsultaSugerencias) object;
        if ((this.idSugerencia == null && other.idSugerencia != null) || (this.idSugerencia != null && !this.idSugerencia.equals(other.idSugerencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.PghConsultaSugerencias[ idSugerencia=" + idSugerencia + " ]";
    }
    
}
