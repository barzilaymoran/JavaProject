package server_side;

import java.util.*;

public abstract class CommonSearcher<T> implements Searcher<T> {
	
	protected PriorityQueue<State<T>> openList;
	private int evaluatedNodes;
	
	public CommonSearcher() {
		openList = new PriorityQueue<State<T>>();
		evaluatedNodes = 0;
	}
	
	protected State<T> popOpenList() {
		evaluatedNodes++;
		return openList.poll();
	}

	@Override
	public abstract String search(Searchable<T> s);
	
	@Override
	public int getNumberOfNodesEvaluated() {
		return evaluatedNodes;
	}
	
	protected void addToOpenList(State<T> s){
		openList.add(s);
	}
	
	protected boolean openListContains(State<T> s){
		return openList.contains(s);
	}
	
	protected void updatePriority(State<T> s, double newPriority, State<T> n){
		State<T> temp = s;
		openList.remove(s);
		temp.setCost(newPriority);
		temp.setCameFrom(n);
		addToOpenList(temp);
	}
	
	public Integer[] extractCoor (String str){
		Integer[] coordinate = new Integer[2];
		String[] strToCoor = new String[2];
		
		strToCoor = str.split(",");
		
		for(int i=0; i<coordinate.length; i++)
			coordinate[i] = Integer.parseInt(strToCoor[i]);
		
		return coordinate;
	}	
	
	protected String backTrace(State<T> end, State<T> start){
		List<String> dir = new ArrayList<>();
		String output = "";
		
		while(end.getCameFrom()!= null){
			Integer[] endCoor = extractCoor(""+end.getState());
			Integer[] cameFromCoor = extractCoor("" + end.getCameFrom().getState());
			
			System.out.println("***************************");
			System.out.println("endCoor: "+ endCoor[0]+","+ endCoor[1]);
			System.out.println("cameFromCoor: "+ cameFromCoor[0]+","+ cameFromCoor[1]);
			System.out.println("***************************");
			
			//x
			if(endCoor[0]>cameFromCoor[0])
				dir.add(",Down");
			
			else if(endCoor[0]<cameFromCoor[0])
				dir.add(",Up");
			
			//y
			else if (endCoor[1]>cameFromCoor[1])
				dir.add(",Right");
			else
				dir.add(",Left");
			
			end=end.getCameFrom();
		}
		
		Collections.reverse(dir);
		
		for(String d: dir)
			output += d;
		
		output = output.substring(1);
		return output;
	}
}
