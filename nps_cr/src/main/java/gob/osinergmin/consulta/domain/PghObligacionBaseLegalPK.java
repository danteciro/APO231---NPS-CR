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
public class PghObligacionBaseLegalPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_OBLIGACION")
    private long idObligacion;
    @Basic(optional = false)
    @Column(name = "ID_BASE_LEGAL")
    private long idBaseLegal;

    public PghObligacionBaseLegalPK() {
    }

    public PghObligacionBaseLegalPK(long idObligacion, long idBaseLegal) {
        this.idObligacion = idObligacion;
        this.idBaseLegal = idBaseLegal;
    }

    public long getIdObligacion() {
        return idObligacion;
    }

    public void setIdObligacion(long idObligacion) {
        this.idObligacion = idObligacion;
    }

    public long getIdBaseLegal() {
        return idBaseLegal;
    }

    public void setIdBaseLegal(long idBaseLegal) {
        this.idBaseLegal = idBaseLegal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idObligacion;
        hash += (int) idBaseLegal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghObligacionBaseLegalPK)) {
            return false;
        }
        PghObligacionBaseLegalPK other = (PghObligacionBaseLegalPK) object;
        if (this.idObligacion != other.idObligacion) {
            return false;
        }
        if (this.idBaseLegal != other.idBaseLegal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PghObligacionBaseLegalPK[ idObligacion=" + idObligacion + ", idBaseLegal=" + idBaseLegal + " ]";
    }
    
}
