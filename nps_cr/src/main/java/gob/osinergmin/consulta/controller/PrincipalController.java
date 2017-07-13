/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.controller;

import gob.osinergmin.consulta.util.ConstantesWeb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author lchancayauri
 */
@Controller
@RequestMapping("/consulta/principal")
public class PrincipalController {

    private static final Logger log = LoggerFactory.getLogger(PrincipalController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String inicio() {
        return ConstantesWeb.Navegacion.PAGE_GENERAL_INICIO;
    }
}
