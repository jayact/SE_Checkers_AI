package checkers.checkers;

public abstract class Player 
{
	protected char piece;
	/**
	 * Takes the turn, whether it be by algorithm or by user input.
	 * @param b is the board.
	 * @param gui is the gui.
	 * @return the finished turn, represented as a board
	 * @throws Exception
	 */
	public abstract Board takeTurn(Board b, GUI gui) throws Exception;
	
	public Player(char player)
	{
		this.piece = player;
	}
}
