/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cflorian
 */
@Entity
@Table(name = "MDI_UNIDAD_MEDIDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiUnidadMedida.findAll", query = "SELECT m FROM MdiUnidadMedida m"),
    @NamedQuery(name = "MdiUnidadMedida.findByDescripcion", query = "SELECT m FROM MdiUnidadMedida m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "MdiUnidadMedida.findByAbreviatura", query = "SELECT m FROM MdiUnidadMedida m WHERE m.abreviatura = :abreviatura"),
    @NamedQuery(name = "MdiUnidadMedida.findByIdUnidadMedida", query = "SELECT m FROM MdiUnidadMedida m WHERE m.idUnidadMedida = :idUnidadMedida"),
    @NamedQuery(name = "MdiUnidadMedida.findByUsuarioActualizacion", query = "SELECT m FROM MdiUnidadMedida m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiUnidadMedida.findByUsuarioCreacion", query = "SELECT m FROM MdiUnidadMedida m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiUnidadMedida.findByFechaActualizacion", query = "SELECT m FROM MdiUnidadMedida m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiUnidadMedida.findByFechaCreacion", query = "SELECT m FROM MdiUnidadMedida m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiUnidadMedida.findByTerminalActualizacion", query = "SELECT m FROM MdiUnidadMedida m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiUnidadMedida.findByTerminalCreacion", query = "SELECT m FROM MdiUnidadMedida m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiUnidadMedida.findByEstado", query = "SELECT m FROM MdiUnidadMedida m WHERE m.estado = :estado")})
public class MdiUnidadMedida extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "ABREVIATURA")
    private String abreviatura;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_UNIDAD_MEDIDA")
    private Long idUnidadMedida;    
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;

    public MdiUnidadMedida() {
    }

    public MdiUnidadMedida(Long idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    public MdiUnidadMedida(Long idUnidadMedida, String usuarioActualizacion, String usuarioCreacion, Date fechaCreacion, String terminalActualizacion, String terminalCreacion, char estado) {
        this.idUnidadMedida = idUnidadMedida;
        this.usuarioActualizacion = usuarioActualizacion;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalActualizacion = terminalActualizacion;
        this.terminalCreacion = terminalCreacion;
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public Long getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(Long idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidadMedida != null ? idUnidadMedida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiUnidadMedida)) {
            return false;
        }
        MdiUnidadMedida other = (MdiUnidadMedida) object;
        if ((this.idUnidadMedida == null && other.idUnidadMedida != null) || (this.idUnidadMedida != null && !this.idUnidadMedida.equals(other.idUnidadMedida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiUnidadMedida[ idUnidadMedida=" + idUnidadMedida + " ]";
    }
    
}
