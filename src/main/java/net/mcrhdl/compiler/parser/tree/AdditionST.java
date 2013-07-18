package net.mcrhdl.compiler.parser.tree;

import net.mcrhdl.compiler.parser.OperationType;

import java.util.List;

public class AdditionST extends ExpressionST {
    public List<TermST> terms;
    public List<OperationType> operations;
}
