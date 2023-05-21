package com.example.srach.interpretator;
import java.util.Objects;

public class BranchNode extends LogicNode {
    private LogicNode nextTrue;
    private LogicNode nextFalse;
    private MathNode evaluateResult;

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
