package com.example.srach.interpretor.variables;

import com.example.srach.interpretor.DataType;
import com.example.srach.interpretor.logic.LogicNode;
import com.example.srach.interpretor.VariablesAndArraysStorage;

public class DeclarationVariableNode extends LogicNode {
    private String name;
    private DataType type;
    private final VariablesAndArraysStorage variablesAndArraysStorage;
    public DeclarationVariableNode(VariablesAndArraysStorage variablesAndArraysStorage) {
        this.name = null;
        this.type = null;
        this.variablesAndArraysStorage = variablesAndArraysStorage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    @Override
    public void work() {
        variablesAndArraysStorage.setVariable(name, null, type);
    }
}
