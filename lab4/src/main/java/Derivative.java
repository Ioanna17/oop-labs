public class Derivative implements FinancialInstrument {
    private String underlyingAsset;
    private String contractType;
    private String expirationDate;
    private double leverage;

    public Derivative(String underlyingAsset, String contractType, String expirationDate, double leverage) {
        this.underlyingAsset = underlyingAsset;
        this.contractType = contractType;
        this.expirationDate = expirationDate;
        this.leverage = leverage;
    }

    public String getUnderlyingAsset() {
        return underlyingAsset;
    }

    public String getContractType() {
        return contractType;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public double getLeverage() {
        return leverage;
    }

    @Override
    public FinancialInstrument clone() {
        return new Derivative(underlyingAsset, contractType, expirationDate, leverage);
    }

    @Override
    public void displayDetails() {
        System.out.printf("Derivative [Underlying Asset: %s, Contract Type: %s, Expiration Date: %s, Leverage: %.2f]%n",
                underlyingAsset, contractType, expirationDate, leverage);
    }
}
