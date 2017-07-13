package gob.osinergmin.consulta.controller;

import gob.osinergmin.consulta.domain.PghContadorVisita;
import gob.osinergmin.consulta.service.MaestroColumnaService;
import gob.osinergmin.consulta.service.ObligacionesService;
import gob.osinergmin.consulta.util.Constantes;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * @author cflorian
 *
 */
public class CustomServletContextListener implements ServletContextListener{
	private static final Logger log = LoggerFactory.getLogger(CustomServletContextListener.class);
	
	@Inject
    private ObligacionesService obligacionesService;
	@Inject
	private MaestroColumnaService maestroColumnaService;
	
	/**
	 * Metodo que se ejecuta apenas se deploya el proyecto
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("contextInitialized - CustomServletContextListener");
		WebApplicationContextUtils
        .getRequiredWebApplicationContext(sce.getServletContext())
        .getAutowireCapableBeanFactory()
        .autowireBean(this);
		
		Long idTipoConsultaObligaciones = maestroColumnaService.obtenerIdMaestroColumna(
				Constantes.PGH_OBLIG_CNT_VISITA,
				Constantes.APLICACION_OBLIGACIONES);
	
		Long idTipoConsultaRequisitos = maestroColumnaService.obtenerIdMaestroColumna(
				Constantes.PGH_OBLIG_CNT_VISITA,
				Constantes.APLICACION_REQUISITOS);
		
		PghContadorVisita contadorObligaciones = obligacionesService.consultarContador(idTipoConsultaObligaciones);
		PghContadorVisita contadorRequisitos = obligacionesService.consultarContador(idTipoConsultaRequisitos);
                log.info("contadorObligaciones == "+contadorObligaciones);
                log.info("contadorRequisitos == "+contadorRequisitos);
                int cntObligaciones = 0;
                int cntRequisitos = 0;
                if(contadorObligaciones != null && contadorObligaciones.getCntVisitas() != null){
                    cntObligaciones = contadorObligaciones.getCntVisitas().intValue();
                }
                if(contadorRequisitos != null && contadorRequisitos.getCntVisitas() != null){
                    cntRequisitos = contadorRequisitos.getCntVisitas().intValue();
                }
		sce.getServletContext().setAttribute(Constantes.P_CONTADOR_OBLIGACIONES, new Long(cntObligaciones));
		sce.getServletContext().setAttribute(Constantes.P_CONTADOR_REQUISITOS, new Long(cntRequisitos));
                
	}

	/**
	 * Metodo que se ejecuta cuando se undeploya el proyecto
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("contextDestroyed - CustomServletContextListener");
		
		Long idTipoConsultaObligaciones = maestroColumnaService.obtenerIdMaestroColumna(
				Constantes.PGH_OBLIG_CNT_VISITA,
				Constantes.APLICACION_OBLIGACIONES);
	
		Long idTipoConsultaRequisitos = maestroColumnaService.obtenerIdMaestroColumna(
				Constantes.PGH_OBLIG_CNT_VISITA,
				Constantes.APLICACION_REQUISITOS);
		
		Long contadorObligaciones = (Long)sce.getServletContext().getAttribute(Constantes.P_CONTADOR_OBLIGACIONES);
		Long contadorRequisitos = (Long)sce.getServletContext().getAttribute(Constantes.P_CONTADOR_REQUISITOS);
		obligacionesService.registrarContador(idTipoConsultaObligaciones, contadorObligaciones);
		obligacionesService.registrarContador(idTipoConsultaRequisitos, contadorRequisitos);
	}

	
	
}
