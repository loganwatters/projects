import java.util.Scanner;

/**
 * This class extends from the Game class and represents a game of Hangman. The abstract functions of the Game class
 * are defined and implemented in order to support the nuances of the game.
 */
public class Hangman extends Game {
    private WordsList wordsToUse;
    private int minWordLen;
    private int maxWordLen;
    private int maxGuesses;
    private int guesses;
    private char[] hint;
    private String word;

    /**
     * Default constructor.
     */
    public Hangman() {
        wordsToUse = null;
        minWordLen = -1;
        maxWordLen = -1;
        maxGuesses = -1;
        guesses = -1;
        hint = null;
        word = "";
    }

    /**
     * Constructor. Takes a WordslList object, and three integers representing the minimum length of the word to guess,
     * the maximum length of the word to guess, and the maximum amount of guesses for the user.
     * @param words
     * @param minWordLen
     * @param maxWordLen
     * @param maxGuesses
     */
    public Hangman(WordsList words, int minWordLen, int maxWordLen, int maxGuesses){
        wordsToUse = words;
        this.minWordLen = minWordLen;
        this.maxWordLen = maxWordLen;
        this.maxGuesses = maxGuesses;
        guesses = 0;
    }

    /**
     * Returns the name of the game: "Hangman"
     * @return
     */
    public String getName () {
        return "Hangman";
    }

    /**
     * Takes no parameters. Uses the WordsList object to get a word within the bounds of the word length interval.
     * Then, the hint char[] is created to be the length of the chosen word. Every index is the assigned with a "_"
     * to represent a blank space. The game rules are then returned in the form of a string.
     * @return
     */
    public String prepToPlay() {
        word = wordsToUse.getWord(minWordLen, maxWordLen);
        hint = new char[word.length()];
        guesses = 0;
        for(int i = 0; i<word.length(); i++) {
            hint[i] = '_';
        }
        return "I've picked a " + word.length() + " letter word. Guess letters you think are in the word. You get " + maxGuesses + " guesses.";
    }

    /**
     * Takes no parameters. Checks game conditions to see if the game is over. Returns true if guesses==maxGuesses and
     * returns false if any of the characters in the hint array do not equal the word at that index.
     * If the function makes it past this point, then the player has guessed the word within the guesses given and
     * therefore the game is over.
     * @return
     */
    protected boolean isOver() {
        if(guesses == maxGuesses) {
            return true;
        }

        for(int i = 0; i<word.length(); i++) {
            if(hint[i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Takes a String representing a play as a parameter. Returns true if the length of the string is 1 and returns
     * false every other time.
     * @param move
     * @return
     */
    protected boolean isValid(String move) {
        if(move.length() == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Takes a string representing a move as a parameter. Increases the guesses each time, then updates the hint array
     * with the letter guessed if it is in the word through a for loop. Then, the hint array is concatenated into a
     * string and this is returned as the hint.
     * @param move
     * @return
     */
    protected String processMove(String move) {
        guesses++;
        for(int i = 0; i<word.length(); i++){
            if(word.charAt(i) == move.charAt(0)) {
                hint[i] = move.charAt(0);
            }
        }
        String hintAsString = "";
        for(int i = 0; i<word.length(); i++){
            hintAsString += hint[i];
        }
        return hintAsString;
    }

    /**
     * Takes no parameters. Reveals the word as a string.
     * @return
     */
    protected String finalMessage() {
        guesses = 0;
        return "The word was: " + word;
    }
}
