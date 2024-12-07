public interface FinancialInstrument extends Cloneable {
    FinancialInstrument clone();
    void performOperation();
    double getValue();
    String getName();
}