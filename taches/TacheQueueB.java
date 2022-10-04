package TD1.taches;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import TD1.brokers.QueueBroker;
import TD1.brokers.QueueBrokerImplem;
import TD1.channels.MessageQueue;

public class TacheQueueB extends Tache {

	private QueueBroker queueBroker;

	public TacheQueueB(String name) {
		super(name);
		this.queueBroker = new QueueBrokerImplem(name);
	}

	public void run() {
		MessageQueue queue = queueBroker.connect("test1", 123);
		byte[] message = null;
		try {
			message = queue.receive();
		} catch (IOException e) {
			System.out.println("je suis déconnecté");
		}

		String msgString = new String(message, StandardCharsets.UTF_8);
		System.out.println("message reçu : " + msgString);
	}

}
