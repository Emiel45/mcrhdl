package net.mcrhdl.compiler.tokenizer;

public class Token {
    private TokenType type;
    private String data;
    private int column, line;

    public Token(TokenType type, String data, int column, int line) {
        this.type = type;
        this.data = data;
        this.column = column;
        this.line = line;
    }

    public TokenType getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "[" + (column + 1) + ", " + (line + 1) + "]" + type + "('" + data + "')";
    }
}
