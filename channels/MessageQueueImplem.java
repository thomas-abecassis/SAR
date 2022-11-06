package TD1.channels;

import java.io.IOException;

public class MessageQueueImplem extends MessageQueue {

	private Channel channel;

	public MessageQueueImplem(Channel channel) {
		this.channel = channel;
	}

	/**
	 * écriture bloquante et SYNCHRONISE d'un message de longueur max 256 octets
	 * dans le canal
	 * 
	 * @param byte[] bytes - tableau contenant le message à ecrire
	 * @param int    offset - offset indicant le decalage par lequel les octets vont
	 *               être écrits
	 * @param int    length - taille maximum d'octets à ecrire
	 * @throws IOException              - Lève une exception lorsqu'il y a une
	 *                                  erreur dans la communication
	 * @throws IllegalArgumentException - Lève une exception lorsque le message
	 *                                  dépasse les 256 octets
	 **/
	public void write(byte[] bytes, int offset, int length) throws IOException {
		if (length > 256)
			throw new IllegalArgumentException();
		byte messageLength = (byte) (((byte) length) & 0xFF);
		byte[] lengthArray = { messageLength };
		channel.write(lengthArray, 0, 1);
		channel.write(bytes, offset, length);
	}

	/**
	 * Lecture bloquante d'un message dans le canal
	 * 
	 * @throws IOException - Lève une exception lorsqu'il y a une erreur dans la
	 *                     communication
	 * @return byte[] - message lu
	 **/
	public byte[] receive() throws IOException {
		byte[] lengthArray = new byte[1];
		channel.read(lengthArray, 0, 1);

		byte[] msgArray = new byte[lengthArray[0]];
		channel.read(msgArray, 0, lengthArray[0]);
		return msgArray;
	}

	/**
	 * deconnecte le canal de communication
	 * 
	 * @return void
	 **/
	public void close() {
		channel.disconnect();
	}

	/**
	 * rretourne l'etat du canal
	 * 
	 * @return boolean - true si il est actif, false sinon
	 **/
	public boolean closed() {
		return channel.disconnected();
	}
}
