package TD1.taches;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import TD1.brokers.Broker;
import TD1.brokers.BrokerImplem;
import TD1.channels.Channel;

public class TacheB extends Tache {
	private Broker broker;

	public TacheB(String name) {
		super(name);
		this.broker = new BrokerImplem(name);
	}

	public void run() {
		Channel channel = broker.connect("test1", 123);
		byte[] bytesRecus = new byte[12];
		try {
			channel.read(bytesRecus, 0, 12);
		} catch (IOException e) {
			System.out.println("je suis déconnecté");
		}
		String msgString = new String(bytesRecus, StandardCharsets.UTF_8);
		System.out.println("message reçu : " + msgString);
	}

}
