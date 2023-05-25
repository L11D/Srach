package com.example.srach.interpretor;

import java.util.ArrayList;

public class DeclarationArrayNode extends LogicNode {
    private String name;
    private DataType type;
    private int size;
    private final Variables variables;
    DeclarationArrayNode(String name, DataType type, int size, Variables variables) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.variables = variables;
    }

    @Override
    public void work() {
        variables.setArray(name, new ArrayList<>(size), type);
    }
}
