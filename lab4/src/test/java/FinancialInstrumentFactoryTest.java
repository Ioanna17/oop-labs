import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinancialInstrumentFactoryTest {
    private FinancialInstrumentFactory factory;

    @BeforeEach
    void setUp() {
        factory = new FinancialInstrumentFactory();

        factory.registerPrototype("stock", new Stock("AAPL", 150.0, "Technology", 1.5, "Medium"));
        factory.registerPrototype("bond", new Bond("Government", 1000.0, "2030-12-31", 2.5, "AAA"));
        factory.registerPrototype("derivative", new Derivative("Gold", "Futures", "2025-06-30", 10.0));
        factory.registerPrototype("stock-tech", new Stock("MSFT", 320.0, "Technology", 1.8, "Low"));
        factory.registerPrototype("bond-corporate", new Bond("Tesla Inc.", 2000.0, "2028-05-15", 3.0, "BB"));
        factory.registerPrototype("derivative-option", new Derivative("Oil", "Option", "2024-12-31", 5.0));
    }

    @Test
    void testCreateStock() {
        FinancialInstrument stock = factory.createInstrument("stock");
        assertNotNull(stock, "Stock should not be null");
        assertTrue(stock instanceof Stock, "Created instrument should be an instance of Stock");
    }

    @Test
    void testCreateBond() {
        FinancialInstrument bond = factory.createInstrument("bond");
        assertNotNull(bond, "Bond should not be null");
        assertTrue(bond instanceof Bond, "Created instrument should be an instance of Bond");
    }

    @Test
    void testCreateDerivative() {
        FinancialInstrument derivative = factory.createInstrument("derivative");
        assertNotNull(derivative, "Derivative should not be null");
        assertTrue(derivative instanceof Derivative, "Created instrument should be an instance of Derivative");
    }

    @Test
    void testCreateStockTech() {
        FinancialInstrument stockTech = factory.createInstrument("stock-tech");
        assertNotNull(stockTech, "Stock-tech should not be null");
        assertTrue(stockTech instanceof Stock, "Created instrument should be an instance of Stock");
    }

    @Test
    void testCreateBondCorporate() {
        FinancialInstrument bondCorporate = factory.createInstrument("bond-corporate");
        assertNotNull(bondCorporate, "Bond-corporate should not be null");
        assertTrue(bondCorporate instanceof Bond, "Created instrument should be an instance of Bond");
    }

    @Test
    void testUnregisteredPrototype() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> factory.createInstrument("crypto")
        );
        assertEquals("Prototype for type crypto not found", exception.getMessage());
    }

    @Test
    void testPrototypeIndependence() {
        FinancialInstrument stock1 = factory.createInstrument("stock");
        FinancialInstrument stock2 = factory.createInstrument("stock");
        assertNotSame(stock1, stock2, "Each created instrument should be a new instance");
    }

    @Test
    void testPrototypeCloning() {
        FinancialInstrument stock1 = factory.createInstrument("stock");
        FinancialInstrument stock2 = factory.createInstrument("stock");

        assertNotSame(stock1, stock2, "Each created instrument should be a new instance");
        assertEquals(stock1.clone().getClass(), stock2.getClass(), "Cloned instruments should have the same type");
    }

    @Test
    void testCloneAttributes() {
        FinancialInstrument stock = factory.createInstrument("stock");
        FinancialInstrument clonedStock = stock.clone();

        if (stock instanceof Stock && clonedStock instanceof Stock) {
            Stock original = (Stock) stock;
            Stock clone = (Stock) clonedStock;
            assertEquals(original.getSymbol(), clone.getSymbol());
            assertEquals(original.getPrice(), clone.getPrice(), 0.001);
            assertEquals(original.getSector(), clone.getSector());
            assertEquals(original.getDividendYield(), clone.getDividendYield(), 0.001);
            assertEquals(original.getVolatilityRating(), clone.getVolatilityRating());
        }
    }

    @Test
    void testCloneAttributesForBond() {
        FinancialInstrument bond = factory.createInstrument("bond");
        FinancialInstrument clonedBond = bond.clone();

        if (bond instanceof Bond && clonedBond instanceof Bond) {
            Bond original = (Bond) bond;
            Bond clone = (Bond) clonedBond;
            assertEquals(original.getIssuer(), clone.getIssuer());
            assertEquals(original.getFaceValue(), clone.getFaceValue(), 0.001);
            assertEquals(original.getMaturityDate(), clone.getMaturityDate());
            assertEquals(original.getCouponRate(), clone.getCouponRate(), 0.001);
            assertEquals(original.getCreditRating(), clone.getCreditRating());
        }
    }

    @Test
    void testCloneAttributesForDerivative() {
        FinancialInstrument derivative = factory.createInstrument("derivative");
        FinancialInstrument clonedDerivative = derivative.clone();

        if (derivative instanceof Derivative && clonedDerivative instanceof Derivative) {
            Derivative original = (Derivative) derivative;
            Derivative clone = (Derivative) clonedDerivative;
            assertEquals(original.getUnderlyingAsset(), clone.getUnderlyingAsset());
            assertEquals(original.getContractType(), clone.getContractType());
            assertEquals(original.getExpirationDate(), clone.getExpirationDate());
            assertEquals(original.getLeverage(), clone.getLeverage(), 0.001);
        }
    }

    @Test
    void testRegisterDuplicatePrototype() {
        factory.registerPrototype("stock", new Stock("GOOGL", 2800.0, "Technology", 0.5, "High"));
        FinancialInstrument stock = factory.createInstrument("stock");
        assertTrue(stock instanceof Stock);
        assertEquals("GOOGL", ((Stock) stock).getSymbol());
    }

    @Test
    void testDisplayDetailsOutput() {
        FinancialInstrument stock = factory.createInstrument("stock");
        assertNotNull(stock);
        stock.displayDetails(); // Додати логіку перевірки виводу через перехоплення потоку, якщо це критично.
    }
}
