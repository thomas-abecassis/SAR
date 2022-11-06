package TD1.taches;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import TD1.brokers.QueueBroker;
import TD1.brokers.QueueBrokerImplem;
import TD1.channels.MessageQueue;

public class TacheBroadcastA extends Tache {
	private QueueBroker broker;
	private int portConnexion;

	public TacheBroadcastA(String name, int portConnexion) {
		super(name);
		this.broker = new QueueBrokerImplem(name);
		this.portConnexion = portConnexion;
	}

	public void run() {
		byte[] message = null;
		MessageQueue queue = broker.connect("Broadcast", portConnexion);
		try {
			queue.write("Hello World!".getBytes(), 0, 12);
			message = queue.receive();
			String msgString = new String(message, StandardCharsets.UTF_8);
			System.out.println("Tache A reçu : " + msgString);
		} catch (IOException e) {
			System.out.println("je suis déconnecté");
		}
	}

}
