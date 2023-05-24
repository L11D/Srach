package com.example.srach.interpretor;

public class SetArrayIndexNode extends LogicNode {
    ArrayNode array;
    ValueNode index;
    MathNode value;

    SetArrayIndexNode() {
        array = null;
        index = null;
        value = null;
    }

    public void setArray(ArrayNode array) {
        this.array = array;
    }

    public void setIndex(ValueNode index) {
        this.index = index;
    }

    public void setValue(MathNode value) {
        this.value = value;
    }

    @Override
    public void work() {
        if (array.getType() == value.evaluate().type)
            array.setArrayIndexValue(Integer.parseInt(index.evaluate().value), value.evaluate().value);
        else
            throw new IllegalStateException();
    }
}
