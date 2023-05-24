package com.example.srach.interpretor;

public class ValueNode extends MathNode {
    private Data value;
    ValueNode() {
        this.value = null;
    }

    public void setValue(Data value) {
        this.value = value;
    }

    public Data getValue() {
        return value;
    }

    @Override
    public Data evaluate() {
        return value;
    }
}
