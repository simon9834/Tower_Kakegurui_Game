import javax.swing.*;
import java.awt.*;

public class ChessPuzzlesPanel extends JPanel {
    private JLabel puzzleLabel, textLabel;
    private Image puzzle;
    private String text;
    public ChessPuzzlesPanel(Image puzzle, String text, int PANEL_WIDTH, int PANEL_HEIGHT) {
        this.puzzle = puzzle;
        this.text = text;
        createPanel();
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    }
    public void createPanel(){
        puzzleLabel = new JLabel(new ImageIcon(puzzle));
        textLabel = new JLabel(text);
        add(textLabel);
        add(puzzleLabel);
    }


}
