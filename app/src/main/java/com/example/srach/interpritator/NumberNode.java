package com.example.srach.interpritator;

public class NumberNode extends OperandNode {
    private int value;

    {
        value = 0;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int evaluate() {
        return value;
    }
}
