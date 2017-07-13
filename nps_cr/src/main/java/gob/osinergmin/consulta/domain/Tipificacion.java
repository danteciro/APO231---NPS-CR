/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.osinergmin.consulta.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author gvillanueva
 */
@Entity
@Table(name="CAB_TIPIFICACION")
@NamedQueries({
    @NamedQuery(name="Tipificacion.findAll",query="Select t from Tipificacion t")
})
public class Tipificacion implements Serializable{
    private static final Long serialVersionUID = 1L;
    
    @Id
    @Basic(optional=false)
    @Column(name="ID_TIPIFICACION",nullable=false)
    private Long idTipificacion;
    
    @Basic(optional=false)
    @Column(name="COD_TIPIFICACION",nullable=false)
    private String codTipificacion;
    
    @Column(name="MONT_SANCION")
    private String monSancion;
    
    @Column(name="MONT_UMD")
    private String monUmd;
    
    @Column(name="TIPIFICACION_PADRE")
    private String tipificacionPadre;

    public Long getIdTipificacion() {
        return idTipificacion;
    }

    public void setIdTipificacion(Long idTipificacion) {
        this.idTipificacion = idTipificacion;
    }

    public String getCodTipificacion() {
        return codTipificacion;
    }

    public void setCodTipificacion(String codTipificacion) {
        this.codTipificacion = codTipificacion;
    }

    public String getMonSancion() {
        return monSancion;
    }

    public void setMonSancion(String monSancion) {
        this.monSancion = monSancion;
    }

    public String getMonUmd() {
        return monUmd;
    }

    public void setMonUmd(String monUmd) {
        this.monUmd = monUmd;
    }

    public String getTipificacionPadre() {
        return tipificacionPadre;
    }

    public void setTipificacionPadre(String tipificacionPadre) {
        this.tipificacionPadre = tipificacionPadre;
    }
    
    
    
}
