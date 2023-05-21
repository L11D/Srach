package com.example.srach.interpretator;
import java.util.Objects;

public class NotEqualNode extends OperatorNode {
    @Override
    public Data evaluate() {
        if (leftType == rightType) {
            switch (leftType) {
                case INT -> {
                    return new Data(String.valueOf(Integer.parseInt(leftValue) != Integer.parseInt(rightValue)), DataType.BOOL);
                }
                case CHAR -> {
                    return new Data(String.valueOf(leftValue.charAt(0) != rightValue.charAt(0)), DataType.BOOL);
                }
                case DOUBLE -> {
                    return new Data(String.valueOf(Double.parseDouble(leftValue) != Double.parseDouble(rightValue)), DataType.BOOL);
                }
                case BOOL -> {
                    return new Data(String.valueOf(Boolean.parseBoolean(leftValue) != Boolean.parseBoolean(rightValue)), DataType.BOOL);
                }
                case STRING -> {
                    return new Data((String.valueOf(!Objects.equals(leftValue, rightValue))), DataType.BOOL);
                }
                default -> throw new IllegalStateException();
            }
        }
        return new Data("false", DataType.BOOL);
    }
}
