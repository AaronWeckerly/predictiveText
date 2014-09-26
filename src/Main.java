//Aaron Weckerly
//CPSC 374
// Project2 "Write like Lewis Carroll"
//Main


import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	
	public static void main(String[] args) throws FileNotFoundException {
	
	Scanner scan = new Scanner(System.in);
	
	int seedLength, genLength; //will be used to store user input

	System.out.println("This program will choose a seed word from a text file and generate characters from that seed");
	System.out.println("The generated characters will be selected at random by finding which ones would likely to follow");
	
	//Prompts user for seed length
	System.out.println("Please enter the length of word you would like to use as a seed");
	System.out.println("Hint:  The longest word in the English dicitonary is 45 characters");
	seedLength = scan.nextInt(); //gets length of seed from user

	//if length seems too large, prompts user again
	if(seedLength > 45){
		System.out.println("You have chosen a seed length of "+seedLength+" This seems a little large");
		System.out.println("An extremely large seed length will effect the efficiency of the program");
		System.out.println("Please enter you seed length again, this will be the length used");
		seedLength = scan.nextInt();
	}//end if large seed
	
	//Prompts user for character generation length
	System.out.println("Please enter the length of characters you would like to generate from the seed");
	genLength = scan.nextInt();
	
	//error checking for extremely large gen length
	
	stringGen alice = new stringGen(seedLength, genLength);
	
	//System.out.println("The seed used was "+alice.getSeed());
	//System.out.println("The generated text is "+alice.getGenText());
	
	}
}
