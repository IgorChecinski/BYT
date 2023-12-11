package b_Money;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;

	@Before
	public void setUp() throws Exception {
		// Setting up various currencies and Money instances for testing.
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10054, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		// Testing if the getAmount method returns the correct amount.
		assertEquals(10054, SEK100.getAmount(),0);
		assertEquals(0, SEK0.getAmount(),0);
	}

	@Test
	public void testGetCurrency() {
		// Testing if the getCurrency method returns the correct currency.
		assertEquals(SEK, SEK100.getCurrency());
		assertEquals(EUR, EUR10.getCurrency());
	}

	@Test
	public void testToString() {
		// Testing the string representation of Money objects.
		assertEquals("100.54 SEK", SEK100.toString());
		assertEquals("0.0 SEK", SEK0.toString());
	}

	@Test
	public void testGlobalValue() {
		// Testing if the universalValue method calculates correctly.
		assertEquals(1500, EUR10.universalValue(),0);
	}

	@Test
	public void testEqualsMoney() {
		// Testing equality between different Money instances.
		assertTrue("EUR0 and SEK0 are equal", EUR0.equals(SEK0));
		assertFalse("EUR0 and SEK0 aren't equal", EUR0.equals(SEK100));
	}

	@Test
	public void testAdd() {
		// Testing the add method for combining Money instances.
		assertEquals(10000,SEK0.add(EUR10).getAmount(),0);
	}

	@Test
	public void testSub() {
		// Testing the subtract method for Money instances.
		assertEquals(-10000.0,SEK0.sub(EUR10).getAmount(),0);
	}

	@Test
	public void testIsZero() {
		// Testing if isZero correctly identifies zero and non-zero amounts.
		assertTrue("Amount of SEK0 is 0" , SEK0.isZero());
		assertFalse("Amount of SEK100 is not 0", SEK100.isZero());
	}

	@Test
	public void testNegate() {
		// Testing the negate method, which should invert the amount.
		assertEquals(-1000,EUR10.negate().getAmount(),0);
		assertEquals(0,EUR0.negate().getAmount(),0);
	}

	@Test
	public void testCompareTo() {
		// Testing the compareTo method for comparing Money instances.
		assertEquals(1, EUR10.compareTo(EUR0));
		assertEquals(0,EUR0.compareTo(SEK0));
		assertEquals(-1, EUR0.compareTo(SEK100));
	}
}
