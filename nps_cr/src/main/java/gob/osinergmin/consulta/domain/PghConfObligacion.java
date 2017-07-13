/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cflorian
 */
@Entity
@Table(name = "PGH_CONF_OBLIGACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PghConfObligacion.findAll", query = "SELECT p FROM PghConfObligacion p"),
    @NamedQuery(name = "PghConfObligacion.findByEstado", query = "SELECT p FROM PghConfObligacion p WHERE p.estado = :estado"),
    @NamedQuery(name = "PghConfObligacion.findByUsuarioCreacion", query = "SELECT p FROM PghConfObligacion p WHERE p.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "PghConfObligacion.findByFechaCreacion", query = "SELECT p FROM PghConfObligacion p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PghConfObligacion.findByTerminalCreacion", query = "SELECT p FROM PghConfObligacion p WHERE p.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "PghConfObligacion.findByUsuarioActualizacion", query = "SELECT p FROM PghConfObligacion p WHERE p.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "PghConfObligacion.findByFechaActualizacion", query = "SELECT p FROM PghConfObligacion p WHERE p.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "PghConfObligacion.findByTerminalActualizacion", query = "SELECT p FROM PghConfObligacion p WHERE p.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "PghConfObligacion.findByIdConfObligacion", query = "SELECT p FROM PghConfObligacion p WHERE p.idConfObligacion = :idConfObligacion")})
public class PghConfObligacion extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Column(name = "ESTADO")
    private String estado;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CONF_OBLIGACION")
    private Long idConfObligacion;
    @JoinColumns({
        @JoinColumn(name = "ID_PROCESO", referencedColumnName = "ID_OBLIGACION_TIPO"),
        @JoinColumn(name = "ID_ACTIVIDAD", referencedColumnName = "ID_PROCESO"),
        @JoinColumn(name = "ID_OBLIGACION_TIPO", referencedColumnName = "ID_ACTIVIDAD")})
    @ManyToOne
    private PghProcesoObligacionTipo pghProcesoObligacionTipo;
    @JoinColumn(name = "ID_OBLIGACION", referencedColumnName = "ID_OBLIGACION")
    @ManyToOne(optional = false)
    private PghObligacion idObligacion;

    public PghConfObligacion() {
    }

    public PghConfObligacion(Long idConfObligacion) {
        this.idConfObligacion = idConfObligacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdConfObligacion() {
        return idConfObligacion;
    }

    public void setIdConfObligacion(Long idConfObligacion) {
        this.idConfObligacion = idConfObligacion;
    }

    public PghProcesoObligacionTipo getPghProcesoObligacionTipo() {
        return pghProcesoObligacionTipo;
    }

    public void setPghProcesoObligacionTipo(PghProcesoObligacionTipo pghProcesoObligacionTipo) {
        this.pghProcesoObligacionTipo = pghProcesoObligacionTipo;
    }

    public PghObligacion getIdObligacion() {
        return idObligacion;
    }

    public void setIdObligacion(PghObligacion idObligacion) {
        this.idObligacion = idObligacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConfObligacion != null ? idConfObligacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghConfObligacion)) {
            return false;
        }
        PghConfObligacion other = (PghConfObligacion) object;
        if ((this.idConfObligacion == null && other.idConfObligacion != null) || (this.idConfObligacion != null && !this.idConfObligacion.equals(other.idConfObligacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinerg.consulta.domain.PghConfObligacion[ idConfObligacion=" + idConfObligacion + " ]";
    }
    
}
