package com.example.srach.interpretor;

import java.util.Objects;

public class BranchNode extends com.example.srach.interpretor.LogicNode {
    private com.example.srach.interpretor.LogicNode nextTrue;
    private com.example.srach.interpretor.LogicNode nextFalse;
    private MathNode evaluateResult;

    {
        this.nextTrue = null;
        this.nextFalse = null;
        this.evaluateResult = null;
    }

    public void setNextTrue(com.example.srach.interpretor.LogicNode nextTrue) {
        this.nextTrue = nextTrue;
    }

    public void setNextFalse(LogicNode nextFalse) {
        this.nextFalse = nextFalse;
    }

    public void setEvaluateResult(MathNode evaluateResult) {
        this.evaluateResult = evaluateResult;
    }

    @Override
    public void work() {
        if (evaluateResult.evaluate().type == DataType.BOOL)
            if (Objects.equals(evaluateResult.evaluate().value, "true"))
                super.setNext(nextTrue);
            else
                super.setNext(nextFalse);
        else
            throw new IllegalStateException();
    }
}
