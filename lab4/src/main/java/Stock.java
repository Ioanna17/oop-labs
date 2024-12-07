public class Stock extends BaseInstrument {
    private double dividendYield; // Дивідендна дохідність

    public Stock(String name, double value, double dividendYield) {
        super(name, value);
        this.dividendYield = dividendYield;
    }

    public void simulatePriceChange() {
        // Імітуємо зміну ціни в діапазоні ±10%
        double change = (Math.random() - 0.5) * 0.2 * value;
        value += change;
    }

    public void payDividend() {
        double dividend = value * dividendYield;
        System.out.println("Виплачено дивіденди: " + dividend);
    }

    @Override
    public void performOperation() {
        simulatePriceChange();
        payDividend();
    }
}
