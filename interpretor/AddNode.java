package com.example.srach.interpretor;

public class AddNode extends OperatorNode {
    @Override
    public Data evaluate() {
        if (leftType == DataType.BOOL || rightType == DataType.BOOL) {
            throw new IllegalStateException();
        }
        if (leftType == rightType) {
            switch (leftType) {
                case INT -> {
                    return new Data(String.valueOf(Integer.parseInt(leftValue) + Integer.parseInt(rightValue)), DataType.INT);
                }
                case CHAR -> {
                    return new Data(String.valueOf(leftValue.charAt(0) + rightValue.charAt(0)), DataType.INT);
                }
                case DOUBLE -> {
                    return new Data(String.valueOf(Double.parseDouble(leftValue) + Double.parseDouble(rightValue)), DataType.DOUBLE);
                }
                case STRING -> {
                    return new Data(leftValue + rightValue, DataType.STRING);
                }
                default -> throw new IllegalStateException();
            }
        }
        switch (leftType) {
            case DOUBLE -> {
                switch (rightType) {
                    case INT -> {
                        return new Data(String.valueOf(Double.parseDouble(leftValue) + Integer.parseInt(rightValue)), DataType.DOUBLE);
                    }
                    case CHAR -> {
                        return new Data(String.valueOf(Double.parseDouble(leftValue) + rightValue.charAt(0)), DataType.DOUBLE);
                    }
                    default -> throw new IllegalStateException();
                }
            }
            case CHAR -> {
                switch (rightType) {
                    case INT -> {
                        return new Data(String.valueOf(leftValue.charAt(0) + Integer.parseInt(rightValue)), DataType.INT);
                    }
                    case DOUBLE -> {
                        return new Data(String.valueOf(leftValue.charAt(0) + Double.parseDouble(rightValue)), DataType.DOUBLE);
                    }
                    default -> throw new IllegalStateException();
                }
            }
            case INT -> {
                switch (rightType) {
                    case CHAR -> {
                        return new Data(String.valueOf(Integer.parseInt(leftValue) + leftValue.charAt(0)), DataType.INT);
                    }
                    case DOUBLE -> {
                        return new Data(String.valueOf(Integer.parseInt(leftValue) + Double.parseDouble(rightValue)), DataType.DOUBLE);
                    }
                    default -> throw new IllegalStateException();
                }
            }
            default -> throw new IllegalStateException();
        }
    }
}
