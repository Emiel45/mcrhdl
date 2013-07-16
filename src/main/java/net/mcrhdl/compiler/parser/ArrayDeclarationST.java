package net.mcrhdl.compiler.parser;

public class ArrayDeclarationST extends DeclarationST {
    public String type, name;
    public ExpressionST subscript;
    public ExpressionST rightSide;
}
