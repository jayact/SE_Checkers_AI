package checkers;
import arrayManagement.ArrayManager;

public class Board {
	private Piece[][] board;
	private int b_units;
	private int r_units;
	
	public Board()
	{
		setup();
	}
	
	/**
	 * sets the board up in the standard configuration
	 * @return the newly setup board
	 */
	public Piece[][] setup()
	{
		board = new Piece[8][8];
		for(int i = 0; i < 64; i++)
		{
			if(i%2 == 0)
			{
				if(i >= 56)
					board[i/8][i%8] = new Piece('R', i/8, i%8);
				else if(i >= 40 && i < 48)
					board[i/8][i%8] = new Piece('R', i/8, i%8);
				else if(i < 16 && i >= 8)
					board[i/8][i%8] = new Piece('B', i/8, i%8);
				else
					board[i/8][i%8] = new Piece('-',i/8,i%8);
			}
			else
			{
				if(i < 56 && i >= 48)
					board[i/8][i%8] = new Piece('R', i/8, i%8);
				else if(i >= 16 && i < 24)
					board[i/8][i%8] = new Piece('B', i/8, i%8);
				else if(i < 8)
					board[i/8][i%8] = new Piece('B', i/8, i%8);
				else
					board[i/8][i%8] = new Piece('-',i/8,i%8);
			}
		}
		r_units = 12;
		b_units = 12;
		return board;
	}
	
