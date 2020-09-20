package server_side;

public interface CacheManager <Problem, Solution> {
	
	public boolean isSolutionExist(Problem key);
	public Solution getSolution(Problem key);
	public void saveSolution(Problem key, Solution value);
}