package pt.ulisboa.tecnico.learnjava.sibs.domain;

import pt.ulisboa.tecnico.learnjava.bank.services.Services;

public class Retry extends State{

	
	private int retries ;
	
	public Retry() {
		this.retries = 0;
	}
	
	public void process(Operation operation, Services services) {

		if (retries < 2)
			retries++;
		else
			operation.setState(new ErrorState());

	}

	public void cancel(Operation operation, Services services) {

	}

}
