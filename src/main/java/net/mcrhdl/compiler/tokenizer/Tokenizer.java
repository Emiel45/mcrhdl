package net.mcrhdl.compiler.tokenizer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.List;

public class Tokenizer {
    private int index;
    private char[] code;

    public static HashMap<String, TokenType> KEYWORDS  = Maps.newHashMap();
    public static HashMap<String, TokenType> OPERATORS = Maps.newHashMap();

    public Tokenizer(char[] code) {
        this.code = code;
    }

    public boolean allCharactersInAsciiRange(String data, int min, int max)
    {
        for(char c : data.toCharArray())
        {
            if(!(c >= min && c <= max))
                return false;
        }
        return true;
    }

    public List<Token> tokenize() {
        List<Token> tokens = Lists.newArrayList();

        while(index < code.length) {
            //System.out.println(index + ", '" + getChar() + "'");

            while(charIsWhitespace()) {
                  nextChar();
            }

            StringBuilder tokenBuilder = new StringBuilder();

            tokenBuilder.append(getChar());
            nextChar();

            // Search for operators
            while(OPERATORS.containsKey(tokenBuilder.toString())) {
                //System.out.println("Matching for operator.");
                tokenBuilder.append(getChar());
                nextChar();
            }

            prevChar();
            tokenBuilder = new StringBuilder(tokenBuilder.substring(0, tokenBuilder.length() - 1));

            //System.out.println("'" + tokenBuilder.toString() + "'");

            // Add the operator token
            if(OPERATORS.containsKey(tokenBuilder.toString())) {
                TokenType type = OPERATORS.get(tokenBuilder.toString());

                if(type == TokenType.LINE_COMMENT) {
                    //System.out.println("COMMENT");
                    while(getChar() != '\n' && getChar() != '\r') {
                        nextChar();
                    }
                } else if(type == TokenType.LMULTI_LINE_COMMENT) {
                    // TODO: Get off my ass and work
                } else {
                    tokens.add(new Token(type, tokenBuilder.toString()));
                }
            }

            tokenBuilder = new StringBuilder();

            // Add literals or idents
            if(getChar() == '"') {
                nextChar();
                while(getChar() != '"') {
                    tokenBuilder.append(getChar());
                    nextChar();
                }
                tokens.add(new Token(TokenType.STRING, tokenBuilder.toString()));
                nextChar();
            } else if(charIsDigit()) {
                // TODO: Proper number parsing with decimal places (is this needed)
                while(charIsDigit()) {
                    tokenBuilder.append(getChar());
                    nextChar();
                }
                tokens.add(new Token(TokenType.NUMBER, tokenBuilder.toString()));
            } else if(charIsAlpha()) {
                tokenBuilder.append(getChar());
                nextChar();
                while(charIsAlpha() || charIsDigit()) {
                    tokenBuilder.append(getChar());
                    nextChar();
                }
                tokens.add(new Token(TokenType.IDENTIFIER, tokenBuilder.toString()));
            } else if(getChar() == '#') {
                throw new RuntimeException("Macros not yet implemented.");

                /* String name = null;
                List<String> argumentTypes = Lists.newArrayList();
                List<String> argumentNames = Lists.newArrayList();

                // Get name of macro
                nextChar();

                if(!charIsAlpha()) {
                    throw new RuntimeException("Macro operator must be followed by an identifier.");
                }

                tokenBuilder.append(getChar());
                while(charIsAlpha() || charIsDigit()) {
                    tokenBuilder.append(getChar());
                    nextChar();
                } */

                // Get arguments

            } else {
                //throw new RuntimeException("Could not tokenize character '" + getChar() + "'");
            }
        }

        return tokens;
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

    private char prevChar() {
        index--;
        return getChar();
    }

    private boolean charIsWhitespace() {
        char c = getChar();
        return c == ' ' || c == '\n' || c == '\t' || c == '\r';
    }

    private boolean charIsAlpha() {
        char c = getChar();
        return (c >= 65 && c <= 90) || (c >= 97 && c <= 122);
    }

    private boolean charIsDigit() {
        char c = getChar();
        return c >= 48 && c <= 57;
    }
}
