/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.trolsoft.makebuilder.lexer;

import java.util.*;
import org.netbeans.spi.lexer.LanguageHierarchy;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;
/**
 *
 * @author trol
 */
public class MBLanguageHierarchy extends LanguageHierarchy<MBTokenId> {

    @Override
    protected synchronized Collection<MBTokenId> createTokenIds() {
        return EnumSet.allOf(MBTokenId.class);
    }

    @Override
    protected synchronized Lexer<MBTokenId> createLexer(LexerRestartInfo<MBTokenId> info) {
        return new MBLexer(info);
    }

    @Override
    protected String mimeType() {
        return "text/x-builder";
    }

}
