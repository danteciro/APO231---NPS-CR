/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain.ui;

/**
*
* @author l_garcia_r
*/

import gob.osinergmin.consulta.domain.ui.base.BasePaginatorFilter;

public class TipoSancionFilter extends BasePaginatorFilter{
    private Long idTipoSancion;
    private String descripcion;
    private String estado;

    public Long getIdTipoSancion() {
        return idTipoSancion;
    }

    public void setIdTipoSancion(Long idTipoSancion) {
        this.idTipoSancion = idTipoSancion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}