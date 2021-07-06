package pt.ulisboa.tecnico.learnjava.mbway.domain;

public class ClientMbway {
	
	private String iban;
	private String phoneNumber;
	private int confCode;
	private boolean isConfirmed;
	
	public ClientMbway(String iban, String phoneNumber, int confCode) {
		this.iban = iban;
		this.phoneNumber = phoneNumber;
		this.confCode = confCode;
		this.isConfirmed = false;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public int getConfCode() {
		return confCode;
	}
	
	public boolean getIsConfirmed() {
		return isConfirmed;
	}
	
	public void setIsConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

}
