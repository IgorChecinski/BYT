package b_Money;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;

	@Before
	public void setUp() throws Exception {
		// Setting up currencies with their respective exchange rates.
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		// Testing if getName correctly returns the currency's name.
		assertEquals("SEK", SEK.getName());
	}

	@Test
	public void testGetRate() {
		// Testing if getRate correctly returns the currency's exchange rate.
		assertEquals(0.15, SEK.getRate(), 0);
	}

	@Test
	public void testSetRate() {
		// Testing the setRate method by updating and verifying the exchange rate.
		SEK.setRate(1.40);
		assertEquals(1.40, SEK.getRate(), 0);
	}

	@Test
	public void testGlobalValue() {
		// Testing the calculation of universal value of an amount in this currency.
		assertEquals(15, SEK.universalValue(100), 0);
	}

	@Test
	public void testValueInThisCurrency() {
		// Testing conversion of an amount from another currency to this currency.
		assertEquals(22.5, SEK.valueInThisCurrency(100, EUR), 0.5);
	}
}
