/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author cflorian
 */
@Embeddable
public class MdiMacroRegionXRegionPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_MACRO_REGION")
    private long idMacroRegion;
    @Basic(optional = false)
    @Column(name = "ID_REGION")
    private long idRegion;

    public MdiMacroRegionXRegionPK() {
    }

    public MdiMacroRegionXRegionPK(long idMacroRegion, long idRegion) {
        this.idMacroRegion = idMacroRegion;
        this.idRegion = idRegion;
    }

    public long getIdMacroRegion() {
        return idMacroRegion;
    }

    public void setIdMacroRegion(long idMacroRegion) {
        this.idMacroRegion = idMacroRegion;
    }

    public long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(long idRegion) {
        this.idRegion = idRegion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idMacroRegion;
        hash += (int) idRegion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiMacroRegionXRegionPK)) {
            return false;
        }
        MdiMacroRegionXRegionPK other = (MdiMacroRegionXRegionPK) object;
        if (this.idMacroRegion != other.idMacroRegion) {
            return false;
        }
        if (this.idRegion != other.idRegion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiMacroRegionXRegionPK[ idMacroRegion=" + idMacroRegion + ", idRegion=" + idRegion + " ]";
    }
    
}
