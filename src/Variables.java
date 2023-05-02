import java.util.HashMap;
import java.util.Map;

public class Variables {
    static class Variable implements Node {
        private final String name;

        public Variable(String name) {
            this.name = name;
        }

        @Override
        public int evaluate() {
            return variables.get(name);
        }
    }

    private static Map<String, Integer> variables;

    public Variables() {
        variables = new HashMap<>();
    }

    public void setVariable(String name, int value) {
        variables.put(name, value);
    }

    public int getVariable(String name) {
        if (variables.containsKey(name))
            return variables.get(name);
        else
            throw new IllegalArgumentException("Variable " + name + " doesn't exist");
    }
}
