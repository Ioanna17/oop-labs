import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FinancialSimulation {
    private static List<FinancialInstrument> portfolio = new ArrayList<>();
    private static InstrumentManager manager = new InstrumentManager();
    private static String marketTrend = "neutral"; // Стан ринку: "bullish", "bearish", "neutral"

    public static void main(String[] args) {
        // Ініціалізація прототипів
        manager.addPrototype("stock", new Stock("Default Stock", 100.0, 0.02));
        manager.addPrototype("bond", new Bond("Default Bond", 1000.0, 0.05));
        manager.addPrototype("derivative", new Derivative("Default Derivative", 0.0, new Stock("Tesla", 200.0, 0.03)));

        // Інтерактивне меню
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Меню ---");
            System.out.println("1. Додати акції");
            System.out.println("2. Додати облігації");
            System.out.println("3. Додати деривативи");
            System.out.println("4. Наступний рік (симуляція)");
            System.out.println("5. Переглянути портфель");
            System.out.println("6. Вийти");
            System.out.print("Виберіть опцію: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addStock(scanner);
                case 2 -> addBond(scanner);
                case 3 -> addDerivative(scanner);
                case 4 -> simulateNextYear();
                case 5 -> viewPortfolio();
                case 6 -> exit = true;
                default -> System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
        scanner.close();
    }

    private static void addStock(Scanner scanner) {
        System.out.print("Введіть назву акції: ");
        String name = scanner.next();
        System.out.print("Введіть початкову вартість: ");
        double value = scanner.nextDouble();
        System.out.print("Введіть дивідендну дохідність (у %): ");
        double dividendYield = scanner.nextDouble() / 100;

        FinancialInstrument stock = new Stock(name, value, dividendYield);
        portfolio.add(stock);
        System.out.println("Акція успішно додана до портфеля.");
    }

    private static void addBond(Scanner scanner) {
        System.out.print("Введіть назву облігації: ");
        String name = scanner.next();
        System.out.print("Введіть номінальну вартість: ");
        double value = scanner.nextDouble();
        System.out.print("Введіть річну процентну ставку (у %): ");
        double annualRate = scanner.nextDouble() / 100;

        FinancialInstrument bond = new Bond(name, value, annualRate);
        portfolio.add(bond);
        System.out.println("Облігація успішно додана до портфеля.");
    }

    private static void addDerivative(Scanner scanner) {
        System.out.print("Введіть назву деривативу: ");
        String name = scanner.next();
        System.out.print("Введіть початкову вартість: ");
        double value = scanner.nextDouble();
        System.out.print("Введіть базову ціну активу: ");
        double baseValue = scanner.nextDouble();

        FinancialInstrument underlying = new Stock("Базовий актив", baseValue, 0.02);
        FinancialInstrument derivative = new Derivative(name, value, underlying);
        portfolio.add(derivative);
        System.out.println("Дериватив успішно доданий до портфеля.");
    }

    private static void simulateNextYear() {
        System.out.println("\n--- Симуляція наступного року ---");
        // Випадково визначаємо тренд ринку
        double trend = Math.random();
        marketTrend = trend < 0.4 ? "bearish" : trend > 0.6 ? "bullish" : "neutral";
        System.out.println("Ринок зараз: " + marketTrend);

        for (FinancialInstrument instrument : portfolio) {
            if (instrument instanceof Stock stock) {
                if (marketTrend.equals("bullish")) {
                    stock.simulatePriceChange(); // Ціна зростає
                } else if (marketTrend.equals("bearish")) {
                    stock.simulatePriceChange(); // Ціна падає
                }
                stock.payDividend();
            } else if (instrument instanceof Bond bond) {
                bond.performOperation(); // Нарахування річного доходу
            } else if (instrument instanceof Derivative derivative) {
                derivative.performOperation(); // Оновлення вартості деривативу
            }
        }
    }

    private static void viewPortfolio() {
        System.out.println("\n--- Ваш портфель ---");
        for (FinancialInstrument instrument : portfolio) {
            System.out.println(instrument.getName() + " | Вартість: " + instrument.getValue());
        }
    }
}
