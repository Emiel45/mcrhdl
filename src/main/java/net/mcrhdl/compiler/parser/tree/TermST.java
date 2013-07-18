package net.mcrhdl.compiler.parser.tree;

import net.mcrhdl.compiler.parser.OperationType;

import java.util.List;

public class TermST extends ExpressionST {
    public List<UnaryST> unaries;
    public List<OperationType> opeartions;
}
