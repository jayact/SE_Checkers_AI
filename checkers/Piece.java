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
	/**
	 * Gets the owner/color of the piece.
	 * R if red, B if block, - if not owned/blank.
	 * @return
	 */
	public char getColor()
	{
		return color;
	}
	/**
	 * Sets the color/owner of the piece.
	 * R if red, B if black, - if not owned/blank.
	 * @param color
	 */
	public void setColor(char color)
	{
		this.color = color;
	}
	/**
	 * Sets the king status.
	 * @param status is the king status to set it to.
	 */
	public void king(boolean status)
	{
		king = status;
	}
	/**
	 * Gets the position of the piece.
	 * @return the position, represented as int[row, col]
	 */
	public int[] getPosition()
	{
		return new int[] {row, col};
	}
	/**
	 * Sets the position of the piece
	 * @param row
	 * @param col
	 */
	public void setPosition(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
	/**
	 * Checks if the piece is kinged.
	 * @return status.
	 */
	public boolean isKing()
	{
		return king;
	}
	/**
	 * Overrides object equals method, it checks if two pieces are equivelant. 
	 * Note: Not a deep check, just checks values ONLY.
	 * @param j is the comparator.
	 * @override
	 */
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
