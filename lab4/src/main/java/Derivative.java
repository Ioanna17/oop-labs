class Derivative extends FinancialInstrument {
    private Stock baseAsset;
    private double strikePrice;

    public Derivative(String name, Stock baseAsset, double strikePrice) {
        super(name);
        this.baseAsset = baseAsset;
        this.strikePrice = strikePrice;
    }

    @Override
    public FinancialInstrument clone() {
        return new Derivative(this.getName(), this.baseAsset, this.strikePrice);
    }

    @Override
    public void simulate() {
        System.out.println(getName() + "\n==============================");
        System.out.println("Ціна базового активу: " + String.format("%.2f", baseAsset.getPrice()));
        System.out.println("Ціна виконання: " + String.format("%.2f", strikePrice));
        double profitOrLoss = baseAsset.getPrice() - strikePrice;
        System.out.println("Прибуток/Збиток: " + String.format("%.2f", profitOrLoss));
        System.out.println("==============================\n");
    }

    @Override
    public double calculateTax() {
        double profitOrLoss = baseAsset.getPrice() - strikePrice;
        double taxRate = profitOrLoss > 0 ? 0.2 : 0.0; // 20% податок на прибуток
        double tax = profitOrLoss * taxRate;
        System.out.println("Податок для деривативу: " + String.format("%.2f", tax));
        return tax;
    }
}