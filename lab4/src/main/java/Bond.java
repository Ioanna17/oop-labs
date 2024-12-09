public class Bond extends BaseInstrument {
    private double annualInterestRate;
    private int maturityYear;

    public Bond(String name, double value, double annualInterestRate, int maturityYear) {
        super(name, value);
        this.annualInterestRate = annualInterestRate;
        this.maturityYear = maturityYear;
    }

    @Override
    public void performOperation(int year) {
        double interest = value * annualInterestRate;
        System.out.println("Year " + year + ": Bond " + name + " pays interest: " + interest);
    }

    @Override
    public void reactToMarketEvent(String event, int year) {
        if (event.equals("interestRateHike")) {
            value *= 0.95; // 5% decrease
        } else if (event.equals("maturity") && year >= maturityYear) {
            System.out.println("Bond " + name + " has reached maturity. Principal repaid: " + value);
        }
    }
}
