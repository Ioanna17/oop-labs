import java.util.HashMap;
import java.util.Map;

class FinancialInstrumentRegistry {
    private Map<String, FinancialInstrument> prototypes = new HashMap<>();

    public void addPrototype(String key, FinancialInstrument prototype) {
        prototypes.put(key, prototype);
    }

    public FinancialInstrument createPrototype(String key) {
        FinancialInstrument prototype = prototypes.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("Прототип не знайдено для ключа: " + key);
        }
        return prototype.clone();
    }
}