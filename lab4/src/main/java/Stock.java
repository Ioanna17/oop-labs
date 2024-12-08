public class Stock extends BaseInstrument {
    private double dividendYield; // Дивідендна дохідність
    private static String marketTrend; // Загальний тренд ринку для всіх акцій

    // Конструктор для створення акції з назвою, початковою вартістю і дивідендною дохідністю
    public Stock(String name, double value, double dividendYield) {
        super(name, value); // Викликає конструктор базового класу
        this.dividendYield = dividendYield; // Ініціалізує дивідендну дохідність
    }

    // Метод для встановлення ринкового тренду
    public static void setMarketTrend(String trend) {
        marketTrend = trend;
    }

    // Метод для симуляції зміни ціни акції
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

    // Метод для виплати дивідендів
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
            value *= 0.9; // 10% decrease
        } else if (event.equals("bullMarket")) {
            value *= 1.1; // 10% increase
        }
    }

//    public void performStockSplit(int splitRatio) {
//        value /= splitRatio;
//        System.out.println("Stock " + name + " split " + splitRatio + "-for-1. New price: " + value);
//    }
}
