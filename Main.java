package tictactoe;
/* So this is tic tac toe, a fun board game, except this time its made in java! It utilizes multiple forms of almost everything and a new type, the module
switch!

made by jai & sundi

btw I didnt comment explicitly every single line because it would be a little redundant to say the same thing over and over and over and over and over and o-

*/

//imports the neccesary modules
import java.util.Scanner;
import java.util.Random;
//New class, otherwise known as functions
public class Main { // initialization
	//Creates a new variable of a string...or text
	static String playerSymb;
	static String[] squares = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	static boolean gameOver;
	static String compSymb;
	static int pmove;
	static String winner;
	//A method, or a smaller scale function
	public static void printBoard(String[] curr) { // method for printing board
		//Print to the terminal and then paste a new line
		System.out.println("\n " + curr[1] + " | " + curr[2] + " | " + curr[3] + 
				"\n---+---+---\n " + curr[4] + " | " + curr[5] + " | " + 
				curr[6] + "\n---+---+---\n " + curr[7] + " | " + curr[8] + 
				" | " + curr[9] + "\n");
	}
	public static String getInput(String prompt) { // get latest player input
		//print to the terminal without making a new line
		System.out.print(prompt);
		Scanner input = new Scanner(System.in);
		String output = input.nextLine();
		//defines the method as the result to be used in other functions
		return output;
	}
	public static void init() { // initialization
		final String[] initSquares = {"0", "1", "2", "3", "4", "5", "6", "7", 
				"8", "9"};
		squares = initSquares;
		//A new type of variable, a boolean, which means it is either true or false
		gameOver = false;
		// A while loop which means while a condition: run inside
		while (true) {
			String initSymb = getInput("Player symbol? (X/O)");
			if (initSymb.toLowerCase().equals("x")) {
				playerSymb = "X";
				compSymb = "O";
				//stops the loop
				break;
			} else if (initSymb.toLowerCase().equals("o")) {
				// just a couple more variables
				playerSymb = "O";
				compSymb = "X";
				break;
			//Otherwise print a false reaction aka "invalid input"
			} else {
				System.out.println("Invalid Input");
			};
		}
		System.out.println("Choose number in square to take");
	}
	//This is a new type of method, a bool method that will only return true or false, otherwise it won't work.
	public static boolean isStaleMate(String[] input, String[] input2) {
		int count = 0;
		//defines a new boolean variable
		boolean staleMate;
		//the rumored for loop, which means for as many times as stated, run this
		for (int i = 0; i < input.length; i++) {
			if (!input[i].equals(input2[i])) {
				count += 1;
			}
		}
		//your typical if else statement
		if (count == input.length) {
			staleMate = true;
		} else {
			staleMate = false;
		}
		//makes the method set to staleMate aka either true or false
		return staleMate;
	}
	public static String checkWin(String[] input) { // checking for win
		String winchar = "default";
		for (int i = 0; i < 8; i++) {
			String line = null;
			//Basically checks the condition and then stops depending on said condition
			switch (i) {
			//Check case #1
			case 0:
				line = input[1] + input[2] + input[3];
				break;
			//check case #2
			case 1:
				line = input[4] + input[5] + input[6];
				break;
			//can you guess which case gets checked?
			case 2:
				line = input[7] + input[8] + input[9];
				break;
			//etc
			case 3:
				line = input[1] + input[4] + input[7];
				break;
			//etc
			case 4:
				line = input[2] + input[5] + input[8];
				break;
			//etc
			case 5:
				line = input[3] + input[6] + input[9];
				break;
			//etc
			case 6:
				line = input[1] + input[5] + input[9];
				break;
			//etc
			case 7:
				line = input[3] + input[5] + input[7];
				break;
			}
			//another one of your typical if else statements to check if line is equal to a win condition
			if (line.equals("XXX")) {
				winchar = "X";
			} else if (line.equals("OOO")) {
				winchar = "O";
			}
		}
		//if winchar is equal to the player it will say the player won
		if (winchar.equals(playerSymb)) {
			winner = "Player";
		//if winchar is equal to the computer it will print the computer
		} else if (winchar.equals(compSymb)) {
			winner = "Computer";
		//otherwise print whatever else is in winchar
		} else {
			winner = winchar;
		}
		//return the results of the above if else statement
		return winner;
	}
	public static void main(String[] args) { // main logic
		final String[] initSquares = {"0", "1", "2", "3", "4", "5", "6", "7", 
				"8", "9"};
		//generates a random number
		Random random = new Random();
		outerloop:
		//another while function
		while (true) {
			init();
			printBoard(squares);
			mainloop:
			while (true) {
				if (isStaleMate(squares, initSquares)) {
					break mainloop;
				}
				//attempt to run below
				try {
					playerloop:
					while (true) {
						String rawMove = getInput("Square to take: ");
						int intMove = Integer.parseInt(rawMove);
						if (intMove <= 9 && intMove > 0 && 
								squares[intMove].equals(rawMove)) {
							pmove = intMove;
							break playerloop;
						} else if (!squares[intMove].equals(rawMove)) {
							System.out.println("Already taken");
						} else {
							System.out.println("Invalid Input");
						}
					}
				// if unable to do this
				} catch (Exception e) {
					System.out.println("Invalid Input");
				}
				if (!checkWin(squares).equals("default")) {
					System.out.println(checkWin(squares) + " wins!");
					break mainloop;
				}
				squares[pmove] = playerSymb;
				squares[0] = "none";
				computerloop:
				//another while loop that runs based on a bool
				while (true) {
					int cIntMove = random.nextInt(9) + 1;
					if (isStaleMate(squares, initSquares)) {
						break mainloop;
					}
					if (squares[cIntMove].equals(Integer.toString(cIntMove))) {
						squares[cIntMove] = compSymb;
						break computerloop;
					}
				}
				if (!checkWin(squares).equals("default")) {
					System.out.println(checkWin(squares) + " wins!");
					break mainloop;
				}
				printBoard(squares);
			}
			printBoard(squares);
			if (isStaleMate(squares,initSquares)) {
				System.out.println("Stalemate!");
			}
			String playAgainRaw = getInput("Play again? (Y/N) ").toLowerCase();
			if (playAgainRaw.equals("n") || 
					playAgainRaw.equals("no")) {
				break outerloop;
			} else if (!(playAgainRaw.equals("y") ||
					playAgainRaw.equals("yes"))) {
				System.out.println("Invalid Input");
			}
		}
	}
}
