package checkers;

public class AI  extends Player{
	private Algorithm algo;
	
	public AI(char piece, Algorithm algo)
	{
		super(piece);
		setAlgo(algo);
	}
	
	/**
	 * Sets the algorithm for the AI to use
	 * @param algo is the algorithm to set
	 */
	public void setAlgo(Algorithm algo)
	{
		this.algo = algo;
	}
	
	
	/**
	 * Takes the turn.
	 * @Override
	 */
	public Board takeTurn(Board b, GUI gui) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
