package net.mcrhdl.compiler.tokenizer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tokenizer {
    private int index;
    private char[] code;

    public static HashMap<String, TokenType> TOKENS = new HashMap<String, TokenType>();

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
            while(charIsWhitespace()) {
                  nextChar();
            }

            char c = getChar();
            if(TOKENS.containsKey(String.valueOf(c)))
            {
                tokens.add(new Token(TOKENS.get(String.valueOf(c)), String.valueOf(c)));
                index++;
            }
            else
            {
                String temp = String.valueOf(c);
                while(!charIsWhitespace())
                {
                    temp += nextChar();
                }
                if(!TOKENS.containsKey(temp))
                {
                    if(allCharactersInAsciiRange(temp, 48, 57))  //isNumber
                    {
                        tokens.add(new Token(TokenType.NUMBER, temp));
                    }
                    else
                    {
                        tokens.add(new Token(TokenType.STRING, temp));
                    }
                }
                else
                {
                    tokens.add(new Token(TOKENS.get(temp), temp));
                }
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

    private boolean charIsWhitespace() {
        char c = getChar();
        return c == ' ' || c == '\n' || c == '\t' || c == '\r';
    }
}
