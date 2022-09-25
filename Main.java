package TD1;

class Main {
    public static void main(String[] args) {
    	Tache tacheA = new TacheA("test1");
    	Tache tacheB = new TacheB("test2");
    	
    	tacheA.start();
    	tacheB.start();
    }
}