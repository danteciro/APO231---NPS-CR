package gob.osinergmin.consulta.controller;

import gob.osinergmin.consulta.dto.ActividadDTO;
import gob.osinergmin.consulta.dto.BaseLegalDTO;
import gob.osinergmin.consulta.dto.MaestroColumnaDTO;
import gob.osinergmin.consulta.dto.ObligacionTipoDTO;
import gob.osinergmin.consulta.dto.ObligacionesDTO;
import gob.osinergmin.consulta.dto.ReporteObligacionesDTO;
import gob.osinergmin.consulta.dto.SugerenciaDTO;
import gob.osinergmin.consulta.service.CaptchaServiceSingleton;
import gob.osinergmin.consulta.service.ConsultaRequisitosService;
import gob.osinergmin.consulta.service.MaestroColumnaService;
import gob.osinergmin.consulta.service.ObligacionesService;
import gob.osinergmin.consulta.util.Constantes;
import gob.osinergmin.consulta.util.ConstantesWeb;
import gob.osinergmin.consulta.util.JasperUtil;
import gob.osinergmin.consulta.util.StringUtil;
import gob.osinergmin.consulta.util.Utiles;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Encoder;

import com.octo.captcha.service.CaptchaServiceException;

/**
 * Clase controladora que permite gestionar el Registro de los expedientes
 * 
 * @author lchancayauri, cflorian
 * @version 1.1
 * @see
 */
@Controller
@RequestMapping("/consultaObligaciones")
public class ObligacionesController {
	private static final Logger log = LoggerFactory
			.getLogger(ObligacionesController.class);
	
	@Inject
    private ConsultaRequisitosService consultaRequisitosService;

	@Value("${server}")
	private String server;
	@Value("${sender}")
	private String sender;
	@Value("${user_sender}")
	private String user_sender;
	@Value("${user_password}")
	private String user_password;
	@Value("${user_arrived}")
	private String user_arrived;
	@Value("${puerto}")
	private String puerto;
	@Value("${user_admin}")
	private String user_admin;
	@Value("${users_copy}")
	private String users_copy;

	@Value("${ayuda.actividad}")
	private String ayudaActividad;
	@Value("${ayuda.rubro}")
	private String ayudaRubro;
	@Value("${ayuda.obligacionTipo}")
	private String ayudaObligacionTipo;
	@Value("${ayuda.clasificacion}")
	private String ayudaClasificacion;
	@Value("${ayuda.criticidad}")
	private String ayudaCriticidad;

	@Inject
	private ConsultaRequisitosService requisitoService;
	@Inject
	private ObligacionesService obligacionesService;
	@Inject
	private MaestroColumnaService maestroColumnaService;

	private List<ObligacionesDTO> listaObligaciones;
	
	private List<ReporteObligacionesDTO> listaReporteWeb;
	private List<BaseLegalDTO> lstBasesLegales;
	
	private String desRubro;
	private String desObligacionTipo;
	private	String desClasificacion;
	private String desCriticidad;
	
	Locale locale1 = new Locale("es", "ES");

