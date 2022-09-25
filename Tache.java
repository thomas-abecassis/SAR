package TD1;

public class Tache extends Thread{
	
	protected String name;
	protected Broker broker;
	
	public Tache(String name) {
		this.name = name;
		this.broker = new BrokerImplem(name);
	}
}
