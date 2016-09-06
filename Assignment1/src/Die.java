/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Implementation of a single six-sided Die
 *  
 * @since 9/6/2016
 * @author Benji Leavitt
 */
public class Die 
{
    /**
     * The value on the top of the die in its current state
     */
    private int currentTop;
    
    /**
     * Initializes the top face to 1
     */
    public Die() 
    {
        currentTop = 1;
    }
    
    /**
     * Randomly rolls the die
     * @return value on top of die after roll
     */
    int roll()
    {
        currentTop = (int)(Math.random()*6)+1;
        return currentTop;
    }
            
    /**
     * Gets value of of top face
     * @return top of die in current state
     */
    int getTop()
    {
        return currentTop;
    }
    
}