	/**
	 * returns the  board
	 * @return 
	 */
	public Piece[][] getBoard()
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
		return board[row][col];
	}
	
	/**
	 * returns a combined list of available jumps and moves. Does NOT return it recursively, meaning, further jumps will be given as needed by the validJumps function
	 * @param p is the piece in question
	 * @return is the list of valid moves (represented as pieces)
	 * @throws Exception 
	 */
	public Piece[] validMoves(Piece p) throws Exception
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
			throw new Exception("This is not a valid play piece");
		
		Piece[] moves = new Piece[4];
		int count = 0;
		
		//Check single moves first.
		//If in bounds and diagnal space is free, add it.
		if(p_row != up_limit ) //Check forward if possible
		{
			if(p_col > 0)
			{
				if(board[p_row-1*direction][p_col-1].getColor() == '-') //if the piece to the left is empty
				{
					moves[count] = new Piece(board[p_row-1*direction][p_col-1].getColor(), p_row-1*direction, p_col-1);
					count++;
				}
			}
			
			if(p_col < 7)
			{
				if(board[p_row-1*direction][p_col+1].getColor() == '-')
				{
					moves[count] = new Piece(board[p_row-1*direction][p_col+1].getColor(), p_row-1*direction, p_col+1);
					count++;
				}
			}
		}
		//Check backwards moves if kinged and spaces are empty.
		if(p_row != down_limit && king == true) //check backward if possible and if kinged
		{
			if(p_col > 0)
			{
				if(board[p_row+1*direction][p_col-1].getColor() == '-')
				{
					moves[count] = new Piece(board[p_row+1*direction][p_col-1].getColor(), p_row+1*direction, p_col-1);
					count++;
				}
			}
			if(p_col < 7)
			{
				if(board[p_row+1*direction][p_col+1].getColor() == '-')
				{
					moves[count] = new Piece(board[p_row+1*direction][p_col+1].getColor(), p_row+1*direction, p_col+1);
					count++;
				}
			}
		}
		//Now, check the jumps!
		moves = (Piece[])ArrayManager.resizeArray(moves, Piece.class);
		Piece[] jump_moves = validJumps(p);
		moves = (Piece[])ArrayManager.combineArrays(moves, jump_moves, Piece.class);
		return moves;
	}
	/**
	 * this checks the jumps available at the current position. will need to call again if a jump is made, as it does NOT do recursive jumps.
	 * reason is due to piece removal involved in said jumps.
	 * @param p is the piece position in question
	 * @return is the list of available jumps, represented as pieces
	 */
	public Piece[] validJumps(Piece p)
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
		
		Piece[] moves = new Piece[4];
		int count = 0;
		if(p_row - direction != up_limit && p_row != up_limit)
		{
			if(p_col > 1)
			{
				if(board[p_row-2*direction][p_col-2].getColor() == '-' && board[p_row-1*direction][p_col-1].getColor() != p_color && board[p_row-1*direction][p_col-1].getColor() != '-') //otherwise, if the piece to the left is an enemy and the piece after is open
				{
					
					moves[count] = new Piece(board[p_row-2*direction][p_col-2].getColor(), p_row-2*direction, p_col-2);
					count++;
				}
			}
			if(p_col < 6)
			{
				if(board[p_row-2*direction][p_col+2].getColor() == '-' && board[p_row-1*direction][p_col+1].getColor() != p_color && board[p_row-1*direction][p_col+1].getColor() != '-')
				{
					moves[count] = new Piece(board[p_row-2*direction][p_col+2].getColor(), p_row-2*direction, p_col+2);
					count++;
				}
			}
		}
		if(p_row + direction != down_limit && king == true && p_row != down_limit)
		{
			if(p_col > 1)
			{
				if(board[p_row+2*direction][p_col-2].getColor() == '-' && board[p_row+1*direction][p_col-1].getColor() != p_color&& board[p_row+1*direction][p_col-1].getColor() != '-')
				{
					moves[count] = new Piece(board[p_row+2*direction][p_col-2].getColor(), p_row+2*direction, p_col-2);
					count++;
				}
			}
			if(p_col < 6)
			{
				if(board[p_row+2*direction][p_col+2].getColor() == '-' && board[p_row+1*direction][p_col+1].getColor() != p_color&& board[p_row+1*direction][p_col+1].getColor() != '-')
				{
					moves[count] = new Piece(board[p_row+2*direction][p_col+2].getColor(), p_row+2*direction, p_col+2);
					count++;
				}
			}
		}
		moves = (Piece[])ArrayManager.resizeArray(moves, Piece.class);
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
		Piece[] moves = validMoves(old_p);
		
		boolean found = false;
		boolean available = false;
		boolean kinged = false;
		int i = 0;
		while(i < moves.length && found == false)
		{
			if(moves[i].equals(new_p))
			{
				found = true;
			}
			i++;
		}
		
		if(found == false)
			throw new Exception("This move doesn't exist, what are you doing?"); //this move is not possible, don't make it.
		
		int up_limit = 0;
		char p_color = old_p.getColor();
		
		if(p_color == 'B')
		{
			up_limit = 7;
		}
		
		int[] pos_old = old_p.getPosition();
		int[] pos_new = new_p.getPosition();
		
		int row_diff = pos_old[0]-pos_new[0];
		if(row_diff < 0)
			row_diff *= -1;
		//change the characters, and kind status.
		board[pos_new[0]][pos_new[1]].setColor(old_p.getColor());
		board[pos_new[0]][pos_new[1]].king(board[pos_old[0]][pos_old[1]].isKing());
		board[pos_old[0]][pos_old[1]].king(false);
		board[pos_old[0]][pos_old[1]].setColor('-');
		
		if(pos_new[0] == up_limit && board[pos_new[0]][pos_new[1]].isKing() == false)
		{
			board[pos_new[0]][pos_new[1]].king(true);
			kinged = true;
		}
		
		if(row_diff == 2)
		{
			char color = board[pos_new[0]-1*(pos_new[0]-pos_old[0])/2][pos_new[1]-1*(pos_new[1]-pos_old[1])/2].getColor();
			if(color == 'B')
				b_units--;
			else if(color == 'R')
				r_units--;
			board[pos_new[0]-1*(pos_new[0]-pos_old[0])/2][pos_new[1]-1*(pos_new[1]-pos_old[1])/2].setColor('-');
			if(validJumps(new_p).length != 0)
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
	 * @param piece is the color
	 * @return a list of pieces that can jump.
	 */
	public Piece[] getJumps(char piece)
	{
		Piece[] result = new Piece[12];
		int count = 0;
		for(int row = 0; row < 8; row++)
		{
			for(int col = 0; col < 8; col++)
			{
				if(board[row][col].getColor() == piece)
				{
					Piece[] r = validJumps(board[row][col]);
					if(r.length != 0)
					{
						result[count] = board[row][col];
						count++;
					}
				}
			}
		}
		return ArrayManager.resizeArray(result, Piece.class);
	}
}