	/**
	 * Metodo que da inicio a la clase
	 * 
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public String inicio(Model model, HttpServletRequest request, HttpServletResponse response) {
		log.info("inicio - ObligacionesController");
		opcion1(model, request, response);
		return ConstantesWeb.Navegacion.PAGE_MENU_OBLIGACIONES_OPCION_1;
	}

	/**
	 * Metodo que redirecciona a la opcion publica de consulta de obligaciones
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/opcion1")
	public String opcion1(Model model, HttpServletRequest request, HttpServletResponse response) {
		log.info("opcion1 - ObligacionesController");
		model.addAttribute(Constantes.P_ACTIVIDAD, ayudaActividad);
		model.addAttribute(Constantes.P_RUBRO, ayudaRubro);
		model.addAttribute(Constantes.P_OBLIGACION_TIPO, ayudaObligacionTipo);
		model.addAttribute(Constantes.P_CLASIFICACION, ayudaClasificacion);
		model.addAttribute(Constantes.P_CRITICIDAD, ayudaCriticidad);
		List<ActividadDTO> lstActividades = requisitoService
				.listarActitividades();
		model.addAttribute("lstActividades", lstActividades);

		if (request.getSession().isNew() == true) {
			Long contador = (Long) request.getSession().getServletContext()
					.getAttribute(Constantes.P_CONTADOR_OBLIGACIONES);
			log.info("contador: " + contador);
			request.getSession().getServletContext()
					.setAttribute(Constantes.P_CONTADOR_OBLIGACIONES, contador + 1L);
		}
		
		return ConstantesWeb.Navegacion.PAGE_MENU_OBLIGACIONES_OPCION_1;
	}

	/**
	 * Metodo que redirecciona a la opcion de consulta para scop
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/scop")
	public String scop(Model model, HttpServletRequest request,
			HttpSession session) {
		log.info("scop - ObligacionesController");
		String login = "";

		try {
			login = (String) request.getParameter("p_usuario");
			if (StringUtil.nullToBlank(login).equals("")) {
				log.error("No se pudo obtener el login");
				model.addAttribute("exception" + "No se pudo obtener el Login");
				return ConstantesWeb.Navegacion.PAGE_ERROR;
			}
		} catch (Exception e) {
			log.error("No se pudo obtener el login");
			model.addAttribute("exception" + e);
			return ConstantesWeb.Navegacion.PAGE_ERROR;
		}

		model.addAttribute(Constantes.P_ACTIVIDAD, ayudaActividad);
		model.addAttribute(Constantes.P_RUBRO, ayudaRubro);
		model.addAttribute(Constantes.P_OBLIGACION_TIPO, ayudaObligacionTipo);
		model.addAttribute(Constantes.P_CLASIFICACION, ayudaClasificacion);
		model.addAttribute(Constantes.P_CRITICIDAD, ayudaCriticidad);

		List<ActividadDTO> lstActividades = requisitoService
				.listarActitividades();
		model.addAttribute("lstActividades", lstActividades);

		Map mapDatosScop = new HashMap();
		try {
			log.debug("Login obtenido1: " + login);
			log.info("Login obtenido2: " + login);
			log.error("Login obtenido3: " + login);
			session.setAttribute(Constantes.LOGIN_USUARIO, login);
			Map mapDatos = obligacionesService.obtenerTipoUsuario(login);
			session.setAttribute(Constantes.LOGIN_USUARIO_MAP, mapDatos);
			String tipoUsuario = (String) mapDatos.get(Constantes.MAP_TIPO_USU);
			if (tipoUsuario.equals("A") || tipoUsuario.equals("X")) {
				return ConstantesWeb.Navegacion.PAGE_MENU_OBLIGACIONES_OPCION_1;
			}

			mapDatosScop = obligacionesService.obtenerActividadYRubro(login);
			model.addAttribute(
					"ACTIVIDAD",
					new Long(StringUtil.nullToBlank(mapDatosScop
							.get(Constantes.MAP_ID_ACTIVIDAD))));
			model.addAttribute(
					"RUBRO",
					new Long(StringUtil.nullToBlank(mapDatosScop
							.get(Constantes.MAP_ID_RUBRO))));
		} catch (Exception e) {
			log.error("Error al recuperar datos de la BD");
			e.printStackTrace();
			model.addAttribute("exception" + e);
			return ConstantesWeb.Navegacion.PAGE_ERROR;
		}

		List<ActividadDTO> lstRubros = requisitoService.listarRubros(new Long(
				StringUtil.nullToBlank(mapDatosScop
						.get(Constantes.MAP_ID_ACTIVIDAD))));
		model.addAttribute("lstRubros", lstRubros);

		if (request.getSession().isNew() == true) {
			Long contador = (Long) request.getSession().getServletContext()
					.getAttribute(Constantes.P_CONTADOR_OBLIGACIONES);
			request.getSession().getServletContext()
					.setAttribute(Constantes.P_CONTADOR_OBLIGACIONES, contador + 1L);
		}

		return ConstantesWeb.Navegacion.PAGE_MENU_OBLIGACIONES_OPCION_2;
	}

	/**
	 * Metodo que redirecciona a la opcion de consulta de SFH
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sfh")
	public String sfh(Model model) {
		log.info("sfh - ObligacionesController");
		model.addAttribute(Constantes.P_ACTIVIDAD, ayudaActividad);
		model.addAttribute(Constantes.P_RUBRO, ayudaRubro);
		model.addAttribute(Constantes.P_OBLIGACION_TIPO, ayudaObligacionTipo);
		model.addAttribute(Constantes.P_CLASIFICACION, ayudaClasificacion);
		model.addAttribute(Constantes.P_CRITICIDAD, ayudaCriticidad);
		List<ActividadDTO> lstActividades = requisitoService
				.listarActitividades();
		model.addAttribute("lstActividades", lstActividades);
		return ConstantesWeb.Navegacion.PAGE_MENU_OBLIGACIONES_OPCION_3;
	}

	/**
	 * Metodo que obtiene los rubros segun el padre actividad
	 * 
	 * @param idGrupoActividad
	 * @return
	 */
	@RequestMapping(value = "/obtenerActividades", method = RequestMethod.GET)
	public @ResponseBody
	List<ActividadDTO> obtenerActividades(Long idGrupoActividad) {
		log.info("obtenerActividades - ObligacionesController");
		if (idGrupoActividad == null) {
			return null;
		}
		List<ActividadDTO> lstRubros = requisitoService
				.listarRubros(idGrupoActividad);
		return lstRubros;
	}

