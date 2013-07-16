package net.mcrhdl.compiler.parser;

import com.google.common.collect.Lists;

import java.util.List;

public class ProgramST extends SyntaxTree {
    public List<FunctionST> functions = Lists.newArrayList();
    public List<ClassST> classes = Lists.newArrayList();
}
