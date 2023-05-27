package com.example.srach.interpretor.functions;

import com.example.srach.interpretor.functions.Function;
import com.example.srach.interpretor.logic.LogicNode;
import com.example.srach.interpretor.math.MathNode;

public class SetArgumentNode extends LogicNode {
    private Integer number;
    private MathNode evaluateResult;
    private Function function;
    public SetArgumentNode() {
        number = null;
        evaluateResult = null;
        function = null;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public void setData(MathNode evaluateResult) {
        this.evaluateResult = evaluateResult;
    }

    public int getNumber() {
        return number;
    }

    public Function getFunction() {
        return function;
    }

    public MathNode getData() {
        return evaluateResult;
    }

    @Override
    public void work() {
        function.setArgument(number, evaluateResult.evaluate());
    }
}
