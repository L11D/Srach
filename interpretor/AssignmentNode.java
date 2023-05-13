public class AssignmentNode extends LogicNode {
    private VariableNode variable;
    private MathNodeInt evaluateResult;

    public void setName(VariableNode variable) {
        this.variable = variable;
    }

    public void setEvaluateResult(MathNodeInt evaluateResult) {
        this.evaluateResult = evaluateResult;
    }

    public void assignment() {
        variable.setValue(evaluateResult.evaluate());
    }

    @Override
    public void work() {
        assignment();
    }
}
