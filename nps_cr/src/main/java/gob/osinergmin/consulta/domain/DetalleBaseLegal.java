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
@Table(name="DET_BASE_LEGAL")
@NamedQueries({
    @NamedQuery(name="DetalleBaseLegal.findAll",query="Select d from DetalleBaseLegal d")
})
public class DetalleBaseLegal implements Serializable{
    private static final Long serialVersionUID = 1L;
    
    @Id
    @Basic(optional=false)
    @Column(name = "ID_DET_BASE_LEGAL",nullable=false)
    private Long idDetBaseLegal;
    
    @Column(name="TIPO_DETALLE")
    private String tipoDetalle;
    
    @Column(name="ARTICULO")
    private String articulo;
    
    @Column(name="INCISO_1")
    private String inciso1;
    
    @Column(name="INCISO_2")
    private String inciso2;
    
    @Column(name="NORMA_TECNICA")
    private String normaTecnica;
    
    @Column(name="SIGLA")
    private String sigla;
    
    @Column(name="FEC_ENT_VIG")
    private Date fecEntVig;
    
    @Column(name="FEC_PUB")
    private Date fecPub;
    
    @Column(name="MOD_BASE_LEGAL")
    private String modBaseLegal;
    
    @Column(name="TITULO")
    private String titulo;
    
    @Column(name="CONCORDANCIA")
    private String concordancia;
    
    @Column(name="DESCRIPCION")
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name="ID_BASE_LEGAL")
    private BaseLegal baseLegal;
    
    public BaseLegal getBaseLegal(){
        return baseLegal;
    }
    public void setBaseLegal(BaseLegal baseLegal){
        this.baseLegal=baseLegal;
    }

    public DetalleBaseLegal(){
    
    }
    
    public DetalleBaseLegal(Long idDetBaseLegal){
        this.idDetBaseLegal=idDetBaseLegal;
    }
    
    public Long getIdDetBaseLegal() {
        return idDetBaseLegal;
    }

    public void setIdDetBaseLegal(Long idDetBaseLegal) {
        this.idDetBaseLegal = idDetBaseLegal;
    }

    public String getTipoDetalle() {
        return tipoDetalle;
    }

    public void setTipoDetalle(String tipoDetalle) {
        this.tipoDetalle = tipoDetalle;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getInciso1() {
        return inciso1;
    }

    public void setInciso1(String inciso1) {
        this.inciso1 = inciso1;
    }

    public String getInciso2() {
        return inciso2;
    }

    public void setInciso2(String inciso2) {
        this.inciso2 = inciso2;
    }

    public String getNormaTecnica() {
        return normaTecnica;
    }

    public void setNormaTecnica(String normaTecnica) {
        this.normaTecnica = normaTecnica;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Date getFecEntVig() {
        return fecEntVig;
    }

    public void setFecEntVig(Date fecEntVig) {
        this.fecEntVig = fecEntVig;
    }

    public Date getFecPub() {
        return fecPub;
    }

    public void setFecPub(Date fecPub) {
        this.fecPub = fecPub;
    }

    public String getModBaseLegal() {
        return modBaseLegal;
    }

    public void setModBaseLegal(String modBaseLegal) {
        this.modBaseLegal = modBaseLegal;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConcordancia() {
        return concordancia;
    }

    public void setConcordancia(String concordancia) {
        this.concordancia = concordancia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
        
}
