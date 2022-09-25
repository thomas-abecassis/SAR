package TD1;

import java.nio.charset.StandardCharsets;

public class TacheB extends Tache {
	public TacheB(String name) {
		super(name);
	}
	
	public void run() {
		Channel channel = broker.connect("test1",123);
        byte[] bytesRecus = new byte[12];
        channel.read(bytesRecus,0,12);
        String msgString = new String(bytesRecus, StandardCharsets.UTF_8);
        System.out.println("message reçu : " + msgString);
	}

}
