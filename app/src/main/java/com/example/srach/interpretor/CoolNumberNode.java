package com.example.srach.interpretor;

public class CoolNumberNode extends MathNode {
    private String value;
    private DataType type;

    public CoolNumberNode(DataType type) {
        this.type = type;
        this.value = "0";
    }

    public void setValue(String value) {
        this.value = value;
    }
    public void setType(DataType type) {this.type = type;}

    @Override
    public Data evaluate() {
        return new Data(value, type);
    }
}