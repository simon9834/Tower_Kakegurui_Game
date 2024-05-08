import javax.swing.*;
import java.awt.*;

public class RiddlePanel extends JPanel {
    private JLabel riddleLabel, answerLabel;
    private String riddle;
    private String answer;

    public RiddlePanel(String riddle, String answer, int PANEL_WIDTH, int PANEL_HEIGHT) {
        this.riddle = riddle;
        this.answer = answer;
        createPanel();
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    }
    public void createPanel(){
        riddleLabel = new JLabel(riddle);
        answerLabel = new JLabel(answer);
        add(riddleLabel);
    }
    /*public void check(){
        if(){//if the person answered
            add(answerLabel);
        }
    }*/
}
