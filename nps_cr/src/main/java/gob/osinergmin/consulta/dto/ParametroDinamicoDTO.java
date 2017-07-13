/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.dto;

import java.io.Serializable;

/**
 *
 * @author lchancayauri
 */
public class ParametroDinamicoDTO implements Serializable {

    private Long idParametroDinamico;
    private String nombre;
    private Long idAmbitoParametrico;
    private String descripcion;
    private String comentario;
    private String estado;
    private String pregunta;
    private Long tipoConsulta;

    public Long getIdParametroDinamico() {
        return idParametroDinamico;
    }

    public void setIdParametroDinamico(Long idParametroDinamico) {
        this.idParametroDinamico = idParametroDinamico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdAmbitoParametrico() {
        return idAmbitoParametrico;
    }

    public void setIdAmbitoParametrico(Long idAmbitoParametrico) {
        this.idAmbitoParametrico = idAmbitoParametrico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public Long getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(Long tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }
    
}
