package exam.exam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.String;


public class TextAnalytics 
{
    String text;
    List<String> words;
    List<Character> Chars;
    HashMap<String, Integer> uniqueWordCounter = new HashMap<>();
    HashMap<Character, Integer> uniqueCharCounter = new HashMap<>();
    HashMap<Character, Integer> specialCharCounter = new HashMap<>();

    public TextAnalytics(String text) {
        this.text = text;
        this.splitToWords();
        this.turnWordsToLowercase();
        this.countUniqueWords();
        this.countUniqueCharacters();
        this.countSpecialChars();    
    }

    public void splitToWords() {
        this.words = Arrays.asList(this.text.split(" "));
    }

    // Uses the Streams introduced in Java 8 to proccess a list of strings into
    // lowercase and saves them as a List using collect and the Collector class' static toList function.
    public void turnWordsToLowercase() {
        this.words = this.words.stream().map(String::toLowerCase).collect(Collectors.toList());
    }

    public void countUniqueWords() {
        for (String word : this.words) {
            if (this.uniqueWordCounter.containsKey(word)) {
                int updatedCounter = this.uniqueWordCounter.get(word) + 1;
                this.uniqueWordCounter.put(word, updatedCounter);
            } else {
                this.uniqueWordCounter.put(word, 1);
            }
        }
        System.out.println("words: "+ this.uniqueWordCounter);
    }
    
    public void countSpecialChars() {
    	// otsitavad tähed
    	char[] charArray = new char[] {'ä', 'ö', 'ü', 'õ'};
    	// liigun läbi kõikide sõnade
    	for (String word : this.words) {
    		// sõna esimene täht -> string
    		char c = word.charAt(0);
    		// kui sõna esimene täht on eelnevas täpitähtede arrays, siis suurendan counterit ja kirjutan uue counteri hashmappi
    		if (new String(charArray).contains(Character.toString(c))) {
    			if (this.specialCharCounter.containsKey(c)) {
        			int updatedCounter = this.specialCharCounter.get(c) + 1;
                    this.specialCharCounter.put(c, updatedCounter);	
				} else {
	    			this.specialCharCounter.put(c, 1);
	    		}
    		}
    	}
    	System.out.println("special chars: " + this.specialCharCounter);
    }
    
    public void countUniqueCharacters(){
        for(char character : text.toCharArray()){
          if( this.uniqueCharCounter.containsKey(character)){    
              int updatedCounter = this.uniqueCharCounter.get(character) + 1;
              this.uniqueCharCounter.put(character, updatedCounter);
          } else {
              this.uniqueCharCounter.put(character, 1);
              }
          }
        System.out.println("unique chars: "+ this.uniqueCharCounter);
    }

    // Simple function to return the data from this class
    // as a HashMap so that the RestController could return this as valid JSON to the frontend.
    public HashMap<String, String> toJSON(){
        HashMap<String, String> json = new HashMap<>();
        json.put("uniqueWordCount", Integer.toString(this.uniqueWordCounter.size()));
        json.put("wordOccurances", this.uniqueWordCounter.toString());
        json.put("characterOccurances", this.uniqueCharCounter.toString());
        json.put("specialCharOccurances", this.specialCharCounter.toString());
        return json;

    }

    // Just some code to check if the class is functional.
    // You will never need to touch change this or run this.... EVER.
    // Hands off, shoo!
    public static void main(String[] args) {
    	TextAnalytics analytics = new TextAnalytics("Lorem ipsum ipsum adfinitum öööööö");
    	System.out.println(analytics.uniqueWordCounter.toString());

    }
}
