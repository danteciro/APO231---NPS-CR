package gob.osinergmin.consulta.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase Utilitaria
 * 
 * @author cflorian
 * 
 */
public class Utiles {
	private static final Logger log = LoggerFactory.getLogger(Utiles.class);

	public static final String RUTA_TEMPORAL = "/temp";

	public static String FORMATO_FECHA_LARGE = "dd/MM/yyyy hh:mm:ss";
	public static String FORMATO_FECHA_CORTA = "dd/MM/yyyy";
	public static String FORMATO_FECHA_CORTA_MYSQL = "dd-MM-yyyy";

	/**
	 * Retorna una cadena vacia en caso de ser null
	 * 
	 * @param texto
	 * @return
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
			return "";
		}

	}

	/**
	 * Convierte un objeto a zero(Integer) si fuese null
	 * 
	 * @param texto
	 * @return
	 */
	public static Integer nullToZero(Object texto) {
		try {
			if (texto == null)
				return Integer.valueOf(0);
		} catch (Exception e) {
			return Integer.valueOf(0);
		}
		return Integer.valueOf(Integer.parseInt(texto.toString()));
	}

	/**
	 * Convierte un objeto a zero(Double) si fuese null
	 * 
	 * @param texto
	 * @return
	 */
	public static Double nullToZeroD(Object texto) {
		try {
			if (texto == null)
				return Double.valueOf(0);
		} catch (Exception e) {
			return Double.valueOf(0);
		}
		return Double.valueOf(texto.toString());
	}

	/**
	 * Convierte un objeto a zero(Long) si fuese null
	 * 
	 * @param texto
	 * @return
	 */
	public static Long nullToZeroL(Object texto) {
		try {
			if (texto == null)
				return Long.valueOf(0);
			return Long.valueOf(texto.toString());
		} catch (Exception e) {
			return Long.valueOf(0);
		}
	}

	/**
	 * entrega un objetod el tipo GregorianCalendar con la fecha indicada
	 * 
	 * @param fecha
	 *            texto a convertir en fecha
	 * @param formato
	 *            usar Utils.FORMATO_FECHA_CORTA o Utils.FORMATO_FECHA_LARGE
	 * @return objeto gregoriancalendar con la fecha en el formato indicado
	 * @throws Exception
	 */
	public static GregorianCalendar stringToCalendar(String fecha,
			String formato) throws Exception {
		fecha = nullToBlank(fecha);
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat df = new SimpleDateFormat(formato);
		gc.setTime(df.parse(fecha));
		return gc;
	}

