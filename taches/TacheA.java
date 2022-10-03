package TD1.taches;

import java.io.IOException;

import TD1.channels.Channel;

public class TacheA extends Tache {

	public TacheA(String name) {
		super(name);
	}

	public void run() {
		Channel channel = broker.accept(123);
		System.out.println("tachaco");
		try {
			channel.write("Hello World!".getBytes(), 0, 12);
		} catch (IOException e) {
			System.out.println("je suis déconnecté");
		}
	}

}
