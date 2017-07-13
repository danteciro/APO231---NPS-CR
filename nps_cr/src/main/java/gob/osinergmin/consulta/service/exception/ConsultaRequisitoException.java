/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.service.exception;

/**
 *
 * @author lchancayauri
 */
public class ConsultaRequisitoException extends BaseException {

    public ConsultaRequisitoException(String message) {
        super(message);
    }

    public ConsultaRequisitoException(String message, Exception exception) {
        super(message, exception);
    }

    public ConsultaRequisitoException(String codigo, String message, Exception exception) {
        super(codigo, message, exception);
    }
}
