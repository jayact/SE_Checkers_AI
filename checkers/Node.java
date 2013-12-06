package checkers.checkers;


import java.util.LinkedList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jack
 */
public class Node {
    Board parent;
    Board current;
    LinkedList<Node> children = new LinkedList<>();
    private static int boardSize = 8;
    
    public Node(Board parent, Board current)
    {
        this.parent = parent;
        this.current = current;
    }
    
    public void createChildren(char piece) throws Exception
    {
        for(Board b: current.finalMoves(piece))
        {
            children.add(new Node(current, b));
        }
//        Board temp = current;
//        for(int i=0;i<boardSize;i++)
//        {
//            for(int j=0;j<boardSize;j++)
//            {
//                Piece tempP = temp.getPiece(i,j);
//                if(tempP.getColor() == piece)
//                {
//                    for(Piece p: temp.finalMoves(tempP))
//                    {
//                        temp.makeMove(tempP, p);
//                        children.add(new Node(current, temp));
//                        temp = current;
//                    }
//                }
//            }
//        }
    }
    
    public Board getParent()
    {
        return parent;
    }
    
    public Board getCurrent()
    {
        return current;
    }
    
    public LinkedList<Node> getChildren()
    {
        return children;
    }
    
}
