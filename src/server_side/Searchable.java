package server_side;

import java.util.List;

public interface Searchable<T> {
	
	State<T> getInitialState();
	boolean isGoalState(State<T> s);
	List<State<T>> getAllPossibleStates(State<T>s);
}
