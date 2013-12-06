package checkers;


import java.util.LinkedList;

/*
 * Creates a node object to hold the structure of the solution space.  Capable 
 * of generating the children of its current board for all posible moves of 
 * player the player specified by piece
 */

/**
 *
 * @author Jack
 */
public class Node {
    Node parent;
    Board current;
    LinkedList<Node> children = new LinkedList<>();
    int rating;
    private static int boardSize = 8;
    
    /**
     * Creates an instance of a Node object
     * @param parent the board state previous to the last move
     * @param current the current board state
     */
    public Node(Node parent, Board current)
    {
        this.parent = parent;
        this.current = current;
    }
    
    /**
     * Creates all possible moves from the current board and their cooresponding
     * Node object with the current position as the parent
     * @param piece specifies player that can make the move
     * @throws Exception 
     */
    public void createChildren(char piece) throws Exception
    {
        for(Board b: current.finalMoves(piece))
        {
            Node temp = new Node(this, b);
            //temp.rateBoard(b, piece);
            children.add(temp);
        }
    }
    
    /**
         * Overall heuristic function of the board
         * @param b rated board
         * @param piece player to give value to
         * @return final heuristic value
         */
        public void rateBoard(char piece)
        {
            int scalar1 = 1;
            int scalar2 = 1;
            rating = (scalar1*h1(current, piece) + (scalar2*h2(current, piece)));
        }
        
        public void setRating(int rate)
        {
            rating = rate;
        }
        
	/**
	 * Rates a board by the number of pieces.
	 * @param b
	 * @return
	 */
	private int h1(Board b, char piece)
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
        private int h2(Board b, char piece)
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
     * Accessor for parent
     * @return 
     */
    public Node getParent()
    {
        return parent;
    }
    
    /**
     * Accessor for current board
     * @return 
     */
    public Board getCurrent()
    {
        return current;
    }
    
    /**
     * Accessor for children Nodes
     * @return 
     */
    public LinkedList<Node> getChildren()
    {
        return children;
    }
    
    /**
     * Accessor for current rating
     * @return 
     */
    public int getRating()
    {
        return rating;
    }
    
}
