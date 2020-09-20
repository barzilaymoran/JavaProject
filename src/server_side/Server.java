package server_side;

public interface Server {
	
	void open (int port, ClientHandler c); //opens server and waits for clients
	void stop(); //closes the server
	public void run();

}

