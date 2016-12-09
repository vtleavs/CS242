/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ben
 */
public class Waiter implements Runnable
{
    private final static int MAX_WAITER_MILLIS = 4000; // must wait for between 0 and 4 seconds.
    private final static int N_COURSES = 3; // number of courses is exactly three.
    private Table[] tables; // array of Table objects this Waiter waits on
    private String waiterName; // name of this Waiter
    private String[] customers; // Customer at this table
    private String[][] courses; // multi-dimensional array of courses for each Customer of this Waiter

    public Waiter( Table[] tables, String waiterName, String[] customers, String[][] courses)
    {
        this.tables = tables;
        this.customers = customers;
        this.waiterName = waiterName;
        this.courses = courses;
        
        for(int i = 0; i < customers.length; ++i)
        {
            Thread t = new Thread(new Customer(this.tables[i], this.customers[i]));
            t.setName("Customer Thread " + i);
            t.start();
        }
    }
    
    @Override
    public void run() 
    {
        for(int i = 0; i < N_COURSES; i++)
        {
            for(int j = 0; j < customers.length; ++i)
            {
                if(tables[j].getIsEmpty())
                {
                    tables[j].serve(courses[j][i]);
                    System.out.println(waiterName + " serves " 
                            + customers[j] + " " + courses[j][i]);
                }
                try {
                    wait(4000);
                } catch (InterruptedException ex) {
                    
                }
            }
        }
    }
}
