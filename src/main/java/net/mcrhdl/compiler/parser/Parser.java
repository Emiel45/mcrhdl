package net.mcrhdl.compiler.parser;

import net.mcrhdl.compiler.tokenizer.Token;
import net.mcrhdl.compiler.tokenizer.TokenType;
import static net.mcrhdl.compiler.tokenizer.TokenType.*;

import java.util.List;

public class Parser {
    private int index;
    private List<Token> tokens;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public ProgramST parse()  throws ParseException {
        ProgramST p = new ProgramST();

        while(isTokenAvailable()) {
            topLevel(p);
        }

        return p;
    }

    private TopLevelST topLevel(ProgramST p) throws ParseException {
        TopLevelST tl = new TopLevelST();

        if(accept(NATIVE, CLASS)) {
            p.classes.add(clazz(p));
        } else {
            expect(LPAREN);
            p.functions.add(function(p));
        }

        return tl;
    }

    private ClassST clazz(ProgramST p) throws ParseException {
        ClassST c = new ClassST();
        nextToken();
        if(accept(CLASS))
            nextToken();

        expect(IDENTIFIER);
        c.name = getToken().getData();

        nextToken();
        expect(LBRACE);

        nextToken();
        while(!accept(RBRACE)) {
            if(accept(IDENTIFIER)) {
                c.members.add(memberDeclaration(c));
            } else {
                expect(LPAREN);
            }
        }

        return c;
    }

    private FunctionST function(ProgramST p) {
        FunctionST f = new FunctionST();

        return f;
    }

    private LocalDeclarationST memberDeclaration(ClassST c) {
        LocalDeclarationST d = new LocalDeclarationST();

        return d;
    }

    private DeclarationST codeDeclaration(ProgramST p) {

    }

    private Token getToken() {
        if(isTokenAvailable())
            return tokens.get(index);
        else
            return null;
    }

    private Token nextToken() {
        index++;
        return getToken();
    }

    private Token prevToken() {
        index--;
        return getToken();
    }

    private boolean accept(TokenType... types) {
        for(TokenType type : types) {
            if(type == getToken().getType())
                return true;
        }
        return false;
    }

    private void expect(TokenType... types) throws ParseException {
        boolean ret = false;
        for(TokenType type : types) {
            if(type == getToken().getType()) {
                ret = true;
                break;
            }
        }
        if(!ret)
            throw new ParseException("Expected a different token.");
    }

    private boolean isTokenAvailable() {
        return index < tokens.size();
    }
}
