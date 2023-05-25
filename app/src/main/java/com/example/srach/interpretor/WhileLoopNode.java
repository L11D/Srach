package com.example.srach.interpretor;

public class WhileLoopNode extends LogicNode {
    private BeginNode loopBegin;
    private LogicNode completed;
    private MathNode condition;
    WhileLoopNode() {
        loopBegin = null;
        completed = null;
        condition = null;
    }

    public void setLoopBegin(BeginNode loopBegin) {
        this.loopBegin = loopBegin;
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

    @Override
    public void work() {
        if (condition.evaluate().type == DataType.BOOL)
            if (Boolean.parseBoolean(condition.evaluate().value))
                setNext(loopBegin);
            else
                setNext(completed);
    }
}
