package com.example.srach.interpretator;

public class GetArrayIndexNode extends MathNode {
    ArrayNode array;
    NumberNode index;

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
