//Aaron Weckerly
//CPSC 374
//Project 2 "Write like Lewis Carroll"
//stringGen class - Generates string based off seed from text


import java.util.ArrayList;
import java.lang.Character;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;


public class stringGen {
	
	int MAX = 45; //creates an array large enough to store words from 1 - 45 letters long. the shortest and longest in the english dictionary
	
	private String book;
	private String seedWord, genText;
	private ArrayList<String> words[] = (ArrayList<String>[])new ArrayList[MAX];
	{
		for (int i = 0; i < MAX; i++)
			words[i] = new ArrayList<String>();
	}
	
	private ArrayList<Character> letters = new ArrayList<Character>();

	
	public stringGen(int seedLength, int genTextLength) throws FileNotFoundException{
		loadBook();
		seedWord = findSeed(seedLength);
		genText = findText(seedWord, genTextLength);
		System.out.println(" ");
		System.out.println("The seed word is "+seedWord);
		System.out.println("The generated text is "+genText);
	}
	
	//public function to get seed word
	public String getSeed(){
		return seedWord;
	}
	
	//public function to get generated text
	public String getGenText(){
		return genText;
	}

/*************************load book*************************/
	//private helper function to load book into string
	private void loadBook() throws FileNotFoundException{
		Scanner fileIn = new Scanner(new File("src/alice.txt"));
		while(fileIn.hasNext()){
			book = book+fileIn.next();
			book = book+" ";
				
		}//while
	}//loadbook
	
/******************************find seed**********************/
	//helper function to be called by stringGen to get seed
	private String findSeed(int seedLength){
		parse();
		//generate number for random location of word at specified length
		int index = seedLength-1;
		while(words[index].isEmpty() && index>0)
		{
			index--;
		}
		Random randNum = new Random(); 
		int number = (words[index].size())-1;
		if(number<1)
			number=1;
		int randNumber = randNum.nextInt(number);
		String test = words[index].get(randNumber); //returns seed from location randomly generated between 0 and size
		return test;
	}//find seed
	
/**************************findText***********************/
	//helper function to be called by stringGen to get text string
	private String findText(String seed, int genTextLength){
		
		String Seedstr = seed;
		String Finalstr ="";
		System.out.println("Generating char number: ");
		for(int i = 0; i < genTextLength; i++){
		int index = 0;
		//int booklength = book.length();
		System.out.print((i+1)+"..");
		//loop searches through book to find chars following seed
		int lastIndex = book.lastIndexOf(Seedstr)+Seedstr.length()+1;
		while(index < lastIndex){
			int location = book.indexOf(Seedstr, index);
			char ch;
			if(location+(Seedstr.length()) < book.length())
				ch = book.charAt(location+(Seedstr.length()));
			else //if looking for char at end of book, make is a space
				ch = '\b';
			letters.add(ch);
			index=index+1;
		
		}//while
		
		Random randNum = new Random();	//randomly generates a number to choose a letter
		
		char a = (letters.get(randNum.nextInt(letters.size())));
		Seedstr = Seedstr+a;
		Finalstr = Finalstr+a;
		Seedstr = Seedstr.substring(1);
		System.out.println(Seedstr);
		
		letters.clear(); //clears list to use for next 'seed'
		
		} //for
		return Finalstr;
	}//findText
	
	
/******************parse**************************************************/
	//helper function to be called by getSeed
	private void parse() {
		String delims = "[ -.,;:?!\\n\\r]+";
		String word[] = book.split(delims);
		for(int i = 1; i < word.length; i++){
			words[(word[i].length())-1].add(word[i]);
		}
	}//parse
}//stringGen class