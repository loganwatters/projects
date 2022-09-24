import java.util.Random;
import java.util.Scanner;

/**
 * Class representing a menu on a video game console that allows you to play multiple games. One function: doMenu()
 * starts the game and doesn't stop until the user decides to quit.
 */
public class GameGrabber {
    private Game[] games;
    public Scanner user;

    /**
     * Default constructor.
     */
    public GameGrabber() {
        games = null;
        user = null;
    }
    /**
     * Constructor. Takes a Game object array and a Scanner object.
     * @param games
     * @param user
     */
    public GameGrabber(Game[] games, Scanner user) {
        this.games = games;
        this.user = user;
    }

    /**
     * Takes no parameters and returns nothing. Through a while loop, the games from the games array are printed out
     * and a choice is recieved from the user representing the index of the array. Then, this index's play function
     * is run. Repeat until user says they want to quit (by entering the integer representing the length of the games
     * array).
     */
    public void doMenu() {
        int selection = -1;
        while(true) {
            for (int i = 0; i < games.length; i++) {
                System.out.println(i + ") " + games[i].getName());
            }
            System.out.println(games.length + ") " + "Quit");
            while (!(selection >= 0 && selection <= games.length)) {
                System.out.print("Pick a game (0-" + games.length + ") ");
                String selectionAsString = user.next();
                selection = Integer.parseInt(selectionAsString);
            }
            if (selection != games.length) {
                games[selection].play(user);
                selection = -1;
            }
            else {
                break;
            }

        }
        System.out.println("goodbye");
    }

    public static void main(String[] args){
        Random rng = new Random();
        WordsList words = new WordsList(rng);
        Game[] games = new Game[4];
        games[0] = new Hangman(words, 3,10,5);
        games[1] = new NumberGuesser(rng, 10, 15);
        games[2] = new RPS(rng, 3, 3);
        games[3] = new WordJumble(words, rng, 10, 10, 10);
        Scanner user = new Scanner(System.in);
        GameGrabber gamer = new GameGrabber(games,user);
        gamer.doMenu();
    }
}
