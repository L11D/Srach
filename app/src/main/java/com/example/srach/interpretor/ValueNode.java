package com.example.srach.interpretor;

import com.example.srach.interpretor.math.MathNode;

public class ValueNode extends MathNode {
    private Data value;
    public ValueNode() {
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
