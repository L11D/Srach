package com.example.srach.interpritator;

public class Main {
    public static void main(String[] args) {
        Variables vars = new Variables();

        VariableNode a = new VariableNode("a", vars);
        StartLogicNode start = new StartLogicNode();
        LessNode less = new LessNode();
        AssignmentNode ass = new AssignmentNode();
        AddNode add = new AddNode();
        NumberNode num1 = new NumberNode();
        num1.setValue(1);
        NumberNode num50 = new NumberNode();
        num50.setValue(50);
        PrintNode print = new PrintNode();
        WhileLoopNode loop = new WhileLoopNode();

        start.setNext(loop);
        add.setLeft(a);
        add.setRight(num1);
        ass.setVariable(a);
        ass.setEvaluateResult(add);
        loop.setLoopBody(ass);
        print.setEvaluateResult(a);
        loop.setCompleted(print);
        less.setLeft(a);
        less.setRight(num50);
        loop.setCondition(less);

        checkLoops(start);
        goLogic(start);
    }

    public static void checkLoops(LogicNode node) {
        if (node.getNext() instanceof WhileLoopNode) {
            ((WhileLoopNode) node.getNext()).setEndOfLoopBody((WhileLoopNode) node.getNext());
            checkLoops(node.getNext());
        }
    }

    public static void goLogic(LogicNode node) {
        if (node.getNext() != null) {
            node.getNext().work();
            goLogic(node.getNext());
        }
        else
            System.out.print("End");
    }
}

