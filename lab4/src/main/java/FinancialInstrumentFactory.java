import java.util.HashMap;
import java.util.Map;

public class FinancialInstrumentFactory {
    private Map<String, FinancialInstrument> prototypes = new HashMap<>();

    public void registerPrototype(String type, FinancialInstrument prototype) {
        prototypes.put(type, prototype);
    }

    public FinancialInstrument createInstrument(String type) {
        FinancialInstrument prototype = prototypes.get(type);
        if (prototype == null) {
            throw new IllegalArgumentException("Prototype for type " + type + " not found");
        }
        return prototype.clone();
    }

}
