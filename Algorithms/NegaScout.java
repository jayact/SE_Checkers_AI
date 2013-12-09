/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import checkers.Algorithm;
import checkers.Board;
import checkers.Node;
import checkers.SolutionSpace;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Jack
 */
public class NegaScout extends Algorithm {
    
    public NegaScout()
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
        root.sortChildren();
        Node alpha = new Node();
        alpha.setRating(Integer.MIN_VALUE);
        Node beta = new Node();
        beta.setRating(Integer.MAX_VALUE);
        return maximize(root, max, plyDepth, alpha, beta).getCurrent();
    }
    
    private Node maximize(Node current, char piece, int depth, Node alpha, Node beta)
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
            //current.rateBoard(piece);
            return current;
        }
        else
        {
            int maxRating = alpha.getRating();
            ArrayList<Node> maxNode = new ArrayList<>();
            LinkedList<Node> children = current.getChildren();
            //Node alphaNode = current;
            for(Node n: children)
            {
                Node temp = minimize(n, min, depth-1, alpha, beta);
                temp.rateBoard(piece);
                int tempRating = temp.getRating();
//                if(tempRating > alpha)
//                {
//                    alpha = tempRating;
//                    alphaNode = n;
//                }
                if(tempRating > maxRating)
                {
                    alpha = n;
                    maxNode.add(n);
                }
            }
            if(maxNode.isEmpty())
                return beta;
            return maxNode.get(r.nextInt(maxNode.size()));
        }
    }
    
    private Node minimize(Node current, char piece, int depth, Node alpha, Node beta)
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
            //current.rateBoard(piece);
            return current;
        }
        else
        {
            int minRating = beta.getRating();
            ArrayList<Node> minNode = new ArrayList<>();
            LinkedList<Node> children = current.getChildren();;
            //Node betaNode = current;
            for(Node n: children)
            {
                Node temp = maximize(n, min, depth-1, alpha, beta);
                temp.rateBoard(piece);
                int tempRating = temp.getRating();
                if(tempRating < minRating)
                {
                    beta = n;
                    minNode.add(n);
                }
            }
            if(minNode.isEmpty())
                return beta;
            return minNode.get(r.nextInt(minNode.size()));
        }
        
    }
    
    
}
