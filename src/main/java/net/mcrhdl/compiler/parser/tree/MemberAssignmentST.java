package net.mcrhdl.compiler.parser.tree;

import java.util.List;

public class MemberAssignmentST extends AssignmentST {
    public List<String> leftSide;
    public ExpressionST rightSide;
}
