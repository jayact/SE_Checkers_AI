package checkers;

import java.util.Random;

/**
 * Abstract class that the algorithms will extend.
 * Different algorithms have different ways of searching through solution space.
 * @author Jack
 */

public abstract class Algorithm 
{
    protected SolutionSpace tree;
    protected int plyDepth;
    protected Node root;
    protected char max;
    protected char min;
    protected Random r = new Random();
    
    /**
     * Creates an instance of Algorithm.  Used by specific algorithms to set up 
     * the common features.
     * @param b current board position
     * @param piece specifies the player to make a move
     */
        public Algorithm()//Board b, char piece)
        {
        }
        
        
	
	/**
	 * Uses the algorithm specific to each individual class to choose a board
	 * @param b is the board
	 * @param piece is the piece
	 * @return the winning board.
	 */
	public abstract Board getMove(Board b, char piece);
}
