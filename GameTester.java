import java.util.Scanner;

public class GameTester {
    public static void main(String[] args) {
        DummyGame dg = new DummyGame();
        String input;

        System.out.println("Test 1");
        input = "invalid\ninvalid\nvalid\ninvalid\ninvalid\nvalid\nvalid\nwin";
        dg.play(new Scanner(input));

        System.out.println("Test 2");
        input = "valid\nskip\nwin";
        dg.play(new Scanner(input));
    }
}
/*
 Note -- because the "newlines" in a true interactive engagement with a user would be entered by the user (hitting the
 enter key to send data to the program) the output of your program will often have multiple prompts on one line of code.

Test 1
Dummy Game is for dummies.
Enter Your Move or 'quit' to quit> Invalid Move! try again> Invalid Move! try again> good move
Enter Your Move or 'quit' to quit> Invalid Move! try again> Invalid Move! try again> good move
Enter Your Move or 'quit' to quit> good move
Enter Your Move or 'quit' to quit> good move
GoodBye!
Test 2
Dummy Game is for dummies.
Enter Your Move or 'quit' to quit> good move
Enter Your Move or 'quit' to quit> Enter Your Move or 'quit' to quit> good move
GoodBye!
 */