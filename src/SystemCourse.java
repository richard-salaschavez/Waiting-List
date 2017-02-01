/**
 * Course which will go in the system. More specialized than parent as it has 
 * more fields : a capacity, a list of pre requisites, a waiting list and a class list 
 * 
 * @author Richard Salas Chavez (7764077)
 * @version June 3, 2016
 */
public class SystemCourse extends Course 
{
    // instance variables 
    private int capacity;
    private LinkedList preReqsList; //list of prerequisite courses
    private LinkedList waitList; // wait list of class 
    private LinkedList classList; // students enrolled in the course

    /**
     * Constructor for objects of class SystemCourse
     */
    public SystemCourse(String id,LinkedList preReqs)
    {
        super(id);
        capacity = 0; //initialize capacity to zero
        preReqsList = preReqs;  
        waitList = new LinkedList(); // start a new waiting list 
        classList = new LinkedList(); // initialize a new class list
    }

    /**
     * this method adds students to a course if there is room in the course
     * can only add students if the student has all the pre requiste courses 
     * 
     * @param  student   Student you wish to add to a course
     * @return     void
     */
    public void add(Student student)
    {
        int availableSpots = capacity - classList.size(); // available seats
        LinkedList transcriptList = student.getTranscript(); // get student's transcript
        int count = 0; // keep track of how many courses match
        if (preReqsList.size() > 0) // if there are prereqs for the course
        {
            for(int i = 0; i < preReqsList.size(); i++) // loop through course's pre reqs
            {
                // get name of course from the course's pre Reqs list and then make a TranscriptCourse obj  
                Course check = (Course) preReqsList.get(i); 
                // check that the student has all the pre reqs
                int pos = transcriptList.indexOf(check); // gets position
                if(pos >= 0) // the course was found in student's transcript
                {
                    TranscriptCourse transCourse = (TranscriptCourse) transcriptList.get(pos); // get correct course from student's transcript
                    if("PASS".equals(transCourse.getStatus().trim()) || "CURRENT".equals(transCourse.getStatus().trim())) // student has passed the course's pre req
                    {
                        count++;
                    }
                }
            }
        }
        // if there are no pre req courses or if prereqs have been checked
        if(count == preReqsList.size())
        {
            if(availableSpots > 0)
            {
                TranscriptCourse currentCourse = new TranscriptCourse(super.getCourseId(), "CURRENT", "class list");// make a transcript entry 
                classList.add(student); // add student to class list
                student.addToTranscript(currentCourse); // add a record to his/her transcript
                System.out.println("ADDED");
            }
            
            else // no available spots
            {
                TranscriptCourse currentCourse = new TranscriptCourse(this.getCourseId(), "CURRENT", "waitlist");// make a transcript entry 
                waitList.add(student); // add student to wait list
                student.addToTranscript(currentCourse); // add a record to his/her transcript
                System.out.println("CONFIRMED");
            }
        }
            
        else 
        {
            System.out.println("NO PREREQ");
        }
    }

    /**
     * increases capacity of course, and adds waiting students to class list
     * if there is room 
     *
     * @param  cap  int, number you wish to increase capacity by
     * @return    void
     */
    public void capacity(int cap)
    {
        capacity += cap; 
        int availableSpots = capacity - classList.size(); // available seats
        int waiting = waitList.size();  // student's waiting
        if(availableSpots > 0 && waiting > 0) // if there are available spots and students waiting
        {
            for(int i = 0; i < availableSpots; i++)
            {
                Student waitingStudent = (Student) waitList.get(0); // get's first student in line
                classList.add(waitingStudent); // add student to class list
                    
                LinkedList transcript = waitingStudent.getTranscript(); // get student's transcript
                int pos = transcript.indexOf(this); // get position of course in student's transcript
                TranscriptCourse currentEntry = (TranscriptCourse) transcript.get(pos); // get the transcript entry 
                currentEntry.setList("class list");// updates entry in student's transcript
                    
                waitList.remove(0); // get's rid of first in line so the next person in line can be first
            }
            System.out.println("CONFIRMED");
        }
        else // if there are not any spots available or no student's waiting
        {
            // do nothing
        }
    }

    /**
     * removes student from waitlist of a course
     * 
     * @param  stud  Student you wish to remove 
     * @return     void
     */
    public void remove(Student stud)
    {
        int lookStud = waitList.indexOf(stud);
        if (lookStud >= 0)
        {
            waitList.remove(lookStud); // removes student from waitlis
            System.out.println("CONFIRMED");
        }
        else
        {
            System.out.println("NOT APPLICABLE");
        }
    }
    
    /**
     * prints out the status of course i.e. who is in the course and who is waiting
     * 
     * @param 
     * @return     void
     */
    public void lists()
    {
        System.out.println(toString());
        // who's in the course
        System.out.println("Students in course:");
        System.out.println(classList.toString());
        // who's waiting for the course
        System.out.println("Students waiting for course:");
        System.out.println(waitList.toString());
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
        str += super.toString() + " "; //+ capacity + " " + preReqsList.toString();
        return str;
    }

    /**
     * getter of field waitList
     *
     * @param  
     * @return     LinkedList waitList field
     */
    public LinkedList getWaitList()
    {
        return waitList;
    }

    /**
     * getter of field classList
     *
     * @param  
     * @return     LinkedList classList field
     */
    public LinkedList getClassList()
    {
        return classList;
    }

    /**
     * getter of field preReqsList
     *
     * @param  
     * @return     LinkedList preReqsList field
     */
    public LinkedList getPreReqs()
    {
        return preReqsList;
    }
}
