package com.example.srach.interpretor.variables;

import com.example.srach.interpretor.Data;
import com.example.srach.interpretor.DataType;
import com.example.srach.interpretor.math.MathNode;
import com.example.srach.interpretor.math.MathNodeEvaluate;
import com.example.srach.interpretor.VariablesAndArraysStorage;

public class VariableNode extends MathNode implements MathNodeEvaluate {
    private String name;
    private final VariablesAndArraysStorage variablesAndArraysStorage;
    public VariableNode(VariablesAndArraysStorage variablesAndArraysStorage) {
        name = null;
        this.variablesAndArraysStorage = variablesAndArraysStorage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public VariablesAndArraysStorage getVariables() {
        return variablesAndArraysStorage;
    }

    @Override
    public Data evaluate() {
        switch (variablesAndArraysStorage.getVariable(name).type) {
            case INT -> {
                return new Data(variablesAndArraysStorage.getVariable(name).value, DataType.INT);
            }
            case BOOL -> {
                return new Data(variablesAndArraysStorage.getVariable(name).value, DataType.BOOL);
            }
            case CHAR -> {
                return new Data(variablesAndArraysStorage.getVariable(name).value, DataType.CHAR);
            }
            case DOUBLE -> {
                return new Data(variablesAndArraysStorage.getVariable(name).value, DataType.DOUBLE);
            }
            case STRING -> {
                return new Data(variablesAndArraysStorage.getVariable(name).value, DataType.STRING);
            }
            default -> throw new IllegalStateException();
        }
    }
}
