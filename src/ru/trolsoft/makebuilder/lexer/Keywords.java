/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.trolsoft.makebuilder.lexer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author trol
 */
public class Keywords {
	private static final Set<String> KEYWORDS = set(
			  "class", "finally", "is", "return", "continue", "for", "def", "try", "if",
			  "and", "del", "global", "not", "with", "as", "elif", "or", "yield", "lambda",
			  "assert", "else", "import", "pass", "break", "except", "in", "raise",
			  "from", "nonlocal", "while", "True", "False", "None", "self"
	);

	private static final Set<String> FUNCTIONS = set(
		"abs", "divmod", "input", "open", "staticmethod", "all", "enumerate", "int", "ord", "str",
		"any", "eval", "isinstance", "pow", "sum", "basestring", "execfile", "issubclass", "print",
		"super", "bin", "file", "iter", "property", "tuple", "bool", "filter", "len", "range",
		"type", "bytearray", "float", "list", "raw_input", "unichr", "callable", "format", "locals",
		"reduce", "unicode", "chr", "frozenset", "long", "reload", "vars", "classmethod",
		"getattr", "map", "repr", "xrange", "cmp", "globals", "max", "reversed", "zip",
		"compile", "hasattr", "memoryview", "round", "__import__", "complex", "hash", "min", "set",
		"delattr", "help", "next", "setattr", "dict", "hex", "object", "slice", "dir", "id", "oct",
		"sorted",
	   "error"
	);

	 private static Set<String> set(String ...s) {
		 Set<String> result = new HashSet<>();
		 result.addAll(Arrays.asList(s));
		 return result;
	 }

	 public static boolean isKeyword(String s) {
		 return KEYWORDS.contains(s);
	 }

	 public static boolean isFunction(String s) {
		 return FUNCTIONS.contains(s);
	 }
}
