public class Derivative extends BaseInstrument {
    private FinancialInstrument underlyingAsset;

    public Derivative(String name, double value, FinancialInstrument underlyingAsset) {
        super(name, value);
        this.underlyingAsset = underlyingAsset;
    }

    public void calculateValue() {
        double newValue = underlyingAsset.getValue() * 1.1; // Наприклад, 10% премія
        System.out.println("Розрахована вартість деривативу: " + newValue);
    }

    @Override
    public void performOperation() {
        calculateValue();
    }
}
