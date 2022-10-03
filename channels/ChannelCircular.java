package TD1.channels;

import java.io.IOException;

public class ChannelCircular extends Channel {

	private CircularBuffer bufferRead;
	private CircularBuffer bufferWrite;
	private boolean disconect = false;
	private ChannelCircular channelExtern;

	public ChannelCircular(CircularBuffer bufferRead, CircularBuffer bufferWrite) {
		this.bufferRead = bufferRead;
		this.bufferWrite = bufferWrite;
	}

	public ChannelCircular(CircularBuffer bufferRead, CircularBuffer bufferWrite, ChannelCircular channelExtern) {
		this(bufferRead, bufferWrite);
		this.channelExtern = channelExtern;
	}

	public void setChannelExtern(ChannelCircular channelExtern) {
		this.channelExtern = channelExtern;
	}

	public int read(byte[] bytes, int offset, int length) throws IOException {
		int i = 0;
		while (i < length) {
			if (channelExtern.disconnected()) {
				this.disconnect();
				throw new IOException();
			}
			if (!bufferRead.empty()) {
				bytes[offset + i] = bufferRead.pull();
				i++;
			} else {
				// wait
			}
		}
		return i;
	}

	public int write(byte[] bytes, int offset, int length) throws IOException {
		int i = 0;
		while (i < length) {
			if (channelExtern.disconnected()) {
				this.disconnect();
				throw new IOException();
			}
			if (!bufferWrite.full()) {
				bufferWrite.push(bytes[offset + i]);
				i++;
			} else {
				// wait
			}
		}
		return i;
	}

	public void disconnect() {
		this.disconect = true;
	}

	public boolean disconnected() {
		return this.disconect;
	}

}
