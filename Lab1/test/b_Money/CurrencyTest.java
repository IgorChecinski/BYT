package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;

	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals("SEK",SEK.getName());
	}

	@Test
	public void testGetRate() {
		assertEquals(0.15,SEK.getRate(),0);
	}

	@Test
	public void testSetRate() {
		SEK.setRate(1.40);
		assertEquals(1.40, SEK.getRate(),0);
	}

	@Test
	public void testGlobalValue() {
		assertEquals(15, SEK.universalValue(100),0);
	}

	@Test
	public void testValueInThisCurrency() {
		assertEquals(22.5, SEK.valueInThisCurrency(100,EUR),0.5);
	}
}
