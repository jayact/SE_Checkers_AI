package checkers;



public abstract class Algorithm 
{
    protected SolutionSpace tree;
    
        public Algorithm(Board b, char piece)
        {
            tree = new SolutionSpace(b, piece);
        }
        
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
            int hvalue = 0;
		if(piece=='R')
                {
                    hvalue += (b.rUnits()-b.bUnits());
                }
                else
                {
                    hvalue += (b.bUnits()-b.rUnits());
                }
             return hvalue;
	}
        
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
                    if(p.getColor()==piece)
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
