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
public class PghObligacionTipificacionPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_OBLIGACION")
    private long idObligacion;
    @Basic(optional = false)
    @Column(name = "ID_TIPIFICACION")
    private long idTipificacion;

    public PghObligacionTipificacionPK() {
    }

    public PghObligacionTipificacionPK(long idObligacion, long idTipificacion) {
        this.idObligacion = idObligacion;
        this.idTipificacion = idTipificacion;
    }

    public long getIdObligacion() {
        return idObligacion;
    }

    public void setIdObligacion(long idObligacion) {
        this.idObligacion = idObligacion;
    }

    public long getIdTipificacion() {
        return idTipificacion;
    }

    public void setIdTipificacion(long idTipificacion) {
        this.idTipificacion = idTipificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idObligacion;
        hash += (int) idTipificacion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghObligacionTipificacionPK)) {
            return false;
        }
        PghObligacionTipificacionPK other = (PghObligacionTipificacionPK) object;
        if (this.idObligacion != other.idObligacion) {
            return false;
        }
        if (this.idTipificacion != other.idTipificacion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PghObligacionTipificacionPK[ idObligacion=" + idObligacion + ", idTipificacion=" + idTipificacion + " ]";
    }
    
}
