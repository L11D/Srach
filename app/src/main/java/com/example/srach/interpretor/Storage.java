package com.example.srach.interpretor;

import com.example.srach.interpretor.functions.Function;
import com.example.srach.interpretor.logic.LogicNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kotlin.Pair;

public class Storage {
    private final Map<String, Data> variables = new HashMap<>();
    private final Map<String, Pair<ArrayList<String>, DataType>> arrays = new HashMap<>();
    private final Map<String, Function> functions = new HashMap<>();
    public Storage(){}

    public Data getVariable(String name) {
        return this.variables.get(name);
    }

    public void setVariable(String name, String value, DataType type) {
        variables.put(name, new Data(value, type));
    }

    public Pair<ArrayList<String>, DataType> getArray(String name) {
        return this.arrays.get(name);
    }

    public void setArray(String name, ArrayList<String> array, DataType type) {
        arrays.put(name, new Pair<>(array, type));
    }

    public Function getFunction(String name) {
        return functions.get(name);
    }

    public void setFunction(String name, DataType type, int amountArguments, LogicNode link) {
        functions.put(name, new Function(type, amountArguments, link));
    }

    public Map<String, Data> getVariables() {
        return variables;
    }

    public Map<String, Pair<ArrayList<String>, DataType>> getArrays() {
        return arrays;
    }

    public Map<String, Function> getFunctions() {
        return functions;
    }
}
