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
 * The purpose is to have a convenient way to search through
 * all of the possible moves. children generated alternates,
 * for minimax purposes.
 * @author Jack
 */
public class SolutionSpace {
    Node root;
    static int plyDepth = 5;      
    //decrease to increase speed
    //increase to increase skill level
    
    /**
     * Creates an instance of a SolutionSpace from board b, with the first 
     * player to move piece
     * @param b initial board state
     * @param color player to move first
     */
    public SolutionSpace(Board b, char color)
    {
        root = new Node(null, b);
        if(b.bUnits() + b.rUnits() <= 8)
            plyDepth = 7;
        try {
            helper(root, color, plyDepth);
        } catch (Exception ex) {
            Logger.getLogger(SolutionSpace.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * recursive helper method to generate children nodes
     * creates the search space for the future moves.
     * @param current is the current node
     * @param color is the color we are generating for
     * @param count the amount of iterations to repeat
     * @throws Exception
     */
    private void helper(Node current, char color, int count) throws Exception
    {
        char other;
        if(color == 'B')
            other = 'R';
        else
            other = 'B';

        if(count==0)
            return;
        if(current.getCurrent().victory())
            return;
        current.createChildren(color);
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
    
    /**
     * @return the ply depth
     */
    public int getPlyDepth()
    {
            return plyDepth;
    }
}
