package gob.osinergmin.consulta.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que permite gestionar las fechas
 * 
 * @author cflorian
 * @version 2.0
 * @see
 */
public class StringUtil {
	private static Logger log = LoggerFactory.getLogger(StringUtil.class);

	public static final String ZERO_STRING = "0";
	public static final String EMPTY_STRING = "";

	/**
	 * Verifica si esta esta vacia la cadena
	 * 
	 * @param obj
	 * @return true, false
	 */
	public static boolean isEmpty(String obj) {
		if ((obj == null) || (obj.trim().length() == 0)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Si la cadena pasada es vacia (devuelve true para la funcion esVacio)
	 * devuelve una cadena numérica que representa un cero entero :"0". Si no,
	 * devuelve la misma cadena
	 * 
	 * @param cadena
	 * @return cadena vacia
	 */
	public static String emptyAsZero(String cadena) {
		if (isEmpty(cadena)) {
			return ZERO_STRING;
		} else {
			return cadena;
		}
	}

	/**
	 * Convierte una cadena nula a vacio
	 * 
	 * @param texto
	 * @return cadena vacia
	 */
	public static String nullToBlank(Object texto) {
		try {
			if (texto == null) {
				return "";
			}
			if (texto.toString().trim().equals("null")) {
				return "";
			}
			return texto.toString().trim();
		} catch (Exception e) {
			log.error("Error StringUtil metodo nullToBlank. ");
			return "";
		}

	}
	
	/**
	 * Convierte una cadena nula a vacio y si no es vacio le agrega una coma al inicio
	 * 
	 * @param texto
	 * @return cadena vacia
	 */
	public static String addComa(Object texto) {
		try {
			if (texto == null) {
				return "";
			}
			if (texto.toString().trim().equals("null")) {
				return "";
			}
			if (texto.equals("")) {
				return "";
			}
			return ", " + texto.toString().trim();
		} catch (Exception e) {
			log.error("Error StringUtil metodo nullToBlank. ");
			return "";
		}

	}
	public static String addGuion(Object texto) {
		try {
			if (texto == null) {
				return "";
			}
			if (texto.toString().trim().equals("null")) {
				return "";
			}
			if (texto.equals("")) {
				return "";
			}
			return "_ " + texto.toString().trim();
		} catch (Exception e) {
			log.error("Error StringUtil metodo nullToBlank. ");
			return "";
		}

	}

	/**
	 * Permite convertir una cadena nula a un valor determinado por el parametro
	 * 
	 * @param texto
	 * @param param
	 * @return
	 */
	public static String nullToParameter(Object texto, String param) {
		try {
			if (texto == null) {
				return param;
			}
			if (texto.toString().trim().equals("null")) {
				return param;
			}
			if (texto.toString().trim().equals("")) {
				return param;
			}
			return texto.toString().trim();
		} catch (Exception e) {
			return param;
		}

	}

	/**
	 * Convierte una cadena nula a cero de tipo string
	 * 
	 * @param texto
	 * @return cadena: "0"
	 */
	public static String nullToBlankCero(Object texto) {
		try {
			if (texto == null) {
				return "0";
			}
			if (texto.toString().trim().equals("null")) {
				return "0";
			}
			if (texto.toString().trim().equals("")) {
				return "0";
			}
			return texto.toString().trim();
		} catch (Exception e) {
			return "0";
		}

	}

	/**
	 * Permite separar una coleccion de objeto mediante un separador determinado
	 * en el paso de parametro
	 * 
	 * @param col
	 * @param separator
	 * @return cadena con separador
	 */
	public static String arrayAsLongString(java.util.Collection col,
			String separator) {
		java.util.Iterator it = col.iterator();
		StringBuffer sb = new StringBuffer();
		boolean firstAdded = false;
		while (it.hasNext()) {
			Object o = it.next();
			if ((o != null) && !isEmpty(o.toString())) {
				if (firstAdded && separator != null) {
					sb.append(separator);
				}
				sb.append(o.toString());
				firstAdded = true;
			}
		}
		return sb.toString();
	}

	/**
	 * Permite separar un array de string de objeto mediante un separador
	 * determinado en el paso de parametro
	 * 
	 * @param array
	 * @param separator
	 * @return cadena con separador
	 */
	public static String arrayAsLongString(String[] array, String separator) {
		StringBuffer sb = new StringBuffer();
		boolean firstAdded = false;
		for (int i = 0; i < array.length; i++) {
			if (!isEmpty(array[i])) {
				if (firstAdded && separator != null) {
					sb.append(separator);
				}
				sb.append(array[i]);
				firstAdded = true;
			}
		}
		return sb.toString();
	}

	/**
	 * Permite convertir un objeto nulo a double: 0.0
	 * 
	 * @param texto
	 * @return objeto de tipo Double
	 */
	public static Double nullToZeroD(Object texto) {
		try {
			if (texto == null) {
				return Double.valueOf(0);
			}
		} catch (Exception e) {
			return Double.valueOf(0);
		}
		return Double.valueOf(texto.toString());
	}

	/**
	 * Verifica si la cadena es vacia
	 * 
	 * @param cadena
	 * @return true, false
	 */
	public static boolean esVacio(String cadena) {
		return ((cadena == null) || (cadena.trim().length() == 0));
	}

	/**
	 * Convierte cadena nula a vacia
	 * 
	 * @param cadena
	 * @return cadena vacia
	 */
	public static String nullAsEmptyString(String cadena) {
		if (cadena == null) {
			return EMPTY_STRING;
		}
		return cadena;
	}
}