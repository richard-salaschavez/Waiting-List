
/**
 * Course which will go into student's transcript. More specialized    
 * than parent as it has a course status (PASS/FAIL/CURRENT) and 
 * a list field indicating if the student is in a waitlist or class list or
 * none for the specific course in his transcript   
 * 
 * @author Richard Salas Chavez (7764077)
 * @version June 3, 2016
 */
public class TranscriptCourse extends Course
{
    // instance variables - replace the example below with your own
    private String status;
    private String list; // whichList is the student in? Waiting list or class list 
    /**
     * Constructor for objects of class Transcript
     */
    public TranscriptCourse(String courseId, String courseStatus, String list)
    {
        super(courseId);
        status = courseStatus;
        this.list = list;
    }

    /**
     * setter, sets status field
     *
     * @param   String stat
     * @return     void 
     */
    public void setStatus(String stat)
    {
        status = stat;
    }

    /**
     * getter, gets status field
     *
     * @param  
     * @return     status field
     */
    public String getStatus()
    {
        return status;
    }
    
    /**
     * setter, sets list item
     *
     * @param  list   String
     * @return     void
     */
    public void setList(String list)
    {
        this.list = list;
    }

    /**
     * getter, gets list field
     * 
     * @param  
     * @return     String field list
     */
    public String whichList()
    {
        return list;
    }
    
    /**
     * toString returns a string with desired data
     *
     * @param  
     * @return     courseId, which is the course name
     */
    public String toString()
    {
        return super.toString() + " " + status;
    }

}
