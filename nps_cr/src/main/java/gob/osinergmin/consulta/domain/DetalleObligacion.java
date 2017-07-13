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
@Table(name="DET_OBLIGACION")
@NamedQueries({
    @NamedQuery(name="DetalleObligacion.findAll",query="Select d from DetalleObligacion d")
})
public class DetalleObligacion implements Serializable{
    private static final Long serialVersionUID = 1L;
    
    @Id
    @Basic(optional=false)
    @Column(name = "ID_DET_OBLIGACION",nullable=false)
    private Long idDetObligacion;
    
    @Column(name="TIPO_DOCU_OBLIG")
    private String tipoDocuOblig;
    
    @Column(name="DESCRIPCIÓN")
    private String descripcion;
    
    @Column(name="RUTA_ADJUNTO")
    private String rutaAdjunto;
    
    @ManyToOne
    @JoinColumn(name="ID_OBLIGACION")
    private Obligacion obligacion;
    
    public Obligacion getObligacion(){
        return obligacion;
    }
    public void setObligacion(Obligacion obligacion){
        this.obligacion=obligacion;
    }

    public Long getIdDetObligacion() {
        return idDetObligacion;
    }

    public void setIdDetObligacion(Long idDetObligacion) {
        this.idDetObligacion = idDetObligacion;
    }

    public String getTipoDocuOblig() {
        return tipoDocuOblig;
    }

    public void setTipoDocuOblig(String tipoDocuOblig) {
        this.tipoDocuOblig = tipoDocuOblig;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRutaAdjunto() {
        return rutaAdjunto;
    }

    public void setRutaAdjunto(String rutaAdjunto) {
        this.rutaAdjunto = rutaAdjunto;
    }
    

}
