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
public class MdiLocadorCompetenciaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_COMPETENCIA")
    private long idCompetencia;
    @Basic(optional = false)
    @Column(name = "ID_LOCADOR_DESTAQUE")
    private long idLocadorDestaque;

    public MdiLocadorCompetenciaPK() {
    }

    public MdiLocadorCompetenciaPK(long idCompetencia, long idLocadorDestaque) {
        this.idCompetencia = idCompetencia;
        this.idLocadorDestaque = idLocadorDestaque;
    }

    public long getIdCompetencia() {
        return idCompetencia;
    }

    public void setIdCompetencia(long idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    public long getIdLocadorDestaque() {
        return idLocadorDestaque;
    }

    public void setIdLocadorDestaque(long idLocadorDestaque) {
        this.idLocadorDestaque = idLocadorDestaque;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCompetencia;
        hash += (int) idLocadorDestaque;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiLocadorCompetenciaPK)) {
            return false;
        }
        MdiLocadorCompetenciaPK other = (MdiLocadorCompetenciaPK) object;
        if (this.idCompetencia != other.idCompetencia) {
            return false;
        }
        if (this.idLocadorDestaque != other.idLocadorDestaque) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiLocadorCompetenciaPK[ idCompetencia=" + idCompetencia + ", idLocadorDestaque=" + idLocadorDestaque + " ]";
    }
    
}
