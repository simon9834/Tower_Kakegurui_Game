import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JSwingInteractions{

    private MainFunctions game;
    private JFrame frame;
    private JPanel floorPanel;
    private JPanel puzzlePanel;
    private JLabel floorLabel;
    private JLabel puzzleLabel;
    private JTextField answerField;
    private JButton submitButton;
    private Image playerImg, player, bckground, backgroundImg,
            chessPuzzle;

    public JSwingInteractions(MainFunctions game) {
        this.game = game;
        createSwing();
    }

    public void createSwing() {
        frame = new JFrame("Escape the Tower");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 650);

        floorPanel = new JPanel();
        floorLabel = new JLabel();
        floorPanel.add(floorLabel);

        puzzlePanel = new JPanel();
        puzzleLabel = new JLabel();
        answerField = new JTextField(20);
        submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String answer = answerField.getText();
            if (game.checkAnswer(answer)) {
                // Handle correct answer
                // Move to the next room, etc.
            } else {
                // Handle incorrect answer
            }
        });
        puzzlePanel.add(puzzleLabel);
        puzzlePanel.add(answerField);
        puzzlePanel.add(submitButton);

        frame.setLayout(new BorderLayout());
        frame.add(floorPanel, BorderLayout.CENTER);
        frame.add(puzzlePanel, BorderLayout.SOUTH);
        startGame();
    }

    public void displayRoom(TowerFloor floor) {
        floorLabel.setIcon(new ImageIcon(floor.getFloorImage()));
        floorLabel.setText(game.getCurrentFloor() + "Floor");
        backgroundImg = new ImageIcon("1stFloor.jpg").getImage();
        bckground = backgroundImg.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        backgroundImg = new ImageIcon(bckground).getImage();



    }

    public void displayPuzzle(Puzzles puzzle) {
        puzzleLabel.setIcon(new ImageIcon(puzzle.getImg()));
        puzzleLabel.setText(puzzle.getQuestion());
    }

    public void startGame() {
        frame.setVisible(true);
    }
}
