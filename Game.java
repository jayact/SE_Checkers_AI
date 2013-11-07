package checkers;

public class Game 
{
	private static Player p1;
	private static Player p2;
	private static Board board;
	private static GUI gui;
	
	public static void main(String[] args) throws Exception
	{
		startGame();
	}
	
	public static void setP1(Player player1)
	{
		p1 = player1;
	}
	public static void setP2(Player player2)
	{
		p2 = player2;
	}
	public static void setGUI(GUI Gui)
	{
		gui = Gui;
	}
	
	public static void startGame() throws Exception
	{
		setP1(new User('R'));
		setP2(new User('B'));
		setGUI(new TextGUI());
		board = new Board();
		runGame();
	}
	
	public static void runGame() throws Exception
	{
		int count = 0;
		while(board.victory() == false)
		{
			if(count%2 == 0)
			{
				board = p1.takeTurn(board, gui);
			}
			else
			{
				board = p2.takeTurn(board, gui);
			}
			count++;
			gui.display(board);
			System.out.println();
		}
	}
}
