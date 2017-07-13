/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.controller;

import gob.osinergmin.consulta.service.CaptchaServiceSingleton;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.octo.captcha.service.CaptchaServiceException;

/**
 *
 * @author lchancayauri
 */
@Controller
public class CaptchaController {

    @RequestMapping(value = "/captcha")
    public void captchaImagen(HttpSession sesion, HttpServletRequest request,
	    HttpServletResponse response, Model model) throws Exception {

	byte[] captchaChallengeAsJpeg = null;

	ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
	try {
	    String captchaId = request.getSession().getId();
	    BufferedImage challenge = CaptchaServiceSingleton.getInstance()
		    .getImageChallengeForID(captchaId, request.getLocale());

            ImageIO.write(challenge, "jpeg", jpegOutputStream);
	} catch ( IllegalArgumentException e ) {
	    response.sendError(HttpServletResponse.SC_NOT_FOUND);
	} catch ( CaptchaServiceException e ) {
	    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}

	captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setContentType("image/jpeg");
	ServletOutputStream responseOutputStream = response.getOutputStream();
	responseOutputStream.write(captchaChallengeAsJpeg);
	responseOutputStream.flush();
	responseOutputStream.close();
    }
    
}
