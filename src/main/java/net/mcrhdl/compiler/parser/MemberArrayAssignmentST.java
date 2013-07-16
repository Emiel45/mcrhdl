package net.mcrhdl.compiler.parser;

import java.util.List;

public class MemberArrayAssignmentST extends AssignmentST {
    public List<String> leftSide;
    public ExpressionST subscript;
    public ExpressionST rightSide;
}
