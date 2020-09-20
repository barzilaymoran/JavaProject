package server_side;

import java.io.InputStream;
import java.io.OutputStream;

//determines type of conversation with client
public interface ClientHandler {
	
	/*input - reads client msgs
	  output - writes server response */
	public void handleClient(InputStream inFromClient, OutputStream outToClient);
}

