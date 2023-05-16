package com.example.srach.interpritator;

public class BranchNode extends LogicNode {
    private LogicNode nextTrue;
    private LogicNode nextFalse;
    private MathNodeBool evaluateResult;

    {
        this.nextTrue = null;
        this.nextFalse = null;
        this.evaluateResult = null;
    }

    public void setNextTrue(LogicNode nextTrue) {
        this.nextTrue = nextTrue;
    }

    public void setNextFalse(LogicNode nextFalse) {
        this.nextFalse = nextFalse;
    }

    public void setEvaluateResult(MathNodeBool evaluateResult) {
        this.evaluateResult = evaluateResult;
    }

    @Override
    public void work() {
        if (evaluateResult.evaluate())
            super.setNext(nextTrue);
        else
            super.setNext(nextFalse);
    }
}
