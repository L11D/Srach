package com.example.srach.interpritator;

public class AssignmentNode extends LogicNode {
    private VariableNode variable;
    private MathNodeInt evaluateResult;

    public void setVariable(VariableNode variable) {
        this.variable = variable;
    }

    public void setEvaluateResult(MathNodeInt evaluateResult) {
        this.evaluateResult = evaluateResult;
    }

    @Override
    public void work() {
        variable.setValue(evaluateResult.evaluate());
    }
}
