package com.example.srach.interpretor.arrays;

import com.example.srach.interpretor.DataType;
import com.example.srach.interpretor.logic.LogicNode;
import com.example.srach.interpretor.Storage;

import java.util.ArrayList;

public class DeclarationArrayNode extends LogicNode {
    private String name;
    private DataType type;
    private int size;
    private final Storage storage;
    public DeclarationArrayNode(String name, DataType type, int size, Storage storage) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.storage = storage;
    }

    @Override
    public void work() {
        storage.setArray(name, new ArrayList<>(size), type);
    }
}
