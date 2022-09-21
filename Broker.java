package TD1;

/*
* Intermediate between clients allowing them to connect between each others
*/
public class Broker {
  public Broker(String name) {}

  /**
  * wait a client connexion on given port
  * @param int port - port number to listen to
  * @return Channel - Channel communication with the client
  **/
  public Channel accept(int port) { return null; }

 /**
  * Connect to a server with given name and port
  * @param int port - server port number
  * @param String name - server name
  * @return Channel - Channel communication with the Server   
  **/
  public Channel connect(String name, int port) { return null; }
}
