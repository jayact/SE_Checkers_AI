package checkers.checkers;

/**
 * Abstract class that the algorithms will extend
 * @author Jack
 */

public abstract class Algorithm 
{
    protected SolutionSpace tree;
    
    /**
     * Creates an instance of Algorithm.  Used by specific algorithms to set up 
     * the common features.
     * @param b current board position
     * @param piece specifies the player to make a move
     */
        public Algorithm(Board b, char piece)
        {
            tree = new SolutionSpace(b, piece);
        }
        
        /**
         * Overall heuristic function of the board
         * @param b rated board
         * @param piece player to give value to
         * @return final heuristic value
         */
        protected int rateBoard(Board b, char piece)
        {
            int scalar1 = 1;
            int scalar2 = 1;
            return (scalar1*h1(b, piece) + (scalar2*h2(b, piece)));
        }
	/**
	 * Rates a board by the number of pieces.
	 * @param b
	 * @return
	 */
	protected int h1(Board b, char piece)
	{
            int result = 0;
            for(int i=0; i<8; i++)
            {
                for(int j=0; j<8; j++)
                {
                    Piece p = b.getPiece(i, j);
                    int pVal = 0;
                    if(p.getColor() == piece)
                        pVal++;
                    else if(p.getColor() != '-')
                        pVal--;
                    result += pVal;
                }
            }
            return result;
	}
        
        /**
         * Rates a board by the position advantage
         * @param b
         * @param piece
         * @return 
         */
        protected int h2(Board b, char piece)
        {
            int kingVal = 2;
            int result = 0;
            int[][] posVals = {{2,2,2,2,2,2,2,2},
                               {2,1,1,1,1,1,1,2},
                               {2,1,1,1,1,1,1,2},
                               {2,1,1,1,1,1,1,2},
                               {2,1,1,1,1,1,1,2},
                               {2,1,1,1,1,1,1,2},
                               {2,1,1,1,1,1,1,2},
                               {2,2,2,2,2,2,2,2}};
            
            for(int i=0; i<8; i++)
            {
                for(int j=0; j<8; j++)
                {
                    Piece p = b.getPiece(i, j);
                    int pVal = 0;
                    if(p.getColor() == piece)
                        pVal = posVals[i][j];
                    else if(p.getColor() != '-')
                        pVal = -posVals[i][j];
                    if(p.isKing())
                        pVal = pVal*kingVal;
                    result += pVal;
                }
            }
            return result;
        }
	
	/**
	 * Uses the algorithm specific to each individual class to choose a board
	 * @param b is the board
	 * @param piece is the piece
	 * @return the winning board.
	 */
	public abstract Board getMove(Board b, char piece);
}
