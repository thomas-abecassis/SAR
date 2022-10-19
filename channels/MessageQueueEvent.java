package TD1.channels;

abstract class MessageQueueEvent {
	interface Listener {
		void received(byte[] msg);

		void sent(byte[] bytes, int offset, int length, Object cookie);

		void closed();
	}

	void setListener(Listener l) {
	}

	boolean send(byte[] bytes, Object cookie) {
		return false;
	}

	boolean send(byte[] bytes, int offset, int length, Object cookie) {
		return false;
	}

	void close() {
	}

	boolean closed() {
		return false;
	}
}