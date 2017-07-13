package gob.osinergmin.consulta.service.dao.impl;

import gob.osinergmin.consulta.domain.PghAutoayuda;
import gob.osinergmin.consulta.domain.PghConsultaSugerencias;
import gob.osinergmin.consulta.domain.PghContadorVisita;
import gob.osinergmin.consulta.domain.builder.AutoayudaBuilder;
import gob.osinergmin.consulta.domain.builder.MaestroColumnaBuilder;
import gob.osinergmin.consulta.domain.builder.ObligacionBuilder;
import gob.osinergmin.consulta.domain.builder.ObligacionTipoBuilder;
import gob.osinergmin.consulta.dto.AutoayudaDTO;
import gob.osinergmin.consulta.dto.MaestroColumnaDTO;
import gob.osinergmin.consulta.dto.ObligacionTipoDTO;
import gob.osinergmin.consulta.dto.ObligacionesDTO;
import gob.osinergmin.consulta.dto.ReporteObligacionesDTO;
import gob.osinergmin.consulta.dto.SugerenciaDTO;
import gob.osinergmin.consulta.service.dao.CrudDAO;
import gob.osinergmin.consulta.service.dao.ObligacionesDAO;
import gob.osinergmin.consulta.util.Constantes;
import gob.osinergmin.consulta.util.StringUtil;
import gob.osinergmin.consulta.util.Utiles;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.Query;

import oracle.jdbc.driver.OracleTypes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("ObligacionesDAO")
public class ObligacionesDAOImpl implements ObligacionesDAO {

	private static final Logger log = LoggerFactory
			.getLogger(ObligacionesDAOImpl.class);
	@Inject
	private CrudDAO crudDAO;
	
	private static SessionFactory factory;

	/**
	 * Lista las obligaciones tipo segun la actividad
	 */
	@Override
	public List<ObligacionTipoDTO> listarObligacionesTipo(Long idActividad) {
		log.info("listarObligacionesTipo - ObligacionesDAOImpl");

		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT DISTINCT(ot.id_obligacion_tipo), ot.nombre from PGH_CONF_OBLIGACION co, PGH_PROCESO_OBLIGACION_TIPO pot, PGH_OBLIGACION_TIPO ot ");
		jpql.append(" WHERE co.id_proceso = pot.id_proceso ");
		jpql.append(" AND co.id_obligacion_tipo = pot.id_obligacion_tipo ");
		jpql.append(" AND co.id_actividad = pot.id_actividad ");
		jpql.append(" AND pot.id_obligacion_tipo = ot.id_obligacion_tipo ");
		jpql.append(" AND co.estado = '1' ");
		jpql.append(" AND pot.estado = '1' ");
		jpql.append(" AND ot.estado = '1' ");
		jpql.append(" AND co.id_actividad = :pActividad ");

		Query query = crudDAO.getEm().createNativeQuery(jpql.toString());
		query.setParameter("pActividad", idActividad);

		List<Object[]> lstObligacionesTipo = query.getResultList();
		List<ObligacionTipoDTO> listaObligacionTipo = new ArrayList<ObligacionTipoDTO>();
		listaObligacionTipo = ObligacionTipoBuilder
				.getListaOlibacionTipo(lstObligacionesTipo);

		return listaObligacionTipo;
	}

