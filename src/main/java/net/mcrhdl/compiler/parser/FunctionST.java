package net.mcrhdl.compiler.parser;

import com.google.common.collect.Lists;

import java.util.List;

public class FunctionST extends TopLevelST {
    public List<String> returnTypes = Lists.newArrayList();
    public List<String> argumentTypes = Lists.newArrayList();
    public List<String> argumentIdents = Lists.newArrayList();
    public List<StatementST> statements = Lists.newArrayList();
}
