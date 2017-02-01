/**
 * LinkedList will hold a linked list of Nodes
 * 
 * @author Richard Salas Chavez (7764077)
 * @version June 3, 2016
 */

public class LinkedList
{
  private Node first; //The firstNode in the list
  private Node last;
  private int listCount;
  
  /* LinkedList()
   *
   * constructor
   * input: no input
   * output: new object
   * no parameters
   * no return value
   *
   */
  public LinkedList()
  {
    first = last = null;
    listCount = 0;
  }
  
  /* add(String value)
   *
   * adds value to end of the list 
   * input: 
   * output
   * parameters: String value
   * no return value
   *
   */
  public void add(ListItem value)
  {
    Node newNode = new Node(value,null);
    
    if(last==null)
    {
      first = last = newNode;
    }
    else 
    {
      last.setLink(newNode);
      last = newNode;
    }
    listCount++;
  }
  
  /* get(int position)
   *
   * gets element at especific postion
   * n/a
   * n/a
   * int position
   * String
   *
   */
  public ListItem get(int position)
  {
    Node next = first; //initializes
    if (!(position >= 0 && position < listCount))
    {
      System.out.println("Invalid get position: " + position); 
      return null;
    }
    else
    {
      for(int i = 0; i < position; i++) //need to change the node that is in position - 1
      {
        next = next.getLink(); 
      }
      return next.getData();
    }
  }
  
  /* remove()
   *
   * removes element at especific position
   * n/a
   * n/a
   * int position
   * no return value
   *
   */
  public void remove(int position)
  {
    Node next = first; //initializes
    
    if (!(position >= 0 && position < size()))
    {
      System.out.println("Invalid remove position: " + position); 
    }
    
    else
    {
      if (position == 0)
      {
        first = first.getLink();
        if(first==null)
        {
          last=null;
        }
      }
      else
      {
        for(int i = 1; i < position && next.getLink() != null; i++) //need to change the node that is in position - 1
        {
          next = next.getLink(); 
        }
        next.setLink(next.getLink().getLink()); //this makes the change
      }
      listCount--;
    }
  }

  /* indexOf
   *
   * gets the first index of a value
   * parameter
   * n/a
   * String value
   * int index
   *
   */
  public int indexOf(ListItem value)
  {
    Node next = first;
    int found = -1;
    int i = 0;
    while(next != null) //checks that the key has yet to be found
    {
      if ((next.getData()).equals(value))
      {
        found = i;
        break;
      }
      next = next.getLink();
      i++;
    }
    return found;    
  }

  /* size
   *
   * gets the size of the LinkedList
   * n/a
   * n/a
   * n/a
   * size of LinkedList
   *
   */
  public int size()
  {
    return listCount;
  }

  /* toString
   *
   * returns LinkedList values as String
   * n/a 
   * n/a 
   * n/a 
   * returns LinkedList values as String
   *
   */
  public String toString()
  {
    String str = "";
    Node next = first; //starts at the first Node
    while(next != null)
    {
      str += next.getData().toString() + " ";
      next = next.getLink(); //gets the next Node
    }
    return str;
  }
}