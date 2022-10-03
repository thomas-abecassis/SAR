package TD1.channels;

import java.io.IOException;

public abstract class MessageQueue {

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
	public void write(byte[] bytes, int offset, int length) {
	}

	/**
	 * Lecture bloquante d'un message dans le canal
	 * 
	 * @throws IOException - Lève une exception lorsqu'il y a une erreur dans la
	 *                     communication
	 * @return byte[] - message lu
	 **/
	public byte[] receive() {
		return null;
	}

	/**
	 * deconnecte le canal de communication
	 * 
	 * @return void
	 **/
	public void close() {
	}

	/**
	 * rretourne l'etat du canal
	 * 
	 * @return boolean - true si il est actif, false sinon
	 **/
	public boolean closed() {
		return false;
	}
}
