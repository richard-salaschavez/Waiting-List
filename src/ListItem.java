/**
 * Abstract class ListItem - ListItem is an abstract parent class of any items that
 * we wish to insert into a list, such as students or courses
 * 
 * @author Richard Salas Chavez (7764077)
 * @version June 3, 2016
 */
public abstract class ListItem 
{

    /**
     * toString returns a string with desired data
     *
     * @param  
     * @return     String of desired data
     */

    public abstract String toString();
    
    /**
     * equals will compare if ListItem objects are equal
     *
     * @param  item   ListItem you wish to compare
     * @return     boolean, true if items are equal and vice versa
     */
    public abstract boolean equals(ListItem item);
}
