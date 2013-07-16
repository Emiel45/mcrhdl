package net.mcrhdl.compiler.tokenizer;

import java.util.List;

public class TEST
{
    public static void main(String[] args)
    {
        String dataToTokenize = "class bit {\n" +
                "   bit ~a;\n" +
                "   native bit();\n" +
                "   native bit =bit(bit b);\n" +
                "   native bit !();\n" +
                "   native bit |(bit b);\n" +
                "   bit &(bit b) {\n" +
                "       return !(a !& b);\n" +
                "   }\n" +
                "   bit !|(bit b) {\n" +
                "       return !(a | b);\n" +
                "   }\n" +
                "   bit !&(bit b) {\n" +
                "       return !a | !b;\n" +
                "   }\n" +
                "}";
        Tokenizer tokenizer = new Tokenizer(dataToTokenize.toCharArray());
        List<Token> tokens = tokenizer.tokenize();
        for(Token t : tokens)
        {
            System.out.println(t.getType() + " " + t.getData());
        }
    }
}
