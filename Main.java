package TD1;

import TD1.taches.Tache;
import TD1.taches.TacheA;
import TD1.taches.TacheB;

class Main {
	public static void main(String[] args) {
		Tache tacheA = new TacheA("test1");
		Tache tacheB = new TacheB("test2");

		tacheA.start();
		tacheB.start();
	}
}