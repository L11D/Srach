package com.example.srach.interpretor;

public class DeclarationVariableNode extends LogicNode {
    private String name;
    private DataType type;
    private final Variables variables;

    DeclarationVariableNode(String name, DataType type, Variables variables) {
        this.name = name;
        this.type = type;
        this.variables = variables;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    @Override
    public void work() {
        variables.setVariable(name, null, type);
    }
}
