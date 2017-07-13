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
@Table(name = "PGH_LISTADO_BASE_LEGAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PghListadoBaseLegal.findAll", query = "SELECT p FROM PghListadoBaseLegal p"),
    @NamedQuery(name = "PghListadoBaseLegal.findByIdListadoBaseLegal", query = "SELECT p FROM PghListadoBaseLegal p WHERE p.idListadoBaseLegal = :idListadoBaseLegal"),
    @NamedQuery(name = "PghListadoBaseLegal.findByEstado", query = "SELECT p FROM PghListadoBaseLegal p WHERE p.estado = :estado"),
    @NamedQuery(name = "PghListadoBaseLegal.findByUsuarioCreacion", query = "SELECT p FROM PghListadoBaseLegal p WHERE p.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "PghListadoBaseLegal.findByFechaCreacion", query = "SELECT p FROM PghListadoBaseLegal p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PghListadoBaseLegal.findByTerminalCreacion", query = "SELECT p FROM PghListadoBaseLegal p WHERE p.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "PghListadoBaseLegal.findByUsuarioActualizacion", query = "SELECT p FROM PghListadoBaseLegal p WHERE p.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "PghListadoBaseLegal.findByFechaActualizacion", query = "SELECT p FROM PghListadoBaseLegal p WHERE p.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "PghListadoBaseLegal.findByTerminalActualizacion", query = "SELECT p FROM PghListadoBaseLegal p WHERE p.terminalActualizacion = :terminalActualizacion")})
public class PghListadoBaseLegal extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_LISTADO_BASE_LEGAL")
    private Long idListadoBaseLegal;
    @Column(name = "ESTADO")
    private Character estado;    
    @JoinColumn(name = "ID_DETALLE_BASE_LEGAL", referencedColumnName = "ID_DETALLE_BASE_LEGAL")
    @ManyToOne
    private PghDetalleBaseLegal idDetalleBaseLegal;
    @JoinColumn(name = "ID_BASE_LEGAL", referencedColumnName = "ID_BASE_LEGAL")
    @ManyToOne
    private PghBaseLegal idBaseLegal;

    public PghListadoBaseLegal() {
    }

    public PghListadoBaseLegal(Long idListadoBaseLegal) {
        this.idListadoBaseLegal = idListadoBaseLegal;
    }

    public Long getIdListadoBaseLegal() {
        return idListadoBaseLegal;
    }

    public void setIdListadoBaseLegal(Long idListadoBaseLegal) {
        this.idListadoBaseLegal = idListadoBaseLegal;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public PghDetalleBaseLegal getIdDetalleBaseLegal() {
        return idDetalleBaseLegal;
    }

    public void setIdDetalleBaseLegal(PghDetalleBaseLegal idDetalleBaseLegal) {
        this.idDetalleBaseLegal = idDetalleBaseLegal;
    }

    public PghBaseLegal getIdBaseLegal() {
        return idBaseLegal;
    }

    public void setIdBaseLegal(PghBaseLegal idBaseLegal) {
        this.idBaseLegal = idBaseLegal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idListadoBaseLegal != null ? idListadoBaseLegal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghListadoBaseLegal)) {
            return false;
        }
        PghListadoBaseLegal other = (PghListadoBaseLegal) object;
        if ((this.idListadoBaseLegal == null && other.idListadoBaseLegal != null) || (this.idListadoBaseLegal != null && !this.idListadoBaseLegal.equals(other.idListadoBaseLegal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PghListadoBaseLegal[ idListadoBaseLegal=" + idListadoBaseLegal + " ]";
    }
    
}
