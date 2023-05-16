package com.example.srach.interpretator;

public class WhileLoopNode extends LogicNode {
    private LogicNode loopBody;
    private LogicNode completed;
    private MathNodeBool condition;

    {
        loopBody = null;
        completed = null;
        condition = null;
    }

    public void setLoopBody(LogicNode loopBody) {
        this.loopBody = loopBody;
    }

    public LogicNode getLoopBody() {
        return loopBody;
    }

    public void setCompleted(LogicNode completed) {
        this.completed = completed;
    }

    public void setCondition(MathNodeBool condition) {
        this.condition = condition;
    }

    public void setEndOfLoopBody(WhileLoopNode node) {
        LogicNode currentNode = node.loopBody;
        while (node.getNext() != null) {
            currentNode = currentNode.getNext();
        }
        currentNode.setNext(node);
    }

    @Override
    public void work() {
        if (condition.evaluate()) {
            setNext(loopBody);
        } else
            setNext(completed);
    }
}
