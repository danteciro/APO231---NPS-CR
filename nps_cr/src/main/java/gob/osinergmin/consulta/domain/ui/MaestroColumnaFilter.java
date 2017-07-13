/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain.ui;

import gob.osinergmin.consulta.domain.ui.base.BasePaginatorFilter;

/**
 *
 * @author l_garcia_r
 */
public class MaestroColumnaFilter extends BasePaginatorFilter {
    private Long idMaestroColumna;
    private String dominio;
    private String aplicacion;
    private String descripcion;

    public MaestroColumnaFilter(){
    }
    
    public MaestroColumnaFilter(String aplicacion,String dominio,String descripcion){
        this.aplicacion=aplicacion;
        this.dominio=dominio;
        this.descripcion=descripcion;
    }
    
    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getIdMaestroColumna() {
        return idMaestroColumna;
    }

    public void setIdMaestroColumna(Long idMaestroColumna) {
        this.idMaestroColumna = idMaestroColumna;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }
    
}
