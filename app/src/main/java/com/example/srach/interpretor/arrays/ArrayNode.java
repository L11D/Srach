package com.example.srach.interpretor.arrays;

import com.example.srach.interpretor.Data;
import com.example.srach.interpretor.DataType;
import com.example.srach.interpretor.Node;
import com.example.srach.interpretor.Storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kotlin.Pair;

public class ArrayNode extends Node {
    private String name;
    private final Storage storage;
    public ArrayNode(Storage storage) {
        name = null;
        this.storage = storage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataType getType() {
        return storage.getArray(name).component2();
    }

    public Data getArrayIndexValue(int index) {
        return new Data(storage.getArray(name).component1().get(index), storage.getArray(name).component2());
    }

    public void setArrayIndexValue(int index, String value) {
        storage.getArray(name).component1().set(index, value);
    }
}
