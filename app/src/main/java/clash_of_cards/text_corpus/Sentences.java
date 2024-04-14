package clash_of_cards.text_corpus;

import java.util.ArrayList;
import java.util.Random;

public class Sentences {

    Random random = new Random();
    ArrayList<String> sentences = new ArrayList<>();

    public Sentences() {
        sentences.add("<html>This is gonna be ____________</html>");
        sentences.add("<html>the best sleepover ever. Once Mom ________</html>");
        sentences.add("<html>time for _________!</html>");
        sentences.add("<html>When I look in the mirror, I see ______</html>");
        sentences.add("<html>My dad and I enjoy _________</html>");
        sentences.add("<html>Never fear, Captain _________</html>");
        sentences.add("<html>I have discovered something amazing. I have discovered ________________.</html>");
    }

    public String getRandomSentence() {
        return sentences.get(random.nextInt(sentences.size()));
    }
}
