/**
 * WaitingListSystem
 *
 * COMP 2150 SECTION A01
 * ASSIGNMENT    Assignment 1
 * @author       Richard Salas Chavez, 7764077
 * @version      June 4, 2016
 *
 * PURPOSE: A waiting list system to keep track of registrations, and to
 * fill up course spaces with students when they become available 
 */

public class WaitingListSystem{
  
  //a string of characters entered from the keyboard
  public static void main(String[] args)
  {
    InputChecker firstInput = new InputChecker("firstTry.txt");
    firstInput.readInput();
  }
} //end class PalindromeApp