	/**
	 * Lista las obligaciones segun los filtros seleccionados
	 */
	@Override
	@Transactional
	public List<ObligacionesDTO> listarObligaciones(Long rubro,
			Long obligacionTipo, Long clasificacion, Long criticidad) {
		
		ResultSet rs = null;
		CallableStatement callableStatement = null;
		Connection con = null;
				
		try{
			con = ((SessionImpl)crudDAO.getEm().getDelegate()).connection();
			//Connection con = crudDAO.getEm().getDelegate()			
			callableStatement = con.prepareCall("{call PKG_CONSULTAS.LISTAR_OBLIGACIONES(?,?,?,?,?)}");
			callableStatement.setLong(1, rubro);
			callableStatement.setLong(2, obligacionTipo);
			callableStatement.setLong(3, clasificacion);
			callableStatement.setLong(4, criticidad);
			callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
			callableStatement.executeUpdate();
			
			rs = (ResultSet) callableStatement.getObject(5);
			List<ObligacionesDTO> listaObligaciones = new ArrayList<ObligacionesDTO>();
			while (rs.next()) {
					ObligacionesDTO obligacionesDTO = new ObligacionesDTO();
					
					String desObligacion = rs.getString("DESOBLIGA");
					String desBaseLegal = rs.getString("DESBASELEGAL");
					String destipificacionUIT = rs.getString("DESTIPIFICACION_UIT") == null ? "" : rs.getString("DESTIPIFICACION_UIT");
					String desObligaTipo = rs.getString("DESOBLIGTIPO");
					String idActividad = rs.getString("ID_ACTIVIDAD");
					String idObligacion = rs.getString("ID_OBLIGACION");
					String idObligacionTipo = rs.getString("ID_OBLIGACION_TIPO");
					String idArchivo = rs.getString("ID_DOCUMENTO_ADJUNTO");
					
					obligacionesDTO.setDescripcion(StringUtil.nullToBlank(desObligacion));
		        	obligacionesDTO.setDescripcionBaseLegal(StringUtil.nullToBlank(desBaseLegal));
		        	
		        	String[] arrayDesTipificacion = destipificacionUIT.split("-@-");
		        	List<ObligacionesDTO> listobligacionesDTO = new ArrayList<ObligacionesDTO>();
		        	for(int i = 0; i < arrayDesTipificacion.length; i++){
		        		String[] detTipificacion = arrayDesTipificacion[i].split("/");
		        		try{
			        		obligacionesDTO.setTipificacion(StringUtil.nullToBlank(obligacionesDTO.getTipificacion()) + StringUtil.addComa(detTipificacion[0]));
			        	}catch(Exception e){
			        		obligacionesDTO.setTipificacion("");
			        	}		        	
			        	try{
			        		obligacionesDTO.setMonto(StringUtil.nullToBlank(obligacionesDTO.getMonto()) + StringUtil.addComa(detTipificacion[1]));
			        	}catch(Exception e){
			        		obligacionesDTO.setMonto("");
			        	}		        	
			        	try{
			        		ObligacionesDTO objObligacionesDTO= new ObligacionesDTO();
			        		objObligacionesDTO.setBaseLegal(detTipificacion[2]);
			        		listobligacionesDTO.add(objObligacionesDTO);
//			        		obligacionesDTO.setBaseLegal(StringUtil.nullToBlank(obligacionesDTO.getBaseLegal()) + StringUtil.addComa(detTipificacion[2]));
			        	}catch(Exception e){
			        		obligacionesDTO.setBaseLegal("");
			        	}
		        	}
		        	String baseLegalUnique="";
		        	String[] array=new String[0];
		        	Set<String> set = new LinkedHashSet<String>();
		        	for(ObligacionesDTO listaObj:listobligacionesDTO){
		        		set.add(listaObj.getBaseLegal());
		        		array = set.toArray(new String[0]);
		        	}
		        	for(int i=0;i<array.length;i++){
		        		baseLegalUnique +=StringUtil.addGuion(array[i]);
		        	}
		        	obligacionesDTO.setBaseLegal(baseLegalUnique);
		        	//quitando las comas de adelante
		        	obligacionesDTO.setTipificacion(!obligacionesDTO.getTipificacion().equals("") ? obligacionesDTO.getTipificacion().substring(1,obligacionesDTO.getTipificacion().length()) : "");
		        	obligacionesDTO.setMonto(!obligacionesDTO.getMonto().equals("") ? obligacionesDTO.getMonto().substring(1,obligacionesDTO.getMonto().length()) : "");
		        	obligacionesDTO.setBaseLegal(!obligacionesDTO.getBaseLegal().equals("") ? obligacionesDTO.getBaseLegal().substring(1,obligacionesDTO.getBaseLegal().length()) : "");
		        	
		        	
		        	obligacionesDTO.setDescripcionOligacionTipo(StringUtil.nullToBlank(desObligaTipo));
		        	obligacionesDTO.setIdActividad(new Long(idActividad));
		        	obligacionesDTO.setIdObligacion(new Long(idObligacion));
		        	obligacionesDTO.setIdSupervision(new Long(idObligacionTipo));	        	
		        	
		        	obligacionesDTO.setIdDocAdjunto(new Long(StringUtil.nullToBlankCero(idArchivo)));
					
					if (clasificacion != null && clasificacion.intValue() != 0){
						String idTemaObligacion = rs.getString("ID_TEMA_OBLIGACION");
						System.out.println(idTemaObligacion);
						obligacionesDTO.setIdClasificacion(new Long(idTemaObligacion));
					}
					
					listaObligaciones.add(obligacionesDTO);
					
			}
			
			
			
			return listaObligaciones;
			
		}catch(RuntimeException e){
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
			if(rs != null){
				try {
					rs.close();
					if(callableStatement !=null){
						callableStatement.close();
					}
					if(con!=null){
						con.close();
					}
				} catch (SQLException e) {}
			}
			
			
			
			
		}
/*
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT DO.DESCRIPCION AS DESOBLIGA, BL.DESCRIPCION AS DESBASELEGAL, ");
		jpql.append(" T.DESCRIPCION AS DESTIPIFICACION, TSAN.DESCRIPCION AS DESMONTOUIT, ");
		jpql.append(" OBLT.NOMBRE AS DESOBLIGTIPO, CO.ID_ACTIVIDAD, O.ID_OBLIGACION, ");
		jpql.append(" CO.ID_OBLIGACION_TIPO, T.BASES_LEGALES, MDA.NOMBRE_ARCHIVO ");

		if (clasificacion != null && clasificacion.intValue() != 0)
			jpql.append(", TOM.ID_TEMA_OBLIGACION ");

		jpql.append(" FROM PGH_OBLIGACION O, PGH_OBLIGACION_BASE_LEGAL OBL, ");
		jpql.append(" PGH_BASE_LEGAL BL, PGH_OBLIGACION_TIPIFICACION OT, ");
		jpql.append(" PGH_TIPIFICACION T, PGH_TIPIFICACION_SANCION TS, ");
		jpql.append(" PGH_TIPO_SANCION TSAN, PGH_CONF_OBLIGACION CO, ");
		jpql.append(" PGH_PROCESO_OBLIGACION_TIPO POT, PGH_OBLIGACION_TIPO OBLT, ");
		jpql.append(" MDI_DOCUMENTO_ADJUNTO MDA, PGH_DETALLE_OBLIGACION DO ");

		if (clasificacion != null && clasificacion.intValue() != 0)
			jpql.append(", PGH_TEMA_OBLIGACION_MAESTRO TOM, MDI_MAESTRO_COLUMNA MC ");

		jpql.append(" WHERE O.ID_OBLIGACION = OBL.ID_OBLIGACION ");
		jpql.append(" AND O.ID_OBLIGACION = DO.ID_OBLIGACION(+) ");
		jpql.append(" AND OBL.ID_BASE_LEGAL = BL.ID_BASE_LEGAL ");
		jpql.append(" AND O.ID_OBLIGACION = OT.ID_OBLIGACION(+) ");
		jpql.append(" AND OT.ID_TIPIFICACION = T.ID_TIPIFICACION ");
		jpql.append(" AND T.ID_TIPIFICACION = TS.ID_TIPIFICACION ");
		jpql.append(" AND TS.ID_TIPO_SANCION = TSAN.ID_TIPO_SANCION");
		jpql.append(" AND O.ID_OBLIGACION = CO.ID_OBLIGACION ");
		jpql.append(" AND CO.ID_OBLIGACION_TIPO = POT.ID_OBLIGACION_TIPO ");
		jpql.append(" AND CO.ID_PROCESO = POT.ID_PROCESO ");
		jpql.append(" AND CO.ID_ACTIVIDAD = POT.ID_ACTIVIDAD ");
		jpql.append(" AND POT.ID_OBLIGACION_TIPO = OBLT.ID_OBLIGACION_TIPO ");
		jpql.append(" AND O.ID_DOCUMENTO_ADJUNTO = MDA.ID_DOCUMENTO_ADJUNTO(+) ");

		if (clasificacion != null && clasificacion.intValue() != 0) {
			jpql.append(" AND O.ID_OBLIGACION = TOM.ID_OBLIGACION ");
			jpql.append(" AND TOM.ID_TEMA_OBLIGACION = MC.ID_MAESTRO_COLUMNA ");
		}

		jpql.append(" AND CO.ID_ACTIVIDAD = :pRubro ");

		if (obligacionTipo != null && obligacionTipo.intValue() != 0)
			jpql.append(" AND CO.ID_OBLIGACION_TIPO = :pObligacionTipo ");

		if (criticidad != null && criticidad.intValue() != 0)
			jpql.append(" AND O.ID_CRITICIDAD = :pCriticidad ");

		if (clasificacion != null && clasificacion.intValue() != 0)
			jpql.append(" AND TOM.ID_TEMA_OBLIGACION = :pTemaClasificacion ");

		Query query = crudDAO.getEm().createNativeQuery(jpql.toString());
		query.setParameter("pRubro", rubro);

		if (obligacionTipo != null && obligacionTipo.intValue() != 0)
			query.setParameter("pObligacionTipo", obligacionTipo);

		if (criticidad != null && criticidad.intValue() != 0)
			query.setParameter("pCriticidad", criticidad);

		if (clasificacion != null && clasificacion.intValue() != 0)
			query.setParameter("pTemaClasificacion", clasificacion);

		List<Object[]> lstObligacionesTipo = null;
		List<ObligacionesDTO> listaObligaciones = new ArrayList<ObligacionesDTO>();
		listaObligaciones = ObligacionBuilder
				.getListaOlibacion(lstObligacionesTipo);*/

		return null;
	}

	
	
