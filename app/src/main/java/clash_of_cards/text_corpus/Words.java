package clash_of_cards.text_corpus;

import java.util.ArrayList;
import java.util.Random;

public class Words {

    private Random random = new Random();
    private ArrayList<String> Words = new ArrayList<>();

    public Words() {
        Words.add("<html>Questioning authority.</html>");
        Words.add("<html>Nuclear war.</html>");
        Words.add("<html>Germs.</html>");
        Words.add("<html>A cursed llama with no eyes.</html>");
        Words.add("<html>Farting a lot today.</html>");
        Words.add("<html>Going bald.</html>");
        Words.add("<html>The wettest fart you ever heard.</html>");
        Words.add("<html>Shaving Dad's back.</html>");  // Using Unicode for apostrophe
        Words.add("<html>Overthrowing the government.</html>");
        Words.add("<html>Murdering.</html>");
        Words.add("<html>Exploding.</html>");
        Words.add("<html>Bleeding.</html>");
        Words.add("<html>Saying \"I love you.\"</html>");  // Using Unicode for quotes
    }

    public String getRandomWord() {
        return Words.get(random.nextInt(Words.size()));
    }
}
