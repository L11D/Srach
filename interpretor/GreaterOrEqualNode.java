public class GreaterOrEqualNode extends OperatorNodeBool {
    @Override
    public boolean evaluate() {
        return super.getLeft().evaluate() >= super.getRight().evaluate();
    }
}
