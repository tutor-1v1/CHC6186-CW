https://tutorcs.com
WeChat: cstutorcs
QQ: 749389476
Email: tutorcs@163.com
import java.util.List;

public interface IWordleModel {
    int MAX_ATTEMPTS = 6;
    List<String> VALID_WORDS = List.of("APPLE", "HAPPY", "CHERRY", "GRAPE", "SUNNY", "LEMON", "DAISY");

    void initialize();
    boolean processInput(String input);
    boolean isGameOver();
    boolean isGameWon();
    String getTargetWord();
    StringBuilder getCurrentGuess();
    int getRemainingAttempts();
    void startNewGame();
}
