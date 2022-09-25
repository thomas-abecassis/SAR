package TD1;

/*
*  Etablit la connexion entre deux taches. Cree les Channel.
*/
public abstract class Broker {
	
	private String name;
  public Broker(String name) {
	  this.name=name;
  }

  /**
  * Attend (bloquant) la connexion d'une tache sur un port donne
  * @param int port - numero de port sur lequel attendre la connexion
  * @return Channel - canal de communication avec l'autre tache
  **/
  public Channel accept(int port) { return null; }

 /**
  * Connexion bloquante à une tache d'un nom ainsi que d'un port donnes
  * @param int port - numero de port sur lequel se connecter
  * @param String name - nom de la tache 
  * @return Channel - canal de communication entre les taches
  **/
  public Channel connect(String name, int port) { return null; }
}
