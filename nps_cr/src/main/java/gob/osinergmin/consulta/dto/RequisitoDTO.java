/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author lchancayauri
 */
public class RequisitoDTO implements Serializable{

    private Long idRequisito;
    private String descripcion;
    private String comentarioPredeterminado;
    private String estado;
    private Long idDocumentoAdjunto;
    private RequisitoProcedimientoDTO requisitoProcedimiento;
    private String nroParaConsulta;

    private List<RequisitoDTO> listaSubRequisitos;
    
    public Long getIdRequisito() {
        return idRequisito;
    }

    public void setIdRequisito(Long idRequisito) {
        this.idRequisito = idRequisito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getComentarioPredeterminado() {
        return comentarioPredeterminado;
    }

    public void setComentarioPredeterminado(String comentarioPredeterminado) {
        this.comentarioPredeterminado = comentarioPredeterminado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdDocumentoAdjunto() {
        return idDocumentoAdjunto;
    }

    public void setIdDocumentoAdjunto(Long idDocumentoAdjunto) {
        this.idDocumentoAdjunto = idDocumentoAdjunto;
    }

    public List<RequisitoDTO> getListaSubRequisitos() {
        return listaSubRequisitos;
    }

    public void setListaSubRequisitos(List<RequisitoDTO> listaSubRequisitos) {
        this.listaSubRequisitos = listaSubRequisitos;
    }

    public RequisitoProcedimientoDTO getRequisitoProcedimiento() {
        return requisitoProcedimiento;
    }

    public void setRequisitoProcedimiento(RequisitoProcedimientoDTO requisitoProcedimiento) {
        this.requisitoProcedimiento = requisitoProcedimiento;
    }
    
    public String getNroParaConsulta() {
        return nroParaConsulta;
    }

    public void setNroParaConsulta(String nroParaConsulta) {
        this.nroParaConsulta = nroParaConsulta;
    }
       
}
