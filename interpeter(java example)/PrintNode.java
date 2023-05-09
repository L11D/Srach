public class PrintNode extends LogicNode {
    private MathNode evaluateResult;

    public void setEvaluateResult(MathNode evaluateResult) {
        this.evaluateResult = evaluateResult;
    }

    public void print() {
        System.out.println(evaluateResult.evaluate());
    }

    @Override
    public void work() {
        print();
    }
}
