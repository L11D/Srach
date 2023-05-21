package com.example.srach.interpretor;

public class PrintNode extends LogicNode {
    private MathNode evaluateResult;

    public void setEvaluateResult(MathNode evaluateResult) {
        this.evaluateResult = evaluateResult;
    }

    @Override
    public void work() {
        System.out.println(evaluateResult.evaluate().value);
    }
}
