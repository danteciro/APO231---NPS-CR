package gob.osinergmin.consulta.controller;

/**
*
* @author l_garcia_r
*/

import gob.osinergmin.consulta.domain.ui.MaestroColumnaFilter;
import gob.osinergmin.consulta.domain.ui.TipoSancionFilter;
import gob.osinergmin.consulta.dto.MaestroColumnaDTO;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/consultaObligaciones")
public class MaestroColumnaController {

	@Inject
	private MaestroColumnaService maestroColumnaService;
		
	private static final Logger LOG = LoggerFactory.getLogger(MaestroColumnaController.class);
	//Busca_Valor_UIT: MDI_MAESTRO_COLUMNA -- l_garcia_r
    @RequestMapping(value="/findValorUit",method= RequestMethod.POST)
    public @ResponseBody 
    Map<String,Object> findValorUit(MaestroColumnaFilter filtro, HttpSession sesion, HttpServletRequest request) {
    	LOG.info("Funcion: Find ValorUit -- Controller -- Metodo-> findValorUit");    	
        Map<String,Object> listaResultado=new HashMap<String,Object>();     
        try{
        	 List<MaestroColumnaDTO> valorUit;
             valorUit=maestroColumnaService.findValorUit(filtro);
             
             List<MaestroColumnaDTO> valorUitRef = new ArrayList<MaestroColumnaDTO>();
             valorUitRef = valorUit;   
             //Mostrar_de_la_lista:ValorUIT        
             listaResultado.put("valorUIT", valorUitRef.get(0).getDescripcion());
                    	
        }catch(Exception ex){
        	LOG.error("error controller",ex);
        }        
        return listaResultado;
    }    
	

}
