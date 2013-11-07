package checkers;

public abstract class Player 
{
	protected char piece;
	public abstract Board takeTurn(Board b, GUI gui) throws Exception;
	
	public Player(char player)
	{
		this.piece = player;
	}
}
