
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
    
    public SolutionSpace(Board b, char piece)
    {
        root = new Node(null, b);
    }
    
    public Node getRoot()
    {
        return root;
    }
    
}