	/**
	 * Convierte fecha de tipo calendar a String con formato
	 * 
	 * @param fecha
	 * @param formato
	 * @return
	 * @throws Exception
	 */
	public static String CalendarToString(Calendar fecha, String formato)
			throws Exception {
		if (nullToBlank(fecha).equals("")) {
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat(formato);
		return df.format(fecha.getTime());
	}

	/**
	 * Convierte Date a String con formato
	 * 
	 * @param fecha
	 * @param formato
	 * @return
	 * @throws Exception
	 */
	public static String DateToString(Date fecha, String formato)
			throws Exception {
		if (nullToBlank(fecha).equals("")) {
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat(formato);
		return df.format(fecha);
	}

	/**
	 * Valida si es una fecha correcta
	 * 
	 * @param fecha
	 * @param formato
	 * @return
	 * @throws Exception
	 */
	public static String ValidaDate(String fecha, String formato)
			throws Exception {
		fecha = nullToBlank(fecha);
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat df = new SimpleDateFormat(formato);
		gc.setTime(df.parse(fecha));
		return df.format(gc.getTime());
	}

	/**
	 * Convierte fecha cadena a fecha Date
	 * 
	 * @param fecha
	 * @param formato
	 * @return
	 * @throws Exception
	 */
	public static Date stringToDate(String fecha, String formato)
			throws Exception {
		fecha = nullToBlank(fecha);
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat df = new SimpleDateFormat(formato);
		gc.setTime(df.parse(fecha));
		return gc.getTime();
	}

	/**
	 * Crea un archivo con extension jpg en la carpeta temporal del proyecto web
	 * para poder mostrarla en la pagina web
	 * 
	 * @param imagenBuffer
	 *            InputStream que contiene la imagen
	 * @param extra
	 *            texto para incluir en el nombre (D: dedo; H:huella)
	 * @param path
	 *            ruta en donde se grabar� la imagen (ruta de la carpeta que
	 *            contiene los html - webcontent / publichtml)
	 * @return nombre de la imagen con ruta relativa para pintarla en el html
	 * @throws Exception
	 */
	public static String guardaImagenEnDisco(InputStream imagenBuffer,
			String extra, String path) throws Exception {
		File fichero = null;
		String nombre = "";
		try {
			Calendar fe = new GregorianCalendar();
			nombre = extra + fe.getTimeInMillis() + ".jpg";
			fichero = new File(path + "\\temp\\" + nombre);

			BufferedInputStream in = new BufferedInputStream(imagenBuffer);
			BufferedOutputStream out = new BufferedOutputStream(
					new FileOutputStream(fichero));

			byte[] bytes = new byte[8096];
			int len = 0;
			while ((len = in.read(bytes)) > 0) {
				out.write(bytes, 0, len);
			}
			out.flush();
			out.close();
			in.close();
			System.out.println("archivo grabado en : "
					+ fichero.getAbsolutePath());

		} catch (Exception e) {
			System.out.println("Error al escribir en disco " + e.getMessage());
		}
		return "temp/" + nombre;
	}

	/**
	 * Copia un archivo en el sigiente especificado. El metodo coge la ruta,
	 * asigna un nombre y escrive el archivo retornando el nomrbe y la ruta del
	 * archivo nuevo
	 * 
	 * @param in
	 *            InputStream origen
	 * @param ruta
	 *            ruta destino
	 * @param extension
	 *            extension que llevara el archivo
	 * @return nombre del nuevo archivo
	 * @throws Exception
	 */
	public static String copy(InputStream in, String ruta, String extension)
			throws Exception {
		GregorianCalendar now = new GregorianCalendar();
		String tiempo = "" + now.getTimeInMillis();
		String nombre = ruta + File.separator + "up_" + tiempo + "."
				+ extension;
		OutputStream out = new FileOutputStream(nombre);
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
		return "up_" + tiempo + "." + extension;
	}

	/**
	 * recibe de la base de datos un decimal de la forma <b>00000.00</b> o
	 * <b>00000</b> (sin decimales)<br/>
	 * y lo formatea de la forma <b>00000.00</b>
	 * 
	 * @param numero
	 * @return
	 */
	public static String formatoDecimalPunto(String numero) {
		if (!nullToBlank(numero).equals("")) {
			numero = numero.trim();
			String s = "#########.##";
			DecimalFormatSymbols dformater_rules = new DecimalFormatSymbols();
			dformater_rules.setDecimalSeparator('.');// con este simbolo
														// separara a los
														// decimales
			DecimalFormat decimalFormat = new DecimalFormat(s, dformater_rules);
			decimalFormat.setMaximumFractionDigits(2);
			decimalFormat.setMinimumFractionDigits(2);
			System.out.println(" 177 --> " + numero);
			Double num = Double.parseDouble(numero);
			return decimalFormat.format(num);
		}
		return "";
	}

	/**
	 * Valida que la lista contenga un objeto no nulo en la posicion indicada
	 * 
	 * @param lista
	 *            lista en la cual uscar un objeto no nulo
	 * @param i
	 *            posicion en la lista
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean validaNotNullenLista(List lista, int i) {
		try {
			if (lista.get(i) != null) {
				return true;
			}
			return false;
		} catch (IndexOutOfBoundsException e) {
			return false;
		} catch (Exception ez) {
			return false;
		}
	}

	/**
	 * Convierte Date a tipo Calendar
	 * 
	 * @param fecha
	 * @param formato
	 * @return
	 * @throws Exception
	 */
	public static Calendar dateToCalendar(Date fecha, String formato)
			throws Exception {
		if (nullToBlank(fecha).equals("")) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(formato);
		String text = df.format(fecha);
		return stringToCalendar(text, formato);
	}

	/**
	 * Calcula los dias para un determinada fecha
	 * 
	 * @param fechafin
	 * @return
	 */
	public static int diasParaFecha(Date fechafin) {
		try {
			System.out.println("Fecha = " + fechafin + " / ");
			GregorianCalendar fin = new GregorianCalendar();
			fin.setTime(fechafin);
			System.out.println("fin=" + CalendarToString(fin, "dd/MM/yyyy"));
			GregorianCalendar hoy = new GregorianCalendar();
			System.out.println("hoy=" + CalendarToString(hoy, "dd/MM/yyyy"));

			if (fin.get(Calendar.YEAR) == hoy.get(Calendar.YEAR)) {
				System.out.println("Valor de Resta simple: "
						+ String.valueOf(fin.get(Calendar.DAY_OF_YEAR)
								- hoy.get(Calendar.DAY_OF_YEAR)));
				return fin.get(Calendar.DAY_OF_YEAR)
						- hoy.get(Calendar.DAY_OF_YEAR);
			} else {
				int diasAnyo = hoy.isLeapYear(hoy.get(Calendar.YEAR)) ? 366
						: 365;
				int rangoAnyos = fin.get(Calendar.YEAR)
						- hoy.get(Calendar.YEAR);
				int rango = (rangoAnyos * diasAnyo)
						+ (fin.get(Calendar.DAY_OF_YEAR) - hoy
								.get(Calendar.DAY_OF_YEAR));
				System.out.println("dias restantes: " + rango);
				return rango;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Ej. public void action() { String parameterName1 =
	 * FacesUtil.getRequestParameter("parameterName1");
	 * 
	 * @param name
	 * @return
	 */
	public static String getRequestParameter(String name) {
		return (String) FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(name);
	}

	/**
	 * Ej. public void action(ActionEvent event) { String attributeName1 =
	 * Utiles.getActionAttribute(event, "attributeName1");....
	 * 
	 * @param event
	 * @param name
	 * @return
	 */
	public static String getActionAttribute(ActionEvent event, String name) {
		return (String) event.getComponent().getAttributes().get(name);
	}

	/**
	 * Obtiene la hora y minuto de una fecha
	 * 
	 * @param fecha
	 * @return
	 */
	public static String getHorayMinuto(Date fecha) {
		SimpleDateFormat formateador = new SimpleDateFormat("hh:mm");
		return formateador.format(fecha);
	}

	/**
	 * Obtiene la IP
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String obtenerIP() throws Exception {
		InetAddress local = InetAddress.getLocalHost();
		InetAddress inet = InetAddress.getByName("" + local.getHostAddress());
		return inet.toString();
	}

	/**
	 * Envia un correo
	 * 
	 * @param users_copy
	 * @param user_sender
	 * @param user_password
	 * @param user_arrived
	 * @param server
	 * @param puerto
	 * @param sender
	 * @param user_admin
	 * @param sugerencia
	 * @param nombreCompleto
	 * @param email
	 * @param telefono
	 * @return
	 */
    public static boolean enviarCorreo(String sugerencia, String nombreCompleto, String email,String telefono,String tipoConsulta) {
        try {
            String sender = "";
            String protocol = "";
            String password = "";
							
            String path ="/data/properties/";
            String nombreArchivo = "nps_parametros.properties";	            
            // Propiedades de la conexión
            Properties propMail = new Properties();
			
            Properties prop = new Properties();
            InputStream is = new FileInputStream(path+nombreArchivo);//Utiles.class.getClassLoader().getResourceAsStream(Constantes.MAIL_PROPERTY);
            prop.load(is);
			    
            String ubicacion_lectura = prop.getProperty(Constantes.PROPERTY_UBICACION);
            log.info("ubicacion_lectura====>>> " + ubicacion_lectura);
		
            /////////////////////////////////////////////////
            //0=leerá desde el property, 1=leera desde el WL
            if(ubicacion_lectura.equals("0")){	
                log.info("LEYENDO DESDE PROPERTIES");
                //obtenemos la cantidad de parametros a leer
                String cant_param = prop.getProperty(Constantes.PROPERTY_CANT_PARAM);
                log.info("cant_param: " + cant_param);

                if(cant_param == null || cant_param.equals("") || cant_param.equals("0")){
                        log.info("no se pudo obtener la cantidad de parámetros");
                        return false;
                }
                int cantParam=Integer.parseInt(cant_param);

                for (Enumeration e = prop.propertyNames(); e.hasMoreElements();) {
                    Object obj = e.nextElement();

                    if(obj.toString().startsWith("mail.")){
                            String[] value = prop.getProperty(obj.toString()).split(",");
                            log.info("Parámetros mail: " + value[0] + " - " + value[1]);
                            propMail.setProperty(value[0], value[1]);
                    }
                }
                ///////////
                if(propMail.containsKey(Constantes.PROPERTY_NAME_USER) == true){
                    sender = propMail.getProperty(Constantes.PROPERTY_NAME_USER);
                    log.info("sender : " + sender);
                }
                else{
                    log.info("El user sender no esta configurado");
                            return false;
                }

                if(propMail.containsKey(Constantes.PROPERTY_NAME_PROTOCOL) == true){
                    protocol = propMail.getProperty(Constantes.PROPERTY_NAME_PROTOCOL);
                    log.info("protocol : " + protocol);
                }
                else{
                    log.info("El protocolo no esta configurado");
                    return false;
                }			    

                Session sessionJavax = null;
                if(propMail.containsKey(Constantes.PROPERTY_NAME_PASS) == true){
                    password = propMail.getProperty(Constantes.PROPERTY_NAME_PASS);
                    propMail.remove(Constantes.PROPERTY_NAME_PASS);
                    log.info("password : " + password);
                    log.info("propMail.toString(): " + propMail.toString());
                    sessionJavax = Session.getDefaultInstance(propMail, new MyPasswordAuthenticator(sender, password));
                }
                else{
                    sessionJavax = Session.getInstance(propMail);
                }				

                log.info("pasando la linea de sessionJavax");
                StringBuffer mensaje = obtenerTextoAdministrador(nombreCompleto,sugerencia,email,telefono,tipoConsulta);

                log.info("Construyendo mensaje");
                // Construimos el mensaje
                MimeMessage message = new MimeMessage(sessionJavax);
                message.setFrom(new InternetAddress(sender));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(propMail.getProperty(Constantes.PROPERTY_NAME_USER)));
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(email));
                //message.addRecipients(Message.RecipientType.BCC, dest);
                message.setSubject("NOTIFICACION DE SUGERENCIA");
                message.setText(mensaje.toString(), "UTF-8", "html");

                Transport t = sessionJavax.getTransport(protocol);
                log.info("evaluando password");

                if(!password.equals("")){
                        log.info("password : " + password);
                        t.connect(sender, password);
                        t.sendMessage(message, message.getAllRecipients());
                        t.close();
                }else{
                    t.send(message);
                    t.close();
                }
                
            }else{
                log.info("LEYENDO DESDE WL");
                InitialContext ic = new InitialContext();
                Session session = (Session)ic.lookup(prop.getProperty(Constantes.PROPERTY_NAME_WL));
		
                //Properties propWL = session.getProperties();
                Properties propWL = new Properties();
                propMail = session.getProperties();
                propWL.put("mail.from", propMail.getProperty(Constantes.PROPERTY_NAME_USER)); 
                Session sessionJavax = session.getInstance(propWL);
                
                log.info("pasando la linea de sessionJavax");

                StringBuffer mensaje = obtenerTextoAdministrador(nombreCompleto,sugerencia,email,telefono,tipoConsulta);

                log.info("Construyendo mensaje");
                // Construimos el mensaje
                MimeMessage message = new MimeMessage(sessionJavax);
                message.setFrom();
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(propMail.getProperty(Constantes.PROPERTY_NAME_USER)));
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(email));

                message.setSubject("NOTIFICACION DE SUGERENCIA");
                message.setSentDate(new Date());
                message.setText(mensaje.toString(), "UTF-8", "html");
                
                Transport.send(message);
            }
	}catch(IOException ioe) {
            ioe.printStackTrace();
            return false;
        } catch (AddressException address) {
            address.printStackTrace();
            return false;
        } catch (MessagingException message) {
            message.printStackTrace();
            log.error("MessagingException: " + message.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    
    public static StringBuffer obtenerTextoAdministrador(String nombreCompleto,String sugerencia,String email, String telefono,String tipoConsulta) throws Exception{
        StringBuffer mensaje = new StringBuffer();
        
        mensaje.append("<table border=\"0\" cellpadding=\"3\" cellspacing=\"0\" width=\"700px\" align=\"left\"> ");
        mensaje.append("<tr><td width=\"700px\"></td></tr>");
        mensaje.append("<tr><td>Se&ntilde;or(a) " + nombreCompleto + " el "
                        + Utiles.DateToString(new Date(), "dd/MM/yyyy")
                        + " a horas " + Utiles.getHorayMinuto(new Date())
                        + " <br></td></tr>");
        mensaje.append("<tr><td>se registr&oacute; desde la opci&oacute;n Sugerencias del Aplicativo Consulta de Requisitos y Obligaciones lo siguiente: <br><br></td></tr>");
        mensaje.append("<tr><td>" + sugerencia + "<br><br></td></tr>");
        mensaje.append("</table>"); 
        
        return mensaje;
    } 

}
