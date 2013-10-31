package checkers;

public class Board {
	private Piece[][] board;
	
	public Board()
	{
		board = new Piece[10][10];
		setup();
	}
	
	public Piece[][] setup()
	{
		board = new Piece[10][10];
		for(int i = 0; i < 100; i++)
		{
			if(i%2 == 0)
			{
				if(i >= 90)
					board[i/10][i%10] = new Piece('R', i/10, i%10);
				else if(i >= 70 && i < 80)
					board[i/10][i%10] = new Piece('R', i/10, i%10);
				else if(i >= 20 && i < 30)
					board[i/10][i%10] = new Piece('B', i/10, i%10);
				else if(i < 10)
					board[i/10][i%10] = new Piece('B', i/10, i%10);
			}
			else
			{
				if(i >= 90)
					board[i/10][i%10] = new Piece('R', i/10, i%10);
				else if(i >= 70 && i < 80)
					board[i/10][i%10] = new Piece('R', i/10, i%10);
				else if(i >= 20 && i < 30)
					board[i/10][i%10] = new Piece('B', i/10, i%10);
				else if(i < 10)
					board[i/10][i%10] = new Piece('B', i/10, i%10);
			}
		}
		return board;
	}
	
	public Piece[][] getBoard()
	{
		return board;
	}
	
	public Piece[] validMoves(Piece p)
	{
		int[] position = p.getPosition();
		int p_row = position[0];
		int p_col = position[1];
		char p_color = p.getColor();
		
		int direction = 1;
		int up_limit = 0;
		int down_limit = 10;
		
		boolean king = p.isKing();
		
		if(p_color == 'B')
		{
			direction = -1;
			up_limit = 10;
			down_limit = 0;
		}
		
		Piece[] moves = new Piece[1];
		
		//Check single moves first.
		//If in bounds and diagnal space is free, add it.
		if(p_row != up_limit ) //Check forward if possible
		{
			if(p_col > 1)
			{
				if(board[p_row-1*direction][p_col-1] == null) //if the piece to the left is empty
				{
					moves = combineArrays(moves, new Piece(p_color, p_row-1*direction, p_col-1));
				}
			}
			
			if(p_col < 9)
			{
				if(board[p_row-1*direction][p_col+1] == null)
				{
					moves = combineArrays(moves, new Piece(p_color, p_row-1*direction, p_col+1));
				}
			}
		}
		//Check backwards moves if kinged and spaces are empty.
		if(p_row != down_limit && king == true) //check backward if possible and if kinged
		{
			if(p_col > 0)
			{
				if(board[p_row+1*direction][p_col-1] == null)
				{
					moves = combineArrays(moves, new Piece(p_color, p_row+1*direction, p_col-1));
				}
			}
			if(p_col < 9)
			{
				if(board[p_row+1*direction][p_col+1] == null)
				{
					moves = combineArrays(moves, new Piece(p_color, p_row+1*direction, p_col+1));
				}
			}
		}
		//Now, check the jumps!
		Piece[] jump_moves = jumps(p);
		moves = combineArrays(moves, jump_moves);
		return moves;
	}
	
	public Piece[] jumps(Piece p)
	{
		int[] position = p.getPosition();
		int p_row = position[0];
		int p_col = position[1];
		char p_color = p.getColor();
		
		int direction = 1;
		int up_limit = 0;
		int down_limit = 10;
		
		boolean king = p.isKing();
		
		if(p_color == 'B')
		{
			direction = -1;
			up_limit = 10;
			down_limit = 0;
		}
		
		Piece[] moves = new Piece[10];
		if(p_row - direction != up_limit)
		{
			if(p_col > 1)
			{
				if(board[p_row-2*direction][p_col-2] == null && board[p_row-1*direction][p_col-1].getColor() != p_color) //otherwise, if the piece to the left is an enemy and the piece after is open
				{
					Piece c = new Piece(p_color, p_row-2*direction, p_col-2);
					moves = combineArrays(moves, c);
					Piece[] a = jumps(c);
					moves = combineArrays(moves, a);
				}
			}
			if(p_col < 8)
			{
				if(board[p_row-2*direction][p_col+2] == null && board[p_row-1*direction][p_col+1].getColor() != p_color)
				{
					Piece c = new Piece(p_color, p_row-2*direction, p_col+2);
					moves = combineArrays(moves, c);
					Piece[] a = jumps(c);
					moves = combineArrays(moves, a);
				}
			}
		}
		if(p_row + direction != down_limit && king == true)
		{
			if(p_col > 1)
			{
				if(board[p_row+2*direction][p_col+2] == null && board[p_row+1*direction][p_col+1].getColor() != p_color)
				{
					Piece c = new Piece(p_color, p_row+2*direction, p_col+2);
					moves = combineArrays(moves, c);
					Piece[] a = jumps(c);
					moves = combineArrays(moves, a);
				}
			}
			if(p_col < 8)
			{
				if(board[p_row+2*direction][p_col-2] == null && board[p_row+1*direction][p_col-1].getColor() != p_color)
				{
					Piece c = new Piece(p_color, p_row+2*direction, p_col-2);
					moves = combineArrays(moves, c);
					Piece[] a = jumps(c);
					moves = combineArrays(moves, a);
				}
			}
		}
		return moves;
	}
	
	private Piece[] combineArrays(Piece[] a, Piece[] b)
	{
		Piece[] p = new Piece[a.length + b.length];
		int count = 0;
		for(int i = 0; i < a.length; i++)
		{
			p[i] = a[i];
			count++;
		}
		for(int i = 0; i < b.length; i++)
		{
			p[i+count] = b[i];
			count++;
		}
		return p;
	}
	private Piece[] combineArrays(Piece[] a, Piece b)
	{
		Piece[] p = new Piece[a.length + 1];
		int count = 0;
		for(int i = 0; i < a.length; i++)
		{
			p[i] = a[i];
			count++;
		}
		p[count] = b;
		return p;
	}
	
	public boolean makeMove(Piece old_p, Piece new_p)
	{
		Piece[] moves = validMoves(old_p);
		
		boolean found = false;
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
			return false; //this move is not possible, don't make it.
		
		int[] position_old = old_p.getPosition();
		int[] position_new = new_p.getPosition();
		//find a way to make the move
		//if a jump, remove ALL jumped pieces.
		return true;
	}
}
