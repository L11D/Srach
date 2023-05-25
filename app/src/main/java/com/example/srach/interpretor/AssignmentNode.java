package com.example.srach.interpretor;

public class AssignmentNode extends LogicNode {
    private VariableNode variable;
    private MathNode evaluateResult;

    public void setVariable(VariableNode variable) {
        this.variable = variable;
    }

    public void setEvaluateResult(MathNode evaluateResult) {
        this.evaluateResult = evaluateResult;
    }

    @Override
    public void work() {
        if (evaluateResult.evaluate().type != variable.evaluate().type) {
            throw new IllegalStateException();
        }
        else
            variable.getVariables().setVariable(variable.getName(), evaluateResult.evaluate().value, evaluateResult.evaluate().type);
    }
}
