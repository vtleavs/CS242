
import java.util.Scanner;

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
    }
}
