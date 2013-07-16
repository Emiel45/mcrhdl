package net.mcrhdl.compiler.tokenizer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

public class Tokenizer {
    public static HashMap<String, TokenType> KEYWORDS  = Maps.newHashMap();
    public static HashMap<String, TokenType> OPERATORS = Maps.newHashMap();

    private int index;
    private char[] code;

    private EnumSet<TokenType> expectedTokens;

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

    public List<Token> tokenize() throws IOException {
        List<Token> tokens = Lists.newArrayList();

        while(index < code.length) {
            //System.out.println(index + ", '" + getChar() + "'");

            skipWhitespace();

            while(getChar() == '/') {
                nextChar();
                if(getChar() == '/') {
                    expect(EnumSet.of(TokenType.NEWLINE));
                    while(nextChar() != '\n');
                } else if(getChar() == '*') {
                    expect(EnumSet.of(TokenType.LMULTI_LINE_COMMENT_END));
                    nextChar();
                    while(!(nextChar() == '*' && nextChar() == '/'));
                    nextChar();
                } else {
                    prevChar();
                    break;
                }

                skipWhitespace();
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
                tokens.add(new Token(type, tokenBuilder.toString()));
            }

            tokenBuilder = new StringBuilder();

            // Add literals or idents
            if(getChar() == '"') {
                expect(EnumSet.of(TokenType.STRING_QUOTE));
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

                expect(EnumSet.of(TokenType.WHITESPACE));
                if(!charIsWhitespace()) {
                    throw new UnexpectedTokenException();
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
                throw new NotImplementedException();

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

    public void expect(EnumSet<TokenType> tokens) {
        this.expectedTokens = tokens;
    }

    public EnumSet<TokenType> getExpectedTokens() {
        return expectedTokens;
    }

    private void skipWhitespace() throws EOSReachedException {
        while(charIsWhitespace()) {
              nextChar();
        }
    }

    private char getChar() throws EOSReachedException {
        if(index < code.length) {
            return code[index];
        }

        throw new EOSReachedException();
    }

    private char nextChar() throws EOSReachedException {
        index++;
        return getChar();
    }

    private char prevChar() throws EOSReachedException {
        index--;
        return getChar();
    }

    private boolean charIsWhitespace() throws EOSReachedException {
        char c = getChar();
        return c == ' ' || c == '\n' || c == '\t' || c == '\r';
    }

    private boolean charIsAlpha() throws EOSReachedException {
        char c = getChar();
        return (c >= 65 && c <= 90) || (c >= 97 && c <= 122);
    }

    private boolean charIsDigit() throws EOSReachedException {
        char c = getChar();
        return c >= 48 && c <= 57;
    }
}
