package com.example.srach.interpretor;

public class SubtractNode extends OperatorNode {
    @Override
    public Data evaluate() {
        if (getLeft().evaluate().type == DataType.BOOL || getRight().evaluate().type == DataType.BOOL || getLeft().evaluate().type == DataType.STRING || getRight().evaluate().type == DataType.STRING) {
            throw new IllegalStateException();
        }
        if (getLeft().evaluate().type == getRight().evaluate().type) {
            switch (getLeft().evaluate().type) {
                case INT -> {
                    return new Data(String.valueOf(Integer.parseInt(getLeft().evaluate().value) - Integer.parseInt(getRight().evaluate().value)), DataType.INT);
                }
                case CHAR -> {
                    return new Data(String.valueOf(getLeft().evaluate().value.charAt(0) - getRight().evaluate().value.charAt(0)), DataType.INT);
                }
                case DOUBLE -> {
                    return new Data(String.valueOf(Double.parseDouble(getLeft().evaluate().value) - Double.parseDouble(getRight().evaluate().value)), DataType.DOUBLE);
                }
                default -> throw new IllegalStateException();
            }
        }
        switch (getLeft().evaluate().type) {
            case DOUBLE -> {
                switch (getRight().evaluate().type) {
                    case INT -> {
                        return new Data(String.valueOf(Double.parseDouble(getLeft().evaluate().value) - Integer.parseInt(getRight().evaluate().value)), DataType.DOUBLE);
                    }
                    case CHAR -> {
                        return new Data(String.valueOf(Double.parseDouble(getLeft().evaluate().value) - getRight().evaluate().value.charAt(0)), DataType.DOUBLE);
                    }
                    default -> throw new IllegalStateException();
                }
            }
            case CHAR -> {
                switch (getRight().evaluate().type) {
                    case INT -> {
                        return new Data(String.valueOf(getLeft().evaluate().value.charAt(0) - Integer.parseInt(getRight().evaluate().value)), DataType.INT);
                    }
                    case DOUBLE -> {
                        return new Data(String.valueOf(getLeft().evaluate().value.charAt(0) - Double.parseDouble(getRight().evaluate().value)), DataType.DOUBLE);
                    }
                    default -> throw new IllegalStateException();
                }
            }
            case INT -> {
                switch (getRight().evaluate().type) {
                    case CHAR -> {
                        return new Data(String.valueOf(Integer.parseInt(getLeft().evaluate().value) - getLeft().evaluate().value.charAt(0)), DataType.INT);
                    }
                    case DOUBLE -> {
                        return new Data(String.valueOf(Integer.parseInt(getLeft().evaluate().value) - Double.parseDouble(getRight().evaluate().value)), DataType.DOUBLE);
                    }
                    default -> throw new IllegalStateException();
                }
            }
            default -> throw new IllegalStateException();
        }
    }
}