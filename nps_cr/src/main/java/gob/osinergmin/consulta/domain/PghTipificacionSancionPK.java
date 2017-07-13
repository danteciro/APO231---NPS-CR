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
public class PghTipificacionSancionPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_TIPIFICACION")
    private long idTipificacion;
    @Basic(optional = false)
    @Column(name = "ID_TIPO_SANCION")
    private long idTipoSancion;

    public PghTipificacionSancionPK() {
    }

    public PghTipificacionSancionPK(long idTipificacion, long idTipoSancion) {
        this.idTipificacion = idTipificacion;
        this.idTipoSancion = idTipoSancion;
    }

    public long getIdTipificacion() {
        return idTipificacion;
    }

    public void setIdTipificacion(long idTipificacion) {
        this.idTipificacion = idTipificacion;
    }

    public long getIdTipoSancion() {
        return idTipoSancion;
    }

    public void setIdTipoSancion(long idTipoSancion) {
        this.idTipoSancion = idTipoSancion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTipificacion;
        hash += (int) idTipoSancion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghTipificacionSancionPK)) {
            return false;
        }
        PghTipificacionSancionPK other = (PghTipificacionSancionPK) object;
        if (this.idTipificacion != other.idTipificacion) {
            return false;
        }
        if (this.idTipoSancion != other.idTipoSancion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PghTipificacionSancionPK[ idTipificacion=" + idTipificacion + ", idTipoSancion=" + idTipoSancion + " ]";
    }
    
}
