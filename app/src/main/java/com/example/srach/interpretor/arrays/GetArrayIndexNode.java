package com.example.srach.interpretor.arrays;

import com.example.srach.interpretor.Data;
import com.example.srach.interpretor.Main;
import com.example.srach.interpretor.math.MathNode;
import com.example.srach.interpretor.ValueNode;

public class GetArrayIndexNode extends MathNode {
    private ArrayNode array;
    private MathNode index;
    public GetArrayIndexNode() {
        array = null;
        index = null;
    }

    public void setArray(ArrayNode array) {
        this.array = array;
    }

    public void setIndex(MathNode index) {
        this.index = index;
    }

    @Override
    public Data evaluate() {
        return new Data(array.getArrayIndexValue(Integer.parseInt(index.evaluate().value)).value, array.getType());
    }
}
