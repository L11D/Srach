package com.example.srach.interpretor;

import com.example.srach.interpretor.logic.LogicNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kotlin.Pair;

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

//class Function {
//    public DataType type;
//    public int amountArguments;
//    public LogicNode link;
//}

//class FunctionsStorage {
//    private final Map<String, Pair<DataType, Integer>> functions = new HashMap<>();
//    public FunctionsStorage(){}
//
//    public Data getVariable(String name) {
//        return this.variables.get(name);
//    }
//
//    public void setVariable(String name, String value, DataType type) {
//        this.variables.put(name, new Data(value, type));
//    }
//
//    public Pair<ArrayList<String>, DataType> getArray(String name) {
//        return this.arrays.get(name);
//    }
//
//    public void setArray(String name, ArrayList<String> array, DataType type) {
//        this.arrays.put(name, new Pair<>(array, type));
//    }
//}

//class DeclarationFunctionNode {
//    private String name;
//    private DataType type;
//    private final FunctionsStorage functionsStorage;
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

