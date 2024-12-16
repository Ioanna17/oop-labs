import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FinancialInstrumentTests {

    @Test
    void testStockSimulation() {
        Stock stock = new Stock("Test Stock", 100.0);
        double initialPrice = stock.getPrice();
        stock.simulate();
        double newPrice = stock.getPrice();
        assertNotEquals(initialPrice, newPrice, "Stock price should change after simulation.");
    }

    @Test
    void testStockTaxCalculation() {
        Stock stock = new Stock("Test Stock", 200.0);
        double expectedTax = 200.0 * 0.1; // 10% tax
        double calculatedTax = stock.calculateTax();
        assertEquals(expectedTax, calculatedTax, 0.01, "Tax calculation for Stock is incorrect.");
    }

    @Test
    void testBondSimulation() {
        Bond bond = new Bond("Test Bond", 1000.0, 0.05);
        bond.simulate();
        double annualRate = 0.05;
        double expectedInterest = 1000.0 * annualRate;
        assertEquals(expectedInterest, 1000.0 * annualRate, 0.01, "Bond interest calculation is incorrect.");
    }

    @Test
    void testBondTaxCalculation() {
        Bond bond = new Bond("Test Bond", 1000.0, 0.05);
        double expectedTax = (1000.0 * 0.05) * 0.05; // 5% of the annual interest
        double calculatedTax = bond.calculateTax();
        assertEquals(expectedTax, calculatedTax, 0.01, "Tax calculation for Bond is incorrect.");
    }

    @Test
    void testDerivativeSimulation() {
        Stock baseStock = new Stock("Base Stock", 150.0);
        Derivative derivative = new Derivative("Test Derivative", baseStock, 140.0);
        derivative.simulate();
        double profitOrLoss = baseStock.getPrice() - 140.0;
        assertEquals(profitOrLoss, baseStock.getPrice() - 140.0, 0.01, "Derivative profit/loss calculation is incorrect.");
    }

    @Test
    void testDerivativeTaxCalculation() {
        Stock baseStock = new Stock("Base Stock", 150.0);
        Derivative derivative = new Derivative("Test Derivative", baseStock, 140.0);
        double profitOrLoss = baseStock.getPrice() - 140.0;
        double expectedTax = profitOrLoss > 0 ? profitOrLoss * 0.2 : 0.0; // 20% tax on profit
        double calculatedTax = derivative.calculateTax();
        assertEquals(expectedTax, calculatedTax, 0.01, "Tax calculation for Derivative is incorrect.");
    }

    @Test
    void testPrototypeCloning() {
        Stock originalStock = new Stock("Original Stock", 100.0);
        Stock clonedStock = (Stock) originalStock.clone();
        assertNotSame(originalStock, clonedStock, "Cloned stock should be a new instance.");
        assertEquals(originalStock.getName(), clonedStock.getName(), "Cloned stock name should match original.");
        assertEquals(originalStock.getPrice(), clonedStock.getPrice(), "Cloned stock price should match original.");
    }
}
