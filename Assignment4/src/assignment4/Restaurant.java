/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Ben
 */
public class Restaurant 
{
    public static Waiter[] waiters;
    public static Thread[] waiterThreads;
    
    public static void main(String[] args) 
    {
        File inputFile;
        
        Scanner userIn = new Scanner(System.in);
        
        System.out.print("Enter the name of the file to test\n--> ");
        inputFile = new File(userIn.next());
        readFile(inputFile);
        
        waiterThreads = new Thread[waiters.length];
        for(int i = 0; i < waiters.length; ++i)
        {
            waiterThreads[i] = new Thread(waiters[i]);
            waiterThreads[i].setName("Waiter Thread " + i);
            waiterThreads[i].start();
        }
            
    }
    
    public static void readFile(File inFile)
    {
        BufferedReader reader;
        String[] waiterData;
        int numWaiters;
        
        try 
        {
            reader = new BufferedReader(new FileReader(inFile));
            
            numWaiters = Integer.parseInt(reader.readLine());
            waiterData = new String[numWaiters];
            for(int i = 0; i < numWaiters; ++i)
                waiterData[i] = reader.readLine();
        }
        catch (IOException e) 
        {
            System.out.print("The method readFile terminated early ");
            if(e instanceof FileNotFoundException)
                System.out.println("due to a bad input file.");
            else if(e instanceof IOException)
                System.out.println("due to an improperly formatted input file.");
            return;
        }
        
        waiters = new Waiter[numWaiters];
        for(int i = 0; i < numWaiters; ++i)
        { 
            String[] waiterDataSplit = waiterData[i].split(" ");
            String waiterName = waiterDataSplit[0];
            String[] customerNames = new String[waiterDataSplit.length/4];
            String[][] courses = new String[waiterDataSplit.length/4][3];
            for(int j = 2; j < waiterDataSplit.length-1; j += 4)
            {
                int ci = (j-2)/4; // customer index
                customerNames[ci] = waiterDataSplit[j];
                courses[ci][0] = waiterDataSplit[j+1];
                courses[ci][1] = waiterDataSplit[j+2];
                courses[ci][2] = waiterDataSplit[j+3];
            }

            Table[] tables = new Table[customerNames.length];
            for(int j = 0; j < tables.length; ++j)
                tables[j] = new Table();
            
            waiters[i] = new Waiter(tables, waiterName, customerNames, courses);
        }    
    }
    
}
