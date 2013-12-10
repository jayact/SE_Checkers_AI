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

    public NegaScout() {
        super();
    }

    /**
     * Uses the negascout algorithm to decide what move to make.
     *
     * @param b present board state
     * @param piece maximizing player
     * @return move that involves the least risk from the current board state
     */
    @Override
    public Board getMove(Board b, char piece) {
        max = piece;
        if (piece == 'R') {
            min = 'B';
        } else {
            min = 'R';
        }
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

    private Node maximize(Node current, char piece, int depth, Node alpha, Node beta) {
        if (depth == 0) {
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
        } else {
            int maxRating = alpha.getRating();
            ArrayList<Node> maxNode = new ArrayList<>();
            LinkedList<Node> children = current.getChildren();
            //Node alphaNode = current;
            for (Node n : children) {
                char ctemp = max;
                max = min;
                min = ctemp;
                //Node temp = maximize(n, min, depth-1);
                Node temp = maximize(n, max, depth - 1, alpha, beta);
                temp.rateBoard(piece);
                int tempRating = temp.getRating();
//                if(tempRating > alpha)
//                {
//                    alpha = tempRating;
//                    alphaNode = n;
//                }
                if (tempRating > maxRating) {
                    alpha = n;
                    maxNode.add(n);
                }
            }
            if (maxNode.isEmpty()) {
                return beta;
            }
            return maxNode.get(r.nextInt(maxNode.size()));
        }
    }
}
