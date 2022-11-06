package TD1.taches;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import TD1.brokers.QueueBroker;
import TD1.brokers.QueueBrokerImplem;
import TD1.channels.MessageQueue;

public class Broadcast extends Tache {

	private QueueBroker queueBroker;
	private Queue<byte[]> messages = new LinkedList<byte[]>();
	private Collection<MessageQueue> queues = new ArrayList<MessageQueue>();

	public Broadcast(String name) {
		super(name);
		this.queueBroker = new QueueBrokerImplem(name);
	}

	public void run() {
		while (true) {
			// on commence par accepter nos 3 clients, pour un vrai serveur de broadcast
			// c'est évidemment un mauvais design,
			// néanmoins dans notre cas comme il s'agit d'un test controllé nous pouvons
			// nous le permettre

			MessageQueue queue1 = queueBroker.accept(123);
			MessageQueue queue2 = queueBroker.accept(124);
			MessageQueue queue3 = queueBroker.accept(126);

			queues.add(queue1);
			queues.add(queue2);
			queues.add(queue3);

			BroadcastThread broadcastThread1 = new BroadcastThread(messages, queue1);
			BroadcastThread broadcastThread2 = new BroadcastThread(messages, queue2);
			BroadcastThread broadcastThread3 = new BroadcastThread(messages, queue3);

			// Comme notre spec définie que receive est bloquant nous sommes obligés
			// d'utiliser des threads pour écouter l'arriver de messages de 3 clients
			// différents en simultané

			broadcastThread1.start();
			broadcastThread2.start();
			broadcastThread3.start();

			while (true) {
				byte[] message = messages.poll();
				if (message != null) {
					for (MessageQueue queue : queues) {
						try {
							queue.write(message, 0, message.length);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}

		}
	}

}
