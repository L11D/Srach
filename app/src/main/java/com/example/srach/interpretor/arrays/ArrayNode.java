package com.example.srach.interpretor.arrays;

import com.example.srach.interpretor.Data;
import com.example.srach.interpretor.DataType;
import com.example.srach.interpretor.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kotlin.Pair;

public class ArrayNode extends Node {
    private String name;
    private final Map<String, Pair<ArrayList<String>, DataType>> storage;
    public ArrayNode(Map<String, Pair<ArrayList<String>, DataType>> arrays) {
        name = null;
        this.storage = arrays;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataType getType() {
        return storage.get(name).component2();
    }

    public Data getArrayIndexValue(int index) {
        return new Data(storage.get(name).component1().get(index), storage.get(name).component2());
    }

    public void setArrayIndexValue(int index, String value) {
        storage.get(name).component1().set(index, value);
    }
}
