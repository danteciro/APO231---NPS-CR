/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "PGH_OBLIGACION_TIPO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PghObligacionTipo.findAll", query = "SELECT p FROM PghObligacionTipo p"),
    @NamedQuery(name = "PghObligacionTipo.findByIdObligacionTipo", query = "SELECT p FROM PghObligacionTipo p WHERE p.idObligacionTipo = :idObligacionTipo"),
    @NamedQuery(name = "PghObligacionTipo.findByNombre", query = "SELECT p FROM PghObligacionTipo p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PghObligacionTipo.findByUsuarioCreacion", query = "SELECT p FROM PghObligacionTipo p WHERE p.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "PghObligacionTipo.findByFechaCreacion", query = "SELECT p FROM PghObligacionTipo p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PghObligacionTipo.findByTerminalCreacion", query = "SELECT p FROM PghObligacionTipo p WHERE p.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "PghObligacionTipo.findByUsuarioActualizacion", query = "SELECT p FROM PghObligacionTipo p WHERE p.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "PghObligacionTipo.findByFechaActualizacion", query = "SELECT p FROM PghObligacionTipo p WHERE p.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "PghObligacionTipo.findByTerminalActualizacion", query = "SELECT p FROM PghObligacionTipo p WHERE p.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "PghObligacionTipo.findByEstado", query = "SELECT p FROM PghObligacionTipo p WHERE p.estado = :estado")})
public class PghObligacionTipo extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_OBLIGACION_TIPO")
    private Long idObligacionTipo;
    @Column(name = "NOMBRE")
    private String nombre;    
    @Column(name = "ESTADO")
    private Character estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pghObligacionTipo")
    private List<PghProcesoObligacionTipo> pghProcesoObligacionTipoList;

    public PghObligacionTipo() {
    }

    public PghObligacionTipo(Long idObligacionTipo) {
        this.idObligacionTipo = idObligacionTipo;
    }

    public Long getIdObligacionTipo() {
        return idObligacionTipo;
    }

    public void setIdObligacionTipo(Long idObligacionTipo) {
        this.idObligacionTipo = idObligacionTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }    

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<PghProcesoObligacionTipo> getPghProcesoObligacionTipoList() {
        return pghProcesoObligacionTipoList;
    }

    public void setPghProcesoObligacionTipoList(List<PghProcesoObligacionTipo> pghProcesoObligacionTipoList) {
        this.pghProcesoObligacionTipoList = pghProcesoObligacionTipoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObligacionTipo != null ? idObligacionTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghObligacionTipo)) {
            return false;
        }
        PghObligacionTipo other = (PghObligacionTipo) object;
        if ((this.idObligacionTipo == null && other.idObligacionTipo != null) || (this.idObligacionTipo != null && !this.idObligacionTipo.equals(other.idObligacionTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PghObligacionTipo[ idObligacionTipo=" + idObligacionTipo + " ]";
    }
    
}
