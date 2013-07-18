package net.mcrhdl.compiler.parser;

import net.mcrhdl.compiler.parser.tree.*;
import net.mcrhdl.compiler.tokenizer.Token;
import net.mcrhdl.compiler.tokenizer.TokenType;
import static net.mcrhdl.compiler.tokenizer.TokenType.*;

import java.util.List;

/*
 * If you add more parse methods to this class, follow these basic rules.
 * * Invoke parse methods when at the first token of the language construct that they represent.
 * * Return from a parse method at the token after the language construct that they represent.
 * * "expect" tokens to avoid ambiguity in the parser.
 */

public class Parser {
    private int index;
    private List<Token> tokens;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public ProgramST parse()  throws UnexpectedTokenException {
        ProgramST p = new ProgramST();

        while(isTokenAvailable()) {
            System.out.println(getToken());
            topLevel(p);
        }

        return p;
    }

    private TopLevelST topLevel(ProgramST p) throws UnexpectedTokenException {
        TopLevelST tl = new TopLevelST();

        if(accept(NATIVE, CLASS)) {
            p.classes.add(clazz());
        } else {
            expect(LPAREN);
            p.functions.add(function());
        }

        return tl;
    }

    // TODO: Finish implementing classes
    private ClassST clazz() throws UnexpectedTokenException {
        ClassST c = new ClassST();
        nextToken();
        if(accept(CLASS))
            nextToken();

        expect(IDENT);
        c.name = getToken().getData();

        nextToken();
        expect(LBRACE);

        nextToken();
        while(!accept(RBRACE)) {
            if(accept(IDENT)) {
                c.members.add(memberDeclaration(c));
            } else {
                expect(LPAREN);
            }
        }

        return c;
    }

    private FunctionST function() throws UnexpectedTokenException {
        FunctionST f = new FunctionST();

        returnList(f.returnTypes);
        expect(IDENT);
        f.name = getToken().getData();
        nextToken();
        argumentList(f.arguments);
        nextToken();
        expect(LBRACE);
        statements(f.statements, RBRACE);

        return f;
    }

    // TODO: Implement all statement branches
    private void statements(List<StatementST> statements, TokenType stop) throws UnexpectedTokenException {
        while(!accept(stop)) {
            StatementST statement = statement();

            if(statement == null) {
                continue;
            } else {
                statements.add(statement);
            }

            expect(SEMICOLON);
            nextToken();
        }
    }

    private StatementST statement() throws UnexpectedTokenException {
        if(accept(RETURN)) {
            return returnStatement();
        }

        return null;
    }

    private ReturnST returnStatement() throws UnexpectedTokenException {
        ReturnST r = new ReturnST();

        expect(RETURN);
        nextToken();

        expressionList(r.values, SEMICOLON);

        return r;
    }

    // TODO: Implement declarations
    private LocalDeclarationST memberDeclaration(ClassST c) {
        LocalDeclarationST d = new LocalDeclarationST();

        return d;
    }

    // TODO: Implement declarations
    private DeclarationST codeDeclaration(ProgramST p) {
        return null;
    }

    private void expressionList(List<ExpressionST> expressions, TokenType stop) throws UnexpectedTokenException {
        if(!accept(stop)) {
            expressions.add(expression());
        } else {
            return;
        }

        while(!accept(stop)) {
            expect(COMMA);
            nextToken();
            expressions.add(expression());
            nextToken();
        }
    }

    private void returnList(List<String> types) throws UnexpectedTokenException {
        expect(LPAREN);
        nextToken();

        if(!accept(RPAREN)) {
            expect(IDENT);
            types.add(getToken().getData());
            nextToken();
        } else {
            nextToken();
            return;
        }

        while(!accept(RPAREN)) {
            expect(COMMA);
            nextToken();
            expect(IDENT);
            types.add(getToken().getData());
            nextToken();
        }

        nextToken();
    }

    private void argumentList(List<ArgumentPair> arguments) throws UnexpectedTokenException {
        expect(LPAREN);
        nextToken();

        String type, name;

        if(!accept(RPAREN)) {
            expect(IDENT);
            type = getToken().getData();
            nextToken();
            expect(IDENT);
            name = getToken().getData();

            arguments.add(new ArgumentPair(type, name));
            nextToken();
        } else {
            nextToken();
            return;
        }

        while(!accept(RPAREN)) {
            expect(COMMA);
            nextToken();
            expect(IDENT);
            type = getToken().getData();
            nextToken();
            expect(IDENT);
            name = getToken().getData();

            arguments.add(new ArgumentPair(type, name));
            nextToken();
        }

        expect(RPAREN);

        nextToken();
    }

    /* Expressions */

    // TODO: Implement expression parsing
    private AdditionST expression() {
        AdditionST a = new AdditionST();
        return a;
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

    private void expect(TokenType... types) throws UnexpectedTokenException {
        boolean ret = false;
        for(TokenType type : types) {
            if(type == getToken().getType()) {
                ret = true;
                break;
            }
        }

        Token givenToken = getToken();

        if(!ret)
            throw new UnexpectedTokenException(givenToken.getColumn(), givenToken.getLine(), givenToken.getType(), types);
    }

    private boolean isTokenAvailable() {
        return index < tokens.size();
    }
}
