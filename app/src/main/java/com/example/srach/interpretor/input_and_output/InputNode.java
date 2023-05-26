package com.example.srach.interpretor.input_and_output;
import com.example.srach.interpretor.Data;
import com.example.srach.interpretor.DataType;
import com.example.srach.interpretor.math.MathNode;

import java.util.Scanner;

public class InputNode extends MathNode {
    private DataType type;
    public InputNode() {
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
