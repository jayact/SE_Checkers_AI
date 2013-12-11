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
    	boolean tookTurn = false;
    	Board result = new Board(b);
    	while(tookTurn == false)
    	{
    		result = algo.getMove(b, piece);
    		if(b.equals(result))
    		{
    			gui.append("\nAI didn't take turn\n");
    		}
    		else
    			tookTurn = true;
        
    	}
    	return result;
    }
}
