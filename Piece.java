package checkers;

public class Piece 
{
	private char color;
	private boolean king;
	private int row;
	private int col;
	
	public Piece(char color, int row, int col)
	{
		this.color = color;
		king = false;
		setPosition(row, col);
	}
	public char getColor()
	{
		return color;
	}
	public void setColor(char color)
	{
		this.color = color;
	}
	public void king(boolean status)
	{
		king = status;
	}
	public int[] getPosition()
	{
		return new int[] {row, col};
	}
	public void setPosition(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
	public boolean isKing()
	{
		return king;
	}
	public boolean equals(Object j)
	{
		if(!(j instanceof Piece))
			return false;
		Piece b = (Piece) j;
		int[] b_pos = b.getPosition();
		if(b_pos[0] != row)
			return false;
		else if(b_pos[1] != col)
			return false;
		else if(b.isKing() != king)
			return false;
		else if(b.getColor() != color)
			return false;
		else
			return true;
	}
}
