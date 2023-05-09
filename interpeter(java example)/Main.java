public class Main {
    public static void main(String[] args) {
        Variables storage = new Variables();
        VariableNode a = new VariableNode("a", storage);
        a.setValue(25);

        StartLogicNode start = new StartLogicNode();
        PrintNode node1 = new PrintNode();
        PrintNode node2 = new PrintNode();

        NumberNode num1 = new NumberNode();
        num1.setValue(30);
        NumberNode num2 = new NumberNode();
        num2.setValue(10);

        AddNode sum1 = new AddNode();
        sum1.setLeft(num1);
        sum1.setRight(num2);

        AddNode sum2 = new AddNode();
        sum2.setLeft(a);
        sum2.setRight(sum1);

        AssignmentNode ass1 = new AssignmentNode();
        ass1.setName(a);

        node1.setEvaluateResult(sum1);
        ass1.setEvaluateResult(sum2);
        node2.setEvaluateResult(a);

        start.setNext(node1);
        node1.setNext(ass1);
        ass1.setNext(node2);

        goLogic(start);
    }

    public static void goLogic(LogicNode node) {
        if (node.getNext() != null) {
            node.getNext().work();
            goLogic(node.getNext());
        }
        else
            System.out.print("End");
    }
}

