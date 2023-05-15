https://tutorcs.com
WeChat: cstutorcs
QQ: 749389476
Email: tutorcs@163.com
public class WordleApp {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        createAndShowGUI();
                    }
                }
        );
    }

    public static void createAndShowGUI() {
        IWordleModel model = new WordleModel();
        WordleController controller = new WordleController(model);
        WordleView view = new WordleView(model, controller);
    }
}