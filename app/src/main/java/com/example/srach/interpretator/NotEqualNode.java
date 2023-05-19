package com.example.srach.interpretator;

public class NotEqualNode extends OperatorNodeBool {
    @Override
    public boolean evaluate() {
        return super.getLeft().evaluate() != super.getRight().evaluate();
    }
}