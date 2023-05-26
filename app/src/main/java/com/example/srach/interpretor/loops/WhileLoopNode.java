package com.example.srach.interpretor.loops;

import com.example.srach.interpretor.DataType;

public class WhileLoopNode extends LoopNode {
    public WhileLoopNode(){}

    @Override
    public void work() {
        if (getCondition().evaluate().type == DataType.BOOL)
            if (Boolean.parseBoolean(getCondition().evaluate().value))
                setNext(getLoopBegin());
            else
                setNext(getCompleted());
    }
}
