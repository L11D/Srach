package com.example.srach.interpretor;

public class VariableNode extends MathNode implements MathNodeEvaluate {
    private String name;
    private final Variables variables;
    VariableNode(Variables variables) {
        name = null;
        this.variables = variables;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Variables getVariables() {
        return variables;
    }

    @Override
    public Data evaluate() {
        switch (variables.getVariable(name).type) {
            case INT -> {
                return new Data(variables.getVariable(name).value, DataType.INT);
            }
            case BOOL -> {
                return new Data(variables.getVariable(name).value, DataType.BOOL);
            }
            case CHAR -> {
                return new Data(variables.getVariable(name).value, DataType.CHAR);
            }
            case DOUBLE -> {
                return new Data(variables.getVariable(name).value, DataType.DOUBLE);
            }
            case STRING -> {
                return new Data(variables.getVariable(name).value, DataType.STRING);
            }
            default -> throw new IllegalStateException();
        }
    }
}
