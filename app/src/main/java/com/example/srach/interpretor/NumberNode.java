package com.example.srach.interpretor;

public class NumberNode extends MathNode {
    private String value;
    NumberNode() {
        this.value = "0";
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public Data evaluate() {
        if (value.contains("."))
            return new Data(value, DataType.DOUBLE);
        else
            return new Data(value, DataType.INT);
    }
}
