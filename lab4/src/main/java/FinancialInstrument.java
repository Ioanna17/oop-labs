public interface FinancialInstrument extends Cloneable {
    String getName();
    double getValue();
    FinancialInstrument clone();
    void performOperation(int year);
}