/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cflorian
 */
@Entity
@Table(name = "PGH_PROCESO_OBLIGACION_TIPO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PghProcesoObligacionTipo.findAll", query = "SELECT p FROM PghProcesoObligacionTipo p"),
    @NamedQuery(name = "PghProcesoObligacionTipo.findByIdObligacionTipo", query = "SELECT p FROM PghProcesoObligacionTipo p WHERE p.pghProcesoObligacionTipoPK.idObligacionTipo = :idObligacionTipo"),
    @NamedQuery(name = "PghProcesoObligacionTipo.findByIdProceso", query = "SELECT p FROM PghProcesoObligacionTipo p WHERE p.pghProcesoObligacionTipoPK.idProceso = :idProceso"),
    @NamedQuery(name = "PghProcesoObligacionTipo.findByEstado", query = "SELECT p FROM PghProcesoObligacionTipo p WHERE p.estado = :estado"),
    @NamedQuery(name = "PghProcesoObligacionTipo.findByUsuarioCreacion", query = "SELECT p FROM PghProcesoObligacionTipo p WHERE p.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "PghProcesoObligacionTipo.findByFechaCreacion", query = "SELECT p FROM PghProcesoObligacionTipo p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PghProcesoObligacionTipo.findByTerminalCreacion", query = "SELECT p FROM PghProcesoObligacionTipo p WHERE p.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "PghProcesoObligacionTipo.findByUsuarioActualizacion", query = "SELECT p FROM PghProcesoObligacionTipo p WHERE p.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "PghProcesoObligacionTipo.findByFechaActualizacion", query = "SELECT p FROM PghProcesoObligacionTipo p WHERE p.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "PghProcesoObligacionTipo.findByTerminalActualizacion", query = "SELECT p FROM PghProcesoObligacionTipo p WHERE p.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "PghProcesoObligacionTipo.findByIdActividad", query = "SELECT p FROM PghProcesoObligacionTipo p WHERE p.pghProcesoObligacionTipoPK.idActividad = :idActividad")})
public class PghProcesoObligacionTipo extends Auditoria {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PghProcesoObligacionTipoPK pghProcesoObligacionTipoPK;
    @Column(name = "ESTADO")
    private Character estado;
    @JoinColumn(name = "ID_PROCESO", referencedColumnName = "ID_PROCESO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PghProceso pghProceso;
    @JoinColumn(name = "ID_OBLIGACION_TIPO", referencedColumnName = "ID_OBLIGACION_TIPO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PghObligacionTipo pghObligacionTipo;
    @JoinColumn(name = "ID_ACTIVIDAD", referencedColumnName = "ID_ACTIVIDAD", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MdiActividad mdiActividad;
    @OneToMany(mappedBy = "pghProcesoObligacionTipo")
    private List<PghConfObligacion> pghConfObligacionList;

    public PghProcesoObligacionTipo() {
    }

    public PghProcesoObligacionTipo(PghProcesoObligacionTipoPK pghProcesoObligacionTipoPK) {
        this.pghProcesoObligacionTipoPK = pghProcesoObligacionTipoPK;
    }

    public PghProcesoObligacionTipo(long idObligacionTipo, long idProceso, long idActividad) {
        this.pghProcesoObligacionTipoPK = new PghProcesoObligacionTipoPK(idObligacionTipo, idProceso, idActividad);
    }

    public PghProcesoObligacionTipoPK getPghProcesoObligacionTipoPK() {
        return pghProcesoObligacionTipoPK;
    }

    public void setPghProcesoObligacionTipoPK(PghProcesoObligacionTipoPK pghProcesoObligacionTipoPK) {
        this.pghProcesoObligacionTipoPK = pghProcesoObligacionTipoPK;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public PghProceso getPghProceso() {
        return pghProceso;
    }

    public void setPghProceso(PghProceso pghProceso) {
        this.pghProceso = pghProceso;
    }

    public PghObligacionTipo getPghObligacionTipo() {
        return pghObligacionTipo;
    }

    public void setPghObligacionTipo(PghObligacionTipo pghObligacionTipo) {
        this.pghObligacionTipo = pghObligacionTipo;
    }

    public MdiActividad getMdiActividad() {
        return mdiActividad;
    }

    public void setMdiActividad(MdiActividad mdiActividad) {
        this.mdiActividad = mdiActividad;
    }

    @XmlTransient
    public List<PghConfObligacion> getPghConfObligacionList() {
        return pghConfObligacionList;
    }

    public void setPghConfObligacionList(List<PghConfObligacion> pghConfObligacionList) {
        this.pghConfObligacionList = pghConfObligacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pghProcesoObligacionTipoPK != null ? pghProcesoObligacionTipoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghProcesoObligacionTipo)) {
            return false;
        }
        PghProcesoObligacionTipo other = (PghProcesoObligacionTipo) object;
        if ((this.pghProcesoObligacionTipoPK == null && other.pghProcesoObligacionTipoPK != null) || (this.pghProcesoObligacionTipoPK != null && !this.pghProcesoObligacionTipoPK.equals(other.pghProcesoObligacionTipoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinerg.consulta.domain.PghProcesoObligacionTipo[ pghProcesoObligacionTipoPK=" + pghProcesoObligacionTipoPK + " ]";
    }
    
}
