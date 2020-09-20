package server_side;

import java.util.ArrayList;
import java.util.List;

public class GameSearchable implements Searchable<String>{
	
	private ArrayList<ArrayList<State<String>>> board;
	private String start;
	private String end;
	
	public GameSearchable(ArrayList<ArrayList<State<String>>>board, String start, String end){
		this.board = board;
		this.start = start;
		this.end = end;
	}

	@Override
	public State<String> getInitialState() {
		Integer[] startCoor = extractCoor(this.start);
		return this.board.get(startCoor[0]).get(startCoor[1]);
	}

	@Override
	public boolean isGoalState(State<String> s) {
		int ans = s.getState().compareTo(end);
		if(ans == 0)
			return true;
		return false;
	}

	@Override
	public List<State<String>> getAllPossibleStates(State<String> s) {
		List<State<String>> successors = new ArrayList<>();
		Integer[] coor = extractCoor(s.getState());
		
		int numOfRows = board.size();
		int numOfCol = board.get(0).size();
		int x = coor[0];
		int y = coor[1];
		
		//first row
		if(x==0){
			if(y==0) {		
				successors.add(board.get(x+1).get(y));
				successors.add(board.get(x).get(y+1));
			}
			else if(y==numOfCol-1) {
				successors.add(board.get(x+1).get(y));
				successors.add(board.get(x).get(y-1));
			}
			else{
				successors.add(board.get(x).get(y-1));
				successors.add(board.get(x).get(y+1));
				successors.add(board.get(x+1).get(y));
				}
		}
		
		//last row
		else if(x==numOfRows-1){
			if(y==0) {
				successors.add(board.get(x-1).get(y));
				successors.add(board.get(x).get(y+1));
			}
			else if(y==numOfCol-1) {
				successors.add(board.get(x-1).get(y));
				successors.add(board.get(x).get(y-1));
			}
			else {		
				successors.add(board.get(x).get(y-1));
				successors.add(board.get(x).get(y+1));
				successors.add(board.get(x-1).get(y));
			}	
		}
		
		//first col
		else if(y==0){	
			successors.add(board.get(x-1).get(y));
			successors.add(board.get(x+1).get(y));
			successors.add(board.get(x).get(y+1));
		}
		
		//last col
		else if(y==numOfCol-1){
			successors.add(board.get(x-1).get(y));
			successors.add(board.get(x+1).get(y));
			successors.add(board.get(x).get(y-1));
		}
		
		//inside
		else{
			successors.add(board.get(x-1).get(y));
			successors.add(board.get(x+1).get(y));
			successors.add(board.get(x).get(y+1));
			successors.add(board.get(x).get(y-1));
		}
		
		return successors;
	}
	
	public ArrayList<ArrayList<State<String>>> getBoard() {
		return board;
	}

	public void setBoard(ArrayList<ArrayList<State<String>>> board) {
		this.board = board;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
	
	public Integer[] extractCoor (String str){
		Integer[] coordinate = new Integer[2];
		String[] strToCoor = new String[2];
		
		strToCoor = str.split(",");
		
		for(int i=0; i<coordinate.length; i++)
			coordinate[i] = Integer.parseInt(strToCoor[i]);
		
		return coordinate;
	}	
}
