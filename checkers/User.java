package checkers;

import java.util.ArrayList;

/**
 * This class defines the user, or human. It interacts
 * with the gui to select the next move.
 * @author jayact
 *
 */
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
		gui.append("\n" + piece + "'s turn\n");
		if(jumps.size() != 0) //if there are jumps, get the user input for them
		{
			gui.append("\n" + piece + " must jump\n");
			gui.display(b, jumps, '*', null);
			Piece p_old = gui.getMove(b, jumps, piece, null);
			ArrayList<Piece> moves = b.validJumps(p_old);
			boolean looping = true;
			boolean jumped = false;
			while(looping == true)
			{
				gui.display(b, moves, '+', p_old);
				Piece p_new = gui.getMove(b, moves, '-', p_old);
				if(p_new == null)//hack for deselect feature
				{
					if(jumped == false)
						return takeTurn(b, gui);
				}
				else
				{
					looping = b.makeMove(p_old, p_new);
					p_old = p_new;
					moves = b.validJumps(p_old);
					jumped = true;
				}
			}
		}
		else //else, get the user to select a move
		{	
			gui.display(b);
			Piece p_old = gui.getMove(b, piece);
			ArrayList<Piece> moves = b.validMoves(p_old);
			gui.display(b, moves, '+', p_old);
			Piece p_new = gui.getMove(b, moves, '-', p_old);
			if(p_new == null)
				return takeTurn(b,gui);
			b.makeMove(p_old, p_new);
		}
		return b;
	}

}
