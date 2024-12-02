public class Bond implements FinancialInstrument {
    private String issuer;
    private double faceValue;
    private String maturityDate;
    private double couponRate;
    private String creditRating;

    public Bond(String issuer, double faceValue, String maturityDate, double couponRate, String creditRating) {
        this.issuer = issuer;
        this.faceValue = faceValue;
        this.maturityDate = maturityDate;
        this.couponRate = couponRate;
        this.creditRating = creditRating;
    }

    public String getIssuer() {
        return issuer;
    }

    public double getFaceValue() {
        return faceValue;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public double getCouponRate() {
        return couponRate;
    }

    public String getCreditRating() {
        return creditRating;
    }

    @Override
    public FinancialInstrument clone() {
        return new Bond(issuer, faceValue, maturityDate, couponRate, creditRating);
    }

    @Override
    public void displayDetails() {
        System.out.printf("Bond [Issuer: %s, Face Value: %.2f, Maturity Date: %s, Coupon Rate: %.2f%%, Credit Rating: %s]%n",
                issuer, faceValue, maturityDate, couponRate, creditRating);
    }
}
