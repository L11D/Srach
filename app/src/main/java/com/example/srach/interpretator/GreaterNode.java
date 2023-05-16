package com.example.srach.interpretator;

public class GreaterNode extends OperatorNodeBool {
    @Override
    public boolean evaluate() {
        return super.getLeft().evaluate() > super.getRight().evaluate();
    }
}
