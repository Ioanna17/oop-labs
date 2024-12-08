//public class Main {
//    public static void main(String[] args) {
//        // Створення менеджера
//        InstrumentManager manager = new InstrumentManager();
//
//        // Додавання прототипів
//        manager.addPrototype("stock", new Stock("Apple", 150.0, 0.02));
//        manager.addPrototype("bond", new Bond("Government Bond", 1000.0, 0.05));
//        manager.addPrototype("derivative", new Derivative("Option", 0.0, new Stock("Tesla", 200.0, 0.03)));
//
//        // Створення об'єктів
//        FinancialInstrument stock = manager.createInstrument("stock");
//        FinancialInstrument bond = manager.createInstrument("bond");
//        FinancialInstrument derivative = manager.createInstrument("derivative");
//
//        // Виконання операцій
//        stock.performOperation();
//        bond.performOperation();
//        derivative.performOperation();
//    }
//}
