package MainFunctions;

import javax.swing.*;

public class MyJFrame extends JFrame {
    private MyPanel myPanel;

    /**
     * this method adds myPanel class to MainFunctions.MyJFrame and sets the basic functions
     */
    MyJFrame() {
        myPanel = new MyPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(myPanel);
        this.pack();
        this.setVisible(true);
    }
}



