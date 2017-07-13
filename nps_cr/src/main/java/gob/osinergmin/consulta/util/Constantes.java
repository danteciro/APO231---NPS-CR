package gob.osinergmin.consulta.util;

/**
 * Clase que lista todas las constantes usadas en este proyecto
 * 
 * @author cflorian
 * 
 */
public class Constantes {

	// jasper
	public static final String CONTENT_TYPE_EXPORT_REPORTE_EXCEL = "application/vnd.ms-excel";
	public static final String HEADER_UNO_REPORTE_EXCEL = "Content-Disposition";
	public static final String VALOR_HEADER_UNO_REPORTE_EXCEL = "attachment; filename=\"";
	public static final String HEADER_DOS_REPORTE_EXCEL = "Cache-Control";
	public static final String VALOR_DOS_REPORTE_EXCEL = "no-store";
	public static final String HEADER_TRES_REPORTE_EXCEL = "Pragma";
	public static final String VALOR_TRES_REPORTE_EXCEL = "private";
	public static final String HEADER_CUATRO_REPORTE_EXCEL = "Expires";
	public static final int VALOR_CUATRO_REPORTE_EXCEL = 1;
	public static final String EXTENSION_ARCHIVO_EXCEL = ".xls";
	public static final String P_ACTIVIDAD = "P_ACTIVIDAD";
	public static final String P_RUBRO = "P_RUBRO";
	public static final String P_OBLIGACION_TIPO = "P_OBLIGACION_TIPO";
	public static final String P_CLASIFICACION = "P_CLASIFICACION";
	public static final String P_CRITICIDAD = "P_CRITICIDAD";
	public static final String P_CONTADOR_OBLIGACIONES = "P_CONTADOR_OBLIGACIONES";
	public static final String P_CONTADOR_REQUISITOS = "P_CONTADOR_REQUISITOS";
	public static final String P_ETAPA = "P_ETAPA";
	public static final String P_TRAMITE = "P_TRAMITE";
	public static final String P_MULTIOPERADOR = "P_MULTIOPERADOR";

	public static final String MAP_ACTIVIDAD = "MAP_ACTIVIDAD";
	public static final String MAP_RUBRO = "MAP_RUBRO";
	public static final String MAP_CODIGO = "MAP_CODIGO";
	public static final String MAP_LOGIN = "MAP_LOGIN";
	public static final String MAP_NOMBRE = "MAP_NOMBRE";
	public static final String MAP_TIPO_USU = "MAP_TIPO_USU";
	public static final String MAP_CODACTIVIDAD = "MAP_CODACTIVIDAD";
	public static final String MAP_ID_ACTIVIDAD = "MAP_ID_ACTIVIDAD";
	public static final String MAP_ID_RUBRO = "MAP_ID_RUBRO";
	public static final String LOGIN_USUARIO = "LOGIN_USUARIO";
	public static final String LOGIN_USUARIO_MAP = "LOGIN_USUARIO_MAP";
	public static final String LOGIN_USUARIO_ADMINISTADO = "ADMINISTRADO";
        
	// Constantes Consultas Requisitos
	public static final String PARAMETRO_DINAMICO_VALOR_UIT = "VU01";
	public static final String PARAMETRO_DINAMICO_TIPO_CONSULTA_PREGUNTA = "TPP";
	public static final String PARAMETRO_DINAMICO_TIPO_CONSULTA_VISUALIZACION = "TPV";

	public static final Long PREGUNTA_REQUISITO_TIPO_PARAMETRO_DINAMICO = new Long(
			1);
	public static final Long PREGUNTA_REQUISITO_TIPO_TRAMITE_PARAMETRO_DINAMICO = new Long(
			2);
	public static final Long PREGUNTA_REQUISITO_TIPO_TRAMITE_MOTIVO_TRAMITE = new Long(
			3);
	public static final Long PREGUNTA_REQUISITO_TIPO_ZONIFICACION = new Long(4);

	public static final String PROCEDIMIENTO_CALIFICACION_EVALUACION_PREVIA = "CAL02";
        
	public static final String PROCEDIMIENTO_EVALUACION_PREVIA_POSITIVO = "EP01";
        
        public static final String PREGUNTA_REQUISITO_MOTIVO_TRAMITE = "TPRTM";
        public static final String PREGUNTA_REQUISITO_ZONIFICACION = "TPRZ";

	public static final String MAESTRO_COLUMNA_CODIGO_VALOR_UIT = "VU01";
	public static final String MAESTRO_COLUMNA_DOMINIO = "VALOR_UIT";
        public static final String MAESTRO_COLUMNA_DOMINIO_CALIFICACION = "CALIFICACION";
        public static final String MAESTRO_COLUMNA_DOMINIO_EVALUACION_PREVIA = "EVALUACION_PREVIA";
        public static final String MAESTRO_COLUMNA_DOMINIO_PREGUNTA_REQUISITO = "PREGUNTA_REQUISITO";
        public static final String MAESTRO_COLUMNA_DOMINIO_TIPO_PARAMETRO = "TIPO_PARAMETRO";
        public static final String MAESTRO_COLUMNA_DOMINIO_VALOR_UIT = "VALOR_UIT";
	public static final String MAESTRO_COLUMNA_APLICACION_MYC = "MYC";

	public static final String ACTIVO = "1";
	public static final String INACTIVO = "0";
	public static final String DOMINIO_OBLIG_CLASIF = "PGH_OBLIG_CLASIF";
	public static final String DOMINIO_OBLIG_CRITIC = "PGH_OBLIG_CRITIC";
	public static final String PGH_OBLIG_SUGERENCIA = "PGH_OBLIG_SUGERENCIA";
	public static final String PGH_OBLIG_CNT_VISITA = "PGH_OBLIG_CNT_VISITA";
	public static final String APLICACION_OBLIGACIONES = "OBLIGACIONES";
	public static final String APLICACION_REQUISITOS = "REQUISITOS";
	public static final String TIPO_CONSULTA = "TIPO_CONSULTA";
	public static final String TIPO_BYTE = "TIPO_BYTE";
	public static final String TIPO_ENCODE = "TIPO_ENCODE";
	
	public static final String MAIL_PROPERTY = "consultas.properties";
	public static final String PROPERTY_UBICACION = "param.mail.ubicacion";
	public static final String PROPERTY_CANT_PARAM = "param.mail.param";
	public static final String PROPERTY_NAME_WL = "param.mail.wl.lookup";
	public static final String PROPERTY_NAME_USER = "mail.smtp.user";
	public static final String PROPERTY_NAME_PASS = "mail.smtp.password";
	public static final String PROPERTY_NAME_PROTOCOL = "mail.transport.protocol";
	

}