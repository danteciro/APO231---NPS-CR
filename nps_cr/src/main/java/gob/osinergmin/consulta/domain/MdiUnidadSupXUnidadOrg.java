/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.math.BigInteger;
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
@Table(name = "MDI_UNIDAD_SUP_X_UNIDAD_ORG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiUnidadSupXUnidadOrg.findAll", query = "SELECT m FROM MdiUnidadSupXUnidadOrg m"),
    @NamedQuery(name = "MdiUnidadSupXUnidadOrg.findByIdUnidadOrganica", query = "SELECT m FROM MdiUnidadSupXUnidadOrg m WHERE m.mdiUnidadSupXUnidadOrgPK.idUnidadOrganica = :idUnidadOrganica"),
    @NamedQuery(name = "MdiUnidadSupXUnidadOrg.findByIdUnidadSupervisada", query = "SELECT m FROM MdiUnidadSupXUnidadOrg m WHERE m.mdiUnidadSupXUnidadOrgPK.idUnidadSupervisada = :idUnidadSupervisada"),
    @NamedQuery(name = "MdiUnidadSupXUnidadOrg.findByEstado", query = "SELECT m FROM MdiUnidadSupXUnidadOrg m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiUnidadSupXUnidadOrg.findByUsuarioCreacion", query = "SELECT m FROM MdiUnidadSupXUnidadOrg m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiUnidadSupXUnidadOrg.findByUsuarioActualizacion", query = "SELECT m FROM MdiUnidadSupXUnidadOrg m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiUnidadSupXUnidadOrg.findByTerminalCreacion", query = "SELECT m FROM MdiUnidadSupXUnidadOrg m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiUnidadSupXUnidadOrg.findByTerminalActualizacion", query = "SELECT m FROM MdiUnidadSupXUnidadOrg m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiUnidadSupXUnidadOrg.findByFechaCreacion", query = "SELECT m FROM MdiUnidadSupXUnidadOrg m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiUnidadSupXUnidadOrg.findByFechaActualizacion", query = "SELECT m FROM MdiUnidadSupXUnidadOrg m WHERE m.fechaActualizacion = :fechaActualizacion")})
public class MdiUnidadSupXUnidadOrg extends Auditoria {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MdiUnidadSupXUnidadOrgPK mdiUnidadSupXUnidadOrgPK;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;
    @JoinColumn(name = "ID_UNIDAD_SUPERVISADA", referencedColumnName = "ID_UNIDAD_SUPERVISADA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MdiUnidadSupervisada mdiUnidadSupervisada;
    @JoinColumn(name = "ID_UNIDAD_ORGANICA", referencedColumnName = "ID_UNIDAD_ORGANICA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MdiUnidadOrganica mdiUnidadOrganica;

    public MdiUnidadSupXUnidadOrg() {
    }

    public MdiUnidadSupXUnidadOrg(MdiUnidadSupXUnidadOrgPK mdiUnidadSupXUnidadOrgPK) {
        this.mdiUnidadSupXUnidadOrgPK = mdiUnidadSupXUnidadOrgPK;
    }

    public MdiUnidadSupXUnidadOrg(MdiUnidadSupXUnidadOrgPK mdiUnidadSupXUnidadOrgPK, char estado, String usuarioCreacion, String terminalCreacion, Date fechaCreacion) {
        this.mdiUnidadSupXUnidadOrgPK = mdiUnidadSupXUnidadOrgPK;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.terminalCreacion = terminalCreacion;
        this.fechaCreacion = fechaCreacion;
    }

    public MdiUnidadSupXUnidadOrg(BigInteger idUnidadOrganica, long idUnidadSupervisada) {
        this.mdiUnidadSupXUnidadOrgPK = new MdiUnidadSupXUnidadOrgPK(idUnidadOrganica, idUnidadSupervisada);
    }

    public MdiUnidadSupXUnidadOrgPK getMdiUnidadSupXUnidadOrgPK() {
        return mdiUnidadSupXUnidadOrgPK;
    }

    public void setMdiUnidadSupXUnidadOrgPK(MdiUnidadSupXUnidadOrgPK mdiUnidadSupXUnidadOrgPK) {
        this.mdiUnidadSupXUnidadOrgPK = mdiUnidadSupXUnidadOrgPK;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public MdiUnidadSupervisada getMdiUnidadSupervisada() {
        return mdiUnidadSupervisada;
    }

    public void setMdiUnidadSupervisada(MdiUnidadSupervisada mdiUnidadSupervisada) {
        this.mdiUnidadSupervisada = mdiUnidadSupervisada;
    }

    public MdiUnidadOrganica getMdiUnidadOrganica() {
        return mdiUnidadOrganica;
    }

    public void setMdiUnidadOrganica(MdiUnidadOrganica mdiUnidadOrganica) {
        this.mdiUnidadOrganica = mdiUnidadOrganica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mdiUnidadSupXUnidadOrgPK != null ? mdiUnidadSupXUnidadOrgPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiUnidadSupXUnidadOrg)) {
            return false;
        }
        MdiUnidadSupXUnidadOrg other = (MdiUnidadSupXUnidadOrg) object;
        if ((this.mdiUnidadSupXUnidadOrgPK == null && other.mdiUnidadSupXUnidadOrgPK != null) || (this.mdiUnidadSupXUnidadOrgPK != null && !this.mdiUnidadSupXUnidadOrgPK.equals(other.mdiUnidadSupXUnidadOrgPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiUnidadSupXUnidadOrg[ mdiUnidadSupXUnidadOrgPK=" + mdiUnidadSupXUnidadOrgPK + " ]";
    }
    
}
