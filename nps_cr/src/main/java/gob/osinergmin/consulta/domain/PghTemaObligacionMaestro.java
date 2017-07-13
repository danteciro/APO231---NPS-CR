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
@Table(name = "PGH_TEMA_OBLIGACION_MAESTRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PghTemaObligacionMaestro.findAll", query = "SELECT p FROM PghTemaObligacionMaestro p"),
    @NamedQuery(name = "PghTemaObligacionMaestro.findByIdMaestroColumna", query = "SELECT p FROM PghTemaObligacionMaestro p WHERE p.pghTemaObligacionMaestroPK.idMaestroColumna = :idMaestroColumna"),
    @NamedQuery(name = "PghTemaObligacionMaestro.findByIdObligacion", query = "SELECT p FROM PghTemaObligacionMaestro p WHERE p.pghTemaObligacionMaestroPK.idObligacion = :idObligacion"),
    @NamedQuery(name = "PghTemaObligacionMaestro.findByEstado", query = "SELECT p FROM PghTemaObligacionMaestro p WHERE p.estado = :estado"),
    @NamedQuery(name = "PghTemaObligacionMaestro.findByUsuarioCreacion", query = "SELECT p FROM PghTemaObligacionMaestro p WHERE p.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "PghTemaObligacionMaestro.findByFechaCreacion", query = "SELECT p FROM PghTemaObligacionMaestro p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PghTemaObligacionMaestro.findByTerminalCreacion", query = "SELECT p FROM PghTemaObligacionMaestro p WHERE p.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "PghTemaObligacionMaestro.findByUsuarioActualizacion", query = "SELECT p FROM PghTemaObligacionMaestro p WHERE p.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "PghTemaObligacionMaestro.findByFechaActualizacion", query = "SELECT p FROM PghTemaObligacionMaestro p WHERE p.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "PghTemaObligacionMaestro.findByTerminalActualizacion", query = "SELECT p FROM PghTemaObligacionMaestro p WHERE p.terminalActualizacion = :terminalActualizacion")})
public class PghTemaObligacionMaestro extends Auditoria {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PghTemaObligacionMaestroPK pghTemaObligacionMaestroPK;
    @Column(name = "ESTADO")
    private Character estado;    
    @JoinColumn(name = "ID_OBLIGACION", referencedColumnName = "ID_OBLIGACION", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PghObligacion pghObligacion;
    @JoinColumn(name = "ID_MAESTRO_COLUMNA", referencedColumnName = "ID_MAESTRO_COLUMNA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MdiMaestroColumna mdiMaestroColumna;

    public PghTemaObligacionMaestro() {
    }

    public PghTemaObligacionMaestro(PghTemaObligacionMaestroPK pghTemaObligacionMaestroPK) {
        this.pghTemaObligacionMaestroPK = pghTemaObligacionMaestroPK;
    }

    public PghTemaObligacionMaestro(long idMaestroColumna, long idObligacion) {
        this.pghTemaObligacionMaestroPK = new PghTemaObligacionMaestroPK(idMaestroColumna, idObligacion);
    }

    public PghTemaObligacionMaestroPK getPghTemaObligacionMaestroPK() {
        return pghTemaObligacionMaestroPK;
    }

    public void setPghTemaObligacionMaestroPK(PghTemaObligacionMaestroPK pghTemaObligacionMaestroPK) {
        this.pghTemaObligacionMaestroPK = pghTemaObligacionMaestroPK;
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

    public MdiMaestroColumna getMdiMaestroColumna() {
        return mdiMaestroColumna;
    }

    public void setMdiMaestroColumna(MdiMaestroColumna mdiMaestroColumna) {
        this.mdiMaestroColumna = mdiMaestroColumna;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pghTemaObligacionMaestroPK != null ? pghTemaObligacionMaestroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghTemaObligacionMaestro)) {
            return false;
        }
        PghTemaObligacionMaestro other = (PghTemaObligacionMaestro) object;
        if ((this.pghTemaObligacionMaestroPK == null && other.pghTemaObligacionMaestroPK != null) || (this.pghTemaObligacionMaestroPK != null && !this.pghTemaObligacionMaestroPK.equals(other.pghTemaObligacionMaestroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PghTemaObligacionMaestro[ pghTemaObligacionMaestroPK=" + pghTemaObligacionMaestroPK + " ]";
    }
    
}
