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
		assertEquals("Nordea", Nordea.getName());
		assertEquals("SweBank", SweBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, Nordea.getCurrency());
		assertEquals(SEK, SweBank.getCurrency());

	}

	@Test
	public void testOpenAccount() throws AccountExistsException {
		assertThrows(AccountExistsException.class, () -> SweBank.openAccount("Bob"));
		assertThrows(AccountExistsException.class, () -> SweBank.openAccount("Ulrika"));
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		assertThrows(AccountDoesNotExistException.class, () -> SweBank.deposit("Boddfgdgb", new Money(2000, SEK)));
		SweBank.deposit("Ulrika", new Money(5000, SEK));
		assertEquals(5000, SweBank.getBalance("Ulrika"), 0);
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		assertThrows(AccountDoesNotExistException.class, () -> SweBank.withdraw("Boddfgdgb", new Money(2000, SEK)));
		SweBank.withdraw("Bob", new Money(500, SEK));
		assertEquals(-500, SweBank.getBalance("Bob"), 0);
	}

	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertThrows(AccountDoesNotExistException.class, () -> SweBank.getBalance("Boddfgdgb"));
		assertEquals(0, Nordea.getBalance("Bob"), 0);
	}

	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		assertThrows(AccountDoesNotExistException.class, () -> SweBank.transfer("Bob",Nordea,"sfsdf", new Money(1000,SEK)));
		assertThrows(AccountDoesNotExistException.class, () -> SweBank.transfer("Bob","sfsdf", new Money(1000,SEK)));

		//different Banks
		SweBank.transfer("Ulrika", Nordea,"Bob"  ,new Money(100, SEK));
		assertEquals(-100, SweBank.getBalance("Ulrika"), 0);
		assertEquals(100, Nordea.getBalance("Bob"), 0);


		//the same banks
		SweBank.transfer("Bob", "Ulrika", new Money(100, SEK));
		assertEquals(-100, SweBank.getBalance("Bob"), 0);
		assertEquals(0, SweBank.getBalance("Ulrika"), 0);
	}

	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.addTimedPayment("Bob","1", 1 ,0, new Money(10000, SEK),
				SweBank, "Ulrika");
		SweBank.tick();
		assertEquals(10000,SweBank.getBalance("Ulrika"),0);
		SweBank.removeTimedPayment("Bob","1");
		SweBank.tick();
		assertEquals(10000,SweBank.getBalance("Ulrika"),0);
	}
}
