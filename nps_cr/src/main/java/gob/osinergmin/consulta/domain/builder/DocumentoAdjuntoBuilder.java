/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain.builder;

import gob.osinergmin.consulta.domain.MdiDocumentoAdjunto;
import gob.osinergmin.consulta.dto.DocumentoAdjuntoDTO;


/**
 *
 * @author lchancayauri
 */
public class DocumentoAdjuntoBuilder {

    public static DocumentoAdjuntoDTO getDocumentoAdjunto(MdiDocumentoAdjunto documentoAdjunto) {
        DocumentoAdjuntoDTO documentoAdjuntoDTO = new DocumentoAdjuntoDTO();
        if (documentoAdjunto != null) {
            documentoAdjuntoDTO.setIdDocumentoAdjunto(documentoAdjunto.getIdDocumentoAdjunto());
            documentoAdjuntoDTO.setIdTipoDocumentoCarga(documentoAdjunto.getIdTipoDocumentoCarga());
            documentoAdjuntoDTO.setComentario(documentoAdjunto.getComentario());
            documentoAdjuntoDTO.setEstado(documentoAdjunto.getEstado());
            documentoAdjuntoDTO.setFechaCaptura(documentoAdjunto.getFechaCaptura());
            documentoAdjuntoDTO.setFechaCarga(documentoAdjunto.getFechaCarga());
            documentoAdjuntoDTO.setFechaDocumento(documentoAdjunto.getFechaDocumento());
            documentoAdjuntoDTO.setNombre(documentoAdjunto.getNombre());
            documentoAdjuntoDTO.setRutaAlfresco(documentoAdjunto.getRutaAlfresco());
            documentoAdjuntoDTO.setTitulo(documentoAdjunto.getTitulo());
        }
        return documentoAdjuntoDTO;
    }
}
