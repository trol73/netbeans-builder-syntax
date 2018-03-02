/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.trolsoft.makebuilder.lexer;

import java.util.logging.Logger;
import org.netbeans.api.lexer.Token;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerInput;
import org.netbeans.spi.lexer.LexerRestartInfo;

/**
 *
 * @author trol
 */
public class MBLexer implements Lexer<MBTokenId> {

	private static enum State {
		DEFAULT,
		NEW_LINE,
		BLOCK_COMMENT
	}


	private static final String OPERATORS = "+-=!,.<>;()[]{}|&*/^%:";

   private final LexerRestartInfo<MBTokenId> info;
	private State state;

	public MBLexer(LexerRestartInfo<MBTokenId> info) {
        this.info = info;
		  this.state = info.state() == null ? State.DEFAULT : State.values()[(Integer) info.state()];
   }

	@Override
	public Token<MBTokenId> nextToken() {
		LexerInput input = info.input();
		// TODO hex bin oct
		int ch = input.read();
		if (ch == LexerInput.EOF) {
			return null;
		} else if (isWhitespace(ch)) {
			if (ch == '\r' || ch == '\n') {
				if (state != State.BLOCK_COMMENT) {
					state = State.NEW_LINE;
				}
			}
			do {
				ch = input.read();
				if (ch == '\r' || ch == '\n') {
				if (state != State.BLOCK_COMMENT) {
					state = State.NEW_LINE;
				}
			}
			} while (isWhitespace(ch));
			input.backup(1);
			return create(MBTokenId.WHITESPACE);
		} else if (ch == '#') {
			do {
				ch = input.read();
			} while (ch != '\n' && ch != '\r' && ch != LexerInput.EOF);
			input.backup(1);
			return create(MBTokenId.COMMENT);
		} else if (isOperator(ch)) {
			return create(MBTokenId.OPERATOR);
		} else if (isDigit(ch)) {
			return checkNumber(input, ch);
		} else if (ch == '"') {
			return checkString(input, ch);
		} else if (ch == '\'') {
			return checkString(input, ch);
		} else if (isLetter(ch)) {
			do {
				ch = input.read();
			} while (isLetter(ch) || isDigit(ch));
			input.backup(1);
			 String str = input.readText().toString();
			 if (Keywords.isKeyword(str)) {
				 return create(MBTokenId.KEYWORD);
			 } else if (Keywords.isFunction(str)) {
				 return create(MBTokenId.FUNCTION);
			 }
			 return create(MBTokenId.IDENTIFIER);
		}
		 return create(MBTokenId.ERROR);
    }


	private Token<MBTokenId> checkString(LexerInput input, int q) {
		int ch = 0;
		do {
			boolean slashed = ch == '\\';
			ch = input.read();
			if (slashed) {
				ch = ' ';
			}
		} while (ch != '\r' && ch != '\n' && ch != LexerInput.EOF && ch != q);
		if (ch == q) {
			return create(MBTokenId.STRING);
		} else {
			input.backup(1);
			return create(MBTokenId.ERROR);
		}
	}

	private Token<MBTokenId> checkNumber(LexerInput input, int ch) {
		if (ch == '0') {
			ch = input.read();
			int base;
			if (ch == 'x' || ch == 'X') {
				base = 16;
			} else if (ch == 'b' || ch == 'B') {
				base = 2;
			} else if (isDigit(ch)) {
				base = 8;
			} else if (isLetter(ch)) {
				return create(MBTokenId.ERROR);
			} else {
				input.backup(1);
				return create(MBTokenId.NUMBER);
			}
			do {
				ch = input.read();
			} while (isNumberChar(ch, base));
			input.backup(1);
			switch (base) {
				case 2:
					return create(MBTokenId.NUMBER);
				case 8:
					return create(MBTokenId.NUMBER);
				default:
					return create(MBTokenId.NUMBER_HEX);
			}
		}
		boolean e = false;
		do {
				ch = input.read();
				if (ch == 'e' || ch == 'E') {
					if (e) {
						return create(MBTokenId.ERROR);
					}
					e = true;
				}
		} while (isDigit(ch) || ch == 'e' || ch == 'E');
		input.backup(1);
		return create(MBTokenId.NUMBER);
	}

	private static boolean isNumberChar(int ch, int base) {
		switch (base) {
			case 2:
				return ch == '0' || ch == '1';
			case 8:
				return ch >= '0' && ch <= '7';
			case 16:
				return isHexDigit(ch);
		}
		return false;
	}


	 private Token<MBTokenId> create(MBTokenId tokenId) {
		 if (state == State.BLOCK_COMMENT) {
			 return info.tokenFactory().createToken(MBTokenId.COMMENT_BLOCK);
		 } else if (tokenId != MBTokenId.WHITESPACE) {
			state = State.DEFAULT;
		 }
//if (tokenId == MBTokenId.ERROR) {
//	log.info("??? " + info.input().readText());
//}
		 return info.tokenFactory().createToken(tokenId);
	 }

    @Override
    public Object state() {
         return state == State.DEFAULT ? null : state.ordinal();
    }

    @Override
    public void release() {
    }

	private static boolean isOperator(int ch) {
		return OPERATORS.indexOf(ch) >= 0;
	}

	private static boolean isDigit(int ch) {
		return ch >= '0' && ch <= '9';
	}

	private static boolean isHexDigit(int ch) {
		return isDigit(ch) || (ch >= 'a' && ch <= 'f') || (ch >= 'A' && ch <= 'F');
	}

	private static boolean isLetter(int ch) {
		return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == '_';
	}

	private static boolean isWhitespace(int ch) {
		return ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n';
	}

}
