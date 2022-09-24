import java.util.Scanner;

/**
 * This class represents any type of game and the cyclical processes that all games go through. Through this abstract class
 * basic game necessities are layed out and a play function is defined for all children classes.
 */
abstract public class Game {
    abstract protected String prepToPlay();
    abstract protected boolean isOver();
    abstract protected boolean isValid(String move);
    abstract protected String processMove(String move);
    abstract protected String finalMessage();
    abstract public String getName();

    /**
     * Takes a Scanner object as a parameter. Then, the abstract functions above used to lay out the overall theme
     * of a game being played. The while loops checks whether the game is over, then gets the play from the user,
     * checks to see if they want to quit, and then makes sure that the play is a valid play, and then processes the
     * play, and also giving feedback to the user. If the user does enter quit, a final message is displayed.
     * @param user
     */
    public void play(Scanner user){
        System.out.println(prepToPlay());
        String play = "";
        while(!(isOver())){
            System.out.print("Enter Your Move or 'quit' to quit> ");
            play = user.next();
            if(play.equals("quit")) {
                break;
            }
            else {
                while (!(isValid(play))) {
                    if(!(isValid(play))){
                        System.out.print("Invalid Move! try again> ");
                    }
                    play = user.next();
                }
                String returnProcessMove = processMove(play);
                if(returnProcessMove != null && returnProcessMove != ""){
                    System.out.println(returnProcessMove);
                }
            }
        }
        System.out.println(finalMessage());
    }
}
