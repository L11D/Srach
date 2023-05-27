package com.example.srach.interpretor.arrays;

import com.example.srach.interpretor.logic.LogicNode;
import com.example.srach.interpretor.math.MathNode;
import com.example.srach.interpretor.ValueNode;

public class SetArrayIndexNode extends LogicNode {
    private ArrayNode array;
    private MathNode index;
    private MathNode value;
    public SetArrayIndexNode() {
        array = null;
        index = null;
        value = null;
    }

    public void setArray(ArrayNode array) {
        this.array = array;
    }

    public void setIndex(MathNode index) {
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
            throw new IllegalStateException("Нельзя добавить данные типа " + value.evaluate().type + " в массив типа " + array.getType());
    }
}
