package com.example.srach.interpretor.variables;

import com.example.srach.interpretor.Data;
import com.example.srach.interpretor.DataType;
import com.example.srach.interpretor.math.MathNode;
import com.example.srach.interpretor.math.MathNodeEvaluate;
import com.example.srach.interpretor.Storage;

public class VariableNode extends MathNode implements MathNodeEvaluate {
    private String name;
    private final Storage storage;
    public VariableNode(Storage storage) {
        name = null;
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

    @Override
    public Data evaluate() {
        switch (storage.getVariable(name).type) {
            case INT -> {
                return new Data(storage.getVariable(name).value, DataType.INT);
            }
            case BOOL -> {
                return new Data(storage.getVariable(name).value, DataType.BOOL);
            }
            case CHAR -> {
                return new Data(storage.getVariable(name).value, DataType.CHAR);
            }
            case DOUBLE -> {
                return new Data(storage.getVariable(name).value, DataType.DOUBLE);
            }
            case STRING -> {
                return new Data(storage.getVariable(name).value, DataType.STRING);
            }
            default -> throw new IllegalStateException("Тип данных " + storage.getVariable(name).type + " не может быть использован в вычислениях");
        }
    }
}
