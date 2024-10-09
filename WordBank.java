/*
  Author: Saul Burns
  The WordBank class will provide functions that scan the word lists to
  provide any necessary information that the game will need.
  Last Date Modified: 04/30/2024
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordBank {

  public static String getAnswerForPuzzleNumber(int puzzleNumber) {
    try {
      /* Create a file scanner to read answers.txt */
      Scanner scanner = new Scanner(new File("answers.txt"));

      /* Skip the first puzzleNumber number of words in the file */
      for (int i = 0; i < puzzleNumber; i++) {
        scanner.next();
      }

      /* Return the very next word */ 
      return scanner.next();

    } catch (Exception e) {
      /* Handle exception */
      System.out.println("Input/File not found!");
    }
    return null;
  }

  /* Return a true value by checking if the user input matches one of the lines in the "dictionary.txt" file */
  public static boolean checkInDictionary(String proposed) {
    try {

      Scanner scanner = new Scanner(new File("dictionary.txt"));

      while (scanner.hasNextLine()){
        if(scanner.nextLine().equals(proposed)){
          return true;
        }
      }

    } catch (Exception e) {
      System.out.println("Input/File not found!");
    }
    return false;  
  }

  /* Return the number of answers that are in the "answers.txt" file.
     This method was implemented in case the file has modifications */
  public static int totalAnswers(){
    try{

      Scanner scanner = new Scanner(new File("answers.txt"));

      int answers = 0;

      while (scanner.hasNextLine()){
        scanner.nextLine();
        answers++;
      }
      return answers;

    } catch (Exception e) {
      System.out.println("Input/File not found!");
    }
    return -1;
  }
}
