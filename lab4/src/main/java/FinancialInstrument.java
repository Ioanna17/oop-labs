abstract class FinancialInstrument implements Cloneable {
    private String name;

    public FinancialInstrument(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract FinancialInstrument clone();

    public abstract void simulate();

    public abstract double calculateTax();
}