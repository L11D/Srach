package com.example.srach.interpretor;

public class ParseNode extends MathNode {
    private MathNode parsingNode;
    private DataType parseTo;
    ParseNode() {
        parsingNode = null;
        parseTo = null;
    }

    public MathNode getParsingNode() {
        return parsingNode;
    }

    public void setParsingNode(MathNode parsingNode) {
        this.parsingNode = parsingNode;
    }

    public DataType getParseTo() {
        return parseTo;
    }

    public void setParseTo(DataType parseTo) {
        this.parseTo = parseTo;
    }

    @Override
    public Data evaluate() {
        switch (parseTo) {
            case INT -> {
                switch (parsingNode.evaluate().type) {
                    case INT, STRING -> {
                        return new Data(parsingNode.evaluate().value, DataType.INT);
                    }
                    case CHAR -> {
                        return new Data(String.valueOf((int) parsingNode.evaluate().value.charAt(0)), DataType.INT);
                    }
                    case DOUBLE -> {
                        return new Data(String.valueOf((int) Double.parseDouble(parsingNode.evaluate().value)), DataType.INT);
                    }
                    default -> throw new IllegalStateException();
                }
            }
            case DOUBLE -> {
                switch (parsingNode.evaluate().type) {
                    case DOUBLE, STRING -> {
                        return new Data(parsingNode.evaluate().value, DataType.DOUBLE);
                    }
                    case INT -> {
                        return new Data(String.valueOf((double) Integer.parseInt(parsingNode.evaluate().value)), DataType.DOUBLE);
                    }
                    case CHAR -> {
                        return new Data(String.valueOf((double) parsingNode.evaluate().value.charAt(0)), DataType.DOUBLE);
                    }
                    default -> throw new IllegalStateException();
                }
            }
            case BOOL -> {
                switch (parsingNode.evaluate().type) {
                    case BOOL, STRING -> {
                        return new Data(parsingNode.evaluate().value, DataType.BOOL);
                    }
                    default -> throw new IllegalStateException();
                }
            }
            case CHAR -> {
                switch (parsingNode.evaluate().type) {
                    case CHAR, STRING -> {
                        return new Data(parsingNode.evaluate().value, DataType.CHAR);
                    }
                    case DOUBLE -> {
                        return new Data(String.valueOf((char) Double.parseDouble(parsingNode.evaluate().value)), DataType.CHAR);
                    }
                    case INT -> {
                        return new Data(String.valueOf((char) Integer.parseInt(parsingNode.evaluate().value)), DataType.CHAR);
                    }
                    default -> throw new IllegalStateException();
                }
            }
            case STRING -> {
                switch (parsingNode.evaluate().type) {
                    case CHAR, INT, BOOL, DOUBLE, STRING -> {
                        return new Data(parsingNode.evaluate().value, DataType.STRING);
                    }
                    default -> throw new IllegalStateException();
                }
            }
            default -> throw new IllegalStateException();
        }
    }
}
