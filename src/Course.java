
/**
 * Course is a parent class of TranscriptCourse and SystemCourse. It has an identifier which is the course name
 * 
 * @author Richard Salas Chavez (7764077)
 * @version June 3, 2016
 */
public class Course extends ListItem
{
    // instance variables - replace the example below with your own
    private String courseId;
    
    /**
     * Constructor for objects of class Course
     */
    public Course(String courseId)
    {
        // initialise instance variables
        this.courseId = courseId; 
    }
    
    /**
     * equals checks if both Course objects have the same course identifier (courseId)
     *
     * @param  item   ListItem you wish to check
     * @return     boolean true or false, depending if the two items are equal
     */
    public boolean equals(ListItem item)
    {
        boolean equal = false;
        if(item instanceof Course)
        {
            Course course = (Course) item;
            if((this.getCourseId()).equals(course.getCourseId()))
                equal = true;
        }
        return equal;
    }
    
    /**
     * toString returns a string with desired data
     *
     * @param  
     * @return     courseId, which is the course name
     */
    public String toString()
    {
        return courseId;
    }

    /**
     * getter of field courseId
     *
     * @param  
     * @return     courseId
     */
    public String getCourseId()
    {
        return courseId;
    }
}
