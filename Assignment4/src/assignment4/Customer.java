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
public class Customer implements Runnable
{
    private static final int MAX_CUSTOMER_MILLIS = 4000;
    
    private String customerName;
    private Table table;
    
    public Customer(Table table, String customerName)
    {
        this.table = table;
        this.customerName = customerName;
    }

    @Override
    public void run() 
    {
        if(!table.getIsEmpty())
        {
            table.eat();
            System.out.println(customerName + " is eating " + table.getCourse());
        }
        try {
            wait(4000);
        } catch (InterruptedException ex) {
            
        }
    }
    
    
}
