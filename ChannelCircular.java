package TD1;

import java.util.Iterator;

public class ChannelCircular extends Channel{
	
	private CircularBuffer bufferRead;
	private CircularBuffer bufferWrite;
	private boolean disconect = false;
	private ChannelCircular channelExtern;

	public ChannelCircular(CircularBuffer bufferRead, CircularBuffer bufferWrite) {
		this.bufferRead=bufferRead;
		this.bufferWrite=bufferWrite;
	}
	
	public ChannelCircular(CircularBuffer bufferRead, CircularBuffer bufferWrite, ChannelCircular channelExtern) {
		this(bufferRead, bufferWrite);
		this.channelExtern = channelExtern;
	}
	
	public void setChannelExtern(ChannelCircular channelExtern) {
		this.channelExtern = channelExtern;
	}
	
	public int read(byte[] bytes, int offset, int length) { 
		int i =0;
		for (int j = 0; j < offset; j++) {
			if(!bufferRead.empty() ) {
				bufferRead.pull();
			}
			else {
				//wait
			}
		}
		while(i <length) {
			if(!bufferRead.empty() ) {
				bytes[i]=bufferRead.pull();
				i++;
			}
			else {
				//wait
			}
		}
		return i;
	}

	public int write(byte[] bytes, int offset, int length) { 
		int i =0;
		for (int j = 0; j < offset; j++) {
			if(!bufferWrite.full() ) {
				bufferWrite.push((byte) 0);
			}
			else {
				//wait
			}
		}
		while(i <length) {
			if(!bufferWrite.full() ) {
				bufferWrite.push(bytes[i]);
				i++;
			}
			else {
				//wait
			}
		}
		return i;
	}

	public void disconnect() {
		this.disconect = true;
	}

	public boolean disconnected() { return false; }
	
}
