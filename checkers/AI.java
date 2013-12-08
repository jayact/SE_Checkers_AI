package checkers;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.Thread;

public class AI extends Player {

    private Algorithm algo;
    
    public AI(char piece, Algorithm algo) {
        super(piece);
        setAlgo(algo);
    }

    /**
     * Sets the algorithm for the AI to use
     *
     * @param algo is the algorithm to set
     */
    public void setAlgo(Algorithm algo) {
        this.algo = algo;
    }

    /**
     * Takes the turn. @Override
     */
    @Override
    public Board takeTurn(Board b, GUI gui) {
//        try {
//            Thread.currentThread().sleep(2000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(AI.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return algo.getMove(b, piece);
    }
}
