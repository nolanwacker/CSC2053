import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class represents the different hash map objects
 * The objects and data structures are used to call the functions located in the helper class
 */
public class Project1 {
	HashMap<String, Integer> map =  new HashMap<String, Integer>();
	
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		Project1 problemOne = new Project1();
		Project1 problemTwo = new Project1();
		Project1 problemThree = new Project1();
		Project1 problemFour = new Project1();
		
		//Reads in the file
		//Change for file on computer
		File file = new File("tom-sawyer.txt"); 
		Scanner sc = new Scanner(file); 
		
		ArrayList<String> fileLines = new ArrayList<String>();
		while (sc.hasNextLine()){
			fileLines.add(sc.nextLine());			
		}
		
		
		
		//Problem One
		System.out.println("Problem One");
		for(String line: fileLines){
			Scanner scan = new Scanner(line);
			while(scan.hasNext()) {
				scan.useDelimiter("[^a-zA-Z']");
				String word = scan.next();
				word=word.toLowerCase();
				word = word.replaceAll("^'+", "");
				word = word.replaceAll("'+$", "");
				String [] arrofChar = helper.splitChar(word);
				problemOne.map = helper.hashChars(arrofChar, problemOne.map);
			}
		}
		helper.topTen(problemOne.map);
		
		
		
		//Problem Two
		System.out.println("Problem Two");
		for(String line: fileLines) {
			String word = helper.regex(line);
			problemTwo.map = helper.hashWords(word, problemTwo.map);
		}
		helper.topTen(problemTwo.map);
		
		
		
		//Problem Three
		System.out.println("Problem Three");
		for(String line: fileLines) {
			String word = helper.regex(line);
			problemThree.map = helper.hashWords(word, problemThree.map);
		}
		//Have to change the file path for the computer
		File stopList = new File("C:\\Users\\Owner\\Documents\\JavaEclipse\\ProjectOne\\eBooksAndStopList\\stop-list.txt");
		Scanner scan = new Scanner(stopList); 
		
		ArrayList<String> stopListLines = new ArrayList<String>();
		while (scan.hasNextLine()){
			stopListLines.add(scan.nextLine());			
		}
		for(String l:stopListLines){
			problemThree.map = helper.removeStopList(l, problemThree.map);
		}
		helper.topTen(problemThree.map);
		
		
		
		//Problem Four
		//Find the top ten most common capital letters
		System.out.println("Problem Four");
		for(String line: fileLines){
			Scanner s = new Scanner(line);
			while(s.hasNext()) {
				s.useDelimiter("[^a-zA-Z']");
				String word = s.next();
				word = word.replaceAll("^'+", "");
				word = word.replaceAll("'+$", "");
				String [] arrofLetter = helper.splitChar(word);
				problemFour.map = helper.hashChars(arrofLetter, problemFour.map);
				problemFour.map = helper.hashCaptial(problemFour.map);
			}
		}
		helper.topTen(problemFour.map);
	}
}
