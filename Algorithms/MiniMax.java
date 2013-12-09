/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import checkers.Board;
import checkers.Node;
import checkers.SolutionSpace;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Jack
 */
public class MiniMax extends checkers.Algorithm{
    
    public MiniMax()
    {
        super();
    }

    @Override
    public Board getMove(Board b, char piece) 
    {
        max = piece;
        if(piece == 'R')
            min = 'B';
        else
            min = 'R';
        tree = new SolutionSpace(b, max);
        plyDepth = tree.getPlyDepth();
        root = tree.getRoot();
        Board move = maximize(root, max, plyDepth).getCurrent();
        return move;
    }
    
    private Node maximize(Node current, char piece, int depth)
    {
        if(depth == 0)
        {
//            int maxRating = 0;
//            Node maxNode = current;
//            for(Node n: current.getChildren())
//            {
//                n.rateBoard(piece);
//                if(n.getRating() > maxRating)
//                {
//                    maxRating = n.getRating();
//                    maxNode = n;
//                }
//            }
//            current.setRating(maxRating);
            return current;
        }
        else
        {
            int maxRating = Integer.MIN_VALUE;
            ArrayList<Node> maxNode = new ArrayList<>();
            LinkedList<Node> children = current.getChildren();
            for(Node n: children)
            {
                Node temp = minimize(n, min, depth-1);
                temp.rateBoard(piece);
                int tempRating = temp.getRating();
                if(tempRating == maxRating)
                {
                    
                }
                else if(tempRating > maxRating)
                {
                    maxRating = tempRating;
                    maxNode.add(n);
                }
                
            }
            if(maxNode.isEmpty())
                return current;
            return maxNode.get(r.nextInt(maxNode.size()));
        }
    }
    
    private Node minimize(Node current, char piece, int depth)
    {
        if(depth == 0)
        {
//            int minRating = Integer.MAX_VALUE;
//            Node minNode = current;
//            for(Node n: current.getChildren())
//            {
//                n.rateBoard(piece);
//                if(n.getRating() < minRating)
//                {
//                    minRating = n.getRating();
//                    minNode = n;
//                }
//            }
//            current.setRating(minRating);
            return current;
        }
        else
        {
            int minRating = Integer.MAX_VALUE;
            ArrayList<Node> minNode = new ArrayList<>();
            LinkedList<Node> children = current.getChildren();
            for(Node n: children)
            {
                Node temp = maximize(n, min, depth-1);
                temp.rateBoard(piece);
                int tempRating = temp.getRating();
                if(tempRating < minRating)
                {
                    minRating = tempRating;
                    minNode.add(n);
                }
            }
            if(minNode.isEmpty())
                return current;
            return minNode.get(r.nextInt(minNode.size()));
        }
    }  
}
