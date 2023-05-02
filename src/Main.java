public class Main {
    public static void main(String[] args) {
        Variables variables = new Variables();
        variables.setVariable("a", 30);
        System.out.println(variables.getVariable("a"));
        System.out.println(new Add(new Number(10), new Variables.Variable("a")).evaluate());
        System.out.println(new Multiplication(new Number(10), new Number(12)).evaluate());
        System.out.println(new Minus(new Number(10), new Number(12)).evaluate());
        System.out.println(new DivisionInteger(new Number(10), new Number(12)).evaluate());
        System.out.println(new RemainderOfDivision(new Number(10), new Number(12)).evaluate());
    }
}







