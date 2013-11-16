package checkers;

import java.io.IOException;

public interface GUI 
{
	/**
	 * Display the board for the user to select a piece to move.
	 * @param b is the board.
	 */
	public void display(Board b);
	/**
	 * Display the board with the list of available moves for the user to select from for the previously selected piece.
	 * @param b is the board.
	 * @param moves is the list of moves, represented as pieces.
	 */
	public void display(Board b, Piece[] moves, char display);
	/**
	 * Gets the piece to move from the user.
	 * @param b is the board.
	 * @param piece is the character of the piece we are looking for.
	 * @return the piece on the board to move.
	 * @throws Exception if input is improper. (ie. e,f instead of 1,2. )
	 */
	public Piece getMove(Board b, char piece)throws Exception;
	/**
	 * Gets the location of movement for the piece from the user.
	 * @param b is the board.
	 * @param moves is the list of moves.
	 * @param piece is the character we are looking for in the piece.
	 * @return the piece to move to.
	 * @throws IOException if input is improper (ie. e,f instead of 1,2.)
	 */
	public Piece getMove(Board b, Piece[] moves, char piece) throws IOException;
	
	/**
	 * Standard help command
	 */
	public void help();
}
