package com.example.srach.interpretor;

public class GetArrayIndexNode extends MathNode {
    ArrayNode array;
    ValueNode index;

    GetArrayIndexNode() {
        array = null;
        index = null;
    }

    public void setArray(ArrayNode array) {
        this.array = array;
    }

    public void setIndex(ValueNode index) {
        this.index = index;
    }

    @Override
    public Data evaluate() {
        return new Data(array.getArrayIndexValue(Integer.parseInt(index.evaluate().value)).value, array.getType());
    }
}
