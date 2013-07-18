package net.mcrhdl.compiler.parser;

import net.mcrhdl.compiler.tokenizer.TokenType;

public class UnexpectedTokenException extends Exception {
    private static String constructMessage(int column, int line, TokenType givenType, TokenType[] expectedTypes) {
        StringBuilder msgBuilder = new StringBuilder();

        msgBuilder.append("Unexpected token " + givenType + " at line " + (line + 1) + ", column " + (column + 1) + ". Possible replacements: ");

        msgBuilder.append(expectedTypes[0]);

        for(int i = 1; i < expectedTypes.length; i++) {
            msgBuilder.append(", " + expectedTypes[i]);
        }

        msgBuilder.append(".");

        return msgBuilder.toString();
    }

    public UnexpectedTokenException(int column, int line, TokenType givenType, TokenType[] expectedTypes) {
        super(constructMessage(column, line, givenType, expectedTypes));
    }
}
