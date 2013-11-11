package checkers;

public abstract class Algorithm 
{
	/**
	 * Rates a board by a heuristic value.
	 * @param b
	 * @return
	 */
	protected int rateBoard(Board b)
	{
		return 0;
	}
	
	/**
	 * Uses the algorithm specific to each individual class to choose a board
	 * @param b is the board
	 * @param piece is the piece
	 * @return the winning board.
	 */
	public abstract Board getMove(Board b, char piece);
}
