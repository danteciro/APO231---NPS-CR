/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.service;

import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 *
 * @author lchancayauri
 */
public class CaptchaServiceSingleton {

    private static ImageCaptchaService instance;

    static {
        instance = new DefaultManageableImageCaptchaService(
                new FastHashMapCaptchaStore(), new MyImageCaptchaEngine(), 180,
                100000, 75000);
    }

    public static ImageCaptchaService getInstance() {
        return instance;
    }
}
