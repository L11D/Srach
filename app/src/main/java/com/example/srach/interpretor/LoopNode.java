package com.example.srach.interpretor;

public abstract class LoopNode extends LogicNode {
    private LogicNode completed;
    private MathNode condition;
    LoopNode() {
        completed = null;
        condition = null;
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
}
