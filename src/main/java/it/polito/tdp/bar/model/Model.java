package it.polito.tdp.bar.model;

public class Model {

	private Simulator sim;
	
	public Model() {
		sim=new Simulator();
		
		
	}
	
	
	public void run() {
		sim.setTavoli();
		sim.run();
		
	}
	public int clientiTotali() {
		return sim.getClientiTotali();
	}
	
public int clientiSoddisfatti() {
		return sim.getClientiSoddisfatti();
	}

public int clientiInsoddisfatti() {
	
	return sim.getClientiInsoddisfatti();
}
}
