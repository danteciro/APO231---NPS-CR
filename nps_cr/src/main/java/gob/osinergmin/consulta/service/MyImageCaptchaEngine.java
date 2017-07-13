/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.service;

import java.awt.Font;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.FunkyBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

/**
 *
 * @author lchancayauri
 */
public class MyImageCaptchaEngine extends ListImageCaptchaEngine{

    protected void buildInitialFactories() { 
	WordGenerator wgen = new RandomWordGenerator(
		"ABCDEFGHIJKLMNPQRSTUVWXYZ123456789");
	RandomRangeColorGenerator cgen = new RandomRangeColorGenerator(
		new int[] { 0, 100 }, new int[] { 0, 100 },
		new int[] { 0, 100 });

	TextPaster textPaster = new RandomTextPaster(new Integer(5),
		new Integer(5), cgen, true);

	RandomRangeColorGenerator cgenb = new RandomRangeColorGenerator(
		new int[] { 221, 221 }, // red
		new int[] { 231, 231 }, // green
		new int[] { 248, 248 }); // blue
	BackgroundGenerator backgroundGenerator = new FunkyBackgroundGenerator(
		new Integer(150), new Integer(50), cgenb);

	// BackgroundGenerator backgroundGenerator = new
	// FunkyBackgroundGenerator(new Integer(150), new Integer(50));

	Font[] fontsList = new Font[] {
	               new Font("Arial", 0, 9),
	               new Font("Tahoma", 0, 9),
	               new Font("Verdana", 0, 9),
	               new Font("Times New Roman", 0, 9),
	            };

	FontGenerator fontGenerator = new RandomFontGenerator(new Integer(20),
		new Integer(35), fontsList);

	WordToImage wordToImage = new ComposedWordToImage(fontGenerator,
		backgroundGenerator, textPaster);
	this.addFactory(new GimpyFactory(wgen, wordToImage));
    }
    
}