	/**
	 * SRAMOS 08112015
	 * Lista obligaciones para la consulta Web
	 */
	@Override
	@Transactional
	public Object[] consultaReporteWebObligaciones(Long rubro,			// lchancayauri
			Long obligacionTipo, Long clasificacion, Long criticidad) { // lchancayauri
//	public List<ReporteObligacionesDTO> consultaReporteWebObligaciones(Long rubro,
//			Long obligacionTipo, Long clasificacion, Long criticidad) {
		
		ResultSet rs = null;
		CallableStatement callableStatement = null;
		Connection con = null;
				
		try{
			con = ((SessionImpl)crudDAO.getEm().getDelegate()).connection();
			callableStatement = con.prepareCall("{call PKG_CONSULTAS.consultas_obligaciones_web_prc(?,?,?,?,?)}");
			callableStatement.setLong(1, rubro);
			callableStatement.setLong(2, obligacionTipo);
			callableStatement.setLong(3, clasificacion);
			callableStatement.setLong(4, criticidad);
			callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
			callableStatement.executeUpdate();
			
			rs = (ResultSet) callableStatement.getObject(5);
			List<ReporteObligacionesDTO> listaConsultaWebObligaciones = new ArrayList<ReporteObligacionesDTO>();
			
			int cantidadIterarObligacion=0;
			int cantidadIterarTipificacion=0;
			int cantidadIterarCriticidad=0;
			
			int correlativo=0;
		
			int obligacionId=0;			
			int tipificacionId=0;
			int criterioId=0;
			/* OSINE_SFS-600 - REQF-0015 - Inicio */
			int documentoAdjuntoId=0;
			/* OSINE_SFS-600 - REQF-0015 - Fin */
				
			int valorAnterior=0;
			int valorAnteriorTipificacion=0;
			int valorAnteriorCriterio=0;
			
			Map<String, String> mapDesTipiLegal = new HashMap<String, String>();
			
			while (rs.next()) 
			{
					ReporteObligacionesDTO obligacionesDTO = new ReporteObligacionesDTO();
					
					cantidadIterarObligacion = rs.getInt("CNT_OBLIGACION");
					cantidadIterarTipificacion = rs.getInt("CNT_TIPIFICACION");
					cantidadIterarCriticidad = rs.getInt("CNT_CRITERIO");
					
					obligacionId=rs.getInt("ID_OBLIGACION");
					tipificacionId=rs.getInt("ID_TIPIFICACION");
					criterioId=rs.getInt("ID_CRITERIO");
					/* OSINE_SFS-600 - REQF-0015 - Inicio */
					documentoAdjuntoId=rs.getInt("ID_DOCUMENTO_ADjUNTO");
					/* OSINE_SFS-600 - REQF-0015 - Fin */
					
								
					obligacionesDTO.setRowSpanItem(String.valueOf(cantidadIterarObligacion));
					
					obligacionesDTO.setObligacionNormativa(rs.getString("DESC_OBLIGACION"));						
					obligacionesDTO.setRowSpanObligacionNormativa(String.valueOf(cantidadIterarObligacion));
					
					obligacionesDTO.setBaseLegal(rs.getString("DESC_BASE_LEGAL"));
					obligacionesDTO.setRowSpanBaseLegal(String.valueOf(cantidadIterarObligacion));
					if(!StringUtil.isEmpty(rs.getString("DESC_CRITICIDAD"))){
						obligacionesDTO.setCriticidad(rs.getString("DESC_CRITICIDAD"));					
					}else{
						obligacionesDTO.setCriticidad("-");
					}
					obligacionesDTO.setRowSpanCriticidad(String.valueOf(cantidadIterarObligacion));
					
					
					/* OSINE_SFS-600 - REQF-0015 - Inicio */
					obligacionesDTO.setAdjunto(String.valueOf(documentoAdjuntoId));
					obligacionesDTO.setRowSpanAdjunto(String.valueOf(cantidadIterarObligacion));
					/* OSINE_SFS-600 - REQF-0015 - Fin */
					
					obligacionesDTO.setTipificacion(rs.getString("DESC_TIPIFICACION"));
					obligacionesDTO.setRowSpanTipificacion(String.valueOf(cantidadIterarTipificacion));
					
					obligacionesDTO.setSancion(rs.getString("SANCION"));
					obligacionesDTO.setRowSpanSancion(String.valueOf(cantidadIterarTipificacion));
					
					obligacionesDTO.setIncumplimiento(rs.getString("DESC_CRITERIO"));
					obligacionesDTO.setRowSpanIncumplimiento(String.valueOf(cantidadIterarCriticidad));
					
					obligacionesDTO.setSancionEspecifica(rs.getString("SANC_CRITERIO"));
					obligacionesDTO.setRowSpanSancionEspecifica(String.valueOf(cantidadIterarCriticidad));
					
					obligacionesDTO.setBaseLegalCriterioEspecifico(rs.getString("DESC_CRITERIO_LEGAL"));
					obligacionesDTO.setRowSpanBaseLegalCriterioEspecifico(String.valueOf(cantidadIterarCriticidad));	
					
					String descTipiLegal = rs.getString("DESC_TIPI_LEGAL");
					if(!StringUtil.isEmpty(descTipiLegal)){
						mapDesTipiLegal.put(descTipiLegal, "1");	
					}
					
					if(valorAnterior != obligacionId)
					{						
						obligacionesDTO.setMostrarItem(String.valueOf(1));
						obligacionesDTO.setMostrarObligacionNormativa(String.valueOf(1));
						obligacionesDTO.setMostrarBaseLegal(String.valueOf(1));
						obligacionesDTO.setMostrarCriticidad(String.valueOf(1));
						/* OSINE_SFS-600 - REQF-0015 - Inicio */
						obligacionesDTO.setMostrarAdjunto(String.valueOf(1));
						/* OSINE_SFS-600 - REQF-0015 - Fin */
						
						obligacionesDTO.setMostrarTipificacion(String.valueOf(1));
						obligacionesDTO.setMostrarSancion(String.valueOf(1));						
						
						obligacionesDTO.setMostrarIncumplimiento(String.valueOf(1));
						obligacionesDTO.setMostrarsancionEspecifica(String.valueOf(1));
						obligacionesDTO.setMostrarBaseLegalCriterioEspecifico(String.valueOf(1));
						correlativo++;								
					}
					else
					{
						obligacionesDTO.setMostrarItem(String.valueOf(0));
						obligacionesDTO.setMostrarObligacionNormativa(String.valueOf(0));
						obligacionesDTO.setMostrarBaseLegal(String.valueOf(0));
						obligacionesDTO.setMostrarCriticidad(String.valueOf(0));
						/* OSINE_SFS-600 - REQF-0015 - Inicio */
						obligacionesDTO.setMostrarAdjunto(String.valueOf(1));
						/* OSINE_SFS-600 - REQF-0015 - Fin */
						
						if( valorAnteriorTipificacion != tipificacionId)
						{							
							obligacionesDTO.setMostrarTipificacion(String.valueOf(1));		
							obligacionesDTO.setMostrarSancion(String.valueOf(1));
																																
						}
						else
						{
							
								obligacionesDTO.setMostrarTipificacion(String.valueOf(0));
								obligacionesDTO.setMostrarSancion(String.valueOf(0));
													
						}
						
						if(valorAnteriorCriterio!=criterioId)
						{
							obligacionesDTO.setMostrarIncumplimiento(String.valueOf(1));																
							obligacionesDTO.setMostrarsancionEspecifica(String.valueOf(1));																		
							obligacionesDTO.setMostrarBaseLegalCriterioEspecifico(String.valueOf(1));
							
						}
						else
						{
							if(cantidadIterarCriticidad!=0)
							{
								obligacionesDTO.setMostrarIncumplimiento(String.valueOf(0));																
								obligacionesDTO.setMostrarsancionEspecifica(String.valueOf(0));																		
								obligacionesDTO.setMostrarBaseLegalCriterioEspecifico(String.valueOf(0));
							}
							else
							{
								obligacionesDTO.setMostrarIncumplimiento(String.valueOf(1));																
								obligacionesDTO.setMostrarsancionEspecifica(String.valueOf(1));																		
								obligacionesDTO.setMostrarBaseLegalCriterioEspecifico(String.valueOf(1));
							}
						}
						
					}																	
						
					valorAnterior=obligacionId;
					valorAnteriorTipificacion=tipificacionId;
					valorAnteriorCriterio=criterioId;
					
					obligacionesDTO.setItem(String.valueOf(correlativo));	
													
					listaConsultaWebObligaciones.add(obligacionesDTO);
					
			}
			
			List<String> listaDescTipiLegal = new ArrayList<String>(mapDesTipiLegal.keySet());
			
			Object[] data = new Object[2];
			data[0] = listaConsultaWebObligaciones;
			data[1] = listaDescTipiLegal;
			return data;	
			
		}catch(RuntimeException e){
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
			if(rs != null){
				try {
					rs.close();
					if(callableStatement !=null){
						callableStatement.close();
					}
					if(con!=null){
						con.close();
					}
				} catch (SQLException e) {}
			}
			
			
			
			
		}
		return null;
	}
	
	
	/**
	 * Registra las sugerencias
	 */
	@Override
	@Transactional
	public boolean registrarSugerencia(SugerenciaDTO sugerenciaDTO) {

		try {
			PghConsultaSugerencias consultaSugerencias = new PghConsultaSugerencias();
			consultaSugerencias
					.setDescSugerencia(sugerenciaDTO.getSugerencia());
			consultaSugerencias.setNombreCompleto(sugerenciaDTO
					.getNombreCompleto());
			consultaSugerencias.setCorreo(sugerenciaDTO.getEmail());
			consultaSugerencias.setTelefono(sugerenciaDTO.getTelefono());
			consultaSugerencias.setEstado(Constantes.ACTIVO);
			consultaSugerencias.setUsuarioCreacion(StringUtil.nullToBlank(sugerenciaDTO.getLogin()).equals("") ? "PUBLICO" : sugerenciaDTO.getLogin());
			consultaSugerencias.setTerminalCreacion(Utiles.obtenerIP());
			consultaSugerencias.setFechaCreacion(new Date());
			consultaSugerencias.setTipoConsulta(sugerenciaDTO
					.getIdTipoConsulta());
			crudDAO.create(consultaSugerencias);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * Registra el contador de visitas
	 */
	@Override
	@Transactional
	public void registrarContador(Long idTipoVisita, Long contador) {
		try {
			PghContadorVisita contadorVisita = consultarContador(idTipoVisita);
			
			if(contadorVisita == null){
				contadorVisita = new PghContadorVisita();
				contadorVisita.setCntVisitas(contador);
				contadorVisita.setEstado(Constantes.ACTIVO);
				contadorVisita.setUsuarioCreacion("USUA01");
				contadorVisita.setTerminalCreacion(Utiles.obtenerIP());
				contadorVisita.setFechaCreacion(new Date());
				contadorVisita.setTipoConsulta(idTipoVisita);
				crudDAO.create(contadorVisita);
			}
			else{
				contadorVisita.setCntVisitas(contador);
				contadorVisita.setUsuarioActualizacion("USUA01");
				contadorVisita.setTerminalActualizacion(Utiles.obtenerIP());
				contadorVisita.setFechaActualizacion(new Date());
				crudDAO.update(contadorVisita);
			}
		} catch (Exception e) {
			log.error("Se produjo un error al registrar contador");
		}
	}

	/**
	 * Consulta el contador actual
	 */
	@Override
	public PghContadorVisita consultarContador(Long idTipoVisita) {
		Query query = crudDAO
				.getEm()
				.createQuery(
						"Select b from PghContadorVisita b where b.estado = '1' and b.tipoConsulta = :pTipoVisita ");
		query.setParameter("pTipoVisita", idTipoVisita);
		List<PghContadorVisita> lstContador = query.getResultList();
		
		if (lstContador == null || lstContador.size() == 0) {
			return null;
		} else {
			return ((PghContadorVisita) (lstContador.get(0)));
		}
	}

	/**
	 * Obtiene el tipo de usuario que se esta logeando
	 */
	@Override
	public Map obtenerTipoUsuario(String login) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select u.codigo, u.login, u.nombre, upper(uuo.uuo_tipusu) ");
		jpql.append(" from USUA_UNOP_UUO UUO, SEG_USUARIO u ");
		jpql.append(" where uuo.uuo_usuaid = u.codigo ");
		jpql.append(" and u.estado = 'A' ");
		jpql.append(" and upper(u.login) = upper(:pUsuario) ");

		Query query = crudDAO.getEm().createNativeQuery(jpql.toString());
		query.setParameter("pUsuario", login);

		List<Object[]> lstDatos = query.getResultList();

		Map mapDatos = new HashMap();
		mapDatos.put(Constantes.MAP_CODIGO,
				StringUtil.nullToBlank((lstDatos.get(0))[0]));
		mapDatos.put(Constantes.MAP_LOGIN,
				StringUtil.nullToBlank((lstDatos.get(0))[1]));
		mapDatos.put(Constantes.MAP_NOMBRE,
				StringUtil.nullToBlank((lstDatos.get(0))[2]));
		mapDatos.put(Constantes.MAP_TIPO_USU,
				StringUtil.nullToBlank((lstDatos.get(0))[3]));

		return mapDatos;
	}

	/**
	 * Obtiene la actividad y rubro del usuario logeado
	 */
	@Override
	public Map obtenerActividadYRubro(String login) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select sa.CODIGO,seg.nombre,sa.CODIGO AS CODACTIVIDAD ");
		jpql.append(" FROM SFH_UNDDES_ACTVDDES SUA, SFH_ACTVDDES SA, SFH_UNDDES_OPRTVAS  SUO, ");
		jpql.append(" SFH_CDGOS_DGH DGH, USUA_UNOP_UUO UUO, SEG_USUARIO  SEG ");
		jpql.append(" WHERE SUA.ACTIVI_ID = SA.ID ");
		jpql.append(" AND SUA.UNIOPE_ID = SUO.ID ");
		jpql.append(" AND SUA.ID = DGH.UNIACT_ID ");
		jpql.append(" AND SUA.ESTADO = 'HA' ");
		jpql.append(" AND DGH.ESTADO = 'RV' ");
		jpql.append(" and seg.codigo=uuo.uuo_usuaid ");
		jpql.append(" and suo.ID = uuo.uniope_id ");
		jpql.append(" and upper(seg.login) = upper(:pUsuario) ");

		Query query = crudDAO.getEm().createNativeQuery(jpql.toString());
		query.setParameter("pUsuario", login);

		List<Object[]> lstDatos = query.getResultList();

		Map mapDatos = new HashMap();
		mapDatos.put(Constantes.MAP_CODIGO,
				StringUtil.nullToBlank((lstDatos.get(0))[0]));
		mapDatos.put(Constantes.MAP_NOMBRE,
				StringUtil.nullToBlank((lstDatos.get(0))[1]));
		mapDatos.put(Constantes.MAP_CODACTIVIDAD,
				StringUtil.nullToBlank((lstDatos.get(0))[2]));

		StringBuilder jpql2 = new StringBuilder();
		jpql2.append(" select t.id_actividad, t.id_actividad_padre from MDI_ACTIVIDAD t where t.codigo = :pCodigoActividad ");
		Query query2 = crudDAO.getEm().createNativeQuery(jpql2.toString());
		query2.setParameter("pCodigoActividad",
				mapDatos.get(Constantes.MAP_CODACTIVIDAD));
		List<Object[]> lstDatos2 = query2.getResultList();
		mapDatos.put(Constantes.MAP_ID_RUBRO,
				StringUtil.nullToBlank((lstDatos2.get(0))[0]));
		mapDatos.put(Constantes.MAP_ID_ACTIVIDAD,
				StringUtil.nullToBlank((lstDatos2.get(0))[1]));

		return mapDatos;
	}
	
