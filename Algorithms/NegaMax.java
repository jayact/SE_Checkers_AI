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
        max = piece;
        if (piece == 'R') {
            min = 'B';
        } else {
            min = 'R';
        }
        tree = new SolutionSpace(b, max);
        plyDepth = tree.getPlyDepth();
        root = tree.getRoot();
        Board move = maximize(root, max, plyDepth).getCurrent();
        return move;
    }

    private Node maximize(Node current, char piece, int depth) {
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
            return current;
        } else {
            int maxRating = Integer.MIN_VALUE;
            ArrayList<Node> maxNode = new ArrayList<>();
            LinkedList<Node> children = current.getChildren();
            for (Node n : children) {
                char ctemp = max;
                max = min;
                min = ctemp;
                //Node temp = maximize(n, min, depth-1);
                Node temp = maximize(n, max, depth - 1);
                temp.rateBoard(piece);
                int tempRating = temp.getRating();
                if (tempRating == maxRating) {
                } else if (tempRating > maxRating) {
                    maxRating = tempRating;
                    maxNode.add(n);
                }

            }
            if (maxNode.isEmpty()) {
                return current;
            }
            return maxNode.get(r.nextInt(maxNode.size()));
        }
    }

//    private Node minimize(Node current, char piece, int depth) {
//        if (depth == 0) {
////            int minRating = Integer.MAX_VALUE;
////            Node minNode = current;
////            for(Node n: current.getChildren())
////            {
////                n.rateBoard(piece);
////                if(n.getRating() < minRating)
////                {
////                    minRating = n.getRating();
////                    minNode = n;
////                }
////            }
////            current.setRating(minRating);
//            return current;
//        } else {
//            int minRating = Integer.MAX_VALUE;
//            ArrayList<Node> minNode = new ArrayList<>();
//            LinkedList<Node> children = current.getChildren();
//            for (Node n : children) {
//                Node temp = maximize(n, min, depth - 1);
//                temp.rateBoard(piece);
//                int tempRating = temp.getRating();
//                if (tempRating < minRating) {
//                    minRating = tempRating;
//                    minNode.add(n);
//                }
//            }
//            if (minNode.isEmpty()) {
//                return current;
//            }
//            return minNode.get(r.nextInt(minNode.size()));
//        }
//    }
}
