//package com.example.srach.interpretor;
//
//public class WhileLoopNode extends LogicNode {
//    private BeginEnd loopBody;
//    private LogicNode completed;
//    private MathNode condition;
//
//    {
//        loopBody = null;
//        completed = null;
//        condition = null;
//    }
//
//    public void setLoopBody(BeginEnd loopBody) {
//        this.loopBody = loopBody;
//    }
//
//    public BeginEnd getLoopBody() {
//        return loopBody;
//    }
//
//    public void setCompleted(LogicNode completed) {
//        this.completed = completed;
//    }
//
//    public LogicNode getCompleted() {
//        return completed;
//    }
//
//    public void setCondition(MathNode condition) {
//        this.condition = condition;
//    }
//
//    public void setEndOfLoopBody(WhileLoopNode node) {
//        LogicNode currentNode = node.loopBody;
//        while (node.getNext() != null) {
//            currentNode = currentNode.getNext();
//        }
//        currentNode.setNext(node);
//    }
//
//    @Override
//    public void work() {
//        if (condition.evaluate().type == DataType.BOOL)
//            if (Boolean.parseBoolean(condition.evaluate().value))
//                setNext(loopBody.getBegin());
//            else
//                setNext(completed);
//    }
//}