	@Override
	public AutoayudaDTO obtenerAutoayuda(Long idAutoayuda) {
		// TODO Auto-generated method stub
		PghAutoayuda objPghAutoayuda = crudDAO.find(idAutoayuda, PghAutoayuda.class);
        AutoayudaDTO autoayudaDTO =AutoayudaBuilder.getAutoayuda(objPghAutoayuda); 
		return autoayudaDTO;
	}

	@Override
	public List<MaestroColumnaDTO> listarClasificacionFiltro(Long idActividad,Long idObligacionTipo) {
		// TODO Auto-generated method stub
		List<MaestroColumnaDTO> listado=null;
		Query query;
		try{
			StringBuilder jpql = new StringBuilder();			
        	jpql.append("select distinct tom.ID_TEMA_OBLIGACION, mc.DESCRIPCION from pgh_obligacion o " );
        	jpql.append("inner join pgh_conf_obligacion cnf on o.id_obligacion=cnf.id_obligacion and cnf.estado = 1 " );
        	jpql.append("inner join PGH_TEMA_OBLIGACION_MAESTRO tom on o.ID_OBLIGACION = tom.ID_OBLIGACION and tom.estado = 1 " );
        	jpql.append("inner join MDI_MAESTRO_COLUMNA mc on tom.ID_TEMA_OBLIGACION = mc.ID_MAESTRO_COLUMNA and mc.ESTADO=1 " );
        	jpql.append("where  o.estado = 1 " );
        	if(idActividad!=null){
        		jpql.append("and cnf.ID_ACTIVIDAD =:idActividad " );	
        	}
        	if(idObligacionTipo!=null && idObligacionTipo!=0){
        		jpql.append("and cnf.ID_OBLIGACION_TIPO =:idObligacionTipo " );
        	}        	
        	query = crudDAO.getEm().createNativeQuery(jpql.toString());
        	if(idActividad!=null){
        		query.setParameter("idActividad",idActividad); 
        	}
        	if(idObligacionTipo!=null && idObligacionTipo!=0){
        		query.setParameter("idObligacionTipo",idObligacionTipo); 
        	}
        	List<Object[]> listaDomain = query.getResultList();
        	if(!listaDomain.isEmpty()){
            	listado= MaestroColumnaBuilder.toListClasificacion(listaDomain);
        	}

		}catch(Exception e){
			log.error("Error Listar clasificaciones: " + e.getMessage());
			e.printStackTrace();
		}		
    	return listado;
	}
	
}
