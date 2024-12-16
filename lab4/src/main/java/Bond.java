class Bond extends FinancialInstrument {
    private double principal;
    private double annualRate;

    public Bond(String name, double principal, double annualRate) {
        super(name);
        this.principal = principal;
        this.annualRate = annualRate;
    }

    @Override
    public FinancialInstrument clone() {
        return new Bond(this.getName(), this.principal, this.annualRate);
    }

    @Override
    public void simulate() {
        System.out.println(getName() + "\n==============================");
        System.out.println("Основна сума: " + principal);
        System.out.println("Річна ставка: " + String.format("%.2f", annualRate * 100) + "%");
        double interest = principal * annualRate;
        System.out.println("Річний дохід: " + String.format("%.2f", interest));
        System.out.println("==============================\n");
    }

    @Override
    public double calculateTax() {
        double taxRate = 0.05; // 5% податок
        double tax = principal * annualRate * taxRate;
        System.out.println("Податок для облігації: " + String.format("%.2f", tax));
        return tax;
    }
}