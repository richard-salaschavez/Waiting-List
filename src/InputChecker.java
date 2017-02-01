import java.util.Scanner;
import java.io.*;
/**
 * Will take a file input, read it, and perform the necessary actions and calls needed
 * 
 * @author Richard Salas Chavez (7764077)
 * @version June 3, 2016
 */
public class InputChecker
{
    // instance variables
    private String input;
    private LinkedList studentList = new LinkedList(); // is this a ok?
    private LinkedList courseList = new LinkedList();
 
    /**
     * Constructor for objects of class InputChecker
     */
    public InputChecker(String in)
    {
        // initialise instance variables
        input = in;
    }
    
    /**
     * reads input file line by line and calls the method checkInput every new line
     *
     * @param  
     * @return     void
     */
    public void readInput()
    {
        try {
            Scanner reader = new Scanner(new File(input));
            String line;
      
            while (reader.hasNextLine()){ //will only run so long as there is a line in the file
                line = reader.nextLine(); 
                checkInput(line.trim());
            } 
        } catch(FileNotFoundException e) {
            System.out.println("File was not found!");
        }
    } // end readFile()

    /**
     * checks if Student is a duplicate or not
     *
     * @param  stud   Student which you wish to check
     * @return     output
     */
    public int checkStudent(Student stud)
    {
        //int duplicate = studentList.checkDuplicate(stud);
        int duplicate = studentList.indexOf(stud);
        int output = 0;  
        if (duplicate >= 0)
        {
            output = 1; // corresponds to "DUPLICATE"
            System.out.println("DUPLICATE");
        }
        else
        {
            studentList.add(stud); // add new Student to system
            System.out.println("CONFIRMED");
            output = 2; // corresponds to "CONFIRMED" 
        }
        return output;
    }

    /**
     * checks if the course is a duplicate or not
     *
     * @param  course   Course object you wish to check
     *         parts1   String[] that contains preRequisite courses  
     * @return     the sum of x and y
     */
    public int checkCourse(Course course, String[] parts1)
    {
        int duplicate = courseList.indexOf(course);
        int output = 0;         
        SystemCourse completeCourse; //with preReqs List
        if (duplicate >= 0)
        {
            System.out.println("DUPLICATE");
        }
        
        else if (parts1.length > 3) // if there are pre req courses
        {
            String preReqs = parts1[3].trim(); // prerequite courses
            String[] preReqsParts = preReqs.split(" "); 
            LinkedList preRequisites = makePreReqList(preReqsParts);
            if(checkPreReqs(preRequisites)) //check that preReqs are in the system
            {
                completeCourse = new SystemCourse(course.getCourseId(), preRequisites); //course with preReq list 
                courseList.add(completeCourse); 
                System.out.println("CONFIRMED");
            }
            
            else
            {
                System.out.println("NOT FOUND"); // pre reqs not found in system
            }
        }
        
        else // if there aren't prereq courses present
        {
            completeCourse = new SystemCourse(course.getCourseId(), new LinkedList()); // empty preReqs list
            courseList.add(completeCourse); // note: this course does not have a prereq list
            System.out.println("CONFIRMED");
        }
        return output;
    }

    /**
     * takes a String[] from a String of PreRequisite courses and forms as many Course
     * objects as needed  
     *
     * @param  str  String[] split String of PreRequisite
     * @return      LinkedList of prerequisite courses 
     */
    public LinkedList makePreReqList(String[] str)
    {
        LinkedList preReqsList = new LinkedList();
        //loop through preReqsParts String to make Course object for preReqs
        for (int i = 0; i < str.length; i = i + 2) 
        {
            Course preReq = new Course(str[i].trim() + " " + str[i + 1].trim()); // makes a course object
            preReqsList.add(preReq); // we do this because the preReqList is made of Course objects
        }
        return preReqsList;
    }

    /**
     * checks that preRequites of a course are in the system
     *
     * @param  preReqsList   a Linked list of pre requisite courses
     * @return     boolean true if all preRequisties were found in the system and vice versa 
     */
    public boolean checkPreReqs(LinkedList preReqsList)
    {
        boolean found = false;
        int count = 0; // to keep track of how many courses were found
        //loop through preReqsParts String to make Course object for preReqs
        for (int i = 0; i < preReqsList.size(); i++) 
        {
            Course preReq = (Course) preReqsList.get(i);
            int duplicatePosition = courseList.indexOf(preReq);
            //this time you want there to be duplicate and keep track of where it was
            if (duplicatePosition >= 0)
            {
                count++;
            }
        }
        
        if (count == preReqsList.size())
        {
            found = true;
        }
       
        return found;
    }

