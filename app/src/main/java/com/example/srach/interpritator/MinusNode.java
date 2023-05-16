package com.example.srach.interpritator;
public class MinusNode extends OperatorNodeInt {
    @Override
    public int evaluate() {
        return super.getLeft().evaluate() - super.getRight().evaluate();
    }
}
