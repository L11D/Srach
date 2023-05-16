package com.example.srach.interpritator;

public class DivideRemainderNode extends OperatorNodeInt {
    @Override
    public int evaluate() {
        return super.getLeft().evaluate() % super.getRight().evaluate();
    }
}
