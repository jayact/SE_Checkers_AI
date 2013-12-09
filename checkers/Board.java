package checkers;
import java.util.ArrayList;

/**
 * Represents the board state, with the current pieces and unit counts.
 * @author jayact
 *
 */
public class Board{
	private int b_units;
	private int r_units;
	ArrayList<ArrayList<Piece>> board;
	
	public Board()
	{
		setup();
	}
	
	/**
	 * Deep copies another board class
	 * @param board
	 */
	public Board(Board board)
    {
        this.board = new ArrayList<ArrayList<Piece>>();
        for(int i = 0; i < 8; i++)
        {
            this.board.add(new ArrayList<Piece>());
            for(int j = 0; j < 8; j++)
            {
                Piece p = board.getPiece(i, j);
                int[] pos = p.getPosition();
                this.board.get(i).add(new Piece(p.getColor(), pos[0], pos[1]));
                this.board.get(i).get(j).king(p.isKing());
            }
        }
        b_units = board.bUnits();
        r_units = board.rUnits();
    }
        

	
	/**
	 * sets the board up in the standard configuration
	 * @return the newly setup board
	 */
	public ArrayList<ArrayList<Piece>> setup()
	{
		board = new ArrayList<ArrayList<Piece>>();
		for(int i = 0; i < 8; i++)
		{
			board.add(new ArrayList<Piece>());
			for(int j = 0; j < 8; j++)
			{
				board.get(i).add(new Piece('-', i, j));
			}
		}
		
		for(int i = 0; i < 64; i++)
		{
			char a = ' ';
			if(i%2 == 0)
			{
				if(i >= 56)
					a = 'R';
				else if(i >= 40 && i < 48)
					a = 'R';
				else if(i < 16 && i >= 8)
					a = 'B';
				else
					a = '-';
			}
			else
			{
				if(i < 56 && i >= 48)
					a = 'R';
				else if(i >= 16 && i < 24)
					a = 'B';
				else if(i < 8)
					a = 'B';
				else
					a = '-';
			}
			board.get(i/8).set(i%8,new Piece(a, i/8, i%8));
		}
		r_units = 12;
		b_units = 12;
		return board;
	}
	
	/**
	 * @return the board.
	 */
	public ArrayList<ArrayList<Piece>> getBoard()
	{
		return board;
	}
	
	/**
	 * returns the piece at that location
	 * @param row
	 * @param col
	 * @return the piece found.
	 */
	public Piece getPiece(int row, int col)
	{
		return board.get(row).get(col);
	}
	
	/**
	 * @return the number of red units.
	 */
	public int rUnits()
	{
		return r_units;
	}
	/**
	 * @return the number of black units.
	 */
	public int bUnits()
	{
		return b_units;
	}
	
