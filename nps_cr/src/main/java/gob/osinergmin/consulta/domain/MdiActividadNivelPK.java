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
public class MdiActividadNivelPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "NIVEL")
    private int nivel;
    @Basic(optional = false)
    @Column(name = "ID_ACTIVIDAD")
    private long idActividad;

    public MdiActividadNivelPK() {
    }

    public MdiActividadNivelPK(int nivel, long idActividad) {
        this.nivel = nivel;
        this.idActividad = idActividad;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
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
        hash += (int) nivel;
        hash += (int) idActividad;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiActividadNivelPK)) {
            return false;
        }
        MdiActividadNivelPK other = (MdiActividadNivelPK) object;
        if (this.nivel != other.nivel) {
            return false;
        }
        if (this.idActividad != other.idActividad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiActividadNivelPK[ nivel=" + nivel + ", idActividad=" + idActividad + " ]";
    }
    
}
