class Stock extends FinancialInstrument {
    private double price;

    public Stock(String name, double price) {
        super(name);
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public FinancialInstrument clone() {
        return new Stock(this.getName(), this.price);
    }

    @Override
    public void simulate() {
        System.out.println(getName() + "\n==============================");
        System.out.println("Початкова ціна: " + price);
        double marketTrend = (Math.random() - 0.5) / 10;
        System.out.println("Корекція ринкової тенденції: " + String.format("%.4f", marketTrend));
        price *= 1 + marketTrend;
        System.out.println("Нова ціна: " + String.format("%.2f", price));
        System.out.println("==============================\n");
    }

    @Override
    public double calculateTax() {
        double taxRate = 0.1; // 10% податок
        double tax = price * taxRate;
        System.out.println("Податок для акції: " + String.format("%.2f", tax));
        return tax;
    }
}