package net.mcrhdl.compiler.parser.tree;

import net.mcrhdl.compiler.parser.OperationType;

import java.util.List;

public class UnaryST extends ExpressionST {
    public List<FactorST> factors;
    public List<OperationType> operations;
}
