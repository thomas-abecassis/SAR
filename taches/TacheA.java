package TD1.taches;

import java.io.IOException;

import TD1.brokers.Broker;
import TD1.brokers.BrokerImplem;
import TD1.channels.Channel;

public class TacheA extends Tache {

	private Broker broker;

	public TacheA(String name) {
		super(name);
		this.broker = new BrokerImplem(name);
	}

	public void run() {
		Channel channel = broker.accept(123);
		try {
			channel.write("Hello World!".getBytes(), 0, 12);
		} catch (IOException e) {
			System.out.println("je suis déconnecté");
		}
	}

}
