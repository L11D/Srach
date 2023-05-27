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

    private int size;
    private final Storage storage;
    public DeclarationArrayNode(Storage storage) {
        this.size = 0;
        this.type = null;
        this.name = null;
        this.storage = storage;
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

    @Override
    public void work() {
        storage.setArray(name, new ArrayList<>(size), type);
    }

    public void remove(){storage.removeArray(name);}
}
