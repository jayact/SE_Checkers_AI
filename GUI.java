package checkers;

import java.io.IOException;

public interface GUI 
{
	public void display(Board b);
	public void display(Board b, Piece[] moves);
	public Piece getMove(Board b, char piece)throws Exception;
	public Piece getMove(Board b, Piece[] moves, char piece) throws IOException;
}
