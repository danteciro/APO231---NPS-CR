/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author lchancayauri
 */
@Entity
@Table(name = "MDI_UBIGEO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiUbigeo.findDepartamento", query = "SELECT u " +
                "FROM MdiUbigeo u " +
                "WHERE " +
                " u.mdiUbigeoPK.idProvincia = '00' and " +
                " u.mdiUbigeoPK.idDistrito = '00' order by u.nombre asc"),
    @NamedQuery(name = "MdiUbigeo.findProvinciaByIdDepartamento", query = "SELECT u " +
                "FROM MdiUbigeo u " +
                "WHERE " +
                " u.mdiUbigeoPK.idProvincia != '00' and " +
                " u.mdiUbigeoPK.idDistrito = '00' and " +
                " u.mdiUbigeoPK.idDepartamento = :idDepartamento order by u.nombre asc "),
    @NamedQuery(name = "MdiUbigeo.findDistritoByParametros", query = "SELECT u " +
                "FROM MdiUbigeo u " +
                "WHERE " +
                " u.mdiUbigeoPK.idDistrito != '00' and " +
                " u.mdiUbigeoPK.idDepartamento = :idDepartamento and " +
                " u.mdiUbigeoPK.idProvincia = :idProvincia order by u.nombre asc") })
public class MdiUbigeo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MdiUbigeoPK mdiUbigeoPK;
    @Size(max = 18)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ESTADO")
    private Character estado;
    @OneToMany(mappedBy = "mdiUbigeo", fetch = FetchType.LAZY)
    private List<PghZonificacionDetalle> pghZonificacionList;

    public MdiUbigeo() {
    }

    public MdiUbigeo(MdiUbigeoPK mdiUbigeoPK) {
        this.mdiUbigeoPK = mdiUbigeoPK;
    }

    public MdiUbigeo(String idDepartamento, String idProvincia, String idDistrito) {
        this.mdiUbigeoPK = new MdiUbigeoPK(idDepartamento, idProvincia, idDistrito);
    }

    public MdiUbigeoPK getMdiUbigeoPK() {
        return mdiUbigeoPK;
    }

    public void setMdiUbigeoPK(MdiUbigeoPK mdiUbigeoPK) {
        this.mdiUbigeoPK = mdiUbigeoPK;
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
    @JsonIgnore
    public List<PghZonificacionDetalle> getPghZonificacionList() {
        return pghZonificacionList;
    }

    public void setPghZonificacionList(List<PghZonificacionDetalle> pghZonificacionList) {
        this.pghZonificacionList = pghZonificacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mdiUbigeoPK != null ? mdiUbigeoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiUbigeo)) {
            return false;
        }
        MdiUbigeo other = (MdiUbigeo) object;
        if ((this.mdiUbigeoPK == null && other.mdiUbigeoPK != null) || (this.mdiUbigeoPK != null && !this.mdiUbigeoPK.equals(other.mdiUbigeoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.MdiUbigeo[ mdiUbigeoPK=" + mdiUbigeoPK + " ]";
    } 
}
