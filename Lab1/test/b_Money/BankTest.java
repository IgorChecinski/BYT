package b_Money;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThrows;
import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;

	@Before
	public void setUp() throws Exception {
		// Setting up currencies and banks, and opening accounts for testing.
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		// Testing if the getName method returns the correct bank name.
		assertEquals("Nordea", Nordea.getName());
		assertEquals("SweBank", SweBank.getName());
	}

	@Test
	public void testGetCurrency() {
		// Testing if the getCurrency method returns the correct currency for the bank.
		assertEquals(SEK, Nordea.getCurrency());
		assertEquals(SEK, SweBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException {
		// Testing the handling of opening an account that already exists.

		/*
        This test failed originally. It is supposed to throw exception if account for given id already exists.
         */
		assertThrows(AccountExistsException.class, () -> SweBank.openAccount("Bob"));
		assertThrows(AccountExistsException.class, () -> SweBank.openAccount("Ulrika"));
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		// Testing deposit functionality and exception handling for non-existent accounts.

		/*
        Test failed originally, due to NullPointerException with Account object reference
         */
		assertThrows(AccountDoesNotExistException.class, () -> SweBank.deposit("Boddfgdgb", new Money(2000, SEK)));
		SweBank.deposit("Ulrika", new Money(5000, SEK));
		assertEquals(5000, SweBank.getBalance("Ulrika"), 0);
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		// Testing withdrawal functionality and exception handling for non-existent accounts.

		/*
        Test failed originally, due to AccountDoesNotExistException
         */
		assertThrows(AccountDoesNotExistException.class, () -> SweBank.withdraw("Boddfgdgb", new Money(2000, SEK)));
		SweBank.withdraw("Bob", new Money(500, SEK));
		assertEquals(-500, SweBank.getBalance("Bob"), 0);
	}

	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		// Testing if the getBalance method correctly returns the account balance.

		/*
        Test failed originally, due to AccountDoesNotExistException
         */
		assertThrows(AccountDoesNotExistException.class, () -> SweBank.getBalance("Boddfgdgb"));
		assertEquals(0, Nordea.getBalance("Bob"), 0);
	}

	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		// Testing transfer functionality between accounts, including exception handling.

		 /*
        Test failed originally, due to AccountDoesNotExistException
         */
		assertThrows(AccountDoesNotExistException.class, () -> SweBank.transfer("Bob",Nordea,"sfsdf", new Money(1000,SEK)));
		assertThrows(AccountDoesNotExistException.class, () -> SweBank.transfer("Bob","sfsdf", new Money(1000,SEK)));

		// Transfer between different banks
		SweBank.transfer("Ulrika", Nordea, "Bob", new Money(100, SEK));
		assertEquals(-100, SweBank.getBalance("Ulrika"), 0);
		assertEquals(100, Nordea.getBalance("Bob"), 0);

		// Transfer within the same bank
		SweBank.transfer("Bob", "Ulrika", new Money(100, SEK));
		assertEquals(-100, SweBank.getBalance("Bob"), 0);
		assertEquals(0, SweBank.getBalance("Ulrika"), 0);
	}

	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		// Testing the addition, execution, and removal of timed payments.

		/*
        Test failed originally, due to NullPointerException
         */
		SweBank.addTimedPayment("Bob", "1", 1, 0, new Money(10000, SEK), SweBank, "Ulrika");
		SweBank.tick(); // Simulates the passing of time.
		assertEquals(10000, SweBank.getBalance("Ulrika"), 0);
		SweBank.removeTimedPayment("Bob", "1");
		SweBank.tick(); // Another tick to check if payment was correctly removed.
		assertEquals(10000, SweBank.getBalance("Ulrika"), 0);
	}
}
