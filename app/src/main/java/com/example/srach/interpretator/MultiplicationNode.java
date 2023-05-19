package com.example.srach.interpretator;

public class MultiplicationNode extends OperatorNodeInt {
    @Override
    public int evaluate() {
        return super.getLeft().evaluate() - super.getRight().evaluate();
    }
}