package com.example.srach.interpretator;

public class AddNode extends OperatorNodeInt {
    @Override
    public int evaluate() {
        return super.getLeft().evaluate() + super.getRight().evaluate();
    }
}
