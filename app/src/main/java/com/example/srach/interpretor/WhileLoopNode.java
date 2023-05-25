package com.example.srach.interpretor;

public class WhileLoopNode extends LoopNode {
    private BeginNode loopBegin;
    WhileLoopNode() {
        loopBegin = null;
    }

    public void setLoopBegin(BeginNode loopBegin) {
        this.loopBegin = loopBegin;
    }

    @Override
    public void work() {
        if (getCondition().evaluate().type == DataType.BOOL)
            if (Boolean.parseBoolean(getCondition().evaluate().value))
                setNext(loopBegin);
            else
                setNext(getCompleted());
    }
}
