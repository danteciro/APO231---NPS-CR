/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.util.Date;

import javax.persistence.Basic;
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
@Table(name = "MDI_ACTIVIDAD_NIVEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiActividadNivel.findAll", query = "SELECT m FROM MdiActividadNivel m"),
    @NamedQuery(name = "MdiActividadNivel.findByNivel", query = "SELECT m FROM MdiActividadNivel m WHERE m.mdiActividadNivelPK.nivel = :nivel"),
    @NamedQuery(name = "MdiActividadNivel.findByNombre", query = "SELECT m FROM MdiActividadNivel m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MdiActividadNivel.findByDescripcion", query = "SELECT m FROM MdiActividadNivel m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "MdiActividadNivel.findByIdActividad", query = "SELECT m FROM MdiActividadNivel m WHERE m.mdiActividadNivelPK.idActividad = :idActividad"),
    @NamedQuery(name = "MdiActividadNivel.findByTerminalActualizacion", query = "SELECT m FROM MdiActividadNivel m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiActividadNivel.findByTerminalCreacion", query = "SELECT m FROM MdiActividadNivel m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiActividadNivel.findByUsuarioCreacion", query = "SELECT m FROM MdiActividadNivel m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiActividadNivel.findByUsuarioActualizacion", query = "SELECT m FROM MdiActividadNivel m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiActividadNivel.findByFechaActualizacion", query = "SELECT m FROM MdiActividadNivel m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiActividadNivel.findByFechaCreacion", query = "SELECT m FROM MdiActividadNivel m WHERE m.fechaCreacion = :fechaCreacion")})
public class MdiActividadNivel extends Auditoria {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MdiActividadNivelPK mdiActividadNivelPK;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "ID_ACTIVIDAD", referencedColumnName = "ID_ACTIVIDAD", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MdiActividad mdiActividad;

    public MdiActividadNivel() {
    }

    public MdiActividadNivel(MdiActividadNivelPK mdiActividadNivelPK) {
        this.mdiActividadNivelPK = mdiActividadNivelPK;
    }

    public MdiActividadNivel(MdiActividadNivelPK mdiActividadNivelPK, String nombre, String descripcion, String terminalCreacion, String usuarioCreacion, Date fechaCreacion) {
        this.mdiActividadNivelPK = mdiActividadNivelPK;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.terminalCreacion = terminalCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
    }

    public MdiActividadNivel(int nivel, long idActividad) {
        this.mdiActividadNivelPK = new MdiActividadNivelPK(nivel, idActividad);
    }

    public MdiActividadNivelPK getMdiActividadNivelPK() {
        return mdiActividadNivelPK;
    }

    public void setMdiActividadNivelPK(MdiActividadNivelPK mdiActividadNivelPK) {
        this.mdiActividadNivelPK = mdiActividadNivelPK;
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
    
    public MdiActividad getMdiActividad() {
        return mdiActividad;
    }

    public void setMdiActividad(MdiActividad mdiActividad) {
        this.mdiActividad = mdiActividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mdiActividadNivelPK != null ? mdiActividadNivelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiActividadNivel)) {
            return false;
        }
        MdiActividadNivel other = (MdiActividadNivel) object;
        if ((this.mdiActividadNivelPK == null && other.mdiActividadNivelPK != null) || (this.mdiActividadNivelPK != null && !this.mdiActividadNivelPK.equals(other.mdiActividadNivelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiActividadNivel[ mdiActividadNivelPK=" + mdiActividadNivelPK + " ]";
    }
    
}
