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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author lbarboza
 *
 */
@Entity
@Table(name = "MDI_MAESTRO_TABLA")
@NamedQueries({
    @NamedQuery(name = "MdiMaestroTabla.findAll", query = "SELECT m FROM MdiMaestroTabla m")
})
public class MdiMaestroTabla extends Auditoria {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MdiMaestroTablaPK mdiMaestroTablaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DESCRIPCION", nullable = false, length = 200)
    private String descripcion;
    @Size(max = 2)
    @Column(name = "ES_EDITABLE", length = 2)
    private String esEditable;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mdiMaestroTabla", fetch = FetchType.LAZY)
    private List<MdiMaestroColumna> mdiMaestroColumnaList;

    public MdiMaestroTabla() {
    }

    public MdiMaestroTabla(MdiMaestroTablaPK mdiMaestroTablaPK) {
        this.mdiMaestroTablaPK = mdiMaestroTablaPK;
    }

    public MdiMaestroTabla(MdiMaestroTablaPK mdiMaestroTablaPK, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.mdiMaestroTablaPK = mdiMaestroTablaPK;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public MdiMaestroTabla(String dominio, String aplicacion) {
        this.mdiMaestroTablaPK = new MdiMaestroTablaPK(dominio, aplicacion);
    }

    public MdiMaestroTablaPK getMdiMaestroTablaPK() {
        return mdiMaestroTablaPK;
    }

    public void setMdiMaestroTablaPK(MdiMaestroTablaPK mdiMaestroTablaPK) {
        this.mdiMaestroTablaPK = mdiMaestroTablaPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEsEditable() {
        return esEditable;
    }

    public void setEsEditable(String esEditable) {
        this.esEditable = esEditable;
    }

    public List<MdiMaestroColumna> getMdiMaestroColumnaList() {
        return mdiMaestroColumnaList;
    }

    public void setMdiMaestroColumnaList(List<MdiMaestroColumna> mdiMaestroColumnaList) {
        this.mdiMaestroColumnaList = mdiMaestroColumnaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mdiMaestroTablaPK != null ? mdiMaestroTablaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiMaestroTabla)) {
            return false;
        }
        MdiMaestroTabla other = (MdiMaestroTabla) object;
        if ((this.mdiMaestroTablaPK == null && other.mdiMaestroTablaPK != null) || (this.mdiMaestroTablaPK != null && !this.mdiMaestroTablaPK.equals(other.mdiMaestroTablaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.MdiMaestroTabla[ mdiMaestroTablaPK=" + mdiMaestroTablaPK + " ]";
    }
}
