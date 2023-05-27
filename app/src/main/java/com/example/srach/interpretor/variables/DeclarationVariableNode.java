package com.example.srach.interpretor.variables;

import com.example.srach.interpretor.DataType;
import com.example.srach.interpretor.logic.LogicNode;
import com.example.srach.interpretor.Storage;

public class DeclarationVariableNode extends LogicNode {
    private String name;
    private DataType type;
    private String value;
    private final Storage storage;
    public DeclarationVariableNode(Storage Storage) {
        name = null;
        type = null;
        value = null;
        this.storage = Storage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public DataType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void work() {
        storage.setVariable(name, value, type);
    }
    public void remove() { storage.removeVariable(name); }
}
