public class Derivative extends BaseInstrument {
    private FinancialInstrument underlyingAsset;
    private int expirationYear;

    public Derivative(String name, double value, FinancialInstrument underlyingAsset, int expirationYear) {
        super(name, value);
        this.underlyingAsset = underlyingAsset;
        this.expirationYear = expirationYear;
    }

    public void calculateValue() {
        double newValue = underlyingAsset.getValue() * 1.1; // Наприклад, 10% премія
        System.out.println("Розрахована вартість деривативу: " + newValue);
    }

    @Override
    public void performOperation(int year) {
        calculateValue();
        System.out.println("Year: " + year);
    }

    @Override
    public void reactToMarketEvent(String event, int year) {
        if (event.equals("underlyingPriceIncrease")) {
            value = underlyingAsset.getValue() * 1.1; // Adjust based on underlying asset
        } else if (event.equals("expiration") && year >= expirationYear) {
            // Handle expiration logic
        }
    }
}
