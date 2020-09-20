package server_side;

public class State<T> implements Comparable<State<T>>{
	
	private T state;
	private double cost;
	private State<T> cameFrom;
	private final double value;
	
	public State(T state, double cost, State<T> cameFrom, double value){
		this.state = state;
		this.cost = cost;
		this.cameFrom = cameFrom;
		this.value = value;
	}
	
	public boolean equals(State<T> s){
		return state.equals(s.state);
	}

	public T getState() {
		return state;
	}

	public void setState(T state) {
		this.state = state;
	}
	
	public double getValue() {
		return value;
	}
	
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public State<T> getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	
	public int hashCode() {
		return ("" + state + cost + cameFrom + value).hashCode();
	}

	@Override
	public int compareTo(State<T> s) {
		if(this.cost > s.getCost())
			return 1;
		else if (this.cost < s.getCost())
			return -1;
		return 0;
	}
}
