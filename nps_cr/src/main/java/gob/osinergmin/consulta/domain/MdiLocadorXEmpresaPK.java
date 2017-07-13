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
public class MdiLocadorXEmpresaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_CONTRATO_EMPRESA_LOCADOR")
    private long idContratoEmpresaLocador;
    @Basic(optional = false)
    @Column(name = "ID_LOCADOR")
    private long idLocador;

    public MdiLocadorXEmpresaPK() {
    }

    public MdiLocadorXEmpresaPK(long idContratoEmpresaLocador, long idLocador) {
        this.idContratoEmpresaLocador = idContratoEmpresaLocador;
        this.idLocador = idLocador;
    }

    public long getIdContratoEmpresaLocador() {
        return idContratoEmpresaLocador;
    }

    public void setIdContratoEmpresaLocador(long idContratoEmpresaLocador) {
        this.idContratoEmpresaLocador = idContratoEmpresaLocador;
    }

    public long getIdLocador() {
        return idLocador;
    }

    public void setIdLocador(long idLocador) {
        this.idLocador = idLocador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idContratoEmpresaLocador;
        hash += (int) idLocador;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiLocadorXEmpresaPK)) {
            return false;
        }
        MdiLocadorXEmpresaPK other = (MdiLocadorXEmpresaPK) object;
        if (this.idContratoEmpresaLocador != other.idContratoEmpresaLocador) {
            return false;
        }
        if (this.idLocador != other.idLocador) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiLocadorXEmpresaPK[ idContratoEmpresaLocador=" + idContratoEmpresaLocador + ", idLocador=" + idLocador + " ]";
    }
    
}
