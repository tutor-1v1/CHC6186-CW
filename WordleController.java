https://tutorcs.com
WeChat: cstutorcs
QQ: 749389476
Email: tutorcs@163.com
public class WordleController {
    private IWordleModel model;
    private WordleView view;

    public WordleController(IWordleModel model) {
        this.model = model;
    }

    public void processInput(String input) {
        model.processInput(input);
    }

    public void setView(WordleView view) {
        this.view = view;
    }

    public boolean isGameOver() {
        return model.isGameOver();
    }

    public boolean isGameWon() {
        return model.isGameWon();
    }

    public String getTargetWord() {
        return model.getTargetWord();
    }

    public StringBuilder getCurrentGuess() {
        return model.getCurrentGuess();
    }

    public int getRemainingAttempts() {
        return model.getRemainingAttempts();
    }

    public void startNewGame() {
        model.startNewGame();
    }
}