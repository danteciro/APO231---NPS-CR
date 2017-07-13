/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.service.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * @author saul.ramos
 * @version 1.0 24/06/2013
 * @see
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -8988101228989337918L;
    public static final String ERROR_GENERICO = "generico.service.error";
    private String codigo;
    private List<String> validationErrors = new ArrayList<String>();

    /**
     * Constructor de la clase BaseException que recibe como parametro el
     * mensaje de la exception
     *
     * @param exception
     */
    public BaseException(String message) {
        super(message);
    }

    /**
     * Constructor de la clase BaseException que recibe como parametro exception
     *
     * @param exception
     */
    public BaseException(Exception exception) {
        super(exception);
    }

    /**
     * Constructor de la clase BaseException que recibe como parametros message
     * y exception
     *
     * @param message
     * @param exception
     */
    public BaseException(String message, Exception exception) {
        super(message, exception);
    }

    /**
     * Constructor de la clase BaseException que recibe como parametros message
     * y exception
     *
     * @param message
     * @param exception
     */
//	public BaseException(String codigo, Exception exception, boolean buscarCodigo) {		
//		super(PropertyUtils.getProperty(codigo), exception);
//	}
    /**
     * Constructor de la clase BaseException que recibe como parametros
     * codigoException, message y exception
     *
     * @param codigo
     * @param message
     * @param exception
     */
    public BaseException(String codigo, String message, Exception exception) {
        super(message, exception);
        this.codigo = codigo;
    }

    /**
     * Constructor de la clase BaseException que recibe como parametro
     * codigoException
     *
     * @param codigo
     */
//	public BaseException(String codigo) {
//		super(PropertyUtils.getProperty(codigo));
//		this.codigo = codigo;
//	}
    /**
     * @return the codigoException
     */
    public String getCodigo() {
        return codigo;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}
