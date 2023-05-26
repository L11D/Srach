package com.example.srach.interpretor;

import com.example.srach.interpretor.logic.LogicNode;

public class Main {
    public static void main(String[] args) {
//        VariablesAndArraysStorage variables = new VariablesAndArraysStorage();
//        ArrayList listOfVars = new ArrayList<>();
//
//        WhileLoopNode loop = new WhileLoopNode();
//        BeginNode begin = new BeginNode();
//        EndNode end = new EndNode();
//        VariableNode a = new VariableNode("a", DataType.INT, variables);
//        StartNode start = new StartNode();
//        PrintNode print = new PrintNode();
//        AssignmentNode ass = new AssignmentNode();
//        ValueNode num = new ValueNode();
//        AddNode add = new AddNode();
//
//        start.setNext(loop);
//        loop.setLoopBegin(begin);
//        begin.setNext(ass);
//        end.setLoopNode(loop);
//        ass.setVariable(a);
//        ass.setEvaluateResult(add);
//        add.setLeft(a);
//        add.setRight(num);
//        num.setValue(new Data("1", DataType.INT));
//        print.setEvaluateResult(a);
//        ass.setNext(print);
//
//        goLogic(start);
    }

    public static void goLogic(LogicNode node) {
        if (node.getNext() != null) {
            node.getNext().work();
            goLogic(node.getNext());
        }
    }
}

//class DeclarationFunctionNode extends LogicNode {
//    private String name;
//    private DataType type;
//    private final VariablesAndArraysStorage variablesAndArraysStorage;
//    public DeclarationVariableNode(VariablesAndArraysStorage variablesAndArraysStorage) {
//        this.name = null;
//        this.type = null;
//        this.variablesAndArraysStorage = variablesAndArraysStorage;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setType(DataType type) {
//        this.type = type;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public DataType getType() {
//        return type;
//    }
//
//    @Override
//    public void work() {
//        variablesAndArraysStorage.setVariable(name, null, type);
//    }
//}

