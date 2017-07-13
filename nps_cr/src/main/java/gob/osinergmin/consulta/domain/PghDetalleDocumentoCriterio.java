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
@Table(name = "PGH_DETALLE_DOCUMENTO_CRITERIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PghDetalleDocumentoCriterio.findAll", query = "SELECT p FROM PghDetalleDocumentoCriterio p"),
    @NamedQuery(name = "PghDetalleDocumentoCriterio.findByTitulo", query = "SELECT p FROM PghDetalleDocumentoCriterio p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "PghDetalleDocumentoCriterio.findByEstado", query = "SELECT p FROM PghDetalleDocumentoCriterio p WHERE p.estado = :estado"),
    @NamedQuery(name = "PghDetalleDocumentoCriterio.findByUsuarioCreacion", query = "SELECT p FROM PghDetalleDocumentoCriterio p WHERE p.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "PghDetalleDocumentoCriterio.findByFechaCreacion", query = "SELECT p FROM PghDetalleDocumentoCriterio p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PghDetalleDocumentoCriterio.findByTerminalCreacion", query = "SELECT p FROM PghDetalleDocumentoCriterio p WHERE p.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "PghDetalleDocumentoCriterio.findByUsuarioActualizacion", query = "SELECT p FROM PghDetalleDocumentoCriterio p WHERE p.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "PghDetalleDocumentoCriterio.findByFechaActualizacion", query = "SELECT p FROM PghDetalleDocumentoCriterio p WHERE p.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "PghDetalleDocumentoCriterio.findByTerminalActualizacion", query = "SELECT p FROM PghDetalleDocumentoCriterio p WHERE p.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "PghDetalleDocumentoCriterio.findByIdDetalleDocumentoCriterio", query = "SELECT p FROM PghDetalleDocumentoCriterio p WHERE p.idDetalleDocumentoCriterio = :idDetalleDocumentoCriterio")})
public class PghDetalleDocumentoCriterio extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "ESTADO")
    private Character estado;    
    @Id
    @Basic(optional = false)
    @Column(name = "ID_DETALLE_DOCUMENTO_CRITERIO")
    private Long idDetalleDocumentoCriterio;
    @JoinColumn(name = "ID_CRITERIO", referencedColumnName = "ID_CRITERIO")
    @ManyToOne
    private PghCriterio idCriterio;

    public PghDetalleDocumentoCriterio() {
    }

    public PghDetalleDocumentoCriterio(Long idDetalleDocumentoCriterio) {
        this.idDetalleDocumentoCriterio = idDetalleDocumentoCriterio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Long getIdDetalleDocumentoCriterio() {
        return idDetalleDocumentoCriterio;
    }

    public void setIdDetalleDocumentoCriterio(Long idDetalleDocumentoCriterio) {
        this.idDetalleDocumentoCriterio = idDetalleDocumentoCriterio;
    }

    public PghCriterio getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(PghCriterio idCriterio) {
        this.idCriterio = idCriterio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleDocumentoCriterio != null ? idDetalleDocumentoCriterio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghDetalleDocumentoCriterio)) {
            return false;
        }
        PghDetalleDocumentoCriterio other = (PghDetalleDocumentoCriterio) object;
        if ((this.idDetalleDocumentoCriterio == null && other.idDetalleDocumentoCriterio != null) || (this.idDetalleDocumentoCriterio != null && !this.idDetalleDocumentoCriterio.equals(other.idDetalleDocumentoCriterio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PghDetalleDocumentoCriterio[ idDetalleDocumentoCriterio=" + idDetalleDocumentoCriterio + " ]";
    }
    
}
