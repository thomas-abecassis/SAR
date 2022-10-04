package TD1.taches;

import java.io.IOException;

import TD1.brokers.QueueBroker;
import TD1.brokers.QueueBrokerImplem;
import TD1.channels.MessageQueue;

public class TacheQueueA extends Tache {
	private QueueBroker broker;

	public TacheQueueA(String name) {
		super(name);
		this.broker = new QueueBrokerImplem(name);
	}

	public void run() {
		MessageQueue queue = broker.accept(123);
		try {
			queue.write("Hello World!".getBytes(), 0, 12);
		} catch (IOException e) {
			System.out.println("je suis déconnecté");
		}
	}

}
