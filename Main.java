package TD1;

import TD1.taches.Broadcast;
import TD1.taches.TacheBroadcastA;
import TD1.taches.TacheBroadcastB;

class Main {
	public static void main(String[] args) {
		/*
		 * Tache tacheA = new TacheA("test1"); Tache tacheB = new TacheB("test2");
		 * 
		 * tacheA.start(); tacheB.start();
		 */

		/*
		 * TacheQueueA tacheQueueA = new TacheQueueA("test1"); TacheQueueB tacheQueueB =
		 * new TacheQueueB("test2");
		 * 
		 * tacheQueueA.start(); tacheQueueB.start();
		 */

		TacheBroadcastA tacheQueueA = new TacheBroadcastA("test1", 123);
		TacheBroadcastB tacheQueueB1 = new TacheBroadcastB("test2", 124);
		TacheBroadcastB tacheQueueB2 = new TacheBroadcastB("test3", 126);

		Broadcast broadcast = new Broadcast("Broadcast");

		broadcast.start();
		tacheQueueA.start();
		tacheQueueB1.start();
		tacheQueueB2.start();
	}
}