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
public class PreguntaRequisitoDTO implements Serializable {

    private Long idPreguntaRequisito;
    private Long idPregunta;
    private Long tipoPregunta;
    private String pregunta;
    private List<PreguntaRequisitoValorDTO> listaPreguntaRequisitoValor;
    private String preguntaRespuesta;

    public Long getIdPreguntaRequisito() {
        return idPreguntaRequisito;
    }

    public void setIdPreguntaRequisito(Long idPreguntaRequisito) {
        this.idPreguntaRequisito = idPreguntaRequisito;
    }

    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Long getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(Long tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public List<PreguntaRequisitoValorDTO> getListaPreguntaRequisitoValor() {
        return listaPreguntaRequisitoValor;
    }

    public void setListaPreguntaRequisitoValor(List<PreguntaRequisitoValorDTO> listaPreguntaRequisitoValor) {
        this.listaPreguntaRequisitoValor = listaPreguntaRequisitoValor;
    }

    public String getPreguntaRespuesta() {
        return preguntaRespuesta;
    }

    public void setPreguntaRespuesta(String preguntaRespuesta) {
        this.preguntaRespuesta = preguntaRespuesta;
    }
    
}
