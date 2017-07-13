/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.osinergmin.consulta.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author gvillanueva
 */
@Entity
@Table(name="DET_TIPIFICACION")
@NamedQueries({
    @NamedQuery(name="DetalleTipificacion.findAll",query="Select d from DetalleTipificacion d")
})
public class DetalleTipificacion {
    private static final Long serialVersionUID = 1L;
    
    @Id
    @Basic(optional=false)
    @Column(name="ID_DET_TIPIFICACION",nullable=false)
    private Long idDetTipificacion;
    
    @Column(name="TIPO_SANCIONES")
    private String tipoSanciones;
    
    @ManyToOne
    @JoinColumn(name="ID_TIPIFICACION")
    private Tipificacion tipificacion;
    
    public Tipificacion getTipificacion(){
        return tipificacion;
    }
    public void setObligacion(Tipificacion tipificacion){
        this.tipificacion=tipificacion;
    }

    public Long getIdDetTipificacion() {
        return idDetTipificacion;
    }

    public void setIdDetTipificacion(Long idDetTipificacion) {
        this.idDetTipificacion = idDetTipificacion;
    }

    public String getTipoSanciones() {
        return tipoSanciones;
    }

    public void setTipoSanciones(String tipoSanciones) {
        this.tipoSanciones = tipoSanciones;
    }
    
    
    
}
