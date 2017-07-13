package gob.osinergmin.consulta.controller;
import java.util.HashMap;
import java.util.Map;

import gob.osinergmin.consulta.dto.AutoayudaDTO;
import gob.osinergmin.consulta.dto.UsuarioDTO;
import gob.osinergmin.consulta.service.ObligacionesService;
import gob.osinergmin.consulta.util.Constantes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/consultaObligaciones")
public class AutoayudaController {
	
	@Inject
	ObligacionesService obligacionesService;
	
	@RequestMapping(value = "/cargar")
	public @ResponseBody
	Map<String, Object> consulta(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session, String descripcion,String ayuda) {
        String saludo=descripcion;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
		AutoayudaDTO autoayudaDTO = obligacionesService.obtenerAutoayuda(Long.parseLong(ayuda));
		System.out.println(autoayudaDTO.getDescripcion());
		map.put("descripcion", autoayudaDTO.getDescripcion());
		map.put("ok", 1);
        } catch (Exception e) {
        	map.put("ok", 0);
		}
		return map;
		
	};

}
