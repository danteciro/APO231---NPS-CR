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
public class PghProcesoObligacionTipoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_OBLIGACION_TIPO")
    private long idObligacionTipo;
    @Basic(optional = false)
    @Column(name = "ID_PROCESO")
    private long idProceso;
    @Basic(optional = false)
    @Column(name = "ID_ACTIVIDAD")
    private long idActividad;

    public PghProcesoObligacionTipoPK() {
    }

    public PghProcesoObligacionTipoPK(long idObligacionTipo, long idProceso, long idActividad) {
        this.idObligacionTipo = idObligacionTipo;
        this.idProceso = idProceso;
        this.idActividad = idActividad;
    }

    public long getIdObligacionTipo() {
        return idObligacionTipo;
    }

    public void setIdObligacionTipo(long idObligacionTipo) {
        this.idObligacionTipo = idObligacionTipo;
    }

    public long getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(long idProceso) {
        this.idProceso = idProceso;
    }

    public long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(long idActividad) {
        this.idActividad = idActividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idObligacionTipo;
        hash += (int) idProceso;
        hash += (int) idActividad;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghProcesoObligacionTipoPK)) {
            return false;
        }
        PghProcesoObligacionTipoPK other = (PghProcesoObligacionTipoPK) object;
        if (this.idObligacionTipo != other.idObligacionTipo) {
            return false;
        }
        if (this.idProceso != other.idProceso) {
            return false;
        }
        if (this.idActividad != other.idActividad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinerg.consulta.domain.PghProcesoObligacionTipoPK[ idObligacionTipo=" + idObligacionTipo + ", idProceso=" + idProceso + ", idActividad=" + idActividad + " ]";
    }
    
}
