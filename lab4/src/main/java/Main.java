public class Main {
    public static void main(String[] args) {
        InstrumentManager manager = new InstrumentManager();

        manager.addPrototype("stock", new Stock("Apple", 150.0, 0.02));
        manager.addPrototype("bond", new Bond("Government Bond", 1000.0, 0.05, 2030));
        manager.addPrototype("derivative", new Derivative("Option", 0.0,
                new Stock("Tesla", 200.0, 0.03), 2025));

        FinancialInstrument stock = manager.createInstrument("stock");
        FinancialInstrument bond = manager.createInstrument("bond");
        FinancialInstrument derivative = manager.createInstrument("derivative");

    }
}
