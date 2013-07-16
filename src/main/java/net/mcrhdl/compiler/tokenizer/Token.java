package net.mcrhdl.compiler.tokenizer;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

public class Token {
    private TokenType type;
    private String data;
    public Token(TokenType type, String data) {
        this.type = type;
        this.data = data;
    }

    public TokenType getType() {
        return type;
    }

    public String getData() {
        return data;
    }
}
