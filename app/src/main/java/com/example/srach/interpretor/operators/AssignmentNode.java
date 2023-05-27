package com.example.srach.interpretor.operators;

import com.example.srach.interpretor.logic.LogicNode;
import com.example.srach.interpretor.math.MathNode;
import com.example.srach.interpretor.variables.VariableNode;

public class AssignmentNode extends LogicNode {
    private VariableNode variable;
    private MathNode evaluateResult;
    public AssignmentNode() {
        variable = null;
        evaluateResult = null;
    }

    public void setVariable(VariableNode variable) {
        this.variable = variable;
    }

    public void setEvaluateResult(MathNode evaluateResult) {
        this.evaluateResult = evaluateResult;
    }

    @Override
    public void work() {
        if (evaluateResult.evaluate().type != variable.evaluate().type) {
            throw new IllegalStateException("Нельзя присвоить переменной типа " + variable.evaluate().type + " тип " + evaluateResult.evaluate().type);
        }
        else
            variable.getVariables().setVariable(variable.getName(), evaluateResult.evaluate().value, evaluateResult.evaluate().type);
    }
}
