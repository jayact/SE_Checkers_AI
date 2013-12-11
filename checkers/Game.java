package checkers;

import view.StartupGUI;
import view.BoardGUI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class manages the game's runtime.
 * @author jayact
 */
public class Game {

    private static Stopwatch p1Stop;
    private static Stopwatch p2Stop;
    private static Player p1;
    private static Player p2;
    private static Board board;
    private static GUI gui;

    public static void main(String[] args) throws Exception {
        try {
            StartupGUI q = new StartupGUI();
            q.setVisible(true);
            Player[] players = q.getPlayers();
            startGame(players[0], players[1]);
        } catch (NullPointerException ex) {
            System.out.println("Victory in " + SolutionSpace.plyDepth + " moves!!");
        } catch (Exception ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void timeStuff() {
    	if(p1 instanceof AI)
    	{
	        gui.append("\nP1: Total time:\n" + p1Stop.total());
	        gui.append("\nP1: Average time\n" + p1Stop.average());
    	}
    	if(p2 instanceof AI)
    	{
	        gui.append("\nP2: Total time:\n" + p2Stop.total());
	        gui.append("\nP2: Average time:\n" + p2Stop.average());
    	}
    }

    /**
     * Sets player 1
     * @param player1
     */
    public static void setP1(Player player1) {
        p1 = player1;
    }

    /**
     * Sets player 2
     * @param player2
     */
    public static void setP2(Player player2) {
        p2 = player2;
    }

    /**
     * Sets the GUI to be used
     *
     * @param Gui
     */
    public static void setGUI(BoardGUI Gui) {
        gui = Gui;
    }

    /**
     * Sets up the game using the two players provided by creating the BoardGUI
     * and calling runGame
     * 
     * @param p1, the first player
     * @param p2, the second player
     */
    public static void startGame(Player p1, Player p2) {
        setP1(p1);
        p1Stop = new Stopwatch();
        setP2(p2);
        p2Stop = new Stopwatch();
        board = new Board();
        gui = new BoardGUI();

        gui.display(board);
        try {
            runGame();
        } catch (Exception ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
    }

    /**
     * Runs the game until a winner is declared.
     */
    public static void runGame() {
    	if(p1 instanceof User || p2 instanceof User)
    		gui.help();
        char play = 'B';
        while (board.victory(play) == false) {
            try {
                if (play == 'B') {
                	gui.append("\n" +p1.piece + "'s turn!");
                    p1Stop.start();
                    board = p1.takeTurn(board, gui);
                    p1Stop.end();
                    play = 'R';
                } else {
                	gui.append("\n" +p2.piece + "'s turn!");
                    p2Stop.start();
                    board = p2.takeTurn(board, gui);
                    p2Stop.end();
                    play = 'B';
                }
            } catch (Exception e) {
                timeStuff();
                System.out.println("Error encountered: " + e.getMessage());
                //System.exit(10);
            }

            gui.display(board);
           /* if(p1 instanceof AI || p2 instanceof AI)
            {
	            try {
	                //do what you want to do before sleeping
	                Thread.currentThread().sleep(2000);//sleep for 1000 ms
	                //do what you want to do after sleeptig
	            } catch (InterruptedException ie) {
	                //If this thread was intrrupted by nother thread
	            }
            }*/
            gui.clear();
        }
        gui.victory(board.winner());
        timeStuff();
    }
}
