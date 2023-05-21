package com.example.srach.interpretator;

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
        Data data = null;
        switch (type) {
            case INT -> data = new Data(getValue(), DataType.INT);
            case BOOL -> data = new Data(getValue(), DataType.BOOL);
            case CHAR -> data = new Data(getValue(), DataType.CHAR);
            case DOUBLE -> data = new Data(getValue(), DataType.DOUBLE);
            case STRING -> data = new Data(getValue(), DataType.STRING);
        }
        return data;
    }
}
