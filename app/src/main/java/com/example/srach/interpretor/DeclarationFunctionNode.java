package com.example.srach.interpretor;

import com.example.srach.interpretor.logic.LogicNode;

import java.util.ArrayList;

public class DeclarationFunctionNode extends LogicNode {
    private String name;
    private DataType type;
    private Integer amountArguments;
    private LogicNode returnData;
    private final Storage storage;
    public DeclarationFunctionNode(Storage storage) {
        name = null;
        type = null;
        amountArguments = null;
        this.storage = storage;
        returnData = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public DataType getType() {
        return type;
    }

    public void setAmountArguments(Integer amountArguments) {
        this.amountArguments = amountArguments;
    }

    public Integer getAmountArguments() {
        return amountArguments;
    }

    public void setReturnData(LogicNode returnData) {
        this.returnData = returnData;
    }

    public LogicNode getReturnData() {
        return returnData;
    }

    @Override
    public void work() {
        storage.setFunction(name, type, amountArguments, this);
    }
}
