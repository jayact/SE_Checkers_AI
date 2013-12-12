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

/**
 *
 * @author Jack
 */
public class NegaMax extends checkers.Algorithm {

    public NegaMax() {
        super();
    }

    /**
     * Uses the negamax algorithm to decide what move to make.
     *
     * @param b present board state
     * @param piece maximizing player
     * @return move that involves the least risk from the current board state
     */
    @Override
    public Board getMove(Board b, char piece) {
        tree = new SolutionSpace(b, piece);
        tree.setPlyDepth(4);
        plyDepth = tree.getPlyDepth();
        root = tree.getRoot();
        Board move = maximize(root, piece, plyDepth).getCurrent();
        return move;
    }

    private Node maximize(Node current, char piece, int depth) {
        if (depth == 0 || current.getRating() == Integer.MAX_VALUE || current.getRating() == Integer.MIN_VALUE) 
        {
            return current;
        } 
        else 
        {
            int maxRating = Integer.MIN_VALUE;
            ArrayList<Node> maxNode = new ArrayList<>();
            LinkedList<Node> children = current.getChildren();
            for (Node n : children) {
                char other;
            	if(piece == 'B')
            		other = 'R';
            	else
            		other = 'B';
                Node temp = maximize(n, other, depth - 1);
                temp.rateBoard(piece);
                int tempRating = temp.getRating();
                if (tempRating == maxRating){
                	maxNode.add(n);
                } 
                else if (tempRating > maxRating){
                    maxRating = tempRating;
                    maxNode.clear();
                    maxNode.add(n);
                }

            }
            if(maxNode.size() == 0)
            	return current;
            return maxNode.get(r.nextInt(maxNode.size()));
        }
    }
}
