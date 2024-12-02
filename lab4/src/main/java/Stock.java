public class Stock implements FinancialInstrument {
    private String symbol;
    private double price;
    private String sector;
    private double dividendYield;
    private String volatilityRating;

    public Stock(String symbol, double price, String sector, double dividendYield, String volatilityRating) {
        this.symbol = symbol;
        this.price = price;
        this.sector = sector;
        this.dividendYield = dividendYield;
        this.volatilityRating = volatilityRating;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public String getSector() {
        return sector;
    }

    public double getDividendYield() {
        return dividendYield;
    }

    public String getVolatilityRating() {
        return volatilityRating;
    }

    @Override
    public FinancialInstrument clone() {
        return new Stock(symbol, price, sector, dividendYield, volatilityRating);
    }

    @Override
    public void displayDetails() {
        System.out.printf("Stock [Symbol: %s, Price: %.2f, Sector: %s, Dividend Yield: %.2f%%, Volatility: %s]%n",
                symbol, price, sector, dividendYield, volatilityRating);
    }
}
