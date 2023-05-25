package com.example.srach.interpretor.cast;

import com.example.srach.interpretor.Data;
import com.example.srach.interpretor.DataType;
import com.example.srach.interpretor.math.MathNode;

public class CastNode extends MathNode {
    private MathNode castingNode;
    private DataType parseTo;
    public CastNode() {
        castingNode = null;
        parseTo = null;
    }

    public MathNode getCastingNode() {
        return castingNode;
    }

    public void setCastingNode(MathNode castingNode) {
        this.castingNode = castingNode;
    }

    public DataType getCastTo() {
        return parseTo;
    }

    public void setParseTo(DataType parseTo) {
        this.parseTo = parseTo;
    }

    @Override
    public Data evaluate() {
        switch (parseTo) {
            case INT -> {
                switch (castingNode.evaluate().type) {
                    case INT, STRING -> {
                        return new Data(castingNode.evaluate().value, DataType.INT);
                    }
                    case CHAR -> {
                        return new Data(String.valueOf((int) castingNode.evaluate().value.charAt(0)), DataType.INT);
                    }
                    case DOUBLE -> {
                        return new Data(String.valueOf((int) Double.parseDouble(castingNode.evaluate().value)), DataType.INT);
                    }
                    default -> throw new IllegalStateException();
                }
            }
            case DOUBLE -> {
                switch (castingNode.evaluate().type) {
                    case DOUBLE, STRING -> {
                        return new Data(castingNode.evaluate().value, DataType.DOUBLE);
                    }
                    case INT -> {
                        return new Data(String.valueOf((double) Integer.parseInt(castingNode.evaluate().value)), DataType.DOUBLE);
                    }
                    case CHAR -> {
                        return new Data(String.valueOf((double) castingNode.evaluate().value.charAt(0)), DataType.DOUBLE);
                    }
                    default -> throw new IllegalStateException();
                }
            }
            case BOOL -> {
                switch (castingNode.evaluate().type) {
                    case BOOL, STRING -> {
                        return new Data(castingNode.evaluate().value, DataType.BOOL);
                    }
                    default -> throw new IllegalStateException();
                }
            }
            case CHAR -> {
                switch (castingNode.evaluate().type) {
                    case CHAR, STRING -> {
                        return new Data(castingNode.evaluate().value, DataType.CHAR);
                    }
                    case DOUBLE -> {
                        return new Data(String.valueOf((char) Double.parseDouble(castingNode.evaluate().value)), DataType.CHAR);
                    }
                    case INT -> {
                        return new Data(String.valueOf((char) Integer.parseInt(castingNode.evaluate().value)), DataType.CHAR);
                    }
                    default -> throw new IllegalStateException();
                }
            }
            case STRING -> {
                switch (castingNode.evaluate().type) {
                    case CHAR, INT, BOOL, DOUBLE, STRING -> {
                        return new Data(castingNode.evaluate().value, DataType.STRING);
                    }
                    default -> throw new IllegalStateException();
                }
            }
            default -> throw new IllegalStateException();
        }
    }
}
