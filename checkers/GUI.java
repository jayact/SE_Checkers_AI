package checkers;

import java.io.IOException;
import java.util.ArrayList;

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
	public void display(Board b, ArrayList<Piece> moves, char display, Piece orig);
	/**
	 * Gets the piece to move from the user.
	 * @param b is the board.
	 * @param color is the character of the piece we are looking for.
	 * @return the piece on the board to move.
	 * @throws Exception if input is improper. (ie. e,f instead of 1,2. )
	 */
	public Piece getMove(Board b, char color)throws Exception;
	/**
	 * Gets the location of movement for the piece from the user.
	 * @param b is the board.
	 * @param moves is the list of moves.
	 * @param color is the character we are looking for in the piece.
	 * @param orig is the original piece being movved. (for deselect check)
	 * @return the piece to move to.
	 * @throws IOException if input is improper (ie. e,f instead of 1,2.)
	 */
	public Piece getMove(Board b, ArrayList<Piece> moves, char color, Piece orig) throws IOException;
	
	/**
	 * Standard help command
	 */
	public void help();
        
	/**
	 * adds text to the console
	 * @param s
	 */
        public void append(String s);
        
        /**
         * Declares the winner
         * @param winner
         */
        public void victory(char winner);
        
        /**
         * clears the console
         */
        public void clear();
}
