package dice;

import java.util.Random;

/**
 * Implementation of a single n-sided Die
 *  
 * @since 9/18/2016
 * @author Benji Leavitt
 */
public class Die implements Comparable<Die>
{
    /**
     * The value on the top of the die in its current state
     */
    protected int currentTop;
    
    /**
     * Number of sides the die has
     */
    protected int numSides;
    
    /**
     * A random number stream
     */
    protected Random rand = new Random();
    
    /**
     * Initializes the top face to 1 and the number of sides to 6
     */
    public Die() 
    {
        this(6);
    }
    
    /**
     * Initializes the top face to 1 and the number of sides to n
     * @param n Number of sides the die should have
     */
    public Die(int n) throws IllegalArgumentException
    {
        if(n < 4)
            throw new IllegalArgumentException("n must be at least 4");
        numSides = n;
        currentTop = 1;
    }
    
    /**
     * Randomly rolls the die
     * @return value on top of die after roll
     */
    int roll()
    {
        currentTop = rand.nextInt(numSides)+1;
        return currentTop;
    }
            
    /**
     * Gets value of top face
     * @return top of die in current state
     */
    int getTop()
    {
        return currentTop;
    }

    /**
     * Checks if this Die is equal to an object 'o'.
     * @param o the object to compare to
     * @return true if all fields are equal
     */
    @Override
    public boolean equals(Object o) 
    {
        Die d = (Die)o;
        return this.numSides == d.numSides
                && this.currentTop == d.currentTop;
    }

    /**
     * Creates a hash code based on the values of the fields of this Die
     * @return the hash code
     */
    @Override
    public int hashCode() 
    {
        int hash = 17;
        hash = 37*hash + currentTop;
        hash = 37*hash + numSides;
        return hash;
    }

    /**
     * Prints out the size of the die and the values with the current top
     * @return the out string
     */
    @Override
    public String toString() 
    {
        String s = super.toString();
                
        s += " D" + numSides + ": ";
        
        for(int i = 1; i <= currentTop; ++i)
            s += i + " ";
        
        s += "[" + currentTop + "]";
        
        for(int i = currentTop+1; i <= numSides; ++i)
            s += " " + i;
        
        return s;
    } 

    /**
     * Compares this die to d
     * @param d The die to be compared to
     * @return an integer based on the comparative value
     */
    @Override
    public int compareTo(Die d) 
    {
        if(this.currentTop < d.currentTop)
            return -1;
        else if (this.currentTop == d.currentTop)
            return 0;
        else
            return 1;
    }
}
