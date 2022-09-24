import java.util.Random;

/**
 * Extends the game class. Represents a game of rock, paper, scissors played against a virtual machine. Through
 * various functions the game is playec.
 */
public class RPS extends Game{
    private Random rng;
    private int requiredWins;
    private int wins;
    private int maxLosses;
    private int losses;
    private int AIMove;

    /**
     * Default constructor.
     */
    public RPS() {
        rng = null;
        requiredWins = -1;
        wins = -1;
        maxLosses = -1;
        losses = -1;
    }
    /**
     * Constructor. Takes a Random object and two ints representing the required wins in order to win the tournament
     * and the maximum amount of losses before the AI wins.
     * @param rng
     * @param requiredWins
     * @param maxLosses
     */
    public RPS(Random rng, int requiredWins, int maxLosses) {
        this.rng = rng;
        this.requiredWins = requiredWins;
        this.maxLosses = maxLosses;
    }

    /**
     * Takes no parameters. Sets the wins and losses to 0 and returns the game parameters as a string.
     * @return
     */
    protected String prepToPlay() {
        wins = 0;
        losses = 0;
        return "Enter rock, paper, or scissors. Beat me " + requiredWins + " before I win " + maxLosses + " times!";
    }

    /**
     * Checks to see if the game is over by comparing losses and maxLosses and wins and requiredWins. Returns true
     * if maxLosses is less than or tqual to losses and if wins is greater than or equal to required wins. Otherwise,
     * returns false.
     * @return
     */
    protected boolean isOver() {
        if(maxLosses <= losses) {
            return true;
        }
        else if(wins >= requiredWins){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Takes a String representing a move. if the string is equal to "scissors", "rock", or "paper", true is returned.
     * Otherwise, false is returned.
     * @param move
     * @return
     */
    protected boolean isValid(String move) {
        if (move.equals("scissors") || move.equals("rock") || move.equals("paper")) {
            return true;
        }
        return false;
    }

    /**
     * Takes a String representing a player's move as a parameter. A random integer within [0,2] is assigned to the
     * AIMove variable to represent the random move form the machine. From there, if else statements strung together
     * return the result of that match using basic RPS rules.
     * @param move
     * @return
     */
    protected String processMove(String move){
        AIMove = rng.nextInt(3);
        if(AIMove == 0 && move.equals("rock")) {
            return "We tie";
        }
        else if(AIMove == 1 && move.equals("paper")){
            return "We tie";
        }
        else if(AIMove == 2 && move.equals("scissors")){
            return "We tie";
        }
        else if(AIMove == 0 && move.equals("paper")){
            wins++;
            return "You win";
        }
        else if(AIMove == 0 && move.equals("scissors")){
            losses++;
            return "You lose";
        }
        else if(AIMove == 1 && move.equals("rock")) {
            losses++;
            return "You lose";
        }
        else if(AIMove == 1 && move.equals("scissors")){
            wins++;
            return "You win";
        }
        else if(AIMove == 2 && move.equals("rock")){
            wins++;
            return "You win";
        }
        else if(AIMove == 2 && move.equals("paper")){
            losses++;
            return "You lose";
        }
        return "We tie";
    }

    /**
     * Returns whether or not the user won or lost through comparisons of maxLosses and losses and requiredWins and
     * wins
     * @return
     */
    protected String finalMessage(){
        if(maxLosses <= losses){
            return "You lose the set";
        }
        else if(requiredWins <= wins) {
            return "You win the set";
        }
        else {
            return "I should never get here";
        }
    }

    /**
     * Takes no parameters and returns the name "Rock Paper Scissors".
     * @return
     */
    public String getName(){
        return "Rock Paper Scissors";
    }
}