	/**
	 * Metodo que obtiene las obligaciones tipo segun el rubro
	 * 
	 * @param idActividad
	 * @return
	 */
	@RequestMapping(value = "/obtenerObligacionTipo", method = RequestMethod.GET)
	public @ResponseBody
	List<ObligacionTipoDTO> obtenerObligacionTipo(Long idActividad) {
		log.info("obtenerObligacionTipo - ObligacionesController");
		if (idActividad == null) {
			return null;
		}
		List<ObligacionTipoDTO> lstObligacionTipoDTO = obligacionesService
				.listarObligacionesTipo(idActividad);
		return lstObligacionTipoDTO;
	}

	/**
	 * Metodo que obtien las clasificaciones
	 * 
	 * @return
	 */
	@RequestMapping(value = "/obtenerClasificacion", method = RequestMethod.GET)
	public @ResponseBody
	List<MaestroColumnaDTO> obtenerClasificacion() {
		log.info("obtenerClasificacion - ObligacionesController");
		List<MaestroColumnaDTO> lstClasificacion = obligacionesService
				.listarClasificacion(Constantes.DOMINIO_OBLIG_CLASIF,
						Constantes.APLICACION_OBLIGACIONES);
		return lstClasificacion;
	}
	
	@RequestMapping(value = "/obtenerClasificacionFiltro", method = RequestMethod.GET)
	public @ResponseBody
	List<MaestroColumnaDTO> obtenerClasificacionFiltro(Long idActividad,Long idObligacionTipo) {
		log.info("obtenerClasificacion - ObligacionesController");
		List<MaestroColumnaDTO> lstClasificacion = obligacionesService.listarClasificacionFiltro(idActividad,idObligacionTipo);
		return lstClasificacion;
	}

	/**
	 * Metodo que obtiene las criticidades
	 * 
	 * @return
	 */
	@RequestMapping(value = "/obtenerCriticidad", method = RequestMethod.GET)
	public @ResponseBody
	List<MaestroColumnaDTO> obtenerCriticidad() {
		log.info("obtenerCriticidad - ObligacionesController");
		List<MaestroColumnaDTO> lstClasificacion = obligacionesService
				.listarClasificacion(Constantes.DOMINIO_OBLIG_CRITIC,
						Constantes.APLICACION_OBLIGACIONES);
		return lstClasificacion;
	}

