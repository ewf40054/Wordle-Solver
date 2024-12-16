import java.util.Scanner;
import java.net.URL;
import java.util.ArrayList;
import java.io.File;

public class WordleSolver {

    
    private String current;
    private ArrayList<String> words;
    private Scanner input;
    private boolean win;
    private String lastGuess;
    private String currWord;
    private boolean quit;
    private String safeLetters;
    private Scanner userInput;
    

    public WordleSolver() {
	this.current = null;
	this.words = new ArrayList<>();
	readFile();
	this.win = false;
	this.lastGuess = null;
	this.currWord = null;
	this.quit = false;
	this.safeLetters = "";
	userInput = new Scanner(System.in);
	
    } // WordleSolver

    public void guess() {
	if (current == null){
	    System.out.println("Enter a 5 letter word.");
	    lastGuess = userInput.nextLine();	    
	} else {
	    System.out.println("Possible Words: ");
	    for (String w: words) {
		System.out.println(w);
	    } // for
	   
	    // lastGuess = words.get(0);
	    String diverse = getDiverseWord();
	    lastGuess = diverse;
	    int size = words.size() + 1;
	    System.out.println("Try: " + lastGuess + " out of " + size + " possible words.");
	    
	} // if
	
    } // guess

    public String getDiverseWord() {
	String maxDiverse = "";
	int maxCount = 0;
	int index = 0;
	int maxIndex = 0;
	for (String w: words) {
	    int currCount = 0;
	    boolean lettersEqual = false;
	    for(int i = 0; i < 5; i ++) {
		String curr = w.substring(i, i + 1);
		for(int j = i + 1; j < 5; j ++) {
		    String temp = w.substring(j, j + 1);
		    if(curr.equals(temp)) {
			lettersEqual = true;
		    } // if
		} // for
		if(!lettersEqual) {
		    currCount ++;
		} // if
	    } // for
	    if(currCount > maxCount) {
		maxCount = currCount;
		maxDiverse = w;
		maxIndex = index;
	    } // if
	    index ++;
	} // for
	words.remove(maxIndex);
	return maxDiverse;
    } // getDiverseWord
    
    public boolean enter(String attempt) {
	if (attempt.equals("ggggg")) {
	    return true;
	} // if
	for(int i = 0; i < attempt.length(); i ++) {
	    if (attempt.substring(i, i + 1).equals("g") || attempt.substring(i, i + 1).equals("y")) {
		safeLetters += lastGuess.substring(i, i + 1);
	    } // if
	} // for
	if (attempt.equals("no")) {
	    words.remove(lastGuess);
	    WordleDriver.i --;
	} // if
	current = attempt;
	for (int i = 0; i < attempt.length(); i ++) {
	    String currChar = attempt.substring(i, i + 1);
	    CharSequence spot = lastGuess.subSequence(i, i + 1);
	    if (currChar.equals("g")) {
		for (int j = 0; j < words.size(); j ++) {
		    currWord = words.get(j);
		    if (!(lastGuess.substring(i, i + 1).equals(currWord.substring(i, i + 1)))) {
			words.remove(j);
			j --;
		    } // if
		} // for
		
	    } else if (currChar.equals("y")) {
		for (int k = 0; k < words.size(); k ++) {
		    currWord = words.get(k);
		    if (!(currWord.contains(spot))) {
			words.remove(k);
			k--;
		    } // if
		    if (lastGuess.substring(i, i + 1).equals(currWord.substring(i, i + 1))) {
			words.remove(k);
			k--;
		    } // if
		} // for
		
	    } else if (currChar.equals("-")) {
		for (int z = 0; z < words.size(); z ++) {
		    currWord = words.get(z);
		    
		    if (currWord.contains(spot) && !(safeLetters.contains(spot))) {
		      
			words.remove(z);
			z--;
		    } // if		   
		} // for
	    } // if
	} // for
	return false;
    } // enter

    
    public void readFile() {
	try {
	    input = new Scanner(new File("words_alpha.txt"));
	} catch (Exception e) {
	    System.out.println("Could not find file.");
	} // try
	while(input.hasNext()) {
	    String curr = input.next();
	    if (curr.length() == 5) {
		words.add(curr);
	    } // if
	} // while
	input.close();
    } // readFile

    public void printWords() {
	for (String w: words) {
	    System.out.println(w);
	} // for
    } // printWords
} // WordleSolver
