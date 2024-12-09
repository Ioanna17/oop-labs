import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class FinancialSimulation {
    private static List<FinancialInstrument> portfolio = new ArrayList<>();
    private static InstrumentManager manager = new InstrumentManager();
    private static String marketTrend = "neutral"; // Market trend: "bullish", "bearish", "neutral"
    private static int year = 2024; // Starting year

    public static void main(String[] args) {
        // Add prototypes for cloning
        manager.addPrototype("stock", new Stock("Default Stock", 100.0, 0.02));
        manager.addPrototype("bond", new Bond("Default Bond", 1000.0, 0.05, 2030));
        manager.addPrototype("derivative", new Derivative("Default Derivative", 0.0, new Stock("Tesla", 200.0, 0.03), 2025));

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Stock");
            System.out.println("2. Add Bond");
            System.out.println("3. Add Derivative");
            System.out.println("4. Next Year (Simulation)");
            System.out.println("5. View Portfolio");
            System.out.println("6. Exit");
            System.out.print("Select option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addStock(scanner);
                case 2 -> addBond(scanner);
                case 3 -> addDerivative(scanner);
                case 4 -> simulateNextYear();
                case 5 -> viewPortfolio();
                case 6 -> exit = true;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    private static void addStock(Scanner scanner) {
        System.out.println("\n--- Adding New Stock ---");
        System.out.print("Enter stock name: ");
        scanner.nextLine(); // Consume newline
        String name = scanner.nextLine();
        System.out.print("Enter stock value: ");
        double value = scanner.nextDouble();
        System.out.print("Enter dividend yield (e.g., 0,05): ");
        double dividendYield = scanner.nextDouble();

        Stock stock = new Stock(name, value, dividendYield);
        portfolio.add(stock);
        System.out.println("Stock added to portfolio!");
    }

    private static void addBond(Scanner scanner) {
        System.out.println("\n--- Adding New Bond ---");
        System.out.print("Enter bond name: ");
        scanner.nextLine(); // Consume newline
        String name = scanner.nextLine();
        System.out.print("Enter bond value: ");
        double value = scanner.nextDouble();
        System.out.print("Enter interest rate (e.g., 0,05): ");
        double interestRate = scanner.nextDouble();
        System.out.print("Enter bond maturity year: ");
        int maturityYear = scanner.nextInt();

        Bond bond = new Bond(name, value, interestRate, maturityYear);
        portfolio.add(bond);
        System.out.println("Bond added to portfolio!");
    }

    private static void addDerivative(Scanner scanner) {
        System.out.println("\n--- Adding New Derivative ---");
        System.out.print("Enter derivative name: ");
        scanner.nextLine(); // Consume newline
        String name = scanner.nextLine();
        System.out.print("Enter derivative value: ");
        double value = scanner.nextDouble();
        System.out.print("Enter derivative expiration year: ");
        int expirationYear = scanner.nextInt();

        // Select underlying asset from portfolio
        System.out.println("Select underlying asset for derivative:");
        for (int i = 0; i < portfolio.size(); i++) {
            System.out.println((i + 1) + ". " + portfolio.get(i).getName() + " | Value: $" + portfolio.get(i).getValue());
        }
        System.out.print("Enter asset number: ");
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= portfolio.size()) {
            FinancialInstrument underlyingAsset = portfolio.get(choice - 1);
            Derivative derivative = new Derivative(name, value, underlyingAsset, expirationYear);
            portfolio.add(derivative);
            System.out.println("Derivative added to portfolio!");
        } else {
            System.out.println("Invalid asset selection.");
        }
    }

    private static void simulateNextYear() {
        System.out.println("\n--- Next Year Simulation ---");
        year++; // Increment year
        System.out.println("Current year: " + year);

        // Set market trend based on random value
        double trend = Math.random();
        marketTrend = trend < 0.4 ? "bearish" : trend > 0.6 ? "bullish" : "neutral";
        Stock.setMarketTrend(marketTrend);
        System.out.println("Market trend: " + marketTrend);

        // Generate random market event
        String marketEvent = getRandomMarketEvent();
        System.out.println("Market event: " + marketEvent);

        // Perform operations for each instrument
        for (FinancialInstrument instrument : portfolio) {
            instrument.performOperation(year);
            instrument.reactToMarketEvent(marketEvent, year);
        }
    }

    private static String getRandomMarketEvent() {
        String[] events = {"recession", "bullMarket", "interestRateHike", "maturity", "underlyingPriceIncrease", "expiration"};
        return events[new Random().nextInt(events.length)];
    }

    private static void viewPortfolio() {
        System.out.println("\n--- Portfolio ---");
        for (FinancialInstrument instrument : portfolio) {
            System.out.println(instrument.getName() + " | Value: $" + instrument.getValue());
        }
    }
}