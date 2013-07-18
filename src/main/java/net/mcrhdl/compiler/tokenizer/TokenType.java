package net.mcrhdl.compiler.tokenizer;

public enum TokenType {
    /* Normal operators. */
    // End of source indicator.
    EOS(false, null, 0),

    /* Punctuators. */
    LPAREN(false, "(", 0),
    RPAREN(false, ")", 0),
    LBRACK(false, "[", 0),
    RBRACK(false, "]", 0),
    LBRACE(false, "{", 0),
    RBRACE(false, "}", 0),
    COLON(false, ":", 0),
    SEMICOLON(false, ";", 0),
    PERIOD(false, ".", 0),
    COMMA(false, ",", 1),

    /* Overloadable operators. */
    // Punctuators.
    CONDITIONAL(false, "?", 3),
    INC(false, "++", 0),
    DEC(false, "--", 0),
    NOT(false, "!", 0),
    BIT_NOT(false, "~", 0),

    /* Assignment operators. */
    ASSIGN(false, "=", 2),
    ASSIGN_BIT_OR(false, "|=", 2),
    ASSIGN_BIT_XOR(false, "^=", 2),
    ASSIGN_BIT_AND(false, "&=", 2),
    ASSIGN_SHL(false, "<<=", 2),
    ASSIGN_SAR(false, ">>=", 2),
    ASSIGN_SHR(false, ">>>=", 2),
    ASSIGN_ADD(false, "+=", 2),
    ASSIGN_SUB(false, "-=", 2),
    ASSIGN_MUL(false, "*=", 2),
    ASSIGN_DIV(false, "/=", 2),
    ASSIGN_MOD(false, "%=", 2),

    /* Binary operators. */
    OR(false, "||", 4),
    AND(false, "&&", 5),
    BIT_OR(false, "|", 6),
    BIT_XOR(false, "^", 7),
    BIT_AND(false, "&", 8),
    SHL(false, "<<", 11),
    SAR(false, ">>", 11),
    SHR(false, ">>>", 11),
    ADD(false, "+", 12),
    SUB(false, "-", 12),
    MUL(false, "*", 13),
    DIV(false, "/", 13),
    MOD(false, "%", 13),

    /* Compare operators. */
    EQ(false, "==", 9),
    NE(false, "!=", 9),
    EQ_STRICT(false, "===", 9),
    NE_STRICT(false, "!==", 9),
    LT(false, "<", 10),
    GT(false, ">", 10),
    LTE(false, "<=", 10),
    GTE(false, ">=", 10),

    /* Keywords. */
    NEW(true, "new", 0),
    RETURN(true, "return", 0),
    THIS(true, "this", 0),
    CLASS(true, "class", 0),
    NATIVE(true, "native", 0),

    /* Literals. */
    NUMBER(false, null, 0),
    STRING(false, null, 0),

    /* Macros */
    MACRO(false, null, 0),

    /* Identifiers. */
    IDENT(false, null, 0),

    /* Future reserved words. */
    FUTURE_RESERVED_WORD(false, null, 0),
    FUTURE_STRICT_RESERVED_WORD(false, null, 0),

    /* Error reporting only */
    /* Comments */
    NEWLINE(false, "\n", 0),
    STRING_QUOTE(false, "\"", 0),
    LINE_COMMENT(false, "//", 0),
    LMULTI_LINE_COMMENT_BEGIN(false, "/*", 0),
    LMULTI_LINE_COMMENT_END(false, "/*", 0),

    /* Illegal token - not able to scan. */
    ILLEGAL(false, "ILLEGAL", 0),

    /* Scanner-internal use only. */
    WHITESPACE(false, null, 0);

    private boolean keyword;
    private String identifier;
    private int precedence;

    private TokenType(boolean keyword, String identifier, int precedence) {
        this.keyword = keyword;
        this.identifier = identifier;
        this.precedence = precedence;

        if(keyword) {
            Tokenizer.KEYWORDS.put(identifier, this);
        } else {
            Tokenizer.OPERATORS.put(identifier, this);
        }
    }

    public int getPrecedence() {
        return precedence;
    }

    public boolean isKeyword() {
        return keyword;
    }
}