/*
  Author: Saul Burns
  The WordleGame class represents a single game. It will 
  show the user's current guesses and the color of each character depending on 
  whether or not it is in the answer until reaching the responses limit, which is 6.
  If the user can guess the word before all the correct answers,
  the game ends with a victory, but if they use all their attempts 
  without guessing the word, they lose.
  Last Date Modified: 04/30/2024
*/

import java.io.FileNotFoundException;

public class WordleGame {
  /* allGuesses represents the wordle game */
  private WordleLetter[][] allGuesses = new WordleLetter[6][5];

  /* represents a 2D array of booleans where if the characters of the guess words matches with the 
     characters of the answer return a true value this attribute is used to handle 
     the duplicate letters words */
  private boolean[][] charFoundInGuess = new boolean[6][5]; 
  
  /* the secret answer to win the game */
  private String answer;

  /* represents the current guesses of the user, initial value=0 */
  private int numberGuessesSoFar;

  /* represents the current proposed word of the user*/
  private String currentGuess;


  /* this constructor object takes a number from the "answers.txt" and set the answer attribute */
  public WordleGame(int puzzleNumberIn){ /*To get a word of the bank*/
    this.answer = WordBank.getAnswerForPuzzleNumber(puzzleNumberIn);
  }

  /* to return the value of the answer attribute */
  public String getAnswer(){
    return this.answer;
  }

  /* to return the number of valid guesses the user did */
  public int getNumberGuessesSoFar(){
    return this.numberGuessesSoFar;
  }

  /* to return one row of the 2D array allGuesses that already have objects with attributes,
     to later be used in the toString() method to represent the current board game */
  public WordleLetter[] getGuess(int guessNumber){
    WordleLetter[] guessWord = new WordleLetter[5];
    for(int i = 0; i < 5; i++){
      guessWord[i] = this.allGuesses[guessNumber][i];
    }
    return guessWord;
  }

  public String toString() {
    /* result will be used to build the full answer String */ 
    String result = ""; 
       /* for each word guessed so far */ 
    for (int i = 0; i < getNumberGuessesSoFar(); i++) {
         /* get each letter of each word */
      for (int j = 0; j < 5; j++) {
           /* concatenate it to the result */ 
           /* WordleLetter's toString() is automatically invoked here. */
        result += getGuess(i)[j];
        //array[location]
      }
         /* new line separator between each word */ 
      result += "\n";
    }
    return result;
  }
  
  /* guess() method has the feature of setting the color of the WordleLetter objects depending on the position and
    existence of the character in the word */
  public void guess(String guessWord){
  
    this.currentGuess = guessWord;
    int line = this.numberGuessesSoFar;

    /* five new WordleLetter objects in the allGuesses array will be created with the characters of the user guess */
    for(int column = 0; column < 5; column++){   
      allGuesses[line][column] = new WordleLetter(guessWord.charAt(column));
    }
    
    /* if there are characters in the same position in the answer and guess word, 
        this characters will be colored in green */
    for(int column = 0; column < 5; column++){
      if(this.answer.charAt(column) == guessWord.charAt(column)){
      allGuesses[line][column].setColor("green");

      /* the indices in allGuesses array where the letter are colored to green, 
        for the charFoundInGuess 2D Array the same indices will be true */
      charFoundInGuess[line][column] = true;
      }
    }

    /* now that we have all our green objects, let's find if a character is present in the answer but not in the right
    position */

    /* traverse the array again to find the objects that are still not colored */
    for(int column = 0; column < 5; column++){
      if(allGuesses[line][column].isColorSet() == false){
        int index = -1;

        /* traverse the answer again to check if it presents the character of the guess word, and we 
        check if the position is already colored green by checking the charFoundInGuess 2D array to handle duplicates.
        We save the index of that character if the char is present and the position of that char is false */
        for(int column2ndTime = 0; column2ndTime < 5; column2ndTime++){
          if(!charFoundInGuess[line][column2ndTime] && (this.answer.charAt(column2ndTime) == guessWord.charAt(column))){
            index = column2ndTime;
            break;
          }
        }

        /* if index is not the default(-1) meaning that we found a character present in the array but no in the correct
        position, it will be colored yellow */
        if(index != -1){
          allGuesses[line][column].setColor("yellow");
          charFoundInGuess[line][index] = true;
        } else{ /* if that character is not in the answer then turn it to red */
          allGuesses[line][column].setColor("red");
        }
      }
    }

    /* add one guess since the use one attempt */
    this.numberGuessesSoFar++;
  }

  /* Check if the user input equals to the secret word or if the number of current guesses 
     equals the maximum attempts to return true, meaning the game ends. */
  public boolean isGameOver(){
    if(this.answer.equals(this.currentGuess)){
      return true;
    } else if(this.numberGuessesSoFar == 6){
      return true;
    } else{
      return false;
    }
  }

  /* Check if the user input equals to the secret word to return true, meaning that the user wins. */
  public boolean isGameWin(){
    if(this.answer.equals(this.currentGuess)){
      return true;
    } 
    return false;
  }
}
