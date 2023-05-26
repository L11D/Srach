package com.example.srach.interpretor;

import com.example.srach.interpretor.logic.LogicNode;
import com.example.srach.interpretor.math.MathNode;
import com.example.srach.interpretor.math.MathNodeEvaluate;

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

class ReturnNode extends LogicNode implements MathNodeEvaluate {


    @Override
    public Data evaluate() {
        return null;
    }

    @Override
    public void work() {

    }
}

class GetArgumentNode extends MathNode {
    private int number;
    private Function function;

    public void setNumber(int number) {
        this.number = number;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public int getNumber() {
        return number;
    }

    public Function getFunction() {
        return function;
    }

    @Override
    public Data evaluate() {
        return function.getArgument(number);
    }
}

class SetArgumentNode extends LogicNode {
    private int number;
    private MathNode data;
    private Function function;

    public void setNumber(int number) {
        this.number = number;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public void setData(MathNode data) {
        this.data = data;
    }

    public int getNumber() {
        return number;
    }

    public Function getFunction() {
        return function;
    }

    public MathNode getData() {
        return data;
    }

    @Override
    public void work() {
        function.s
    }
}

class FunctionCallNode extends MathNode {
    private String name;
    private ArrayList<Data> arguments;
    private final Storage storage;
    public FunctionCallNode(Storage storage) {
        name = null;
        arguments = new ArrayList<>(arguments);
        this.storage = storage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Storage getVariables() {
        return storage;
    }

    public ArrayList<Data> getArguments() {
        return arguments;
    }

    public Data getArgument(int number) {
        return arguments.get(number - 1);
    }

    public void setArgument(int number, Data data) {
        arguments.set(number, data);
    }

    @Override
    public Data evaluate() {
        switch (storage.getFunction(name).type) {
            case INT -> {
                return new Data(storage.getFunction(name).link, DataType.INT);
            }
            case BOOL -> {
                return new Data(storage.getFunction(name).value, DataType.BOOL);
            }
            case CHAR -> {
                return new Data(storage.getFunction(name).value, DataType.CHAR);
            }
            case DOUBLE -> {
                return new Data(storage.getFunction(name).value, DataType.DOUBLE);
            }
            case STRING -> {
                return new Data(storage.getFunction(name).value, DataType.STRING);
            }
            default -> throw new IllegalStateException();
        }
    }
}

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

