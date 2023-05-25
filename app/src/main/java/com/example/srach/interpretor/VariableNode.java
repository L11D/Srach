package com.example.srach.interpretor;

public class VariableNode extends MathNode implements MathNodeEvaluate {
    private final String name;
    private final DataType type;
    private final Variables variables;
    VariableNode(String name, DataType type, Variables storage) {
        this.name = name;
        this.type = type;
        this.variables = storage;
        this.variables.setVariableValue(name, null);
    }

    public String getValue() {
        return variables.getVariableValue(name);
    }

    public DataType getType() {
        return type;
    }

    public void setValue(String value) {
        variables.setVariableValue(name, value);
    }

    @Override
    public Data evaluate() {
        switch (type) {
            case INT -> {
                return new Data(getValue(), DataType.INT);
            }
            case BOOL -> {
                return new Data(getValue(), DataType.BOOL);
            }
            case CHAR -> {
                return new Data(getValue(), DataType.CHAR);
            }
            case DOUBLE -> {
                return new Data(getValue(), DataType.DOUBLE);
            }
            case STRING -> {
                return new Data(getValue(), DataType.STRING);
            }
            default -> throw new IllegalStateException();
        }
    }
}
