https://tutorcs.com
WeChat: cstutorcs
QQ: 749389476
Email: tutorcs@163.com
import javax.swing.*;
import java.awt.*;
import java.util.Observer;

public class WordleView extends JFrame implements Observer {
    private final IWordleModel model;
    private WordleController controller;
    private JLabel targetWordLabel;
    private JTextField inputTextField;
    private JLabel attemptsLabel;
    private JButton[] letterButtons;

    public WordleView(IWordleModel model, WordleController controller) {

        this.controller = controller;
        this.model = model;
        controller.startNewGame();
        ((WordleModel)this.model).addObserver(this);
        initialize();
        this.controller.setView(this);
        update((WordleModel)this.model, null);


    }

    public void initialize() {
        // Set up the frame.
        setTitle("Wordle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(new BorderLayout());

        // Set up the target word label.
        JPanel targetWordPanel = new JPanel();
        targetWordLabel = new JLabel("_____");
        targetWordLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        targetWordPanel.add(targetWordLabel);
        add(targetWordPanel, BorderLayout.NORTH);

        // Set up the input text field and submit button.
        JPanel inputPanel = new JPanel();
        inputTextField = new JTextField(5);
        inputPanel.add(inputTextField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            controller.processInput(inputTextField.getText());
            inputTextField.setText("");

        });
        inputPanel.add(submitButton);

        add(inputPanel, BorderLayout.SOUTH);

        // Set up the attempts label.
        JPanel attemptsPanel = new JPanel();
        attemptsLabel = new JLabel("Attempts remaining: " + controller.getRemainingAttempts());
        attemptsPanel.add(attemptsLabel);
        add(attemptsPanel, BorderLayout.EAST);

        // Set up the letter buttons.
        JPanel letterPanel = new JPanel();
        letterPanel.setLayout(new GridLayout(4,10));
        letterButtons = new JButton[26];

        for (char c = 'A'; c <= 'Z'; c++) {
            JButton button = new JButton(Character.toString(c));
            button.setEnabled(true);
            button.addActionListener(e -> {
                inputTextField.setText(inputTextField.getText() + button.getText());
            });
            letterButtons[c - 'A'] = button;
            letterPanel.add(button);
        }

        add(letterPanel, BorderLayout.CENTER);

        // Make the frame visible.
        setVisible(true);
    }

    public void update() {
        targetWordLabel.setText(controller.getCurrentGuess().toString());
        attemptsLabel.setText("Attempts remaining: " + controller.getRemainingAttempts());

        if (controller.isGameOver()) {
            // Disable all letter buttons.
            for (JButton button : letterButtons) {
                button.setEnabled(false);
            }

            // Show a dialog indicating whether the game was won or lost.
            if (controller.isGameWon()) {
                JOptionPane.showMessageDialog(this, "Congratulations! You won!");
            } else {
                JOptionPane.showMessageDialog(this, "Sorry, you lost. The word was " + controller.getTargetWord());
            }
        }
    }

    public void update(java.util.Observable o, Object arg) {
        update();
    }
}