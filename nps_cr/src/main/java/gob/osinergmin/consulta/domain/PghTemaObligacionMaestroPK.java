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
public class PghTemaObligacionMaestroPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_MAESTRO_COLUMNA")
    private long idMaestroColumna;
    @Basic(optional = false)
    @Column(name = "ID_OBLIGACION")
    private long idObligacion;

    public PghTemaObligacionMaestroPK() {
    }

    public PghTemaObligacionMaestroPK(long idMaestroColumna, long idObligacion) {
        this.idMaestroColumna = idMaestroColumna;
        this.idObligacion = idObligacion;
    }

    public long getIdMaestroColumna() {
        return idMaestroColumna;
    }

    public void setIdMaestroColumna(long idMaestroColumna) {
        this.idMaestroColumna = idMaestroColumna;
    }

    public long getIdObligacion() {
        return idObligacion;
    }

    public void setIdObligacion(long idObligacion) {
        this.idObligacion = idObligacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idMaestroColumna;
        hash += (int) idObligacion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghTemaObligacionMaestroPK)) {
            return false;
        }
        PghTemaObligacionMaestroPK other = (PghTemaObligacionMaestroPK) object;
        if (this.idMaestroColumna != other.idMaestroColumna) {
            return false;
        }
        if (this.idObligacion != other.idObligacion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PghTemaObligacionMaestroPK[ idMaestroColumna=" + idMaestroColumna + ", idObligacion=" + idObligacion + " ]";
    }
    
}