	/**
	 * Metodo que lista las obligaciones segun los filtros seleccionados y el
	 * listado de bases legales
	 * 
	 * @param rows
	 * @param page
	 * @param session
	 * @param model
	 * @param request
	 * @param idRubro
	 * @param idObligacionTipo
	 * @param idClasificacion
	 * @param idCriticidad
	 * @return
	 */
	@RequestMapping(value = "/obtenerGridObligaciones", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> obtenerGridObligaciones(int rows, int page,
			HttpSession session, Model model, HttpServletRequest request,
			Long idRubro, Long idObligacionTipo, Long idClasificacion,
			Long idCriticidad, String desRubro, String desObligacionTipo,
			String desClasificacion, String desCriticidad, HttpServletResponse response) {
		log.info("obtenerGridObligaciones - ObligacionesController");
		
		this.desRubro = desRubro;
		this.desObligacionTipo = desObligacionTipo;
		this.desClasificacion = desClasificacion;
		this.desCriticidad = desCriticidad;
		
		
		listaObligaciones = obligacionesService.listarObligaciones(idRubro,
				idObligacionTipo, idClasificacion, idCriticidad);
		
		
		List<ObligacionesDTO> lstObligaciones = new ArrayList<ObligacionesDTO>();
		if (idObligacionTipo.equals(new Long(0))) {
			for (ObligacionesDTO obligacionDTO : listaObligaciones) {
				if (obligacionDTO.getIdActividad().equals(idRubro)) {
					if (!idClasificacion.equals(new Long(0))) {
						if (obligacionDTO.getIdClasificacion().intValue() == idClasificacion
								.intValue()) {
							
							/*Map map = adjuntarArchivoAlfresco(obligacionDTO.getIdDocAdjunto());
							if(map != null){
								obligacionDTO.setArchivo((byte[])map.get(Constantes.TIPO_BYTE));
								obligacionDTO.setArchivo64((String)map.get(Constantes.TIPO_ENCODE));
							}*/					
							
							lstObligaciones.add(obligacionDTO);
						}
					} else {
						
						/*Map map = adjuntarArchivoAlfresco(obligacionDTO.getIdDocAdjunto());
						if(map != null){
							obligacionDTO.setArchivo((byte[])map.get(Constantes.TIPO_BYTE));
							obligacionDTO.setArchivo64((String)map.get(Constantes.TIPO_ENCODE));
						}*/	
						
						lstObligaciones.add(obligacionDTO);
					}
				}
			}
		} else {
			for (ObligacionesDTO obligacionDTO : listaObligaciones) {
				if (obligacionDTO.getIdActividad().equals(idRubro)
						&& obligacionDTO.getIdSupervision().equals(
								idObligacionTipo)) {

					if (!idClasificacion.equals(new Long(0))) {
						if (obligacionDTO.getIdClasificacion().intValue() == idClasificacion
								.intValue()) {
							
							/*Map map = adjuntarArchivoAlfresco(obligacionDTO.getIdDocAdjunto());
							if(map != null){
								obligacionDTO.setArchivo((byte[])map.get(Constantes.TIPO_BYTE));
								obligacionDTO.setArchivo64((String)map.get(Constantes.TIPO_ENCODE));
							}*/
							
							lstObligaciones.add(obligacionDTO);
						}
					} else {
						
						/*Map map = adjuntarArchivoAlfresco(obligacionDTO.getIdDocAdjunto());
						if(map != null){
							obligacionDTO.setArchivo((byte[])map.get(Constantes.TIPO_BYTE));
							obligacionDTO.setArchivo64((String)map.get(Constantes.TIPO_ENCODE));
						}*/
						
						lstObligaciones.add(obligacionDTO);
					}
				}
			}
		}

		Long contador = new Long(lstObligaciones.size());
		int indiceInicial = (page - 1) * rows;
		int indiceFinal = indiceInicial + rows;
		List<ObligacionesDTO> listaObligacionesPaginada = lstObligaciones
				.subList(
						indiceInicial,
						indiceFinal > lstObligaciones.size() ? lstObligaciones
								.size() : indiceFinal);
		Long numeroFilas = (contador / rows);
		if ((contador % rows) > 0) {
			numeroFilas = numeroFilas + 1L;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", numeroFilas);
		map.put("pagina", page);
		map.put("registros", contador);
		map.put("filas", listaObligacionesPaginada);

		lstBasesLegales = new ArrayList<BaseLegalDTO>();
		Map<String, Object> mapTemp = new HashMap<String, Object>();
		Long indice = 0L;
		for (int i = 0; i < lstObligaciones.size(); i++) {
			ObligacionesDTO obligacionDTO = lstObligaciones.get(i);
			
			String[] arrayBasesLegales = obligacionDTO.getBaseLegal().split("_");
			for(int y = 0; y < arrayBasesLegales.length; y++){
				if(!arrayBasesLegales[y].trim().equals("")){
					if(i == 0){
						lstBasesLegales.add(new BaseLegalDTO(++indice,arrayBasesLegales[y].trim()));
						mapTemp.put(arrayBasesLegales[y].trim(), arrayBasesLegales[y].trim());
					}
					else{
						boolean existe = mapTemp.containsKey(arrayBasesLegales[y].trim());
						if(!existe){
							lstBasesLegales.add(new BaseLegalDTO(++indice, arrayBasesLegales[y].trim()));
							mapTemp.put(arrayBasesLegales[y].trim(), arrayBasesLegales[y].trim());
						}
					}
				}
			}
		}

		map.put("lstBasesLegales", lstBasesLegales);
		String valorUIT = consultaRequisitosService.obtenerValorUIT();
        map.put("valorUIT", valorUIT);
        model.addAttribute("valorUIT", valorUIT);
		return map;
	}
	
	

	@RequestMapping(value = "/obtenerGridObligaciones2", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> obtenerGridObligaciones2(int rows, int page,
			HttpSession session, Model model, HttpServletRequest request,
			Long idRubro, Long idObligacionTipo, Long idClasificacion,
			Long idCriticidad, String desRubro, String desObligacionTipo,
			String desClasificacion, String desCriticidad, HttpServletResponse response) {
		log.info("obtenerGridObligaciones - ObligacionesController");
		
		this.desRubro = desRubro;
		this.desObligacionTipo = desObligacionTipo;
		this.desClasificacion = desClasificacion;
		this.desCriticidad = desCriticidad;
		
		//lchancayauri
		//listaReporteWeb = obligacionesService.consultaReporteWeb(idRubro,
		//		idObligacionTipo, idClasificacion, idCriticidad);
		 Object[] data = obligacionesService.consultaReporteWeb(idRubro,
						idObligacionTipo, idClasificacion, idCriticidad);
		 lstBasesLegales = new ArrayList<BaseLegalDTO>();
		 listaReporteWeb = (List<ReporteObligacionesDTO>)data[0]; 
		 List<String> listaDescTipiLegal = (List<String>)data[1];
		 for (String descTipiLegal : listaDescTipiLegal) {
			 BaseLegalDTO baseLegalDTO = new BaseLegalDTO();
			 baseLegalDTO.setDescripcionBaseLegal(descTipiLegal.trim());
			 lstBasesLegales.add(baseLegalDTO);
		 }
		//lchancayauri
		

		Long contador = new Long(listaReporteWeb.size());
		int indiceInicial = (page - 1) * rows;
		int indiceFinal = indiceInicial + rows;
		List<ReporteObligacionesDTO> listaObligacionesPaginada = listaReporteWeb
				.subList(
						indiceInicial,
						indiceFinal > listaReporteWeb.size() ? listaReporteWeb
								.size() : indiceFinal);
		Long numeroFilas = (contador / rows);
		if ((contador % rows) > 0) {
			numeroFilas = numeroFilas + 1L;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", numeroFilas);
		map.put("pagina", page);
		map.put("registros", contador);
		map.put("filas", listaObligacionesPaginada);

		map.put("lstBasesLegales", lstBasesLegales);
		String valorUIT = consultaRequisitosService.obtenerValorUIT();
        map.put("valorUIT", valorUIT);
        //lchancayauri
        //model.addAttribute("valorUIT", valorUIT);
        //lchancayauri
        /*
        List<ReporteObligacionesDTO> listaObligacionesPaginada2 = new ArrayList<ReporteObligacionesDTO>();
        ReporteObligacionesDTO obligacionDto = null;
        obligacionDto = new ReporteObligacionesDTO("1","1","2", "obligacion x", "1", "2", "RCD XXX", "1", "2", "2.1.5", "1", "1", "HASTA 300 UIT","1", "1", "Incumplimiento a", "1", "1", "0.15 UIT", "1", "1", "RGG 147", "1", "1", "Baja", "1", "2" );
        listaObligacionesPaginada2.add(obligacionDto);
        obligacionDto = new ReporteObligacionesDTO("1","0","2", "obligacion x", "0", "2", "RCD XXX", "0", "2", "2.1.6", "1", "1", "HASTA 200 UIT","1", "1", "Incumplimiento b", "1", "1", "0.20 UIT", "1", "1", "RGG 95", "1", "1", "Baja", "0", "2" );
        listaObligacionesPaginada2.add(obligacionDto);
        obligacionDto = new ReporteObligacionesDTO("2","1","2", "obligacion y", "1", "2", "RCD YYY", "1", "2", "2.3", "1", "2", "HASTA 200 UIT","1", "2", "Incumplimiento aa", "1", "1", "0.29 UIT", "1", "1", "RGG 147", "1", "1", "Medio", "1", "2" );
        listaObligacionesPaginada2.add(obligacionDto);
        obligacionDto = new ReporteObligacionesDTO("2","0","2", "obligacion y", "0", "2", "RCD YYY", "0", "2", "2.3", "0", "2", "HASTA 200 UIT","0", "2", "Incumplimiento bb", "1", "1", "0.10 UIT", "1", "1", "RGG 95", "1", "1", "Medio", "0", "2" );
        listaObligacionesPaginada2.add(obligacionDto);
        obligacionDto = new ReporteObligacionesDTO("3","1","1", "obligacion z", "1", "1", "RCD zzz", "1", "1", "2.4", "1", "1", "HASTA 100 UIT","1", "1", "", "1", "1", "", "1", "1", "", "1", "1", "Alta", "1", "1" );
        listaObligacionesPaginada2.add(obligacionDto);
        */
        map.put("filas", listaReporteWeb);
        
		return map;
	}
	
	private boolean verificarExistencia(String[] lstBasesLegales, String baseLegalToCompare){
		
		for(int i = 0; i < lstBasesLegales.length; i++){
			String baseLegal = lstBasesLegales[i];
			if(!baseLegalToCompare.equals(baseLegal.trim())){
				return true;	
			}
		}
		
		return false;
	}

	/**
	 * Metodo que descarga el contenido del listado de obligaciones en formato
	 * pdf, excel y html
	 * 
	 * @param request
	 * @param sesion
	 * @param response
	 * @param tipo
	 * @throws Exception
	 */
	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	public void downloadFile(HttpServletRequest request, HttpSession sesion,
			HttpServletResponse response, String tipo) throws Exception {
		log.info("downloadFile - ObligacionesController");
		try {
			List<Map> listaReporteObligaciones = new ArrayList<Map>();
			Map oHashObligaciones = new HashMap();
			ServletContext context = sesion.getServletContext();
			
			boolean flgTieneImagen = false;
			int i = 1;
			/* SRAMOS 08112015
			for (ObligacionesDTO obligacionesDTO : listaObligaciones) {
				oHashObligaciones = new HashMap();
				oHashObligaciones.put("indice", i);
				oHashObligaciones.put("obligacionNormativa",
						obligacionesDTO.getDescripcion());
				oHashObligaciones.put("baseLegal",
						obligacionesDTO.getDescripcionBaseLegal());
				oHashObligaciones.put("tipificacion",
						obligacionesDTO.getTipificacion());
				oHashObligaciones.put("montoUIT", obligacionesDTO.getMonto());
				oHashObligaciones.put("obligacionTipo",
						obligacionesDTO.getDescripcionOligacionTipo());
					
				if(obligacionesDTO.getArchivo() != null){
					//convirtiendo byte[] to inputStream
					byte[] archivoByte = obligacionesDTO.getArchivo();
					InputStream inputStr = new ByteArrayInputStream(archivoByte);				
						oHashObligaciones.put("RUTA_IMAGEN_GRID",inputStr);
					flgTieneImagen = true;
				}				
				
				i++;

				listaReporteObligaciones.add(oHashObligaciones);
			}*/
			for (ReporteObligacionesDTO obligacionesDTO : listaReporteWeb) {
				oHashObligaciones = new HashMap();
				oHashObligaciones.put("obligacionNormativa", obligacionesDTO.getObligacionNormativa());
				oHashObligaciones.put("indice", i);
				oHashObligaciones.put("item", obligacionesDTO.getItem());
				oHashObligaciones.put("baseLegal", obligacionesDTO.getBaseLegal());
				oHashObligaciones.put("tipificacion", obligacionesDTO.getTipificacion());
				oHashObligaciones.put("incumplimiento", obligacionesDTO.getIncumplimiento());
				
				oHashObligaciones.put("incumplimiento", obligacionesDTO.getIncumplimiento());
				oHashObligaciones.put("sancion", obligacionesDTO.getSancion());
				oHashObligaciones.put("sancionEspecifica", obligacionesDTO.getSancionEspecifica());
				
				oHashObligaciones.put("baseLegalCriterioEspecifico", obligacionesDTO.getBaseLegalCriterioEspecifico());
				oHashObligaciones.put("criticidad", obligacionesDTO.getCriticidad());
				
				
			  	i++;
				listaReporteObligaciones.add(oHashObligaciones);
			}
			

			List<Map> listaReporteBaseLegal = new ArrayList<Map>();
			Map oHashObligacionesBaseLegal = new HashMap();
			
			//--sramos
//			lstBasesLegales=new ArrayList<BaseLegalDTO>();
			log.info("lstBasesLegales = "+lstBasesLegales.size());
			int indiceBaseLegal = 1;
                        String strListadoBaseLegal = "";
			for (BaseLegalDTO legalDTO : lstBasesLegales) {
				oHashObligacionesBaseLegal = new HashMap();
				//lchancayauri
				oHashObligacionesBaseLegal.put("indiceSubReport", new Long(indiceBaseLegal));
				//lchancayauri
				oHashObligacionesBaseLegal.put("descripcionBaseLegal",
						legalDTO.getDescripcionBaseLegal());
				listaReporteBaseLegal.add(oHashObligacionesBaseLegal);
				//lchancayauri
                                strListadoBaseLegal += String.valueOf(indiceBaseLegal)+" "+legalDTO.getDescripcionBaseLegal()+" \n";
				indiceBaseLegal++;
				//lchancayauri
			}
			
			String rutaReporte = "";
			//lchancayauri
//			if(this.desObligacionTipo.equals("")){
//				
//				if(flgTieneImagen == true){
//					// SRAMOS rutaReporte = "/WEB-INF/reports/listadoObligaciones.jasper";
//					rutaReporte = "/WEB-INF/reports/listadoObligacionessn_sr.jasper";
//				}
//				else{
//					//SRAMOS RutaReporte = "/WEB-INF/reports/listadoObligacionessn.jasper";
//					rutaReporte = "/WEB-INF/reports/listadoObligacionessn_sr.jasper";					
//				}
//			}
//			else{
//				if(flgTieneImagen == true){
//					//SRAMOS rutaReporte = "/WEB-INF/reports/listadoObligacionesOT.jasper";
//					rutaReporte = "/WEB-INF/reports/listadoObligacionessn_sr.jasper";
//				}
//				else{
//					//SRAMOS rutaReporte = "/WEB-INF/reports/listadoObligacionessnOT.jasper";
//					rutaReporte = "/WEB-INF/reports/listadoObligacionessn_sr.jasper";
//				}
//			}
			//lchancayauri
			InputStream reportStream = null;
			//lchancayauri
			//reportStream = context.getResourceAsStream(rutaReporte);
			//lchancayauri
			JRDataSource ds = new JRBeanCollectionDataSource(
					listaReporteObligaciones);
			String strNombre = "reporteObligaciones";

			HashMap parameters = new HashMap();
			parameters.put("lstBasesLegales", new JRBeanCollectionDataSource(
					listaReporteBaseLegal));
			
			InputStream reportStreamSubReport = context
					.getResourceAsStream("/WEB-INF/reports/listadoBaseLegales.jasper");
			JasperReport subreport = (JasperReport) JRLoader
					.loadObject(reportStreamSubReport);
			parameters.put("SUBREPORT_DIR", subreport);
			
			InputStream reportStreamImage = context
					.getResourceAsStream("/images/cabecera.png");
			parameters.put("RUTA_IMAGEN", reportStreamImage);
			
			String login = "";
			String nombre = "";
			try{
				Map mapDatos = (Map)sesion.getAttribute(Constantes.LOGIN_USUARIO_MAP);
				login = (String)mapDatos.get(Constantes.MAP_LOGIN);
				nombre = (String)mapDatos.get(Constantes.MAP_NOMBRE);
			}catch(Exception e){
				log.info("No existe login de usuario");
			}
			
			//formatear fecha actual
            SimpleDateFormat formateador = new SimpleDateFormat(
            		   "dd 'de' MMMM 'de' yyyy",locale1);
            		   Date fechaDate = new Date();
            		   String fecha = formateador.format(fechaDate);
            		   System.out.println(fecha);			
			
			parameters.put("P_ADMINISTRADO", nombre);
			parameters.put("P_OBLIGACION_TIPO", desObligacionTipo);
			parameters.put("P_ACTIVIDAD", desRubro);
			parameters.put("P_CODIGO_OSINERGMIN", login);
			parameters.put("P_FECHA_ACTUAL", fecha);
			//lchancayauri
			String valorUIT = consultaRequisitosService.obtenerValorUIT();
			parameters.put("VALOR_UIT", valorUIT);
                        parameters.put("strLstBasesLegales",strListadoBaseLegal);
			//lchancayauri
                        
                        		   
			String nombreArchivo;
			String contentType;
			if (tipo.equals("pdf")) {
				rutaReporte = "/WEB-INF/reports/listadoObligacionessn_sr.jasper";
				reportStream = context.getResourceAsStream(rutaReporte);
				JasperUtil.exportarPDF(reportStream, parameters, ds, response,
						strNombre);
			} else if (tipo.equals("pdfBase")) {
				nombreArchivo = "RCD27.pdf";
				contentType = "application/pdf";
			} else if (tipo.equals("excel")) {
				rutaReporte = "/WEB-INF/reports/listadoObligacionessn_sr_rep_ex.jasper";
				reportStream = context.getResourceAsStream(rutaReporte);
				JasperUtil.exportarExcel(reportStream, parameters, ds,
						response, strNombre);
			} else if (tipo.equals("html")) {
//				rutaReporte = "/WEB-INF/reports/listadoObligacionessn_sr_rep_ex.jasper";
//				rutaReporte = "/WEB-INF/reports/listadoObligacionessn_sr.jasper";
			    rutaReporte = "/WEB-INF/reports/listadoObligacionessn_sr_html.jasper";
				reportStream = context.getResourceAsStream(rutaReporte);
				JasperUtil.exportHtml(reportStream, parameters, ds, response,
						strNombre, request);
			} else {
				rutaReporte = "/WEB-INF/reports/listadoObligacionessn_sr.jasper";
				reportStream = context.getResourceAsStream(rutaReporte);
				JasperUtil.exportarPDF(reportStream, parameters, ds, response,
						strNombre);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * Metodo que abre el popup de sugerencia
	 * 
	 * @param numExpediente
	 * @param codEstado
	 * @param tipoLink
	 * @param sesion
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/abrirPopupMensaje", method = RequestMethod.GET)
	public String abrirPopupMensaje(String numExpediente, String codEstado,
			String tipoLink, HttpSession sesion, Model model) {
		log.info("abrirPopupMensaje - ObligacionesController");
		model.addAttribute(Constantes.TIPO_CONSULTA,
				Constantes.APLICACION_OBLIGACIONES);
		return ConstantesWeb.Navegacion.PAGE_MENU_OBLIGACIONES_MENSAJE;
	}

	/**
	 * Metodo que registra y envia la sugerencia
	 * 
	 * @param session
	 * @param request
	 * @param sugerencia
	 * @param nombreCompleto
	 * @param email
	 * @param telefono
	 * @param textoValidacion
	 * @param tipoConsulta
	 * @return
	 */
	@RequestMapping(value = "/enviarSugerencia", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> enviarSugerencia(HttpSession sessionLocal,
			HttpServletRequest request, String sugerencia,
			String nombreCompleto, String email, String telefono,
			String textoValidacion, String tipoConsulta) {
		log.info("enviarSugerencia - ObligacionesController");
		Map<String, Object> salida = new HashMap<String, Object>();

		try {
			

			boolean captcha = CaptchaServiceSingleton.getInstance()
					.validateResponseForID(request.getSession().getId(),
							textoValidacion.toUpperCase());

			if (captcha == false) {
				salida.put(ConstantesWeb.VV_RESULTADO, ConstantesWeb.VV_ERROR);
				salida.put(ConstantesWeb.VV_MENSAJE,
						"EL C&Oacute;DIGO CAPTCHA INCORRECTO");
				return salida;
			}

			Long idTipoConsulta = null;
			if (tipoConsulta.equals(Constantes.APLICACION_OBLIGACIONES)) {
				idTipoConsulta = maestroColumnaService.obtenerIdMaestroColumna(
						Constantes.PGH_OBLIG_SUGERENCIA,
						Constantes.APLICACION_OBLIGACIONES);
			} else {
				idTipoConsulta = maestroColumnaService.obtenerIdMaestroColumna(
						Constantes.PGH_OBLIG_SUGERENCIA,
						Constantes.APLICACION_REQUISITOS);
			}

			String login = "";
			try {
				login = (String) sessionLocal.getAttribute(Constantes.LOGIN_USUARIO);
			} catch (Exception e) {
				e.printStackTrace();
				login = "";
			}

			boolean flgRegistro = obligacionesService
					.registrarSugerencia(new SugerenciaDTO(sugerencia,
							nombreCompleto, email, telefono, idTipoConsulta,
							login));

			if (flgRegistro == false) {
				salida.put(ConstantesWeb.VV_RESULTADO, ConstantesWeb.VV_ERROR);
				salida.put(ConstantesWeb.VV_MENSAJE, "No se pudo grabar");
				return salida;
			}
			
			
			log.debug("ENVIANDO CORREO FORMA ANTIGUA");
			boolean flgEnvio = Utiles.enviarCorreo(sugerencia, nombreCompleto, email, telefono,tipoConsulta);
			if (flgEnvio == false) {
				salida.put(ConstantesWeb.VV_RESULTADO, ConstantesWeb.VV_ERROR);
				salida.put(ConstantesWeb.VV_MENSAJE,
						"No se pudo enviar el correo");
				return salida;
			}

		} catch (CaptchaServiceException e) {
			e.printStackTrace();
			salida.put(ConstantesWeb.VV_RESULTADO, ConstantesWeb.VV_ERROR);
			salida.put(ConstantesWeb.VV_MENSAJE,
					"EL C&Oacute;DIGO CAPTCHA INCORRECTO");
			return salida;
		} catch (Exception e) {
			e.printStackTrace();
			salida.put(ConstantesWeb.VV_RESULTADO, ConstantesWeb.VV_ERROR);
			salida.put(ConstantesWeb.VV_MENSAJE, "Error inesperado");
			return salida;
		}

		salida.put(ConstantesWeb.VV_RESULTADO, ConstantesWeb.VV_EXITO);
		salida.put(ConstantesWeb.VV_MENSAJE, "Se realiz&oacute; env&iacute;o correctamente");
		return salida;
	}	
	
	private Map adjuntarArchivoAlfresco(Long idDocumentoAdjunto)  {
		
		log.info("idDocumentoAdjunto: " + idDocumentoAdjunto);
		
        log.info("procesando descargaArchivoAlfresco--->" + idDocumentoAdjunto);
        if(idDocumentoAdjunto == 0L){
        	return null;
        }
			
		Map<String, Object> data = null;
		
		data = consultaRequisitosService.descargarDatosAlfresco(idDocumentoAdjunto);
			
			
		try {
	        InputStream is = null;
	        BASE64Encoder encoder = new BASE64Encoder();
	        
			//is = new FileInputStream("c://imagenTest.jpg");
			is = (InputStream)data.get("inputStream");
		
			//String nombreArchivo = "imagenTest.jpg";
			String nombreArchivo = (String)data.get("nombreArchivo");
        	
        	if(is == null){
        		log.info("InputStream is null");
        	}
        	else{
        		Map map = new HashMap();
        		byte[] archivoByte = IOUtils.toByteArray(is);
        		map.put(Constantes.TIPO_BYTE, archivoByte);
        		map.put(Constantes.TIPO_ENCODE, "data:image/png;base64,"+encoder.encode(archivoByte));
        		return map;
        	}
				
        } catch (FileNotFoundException e) {			
			e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info("--->" + ex.getMessage());
            throw new RuntimeException("IOError writing file to output stream");           
        }
		
        return null;
    }
	
	/* OSINE_SFS-600 - REQF-0015 - Inicio */
	
	@RequestMapping(value = "/descargaArchivoAlfresco", method = RequestMethod.GET)
    public void descargaArchivoAlfresco(Long idDocumentoAdjunto, HttpServletResponse response) {
        log.info("procesando descargaArchivoAlfresco--->" + idDocumentoAdjunto);
        Map<String, Object> data = obligacionesService.descargarDatosAlfresco(idDocumentoAdjunto);
        InputStream is = (InputStream)data.get("inputStream");
        String nombreArchivo = (String)data.get("nombreArchivo");
        try {
            if (is == null) {
                response.getWriter().write("ERROR: NO SE ADJUNT&Oacute; DOCUMENTO, VOLVER A INTENTAR..!!");
                return;
            }
            String nombreFichero = nombreArchivo;
            response.setHeader("Content-Disposition", "attachment; filename=\""
                    + nombreFichero + "\"");
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();

        } catch (Exception ex) {
//            ex.printStackTrace();
            log.info("--->" + ex.getMessage());
            log.info("Error writing file to output stream. Filename was '" + nombreArchivo + "'");
            throw new RuntimeException("IOError writing file to output stream");
        }
    }
	
	/* OSINE_SFS-600 - REQF-0015 - Fin */
	
}
