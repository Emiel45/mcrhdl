package net.mcrhdl.compiler.parser.tree;

import com.google.common.collect.Lists;
import net.mcrhdl.compiler.parser.SyntaxTree;

import java.util.List;

public class ProgramST extends SyntaxTree {
    public List<FunctionST> functions = Lists.newArrayList();
    public List<ClassST> classes = Lists.newArrayList();
}
