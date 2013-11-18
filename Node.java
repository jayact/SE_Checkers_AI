package checkers;


import java.util.LinkedList;
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
public class Node {
    Board parent;
    Board current;
    LinkedList<Board> children = new LinkedList<Board>();
    
    public Node(Board parent, Board current)
    {
        this.parent = parent;
        this.current = current;
    }
    
    public void createChildren(char piece)
    {
        //might have to account for multiple jumps
        Board temp = current;
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                Piece p = temp.getPiece(i, j);
                if(p.getColor()==piece)
                {
                    Piece[] moves = null;
                    try {moves = temp.validMoves(p);} catch (Exception ex){}
                    for(Piece move: moves)
                    {
                        temp = current;
                        try {temp.makeMove(p, move);} catch (Exception ex) {}
                        children.add(temp);
                    }
                }
            }
        }
    }
    
    public Board getParent()
    {
        return parent;
    }
    
    public Board getCurrent()
    {
        return current;
    }
    
    public LinkedList<Board> getChildren()
    {
        return children;
    }
    
}
