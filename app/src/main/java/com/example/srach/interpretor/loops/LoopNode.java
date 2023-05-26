package com.example.srach.interpretor.loops;

import com.example.srach.interpretor.logic.LogicNode;
import com.example.srach.interpretor.math.MathNode;

public abstract class LoopNode extends LogicNode {
    private LogicNode completed;
    private MathNode condition;
    private BeginNode loopBegin;
    public LoopNode() {
        completed = null;
        condition = null;
        loopBegin = null;
    }

    public void setCompleted(LogicNode completed) {
        this.completed = completed;
    }

    public LogicNode getCompleted() {
        return completed;
    }

    public void setCondition(MathNode condition) {
        this.condition = condition;
    }

    public MathNode getCondition() {
        return condition;
    }

    public void setLoopBegin(BeginNode loopBegin) {
        this.loopBegin = loopBegin;
    }

    public BeginNode getLoopBegin() {
        return loopBegin;
    }
}
