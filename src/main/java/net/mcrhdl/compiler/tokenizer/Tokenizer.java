package net.mcrhdl.compiler.tokenizer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class Tokenizer {
    private int index;
    private char[] code;

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
