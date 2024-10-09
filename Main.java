/*
  Author: Saul Burns
  The main class is going to execute all the created classes to perform 
  and display one wordle game in the console.
  Last Date Modified: 05/01/2024
*/

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    /* start the game and prompt the user to type a number to create the secret word to be guessed. */
    public static WordleGame startGame(Scanner scanner)  {
        System.out.println("WELCOME TO WORDLE GAME!");
        System.out.print("Enter a number between 0 and 2314 to choose a word to guess: ");
        WordleGame game = new WordleGame(validNumberPuzzle(scanner));
        return game;
    }

    /* prompt the user to type a guess word and if it is in the dictionary, is valid.
       Then this method, color the characters depending if they are in the answer and print
       the board. */
    public static void playGame(Scanner scanner, WordleGame game)  {
        System.out.println("GUESS THE WORD!");
        
        while(!game.isGameOver()){
            System.out.print("Enter your guess: ");
            String guessWord = scanner.nextLine().toLowerCase();
           
            while(!WordBank.checkInDictionary(guessWord)){
                System.out.print("Enter a word that exists: ");
                guessWord = scanner.nextLine().toLowerCase();
            }

            game.guess(guessWord);
            
            System.out.print(game.toString());
        }
    }

    /* this method determines if the user won or lost */
    public static void reportGameOutcome(WordleGame game) {
        if(game.isGameWin()){
            System.out.println("You won!");
        } else{
            System.out.println("You lost! The answer was " + "'" + game.getAnswer() + "'");
        }
    }


    /* All methods in this class are implemented here to run the program */ 
    public static void main(String[] args) {
        /* Only use this Scanner for user input, do not create new ones via new Scanner(System.in).*/ 
        Scanner scanner = new Scanner(System.in);
        WordleGame game = startGame(scanner);
        playGame(scanner, game);
        reportGameOutcome(game);
    }

    /* handle input errors when asking for a number at the beginning of the game */
    public static int validNumberPuzzle(Scanner scanner){
        boolean inRange = false;
        String puzzleNumber = ""; 
        int intPuzzleNumber = -1;

        while(!inRange){
            boolean onlyDigits = false;
        
            while(!onlyDigits){
                puzzleNumber = scanner.nextLine();

                for(int i = 0; i < puzzleNumber.length(); i++){
                    if(puzzleNumber.charAt(i) >= '0' && puzzleNumber.charAt(i) <= '9'){
                        onlyDigits = true;
                    } else{
                        onlyDigits = false;
                        System.out.print("Unknown response! Enter a number between 0 and 2314: ");
                        break;
                    }
                }
            }

            intPuzzleNumber = Integer.parseInt(puzzleNumber);

            if(intPuzzleNumber >= 0 && intPuzzleNumber <= WordBank.totalAnswers()-1){
                inRange = true;
            } else{
                inRange = false;
                System.out.print("Out of range! Enter a number between 0 and 2314: ");
            }
        }
        return intPuzzleNumber;
    }

}
