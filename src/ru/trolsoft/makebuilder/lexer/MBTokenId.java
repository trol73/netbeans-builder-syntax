/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.trolsoft.makebuilder.lexer;

import org.netbeans.api.lexer.Language;
import org.netbeans.api.lexer.TokenId;

/**
 *
 * @author trol
 */
public enum MBTokenId implements TokenId {

    KEYWORD ("keyword"), // NOI18N
    OPERATOR ("operator"), // NOI18N
    WHITESPACE ("whitespace"), // NOI18N
    NUMBER ("number"), // NOI18N
	 NUMBER_HEX ("number_hex"), // NOI18N
    STRING ("string"), // NOI18N
    IDENTIFIER ("identifier"), // NOI18N
    COMMENT ("comment"), // NOI18N
	 COMMENT_BLOCK ("comment_block"), // NOI18N
	 FUNCTION ("function"), // NOI18N
    ERROR ("error"); // NOI18N

	private final String name;

    MBTokenId(String name) {
        this.name = name;
    }

    @Override
    public String primaryCategory() {
        return name;
    }

	 public static Language<MBTokenId> getLanguage() {
	   return new MBLanguageHierarchy().language();
	}

}