    /**
     * Takes an inputLine, checks what command it is an carries out the respective command 
     * 
     * @param  inputLine   String of input 
     * @return void 
     */
    public void checkInput(String inputLine)
    {
        String[] parts = inputLine.split(" ",3); //this will split up the string into parts separated by a space
        
        String action = parts[0]; //the first part of the input is the action
 
        if(action.equals("NEW")) // add student to the system if not a duplicate
        {
            int studentNumber = Integer.parseInt(parts[1]);
            String studentName = parts[2]; // first and last name
            Student stud = new Student(studentNumber, studentName); // create new Student obj
            checkStudent(stud); // check if student is duplicate
            System.out.println();
        }
          
        else if(action.equals("COURSE"))  
        {
            String[] parts1 = inputLine.split(" ",4);
            String courseName = parts1[1];
            String courseNumber = parts1[2];
            // create new Course object 
            Course course = new Course(courseName + " " + courseNumber); 
            checkCourse(course, parts1);
            System.out.println();
        }
            
        else if(action.equals("ADD"))  
        { 
            int studentNumber = Integer.parseInt(parts[1]);
            String newCourse = parts[2];
            
            Course findCourse = new Course(newCourse); // make new Course
            Student newStud = new Student(studentNumber); // make new Student
            int foundC = courseList.indexOf(findCourse); 
            int foundS = studentList.indexOf(newStud);
            
            if (foundC >=0 && foundS >= 0) //student and course in the system
            {
                SystemCourse foundCourse = (SystemCourse) courseList.get(foundC); // gets course from course list
                Student foundStudent = (Student) studentList.get(foundS); // gets student from course list
                LinkedList waitlist = foundCourse.getWaitList(); // get waitlist
                LinkedList classlist = foundCourse.getClassList(); // get classlist
                int waitlistPosition = waitlist.indexOf(newStud); // check that the student is not already in the course's waitlist
                int classlistPosition = classlist.indexOf(newStud); // check that the student is not already in the class list 
                if ((waitlistPosition == -1) && (classlistPosition == -1)) // student is NOT already in waitlist or course
                {
                    // check that the student hasn't already passed the course
                    LinkedList newTranscript = foundStudent.getTranscript(); // get student's transcript
                    Course tCourse = new Course(foundCourse.getCourseId()); //change foundCourse to a Course obj
                    int tPos = newTranscript.indexOf(tCourse);  // find course in transcript 
                    if(tPos >= 0) // course was found in student's transcript
                    {
                        TranscriptCourse checkCourse = (TranscriptCourse) newTranscript.get(tPos); // get that course from student's transcript
                        if(!((checkCourse.getStatus().trim()).equals("PASS"))) // if student hasn't passed the course before 
                        {
                            foundCourse.add(foundStudent); // potentially add him/her to a list
                        }
                        else
                        {
                            System.out.println("Student has already taken the course!");
                        }
                    }
                    else // if it isn't found then it's ok to go 
                    {
                        foundCourse.add(foundStudent);
                    }
                }
                else
                {
                    System.out.println("DUPLICATE"); // student is already in wait list
                }
            }
            
            else
            {
                System.out.println("NOT FOUND");
            }
            System.out.println();
        }
            
        else if(action.equals("REMOVE"))
        {
            int studentNumber = Integer.parseInt(parts[1]); // student number
            String newCourse = parts[2]; // course name
            
            Course findC = new Course(newCourse); // make new Course
            Student findS = new Student(studentNumber); // make new Student
            int fC = courseList.indexOf(findC); 
            int fS = studentList.indexOf(findS);
            
            if (fC >= 0 && fS >= 0)
            {
                SystemCourse foundCourse = (SystemCourse) courseList.get(fC); // gets course from course list
                Student foundStudent = (Student) studentList.get(fS); // gets student from course list
                foundCourse.remove(foundStudent);
            }
            else
            {
                System.out.println("NOT FOUND");
            }
            System.out.println();
        }
            
        else if(action.equals("TRANSCRIPT"))
        {
            String[] parts2 = inputLine.split(" ",5);  
            
            int studentNumber = Integer.parseInt(parts2[1]); // student number
            String newCourse = parts2[2] + " " + parts2[3]; // course name
            String newStatus = parts2[4]; // result of past or current courses to students transcript
            
            //1st check that both the student and the course are both in the system 
            Student studObj = new Student(studentNumber); // find correct student using student number
            int studentPos = studentList.indexOf(studObj); // student position in system
            Course courseObj = new Course(newCourse); 
            int coursePos = courseList.indexOf(courseObj); // course position in system
            
            if (studentPos >= 0 && coursePos >= 0) // if student and course both in the system
            {
                Student correctStud = (Student) studentList.get(studentPos); // gets student from the system
                LinkedList foundTranscript = correctStud.getTranscript(); // gets student's transcript
                int coursePosition = foundTranscript.indexOf(courseObj); // find course in student's transcript
                if (coursePosition == -1) // course wasn't found in student's transcript
                {
                    if(newStatus.equals("PASS"))
                    {
                        TranscriptCourse transCourse = new TranscriptCourse(newCourse, newStatus, "");     
                        correctStud.addToTranscript(transCourse);
                    }    
                    else if(newStatus.equals("FAIL"))
                    {
                        TranscriptCourse transcriptCourse = new TranscriptCourse(newCourse, newStatus, "");     
                        correctStud.addToTranscript(transcriptCourse);
                        // remove student from waitlists or courses
                        for(int i = 0; i < courseList.size(); i++) // loop through all courses to check their preReqs
                        {
                            SystemCourse sysCourse = (SystemCourse) courseList.get(i); // downcast
                            LinkedList preReqs = sysCourse.getPreReqs(); // get preReqs
                            for(int j = 0; j < preReqs.size(); j++) // going through preReqs
                            {
                                Course check = (Course) preReqs.get(j); // gets course from list of preReqs
                                if(check.equals(courseObj)) // found preReq
                                {
                                    LinkedList waitList = sysCourse.getWaitList();
                                    LinkedList classList = sysCourse.getClassList();
                                    if(waitList.size() > 0 && classList.size() > 0) 
                                    {
                                        int pos = waitList.indexOf(correctStud); // check if student is in waitlist
                                        if(pos >= 0)
                                        {                                                       
                                            waitList.remove(pos);
                                        }
                                        pos = classList.indexOf(correctStud); // check if student is in classlist
                                        if(pos >= 0)
                                        {
                                            classList.remove(pos);
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    else if(newStatus.equals("CURRENT"))
                    {
                        TranscriptCourse currentCourse = new TranscriptCourse(newCourse, newStatus, "class list");    
                        correctStud.addToTranscript(currentCourse);
                    }
                    System.out.println("CONFIRMED");
                }
                else // if the course is found and you wish to change status from CURRENT to PASS/FAIL or from FAIL to PASS, to avoid duplicates
                {
                    TranscriptCourse cour = (TranscriptCourse) foundTranscript.get(coursePosition); // downcast to course
                    String status = cour.getStatus();
                    if((status.equals("CURRENT")) && ((newStatus.equals("PASS")) || newStatus.equals("FAIL"))) 
                    {
                        cour.setList(""); // resets list
                        cour.setStatus(newStatus); // change status from CURRENT to either PASS or FAIL
                    }
                    else if((status.equals("FAIL")) && (newStatus.equals("PASS"))) // student has previously failed the course but has now passed it 
                    {
                        // if student hasn't passed this course yet change it to PASS
                        cour.setStatus(newStatus);
                    }
                    else
                    {
                        // if current status is PASS don't do anything
                    }
                }
            }
            else
            {
                System.out.println("NOT FOUND"); // student or course wasn't found in the system
            }
            System.out.println();
        }
    
        else if(action.equals("CAPACITY"))
        {
            String[] parts3 = inputLine.split(" ",4);  
            String newCourse = parts3[1] + " " + parts3[2]; // course name
            int newCapacity = Integer.parseInt(parts3[3]); // capacity of course
            
            // first look for course in system
            Course lookCourse = new Course(newCourse);
            int pos = courseList.indexOf(lookCourse); 
            if (pos >= 0) // found
            {
                SystemCourse foundIt = (SystemCourse) courseList.get(pos);
                foundIt.capacity(newCapacity); // increases capacity
            }
            
            else
            {
                System.out.println("NOT FOUND");
            }
            System.out.println();
        }
            
        else if(action.equals("STATUS"))
        {
            int studentNumber = Integer.parseInt(parts[1]); 
            Student student = new Student(studentNumber); 

            int findStudent = studentList.indexOf(student);
            if(findStudent >= 0) // found
            {
                Student foundStud = (Student) studentList.get(findStudent);
                foundStud.status(); // call status function
            }
            else
            {
                System.out.print("NOT FOUND");
            }
            System.out.println();
        }
            
        else if(action.equals("LISTS"))
        {
            String newCourse = parts[1].trim() + " " + parts[2].trim();
            Course listCourse = new Course(newCourse);
            int cPos = courseList.indexOf(listCourse); // course position
            if (cPos >= 0)
            {
                SystemCourse c = (SystemCourse) courseList.get(cPos); // course
                c.lists();
            }
            else
            {
                System.out.print("NOT FOUND");
            }
            System.out.println();
        }
            
        else if(action.equals("QUIT"))
        {
            System.out.println("DONE");   
            System.exit(0);
        }
        
        else if(action.equals("#"))
        {
            System.out.println(inputLine);   
            System.out.println();
        }
    }
}