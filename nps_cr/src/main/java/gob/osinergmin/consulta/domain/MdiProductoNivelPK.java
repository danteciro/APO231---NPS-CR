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
public class MdiProductoNivelPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_PRODUCTO")
    private long idProducto;
    @Basic(optional = false)
    @Column(name = "NIVEL")
    private int nivel;

    public MdiProductoNivelPK() {
    }

    public MdiProductoNivelPK(long idProducto, int nivel) {
        this.idProducto = idProducto;
        this.nivel = nivel;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProducto;
        hash += (int) nivel;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiProductoNivelPK)) {
            return false;
        }
        MdiProductoNivelPK other = (MdiProductoNivelPK) object;
        if (this.idProducto != other.idProducto) {
            return false;
        }
        if (this.nivel != other.nivel) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiProductoNivelPK[ idProducto=" + idProducto + ", nivel=" + nivel + " ]";
    }
    
}
