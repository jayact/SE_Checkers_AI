package checkers;


import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Creates a node object to hold the structure of the solution space.  Capable 
 * of generating the children of its current board for all posible moves of 
 * player the player specified by piece
 */

/**
 *
 * @author Jack
 */
public class Node implements Comparable{
    Node parent;
    Board current;
    LinkedList<Node> children = new LinkedList<>();
    int rating;
    private static QuickSort<Node> q = new QuickSort<Node>();
    
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
    
    public Node()
    {
        
    }
    
    /**
     * Calls the sorting method to sort the children
     */
    public void sortChildren()
    {
        q.sort(children);
    }
    
    /**
     * Creates all possible moves from the current board and their corresponding
     * Node object with the current position as the parent
     * @param piece specifies player that can make the move
     * @throws Exception 
     */
    public void createChildren(char color)
    {
        ArrayList<Piece> moveable = current.allJumps(color);
        if(moveable.size() != 0) //if it's a jump, take it. find the final states and return it.
        {
            for(Piece move : moveable)
            {
                ArrayList<Board> temp = childrenHelper(current, move);
                for(Board t : temp)
                {
                    children.add(new Node(this, t));
                }
            }
        }
        else //else, make the moves and return it.
        {
            moveable = current.allMoves(color);
            for(Piece move : moveable)
            {
                ArrayList<Piece> move_list= current.validMoves(move);
                for(Piece move_to : move_list)
                {
                    Board temp = new Board(current);
                    try {
						temp.makeMove(move, move_to);
						children.add(new Node(this, temp));
					} catch (Exception e) {
						System.out.println("Move not made: " + move + "-->" + move_to);
					}
                    
                }
            }
        }
    }
   
    /**
     * This specifically gets the jumps, and recursive jumps.
     * @param move is the piece to move
     * @return the final jump states
     * @throws Exception
     */
    private ArrayList<Board> childrenHelper(Board b, Piece move)
    {
        ArrayList<Board> result = new ArrayList<Board>();
        ArrayList<Piece> move_list = b.validJumps(move);
        for(Piece move_to : move_list)
        {
            Board temp = new Board(b);
            boolean remain;
			try {
				remain = temp.makeMove(move, move_to);
				if(remain == true)
	            {
	                ArrayList<Board> future_list = childrenHelper(temp, temp.getPiece(move_to.getPosition()[0], move_to.getPosition()[1]));
	                for(Board future : future_list)
	                {
	                    result.add(future);
	                }
	                if(future_list.size() == 0)
	                	result.add(temp);
	            }
	            else
	            {
	                result.add(temp);
	            }
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
            
        }
        return result;
    }
    
    /**
         * Overall heuristic function of the board
         * @param b rated board
         * @param color player to give value to
         * @return final heuristic value
         */
        public void rateBoard(char color)
        {
        	if(current.victory('B') || current.victory('R'))
        	{
        		if(current.winner() == color)
        			rating = Integer.MAX_VALUE;
        		else 
					rating = Integer.MIN_VALUE;
        	}
        	else
        	{
	            int scalar1 = 0;
	            int scalar2 = 0;
	            int scalar3 = 2;
	            int scalar4 = 1;
	            rating = (scalar1*h1(current, color) + (scalar2*h2(current, color)) + (scalar3*h3(current, color)) + scalar4*h4(current,color));
        	}
        }
        
        public void setRating(int rate)
        {
            rating = rate;
        }
        
	/**
	 * Rates a board by the number of kings.
	 * @param b
	 * @return
	 */
	private int h1(Board b, char color)
	{
            int result = 0;
            for(int i=0; i<8; i++)
            {
                for(int j=0; j<8; j++)
                {
                    Piece p = b.getPiece(i, j);
                    if(p.getColor()==color){
                        result++;
                        if(p.isKing())
                            result++;
                    }
                    else if(p.getColor() != '-')
                    {
                        result--;
                        if(p.isKing())
                            result--;
                    }
                }
            }
            return result;
	}
        
        /**
         * Rates a board by the position advantage
         * @param b
         * @param color
         * @return 
         */
        private int h2(Board b, char color)
        {
            int kingVal = 2;
            int result = 0;
            int[][] posVals = {{2,1,1,1,1,1,1,2},
                               {2,1,1,1,1,1,1,2},
                               {2,1,1,1,1,1,1,2},
                               {2,1,1,1,1,1,1,2},
                               {2,1,1,1,1,1,1,2},
                               {2,1,1,1,1,1,1,2},
                               {2,1,1,1,1,1,1,2},
                               {2,1,1,1,1,1,1,2}};
            
            for(int i=0; i<8; i++)
            {
                for(int j=0; j<8; j++)
                {
                    Piece p = b.getPiece(i, j);
                    int pVal = 0;
                    if(p.getColor() == color)
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
        
        private int h3(Board b, char color)
        {
        	if(color == 'B')
        		return b.bUnits() - b.rUnits();
        	return b.rUnits() - b.bUnits();
        }
        
        private int h4(Board b, char color)
        {
        	int result = 0;
        	for(int i = 0; i < 8; i++)
        	{
        		for(int j = 0; j < 8; j++)
        		{
        			Piece p = b.getPiece(i, j);
        			if(p.getColor() == color && p.isKing() == true)
        			{
        				result++;
        			}
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

    @Override
    public int compareTo(Object o) 
    {
        Node n = (Node)o;
        if(this.getRating() < n.getRating())
            return -1;
        else if(this.getRating() > n.getRating())
            return 1;
        else 
            return 0;
    }
    
    public String toString()
    {
    	return current.toString();
    }
}
