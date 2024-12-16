import java.util.Scanner;

public class WordleDriver {
    public static int i = 0;
    
    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	WordleSolver game = new WordleSolver();
	String word = null;
	for(i = 0; i < 6; i ++) {
	    game.guess();
	    word = input.nextLine();
	    if(game.enter(word)) {
		System.out.println("You win!");
		i = 6;
	    } // if
	} // for
	

    } // main

} // WordleDriver
