package checkers.checkers;


import java.util.ArrayList;
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
    Board parent;
    Board current;
    LinkedList<Node> children = new LinkedList<>();
    private static int boardSize = 8;
    
    /**
     * Creates an instance of a Node object
     * @param parent the board state previous to the last move
     * @param current the current board state
     */
    public Node(Board parent, Board current)
    {
        this.parent = parent;
        this.current = current;
    }
    
    /**
     * Creates all possible moves from the current board and their cooresponding
     * Node object with the current position as the parent
     * @param color specifies player that can make the move
     * @throws Exception 
     */
    public void createChildren(char color) throws Exception
    {
    	ArrayList<Piece> moveable = parent.allJumps(color);
    	if(moveable.size() != 0)
    	{
    		for(Piece move : moveable)
    		{
    			
    		}
    	}
    	else
    	{
    		moveable = parent.allMoves(color);
    		for(Piece move : moveable)
    		{
    			
    		}
    	}
        //for(Board b: current.finalMoves(piece))
        //{
         //   children.add(new Node(current, b));
        //}
//        Board temp = current;
//        for(int i=0;i<boardSize;i++)
//        {
//            for(int j=0;j<boardSize;j++)
//            {
//                Piece tempP = temp.getPiece(i,j);
//                if(tempP.getColor() == piece)
//                {
//                    for(Piece p: temp.finalMoves(tempP))
//                    {
//                        temp.makeMove(tempP, p);
//                        children.add(new Node(current, temp));
//                        temp = current;
//                    }
//                }
//            }
//        }
    }
    
    
    /**
     * Accessor for parent
     * @return 
     */
    public Board getParent()
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
    
}
