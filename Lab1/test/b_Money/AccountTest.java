package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;

	@Before
	public void setUp() throws Exception {
		// Setting up currencies, banks, and accounts for testing.
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}



	@Test
	public void testAddRemoveTimedPayment() {
		// Testing adding and removing timed payments.

		/*
		 Test originally failed at setUp(), due to NullPointerException from Bank.deposit method
		 */
		testAccount.addTimedPayment("1", 1, 1, new Money(10000, SEK), SweBank, "Alice");
		testAccount.addTimedPayment("2", 1, 1, new Money(10000, SEK), SweBank, "Alice");

		assertTrue("Payment has been added", testAccount.timedPaymentExists("1"));
		assertFalse("Payment hasn't been added", testAccount.timedPaymentExists("3"));

		testAccount.removeTimedPayment("1");
		assertTrue("Payment hasn't been removed", testAccount.timedPaymentExists("2"));
		assertFalse("Payment has been removed", testAccount.timedPaymentExists("1"));
	}

	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		// Testing the effect of timed payments on account balance.

		/*
		 Test originally failed at setUp(), due to NullPointerException from Bank.deposit method
		 */
		testAccount.addTimedPayment("1", 1, 1, new Money(10000, SEK), SweBank, "Alice");
		testAccount.tick(); // Simulates the passing of time.
		testAccount.tick();
		assertEquals(9990000, testAccount.getBalance().getAmount(), 0);
	}

	@Test
	public void testAddWithdraw() {
		// Testing withdrawal functionality and its impact on account balance.

		/*
		 Test originally failed at setUp(), due to NullPointerException from Bank.deposit method
		 */
		testAccount.withdraw(new Money(50000, SEK));
		assertEquals(9950000, testAccount.getBalance().getAmount(), 0);
	}

	@Test
	public void testGetBalance() {
		// Testing if the getBalance method returns the correct balance.

		/*
		 Test originally failed at setUp(), due to NullPointerException from Bank.deposit method
		 */
		assertEquals(10000000, testAccount.getBalance().getAmount(), 0);
	}
}
