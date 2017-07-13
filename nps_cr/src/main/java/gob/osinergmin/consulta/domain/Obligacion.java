/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.osinergmin.consulta.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author gvillanueva
 */
@Entity
@Table(name="CAB_OBLIGACION")
@NamedQueries({
    @NamedQuery(name="Obligacion.findAll",query="Select o from Obligacion o")
})
public class Obligacion implements Serializable{
    private static final Long serialVersionUID = 1L;
    @Id
    @Basic(optional=false)
    @Column(name="ID_OBLIGACION",nullable=false)
    private Long idObligacion;
    
    @Basic(optional=false)
    @Column(name="COD_OBLIGACION",nullable=false)
    private String codObligacion;
    
    @Column(name="FEC_ENT_VIG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecEntVig;
    
    @Column(name="FEC_FIN_VIG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecFinVig;
    
    @Column(name="IND_CREACION")
    private String indCreacion;
    
    @Column(name="DESCRIPCION")
    private String descripcion;
    
    @Column(name="TEMA")
    private String tema;
    
    @Column(name="CRITICIDAD")
    private String criticidad;
    
    @Column(name="COD_TIPIFICACION")
    private String codTipificacion;
    
    @Column(name="TIPO_NORMAL_LEGAL")
    private String tipoNormaLegal;

    public Long getIdObligacion() {
        return idObligacion;
    }

    public void setIdObligacion(Long idObligacion) {
        this.idObligacion = idObligacion;
    }

    public String getCodObligacion() {
        return codObligacion;
    }

    public void setCodObligacion(String codObligacion) {
        this.codObligacion = codObligacion;
    }

    public Date getFecEntVig() {
        return fecEntVig;
    }

    public void setFecEntVig(Date fecEntVig) {
        this.fecEntVig = fecEntVig;
    }

    public Date getFecFinVig() {
        return fecFinVig;
    }

    public void setFecFinVig(Date fecFinVig) {
        this.fecFinVig = fecFinVig;
    }

    public String getIndCreacion() {
        return indCreacion;
    }

    public void setIndCreacion(String indCreacion) {
        this.indCreacion = indCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getCriticidad() {
        return criticidad;
    }

    public void setCriticidad(String criticidad) {
        this.criticidad = criticidad;
    }

    public String getCodTipificacion() {
        return codTipificacion;
    }

    public void setCodTipificacion(String codTipificacion) {
        this.codTipificacion = codTipificacion;
    }

    public String getTipoNormaLegal() {
        return tipoNormaLegal;
    }

    public void setTipoNormaLegal(String tipoNormaLegal) {
        this.tipoNormaLegal = tipoNormaLegal;
    }
        
    
}
