package TD1.brokers;

import java.util.ArrayList;
import java.util.HashMap;

import TD1.channels.Channel;
import TD1.channels.ChannelCircular;
import TD1.channels.CircularBuffer;

public class BrokerImplem extends Broker {

	static HashMap<String, BrokerImplem> brokers = new HashMap<String, BrokerImplem>();
	private ArrayList<Integer> portListen = new ArrayList<Integer>();
	private HashMap<Integer, PortData> portsData = new HashMap<Integer, PortData>();

	private class PortData {
		public CircularBuffer bufferReadExtern;
		public CircularBuffer bufferWriteExtern;
		public ChannelCircular channelExtern;
		public ChannelCircular channelIntern = new ChannelCircular(null, null);;
	}

	public BrokerImplem(String name) {
		super(name);
		brokers.put(name, this);
	}

	public PortData getPortData(int port) {
		return portsData.get(Integer.valueOf(port));
	};

	public void setChannelExtern(ChannelCircular channelCircular, int port) {
		PortData portData = portsData.get(Integer.valueOf(port));
		portData.channelExtern = channelCircular;
	}

	public ChannelCircular getChannelIntern(int port) {
		PortData portData = portsData.get(Integer.valueOf(port));
		return portData.channelIntern;
	}

	public boolean isPortOpen(int port) {
		return portListen.contains(port);
	}

	public void setBuffers(CircularBuffer bufferRead, CircularBuffer bufferWrite, int port) {
		PortData portData = portsData.get(Integer.valueOf(port));
		portData.bufferReadExtern = bufferRead;
		portData.bufferWriteExtern = bufferWrite;
	}

	public Channel accept(int port) {
		PortData portData = new PortData();
		portsData.put(Integer.valueOf(port), portData);

		this.portListen.add(Integer.valueOf(port));
		System.out.println("accept 1");
		synchronized (portData.channelIntern) {
			while (portData.channelExtern == null) {
				try {
					portData.channelIntern.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		ChannelCircular channel = new ChannelCircular(portData.bufferWriteExtern, portData.bufferReadExtern,
				portData.channelExtern);
		portData.channelIntern = channel;
		portData.bufferReadExtern = null;
		portData.bufferWriteExtern = null;
		portListen.remove(Integer.valueOf(port));
		return channel;
	}

	public Channel connect(String name, int port) {
		BrokerImplem broker = brokers.get(name);
		while (broker == null || !broker.isPortOpen(port)) {
			broker = brokers.get(name);
			// attente active d'un broker
		}
		PortData portData = broker.getPortData(port);
		CircularBuffer bufferRead = new CircularBuffer(256);
		CircularBuffer bufferWrite = new CircularBuffer(256);
		ChannelCircular channelCircular = new ChannelCircular(bufferRead, bufferWrite, broker.getChannelIntern(port));
		broker.setBuffers(bufferRead, bufferWrite, port);
		broker.setChannelExtern(channelCircular, port);
		synchronized (broker.getChannelIntern(port)) {
			broker.getChannelIntern(port).notify();
		}

		return channelCircular;
	}

}
