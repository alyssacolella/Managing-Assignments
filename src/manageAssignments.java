import java.util.LinkedList;
import java.util.Scanner;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author alyssacolella
 * tui91984@temple.edu
 * Lab 4 - Assign 2
 * manageAssignments class
 * This class includes the methods to add, remove, order and see a list of assignments inputted by the user
 */
public class manageAssignments 
{
    /**
     * This method asks the user details about an assignment and adds the assignment to two linked lists
     * @param assignments linked list
     * @param assignmentsUnordered linked list
     */
    private static void addAssignment(LinkedList<Assignment> assignments, LinkedList<Assignment> assignmentsUnordered)
    {   
        Scanner in = new Scanner(System.in);
       
        System.out.print("What is the course ID (ex: CIS 2168)? ");
        String courseID = in.nextLine();
        System.out.println();
        
        System.out.print("What is the assignment? ");
        String assignment = in.nextLine();
        System.out.println();
        
        System.out.print("What is the due date of the assignment (MM/dd/yyyy)? ");
        String dueDate = in.nextLine();
        System.out.println();
        
        Assignment a = new Assignment(courseID, assignment, dueDate); 
        assignments.add(a);   
        assignmentsUnordered.add(a);
    }
    
    /**
     * This method allows the user to remove an assignment from the linked list
     * @param assignmentList linked list 
     * @param assignmentsUnordered linked list 
     */
    private static void removeAssignment(LinkedList<Assignment> assignments, LinkedList<Assignment> assignmentsUnordered)
    {
        Scanner in = new Scanner(System.in);
        
        String course;
        String task;
        
        System.out.print("Which course do you want to remove an assignment from (type in course id)? ");
        course = in.nextLine();
        System.out.println();
                
        System.out.print("Which assignment do you want to remove (type in assignment name)? ");
        task = in.nextLine();
        System.out.println();
           
        if(assignments.size() < 0)
        {
            System.out.println("There are no assignments to remove.");
        }
        
        for(int i = 0; i < assignments.size(); i++)
        {
            if(assignments.get(i).getCourseID().equals(course) && assignments.get(i).getAssignment().equals(task))
            {
                assignments.remove(assignments.get(i));
            }
            
            if(assignmentsUnordered.get(i).getCourseID().equals(course) && assignmentsUnordered.get(i).getAssignment().equals(task))
            {
                assignmentsUnordered.remove(assignmentsUnordered.get(i));
            }
        } 
    }
    
    /**
     * This method displays the list of assignments in order of due date for the user to see
     * @param assignmentList linked list
     */
    private static void displayListByDueDate(LinkedList<Assignment> assignments)
    {        
        if(assignments.size() == 0)
        {
            System.out.println("You have no assignments!\n");
        }
        
        else
        {
            System.out.println("\nCurrent Assignment List in Order of Due Date: \n");
            
            for(int i = 0; i < assignments.size(); i++)
            {
                Assignment current = assignments.get(i);
                System.out.println(current.toString());
            }
            System.out.println();
        }      
    }
    
    /**
     * /**
     * This method displays the list of assignments in order date assigned for the user to see
     * @param assignmentList linked list
     */
    private static void displayListByAssignedDate(LinkedList<Assignment> assignmentsUnordered)
    {
        if(assignmentsUnordered.size() == 0)
        {
            System.out.println("You have no assignments!\n");
        }
        
        else
        {
            System.out.println("\nCurrent Assignment List in Order of Date Assigned: \n");
           
            for(int i = 0; i < assignmentsUnordered.size(); i++)
            {
                Assignment current = assignmentsUnordered.get(i);
                System.out.println(current.toString());
            }
            System.out.println(); 
        }       
    }
    
    /**
     * This method parses the due date from a string to date type and orders the assignments chronologically
     * @param assignmentList linked list
     * @throws ParseException exception thrown if date is not in correct format
     */
    private static void orderAssignments(LinkedList<Assignment> assignments) throws ParseException 
    {
        for(int j = 0; j < assignments.size(); j++)
        {
            for(int i = 0; i < assignments.size() - 1; i++)
            {
                String s1 = assignments.get(i).getDueDate();
                String s2 = assignments.get(i+1).getDueDate();
                SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
                Date date1 = parser.parse(s1);
                Date date2 = parser.parse(s2);
            
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            
                if(date1.compareTo(date2) > 0)
                {
                    String strDate1 = formatter.format(date1);
                    String strDate2 = formatter.format(date2);
                
                    Assignment temp = assignments.get(i+1);
                    String tempDate = strDate2;
  
                    assignments.set(i + 1, assignments.get(i));
                    strDate2 = strDate1;
                
                    assignments.set(i, temp);
                    strDate1 = tempDate;
                
                    assignments.get(i).setDueDate(strDate1);
                    assignments.get(i+1).setDueDate(strDate2);
                }    
            }
        }
    }
    
    public static void main(String[] args) throws ParseException 
    {
        boolean done = false;
        
        Scanner in = new Scanner(System.in);
        String choice;
        
        //linked lists for ordered and unordered lists of assignments
        LinkedList<Assignment> assignmentsByDueDate = new LinkedList<>();
        LinkedList<Assignment> assignmentsUnordered = new LinkedList<>();
        
        while(!done) //until user quits
        {
            //ask user which action they would like to perform 
            System.out.print("\nWould you like to add an assignment, remove an assignment, see all assignments, or quit (type add, remove, see, or q to quit)? " );
            String input = in.nextLine();
            System.out.println();
            
            if(input.equals("add"))
            {
                addAssignment(assignmentsByDueDate, assignmentsUnordered); //add assignment to list
                orderAssignments(assignmentsByDueDate); //order assignments by due date
            }
            
            else if(input.equals("remove"))
            {
                displayListByDueDate(assignmentsByDueDate); //display list so user can see for reference
                removeAssignment(assignmentsByDueDate, assignmentsUnordered); //remove assignment from list
            }
            
            else if(input.equals("see"))
            {
                System.out.print("Enter a if you want the list ordered by date assigned or d if you want a list in order of due date: ");
                choice = in.nextLine();
                
                if(choice.equals("a"))
                {
                    displayListByAssignedDate(assignmentsUnordered);
                }
                else if(choice.equals("d"))
                {
                    displayListByDueDate(assignmentsByDueDate);
                }
            }
            
            else if(input.equals("q"))
            {
                done = true; //exit loop and quit program 
            }
        }
    }  
}