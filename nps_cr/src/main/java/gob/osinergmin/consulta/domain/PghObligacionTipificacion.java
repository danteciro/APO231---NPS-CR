/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@Table(name = "PGH_OBLIGACION_TIPIFICACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PghObligacionTipificacion.findAll", query = "SELECT p FROM PghObligacionTipificacion p"),
    @NamedQuery(name = "PghObligacionTipificacion.findByIdObligacion", query = "SELECT p FROM PghObligacionTipificacion p WHERE p.pghObligacionTipificacionPK.idObligacion = :idObligacion"),
    @NamedQuery(name = "PghObligacionTipificacion.findByIdTipificacion", query = "SELECT p FROM PghObligacionTipificacion p WHERE p.pghObligacionTipificacionPK.idTipificacion = :idTipificacion"),
    @NamedQuery(name = "PghObligacionTipificacion.findByEstado", query = "SELECT p FROM PghObligacionTipificacion p WHERE p.estado = :estado"),
    @NamedQuery(name = "PghObligacionTipificacion.findByUsuarioCreacion", query = "SELECT p FROM PghObligacionTipificacion p WHERE p.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "PghObligacionTipificacion.findByFechaCreacion", query = "SELECT p FROM PghObligacionTipificacion p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PghObligacionTipificacion.findByTerminalCreacion", query = "SELECT p FROM PghObligacionTipificacion p WHERE p.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "PghObligacionTipificacion.findByUsuarioActualizacion", query = "SELECT p FROM PghObligacionTipificacion p WHERE p.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "PghObligacionTipificacion.findByFechaActualizacion", query = "SELECT p FROM PghObligacionTipificacion p WHERE p.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "PghObligacionTipificacion.findByTerminalActualizacion", query = "SELECT p FROM PghObligacionTipificacion p WHERE p.terminalActualizacion = :terminalActualizacion")})
public class PghObligacionTipificacion extends Auditoria {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PghObligacionTipificacionPK pghObligacionTipificacionPK;
    @Column(name = "ESTADO")
    private Character estado;   
    @JoinColumn(name = "ID_TIPIFICACION", referencedColumnName = "ID_TIPIFICACION", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PghTipificacion pghTipificacion;
    @JoinColumn(name = "ID_OBLIGACION", referencedColumnName = "ID_OBLIGACION", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PghObligacion pghObligacion;

    public PghObligacionTipificacion() {
    }

    public PghObligacionTipificacion(PghObligacionTipificacionPK pghObligacionTipificacionPK) {
        this.pghObligacionTipificacionPK = pghObligacionTipificacionPK;
    }

    public PghObligacionTipificacion(long idObligacion, long idTipificacion) {
        this.pghObligacionTipificacionPK = new PghObligacionTipificacionPK(idObligacion, idTipificacion);
    }

    public PghObligacionTipificacionPK getPghObligacionTipificacionPK() {
        return pghObligacionTipificacionPK;
    }

    public void setPghObligacionTipificacionPK(PghObligacionTipificacionPK pghObligacionTipificacionPK) {
        this.pghObligacionTipificacionPK = pghObligacionTipificacionPK;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public PghTipificacion getPghTipificacion() {
        return pghTipificacion;
    }

    public void setPghTipificacion(PghTipificacion pghTipificacion) {
        this.pghTipificacion = pghTipificacion;
    }

    public PghObligacion getPghObligacion() {
        return pghObligacion;
    }

    public void setPghObligacion(PghObligacion pghObligacion) {
        this.pghObligacion = pghObligacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pghObligacionTipificacionPK != null ? pghObligacionTipificacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghObligacionTipificacion)) {
            return false;
        }
        PghObligacionTipificacion other = (PghObligacionTipificacion) object;
        if ((this.pghObligacionTipificacionPK == null && other.pghObligacionTipificacionPK != null) || (this.pghObligacionTipificacionPK != null && !this.pghObligacionTipificacionPK.equals(other.pghObligacionTipificacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PghObligacionTipificacion[ pghObligacionTipificacionPK=" + pghObligacionTipificacionPK + " ]";
    }
    
}
