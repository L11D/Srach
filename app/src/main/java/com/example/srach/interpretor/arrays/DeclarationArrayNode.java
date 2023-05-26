package com.example.srach.interpretor.arrays;

import com.example.srach.interpretor.DataType;
import com.example.srach.interpretor.logic.LogicNode;
import com.example.srach.interpretor.VariablesAndArraysStorage;

import java.util.ArrayList;

public class DeclarationArrayNode extends LogicNode {
    private String name;
    private DataType type;
    private int size;
    private final VariablesAndArraysStorage variablesAndArraysStorage;
    public DeclarationArrayNode(String name, DataType type, int size, VariablesAndArraysStorage variablesAndArraysStorage) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.variablesAndArraysStorage = variablesAndArraysStorage;
    }

    @Override
    public void work() {
        variablesAndArraysStorage.setArray(name, new ArrayList<>(size), type);
    }
}
