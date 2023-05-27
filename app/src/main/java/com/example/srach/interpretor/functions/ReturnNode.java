package com.example.srach.interpretor.functions;

import com.example.srach.interpretor.Data;
import com.example.srach.interpretor.functions.Function;
import com.example.srach.interpretor.logic.LogicNode;
import com.example.srach.interpretor.math.MathNode;
import com.example.srach.interpretor.math.MathNodeEvaluate;

public class ReturnNode extends LogicNode implements MathNodeEvaluate {
    private Function owner;
    private MathNode evaluateResult;
    ReturnNode() {
        owner = null;
        evaluateResult = null;
    }

    @Override
    public Data evaluate() {
        return evaluateResult.evaluate();
    }

    @Override
    public void work() {
        owner.setReturnedData(evaluate());
    }
}
