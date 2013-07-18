package net.mcrhdl.compiler.parser.tree;

public class ArrayAssignmentST extends AssignmentST {
    public String leftSide;
    public ExpressionST subscript;
    public ExpressionST rightSide;
}
