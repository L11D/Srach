package com.example.srach.interpretor;

public class BreakNode extends LogicNode {
    private EndNode loopNode;

    public void setLoopNode(EndNode loopNode) {
        this.loopNode = loopNode;
        setNext(loopNode.getLoopNode().getCompleted());
    }

    @Override
    public void work(){}
}
