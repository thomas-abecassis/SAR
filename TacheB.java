package TD1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TacheB extends Tache {
	public TacheB(String name) {
		super(name);
	}
	
	public void run() {
		Channel channel = broker.connect("test1",123);
		System.out.println("tachebco");
        byte[] bytesRecus = new byte[12];
        try {
			channel.read(bytesRecus,0,12);
		} catch (IOException e) {
			System.out.println("je suis déconnecté");
		}
        String msgString = new String(bytesRecus, StandardCharsets.UTF_8);
        System.out.println("message reçu : " + msgString);
	}

}
