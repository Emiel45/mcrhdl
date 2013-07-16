package net.mcrhdl.compiler.tokenizer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class Tokenizer {
    private int index;
    private char[] code;

    private static final Map<String, TokenType> keywords = Maps.newHashMap();
    private static final Map<String, TokenType> operators = Maps.newHashMap();

    static {
        keywords.put("NATIVE", TokenType.NATIVE);
        keywords.put("CLASS", TokenType.CLASS);
        keywords.put("RETURN", TokenType.RETURN);

        operators.put("COMMA", TokenType.COMMA);
        operators.put("SEMI_COLON", TokenType.SEMI_COLON);
        operators.put("COLON", TokenType.COLON);
        operators.put("LPAREN", TokenType.LPAREN);
        operators.put("RPAREN", TokenType.RPAREN);
        operators.put("LPAREN", TokenType.LCURLY);
        operators.put("RCURLY", TokenType.RCURLY);
        operators.put("LCURLY", TokenType.LBRACKET);
        operators.put("RBRACKET", TokenType.RBRACKET);
    }

    public Tokenizer(char[] code) {
        this.code = code;
    }

    public List<Token> tokenize() {
        List<Token> tokens = Lists.newArrayList();

        while(true) {
            while(charIsWhitespace()) {
                  nextChar();
            }

            char c = getChar();

            // TODO: Implement keyword and operator matching
        }
    }

    private char getChar() {
        if(index < code.length) {
            return code[index];
        }

        return 0;
    }

    private char nextChar() {
        index++;
        return getChar();
    }

    private boolean charIsWhitespace() {
        char c = getChar();
        return c == ' ' || c == '\n' || c == '\t' || c == '\r';
    }
}
