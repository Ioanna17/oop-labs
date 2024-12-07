public class Bond extends BaseInstrument {
    private double annualInterestRate;

    public Bond(String name, double value, double annualInterestRate) {
        super(name, value);
        this.annualInterestRate = annualInterestRate;
    }

    public void calculateAnnualYield() {
        double yield = value * annualInterestRate;
        System.out.println("Річний дохід від облігації: " + yield);
    }

    @Override
    public void performOperation() {
        calculateAnnualYield();
    }
}
