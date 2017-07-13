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
@Table(name = "PGH_TIPIFICACION_SANCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PghTipificacionSancion.findAll", query = "SELECT p FROM PghTipificacionSancion p"),
    @NamedQuery(name = "PghTipificacionSancion.findByEstado", query = "SELECT p FROM PghTipificacionSancion p WHERE p.estado = :estado"),
    @NamedQuery(name = "PghTipificacionSancion.findByUsuarioCreacion", query = "SELECT p FROM PghTipificacionSancion p WHERE p.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "PghTipificacionSancion.findByFechaCreacion", query = "SELECT p FROM PghTipificacionSancion p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PghTipificacionSancion.findByTerminalCreacion", query = "SELECT p FROM PghTipificacionSancion p WHERE p.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "PghTipificacionSancion.findByUsuarioActualizacion", query = "SELECT p FROM PghTipificacionSancion p WHERE p.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "PghTipificacionSancion.findByFechaActualizacion", query = "SELECT p FROM PghTipificacionSancion p WHERE p.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "PghTipificacionSancion.findByTerminalActualizacion", query = "SELECT p FROM PghTipificacionSancion p WHERE p.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "PghTipificacionSancion.findByIdTipificacion", query = "SELECT p FROM PghTipificacionSancion p WHERE p.pghTipificacionSancionPK.idTipificacion = :idTipificacion"),
    @NamedQuery(name = "PghTipificacionSancion.findByIdTipoSancion", query = "SELECT p FROM PghTipificacionSancion p WHERE p.pghTipificacionSancionPK.idTipoSancion = :idTipoSancion")})
public class PghTipificacionSancion extends Auditoria {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PghTipificacionSancionPK pghTipificacionSancionPK;
    @Column(name = "ESTADO")
    private Character estado;    
    @JoinColumn(name = "ID_TIPO_SANCION", referencedColumnName = "ID_TIPO_SANCION", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PghTipoSancion pghTipoSancion;
    @JoinColumn(name = "ID_TIPIFICACION", referencedColumnName = "ID_TIPIFICACION", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PghTipificacion pghTipificacion;

    public PghTipificacionSancion() {
    }

    public PghTipificacionSancion(PghTipificacionSancionPK pghTipificacionSancionPK) {
        this.pghTipificacionSancionPK = pghTipificacionSancionPK;
    }

    public PghTipificacionSancion(long idTipificacion, long idTipoSancion) {
        this.pghTipificacionSancionPK = new PghTipificacionSancionPK(idTipificacion, idTipoSancion);
    }

    public PghTipificacionSancionPK getPghTipificacionSancionPK() {
        return pghTipificacionSancionPK;
    }

    public void setPghTipificacionSancionPK(PghTipificacionSancionPK pghTipificacionSancionPK) {
        this.pghTipificacionSancionPK = pghTipificacionSancionPK;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public PghTipoSancion getPghTipoSancion() {
        return pghTipoSancion;
    }

    public void setPghTipoSancion(PghTipoSancion pghTipoSancion) {
        this.pghTipoSancion = pghTipoSancion;
    }

    public PghTipificacion getPghTipificacion() {
        return pghTipificacion;
    }

    public void setPghTipificacion(PghTipificacion pghTipificacion) {
        this.pghTipificacion = pghTipificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pghTipificacionSancionPK != null ? pghTipificacionSancionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghTipificacionSancion)) {
            return false;
        }
        PghTipificacionSancion other = (PghTipificacionSancion) object;
        if ((this.pghTipificacionSancionPK == null && other.pghTipificacionSancionPK != null) || (this.pghTipificacionSancionPK != null && !this.pghTipificacionSancionPK.equals(other.pghTipificacionSancionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PghTipificacionSancion[ pghTipificacionSancionPK=" + pghTipificacionSancionPK + " ]";
    }
    
}
