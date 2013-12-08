/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

/**
 *
 * @author Jack
 */
public class Stopwatch {
    private long start;
    private double total = 0.0;
    private long turns;
    
    public void start()
    {
        turns++;
        start = System.currentTimeMillis();
    }
    
    public long end()
    {
        long end = System.currentTimeMillis() - start;
        total += end;
        return end;
    }
    
    public double total()
    {
        return total;
    }
    
    public double average()
    {
        return total/turns;
    }

}