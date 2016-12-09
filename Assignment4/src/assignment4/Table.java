/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4;

/**
 *
 * @author Ben
 */
public class Table 
{
    private String course;
    private boolean isEmpty;
    private Object obj;
    
    public Table()
    {
        isEmpty = true;
    }
    
    public void serve(String course)
    {
        isEmpty = false;
        this.course = course;
    }
    
    public String eat()
    {
        return "";
    }

    public boolean getIsEmpty() {
        return isEmpty;
    }

    public String getCourse() {
        return course;
    }
    
    
    
}
