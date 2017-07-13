/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.util.Date;
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
@Table(name = "MDI_MACRO_REGION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiMacroRegion.findAll", query = "SELECT m FROM MdiMacroRegion m"),
    @NamedQuery(name = "MdiMacroRegion.findByIdMacroRegion", query = "SELECT m FROM MdiMacroRegion m WHERE m.idMacroRegion = :idMacroRegion"),
    @NamedQuery(name = "MdiMacroRegion.findByNombre", query = "SELECT m FROM MdiMacroRegion m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MdiMacroRegion.findByFechaCreacion", query = "SELECT m FROM MdiMacroRegion m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiMacroRegion.findByFechaActualizacion", query = "SELECT m FROM MdiMacroRegion m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiMacroRegion.findByTerminalCreacion", query = "SELECT m FROM MdiMacroRegion m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiMacroRegion.findByTerminalActualizacion", query = "SELECT m FROM MdiMacroRegion m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiMacroRegion.findByUsuarioCreacion", query = "SELECT m FROM MdiMacroRegion m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiMacroRegion.findByUsuarioActualizacion", query = "SELECT m FROM MdiMacroRegion m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiMacroRegion.findByDescripcion", query = "SELECT m FROM MdiMacroRegion m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "MdiMacroRegion.findByEstado", query = "SELECT m FROM MdiMacroRegion m WHERE m.estado = :estado")})
public class MdiMacroRegion extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_MACRO_REGION")
    private Long idMacroRegion;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mdiMacroRegion")
    private List<MdiMacroRegionXRegion> mdiMacroRegionXRegionList;

    public MdiMacroRegion() {
    }

    public MdiMacroRegion(Long idMacroRegion) {
        this.idMacroRegion = idMacroRegion;
    }

    public MdiMacroRegion(Long idMacroRegion, String nombre, Date fechaCreacion, String terminalCreacion, String usuarioCreacion, char estado) {
        this.idMacroRegion = idMacroRegion;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.estado = estado;
    }

    public Long getIdMacroRegion() {
        return idMacroRegion;
    }

    public void setIdMacroRegion(Long idMacroRegion) {
        this.idMacroRegion = idMacroRegion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<MdiMacroRegionXRegion> getMdiMacroRegionXRegionList() {
        return mdiMacroRegionXRegionList;
    }

    public void setMdiMacroRegionXRegionList(List<MdiMacroRegionXRegion> mdiMacroRegionXRegionList) {
        this.mdiMacroRegionXRegionList = mdiMacroRegionXRegionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMacroRegion != null ? idMacroRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiMacroRegion)) {
            return false;
        }
        MdiMacroRegion other = (MdiMacroRegion) object;
        if ((this.idMacroRegion == null && other.idMacroRegion != null) || (this.idMacroRegion != null && !this.idMacroRegion.equals(other.idMacroRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiMacroRegion[ idMacroRegion=" + idMacroRegion + " ]";
    }
    
}
