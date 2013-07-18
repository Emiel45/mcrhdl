package net.mcrhdl.compiler.parser.tree;

import com.google.common.collect.Lists;

import java.util.List;

public class FunctionST extends TopLevelST {
    public List<String> returnTypes = Lists.newArrayList();
    public String name;
    public List<ArgumentPair> arguments = Lists.newArrayList();
    public List<StatementST> statements = Lists.newArrayList();
}
