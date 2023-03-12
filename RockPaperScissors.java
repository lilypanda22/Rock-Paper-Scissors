import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RockPaperScissors {
    //these are constants to help keep track of which value is which
    public static final int ROCK = 1;
    public static final int PAPER = 2;
    public static final int SCISSORS = 3;

    public static void main(String[] args) throws Exception {
        Scanner keyboard = new Scanner(System.in);

        //The following will get the user's name and the number of rounds they want to play
        String name = getName(keyboard);
        int rounds = getRounds(keyboard, name);

        //this method plays the whole game
        playRounds(rounds, name);

        keyboard.close();
    }

    //returns user's name
    public static String getName(Scanner keyboard){
        System.out.println("Welcome to ROCK PAPER SCISSORS. I, Computer, will be your opponent.");
        System.out.print("Please type in your name and press return: ");
        return keyboard.nextLine();
    }

    //returns number of rounds
    public static int getRounds(Scanner keyboard, String name){
        System.out.println("\nWelcome " + name + ".\n");
        System.out.println("All right " + name + ". How many rounds would you like to play?");
        System.out.print("Enter the number of rounds you want to play and press return: ");
        return keyboard.nextInt();
    }

    //returns the player's game choice
    public static int getPlayerInput(int i, String name){
        System.out.println("\nRound " + i + ".");
        System.out.println("\n" + name + ", please enter your choice for this round.");
        System.out.print("1 for ROCK, 2 for PAPER, and 3 for SCISSORS: ");
        return checkInput();
    }

    public static int checkInput(){
        int ans = 0;
        boolean isGood = false;
        do {
            try {
                ans = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
                if (ans < 1 || ans > 3) {
                    System.out.println("Please type an integer between 1 and 3!");
                } else {
                    isGood = true;
                }
            }
            catch (Exception ex) {
                System.out.println("Please type an integer between 1 and 3!");
            }
        } while (!isGood);

        return ans;
    }

    //this method will help with converting the number into the game choices
    public static String conversion(int choice){
        if (choice == 1){
            return "ROCK";
        }
        else if (choice == 2){
            return "PAPER";
        }
        else return "SCISSORS";
    }

    //this method will be how the whole game gets played
    public static void playRounds(int rounds, String name){
        //the score for wins, losses, and draws
        int w = 0;
        int l = 0;
        int d = 0;

        //This for loop will play the game until the end of the amount of rounds
        for (int i = 1; i <= rounds; i++){
            //each round will get the players input and print results
            int playerInput = getPlayerInput(i, name);

            //this is required to get the computer choices from 1-3
            int pcInput = (int) (Math.random() * 3 + 1);

            //here I used conversion as a helper method to convert the int into a choice
            System.out.println("Computer picked " + conversion(pcInput) +
                    ", " + name + " picked " + conversion(playerInput) + ".");

            //this will return the score from each round
            String results = getResults(playerInput, pcInput);

            //this takes the string returned from getResults and will
            //appropriately add to the score
            if (results.equals("w")){
                System.out.print("You win.\n");
                w++;
            }
            else if (results.equals("l")){
                System.out.print("I win.\n");
                l++;
            }
            else{
                System.out.println("\nWe picked the same thing! This round is a draw.");
                d++;
            }
        }
        //this method will only happen at the end of the game (when all the rounds are played)
        results(rounds, name, w, l, d);
    }

    //this method will determine the outcome of each round and ++ if it is a win, loss, or draw
    //(which were declared as global variables)
    public static String getResults(int playerInput, int pcInput) {
        //condition for paper wins
        if (((playerInput == PAPER) && (pcInput == ROCK)) ||
                ((pcInput == PAPER) && (playerInput == ROCK))){
            System.out.print("\nPAPER covers ROCK. ");
            //decides who wins
            if (playerInput == PAPER) return "w";
            else return "l";
        }
        //condition for rock wins
        else if (((playerInput == ROCK) && (pcInput == SCISSORS)) ||
                ((pcInput == ROCK) && (playerInput == SCISSORS))){
            System.out.print("\nROCK breaks SCISSORS. ");
            //decides who wins
            if (playerInput == ROCK) return "w";
            else return "l";
        }
        //condition for scissors win
        else if (((playerInput == SCISSORS) && (pcInput == PAPER)) ||
                ((pcInput == SCISSORS) && (playerInput == PAPER))){
            System.out.print("\nSCISSORS cut PAPER. ");
            //decides who wins
            if (playerInput == SCISSORS) return "w";
            else return "l";
        }
        //condition for draws
        else return "d";
    }

    //this will print the end results of the game
    public static void results(int rounds, String name, int w, int l, int d){
        System.out.println("\n\nNumber of games of ROCK PAPER SCISSORS: " + rounds);
        System.out.println("Number of times Computer won: " + l);
        System.out.println("Number of times " + name + " won: " + w);
        System.out.println("Number of draws: " + d);

        //this determines the overall winner
        if (w > l){
            System.out.println("You, " + name + ", are a master at ROCK, PAPER, SCISSORS.");
        }
        else if (l > w){
            System.out.println("I, Computer, am a master at ROCK, PAPER, SCISSORS.");
        }
        else System.out.println("We are evenly matched.");
    }
}