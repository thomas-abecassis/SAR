package TD1;

public class TacheA extends Tache{

	public TacheA(String name) {
		super(name);
	}
	
	public void run() {
		Channel channel = broker.accept(123);
		channel.write("Hello World!".getBytes(), 0, 12);
	}

}
