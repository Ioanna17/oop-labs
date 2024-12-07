import java.util.HashMap;
import java.util.Map;

public class InstrumentManager {
    private Map<String, FinancialInstrument> prototypes = new HashMap<>();

    public void addPrototype(String key, FinancialInstrument prototype) {
        prototypes.put(key, prototype);
    }

    public FinancialInstrument createInstrument(String key) {
        return prototypes.get(key).clone();
    }
}
