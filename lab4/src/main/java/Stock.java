public class Stock extends BaseInstrument {
    private double dividendYield;
    private static String marketTrend;

    public Stock(String name, double value, double dividendYield) {
        super(name, value);
        this.dividendYield = dividendYield;
    }

    public static void setMarketTrend(String trend) {
        marketTrend = trend;
    }

    public void simulatePriceChange() {
        double changeFactor;
        switch (marketTrend.toLowerCase()) {
            case "bullish": // Бичачий ринок: акції зростають
                changeFactor = 1 + Math.random() * 0.2; // Зростання до 20%
                break;
            case "bearish": // Ведмежий ринок: акції падають
                changeFactor = 1 - Math.random() * 0.2; // Зниження до 20%
                break;
            default: // Нейтральний ринок: малі коливання
                changeFactor = 1 + (Math.random() - 0.5) * 0.1; // Коливання ±5%
                break;
        }
        value *= changeFactor;
        System.out.printf("Ціна акції %s змінена до %.2f (тренд: %s)%n", name, value, marketTrend);
    }

    public void payDividend() {
        double dividend = value * dividendYield;
        System.out.printf("Виплачено дивіденди для %s: %.2f%n", name, dividend);
    }

    @Override
    public void performOperation(int year) {
        simulatePriceChange();
        payDividend();
    }

    @Override
    public void reactToMarketEvent(String event, int year) {
        if (event.equals("recession")) {
            value *= 0.9;
        } else if (event.equals("bullMarket")) {
            value *= 1.1;
        }
    }

}
