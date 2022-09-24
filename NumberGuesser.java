import java.util.Random;
/**
 * This class extends from the Game class and represents a game of NumberGuesser. The abstract functions of the Game class
 * are defined and implemented in order to support the nuances of the game.
 */
public class NumberGuesser extends Game {
    Random rng;
    int maxNumber;
    int maxGuesses;
    int guesses;
    int guess = -1;
    int number;

    /**
     * Default constructor.
     */
    public NumberGuesser(){
        rng = null;
        maxNumber = -1;
        maxGuesses = -1;
    }
    /**
     * Constructor. Takes a Random object and integers representing the largest number wanted and the maximum amount
     * of guesses for the user.
     * @param rng
     * @param maxNumber
     * @param maxGuesses
     */
    public NumberGuesser(Random rng, int maxNumber, int maxGuesses) {
        this.rng = rng;
        this.maxNumber = maxNumber;
        this.maxGuesses = maxGuesses;
    }

    /**
     * Takes no parameters. Using the Random object, a random integer is found and assigned to be the guessing number.
     * From there, a string giving the game details is returned.
     * @return
     */
    protected String prepToPlay() {
        number = rng.nextInt(maxNumber)+1;
        guesses = 0;
        maxGuesses = maxGuesses;
        guess = -1;
        return "I've picked a number 1 to " + maxNumber + ". You get " + maxGuesses + " guesses to guess it";
    }

    /**
     * Takes no parameters. If the amount of guesses done by the user is greater than or equal to the maximum amount
     * of guesses from the game parameters, true is returned. Additionally, if the player's guess is equal to the
     * number, true is returned. Otherwise, false.
     * @return
     */
     protected boolean isOver() {
        if(guesses >= maxGuesses){
            return true;
        }
        if(guess == number){
            return true;
        }
        return false;
     }

    /**
     * Taking a string representing a player's move as a parameters, false is returned if the string is not equal to
     * a single digit. otherwise, true is returned.
     * @param move
     * @return
     */
     protected boolean isValid(String move){
        for(int i = 0; i<move.length(); i++){
            if(move.charAt(i) != '1' && move.charAt(i) != '2' && move.charAt(i) != '3' && move.charAt(i) != '4'
               && move.charAt(i) != '5' && move.charAt(i) != '6' && move.charAt(i) != '7' && move.charAt(i) != '8'
               && move.charAt(i) != '9' && move.charAt(i) != '0') {
                return false;
            }
        }
        return true;
     }

    /**
     * Taking a string representing a player's move as a parameter, the Integer.parseInt is used on move to assign
     * guess with the integer value of the players guess. Guesses is increased and the guess is compared to the number.
     * From there, an appropriate response is given to indicate when in relation to the number the player guessed (low/
     * high/right on). This hint is what is returned as string.
     * @param move
     * @return
     */
     protected String processMove(String move) {
        guess = Integer.parseInt(move);
        guesses++;
        if(guess == number) {
            return "That's it!";
        }
        else if(guess < number) {
            return "Too Low";
        }
        else {
            return "Too High";
        }
     }

    /**
     * Returns what the number was to the player and takes no parameters.
     * @return
     */
     protected String finalMessage(){
        return "The number was: " + number;
     }

    /**
     * Returns the name of the game as a string: NumberGuesser. No parameters are taken.
     * @return
     */
     public String getName() {
         return "NumberGuesser";
     }
}
