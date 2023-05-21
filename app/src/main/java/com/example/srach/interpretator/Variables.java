package com.example.srach.interpretator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Variables {
    private final Map<String, String> variables = new HashMap<>();
    private final Map<String, ArrayList<String>> arrays = new HashMap<>();

    public String getVariableValue(String name) {
        return this.variables.get(name);
    }

    public void setVariableValue(String name, String value) {
        this.variables.put(name, value);
    }

    public ArrayList<String> getArrayValue(String name) {
        return this.arrays.get(name);
    }

    public ArrayList<String> setArrayValue(String name, ArrayList<String> value) {
        return this.arrays.get(name);
    }
}
