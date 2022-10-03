package TD1.brokers;

import java.util.HashMap;

import TD1.channels.Channel;
import TD1.channels.ChannelCircular;
import TD1.channels.CircularBuffer;

public class BrokerImplem extends Broker {

	static HashMap<String, BrokerImplem> brokers = new HashMap<String, BrokerImplem>();
	private int portListen = -1;
	private CircularBuffer bufferReadExtern;
	private CircularBuffer bufferWriteExtern;
	private ChannelCircular channelExtern;
	private ChannelCircular channelIntern;

	public BrokerImplem(String name) {
		super(name);
		channelIntern = new ChannelCircular(null, null);
		brokers.put(name, this);
	}

	public void setChannelExtern(ChannelCircular channelCircular) {
		this.channelExtern = channelCircular;
	}

	public ChannelCircular getChannelIntern() {
		return channelIntern;
	}

	public boolean isPortOpen(int port) {
		return portListen == port;
	}

	public void setBuffers(CircularBuffer bufferRead, CircularBuffer bufferWrite) {
		this.bufferReadExtern = bufferRead;
		this.bufferWriteExtern = bufferWrite;
	}

	public Channel accept(int port) {
		this.portListen = port;
		System.out.println("accept 1");
		synchronized (channelIntern) {
			while (this.channelExtern == null) {
				try {
					channelIntern.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		ChannelCircular channel = new ChannelCircular(bufferWriteExtern, bufferReadExtern, channelExtern);
		this.channelIntern = channel;
		this.bufferReadExtern = null;
		this.bufferWriteExtern = null;
		this.portListen = -1;
		return channel;
	}

	public Channel connect(String name, int port) {
		BrokerImplem broker = brokers.get(name);
		while (broker == null || !broker.isPortOpen(port)) {
			broker = brokers.get(name);
			// attente active d'un broker
		}
		CircularBuffer bufferRead = new CircularBuffer(256);
		CircularBuffer bufferWrite = new CircularBuffer(256);
		ChannelCircular channelCircular = new ChannelCircular(bufferRead, bufferWrite, broker.getChannelIntern());
		broker.setBuffers(bufferRead, bufferWrite);
		broker.setChannelExtern(channelCircular);
		synchronized (broker.getChannelIntern()) {
			broker.getChannelIntern().notify();
		}

		return channelCircular;
	}

}
