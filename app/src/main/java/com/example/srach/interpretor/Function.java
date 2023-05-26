package com.example.srach.interpretor;

import com.example.srach.interpretor.logic.LogicNode;

import java.util.ArrayList;

public class Function {
    public DataType type;
    public int amountArguments;
    private ArrayList<Data> arguments;
    public LogicNode link;
    public Function(DataType type, int amountArguments, LogicNode link) {
        this.type = type;
        this.amountArguments = amountArguments;
        arguments = null;
        this.link = link;
    }

    public ArrayList<Data> getArguments() {
        return arguments;
    }

    public Data getArgument(int number) {
        return arguments.get(number - 1);
    }

    public void setArguments(ArrayList<Data> arguments) {
        this.arguments = arguments;
    }

    public void setArgument(int number, Data data) {
        this.arguments.set(number - 1, data);
    }
}
