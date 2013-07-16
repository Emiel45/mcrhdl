package net.mcrhdl.compiler.parser;

import java.util.List;

public class ClassST extends TopLevelST {
    public String name;
    public List<DeclarationST> members;
    public List<FunctionST> functions;
}
