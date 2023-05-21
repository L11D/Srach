package com.example.srach.interpretor;

public class Main {
    public static void main(String[] args) {
        Variables variables = new Variables();

        VariableNode a = new VariableNode("a", DataType.DOUBLE, variables);
        BeginNode start = new BeginNode();
        PrintNode print1 = new PrintNode();
        PrintNode print2 = new PrintNode();
        AssignmentNode ass = new AssignmentNode();
        NumberNode num = new NumberNode();
        AddNode add = new AddNode();

        a.setValue("150.0");
        num.setValue("423.52");
        start.setNext(print1);
        print1.setNext(ass);
        ass.setNext(print2);
        ass.setVariable(a);
        ass.setEvaluateResult(num);
        print2.setEvaluateResult(add);
        print1.setEvaluateResult(a);
        add.setLeft(a);
        add.setRight(num);



        goLogic(start);
    }

//    public static void checkLoops(LogicNode node) {
//        if (node.getNext() instanceof WhileLoopNode) {
//            ((WhileLoopNode) node.getNext()).setEndOfLoopBody((WhileLoopNode) node.getNext());
//            checkLoops(node.getNext());
//        }
//    }

    public static void goLogic(LogicNode node) {
        if (node.getNext() != null) {
            node.getNext().work();
            goLogic(node.getNext());
        }
        else
            System.out.print("End");
    }
}

//class Break extends LogicNode {
//    WhileLoopNode loopNode;
//
//    public void setOwner(WhileLoopNode owner) {
//        this.loopNode = owner;
//    }
//
//    @Override
//    public void work() {
//        loopNode.setNext(loopNode.getCompleted());
//    }
//}
//
//class BeginEnd {
//    LogicNode owner;
//    LogicNode begin;
//    LogicNode end;
//    BeginEnd() {
//        owner = null;
//        begin = null;
//        end = null;
//    }
//
//    public void setOwner(LogicNode owner) {
//        this.owner = owner;
//    }
//
//    public void setBegin(LogicNode begin) {
//        this.begin = begin;
//    }
//
//    public void setEnd(LogicNode end) {
//        this.end = end;
//    }
//
//    public LogicNode getBegin() {
//        return begin;
//    }
//}



