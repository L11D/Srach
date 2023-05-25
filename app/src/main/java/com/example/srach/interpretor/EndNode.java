package com.example.srach.interpretor;

public class EndNode extends LogicNode {
    private LoopNode loopNode;

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
