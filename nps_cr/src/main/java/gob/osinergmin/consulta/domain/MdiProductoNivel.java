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
@Table(name = "MDI_PRODUCTO_NIVEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiProductoNivel.findAll", query = "SELECT m FROM MdiProductoNivel m"),
    @NamedQuery(name = "MdiProductoNivel.findByIdProducto", query = "SELECT m FROM MdiProductoNivel m WHERE m.mdiProductoNivelPK.idProducto = :idProducto"),
    @NamedQuery(name = "MdiProductoNivel.findByNivel", query = "SELECT m FROM MdiProductoNivel m WHERE m.mdiProductoNivelPK.nivel = :nivel"),
    @NamedQuery(name = "MdiProductoNivel.findByNombre", query = "SELECT m FROM MdiProductoNivel m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MdiProductoNivel.findByDescripcion", query = "SELECT m FROM MdiProductoNivel m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "MdiProductoNivel.findByFechaCreacion", query = "SELECT m FROM MdiProductoNivel m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiProductoNivel.findByUsuarioCreacion", query = "SELECT m FROM MdiProductoNivel m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiProductoNivel.findByTerminalCreacion", query = "SELECT m FROM MdiProductoNivel m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiProductoNivel.findByFechaActualizacion", query = "SELECT m FROM MdiProductoNivel m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiProductoNivel.findByUsuarioActualizacion", query = "SELECT m FROM MdiProductoNivel m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiProductoNivel.findByTerminalActualizacion", query = "SELECT m FROM MdiProductoNivel m WHERE m.terminalActualizacion = :terminalActualizacion")})
public class MdiProductoNivel extends Auditoria {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MdiProductoNivelPK mdiProductoNivelPK;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;    
    @JoinColumn(name = "ID_PRODUCTO", referencedColumnName = "ID_PRODUCTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MdiProducto mdiProducto;

    public MdiProductoNivel() {
    }

    public MdiProductoNivel(MdiProductoNivelPK mdiProductoNivelPK) {
        this.mdiProductoNivelPK = mdiProductoNivelPK;
    }

    public MdiProductoNivel(MdiProductoNivelPK mdiProductoNivelPK, String nombre, String descripcion, Date fechaCreacion, String usuarioCreacion, String terminalCreacion) {
        this.mdiProductoNivelPK = mdiProductoNivelPK;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public MdiProductoNivel(long idProducto, int nivel) {
        this.mdiProductoNivelPK = new MdiProductoNivelPK(idProducto, nivel);
    }

    public MdiProductoNivelPK getMdiProductoNivelPK() {
        return mdiProductoNivelPK;
    }

    public void setMdiProductoNivelPK(MdiProductoNivelPK mdiProductoNivelPK) {
        this.mdiProductoNivelPK = mdiProductoNivelPK;
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

    public MdiProducto getMdiProducto() {
        return mdiProducto;
    }

    public void setMdiProducto(MdiProducto mdiProducto) {
        this.mdiProducto = mdiProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mdiProductoNivelPK != null ? mdiProductoNivelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiProductoNivel)) {
            return false;
        }
        MdiProductoNivel other = (MdiProductoNivel) object;
        if ((this.mdiProductoNivelPK == null && other.mdiProductoNivelPK != null) || (this.mdiProductoNivelPK != null && !this.mdiProductoNivelPK.equals(other.mdiProductoNivelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiProductoNivel[ mdiProductoNivelPK=" + mdiProductoNivelPK + " ]";
    }
    
}
