/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.ArrayList;

import checkers.Algorithm;
import checkers.Board;
import checkers.Node;
import checkers.SolutionSpace;

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
        tree = new SolutionSpace(b, piece);
        plyDepth = tree.getPlyDepth();
        root = tree.getRoot();
        root.sortChildren();
        Node alpha = new Node();
        alpha.setRating(Integer.MIN_VALUE);
        Node beta = new Node();
        beta.setRating(Integer.MAX_VALUE);
        return maximize(root, piece, plyDepth, alpha, beta).getCurrent();
    }
    
    private Node maximize(Node current, char piece, int depth, Node alpha, Node beta)
    {
        if(depth == 0 || current.getRating() == Integer.MAX_VALUE || current.getRating() == Integer.MIN_VALUE)
        {
        	if(alpha.getRating() > current.getRating())
        		return alpha;
        	return current;
        }
        else
        {
        	char other;
        	if(piece == 'B')
        		other = 'R';
        	else
        		other = 'B';
            int maxRating = alpha.getRating();
            ArrayList<Node> maxNode = new ArrayList<Node>();
            //maxNode.add(alpha);
            for(Node n: current.getChildren())
            {
                Node temp = minimize(n, other, depth-1, alpha, beta);
                temp.rateBoard(piece);
                int tempRating = temp.getRating();
                if(tempRating == maxRating)
                {
                	maxNode.add(n);
                }
                else if(tempRating > maxRating)
                {
                	maxRating = tempRating;
                    alpha = n;
                    maxNode.clear();
                    maxNode.add(n);
                }
            }
            if(maxNode.size() == 0)
            {
            	if(alpha.getRating() > current.getRating())
            		return alpha;
            	return current;
            }
            return maxNode.get(r.nextInt(maxNode.size()));
        }
    }
    
    private Node minimize(Node current, char piece, int depth, Node alpha, Node beta)
    {
        if(depth == 0 || current.getRating() == Integer.MAX_VALUE || current.getRating() == Integer.MIN_VALUE)
        {
        	if(beta.getRating() < current.getRating())
        		return beta;
        	return current;
        }
        else
        {
        	char other;
        	if(piece == 'B')
        		other = 'R';
        	else
        		other = 'B';
            int minRating = beta.getRating();
            ArrayList<Node> minNode = new ArrayList<Node>();
            //minNode.add(beta);
            //Node betaNode = current;
            for(Node n: current.getChildren())
            {
                Node temp = maximize(n, other, depth-1, alpha, beta);
                temp.rateBoard(piece);
                int tempRating = temp.getRating();
                if(tempRating == minRating)
                {
                	minNode.add(n);
                }
                else if(tempRating < minRating)
                {
                	minRating = tempRating;
                    beta = n;
                    minNode.clear();
                    minNode.add(n);
                }
            }
            if(minNode.size() == 0)
            {
            	if(beta.getRating() < current.getRating())
            		return beta;
            	return current;
            }
            return minNode.get(r.nextInt(minNode.size()));
        }
        
    }
    
    
}
