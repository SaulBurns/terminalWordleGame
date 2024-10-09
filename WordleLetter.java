/*
	Author: Saul Burns
	The WordleLetter class creates instances as a single letter placed 
	in the wordle game board.
  Last Date Modified: 04/30/2024 
*/
	
public class WordleLetter {
	
	private char letter;
	private String color;

	/* Constructor that takes in a letter and sets it */
	public WordleLetter(char letterIn){
		this.letter = letterIn;
	}
	
  /* Setter that gives an object color attribute a value */
	public void setColor(String colorIn){
		this.color = colorIn;
	}

	public String toString() {

		/*	Determine the special characters to add at the beginning of the String
			to change the text color to the right color. */
		String colorCode;
		if(color.equals("green")) {
			colorCode = "\u001B[32m";
		} else if(color.equals("yellow")) {
			colorCode = "\u001B[33m";
		} else {
			colorCode = "\u001B[31m";
		}
	
		/*These are the special character to add 
			to the end of the String 
			to signify the end of the color change.*/
		String resetCode = "\u001B[0m";

		/* Surround the letter with space characters and with 
			the above color changing special characters. */ 
		return String.format(
			"%s %s %s",
			colorCode, letter, resetCode);
	}

	/* To check if the current object has a no empty color attribute */
	public boolean isColorSet(){
		if(color == null){
			return false;
		}
		return true;
	}

	/* To check if the current object has a "green" color attribute */
	public boolean isGreen(){
		if(color.equals("green")){
			return true;
		}
		return false;
	}
}
