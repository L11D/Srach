package com.example.srach.interpretator;
public class MinusNode extends OperatorNodeInt {
    @Override
    public int evaluate() {
        return super.getLeft().evaluate() - super.getRight().evaluate();
    }
}