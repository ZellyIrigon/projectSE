package pt.ulisboa.tecnico.learnjava.sibs.operation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank.AccountType;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Cancelled;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Sibs;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.SibsException;

public class CancelOperationTest {

	private static final String ADDRESS = "Ave.";
	private static final String PHONE_NUMBER = "987654321";
	private static final String NIF = "123456789";
	private static final String LAST_NAME = "Silva";
	private static final String FIRST_NAME = "António";
	private String sourceIban;
	private String targetIban;
	

	private Sibs sibs;
	private Bank sourceBank;
	private Bank targetBank;
	private Client sourceClient;
	private Client targetClient;
	private Services services;
	
	
	@Before
	public void setUp() throws Exception   {
		this.services = new Services();
		this.sibs = new Sibs(100, services);		
		this.sourceBank = new Bank("CGD");
		this.targetBank = new Bank("BPI");
		this.sourceClient = new Client(this.sourceBank, FIRST_NAME, LAST_NAME, NIF, PHONE_NUMBER, ADDRESS, 33);
		this.targetClient = new Client(this.targetBank, FIRST_NAME, LAST_NAME, NIF, PHONE_NUMBER, ADDRESS, 22);
	    sourceIban = sourceBank.createAccount(AccountType.CHECKING, sourceClient, 3000, 0);
	    targetIban = targetBank.createAccount(AccountType.CHECKING, targetClient, 3000, 0);
	    this.sibs.transfer(sourceIban, targetIban, 1000);
	   
	    
	}
	
	@Test
	public void checkCancelOperation() throws SibsException, AccountException {
				
		sibs.processOperations();
		sibs.cancelOperation(0);
		assertTrue(sibs.getOperation(0).getState() instanceof Cancelled);
		assertEquals(3000, this.services.getAccountByIban(sourceIban).getBalance());
		assertEquals(3000, this.services.getAccountByIban(targetIban).getBalance());
	}
	
	@After
	public void tearDown() {
		Bank.clearBanks();
	}
}
