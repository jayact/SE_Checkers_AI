package checkers;

import Algorithms.MiniMax;
import Algorithms.MiniMaxAlphaBeta;
import Algorithms.NegaScout;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Game {

    private static Stopwatch p1Stop;
    private static Stopwatch p2Stop;
    private static Player p1;
    private static Player p2;
    private static Board board;
    private static BoardGUI gui;
    
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
            System.out.println("P1: Total time = " + p1Stop.total());
            System.out.println("P1: Average time = " + p1Stop.average());
            System.out.println("P2: Total time = " + p2Stop.total());
            System.out.println("P2: Average time = " + p2Stop.average());
    }

    /**
     * Sets player 1
     *
     * @param player1
     */
    public static void setP1(Player player1) {
        p1 = player1;
    }

    /**
     * Sets player 2
     *
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
     * Sets up the game, then calls runGame() This is all default for now. Once
     * the menu GUI is set up, this function won't exist.
     *
     * @throws Exception
     */
    public static void startGame() throws Exception {
        setP1(new AI('B', new MiniMaxAlphaBeta()));
        p1Stop = new Stopwatch();
        //setP1(new User('B'));
        setP2(new AI('R', new NegaScout()));
        p2Stop = new Stopwatch();
        //setP2(new User('R'));
        //setGUI(new TextGUI());
        board = new Board();
        runGame();
    }
    
    public static void startGame(Player p1, Player p2) {
        setP1(p1);
        p1Stop = new Stopwatch();
        setP2(p2);
        p2Stop = new Stopwatch();
        board = new Board();
        gui = new BoardGUI();

        gui.display(board);
        runGame();
    }

    /**
     * Runs the game until a winner is declared.
     *
     * @throws Exception
     */
    public static void runGame() {
        int count = 0;
        while (board.victory() == false) {
            try {
                if (count % 2 == 0) {
                p1Stop.start();
                board = p1.takeTurn(board, gui);
                System.out.println("Player 1 took: " + p1Stop.end() + "milliseconds");
                } else {
                p2Stop.start();
                board = p2.takeTurn(board, gui);
                p2Stop.end();
                System.out.println("Player 2 took: " + p2Stop.end() + " milliseconds");
                }
            } catch (Exception e) {
                System.out.println("Error encountered: " + e.getMessage());
                System.exit(10);
            }
            count++;
            gui.display(board);
            try {
                //do what you want to do before sleeping
                Thread.currentThread().sleep(2000);//sleep for 1000 ms
                //do what you want to do after sleeptig
            } catch (InterruptedException ie) {
                //If this thread was intrrupted by nother thread
            }
              System.out.println();
        }
    }
}
