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
@Table(name = "PGH_DETALLE_CRITERIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PghDetalleCriterio.findAll", query = "SELECT p FROM PghDetalleCriterio p"),
    @NamedQuery(name = "PghDetalleCriterio.findByIdDetalleCriterio", query = "SELECT p FROM PghDetalleCriterio p WHERE p.idDetalleCriterio = :idDetalleCriterio"),
    @NamedQuery(name = "PghDetalleCriterio.findBySancionEspecifica", query = "SELECT p FROM PghDetalleCriterio p WHERE p.sancionEspecifica = :sancionEspecifica"),
    @NamedQuery(name = "PghDetalleCriterio.findByUsuarioCreacion", query = "SELECT p FROM PghDetalleCriterio p WHERE p.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "PghDetalleCriterio.findByFechaCreacion", query = "SELECT p FROM PghDetalleCriterio p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PghDetalleCriterio.findByTerminalCreacion", query = "SELECT p FROM PghDetalleCriterio p WHERE p.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "PghDetalleCriterio.findByUsuarioActualizacion", query = "SELECT p FROM PghDetalleCriterio p WHERE p.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "PghDetalleCriterio.findByFechaActualizacion", query = "SELECT p FROM PghDetalleCriterio p WHERE p.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "PghDetalleCriterio.findByTerminalActualizacion", query = "SELECT p FROM PghDetalleCriterio p WHERE p.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "PghDetalleCriterio.findByEstado", query = "SELECT p FROM PghDetalleCriterio p WHERE p.estado = :estado")})
public class PghDetalleCriterio extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_DETALLE_CRITERIO")
    private Long idDetalleCriterio;
    @Column(name = "SANCION_ESPECIFICA")
    private String sancionEspecifica;    
    @Column(name = "ESTADO")
    private Character estado;
    @JoinColumn(name = "ID_CRITERIO", referencedColumnName = "ID_CRITERIO")
    @ManyToOne
    private PghCriterio idCriterio;

    public PghDetalleCriterio() {
    }

    public PghDetalleCriterio(Long idDetalleCriterio) {
        this.idDetalleCriterio = idDetalleCriterio;
    }

    public Long getIdDetalleCriterio() {
        return idDetalleCriterio;
    }

    public void setIdDetalleCriterio(Long idDetalleCriterio) {
        this.idDetalleCriterio = idDetalleCriterio;
    }

    public String getSancionEspecifica() {
        return sancionEspecifica;
    }

    public void setSancionEspecifica(String sancionEspecifica) {
        this.sancionEspecifica = sancionEspecifica;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
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
        hash += (idDetalleCriterio != null ? idDetalleCriterio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghDetalleCriterio)) {
            return false;
        }
        PghDetalleCriterio other = (PghDetalleCriterio) object;
        if ((this.idDetalleCriterio == null && other.idDetalleCriterio != null) || (this.idDetalleCriterio != null && !this.idDetalleCriterio.equals(other.idDetalleCriterio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PghDetalleCriterio[ idDetalleCriterio=" + idDetalleCriterio + " ]";
    }
    
}
