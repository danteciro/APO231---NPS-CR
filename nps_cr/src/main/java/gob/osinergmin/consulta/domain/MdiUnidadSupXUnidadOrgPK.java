/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author cflorian
 */
@Embeddable
public class MdiUnidadSupXUnidadOrgPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_UNIDAD_ORGANICA")
    private BigInteger idUnidadOrganica;
    @Basic(optional = false)
    @Column(name = "ID_UNIDAD_SUPERVISADA")
    private long idUnidadSupervisada;

    public MdiUnidadSupXUnidadOrgPK() {
    }

    public MdiUnidadSupXUnidadOrgPK(BigInteger idUnidadOrganica, long idUnidadSupervisada) {
        this.idUnidadOrganica = idUnidadOrganica;
        this.idUnidadSupervisada = idUnidadSupervisada;
    }

    public BigInteger getIdUnidadOrganica() {
        return idUnidadOrganica;
    }

    public void setIdUnidadOrganica(BigInteger idUnidadOrganica) {
        this.idUnidadOrganica = idUnidadOrganica;
    }

    public long getIdUnidadSupervisada() {
        return idUnidadSupervisada;
    }

    public void setIdUnidadSupervisada(long idUnidadSupervisada) {
        this.idUnidadSupervisada = idUnidadSupervisada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidadOrganica != null ? idUnidadOrganica.hashCode() : 0);
        hash += (int) idUnidadSupervisada;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiUnidadSupXUnidadOrgPK)) {
            return false;
        }
        MdiUnidadSupXUnidadOrgPK other = (MdiUnidadSupXUnidadOrgPK) object;
        if ((this.idUnidadOrganica == null && other.idUnidadOrganica != null) || (this.idUnidadOrganica != null && !this.idUnidadOrganica.equals(other.idUnidadOrganica))) {
            return false;
        }
        if (this.idUnidadSupervisada != other.idUnidadSupervisada) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiUnidadSupXUnidadOrgPK[ idUnidadOrganica=" + idUnidadOrganica + ", idUnidadSupervisada=" + idUnidadSupervisada + " ]";
    }
    
}
