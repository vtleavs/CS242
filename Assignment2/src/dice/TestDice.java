package dice;

/**
 * Test class for Die class
 *  
 * @since 9/6/2016
 * @author Benji Leavitt
 */
public class TestDice 
{
    /**
     * Main method
     * @param args 
     */
    public static void main(String[] args)
    {   
        System.out.println("-----RUN OLD TESTS (For Continuity)-----\n");
        testOld();
        System.out.println("\n\n\n-----RUN NEW TESTS-----\n");
        testNew();
    }

    static void testNew()
    {
        Die d1 = new Die();
        Die d2 = new Die();
        Die d3 = new Die();

        try
        {
            d1 = new Die();
            d2 = new Die(10);
            d3 = new Die(4);
        }
        catch(IllegalArgumentException e)
        {
            System.err.println(e.getMessage() + " in Die Constructors");
        }

        // Test toString
        
        System.out.println(d1.toString());
        System.out.println(d2.toString());
        System.out.println(d3.toString());

        d1.roll();
        d2.roll();
        d3.roll();

        System.out.println("\nRolling..");

        // Restest toString after rolling
        System.out.println(d1.toString());
        System.out.println(d2.toString());
        System.out.println(d3.toString() + '\n');

        // Test frequency of loaded side (Should be 0%, ~10%, ..., ~90%, 100%)
        // The second value is the chance of the non loaded sides (side 2 in
        // this case)
        for(int i = 0; i <= 100; i+=10)
        {
            LoadedDie ld = new LoadedDie(10, 3, i);

            int sum = 0;
            for(int j = 0; j < 100000; ++j)
            {
                if(ld.roll() == 3)
                    ++sum;
            }
            System.out.print((sum/100000.0)*100 + "%  --  ");

            sum = 0;
            for(int j = 0; j < 100000; ++j)
            {
                if(ld.roll() == 2)
                    ++sum;
            }
            System.out.println((sum/100000.0)*100 + "%");
        }
        
        // run frequency tests on a loaded D6
        frequencyTest(new LoadedDie(6, 3, 50));
    }
    
    static void testOld()
    {
        int numDice = 20;
        
        // Create numDice dice
        Die[] dice = new Die[numDice];

        // Initialize all dice
        for(int i = 0; i < numDice; ++i)
            dice[i] = new Die();

        // Check tops of dice
        System.out.print("Tops of all dice: ");
        for(Die d : dice)
            System.out.print(d.getTop() + " ");
        System.out.println();

        // Roll all dice
        System.out.print("Tops of all dice after roll: ");
        for(Die d : dice)
            System.out.print(d.roll() + " ");
        System.out.println();

        // Check to see if the dice tops are constant
        System.out.print("Tops of all dice after no roll: ");
        for(Die d : dice)
            System.out.print(d.getTop() + " ");
        System.out.println();

        //average all dice (should be 3.5ish)
        double tot = 0;
        for(Die d : dice)
            tot += d.getTop();
        System.out.println("Average Roll:" + tot/numDice);
        
        frequencyTest(new Die());
    }
    
    // Only 6 sided dice
    static void frequencyTest(Die fDie)
    {
        // Begin frequency testing
        for(int i = 1; i < 7; ++i)
        {
            
            // calculate frequencies of numbers rolled an exponentially 
            // increasing amount of times.
            int n1 = 0, n2 = 0, n3 = 0, n4 = 0, n5 = 0, n6 = 0;
            for(int j = 0; j < Math.pow(10, i); ++j)
            {    
                switch(fDie.roll())
                {
                    case 1: n1++;
                        break;
                    case 2: n2++;
                        break;
                    case 3: n3++;
                        break;
                    case 4: n4++;
                        break;
                    case 5: n5++;
                        break;
                    case 6: n6++;
                        break;
                }
            }

            // calculate average frequency
            double fAve = (double)(n1+n2+n3+n4+n5+n6)/6.0;
            
            // calculate percent difference from the mean for all 
            // states of the die
            double pd1 = ((n1-fAve)/(n1+fAve))*200;
            double pd2 = ((n2-fAve)/(n2+fAve))*200;
            double pd3 = ((n3-fAve)/(n3+fAve))*200;
            double pd4 = ((n4-fAve)/(n4+fAve))*200;
            double pd5 = ((n5-fAve)/(n5+fAve))*200;
            double pd6 = ((n6-fAve)/(n6+fAve))*200;
            
            // dispaly all frequencies and percent differences
            System.out.println("\n\nFrequencies over " 
                    + Math.pow(10, i) + " rolls" 
                    + "\n    1: " + n1 + " | %diff: " + pd1 + "%"
                    + "\n    2: " + n2 + " | %diff: " + pd2 + "%"
                    + "\n    3: " + n3 + " | %diff: " + pd3 + "%"
                    + "\n    4: " + n4 + " | %diff: " + pd4 + "%"
                    + "\n    5: " + n5 + " | %diff: " + pd5 + "%"
                    + "\n    6: " + n6 + " | %diff: " + pd6 + "%");

            System.out.println("\n    Frequency Average: " + fAve);

            // calculate average percent difference
            double pdAve = (pd1 + pd2 + pd3 + pd4 + pd5 + pd6)/6.0;

            System.out.println("\n    Average %diff: " + pdAve + "%");
        }
    }
}
