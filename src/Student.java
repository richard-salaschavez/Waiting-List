
/**
 * Student holds information about a student such as their student number and name.
 * 
 * @author Richard Salas Chavez (7764077)
 * @version June 3, 2016
 */

public class Student extends ListItem
{
    // instance variables 
    private int studentNumber;
    private String studentName;
    private LinkedList transcript;
    //private status;

    /**
     * Constructor for objects of class Student
     */
    public Student(int studentNum)
    {
        // initialise instance variables
        studentNumber = studentNum;
        transcript = new LinkedList(); // not sure if I need this 
    }
    
    /**
     * Constructor for objects of class Student
     */
    public Student(int studentNum, String name)
    {
        // initialise instance variables
        studentNumber = studentNum;
        studentName = name;
        transcript = new LinkedList();
    }

    /**
     * adds TrancriptCourse object to student's transcript
     * 
     * @param  course   TranscriptCourse you wish to input into transcript
     * @return     void
     */
    public void addToTranscript(TranscriptCourse course)
    {
        transcript.add(course);
    }
    
    /**
     * change status of course
     * 
     * @param  y   stat, new status of course
     * @return      
     */
    public void status()
    {
        System.out.println(toString());
        System.out.println("Student's transcript: ");
        System.out.println(transcript.toString());
        // Which courses have the CURRENT status
        // what course is the student registered in? 
        String registered = "";
        // which course is the student waiting for? include position in waitlist
        String waiting = "";
        LinkedList thisTranscript = this.getTranscript();
        for(int k = 0; k < thisTranscript.size(); k++) // check transcript for which courses the student is waiting for
        {
                TranscriptCourse currentCourse = (TranscriptCourse) thisTranscript.get(k);
                String checkList = currentCourse.whichList();
                if (checkList.equals("class list"))
                {
                    registered += currentCourse.getCourseId() + " ";
                }
                else if (checkList.equals("waitlist")) 
                {
                    waiting += currentCourse.getCourseId() + "  ";
                }
        }        
        System.out.println("Registered in: " + registered);
        System.out.println("Waiting for: " + waiting);
    }

    /**
     * equals checks if both Student objects have the same student number
     *
     * @param  item   ListItem you wish to check
     * @return     boolean true or false, depending if the two items are equal 
     */
    public boolean equals(ListItem item)
    {
        boolean equal = false;
        Student stud = (Student) item;
        if ((this.getStudentNumber()) == (stud.getStudentNumber()))
        {
            equal = true;
        }
        return equal;
    }

    /**
     * getter of field studentNumber
     *
     * @param  
     * @return    int studentNumber field
     */
    public int getStudentNumber()
    {
        return studentNumber;
    }

    /**
     * getter of field transcript
     *
     * @param  
     * @return     LinkedList transcript field
     */
    public LinkedList getTranscript()
    {
        return transcript;
    }

    /**
     * toString returns a string with desired data
     *
     * @param  
     * @return     courseId, which is the course name
     */
    public String toString()
    {
        String str = "";
        str += studentNumber + " " + studentName;
        return str;
    }

}
