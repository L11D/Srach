package com.example.srach.interpretor.operators;

import com.example.srach.interpretor.Data;
import com.example.srach.interpretor.DataType;

import java.util.Objects;

public class EqualNode extends OperatorNode {
    public EqualNode(){}
    @Override
    public Data evaluate() {
        if (getLeft().evaluate().type == getRight().evaluate().type) {
            switch (getLeft().evaluate().type) {
                case INT -> {
                    return new Data(String.valueOf(Integer.parseInt(getLeft().evaluate().value) == Integer.parseInt(getRight().evaluate().value)), DataType.BOOL);
                }
                case CHAR -> {
                    return new Data(String.valueOf(getLeft().evaluate().value.charAt(0) == getRight().evaluate().value.charAt(0)), DataType.BOOL);
                }
                case DOUBLE -> {
                    return new Data(String.valueOf(Double.parseDouble(getLeft().evaluate().value) == Double.parseDouble(getRight().evaluate().value)), DataType.BOOL);
                }
                case BOOL -> {
                    return new Data(String.valueOf(Boolean.parseBoolean(getLeft().evaluate().value) == Boolean.parseBoolean(getRight().evaluate().value)), DataType.BOOL);
                }
                case STRING -> {
                    return new Data((String.valueOf(Objects.equals(getLeft().evaluate().value, getRight().evaluate().value))), DataType.BOOL);
                }
                default -> throw new IllegalStateException();
            }
        }
        return new Data("false", DataType.BOOL);
    }
}
