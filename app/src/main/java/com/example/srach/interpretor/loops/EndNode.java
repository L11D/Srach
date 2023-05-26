package com.example.srach.interpretor.loops;

import com.example.srach.interpretor.logic.LogicNode;

public class EndNode extends LogicNode {
    private LoopNode loopNode;
    public EndNode() {
        loopNode = null;
    }

    public void setLoopNode(LoopNode loopNode) {
        this.loopNode = loopNode;
    }

    public LoopNode getLoopNode() {
        return loopNode;
    }

    @Override
    public void work() {
        setNext(loopNode);
    }
}
