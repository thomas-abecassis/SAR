package TD1;

/**
* Represent a communication canal between two entities
**/
public class Channel {

/**
* non-blocking read throught the channel
* @param byte[] bytes - bytes reads 
* @param int offset - offset indacting where to start the reading operation
* @param int length - number of maximum bytes read
* @return int - number of actual bytes readed
**/
public int read(byte[] bytes, int offset, int length) { return 0; }

/**
* non-blocking read throught the channel
* @param byte[] bytes - bytes to write 
* @param int offset - offset indacting where to start the writing operation
* @param int length - number of maximum bytes write
* @return int - number of actual bytes writed
**/
public int write(byte[] bytes, int offset, int length) { return 0; }

/**
* disconnect the channel communication
* @return void
**/
public void disconnect() {}

/**
* return the state of the Channel
* @return boolean - true if still active, false otherwise
**/
public boolean disconnected() { return false; }
}
