package gob.osinergmin.consulta.service.impl;

import gob.osinergmin.alfresco.rest.util.AlfrescoInvoker;
import gob.osinergmin.consulta.domain.PghContadorVisita;
import gob.osinergmin.consulta.dto.AutoayudaDTO;
import gob.osinergmin.consulta.dto.DocumentoAdjuntoDTO;
import gob.osinergmin.consulta.dto.MaestroColumnaDTO;
import gob.osinergmin.consulta.dto.ObligacionTipoDTO;
import gob.osinergmin.consulta.dto.ObligacionesDTO;
import gob.osinergmin.consulta.dto.ReporteObligacionesDTO;
import gob.osinergmin.consulta.dto.SugerenciaDTO;
import gob.osinergmin.consulta.service.ObligacionesService;
import gob.osinergmin.consulta.service.dao.ConsultaRequisitoDAO;
import gob.osinergmin.consulta.service.dao.MaestroColumnaDAO;
import gob.osinergmin.consulta.service.dao.ObligacionesDAO;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("ObligacionesService")
public class ObligacionesServiceImpl implements ObligacionesService {
	
	private static final Logger log = LoggerFactory
			.getLogger(ObligacionesServiceImpl.class);
	@Inject
	private ObligacionesDAO obligacionesDAO;
	@Inject
	private MaestroColumnaDAO maestroColumnaDAO;
	/* OSINE_SFS-600 - REQF-0015 - Inicio */
	@Inject
    private ConsultaRequisitoDAO requisitoDAO;
	@Value("${alfresco.host}")
    private String alfrescoHost;
    @Value("${alfresco.download.dir}")
    private String alfrescoDownload;
    @Value("${alfresco.user}")
    private String alfrescoUser;
    @Value("${alfresco.base}")
    private String alfrescoBase;
    @Value("${alfresco.space.dir.obligaciones}")
    private String alfrescoRuta;
	/* OSINE_SFS-600 - REQF-0015 - Fin */

	@Override
	public List<ObligacionTipoDTO> listarObligacionesTipo(Long idActividad) {
		log.info("listarObligacionesTipo - ObligacionesServiceImpl");
		return obligacionesDAO.listarObligacionesTipo(idActividad);
	}

	@Override
	public List<MaestroColumnaDTO> listarClasificacion(String dominio,
			String aplicacion) {
		log.info("listarClasificacion - ObligacionesServiceImpl");
		return maestroColumnaDAO.findMaestroColumna(dominio, aplicacion);
	}

	@Override
	public List<ObligacionesDTO> listarObligaciones(Long rubro,
			Long obligacionTipo, Long clasificacion, Long criticidad) {

		return obligacionesDAO.listarObligaciones(rubro, obligacionTipo,
				clasificacion, criticidad);
	}
	/*SRAMOS 08112015*/
	@Override
	public Object[] consultaReporteWeb(Long rubro, 	//lchancayauri 
			Long obligacionTipo, Long clasificacion, Long criticidad) {	//lchancayauri
	//public List<ReporteObligacionesDTO> consultaReporteWeb(Long rubro,
	//		Long obligacionTipo, Long clasificacion, Long criticidad) {
		return obligacionesDAO.consultaReporteWebObligaciones(rubro, obligacionTipo,
				clasificacion, criticidad);
	}
	@Override
	public boolean registrarSugerencia(SugerenciaDTO sugerenciaDTO) {
		return obligacionesDAO.registrarSugerencia(sugerenciaDTO);
	}

	@Override
	public void registrarContador(Long idTipoVisita, Long contador) {
		obligacionesDAO.registrarContador(idTipoVisita, contador);
	}

	@Override
	public PghContadorVisita consultarContador(Long idTipoVisita) {
		return obligacionesDAO.consultarContador(idTipoVisita);
	}

	@Override
	public Map obtenerTipoUsuario(String login) {
		return obligacionesDAO.obtenerTipoUsuario(login);
	}

	@Override
	public Map obtenerActividadYRubro(String login) {
		return obligacionesDAO.obtenerActividadYRubro(login);
	}
	@Override
	public AutoayudaDTO obtenerAutoayuda(Long idAutoayuda) {
		// TODO Auto-generated method stub
		return obligacionesDAO.obtenerAutoayuda(idAutoayuda);
	}

	@Override
	public List<MaestroColumnaDTO> listarClasificacionFiltro(Long idActividad,Long idObligacionTipo) {
		// TODO Auto-generated method stub
		return obligacionesDAO.listarClasificacionFiltro(idActividad,idObligacionTipo);
	}
	
	/* OSINE_SFS-600 - REQF-0015 - Inicio */
	@Override
    public Map<String, Object> descargarDatosAlfresco(Long idDocumentoAdjunto) {
        Map<String, Object> data = new HashMap<String, Object>();
        InputStream in = null;
        try {
            DocumentoAdjuntoDTO documentoAdjuntoDTO = requisitoDAO.obtenerDocumentoAdjunto(idDocumentoAdjunto);
            in = AlfrescoInvoker.download(alfrescoHost + alfrescoDownload, alfrescoUser, alfrescoBase, null, null, null, alfrescoRuta, documentoAdjuntoDTO.getRutaAlfresco());
            data.put("inputStream", in);
            data.put("nombreArchivo", documentoAdjuntoDTO.getNombre());
        } catch (Exception ex) {
//            ex.printStackTrace();
            log.error("error", ex.getMessage());
        }
        return data;
    }
	/* OSINE_SFS-600 - REQF-0015 - Fin */

}
