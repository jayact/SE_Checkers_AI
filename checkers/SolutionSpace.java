package checkers;

import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * Creates a solution space of all possible moves from a root board position,
 * alternating the moves from player to player.  The solution space will go 
 * a to a fixed depth that can be changed to either increase speed or increase
 * skill level
 */

/**
 *
 * @author Jack
 */
public class SolutionSpace {
    Node root;
    int plyDepth = 5;      
    //decrease to increase speed
    //increase to increase skill level
    
    /**
     * Creates an instance of a SolutionSpace from board b, with the first 
     * player to move piece
     * @param b initial board state
     * @param piece player to move first
     */
    public SolutionSpace(Board b, char piece)
    {
        root = new Node(null, b);
        try {
            helper(root, piece, plyDepth);
        } catch (Exception ex) {
            Logger.getLogger(SolutionSpace.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /*
     * recursive helper method to generate children nodes
     */
    private void helper(Node current, char piece, int count) throws Exception
    {
        char other;
        if(piece == 'B')
            other = 'R';
        else
            other = 'B';

        if(count==0)
            return;
        current.createChildren(piece);
        for(int i=0; i<current.children.size(); i++)
        {
            helper(current.children.get(i), other, count-1);
        }
    }
    
    /**
     * @return the root position of the SolutionSpace
     */
    public Node getRoot()
    {
        return root;
    }
    
}
