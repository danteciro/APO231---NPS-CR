/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "PGH_CRITERIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PghCriterio.findAll", query = "SELECT p FROM PghCriterio p"),
    @NamedQuery(name = "PghCriterio.findByIdCriterio", query = "SELECT p FROM PghCriterio p WHERE p.idCriterio = :idCriterio"),
    @NamedQuery(name = "PghCriterio.findByDescripcion", query = "SELECT p FROM PghCriterio p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "PghCriterio.findByUsuarioCreacion", query = "SELECT p FROM PghCriterio p WHERE p.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "PghCriterio.findByFechaCreacion", query = "SELECT p FROM PghCriterio p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PghCriterio.findByTerminalCreacion", query = "SELECT p FROM PghCriterio p WHERE p.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "PghCriterio.findByUsuarioActualizacion", query = "SELECT p FROM PghCriterio p WHERE p.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "PghCriterio.findByFechaActualizacion", query = "SELECT p FROM PghCriterio p WHERE p.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "PghCriterio.findByTerminalActualizacion", query = "SELECT p FROM PghCriterio p WHERE p.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "PghCriterio.findByEstado", query = "SELECT p FROM PghCriterio p WHERE p.estado = :estado")})
public class PghCriterio extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CRITERIO")
    private Long idCriterio;
    @Column(name = "DESCRIPCION")
    private String descripcion;    
    @Column(name = "ESTADO")
    private Character estado;
    @OneToMany(mappedBy = "idCriterio")
    private List<PghDetalleDocumentoCriterio> pghDetalleDocumentoCriterioList;
    @JoinColumn(name = "ID_TIPIFICACION", referencedColumnName = "ID_TIPIFICACION")
    @ManyToOne(optional = false)
    private PghTipificacion idTipificacion;
    @JoinColumn(name = "ID_OBLIGACION", referencedColumnName = "ID_OBLIGACION")
    @ManyToOne
    private PghObligacion idObligacion;
    @OneToMany(mappedBy = "idCriterio")
    private List<PghDetalleCriterio> pghDetalleCriterioList;

    public PghCriterio() {
    }

    public PghCriterio(Long idCriterio) {
        this.idCriterio = idCriterio;
    }

    public Long getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(Long idCriterio) {
        this.idCriterio = idCriterio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<PghDetalleDocumentoCriterio> getPghDetalleDocumentoCriterioList() {
        return pghDetalleDocumentoCriterioList;
    }

    public void setPghDetalleDocumentoCriterioList(List<PghDetalleDocumentoCriterio> pghDetalleDocumentoCriterioList) {
        this.pghDetalleDocumentoCriterioList = pghDetalleDocumentoCriterioList;
    }

    public PghTipificacion getIdTipificacion() {
        return idTipificacion;
    }

    public void setIdTipificacion(PghTipificacion idTipificacion) {
        this.idTipificacion = idTipificacion;
    }

    public PghObligacion getIdObligacion() {
        return idObligacion;
    }

    public void setIdObligacion(PghObligacion idObligacion) {
        this.idObligacion = idObligacion;
    }

    @XmlTransient
    public List<PghDetalleCriterio> getPghDetalleCriterioList() {
        return pghDetalleCriterioList;
    }

    public void setPghDetalleCriterioList(List<PghDetalleCriterio> pghDetalleCriterioList) {
        this.pghDetalleCriterioList = pghDetalleCriterioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCriterio != null ? idCriterio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghCriterio)) {
            return false;
        }
        PghCriterio other = (PghCriterio) object;
        if ((this.idCriterio == null && other.idCriterio != null) || (this.idCriterio != null && !this.idCriterio.equals(other.idCriterio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PghCriterio[ idCriterio=" + idCriterio + " ]";
    }
    
}
