package com.example.srach.interpretor;

public class GetArrayIndexNode extends MathNode {
    ArrayNode array;
    com.example.srach.interpretor.NumberNode index;

    GetArrayIndexNode() {
        array = null;
        index = null;
    }

    public void setArray(ArrayNode array) {
        this.array = array;
    }

    public void setIndex(NumberNode index) {
        this.index = index;
    }

    @Override
    public Data evaluate() {
        return new Data(array.getArrayIndexValue(Integer.parseInt(index.evaluate().value)), array.getType());
    }
}
