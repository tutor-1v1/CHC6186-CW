https://tutorcs.com
WeChat: cstutorcs
QQ: 749389476
Email: tutorcs@163.com
import java.util.Random;

public class WordleModel extends java.util.Observable implements IWordleModel {
    private String targetWord;
    private StringBuilder currentGuess;
    private int remainingAttempts;
    private boolean gameWon;

    public void initialize() {
        // Select a random word from the list of valid words.
        Random rand = new Random();
        int index = rand.nextInt(VALID_WORDS.size());
        targetWord = VALID_WORDS.get(index);

        // Initialize the current guess to underscores (_). Just 5 here
        currentGuess = new StringBuilder(targetWord.length());
        for (int i = 0; i < targetWord.length(); i++) {
            currentGuess.append("_");
        }

        // Set the initial number of remaining attempts. Just 6 here
        remainingAttempts = MAX_ATTEMPTS;

        // Indicates that the game has not yet been won.
        gameWon = false;

        // Notify observers that the model has changed.
        setChanged();
        notifyObservers();
    }

    public boolean processInput(String input) {
        // Ensure that the input is not a single character. * Ensure the input is a word
        if (input.length() == 1) {
            return false;
        }

        boolean letterFound = false;

        char c = Character.toUpperCase(input.charAt(0));
        // Check if the input letter matches any letter in the target word.
        for (int i = 0; i < targetWord.length(); i++) {
            char targetChar = Character.toUpperCase(targetWord.charAt(i));
            if (c == targetChar) {
                currentGuess.setCharAt(i, c);
                letterFound = true;
            }
        }

        // Decrement remaining attempts
        remainingAttempts--;

        // Check if the game has been won.
        if (currentGuess.toString().equals(targetWord)) {
            gameWon = true;
        }

        // Notify observers that the model has changed.
        setChanged();
        notifyObservers();

        return true;
    }

    public boolean isGameOver() {
        return remainingAttempts <= 0 || gameWon;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public String getTargetWord() {
        return targetWord;
    }

    public StringBuilder getCurrentGuess() {
        return currentGuess;
    }

    public int getRemainingAttempts() {
        return remainingAttempts;
    }

    public void startNewGame() {
        initialize();
    }
}