package com.example.srach.interpretor;

public class Data {
    String value;
    DataType type;
    public DataType getType(){return type;}
    Data(String value, DataType type) {
        this.value = value;
        this.type = type;
    }
}
