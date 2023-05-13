import java.util.HashMap;
import java.util.Map;

public class Variables {
    private final Map<String, Integer> storage = new HashMap<>();

    public int getVariableValue(String name) {
        return this.storage.get(name);
    }

    public void setVariableValue(String name, int value) {
        this.storage.put(name, value);
    }
}
