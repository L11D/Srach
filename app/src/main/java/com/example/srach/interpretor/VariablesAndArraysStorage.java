package com.example.srach.interpretor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kotlin.Pair;

public class VariablesAndArraysStorage {
    private final Map<String, Data> variables = new HashMap<>();
    private final Map<String, Pair<ArrayList<String>, DataType>> arrays = new HashMap<>();
    public VariablesAndArraysStorage(){}

    public Data getVariable(String name) {
        return this.variables.get(name);
    }

    public void setVariable(String name, String value, DataType type) {
        this.variables.put(name, new Data(value, type));
    }
    public void removeVariable(String name) {
        if (variables.containsKey(name))
            variables.remove(name);
    }

    public Pair<ArrayList<String>, DataType> getArray(String name) {
        return this.arrays.get(name);
    }

    public void setArray(String name, ArrayList<String> array, DataType type) {
        this.arrays.put(name, new Pair<>(array, type));
    }
}
