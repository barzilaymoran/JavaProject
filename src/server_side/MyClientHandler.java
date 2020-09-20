package server_side;

import java.io.*;
import java.util.ArrayList;

public class MyClientHandler<Problem, Solution> implements ClientHandler {
	
	private Solver solver;
	private CacheManager cm;
	
	public MyClientHandler(Solver solver, CacheManager cm){
		this.solver=solver;
		this.cm=cm;
	}
	
	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient){
		BufferedReader userInput = new BufferedReader(new InputStreamReader(inFromClient));
		PrintWriter outToScreen = new PrintWriter(outToClient);
		readInputAndSend(userInput, outToScreen, "end");
	}
	
	public void readInputAndSend(BufferedReader in, PrintWriter out, String exitStr){
		try{
			String line;
			Solution returnedLine;
			
			ArrayList<ArrayList<State<String>>> board = new ArrayList<ArrayList<State<String>>>();
			String start;
			String end;
			int i=0;

			//board
			while(!(line=in.readLine()).equals(exitStr)){
				board.add(new ArrayList<State<String>>());
				String[] lineToArray = line.split(",");

				for(int j=0; j<lineToArray.length; j++){
					board.get(i).add(new State<String>(""+i+","+j, Double.parseDouble(lineToArray[j]), null, Double.parseDouble(lineToArray[j])));
				}

				i++;
			}
			
			out.flush();
			
			//start
			start = in.readLine();			
			
			//end
			end = in.readLine();
			
			GameSearchable g = new GameSearchable(board,start,end);
			
			if(cm.isSolutionExist(g)){
				returnedLine = (Solution) cm.getSolution(g);
			}
			else{
				returnedLine = (Solution)solver.solve(g);
				cm.saveSolution(g, returnedLine);	
			}
			
			out.println(returnedLine);
			out.flush();
			
		} catch (IOException e) { e.printStackTrace();}
	}
}

