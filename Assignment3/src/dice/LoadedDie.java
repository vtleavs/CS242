package dice;

/**
 *
 * @author Benji
 */
public class LoadedDie extends Die
{
    /**
     * The loaded side of the die
     */
    private int loadedSide;
    
    /**
     * The percent chance that the loaded side will be rolled
     */
    private int loadFactor;

    /**
     * Creates a loaded die with 6 sides, a load factor of 50% and  a loaded
     * side of 1;
     */
    public LoadedDie() 
    {
        super();
        loadedSide = 1;
        loadFactor = 50;
    }

    /**
     * Creates a loaded die with n sides, a load factor of loadFact% and a 
     * loaded side loadSide
     * @param n The number of sides
     * @param loadSide the side to be loaded
     * @param loadFact a load factor of loadFact%
     * @throws IllegalArgumentException if n < 4
     */
    public LoadedDie(int n, int loadSide, int loadFact) 
            throws IllegalArgumentException 
    {
        super(n);
        if(loadSide < 1 || loadSide > n)
            throw new IllegalArgumentException(
                    "loadSide must be between 1 and n");
        loadedSide = loadSide;
        if(loadFact < 0 || loadFact > 100)
            throw new IllegalArgumentException(
                    "loadFact must be between 0 and 100");
        loadFactor = loadFact;
    }
    
    /**
     * Rolls the LoadedDie taking the load into effect
     * @return the top face after the roll
     */
    @Override
    int roll() 
    {   
        double loadChance = rand.nextInt(100);
        
        if(loadChance < loadFactor)
            currentTop = loadedSide;
        else
        {
            int newRoll = (int)rand.nextInt(numSides-1)+1;
            if(newRoll >= loadedSide)
                newRoll++;
            currentTop = newRoll;
        }
        return currentTop;
    }
    
    /**
     * Checks if this LoadedDie is equal to an object 'o'.
     * @param o the object to compare to
     * @return true if all fields are equal
     */
    @Override
    public boolean equals(Object o) 
    {
        LoadedDie ld = (LoadedDie)o;
        return super.equals(o)
                && this.loadFactor == ld.loadFactor
                && this.loadedSide == ld.loadedSide;
    }
    
    /**
     * Creates a hash code based on the values of the fields of this LoadedDie
     * @return the hash code
     */
    @Override
    public int hashCode() 
    {
        int hash = 17;
        hash = 37*hash + currentTop;
        hash = 37*hash + numSides;
        hash = 37*hash + loadedSide;
        hash = 37*hash + loadFactor;
        return hash;
    }

    /**
     * Prints out the Die, and shows the Load Factor and Loaded Side
     * @return the out string
     */
    @Override
    public String toString() 
    {
        return super.toString() + "  lf:" + loadFactor + "%"
                + "  ls:" + loadedSide;
    }

    /**
     * @return load factor of LoadedDie
     */
    public int getLoadFactor() {
        return loadFactor;
    }

    /**
     * Set the load factor of the LoadedDie
     * @param loadFactor
     */
    public void setLoadFactor(int loadFactor) {
        this.loadFactor = loadFactor;
    }
}
