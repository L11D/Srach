package com.example.srach.interpretor;

public class ContinueNode extends LogicNode {
    private EndNode loopNode;

    public void setLoopNode(EndNode loopNode) {
        this.loopNode = loopNode;
        setNext(loopNode.getLoopNode());
    }

    @Override
    public void work() {
    }
}
