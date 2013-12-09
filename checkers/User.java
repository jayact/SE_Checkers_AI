package checkers;

import java.util.ArrayList;

public class User extends Player 
{
	
	public User(char piece)
	{
		super(piece);
	}
	
	
	/**
	 * Takes the turn by user input, by the GUI.
	 * @param b is the board.
	 * @param gui is the GUI used for out/input
	 * @Override
	 */
	public Board takeTurn(Board b, GUI gui) throws Exception {
		ArrayList<Piece> jumps = b.allJumps(piece);
		if(jumps.size() != 0)
		{
			gui.display(b, jumps, '*');
			gui.append(piece + ": A jump must be \nmade. Select a \nhighlighted piece:\n");
			Piece p_old = gui.getMove(b, jumps, piece);
			ArrayList<Piece> moves = b.validJumps(p_old);
			boolean looping = true;
			boolean jumped = false;
			while(looping == true)
			{
				gui.display(b, moves, '+');
				gui.append(piece + ": Please select a \nhighlighted space:\n");
				Piece p_new = gui.getMove(b, moves, '-');
				if(p_new == null)//hack for deselect featurne
				{
					if(jumped == true)
						return b;
					else
						return takeTurn(b, gui);
				}
				looping = b.makeMove(p_old, p_new);
				p_old = p_new;
				moves = b.validJumps(p_old);
				if(looping == true)
					jumped = true;
			}
		}
		else
		{	
			gui.display(b);
			gui.append(piece + ": Please select a \npiece to move:\n");
			Piece p_old = gui.getMove(b, piece);
			ArrayList<Piece> moves = b.validMoves(p_old);
			gui.display(b, moves, '+');
			gui.append(piece + ": Please select a \nhighlighted space:\n");
			Piece p_new = gui.getMove(b, moves, '-');
			if(p_new == null)
				return takeTurn(b,gui);
			b.makeMove(p_old, p_new);
		}
		return b;
	}

}
