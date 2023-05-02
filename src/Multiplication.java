public class Multiplication implements Node {
    private final Node left;
    private final Node right;

    public Multiplication(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate() {
        return left.evaluate() * right.evaluate();
    }
}
