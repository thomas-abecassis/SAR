package TD1;

import TD1.taches.TacheQueueA;
import TD1.taches.TacheQueueB;

class Main {
	public static void main(String[] args) {
		/*
		 * Tache tacheA = new TacheA("test1"); Tache tacheB = new TacheB("test2");
		 * 
		 * tacheA.start(); tacheB.start();
		 */
		TacheQueueA tacheQueueA = new TacheQueueA("test1");
		TacheQueueB tacheQueueB = new TacheQueueB("test2");

		tacheQueueA.start();
		tacheQueueB.start();
	}
}