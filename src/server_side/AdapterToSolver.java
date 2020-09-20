package server_side;

public class AdapterToSolver<T, Solution> implements Solver<Searchable<T>, Solution>{
	
	private Searcher<T> searcher;
	
	public AdapterToSolver(Searcher<T>s){
		searcher=s;
	}

	@Override
	public Solution solve(Searchable<T> game) {
		return (Solution) searcher.search(game);
	}
}
