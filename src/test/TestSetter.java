package test;

import server_side.*;


public class TestSetter {
	
	static Server s; 
	
	public static void runServer(int port) {
		// put the code here that runs your server
		Solver<Searchable<String>, String> solver = new AdapterToSolver<String, String>(new BestFirstSearch());
		MyClientHandler<Searchable<String>, String> cm = new MyClientHandler(solver,new FileCacheManager<String, String>());
		s=new MySerialServer(port, cm); // initialize
		s.run();
	}

	public static void stopServer() {
		s.stop();
	}
}
