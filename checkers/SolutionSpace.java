package checkers;

import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jack
 */
public class SolutionSpace {
    Node root;
    int plyDepth = 5;
    
    public SolutionSpace(Board b, char piece)
    {
        root = new Node(null, b);
        try {
            helper(root, piece, plyDepth);
        } catch (Exception ex) {
            Logger.getLogger(SolutionSpace.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void helper(Node current, char piece, int count) throws Exception
    {
        char other;
        if(piece == 'b')
            other = 'r';
        else
            other = 'b';

        if(count==0)
            return;
        current.createChildren(piece);
        for(int i=0; i<current.children.size(); i++)
        {
            helper(current.children.get(i), other, count-1);
        }
    }
    
    public Node getRoot()
    {
        return root;
    }
    
}
