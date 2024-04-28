package clash_of_cards.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class SentencesAndAnswers {
    private final Random random = new Random();
    private final ArrayList<String> answers = new ArrayList<>();
    private final ArrayList<String> sentences = new ArrayList<>();
    private String edition;

    public SentencesAndAnswers(String edition) {
        this.edition = edition;
        sentences();
        answers();

    }

    public void sentences() {
        String resourceFile = edition.equals("nerd") ? "nerdSentences.txt" : "familySentences.txt";
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceFile);
            if (inputStream == null) {
                throw new IllegalStateException("Resource file not found.");
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                sentences.addAll(reader.lines().collect(Collectors.toList()));
            }
        } catch (Exception e) {
            System.err.println("Failed to load sentences from file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void answers() {
        String resourceFile = edition.equals("nerd") ? "nerdAnswers.txt" : "familyAnswers.txt";
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceFile);
            if (inputStream == null) {
                throw new IllegalStateException("Resource file not found.");
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                answers.addAll(reader.lines().collect(Collectors.toList()));
            }
        } catch (Exception e) {
            System.err.println("Failed to load answers from file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String getRandomAnswer() {
        if (!answers.isEmpty()) {
            return answers.get(random.nextInt(answers.size()));
        } else {
            return "No answers available.";
        }
    }

    public String getRandomSentence() {
        if (!sentences.isEmpty()) {
            return sentences.get(random.nextInt(sentences.size()));
        } else {
            return "No sentences available.";
        }
    }
}
