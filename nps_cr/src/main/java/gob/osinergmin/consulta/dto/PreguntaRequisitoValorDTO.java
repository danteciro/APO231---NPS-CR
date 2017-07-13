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
public class PreguntaRequisitoValorDTO implements Serializable {

    private Long idPreguntaRequisitoValor;
    private String preguntaValor;

    public Long getIdPreguntaRequisitoValor() {
        return idPreguntaRequisitoValor;
    }

    public void setIdPreguntaRequisitoValor(Long idPreguntaRequisitoValor) {
        this.idPreguntaRequisitoValor = idPreguntaRequisitoValor;
    }

    public String getPreguntaValor() {
        return preguntaValor;
    }

    public void setPreguntaValor(String preguntaValor) {
        this.preguntaValor = preguntaValor;
    }
}
