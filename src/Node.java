/**
 * Node class, works with LinkedList, and holds ListItems
 * 
 * @author Richard Salas Chavez (7764077)
 * @version June 3, 2016
 */

public class Node
{
  private ListItem theItem;
  private Node link;
  
  /* Node(ListItem initItem, Node linkVal)
   *
   * constructor
   * input: parameters
   * output: Node object
   * parameters: (ListItem initItem, Node linkVal) 
   * no return value
   *
   */
  public Node(ListItem initItem, Node linkVal)
  {
      theItem = initItem;
      link = linkVal;
  }
  
  /* getData()
   *
   * returns data
   * input: n/a
   * output: n/a
   * no parameters
   * returns ListItem value
   *
   */
  public ListItem getData()
  {
    return theItem;
  }
  
  /* setData
   *
   * sets Data
   * input: n/a
   * output: n/a
   * parameters: ListItem newData
   * no return value
   *
   */
  public void setData(ListItem newItem)
  {
    theItem = newItem;
  }
  
  /* getLink
   *
   * gets the link
   * n/a
   * n/a
   * n/a
   * link
   *
   */
  public Node getLink()
  {
    return link;
  }
  
  /* set Link
   *
   * sets link 
   * parameter
   * n/a 
   * Node newLink
   * n/a
   *
   */
  public void setLink(Node newLink)
  {
    link = newLink;
  }
  
  /* toString
   *
   * overrites, returns data as String
   * n/a
   * n/a 
   * n/a 
   * String str
   *
   */
  public String toString()
  {
    return theItem.toString();
  }  
}