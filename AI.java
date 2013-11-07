package checkers;

public class AI  extends Player{
	private Algorithm algo;
	
	public AI(char piece, Algorithm algo)
	{
		super(piece);
		setAlgo(algo);
	}
	
	public void setAlgo(Algorithm algo)
	{
		this.algo = algo;
	}
	
	@Override
	public Board takeTurn(Board b, GUI gui) {
		// TODO Auto-generated method stub
		return null;
	}

}
