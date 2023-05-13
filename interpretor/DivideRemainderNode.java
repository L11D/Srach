public class DivideRemainderNode extends OperatorNodeInt {
    @Override
    public int evaluate() {
        return super.getLeft().evaluate() % super.getRight().evaluate();
    }
}
