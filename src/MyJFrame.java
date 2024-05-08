import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyJFrame extends JFrame implements ActionListener {
    MyPanel myPanel;
    QuizPanel qPanel;
    private JFrame frame;
    private JPanel floorPanel;
    private JPanel puzzlePanel;
    private JLabel floorLabel;
    private JLabel puzzleLabel;
    private JTextField answerField;
    private JButton submitButton;

    MyJFrame() {
        myPanel = new MyPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(myPanel);
        this.pack();
        this.setVisible(true);
    }

    public void createSwing() {
        floorPanel = new JPanel();
        floorLabel = new JLabel();
        floorPanel.add(floorLabel);

        puzzlePanel = new JPanel();
        puzzleLabel = new JLabel();
        answerField = new JTextField(20);
        submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String answer = answerField.getText();
            //if ( ) {

            //} else {
                // Handle incorrect answer
            //}
        });
        puzzlePanel.add(puzzleLabel);
        puzzlePanel.add(answerField);
        puzzlePanel.add(submitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //if(e.getSource() == button){

        }
    }


