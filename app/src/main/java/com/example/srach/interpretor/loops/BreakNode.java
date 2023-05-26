package com.example.srach.interpretor.loops;

import com.example.srach.interpretor.logic.LogicNode;

public class BreakNode extends LogicNode {
    private EndNode loopNode;
    public BreakNode() {
        loopNode = null;
    }

    public void setLoopNode(EndNode loopNode) {
        this.loopNode = loopNode;
    }

    @Override
    public void work() {
        setNext(loopNode.getLoopNode().getCompleted());
    }
}
