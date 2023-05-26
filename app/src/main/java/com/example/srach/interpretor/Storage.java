package com.example.srach.interpretor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kotlin.Pair;

public class Storage {
    private final Map<String, Data> variables = new HashMap<>();
    private final Map<String, Pair<ArrayList<String>, DataType>> arrays = new HashMap<>();
    private final Map<String, Pair<DataType, Integer>> functions = new HashMap<>();
    public Storage(){}

    public Data getVariable(String name) {
        return this.variables.get(name);
    }

    public void setVariable(String name, String value, DataType type) {
        this.variables.put(name, new Data(value, type));
    }

    public Pair<ArrayList<String>, DataType> getArray(String name) {
        return this.arrays.get(name);
    }

    public void setArray(String name, ArrayList<String> array, DataType type) {
        this.arrays.put(name, new Pair<>(array, type));
    }
}
