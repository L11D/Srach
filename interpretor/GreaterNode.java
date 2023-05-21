package com.example.srach.interpretator;

public class GreaterNode extends OperatorNode {
    @Override
    public Data evaluate() {
        if (leftType == DataType.BOOL || rightType == DataType.BOOL || leftType == DataType.STRING || rightType == DataType.STRING)
            throw new IllegalStateException();
        if (leftType == rightType) {
            switch (leftType) {
                case INT -> {
                    return new Data(String.valueOf(Integer.parseInt(leftValue) > Integer.parseInt(rightValue)), DataType.BOOL);
                }
                case CHAR -> {
                    return new Data(String.valueOf(leftValue.charAt(0) > rightValue.charAt(0)), DataType.BOOL);
                }
                case DOUBLE -> {
                    return new Data(String.valueOf(Double.parseDouble(leftValue) > Double.parseDouble(rightValue)), DataType.BOOL);
                }
                default -> throw new IllegalStateException();
            }
        }
        return new Data("false", DataType.BOOL);
    }
}
