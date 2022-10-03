package TD1.taches;

import TD1.brokers.Broker;
import TD1.brokers.BrokerImplem;

public class Tache extends Thread {

	protected String name;
	protected Broker broker;

	public Tache(String name) {
		this.name = name;
		this.broker = new BrokerImplem(name);
	}
}
