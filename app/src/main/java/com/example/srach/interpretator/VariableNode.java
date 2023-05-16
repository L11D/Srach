package com.example.srach.interpretator;

public class VariableNode extends OperandNode {
    private final String name;
    private final Variables storage;

    VariableNode(String name, Variables storage) {
        this.name = name;
        this.storage = storage;
        this.storage.setVariableValue(name, 0);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return storage.getVariableValue(name);
    }

    public void setValue(int value) {
        storage.setVariableValue(name, value);
    }

    @Override
    public int evaluate() {
        return getValue();
    }
}
