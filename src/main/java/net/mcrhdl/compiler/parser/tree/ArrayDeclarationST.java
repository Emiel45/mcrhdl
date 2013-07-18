package net.mcrhdl.compiler.parser.tree;

public class ArrayDeclarationST extends DeclarationST {
    public String type, name;
    public ExpressionST subscript;
    public ExpressionST rightSide;
}
