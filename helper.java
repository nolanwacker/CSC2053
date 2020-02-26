import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

/**
 * This class contains methods
 */
public class helper {
	/**
	 * This method puts words and the frequency of the word from the text into the hash map
	 */
	public static HashMap<String, Integer> hashWords(String word, HashMap<String, Integer> map){
		if(map.containsKey(word)){
			map.put(word,map.get(word)+1);
		}
		else{
			map.put(word,1);
		}
		return map;
	}
	/**
	 * This method puts characters and the frequency of the character from the text into the hash map
	 */
	public static HashMap<String, Integer> hashChars(String [] c, HashMap<String, Integer> map){
		for(int i=0; i<c.length; i++){
			
			if(c[i] != null){
				if(map.containsKey(c[i])){
					map.put(c[i], map.get(c[i])+1);
				}
				else{
					map.put(c[i],1);
				}
			}
		}
		return map;
	}
	/**
	 * This method checks if the string is all capital letters
	 */
	public static boolean isStringUpperCase(String s){
		char[] charArray = s.toCharArray();
        for(int i=0; i < charArray.length; i++){
            if( !Character.isUpperCase( charArray[i] ))
                return false;
        }
        
        return true;
	}
	/**
	 * This method removes any strings that are not capital letters
	 */
	public static HashMap<String, Integer> hashCaptial(HashMap<String, Integer> map){
		ArrayList<String> removeList = new ArrayList<String>();
		for (Entry<String, Integer> entry : map.entrySet()) {
			String key = entry.getKey();
			if(isStringUpperCase(key) == false){
				removeList.add(key);
			}
		}
		for(String x: removeList){
			map.remove(x);
		}
		return map;
	}
	/**
	 * This method converts the text into all lower case and easier to read version
	 */
	public static String regex(String line){
		Scanner scan = new Scanner(line);
		//String word = null;
		while(scan.hasNext()) {
			scan.useDelimiter("[^a-zA-Z']");
			String word = scan.next();
			word=word.toLowerCase();
			word = word.replaceAll("^'+", "");
			word = word.replaceAll("'+$", "");
			return word;
		}
		return null;
	}
	
	/**
	 * This finds the highest value and then removes it
	 * Calls another method to find the highest value
	 * Prints the value before removing it
	 * Runs through ten times to create a top ten
	 */
	public static void topTen(HashMap<String, Integer> map){
		Map.Entry<String, Integer> maxEntry = null;
		map.remove("");
		map.remove(null);
		System.out.println(map);
		for(int i=1; i<=10; i++){
			maxEntry = findMax(map);
			System.out.println(i +" Highest value: " + maxEntry);
			String key = maxEntry.getKey();
			map.remove(key);
		}
	}
	/**
	 * This method finds the highest value
	 * It returns the entry of the hash map to the topTen method
	 */
	public static Map.Entry<String,Integer> findMax(HashMap<String, Integer> map){
		Map.Entry<String, Integer> maxEntry = null;
		for (Map.Entry<String, Integer> entry : map.entrySet())
		{
		    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
		    {
		        maxEntry = entry;
		    }
		}
		return maxEntry;
	}
	/**
	 * This method splits a string into an array of the characters
	 */
	public static String[] splitChar(String testing){
		if(testing != null){
			String [] arrofLetters = testing.split("(?!^)");
			return arrofLetters;
		}
		else{
			return null;
		}
		
	}
	
	/**
	 * This method removes any words from the stop list from the map
	 */
	public static HashMap<String, Integer> removeStopList(String line, HashMap<String, Integer> map){
		if(map.containsKey(line)){
			map.remove(line);
		}
		return map;
	}
}
