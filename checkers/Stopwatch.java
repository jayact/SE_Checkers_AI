
package checkers;

/**
 *This just keeps track of the time between moves.
 *This will be used for performance measurement for the AI
 * @author Jack
 */
public class Stopwatch {
    private long start;
    private double total = 0.0;
    private long turns;
    
    /**
     * Starts the timing of the AI
     */
    public void start()
    {
        turns++;
        start = System.currentTimeMillis();
    }
    
    /**
     * Ends the timing
     * @return the time taken
     */
    public long end()
    {
        long end = System.currentTimeMillis() - start;
        total += end;
        return end;
    }
    
    /**
     * @return the total time taken
     */
    public double total()
    {
        return total;
    }
    
    /**
     * @returns the average time taken
     */
    public double average()
    {
        return total/turns;
    }

}