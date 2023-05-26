package com.example.srach.interpretor.loops;

import com.example.srach.interpretor.DataType;
import com.example.srach.interpretor.operators.AssignmentNode;

public class ForLoopNode extends LoopNode {
    private AssignmentNode start;
    private AssignmentNode step;
    private BeginNode loopBegin;
    private boolean isFirstGate;
    public ForLoopNode() {
        start = null;
        step = null;
        loopBegin = null;
        isFirstGate = true;
    }

    public void setStart(AssignmentNode start) {
        this.start = start;
    }

    public void setStep(AssignmentNode step) {
        this.step = step;
    }

    public void setLoopBegin(BeginNode loopBegin) {
        this.loopBegin = loopBegin;
    }


    @Override
    public void work() {
        if (getCondition().evaluate().type == DataType.BOOL) {
            if (isFirstGate) {
                start.work();
                isFirstGate = false;
            } else
                step.work();
            if (Boolean.parseBoolean(getCondition().evaluate().value))
                setNext(loopBegin);
            else
                setNext(getCompleted());
        }
    }
}
