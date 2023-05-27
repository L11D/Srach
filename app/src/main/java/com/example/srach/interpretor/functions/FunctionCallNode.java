package com.example.srach.interpretor.functions;

import com.example.srach.interpretor.Data;
import com.example.srach.interpretor.DataType;
import com.example.srach.interpretor.Storage;
import com.example.srach.interpretor.math.MathNode;

import java.util.ArrayList;

public class FunctionCallNode extends MathNode {
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
        switch (storage.getFunction(name).getReturnedData().type) {
            case INT -> {
                return new Data(storage.getFunction(name).getReturnedData().value, DataType.INT);
            }
            case BOOL -> {
                return new Data(storage.getFunction(name).getReturnedData().value, DataType.BOOL);
            }
            case CHAR -> {
                return new Data(storage.getFunction(name).getReturnedData().value, DataType.CHAR);
            }
            case DOUBLE -> {
                return new Data(storage.getFunction(name).getReturnedData().value, DataType.DOUBLE);
            }
            case STRING -> {
                return new Data(storage.getFunction(name).getReturnedData().value, DataType.STRING);
            }
            default -> throw new IllegalStateException();
        }
    }
}
