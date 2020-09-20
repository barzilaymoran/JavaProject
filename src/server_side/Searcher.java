package server_side;

public interface Searcher<T> {
	
	public String search(Searchable<T> s);
	public int getNumberOfNodesEvaluated();
}
