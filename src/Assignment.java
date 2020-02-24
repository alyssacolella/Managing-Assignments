
/*
 * @author alyssacolella
 * tui91984@temple.edu
 * Assignment class
 * This class includes the constructor for an assignment, getter and setter methods, and a toString method
 */
public class Assignment 
{
    //private variables for details of assignment
    private String courseID;
    private String assignment;
    private String dueDate;
    
    public Assignment()
    {  
    }
    
    /**
     * Assignment constructor
     * @param courseID course id 
     * @param assignment description of assignment 
     * @param dueDate date assignment is due
     */
    public Assignment(String courseID, String assignment, String dueDate)
    {
        this.courseID = courseID;
        this.assignment = assignment;
        this.dueDate = dueDate;
    }
    
    //setter methods
    public void setCourseID(String courseID)
    {
        this.courseID = courseID;
    }
    
    public void setAssignment(String assignment)
    {
        this.assignment = assignment;
    }
    
    public void setDueDate(String dueDate)
    {
        this.dueDate = dueDate;
    }
    
    //getter methods
    public String getCourseID()
    {
        return courseID;
    }
    
    public String getAssignment()
    {
        return assignment;
    }
    
    public String getDueDate()
    {
        return dueDate;
    }
    
    //method to print info of an assignment
    public String toString()
    {
        return "Course ID: " + courseID + " | Assignment: " + assignment + " | Due Date: " + dueDate;
  
    }
}
