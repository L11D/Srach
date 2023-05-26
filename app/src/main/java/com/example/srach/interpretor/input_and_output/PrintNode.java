package com.example.srach.interpretor.input_and_output;

import com.example.srach.interpretor.logic.LogicNode;
import com.example.srach.interpretor.math.MathNode;

public class PrintNode extends LogicNode {
    private MathNode evaluateResult;
    public PrintNode() {
        evaluateResult = null;
    }

    public void setEvaluateResult(MathNode evaluateResult) {
        this.evaluateResult = evaluateResult;
    }

    @Override
    public void work() {
        System.out.println(evaluateResult.evaluate().value);
    }
}
