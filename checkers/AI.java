package checkers;

/**
 * Represents the individual AIs. Uses an algorithm to obtain the move.
 * @author jayact
 *
 */
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
        return algo.getMove(b, piece);
    }
}
