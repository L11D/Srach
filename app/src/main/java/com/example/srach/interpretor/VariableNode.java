package com.example.srach.interpretor;

public class VariableNode extends MathNode implements MathNodeEvaluate {


    private String name;
    private DataType type;
    private final com.example.srach.interpretor.Variables variables;
    public VariableNode(String name, DataType type, Variables storage) {
        this.name = name;
        this.type = type;
        this.variables = storage;
        //this.variables.setVariableValue(name, null);
    }

    public String getValue() {
        return variables.getVariableValue(name);
    }
    public void setValue(String value) {
        variables.setVariableValue(name, value);
    }

    public DataType getType() {
        return type;
    }
    public void setType(DataType type) {this.type = type;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Override
    public Data evaluate() {
        variables.setVariableValue(name, null);
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
