package com.example.srach.interpretor.functions;

import com.example.srach.interpretor.Data;
import com.example.srach.interpretor.DataType;
import com.example.srach.interpretor.logic.LogicNode;

import java.util.ArrayList;

public class Function {
    private DataType type;
    private int amountArguments;
    private ArrayList<Data> arguments;
    private LogicNode link;
    private Data returnedData;
    public Function(DataType type, int amountArguments, LogicNode link) {
        this.type = type;
        this.amountArguments = amountArguments;
        arguments = null;
        this.link = link;
    }

    public DataType getType() {
        return type;
    }

    public int getAmountArguments() {
        return amountArguments;
    }

    public LogicNode getLink() {
        return link;
    }

    public Data getReturnedData() {
        return returnedData;
    }

    public void setReturnedData(Data returnedData) {
        this.returnedData = returnedData;
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
