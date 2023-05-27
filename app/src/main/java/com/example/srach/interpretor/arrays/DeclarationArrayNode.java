package com.example.srach.interpretor.arrays;

import com.example.srach.interpretor.DataType;
import com.example.srach.interpretor.logic.LogicNode;
import com.example.srach.interpretor.Storage;

import java.util.ArrayList;

public class DeclarationArrayNode extends LogicNode {
    private String name;
    private DataType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private int size;
    private final Storage storage;
    public DeclarationArrayNode( Storage storage) {
        this.name = null;
        this.type = null;
        this.size = 0;
        this.storage = storage;
    }

    @Override
    public void work() {
        storage.setArray(name, new ArrayList<>(size), type);
    }

    public void remove(){storage.removeArray(name);}
}
