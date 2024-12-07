public abstract class BaseInstrument implements FinancialInstrument {
    protected String name;
    protected double value;

    public BaseInstrument(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public FinancialInstrument clone() {
        try {
            return (FinancialInstrument) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone not supported", e);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getValue() {
        return value;
    }

}
