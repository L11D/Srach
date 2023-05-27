package com.example.srach.interpretor.operators;

import com.example.srach.interpretor.Data;
import com.example.srach.interpretor.DataType;

public class LessNode extends OperatorNode {
    public LessNode(){}
    public Data evaluate() {
        if (getLeft().evaluate().type == DataType.BOOL || getRight().evaluate().type == DataType.BOOL || getLeft().evaluate().type == DataType.STRING || getRight().evaluate().type == DataType.STRING)
            throw new IllegalStateException();
        if (getLeft().evaluate().type == getRight().evaluate().type) {
            switch (getLeft().evaluate().type) {
                case INT -> {
                    return new Data(String.valueOf(Integer.parseInt(getLeft().evaluate().value) < Integer.parseInt(getRight().evaluate().value)), DataType.BOOL);
                }
                case CHAR -> {
                    return new Data(String.valueOf(getLeft().evaluate().value.charAt(0) < getRight().evaluate().value.charAt(0)), DataType.BOOL);
                }
                case DOUBLE -> {
                    return new Data(String.valueOf(Double.parseDouble(getLeft().evaluate().value) < Double.parseDouble(getRight().evaluate().value)), DataType.BOOL);
                }
                default -> throw new IllegalStateException("Данные типов " + getLeft().evaluate().type + " нельзя сравнивать");
            }
        }
        return new Data("false", DataType.BOOL);
    }
}
