public class FinancialModelingSystem {
    public static void main(String[] args) {
        FinancialInstrumentRegistry registry = new FinancialInstrumentRegistry();

        // Реєстрація прототипів
        Stock defaultStock = new Stock("акція", 100.0);
        registry.addPrototype("stock", defaultStock);
        registry.addPrototype("bond", new Bond("облігація", 1000.0, 0.05));
        registry.addPrototype("derivative", new Derivative("дериватив", defaultStock, 95.0));

        // Створення та симуляція інструментів
        FinancialInstrument stock = registry.createPrototype("stock");
        stock.simulate();
        stock.calculateTax();

        FinancialInstrument bond = registry.createPrototype("bond");
        bond.simulate();
        bond.calculateTax();

        FinancialInstrument derivative = registry.createPrototype("derivative");
        derivative.simulate();
        derivative.calculateTax();

    }
}