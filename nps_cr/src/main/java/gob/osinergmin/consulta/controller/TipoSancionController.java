package gob.osinergmin.consulta.controller;

/**
*
* @author l_garcia_r
*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gob.osinergmin.consulta.domain.ui.TipoSancionFilter;
import gob.osinergmin.consulta.dto.TipoSancionDTO;
import gob.osinergmin.consulta.service.MaestroColumnaService;
import gob.osinergmin.consulta.service.TipoSancionServiceNeg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/consultaObligaciones")
public class TipoSancionController {
	
	@Inject
	private TipoSancionServiceNeg tipoSancionServiceNeg;
	
	@Inject
	private MaestroColumnaService maestroColumnaService;
		
	private static final Logger LOG = LoggerFactory.getLogger(TipoSancionController.class);
	//Busca_Tipo_Sanciones: PGH_TIPO_SANCION -- l_garcia_r
    @RequestMapping(value="/findTipoSancion",method= RequestMethod.POST)
    public @ResponseBody 
    Map<String,Object> findTipoSancion(TipoSancionFilter filtro, HttpSession sesion, HttpServletRequest request) {
    	LOG.info("Funcion: Find TipoSancion -- Controller -- Metodo-> findTipoSancion");    	
        Map<String,Object> listaResultado=new HashMap<String,Object>();     
        try{
        	List<TipoSancionDTO> listado;
        	listado=tipoSancionServiceNeg.findTipoSancion(filtro);
        	
        	List<TipoSancionDTO> listaTipoSancion = new ArrayList<TipoSancionDTO>();
        	listaTipoSancion = listado;            
            //Mostrar_de_la_lista:Tipo_Division         
            listaResultado.put("descTipSanc1", listaTipoSancion.get(0).getAbreviaturaDescripcion());
            listaResultado.put("descTipSanc2", listaTipoSancion.get(1).getAbreviaturaDescripcion());
            listaResultado.put("descTipSanc3", listaTipoSancion.get(2).getAbreviaturaDescripcion());
            listaResultado.put("descTipSanc4", listaTipoSancion.get(3).getAbreviaturaDescripcion());
            listaResultado.put("descTipSanc5", listaTipoSancion.get(4).getAbreviaturaDescripcion());
            listaResultado.put("descTipSanc6", listaTipoSancion.get(5).getAbreviaturaDescripcion());
            listaResultado.put("descTipSanc7", listaTipoSancion.get(6).getAbreviaturaDescripcion()); 
            listaResultado.put("descTipSanc8", listaTipoSancion.get(7).getAbreviaturaDescripcion()); 
            //listaResultado.put("lista", listaTipoSancion); 
            
        }catch(Exception ex){
        	LOG.error("error controller",ex);
        }        
        return listaResultado;
    }    
	

}
