package net.mcrhdl.compiler.tokenizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class TEST
{
    public static void main(String[] args) throws IOException
    {
        File f = new File("test.rhdl");
        if(!f.exists())
            f.createNewFile();

        BufferedReader reader = new BufferedReader(new FileReader(f));
        String dataToTokenize = "";
        String line;
        while((line = reader.readLine()) != null)
        {
            dataToTokenize += line + "\n";
        }
        reader.close();

        Tokenizer tokenizer = new Tokenizer(dataToTokenize.toCharArray());
        List<Token> tokens = tokenizer.tokenize();
        for(Token t : tokens)
        {
            System.out.println(t);
        }
    }
}
