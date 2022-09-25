package TD1;

import java.util.HashMap;

public class BrokerImplem extends Broker{

	static HashMap<String, BrokerImplem> brokers = new HashMap<String, BrokerImplem>();
	private int portListen = -1;
	private CircularBuffer bufferReadExtern = null;
	private CircularBuffer bufferWriteExtern = null;
	
	public BrokerImplem(String name) {
		super(name);
		brokers.put(name, this);
	}
	
	public boolean isPortOpen(int port) {
		return portListen==port;
	}
	
	public void setBuffers(CircularBuffer bufferRead, CircularBuffer bufferWrite) {
		this.bufferReadExtern = bufferRead;
		this.bufferWriteExtern = bufferWrite;
	}
	

	public Channel accept(int port) { 
		this.portListen = port;
		while(this.bufferReadExtern == null || this.bufferWriteExtern == null) {
			//j'attends
		}
		ChannelCircular channel =  new ChannelCircular(bufferWriteExtern, bufferReadExtern);
		this.bufferReadExtern = null;
		this.bufferWriteExtern = null;
		this.portListen = -1;
		return channel;
	}
	
	public Channel connect(String name, int port) { 
		BrokerImplem broker = brokers.get(name);
		while(broker == null || !broker.isPortOpen(port)) {
			broker = brokers.get(name);
		}
		CircularBuffer bufferRead = new CircularBuffer(256);
		CircularBuffer bufferWrite = new CircularBuffer(256);
		ChannelCircular channelCircular = new ChannelCircular(bufferRead, bufferWrite);
		broker.setBuffers(bufferRead, bufferWrite);
		return channelCircular;
	}

}
