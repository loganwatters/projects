import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Random rng = new Random();
        Scanner myObj = new Scanner(System.in);
        WordsList words = new WordsList(rng);
        WordJumble wordJumble = new WordJumble(words, rng, 1, 13, 3);
        wordJumble.play(myObj);
    }
}
