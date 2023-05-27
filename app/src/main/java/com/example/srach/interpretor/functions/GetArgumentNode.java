package com.example.srach.interpretor.functions;

import com.example.srach.interpretor.Data;
import com.example.srach.interpretor.functions.Function;
import com.example.srach.interpretor.math.MathNode;

public class GetArgumentNode extends MathNode {
    private Integer number;
    private Function function;
    GetArgumentNode() {
        number = null;
        function = null;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public int getNumber() {
        return number;
    }

    public Function getFunction() {
        return function;
    }

    @Override
    public Data evaluate() {
        return function.getArgument(number);
    }
}
