package checkers;

public class User extends Player 
{
	
	public User(char piece)
	{
		super(piece);
	}
	
	@Override
	public Board takeTurn(Board b, GUI gui) throws Exception {
		gui.display(b);
		System.out.println(piece + ": Please select a piece to move (row,col):");
		Piece p_old = gui.getMove(b, piece);
		
		Piece[] moves = b.validMoves(p_old);
		boolean looping = true;
		boolean jumped = false;
		while(looping == true)
		{
			gui.display(b, moves);
			System.out.println(piece + ": Please select where to move the piece (row,col), or type exit to deselect:");
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
		return b;
	}

}
