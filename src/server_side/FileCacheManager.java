package server_side;
import java.util.*;

public class FileCacheManager<Problem, Solution> implements CacheManager<Problem, Solution>
{
	private Hashtable<Problem, Solution> map;
	
	public FileCacheManager(){
		map = new Hashtable<Problem, Solution>();
	}
	
	@Override
	public boolean isSolutionExist(Problem key){
		return map.containsKey(key);
	}
	
	@Override
	public Solution getSolution(Problem key){
		return map.get(key);
	}
	
	@Override
	public void saveSolution(Problem key, Solution value){
		map.put(key,value);
	}
}







