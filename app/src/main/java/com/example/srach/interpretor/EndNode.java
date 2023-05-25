package com.example.srach.interpretor;

public class EndNode extends LogicNode {
    private WhileLoopNode loopNode;

    public void setLoopNode(WhileLoopNode loopNode) {
        this.loopNode = loopNode;
    }

    public WhileLoopNode getLoopNode() {
        return loopNode;
    }

    @Override
    public void work() {
    }
}
