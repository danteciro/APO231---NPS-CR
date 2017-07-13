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
@Table(name = "PGH_OBLIGACION_BASE_LEGAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PghObligacionBaseLegal.findAll", query = "SELECT p FROM PghObligacionBaseLegal p"),
    @NamedQuery(name = "PghObligacionBaseLegal.findByIdObligacion", query = "SELECT p FROM PghObligacionBaseLegal p WHERE p.pghObligacionBaseLegalPK.idObligacion = :idObligacion"),
    @NamedQuery(name = "PghObligacionBaseLegal.findByIdBaseLegal", query = "SELECT p FROM PghObligacionBaseLegal p WHERE p.pghObligacionBaseLegalPK.idBaseLegal = :idBaseLegal"),
    @NamedQuery(name = "PghObligacionBaseLegal.findByEstado", query = "SELECT p FROM PghObligacionBaseLegal p WHERE p.estado = :estado"),
    @NamedQuery(name = "PghObligacionBaseLegal.findByUsuarioCreacion", query = "SELECT p FROM PghObligacionBaseLegal p WHERE p.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "PghObligacionBaseLegal.findByFechaCreacion", query = "SELECT p FROM PghObligacionBaseLegal p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PghObligacionBaseLegal.findByTerminalCreacion", query = "SELECT p FROM PghObligacionBaseLegal p WHERE p.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "PghObligacionBaseLegal.findByUsuarioActualizacion", query = "SELECT p FROM PghObligacionBaseLegal p WHERE p.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "PghObligacionBaseLegal.findByFechaActualizacion", query = "SELECT p FROM PghObligacionBaseLegal p WHERE p.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "PghObligacionBaseLegal.findByTerminalActualizacion", query = "SELECT p FROM PghObligacionBaseLegal p WHERE p.terminalActualizacion = :terminalActualizacion")})
public class PghObligacionBaseLegal extends Auditoria {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PghObligacionBaseLegalPK pghObligacionBaseLegalPK;
    @Column(name = "ESTADO")
    private Character estado;    
    @JoinColumn(name = "ID_OBLIGACION", referencedColumnName = "ID_OBLIGACION", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PghObligacion pghObligacion;
    @JoinColumn(name = "ID_BASE_LEGAL", referencedColumnName = "ID_BASE_LEGAL", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PghBaseLegal pghBaseLegal;

    public PghObligacionBaseLegal() {
    }

    public PghObligacionBaseLegal(PghObligacionBaseLegalPK pghObligacionBaseLegalPK) {
        this.pghObligacionBaseLegalPK = pghObligacionBaseLegalPK;
    }

    public PghObligacionBaseLegal(long idObligacion, long idBaseLegal) {
        this.pghObligacionBaseLegalPK = new PghObligacionBaseLegalPK(idObligacion, idBaseLegal);
    }

    public PghObligacionBaseLegalPK getPghObligacionBaseLegalPK() {
        return pghObligacionBaseLegalPK;
    }

    public void setPghObligacionBaseLegalPK(PghObligacionBaseLegalPK pghObligacionBaseLegalPK) {
        this.pghObligacionBaseLegalPK = pghObligacionBaseLegalPK;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public PghObligacion getPghObligacion() {
        return pghObligacion;
    }

    public void setPghObligacion(PghObligacion pghObligacion) {
        this.pghObligacion = pghObligacion;
    }

    public PghBaseLegal getPghBaseLegal() {
        return pghBaseLegal;
    }

    public void setPghBaseLegal(PghBaseLegal pghBaseLegal) {
        this.pghBaseLegal = pghBaseLegal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pghObligacionBaseLegalPK != null ? pghObligacionBaseLegalPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghObligacionBaseLegal)) {
            return false;
        }
        PghObligacionBaseLegal other = (PghObligacionBaseLegal) object;
        if ((this.pghObligacionBaseLegalPK == null && other.pghObligacionBaseLegalPK != null) || (this.pghObligacionBaseLegalPK != null && !this.pghObligacionBaseLegalPK.equals(other.pghObligacionBaseLegalPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PghObligacionBaseLegal[ pghObligacionBaseLegalPK=" + pghObligacionBaseLegalPK + " ]";
    }
    
}
