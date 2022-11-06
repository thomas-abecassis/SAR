package TD1.taches;

import java.io.IOException;
import java.util.Queue;

import TD1.channels.MessageQueue;

public class BroadcastThread extends Thread {

	private Queue<byte[]> messages;
	private MessageQueue queue;

	public BroadcastThread(Queue<byte[]> messages, MessageQueue queue) {
		this.messages = messages;
		this.queue = queue;
	}

	public void run() {
		// attente parralèle, active et infinie de message
		while (true) {
			try {
				messages.offer(queue.receive());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
