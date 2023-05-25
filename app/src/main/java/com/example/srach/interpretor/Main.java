package com.example.srach.interpretor;

public class Main {
    public static void main(String[] args) {
        Variables variables = new Variables();

        WhileLoopNode loop = new WhileLoopNode();
        BeginNode begin = new BeginNode();
        EndNode end = new EndNode();
        VariableNode a = new VariableNode("a", DataType.INT, variables);
        StartNode start = new StartNode();
        PrintNode print = new PrintNode();
        AssignmentNode ass = new AssignmentNode();
        ValueNode num = new ValueNode();
        AddNode add = new AddNode();

        start.setNext(loop);
        loop.setLoopBegin(begin);
        begin.setNext(ass);
        end.setLoopNode(loop);
        ass.setVariable(a);
        ass.setEvaluateResult(add);
        add.setLeft(a);
        add.setRight(num);
        num.setValue(new Data("1", DataType.INT));
        print.setEvaluateResult(a);
        ass.setNext(print);

        goLogic(start);
    }

    public static void goLogic(LogicNode node) {
        if (node.getNext() != null) {
            node.getNext().work();
            goLogic(node.getNext());
        }
    }
}



