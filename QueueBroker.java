package TD1;

public  abstract class QueueBroker {
	
	public QueueBroker(String name) {
	}

	  /**
	  * Attend (bloquant) la connexion d'un broker sur un port donne
	  * @param int port - numero de port sur lequel attendre la connexion
	  * @return MessageQueue - canal de communication avec l'autre tache
	  **/
	  public MessageQueue accept(int port) { return null; }

	 /**
	  * Connexion bloquante à un broker d'un nom ainsi que d'un port donnes
	  * @param int port - numero de port sur lequel se connecter
	  * @param String name - nom de la tache 
	  * @return MessageQueue - canal de communication entre les taches
	  **/
	  public MessageQueue connect(String name, int port) { return null; }
}
