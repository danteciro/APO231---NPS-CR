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
public class MdiEmpresaUnidadSupervisadaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_UNIDAD_SUPERVISADA")
    private long idUnidadSupervisada;
    @Basic(optional = false)
    @Column(name = "ID_EMPRESA_SUPERVISADA")
    private long idEmpresaSupervisada;

    public MdiEmpresaUnidadSupervisadaPK() {
    }

    public MdiEmpresaUnidadSupervisadaPK(long idUnidadSupervisada, long idEmpresaSupervisada) {
        this.idUnidadSupervisada = idUnidadSupervisada;
        this.idEmpresaSupervisada = idEmpresaSupervisada;
    }

    public long getIdUnidadSupervisada() {
        return idUnidadSupervisada;
    }

    public void setIdUnidadSupervisada(long idUnidadSupervisada) {
        this.idUnidadSupervisada = idUnidadSupervisada;
    }

    public long getIdEmpresaSupervisada() {
        return idEmpresaSupervisada;
    }

    public void setIdEmpresaSupervisada(long idEmpresaSupervisada) {
        this.idEmpresaSupervisada = idEmpresaSupervisada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUnidadSupervisada;
        hash += (int) idEmpresaSupervisada;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiEmpresaUnidadSupervisadaPK)) {
            return false;
        }
        MdiEmpresaUnidadSupervisadaPK other = (MdiEmpresaUnidadSupervisadaPK) object;
        if (this.idUnidadSupervisada != other.idUnidadSupervisada) {
            return false;
        }
        if (this.idEmpresaSupervisada != other.idEmpresaSupervisada) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiEmpresaUnidadSupervisadaPK[ idUnidadSupervisada=" + idUnidadSupervisada + ", idEmpresaSupervisada=" + idEmpresaSupervisada + " ]";
    }
    
}
