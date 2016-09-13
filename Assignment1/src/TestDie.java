
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Test class for Die class
 *  
 * @since 9/6/2016
 * @author Benji Leavitt
 */
public class TestDie 
{
    /**
     * Main method
     * @param args 
     */
    public static void main(String[] args)
    {
        int numDice = 20;
        
        // Create numDice dice
        Die[] dice = new Die[numDice];

        // Initialize all dice
        for(int i = 0; i < numDice; ++i)
            dice[i] = new Die();

        // Check tops of dice
        for(Die d : dice)
            System.out.print(d.getTop() + " ");
        System.out.println();

        // Roll all dice
        for(Die d : dice)
            System.out.print(d.roll() + " ");
        System.out.println();

        // Check to see if the dice tops are constant
        for(Die d : dice)
            System.out.print(d.getTop() + " ");
        System.out.println();

        //average all dice (should be 3.5ish)
        double tot = 0;
        for(Die d : dice)
            tot += d.getTop();
        System.out.println(tot/numDice);
        
        
        for(int i = 0; i < numDice; ++i)
            dice[i] = new Die();
        
        int n1 = 0, n2 = 0, n3 = 0, n4 = 0, n5 = 0, n6 = 0;
        for(int i = 0; i < 100; ++i)
        {    
            for(Die d : dice)
            {
                switch(d.roll())
                {
                    case 1: n1++;
                        return;
                    case 2: n2++;
                        return;
                    case 3: n3++;
                        return;
                    case 4: n4++;
                        return;
                    case 5: n5++;
                        return;
                    case 6: n6++;
                        return;
                }
            }
        }
        
        System.out.println("Frequencies over 100 rolls - 1:" + n1 + ", 2:" + n2 
                + ", 3:" + n3 + ", 4:" + n4 + ", 5:" + n5 + ", 6:" + n6);
    }
}
