package com.example.srach.interpretator;
import java.util.Scanner;

public class InputNode extends MathNode {
    private DataType type;
    InputNode() {
        type = null;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    @Override
    public Data evaluate() {
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        switch (type) {
            case INT -> {
                return new Data(String.valueOf(Integer.parseInt(value)), DataType.INT);
            }
            case BOOL -> {
                return new Data(String.valueOf(Boolean.parseBoolean(value)), DataType.BOOL);
            }
            case CHAR -> {
                return new Data(String.valueOf(value.charAt(0)), DataType.CHAR);
            }
            case DOUBLE -> {
                return new Data(String.valueOf(Double.parseDouble(value)), DataType.DOUBLE);
            }
            case STRING -> {
                return new Data(value, DataType.STRING);
            }
            default -> throw new IllegalStateException();
        }
    }
}
