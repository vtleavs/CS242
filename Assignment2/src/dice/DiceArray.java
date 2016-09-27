package dice;

import java.util.Arrays;

/**
 *
 * @author Ben
 */
public class DiceArray 
{
    public static void main(String[] args) 
    {
        // create dice array
        Die dice[] = new Die[5];
        
        // initialize dice
        dice[0] = new Die(12);
        dice[1] = new Die(12);
        dice[2] = new Die(12);
        dice[3] = new LoadedDie(12, 2, 50);
        dice[4] = new LoadedDie(12, 7, 50);
        
        // roll all dice
        for(Die d : dice)
            d.roll();
        
        // print out all dice
        for(Die d : dice)
            System.out.println(d);
        
        // sort dice
        System.out.println("\nSorting..");
        Arrays.sort(dice);
        
        // print out all dice again
        for(Die d : dice)
            System.out.println(d);
    }
}
