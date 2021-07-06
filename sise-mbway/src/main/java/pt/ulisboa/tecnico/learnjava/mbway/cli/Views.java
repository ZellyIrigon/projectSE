package pt.ulisboa.tecnico.learnjava.mbway.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank.AccountType;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.mbway.controllers.MbwayAssociateController;
import pt.ulisboa.tecnico.learnjava.mbway.controllers.MbwayConfirmController;
import pt.ulisboa.tecnico.learnjava.mbway.controllers.MbwaySplitInsuranceController;
import pt.ulisboa.tecnico.learnjava.mbway.controllers.MbwayTransferController;
import pt.ulisboa.tecnico.learnjava.mbway.domain.Mbway;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Sibs;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.MbwayException;

public class Views {

 
	public static void main(String[] args) throws BankException, ClientException, AccountException {

		Services services = new Services();
		Mbway mbway = new Mbway();
		Sibs sibs = new Sibs(100, services);
		Bank bank1 = new Bank("CGD");
		Bank bank2 = new Bank("BPI");
		
		Client client1 = new Client(bank1, "Maria", "Soares", "123456789", "912346987", "Rua Alves Redol", 25);
		Client client2 = new Client(bank2, "Clara", "Rodrigues", "987654321", "917895234", "Rua Redol", 30);
		Client client3 = new Client(bank2, "Marco", "Andrade", "345123678", "967896734", "Rua da Figueira", 40);
		
		String iban1 = bank1.createAccount(AccountType.CHECKING, client1, 2000, 0);
		String iban2 = bank2.createAccount(AccountType.CHECKING, client2, 5000, 0);
		String iban3 = bank2.createAccount(AccountType.CHECKING, client3, 2500, 0);
	
		while (true) {
			try {
				InputStreamReader input = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(input);
				System.out.println("Insira o comando desejado:");
				String command = br.readLine();
				
				String[] inputs = command.split(" ");

				switch(inputs[0]) {
					case "exit":
						System.out.println("MBWAY terminated with success.");
						System.exit(0);
						break;
					case "associate-mbway":
						if(inputs.length!=3)
							throw new IOException();
						MbwayAssociateController ctrl = new MbwayAssociateController(services, mbway);
						String iban = inputs[1];
						String phoneNumber = inputs[2];
						System.out.println(ctrl.associateAccount(iban, phoneNumber));
						break;
					case "confirm-mbway":
						if(inputs.length!=3)
							throw new IOException();
						MbwayConfirmController ctrl1 = new MbwayConfirmController(services, mbway);
						phoneNumber = inputs[1];	//nao e necessario colocar o tipo pois ja foi definido previamente
						Integer code = Integer.valueOf(inputs[2]);
						
						System.out.println(ctrl1.confirmAccount(phoneNumber, code));
						break;
					case "mbway-transfer":
						if(inputs.length!=4)
							throw new IOException();
						MbwayTransferController ctrl2 = new MbwayTransferController(services, mbway);
						String sourceNumber = inputs[1];
						String targetNumber = inputs[2];
						Integer amount = Integer.valueOf(inputs[3]);
						
						System.out.println(ctrl2.transferMoney(sourceNumber, targetNumber, amount));
						break;
					case "mbay-split-insurance":
						if(inputs.length!=4)
							throw new IOException();
						MbwaySplitInsuranceController ctrl3 = new MbwaySplitInsuranceController(services, mbway);
						Integer numberOfFriends = Integer.valueOf(inputs[1]);
						Integer totalAmount = Integer.valueOf(inputs[2]);
						String[] friends = command.split(" ");
	
				}
				
			}

			catch (IOException ioe) {
				System.out.println("Something went wrong, please try again!");
			}
			catch (MbwayException mbe) {
				System.err.println(mbe.getMessage());
			}
		}
	}
}
