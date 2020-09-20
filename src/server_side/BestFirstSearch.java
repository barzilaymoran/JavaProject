package server_side;
import java.util.*;

public class BestFirstSearch<T> extends CommonSearcher<T>{

	@Override
	public String search(Searchable<T> s) {
		Integer[] coordinate = new Integer[2];
		String[] strToCoor = new String[2];
		openList.clear();
		
		addToOpenList(s.getInitialState());
		HashSet<State<T>> closedSet = new HashSet<State<T>>();
		
		while(openList.size()>0){
			State<T> n = popOpenList();// dequeue
			closedSet.add(n);
			
			if(s.isGoalState(n))
				return backTrace(n, s.getInitialState());
			
			List<State<T>> successors = s.getAllPossibleStates(n); 
			
			for(State<T> state : successors){
				if(!closedSet.contains(state) && ! openListContains(state)){
					state.setCameFrom(n);
					state.setCost(n.getCost() + state.getValue());
					addToOpenList(state);
				} 
				
				else if (n.getCost() + state.getValue() < state.getCost()){
					if(!openListContains(state)){
						state.setCameFrom(n);
						state.setCost(n.getCost() + state.getValue());
						addToOpenList(state);
					}	
					else{
						updatePriority(state, n.getCost() + state.getValue() , n);
					}		
				}
			}
		}
		return "";
	}		
}
