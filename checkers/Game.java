package checkers;

import Algorithms.MiniMax;
import Algorithms.MiniMaxAlphaBeta;
import Algorithms.NegaScout;

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
	/**
	 * Sets player 1
	 * @param player1
	 */
	public static void setP1(Player player1)
	{
		p1 = player1;
	}
	/**
	 * Sets player 2
	 * @param player2
	 */
	public static void setP2(Player player2)
	{
		p2 = player2;
	}
	/**
	 * Sets the GUI to be used
	 * @param Gui
	 */
	public static void setGUI(GUI Gui)
	{
		gui = Gui;
	}
	
	/**
	 * Sets up the game, then calls runGame()
	 * This is all default for now. Once the menu GUI
	 * is set up, this function won't exist.
	 * @throws Exception
	 */
	public static void startGame() throws Exception
	{
		setP1(new AI('B', new MiniMaxAlphaBeta()));
                //setP1(new User('B'));
                setP2(new AI('R', new NegaScout()));
		//setP2(new User('R'));
		setGUI(new TextGUI());
		board = new Board();
		runGame();
	}
	
	/**
	 * Runs the game until a winner is declared.
	 * @throws Exception
	 */
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
