package com.example.srach.interpretor;

public class AssignmentNode extends LogicNode {
    private com.example.srach.interpretor.VariableNode variable;
    private MathNode evaluateResult;

    public void setVariable(VariableNode variable) {
        this.variable = variable;
    }

    public void setEvaluateResult(MathNode evaluateResult) {
        this.evaluateResult = evaluateResult;
    }

    @Override
    public void work() {
        if (evaluateResult.evaluate().type != variable.getType()) {
            throw new IllegalStateException();
        }
        else
            variable.setValue(evaluateResult.evaluate().value);
    }
}
