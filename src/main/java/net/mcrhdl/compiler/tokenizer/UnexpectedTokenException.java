package net.mcrhdl.compiler.tokenizer;

import java.io.IOException;

public class UnexpectedTokenException extends IOException {
    public UnexpectedTokenException(int column, int line) {
        super("Unexpected token at line " + (line + 1) + ", column " + (column + 1) + ".");
    }
}
