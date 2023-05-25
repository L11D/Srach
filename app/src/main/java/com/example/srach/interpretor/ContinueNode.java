package com.example.srach.interpretor;

public class ContinueNode extends LogicNode {
    private EndNode loopNode;

    public void setLoopNode(EndNode loopNode) {
        this.loopNode = loopNode;
    }

    @Override
    public void work() {
        setNext(loopNode.getLoopNode());
    }
}
