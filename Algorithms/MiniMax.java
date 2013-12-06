/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import checkers.Board;
import checkers.Node;
import checkers.SolutionSpace;

/**
 *
 * @author Jack
 */
public class MiniMax extends checkers.Algorithm{
    
    public MiniMax(Board b, char piece)
    {
        super();//b, piece);
    }

    @Override
    public Board getMove(Board b, char piece) 
    {
        tree = new SolutionSpace(b, piece);
        plyDepth = tree.getPlyDepth();
        root = tree.getRoot();
        max = piece;
        if(piece == 'R')
            min = 'B';
        else
            min = 'R';
        return maximize(root, max, plyDepth).getCurrent();
    }
    
    private Node maximize(Node current, char piece, int depth)
    {
        if(depth == 1)
        {
            int maxRating = 0;
            Node maxNode = null;
            for(Node n: current.getChildren())
            {
                n.rateBoard(piece);
                if(n.getRating() > maxRating)
                {
                    maxRating = n.getRating();
                    maxNode = n;
                }
            }
            current.setRating(maxRating);
            return maxNode;
        }
        else
        {
            int maxRating = Integer.MIN_VALUE;
            Node maxNode = null;
            for(Node n: current.getChildren())
            {
                Node temp = minimize(n, min, depth-1);
                int tempRating = temp.getRating();
                if(tempRating > maxRating)
                {
                    maxRating = tempRating;
                    maxNode = n;
                }
            }
            return maxNode;
        }
    }
    
    private Node minimize(Node current, char piece, int depth)
    {
        if(depth == 1)
        {
            int minRating = Integer.MAX_VALUE;
            Node minNode = null;
            for(Node n: current.getChildren())
            {
                n.rateBoard(piece);
                if(n.getRating() < minRating)
                {
                    minRating = n.getRating();
                    minNode = n;
                }
            }
            current.setRating(minRating);
            return minNode;
        }
        else
        {
            int minRating = Integer.MAX_VALUE;
            Node minNode = null;
            for(Node n: current.getChildren())
            {
                Node temp = minimize(n, min, depth-1);
                int tempRating = temp.getRating();
                if(tempRating < minRating)
                {
                    minRating = tempRating;
                    minNode = n;
                }
            }
            return minNode;
        }
        
    }
    
}
