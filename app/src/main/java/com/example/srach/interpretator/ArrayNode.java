package com.example.srach.interpretator;
import java.util.ArrayList;

public class ArrayNode extends Node {
    private final String name;
    private final DataType type;
    private final Variables variables;

    ArrayNode(String name, DataType type, Variables storage) {
        this.name = name;
        this.type = type;
        this.variables = storage;
        this.variables.setVariableValue(name, null);
    }

    public ArrayList<String> getValue() {
        return variables.getArrayValue(name);
    }

    public DataType getType() {
        return type;
    }

    public void setValue(ArrayList<String> value) {
        variables.setArrayValue(name, value);
    }

    public String getArrayIndexValue(int index) {
        return variables.getArrayValue(name).get(index);
    }

    public String setArrayIndexValue(int index, String value) {
        return variables.getArrayValue(name).set(index, value);
    }
}
