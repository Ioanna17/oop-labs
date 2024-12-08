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
    public void performOperation(int year) {
        double interest = value * annualInterestRate;
        System.out.println("Year " + year + ": Bond " + name + " pays interest: " + interest);
        // Optionally, update the bond's value over time
    }
}
