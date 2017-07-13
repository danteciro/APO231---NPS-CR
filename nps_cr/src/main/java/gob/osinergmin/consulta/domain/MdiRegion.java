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
@Table(name = "MDI_REGION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiRegion.findAll", query = "SELECT m FROM MdiRegion m"),
    @NamedQuery(name = "MdiRegion.findByIdRegion", query = "SELECT m FROM MdiRegion m WHERE m.idRegion = :idRegion"),
    @NamedQuery(name = "MdiRegion.findByNombre", query = "SELECT m FROM MdiRegion m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MdiRegion.findByUsuarioActualizacion", query = "SELECT m FROM MdiRegion m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiRegion.findByUsuarioCreacion", query = "SELECT m FROM MdiRegion m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiRegion.findByFechaActualizacion", query = "SELECT m FROM MdiRegion m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiRegion.findByFechaCreacion", query = "SELECT m FROM MdiRegion m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiRegion.findByTerminalCreacion", query = "SELECT m FROM MdiRegion m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiRegion.findByTerminalActualizacion", query = "SELECT m FROM MdiRegion m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiRegion.findByJefeRegion", query = "SELECT m FROM MdiRegion m WHERE m.jefeRegion = :jefeRegion"),
    @NamedQuery(name = "MdiRegion.findByCorreoJefe", query = "SELECT m FROM MdiRegion m WHERE m.correoJefe = :correoJefe"),
    @NamedQuery(name = "MdiRegion.findByTelefono", query = "SELECT m FROM MdiRegion m WHERE m.telefono = :telefono"),
    @NamedQuery(name = "MdiRegion.findByEstado", query = "SELECT m FROM MdiRegion m WHERE m.estado = :estado")})
public class MdiRegion extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_REGION")
    private Long idRegion;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;   
    @Column(name = "JEFE_REGION")
    private String jefeRegion;
    @Column(name = "CORREO_JEFE")
    private String correoJefe;
    @Column(name = "TELEFONO")
    private Character telefono;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mdiRegion")
    private List<MdiMacroRegionXRegion> mdiMacroRegionXRegionList;
    @OneToMany(mappedBy = "idRegion")
    private List<MdiDireccionRegion> mdiDireccionRegionList;

    public MdiRegion() {
    }

    public MdiRegion(Long idRegion) {
        this.idRegion = idRegion;
    }

    public MdiRegion(Long idRegion, String nombre, String usuarioCreacion, Date fechaCreacion, String terminalCreacion, char estado) {
        this.idRegion = idRegion;
        this.nombre = nombre;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
        this.estado = estado;
    }

    public Long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Long idRegion) {
        this.idRegion = idRegion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getJefeRegion() {
        return jefeRegion;
    }

    public void setJefeRegion(String jefeRegion) {
        this.jefeRegion = jefeRegion;
    }

    public String getCorreoJefe() {
        return correoJefe;
    }

    public void setCorreoJefe(String correoJefe) {
        this.correoJefe = correoJefe;
    }

    public Character getTelefono() {
        return telefono;
    }

    public void setTelefono(Character telefono) {
        this.telefono = telefono;
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

    @XmlTransient
    public List<MdiDireccionRegion> getMdiDireccionRegionList() {
        return mdiDireccionRegionList;
    }

    public void setMdiDireccionRegionList(List<MdiDireccionRegion> mdiDireccionRegionList) {
        this.mdiDireccionRegionList = mdiDireccionRegionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegion != null ? idRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiRegion)) {
            return false;
        }
        MdiRegion other = (MdiRegion) object;
        if ((this.idRegion == null && other.idRegion != null) || (this.idRegion != null && !this.idRegion.equals(other.idRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiRegion[ idRegion=" + idRegion + " ]";
    }
    
}
