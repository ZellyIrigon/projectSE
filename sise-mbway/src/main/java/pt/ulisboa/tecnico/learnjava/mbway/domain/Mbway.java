package pt.ulisboa.tecnico.learnjava.mbway.domain;

import java.util.HashMap;

public class Mbway {
	
	//phone Number, Client details
	private HashMap<String, ClientMbway> associations = new HashMap<String, ClientMbway>();
	
	public Mbway() {
		//criar hashmap vazia
		this.associations = new HashMap<String, ClientMbway>();
		
	}
		
	public void addClient(String phoneNumber, ClientMbway client) {			
		this.associations.put(phoneNumber, client);
	}
	
	public ClientMbway getClient(String phoneNumber) {
		return this.associations.get(phoneNumber);
	}
}
