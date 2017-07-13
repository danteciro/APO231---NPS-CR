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
@Table(name="CAB_BASE_LEGAL")
@NamedQueries({
    @NamedQuery(name="BaseLegal.findAll",query="Select c from BaseLegal c")
})
public class BaseLegal implements Serializable{
    private static final Long serialVersionUID = 1L;
    
    @Id
    @Basic(optional=false)
    @Column(name = "ID_BASE_LEGAL", nullable = false)
    private Long idBaseLegal;
    
    @Basic(optional=false)
    @Column(name = "COD_BASE_LEGAL", nullable = false)
    private String codBaseLegal;
    
    @Column(name = "TIPO_NORMA_LEGAL", nullable = false)
    private String tipoNormaLegal;
    
    @Column(name = "NUMERO", nullable = false)
    private String numero;
    
    @Column(name = "ANIO")
    private String anio;
    
    @Column(name = "SIGLA")
    private String sigla;
    
    @Column(name = "FEC_ENT_VIG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecEntVig;
    
    @Column(name = "FEC_PUB")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecPub;
    
    @Column(name = "TITULO")
    private String titulo;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Column(name = "RUTA_ADJUNTO")
    private String rutaAdjunto;
    
    
    
    public BaseLegal(){
        
    }
    
    public BaseLegal(Long idBaseLegal){
        this.idBaseLegal=idBaseLegal;
    }
    
    public Long getIdBaseLegal() {
        return idBaseLegal;
    }

    public void setIdBaseLegal(Long idBaseLegal) {
        this.idBaseLegal = idBaseLegal;
    }

    public String getCodBaseLegal() {
        return codBaseLegal;
    }

    public void setCodBaseLegal(String codBaseLegal) {
        this.codBaseLegal = codBaseLegal;
    }

    public String getTipoNormaLegal() {
        return tipoNormaLegal;
    }

    public void setTipoNormaLegal(String tipoNormaLegal) {
        this.tipoNormaLegal = tipoNormaLegal;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
