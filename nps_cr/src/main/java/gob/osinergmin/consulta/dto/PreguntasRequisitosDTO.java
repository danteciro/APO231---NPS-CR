/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lchancayauri
 */
public class PreguntasRequisitosDTO implements Serializable {

    List<PreguntaRequisitoDTO> preguntasRequisitos = new ArrayList<PreguntaRequisitoDTO>();

    public List<PreguntaRequisitoDTO> getPreguntasRequisitos() {
        return preguntasRequisitos;
    }

    public void setPreguntasRequisitos(List<PreguntaRequisitoDTO> preguntasRequisitos) {
        this.preguntasRequisitos = preguntasRequisitos;
    }
    
}
