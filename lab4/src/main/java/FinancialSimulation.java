import java.util.*;

public class FinancialSimulation {
    private static List<FinancialInstrument> portfolio = new ArrayList<>();
    private static InstrumentManager manager = new InstrumentManager();
    private static String marketTrend = "neutral"; // Стан ринку: "bullish", "bearish", "neutral"
    private static int year = 2024; // Початковий рік

    public static void main(String[] args) {
        manager.addPrototype("stock", new Stock("Default Stock", 100.0, 0.02));
        manager.addPrototype("bond", new Bond("Default Bond", 1000.0, 0.05));
        manager.addPrototype("derivative", new Derivative("Default Derivative", 0.0, new Stock("Tesla", 200.0, 0.03)));

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
                case 1 -> addStock();
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

    private static void addStock() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть назву акції:");
        String name = scanner.nextLine();

        System.out.println("Введіть початкову ціну акції:");
        double value = scanner.nextDouble();

        System.out.println("Введіть дивідендну дохідність (у десятковому вигляді, наприклад, 0.05):");
        double dividendYield = scanner.nextDouble();

        Stock stock = new Stock(name, value, dividendYield);
        portfolio.add(stock);

        System.out.println("Акція успішно додана до портфеля!");
    }

    private static void addBond(Scanner scanner) {
        System.out.println("\n--- Додавання нових облігацій ---");
        System.out.print("Введіть назву облігації: ");
        String name = scanner.next();
        System.out.print("Введіть номінальну вартість облігації: ");
        double value = scanner.nextDouble();
        System.out.print("Введіть процентну ставку (наприклад, 0.03 для 3%): ");
        double interestRate = scanner.nextDouble();

        Bond newBond = new Bond(name, value, interestRate);
        portfolio.add(newBond);
        System.out.println("Облігацію " + name + " додано до портфеля!");
    }

    private static void addDerivative(Scanner scanner) {
        System.out.println("\n--- Додавання нового деривативу ---");
        System.out.print("Введіть назву деривативу: ");
        String name = scanner.next();
        System.out.print("Введіть вартість деривативу: ");
        double value = scanner.nextDouble();

        // Запитуємо, на який базовий актив створюється дериватив
        System.out.println("Оберіть базовий актив для деривативу:");
        for (int i = 0; i < portfolio.size(); i++) {
            System.out.println((i + 1) + ". " + portfolio.get(i).getName() + " | Вартість: $" + portfolio.get(i).getValue());
        }
        System.out.print("Введіть номер базового активу: ");
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= portfolio.size()) {
            FinancialInstrument underlyingAsset = portfolio.get(choice - 1);
            Derivative newDerivative = new Derivative(name, value, underlyingAsset);
            portfolio.add(newDerivative);
            System.out.println("Дериватив " + name + " додано до портфеля!");
        } else {
            System.out.println("Невірний вибір базового активу.");
        }
    }

    private static void simulateNextYear() {
        System.out.println("\n--- Симуляція наступного року ---");
        year++; // Increment the year
        System.out.println("Тепер рік: " + year);

        double trend = Math.random();
        marketTrend = trend < 0.4 ? "bearish" : trend > 0.6 ? "bullish" : "neutral";
        Stock.setMarketTrend(marketTrend);
        System.out.println("Ринок зараз: " + marketTrend);

        for (FinancialInstrument instrument : portfolio) {
            instrument.performOperation(year);
        }
    }

    private static void viewPortfolio() {
        System.out.println("\n--- Ваш портфель ---");
        for (FinancialInstrument instrument : portfolio) {
            System.out.println(instrument.getName() + " | Вартість: $" + instrument.getValue());
        }
    }
}