	/**
	 * returns a combined list of available moves. Does NOT return jumps
	 * @param p is the piece in question
	 * @return is the list of valid moves (represented as pieces)
	 * @throws Exception 
	 */
	public ArrayList<Piece> validMoves(Piece p) throws Exception
	{
		int[] position = p.getPosition();
		int p_row = position[0];
		int p_col = position[1];
		char p_color = p.getColor();
		
		int direction = 1;
		int up_limit = 0;
		int down_limit = 7;
		
		boolean king = p.isKing();
		
		if(p_color == 'B')
		{
			direction = -1;
			up_limit = 7;
			down_limit = 0;
		}
		if(p_color == '-')
			throw new Exception("Trying to move a " + p_color + " piece in Board.");
		
		ArrayList<Piece> moves = new ArrayList<Piece>();
		int count = 0;
		
		//Check single moves first.
		//If in bounds and diagnal space is free, add it.
		if(p_row != up_limit ) //Check forward if possible
		{
			if(p_col > 0)
			{
				Piece temp = board.get(p_row-1*direction).get(p_col-1);
				if(temp.getColor() == '-') //if the piece to the left is empty
				{
					moves.add(new Piece(temp.getColor(), p_row-1*direction, p_col-1));
					count++;
				}
			}
			
			if(p_col < 7)
			{
				Piece temp = board.get(p_row-1*direction).get(p_col+1);
				if(temp.getColor() == '-')
				{
					moves.add(new Piece(temp.getColor(), p_row-1*direction, p_col+1));
					count++;
				}
			}
		}
		//Check backwards moves if kinged and spaces are empty.
		if(p_row != down_limit && king == true) //check backward if possible and if kinged
		{
			if(p_col > 0)
			{
				Piece temp = board.get(p_row+1*direction).get(p_col-1);
				if(temp.getColor() == '-')
				{
					moves.add(new Piece(temp.getColor(), p_row+1*direction, p_col-1));
					count++;
				}
			}
			if(p_col < 7)
			{
				Piece temp = board.get(p_row+1*direction).get(p_col+1);
				if(temp.getColor() == '-')
				{
					moves.add(new Piece(temp.getColor(), p_row+1*direction, p_col+1));
					count++;
				}
			}
		}
		return moves;
	}
	/**
	 * this checks the jumps available at the current position. will need to call again if a jump is made, as it does NOT do recursive jumps.
	 * reason is due to piece removal involved in said jumps.
	 * @param p is the piece position in question
	 * @return is the list of available jumps, represented as pieces
	 */
	public ArrayList<Piece> validJumps(Piece p)
	{
		int[] position = p.getPosition();
		int p_row = position[0];
		int p_col = position[1];
		char p_color = p.getColor();
		
		int direction = 1;
		int up_limit = 0;
		int down_limit = 7;
		
		boolean king = p.isKing();
		
		if(p_color == 'B')
		{
			direction = -1;
			up_limit = 7;
			down_limit = 0;
		}
		
		ArrayList<Piece> moves = new ArrayList<Piece>();
		int count = 0;
		if(p_row - direction != up_limit && p_row != up_limit)
		{
			if(p_col > 1)
			{
				Piece end = board.get(p_row-2*direction).get(p_col-2);
				Piece mid = board.get(p_row-1*direction).get(p_col-1);
				if(end.getColor() == '-' && mid.getColor() != p_color && mid.getColor() != '-') //otherwise, if the piece to the left is an enemy and the piece after is open
				{
					
					moves.add(new Piece(end.getColor(), p_row-2*direction, p_col-2));
					count++;
				}
			}
			if(p_col < 6)
			{
				Piece end = board.get(p_row-2*direction).get(p_col+2);
				Piece mid = board.get(p_row-1*direction).get(p_col+1);
				if(end.getColor() == '-' && mid.getColor() != p_color && mid.getColor() != '-')
				{
					moves.add(new Piece(end.getColor(), p_row-2*direction, p_col+2));
					count++;
				}
			}
		}
		if(p_row + direction != down_limit && king == true && p_row != down_limit)
		{
			if(p_col > 1)
			{
				Piece end = board.get(p_row+2*direction).get(p_col-2);
				Piece mid = board.get(p_row+1*direction).get(p_col-1);
				if(end.getColor() == '-' && mid.getColor() != p_color&& mid.getColor() != '-')
				{
					moves.add(new Piece(end.getColor(), p_row+2*direction, p_col-2));
					count++;
				}
			}
			if(p_col < 6)
			{
				Piece end = board.get(p_row+2*direction).get(p_col+2);
				Piece mid = board.get(p_row+1*direction).get(p_col+1);
				if(end.getColor() == '-' && mid.getColor() != p_color && mid.getColor() != '-')
				{
					moves.add(new Piece(end.getColor(), p_row+2*direction, p_col+2));
					count++;
				}
			}
		}
		
		return moves;
	}
	
	
	/**
	 * makes the move for the user. returns true if there are further jumps available, false otherwise.
	 * @param old_p curent piece position
	 * @param new_p new piece position
	 * @return true if further jumps available, false otherwise OR kinged
	 * @throws Exception if new position is not available for this piece to access
	 */
	public boolean makeMove(Piece old_p, Piece new_p) throws Exception
	{
		
		int[] pos_old = old_p.getPosition();
		int[] pos_new = new_p.getPosition();
		
		int row_diff = pos_old[0]-pos_new[0];
		if(row_diff < 0)
			row_diff *= -1;
		ArrayList<Piece> moves;
		//if it's a jump, get the valid jumps. else, valid moves.
		if(row_diff == 2)
			moves = validJumps(old_p);
		else
			moves = validMoves(old_p);
		
		boolean found = false;
		boolean available = false;
		boolean kinged = false;
		int i = 0;
		while(i < moves.size() && found == false)
		{
			if(moves.get(i).equals(new_p))
			{
				found = true;
			}
			i++;
		}
		
		if(found == false)
			throw new Exception("This move isn't available. Old: " + old_p + "; new: " + new_p); //this move is not possible, don't make it.
		
		int up_limit = 0;
		char p_color = old_p.getColor();
		
		if(p_color == 'B')
		{
			up_limit = 7;
		}

		//change the characters, and kind status.
		
		board.get(pos_new[0]).get(pos_new[1]).setColor(old_p.getColor());
		board.get(pos_new[0]).get(pos_new[1]).king(board.get(pos_old[0]).get(pos_old[1]).isKing());
		board.get(pos_old[0]).get(pos_old[1]).king(false);
		board.get(pos_old[0]).get(pos_old[1]).setColor('-');
		
		if(pos_new[0] == up_limit && board.get(pos_new[0]).get(pos_new[1]).isKing() == false)
		{
			board.get(pos_new[0]).get(pos_new[1]).king(true);
			kinged = true;
		}
		
		if(row_diff == 2)
		{
			if(p_color == 'B')
				r_units--;
			else if(p_color == 'R')
				b_units--;
			board.get(pos_new[0]-1*(pos_new[0]-pos_old[0])/2).get(pos_new[1]-1*(pos_new[1]-pos_old[1])/2).setColor('-');
			if(validJumps(new_p).size() != 0)
				available = true;
		}
		
		return available && !kinged;
	}
	
	/**
	 * determines if victory conditions were met.
	 * @returns true if met, false otherwise.
	 */
	public boolean victory()
	{
		if(b_units == 0 || r_units == 0)
			return true;
		return false;
	}
	
	/**
	 * This returns the list of pieces that can jump. A jump must be made, if available, as per the rules.
	 * @param color is the color of the owner.
	 * @return a list of pieces that can jump.
	 */
	public ArrayList<Piece> allJumps(char color)
	{
		ArrayList<Piece> result = new ArrayList<Piece>();
		int count = 0;
		for(int row = 0; row < 8; row++)
		{
			for(int col = 0; col < 8; col++)
			{
				Piece p = board.get(row).get(col);
				if(p.getColor() == color)
				{
					ArrayList<Piece> r = validJumps(p);
					if(r.size() != 0)
					{
						result.add(p);
						count++;
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * Returns all pieces available to jump
	 * @param color
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Piece> allMoves(char color) throws Exception
	{
		ArrayList<Piece> result = new ArrayList<Piece>();

		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				Piece p = board.get(i).get(j);
				if(p.getColor() != '-' && p.getColor() == color)
				{
					ArrayList<Piece> moves = validMoves(p);
					if(moves.size() != 0)
						result.add(p);
				}
			}
		}
		return result;
	}

    
}
