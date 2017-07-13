/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author lchancayauri
 */
public class DocumentoAdjuntoDTO implements Serializable{
    
    private Long idDocumentoAdjunto;
    private String rutaAlfresco;
    private String nombre;
    private String comentario;
    private Long idTipoDocumentoCarga;
    private String titulo;
    private Date fechaCarga;
    private Date fechaDocumento;
    private Date fechaCaptura;
    private String estado;

    public Long getIdDocumentoAdjunto() {
        return idDocumentoAdjunto;
    }

    public void setIdDocumentoAdjunto(Long idDocumentoAdjunto) {
        this.idDocumentoAdjunto = idDocumentoAdjunto;
    }

    public String getRutaAlfresco() {
        return rutaAlfresco;
    }

    public void setRutaAlfresco(String rutaAlfresco) {
        this.rutaAlfresco = rutaAlfresco;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Long getIdTipoDocumentoCarga() {
        return idTipoDocumentoCarga;
    }

    public void setIdTipoDocumentoCarga(Long idTipoDocumentoCarga) {
        this.idTipoDocumentoCarga = idTipoDocumentoCarga;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public Date getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(Date fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
