package com.example.srach.interpretor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kotlin.Pair;

public class ArrayNode extends Node {
    private String name;
    private final Map<String, Pair<ArrayList<String>, DataType>> arrays;
    ArrayNode(Map<String, Pair<ArrayList<String>, DataType>> arrays) {
        name = null;
        this.arrays = arrays;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataType getType() {
        return arrays.get(name).component2();
    }

    public Data getArrayIndexValue(int index) {
        return new Data(arrays.get(name).component1().get(index), arrays.get(name).component2());
    }

    public void setArrayIndexValue(int index, String value) {
        arrays.get(name).component1().set(index, value);
    }
}
