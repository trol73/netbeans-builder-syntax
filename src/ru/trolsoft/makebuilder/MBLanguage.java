/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.trolsoft.makebuilder;

import org.netbeans.api.lexer.Language;
import org.netbeans.modules.csl.spi.DefaultLanguageConfig;
import org.netbeans.modules.csl.spi.LanguageRegistration;
import ru.trolsoft.makebuilder.lexer.MBTokenId;

/**
 *
 * @author trol
 */
@LanguageRegistration(mimeType = "text/x-builder")
public class MBLanguage extends DefaultLanguageConfig {

	@Override
	public Language getLexerLanguage() {
		return MBTokenId.getLanguage();
	}

	@Override
	public String getDisplayName() {
		return "make.builder";
	}

}
