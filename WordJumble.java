import java.util.Random;
/**
 * This class extends from the Game class and represents a game of WordJumble. The abstract functions of the Game class
 * are defined and implemented in order to support the nuances of the game.
 */
public class WordJumble extends Game {
    private WordsList wl;
    private Random rng;
    private int minWordLen;
    private int maxWordLen;
    private int maxGuesses;
    private String word;
    private int guesses = 0;
    private String guess = "";

    /**
     * Default constructor.
     */
    public WordJumble() {
        wl = null;
        rng = null;
        minWordLen = -1;
        maxWordLen = -1;
        maxGuesses = -1;
        word = "";
        guesses = -1;
        guess = "";
    }
    /**
     * Constructor. Takes a Wordslist, Random object, and integers representing the minimum and maximum wanted word lengths
     * and the max amount of guesses allowed.
     * @param wl
     * @param rng
     * @param minWordLen
     * @param maxWordLen
     * @param maxGuesses
     */
    public WordJumble(WordsList wl, Random rng, int minWordLen, int maxWordLen, int maxGuesses){
        this.wl = wl;
        this.rng = rng;
        this.minWordLen = minWordLen;
        this.maxWordLen = maxWordLen;
        this.maxGuesses = maxGuesses;
    }

    /**
     * Returns the name of the game: "Word jumble"
     * @return
     */
    @Override
    public String getName() {
        return "Word jumble";
    }

    /**
     * Takes no parameters. Sets the game up from the start by getting a new random word within the bounds set by
     * the constructor. Then, creates a copy of the word in array form using a for loops and then uses a method
     * to randomly rearrange the array. This array is the concatenated into a string that is assigned as the jumbled
     * word. This jumbled word is returned along with the game parameters.
     * @return
     */
    @Override
    protected String prepToPlay() {
        word = wl.getWord(minWordLen,maxWordLen);
        guesses = 0;
        guess = "";
        String wordCopy = "";
        char [] wordArray = new char [word.length()];
        for(int i = 0; i<word.length(); i++){
            wordArray[i] = word.charAt(i);
        }
        for(int i = word.length()-1; i>0; i--){
            int j = rng.nextInt(i);
            char exchange = wordArray[j];
            wordArray[j] = wordArray[i];
            wordArray[i] = exchange;
        }
        for(int i = 0; i<word.length(); i++){
            wordCopy += wordArray[i];
        }
        return "The following is a jumbled up word: " + wordCopy + " You get " + maxGuesses + " guesses to guess it";
    }

    /**
     * Takes no parameters. If the most recent guess from the player is equal to the word or the player has used
     * all of their guesses, then true is returned. Otherwise, false is returned.
     * @return
     */
    @Override
    protected boolean isOver() {
        if(guess.equals(word)){
            return true;
        }
        else if(guesses == maxGuesses){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Taking a string representing a player's move, true is always returned.
     * @param move
     * @return
     */
    protected boolean isValid(String move){
        return true;
    }

    /**
     * Taking a string representing a player's move, the guess variable is assigned with this string and the guesses
     * are incremented by one. From there, there is a check to see if the guess is equal to the word and if so,
     * an empty string is returned. otherwise, a string noting that the guess is incorrect is returned.
     * @param move
     * @return
     */
    @Override
    protected String processMove(String move) {
        guess = move;
        guesses++;
        if(guess.equals(word)){
            return "";
        }
        else {
            return "That's not it";
        }
    }

    /**
     * Returns a string noting what the word was. Takes no parameters.
     * @return
     */
    protected String finalMessage() {
        return "The word was " + word;
    }
}
