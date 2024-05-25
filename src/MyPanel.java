import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class MyPanel extends JPanel implements ActionListener {
    private JButton rightVent = new JButton(),
            leftVent = new JButton(), rightDoor = new JButton(),
            leftDoor = new JButton(), centerDoor = new JButton();
    private JTextField answerField;
    private JButton submitButton;
    private boolean solvingRn, solved;
    private Font myFont = new Font("Arial", Font.BOLD, 8);
    private int PANEL_WIDTH = 1000;
    private int PANEL_WIDTH_OLD = 1000;
    private int PANEL_HEIGHT_OLD = 1000;
    private int PANEL_HEIGHT = 600;
    private final int animationDuration = 1000;
    private ArrayList<Riddles> riddles = new ArrayList<>();
    private ArrayList<Puzzles> puzzles = new ArrayList<>();
    private ArrayList<String> correctAr = new ArrayList<>();
    private ArrayList<String> incorrectAr = new ArrayList<>();
    private ArrayList<JButton> buttonsAr = new ArrayList<>();
    private String actionWhere;
    private Image playerImg, backgroundImg, chessPuzzle;
    private Timer timer;
    private int endX, endY, randomIndex, startX = 700, startY = 500, xCenteredValue;
    private String riddle, answer, flexibleString;
    private PlayerStats ps = new PlayerStats();
    private final int numFrames = 50 + (100 - ps.getStamina());
    private MainFunctions mf = new MainFunctions();
    private Random rd;

    public void my1floorPanel() {
        buttonsAr.clear();
        createButton("Left Door", 40, 350, leftDoor, false);
        createButton("Right Door", 900, 250, rightDoor, false);
        createButton("Left Vent", 200, 436, leftVent, false);
        createButton("Right Vent", 632, 147, rightVent, false);
        createButton("Center Door", 500, 450, centerDoor, false);
        buttonsAr.add(leftDoor);
        buttonsAr.add(rightDoor);
        buttonsAr.add(leftVent);
        buttonsAr.add(rightVent);
        buttonsAr.add(centerDoor);
        this.add(centerDoor);
        addButtons(leftDoor, leftVent, rightDoor, rightVent);
        createBackground("Floors/1stFloor.jpg");
        createPlayer();
        this.setVisible(true);
        repaint();
    }

    public void my2floorPanel() {
        buttonsAr.clear();
        createButton("Left Door", 615, 380, leftDoor, false);
        createButton("Right Door", 910, 463, rightDoor, false);
        createButton("Left Vent", 0, 506, leftVent, false);
        createButton("Right Vent", 759, 509, rightVent, false);
        buttonsAr.add(leftDoor);
        buttonsAr.add(rightDoor);
        buttonsAr.add(leftVent);
        buttonsAr.add(rightVent);
        addButtons(leftDoor, leftVent, rightDoor, rightVent);
        createBackground("Floors/2stFloor.png");
        createPlayer();
        this.setVisible(true);
        repaint();
    }

    public void my3floorPanel() {
        buttonsAr.clear();
        createButton("Left Door", 5, 404, leftDoor, false);
        createButton("Right Door", 308, 382, rightDoor, false);
        createButton("Left Vent", 214, 513, leftVent, false);
        createButton("Right Vent", 910, 507, rightVent, false);
        buttonsAr.add(leftDoor);
        buttonsAr.add(rightDoor);
        buttonsAr.add(leftVent);
        buttonsAr.add(rightVent);
        addButtons(leftDoor, leftVent, rightDoor, rightVent);
        createBackground("Floors/3thFloor.png");
        createPlayer();
        this.setVisible(true);
        repaint();
    }

  /*  public void my4floorPanel() {
        changePosition(leftDoor, 626, 349);
        changePosition(rightDoor, 969, 397);
        changePosition(leftVent, 36, 470);
        changePosition(rightVent, 747, 478);
        createBackground("2stFloor.png");
        createPlayer();
        this.setVisible(true);
        repaint();
    }//isnt finished

    public void my5floorPanel() {
        changePosition(leftDoor, 626, 349);
        changePosition(rightDoor, 969, 397);
        changePosition(leftVent, 36, 470);
        changePosition(rightVent, 747, 478);
        createBackground("2stFloor.png");
        createPlayer();
        this.setVisible(true);
        repaint();
    }//isnt finished
    */


    public void addButtons(JButton button1, JButton button2, JButton button3, JButton button4) {
        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);
        button1.setLocation(button1.getX(), button1.getY());
        button2.setLocation(button2.getX(), button2.getY());
        button3.setLocation(button3.getX(), button3.getY());
        button4.setLocation(button4.getX(), button4.getY());
    }

    public void resizeButtons() {
        int x;
        int y;
        for (JButton jButton : buttonsAr) {
            x = (int) (((double) jButton.getX() / PANEL_WIDTH_OLD) * PANEL_WIDTH);
            y = (int) (((double) jButton.getY() / PANEL_HEIGHT_OLD) * PANEL_HEIGHT);
            jButton.setLocation(x, y);
        }
        startX = (int) (((double) startX / PANEL_WIDTH_OLD) * PANEL_WIDTH);
        startY = (int) (((double) startY / PANEL_HEIGHT_OLD) * PANEL_HEIGHT);
        PANEL_WIDTH_OLD = PANEL_WIDTH;
        PANEL_HEIGHT_OLD = PANEL_HEIGHT;
    }

    public void createPlayer() {
        playerImg = new ImageIcon("playerIcon.png").getImage();
        playerImg = playerImg.getScaledInstance(52, 52, Image.SCALE_SMOOTH);
        playerImg = new ImageIcon(playerImg).getImage();
    }

    public void createBackground(String filePath) {
        backgroundImg = new ImageIcon(filePath).getImage();
        backgroundImg = backgroundImg.getScaledInstance(PANEL_WIDTH, PANEL_HEIGHT, Image.SCALE_SMOOTH);
        backgroundImg = new ImageIcon(backgroundImg).getImage();
    }

    public MyPanel() {
        layoutSetting();
    }

    public void layoutSetting() {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        switch (mf.getCurrentFloor()) {
            case 1 -> my1floorPanel();
            case 2 -> my2floorPanel();
            case 3 -> my3floorPanel();
           /* case 4 -> my4floorPanel();
            case 5 -> my5floorPanel();*/

        }
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension newSize = getSize();
                resizeGame(newSize.width, newSize.height);
            }
        });

    }

    private void resizeGame(int width, int height) {
        PANEL_WIDTH = width;
        PANEL_HEIGHT = height;
        //resizeButtons();
        repaint();
    }

    public void timerSetting(int milliSecs, boolean waitingForFloor) {
        if (!waitingForFloor) {
            timer = new Timer(milliSecs, this);
        } else {
            timer = new Timer(milliSecs, e -> {
                nextFloorOrBackToFloor();
                timer.setRepeats(false);
            });
        }
    }

    private JButton createButton(String text, int x, int y, JButton button, boolean centered) {
        button.setFont(myFont);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.BLACK);
                button.setForeground(Color.WHITE);
            }
        });
        button.setText(text);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setBounds(x, y, 85, 35);
        button.addActionListener(this);
        button.setVisible(true);
        if (centered) {
            int xNew = centerAButtonX(button);
            button.setBounds(xNew, y, 85, 35);
        }
        return button;
    }

    public Integer centerAButtonX(JButton button) {
        return (int) ((PANEL_WIDTH - button.getBounds().getWidth()) / 2);
    }

    public Integer centerATextFieldX(JTextField textField) {
        return (int) (PANEL_WIDTH - textField.getBounds().getWidth()) / 2;
    }

    public Integer setCenteredWidth(String string, boolean biggerFont) {
        if (biggerFont) {
            myFont = new Font("Arial", Font.BOLD, 20);
        } else {
            myFont = new Font("Arial", Font.BOLD, 8);
        }
        FontMetrics fm1 = g2D.getFontMetrics(myFont);
        flexibleString = string;
        myFont = new Font("Arial", Font.BOLD, 8);
        return centerAString(fm1.stringWidth(flexibleString));
    }

    private Graphics2D g2D;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2D = (Graphics2D) g;
        Font myFont1 = new Font("Arial", Font.BOLD, 20);
        if (chessPuzzle != null) {
            this.removeAll();
            xCenteredValue = setCenteredWidth(rulesForChessPuzzles(), false);
            g2D.drawString(rulesForChessPuzzles(), xCenteredValue, 15);
            g2D.setFont(myFont1);
            xCenteredValue = setCenteredWidth(puzzles.get(randomIndex).getQuestion(), true);
            g2D.drawString(flexibleString, xCenteredValue, 65);
            g2D.drawImage(chessPuzzle, 300, 80, null);
            createSubmitButtonAndTextField();
        } else if (riddle != null) {
            this.removeAll();
            g2D.setFont(myFont1);
            xCenteredValue = setCenteredWidth(riddle, true);
            g2D.drawString(flexibleString, xCenteredValue, 55);
            createSubmitButtonAndTextField();
        } else if (solvingRn) {
            g2D.drawImage(backgroundImg, 0, 0, null);
            waiting(2799);
            solvingRn = false;
        } else {
            g2D.drawImage(backgroundImg, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, this);
            g2D.drawString("You", startX + 13, startY - 5);
            g2D.drawImage(playerImg, startX, startY, null);
        }
    }

    public void waiting(int milliSeconds) {
        timerSetting(milliSeconds, true);
        timer.start();
    }

    public Integer centerAString(int pixelLength) {
        return (PANEL_WIDTH - pixelLength) / 2;
    }

    public void nextFloorOrBackToFloor() {
        timer.stop();
        if (solved) {
            mf.setCurrentFloor(mf.getCurrentFloor() + 1);
            layoutSetting();
        } else {
            layoutSetting();
        }
    }

    public void animate(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        timerSetting(animationDuration / numFrames, false);
        timer.start();
    }

    public String rulesForChessPuzzles() {
        return "Rules: In chess puzzles you need to enter either " +
                "the figure that moves and the placement where it would move, or the " +
                "number of moves that it would take to win";
    }

    public void loadChessPuzzle() { //https://chessfox.com/chess-puzzles-for-intermediate-players/
        puzzles.add(new Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle1.png").getImage(), "where will you get material advantage (white on turn)", "Rf7"));
        puzzles.add(new Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle2.png").getImage(), "where will you get material advantage threatening a mate (black on turn)", "Dh5"));
        puzzles.add(new Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle3.png").getImage(), "how many moves till a mate? (white on turn)", "2"));
        puzzles.add(new Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle4.png").getImage(), "whats the best move here? (black on turn)", "Dh4"));
        puzzles.add(new Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle5.png").getImage(), "where will you move the bishop? (black on turn)", "Bb2"));
        puzzles.add(new Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle6.png").getImage(), "how many moves till mate? (white on move)", "2"));
        puzzles.add(new Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle7.png").getImage(), "how many moves till mate? (white on move)", "2"));
        puzzles.add(new Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle8.png").getImage(), "can you take the knight on e4 without any further problems? (yes/no)", "yes"));
    }

    public void getChessPuzzle() {
        rd = new Random();
        loadChessPuzzle();
        randomIndex = rd.nextInt(puzzles.size() - 1);
        chessPuzzle = puzzles.get(randomIndex).getImg().getScaledInstance(PANEL_HEIGHT - 200, PANEL_HEIGHT - 200, Image.SCALE_SMOOTH);
        chessPuzzle = new ImageIcon(chessPuzzle).getImage();
        answer = puzzles.get(randomIndex).getAnswer();
        repaint();
    }

    public void getRiddle() {
        rd = new Random();
        loadRiddle();
        randomIndex = rd.nextInt(riddles.size() - 1);
        riddle = riddles.get(randomIndex).getRiddle();
        answer = riddles.get(randomIndex).getAnswer();
        repaint();
    }

    public void loadRiddle() {
        riddles.add(new Riddles("What is something that you earn, but can also save and spend?", "money"));
        riddles.add(new Riddles("What is something that can grow over time, but needs to be managed carefully?", "investments"));
        riddles.add(new Riddles("What is something that can protect you from unexpected financial emergencies?", "insurance"));
        riddles.add(new Riddles("What is something that you must do regularly to keep track of your income and expenses?", "budgeting"));
        riddles.add(new Riddles("What is something that can help you achieve your financial goals, but requires patience and discipline?", "saving"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int deltaX = 0;
        int deltaY = 0;
        if (e.getSource() == leftDoor) {
            actionWhere = "leftDoor";
            animate(startX, startY, leftDoor.getX(), leftDoor.getY());
        } else if (e.getSource() == rightDoor) {
            actionWhere = "rightDoor";
            animate(startX, startY, rightDoor.getX(), rightDoor.getY());
        } else if (e.getSource() == leftVent) {
            actionWhere = "leftVent";
            animate(startX, startY, leftVent.getX(), leftVent.getY());
        } else if (e.getSource() == rightVent) {
            actionWhere = "rightVent";
            animate(startX, startY, rightVent.getX(), rightVent.getY());
        } else if (e.getSource() == centerDoor) {
            actionWhere = "centerDoor";
            animate(startX, startY, centerDoor.getX(), centerDoor.getY());
        } else if (e.getSource() == timer) {
            deltaX = (endX - startX) / numFrames;
            deltaY = (endY - startY) / numFrames;

            startX += deltaX;
            startY += deltaY;
            repaint();
        }
        if (deltaX == 0 && deltaY == 0 && e.getSource() == timer) {
            Random random = new Random();
            boolean randomBoolean = random.nextBoolean();
            timer.stop();
            if (randomBoolean)
                getChessPuzzle();
            else {
                getRiddle();
            }
        }
    }

    public void fillCorrectIncorrect() {
        correctAr.add("Correct/correctCompet.jpg");
        correctAr.add("Correct/correct - profesor.jpg");
        correctAr.add("Correct/correct - pidižvík.jpg");
        correctAr.add("Correct/correct - minion.jpg");
        correctAr.add("Correct/correct - cat.png");
        correctAr.add("Correct/correct - simpson.jpg");
        correctAr.add("Correct/correct - sigmaCat.png");
        correctAr.add("Correct/correct - competition.jpg");
        correctAr.add("Correct/ok boomer.jpg");
        correctAr.add("Correct/correctJesus.jpg");
        correctAr.add("Correct/correctBrain.png");

        incorrectAr.add("Incorrect/incorrect - face.jpg");
        incorrectAr.add("Incorrect/incorrect - boss.jpg");
        incorrectAr.add("Incorrect/incorrect - sigma.png");
        incorrectAr.add("Incorrect/incorrect - what da hail.jpg");
        incorrectAr.add("Incorrect/incorrect - obama.jpg");
        incorrectAr.add("Incorrect/incorrect - trump.jpg");
        incorrectAr.add("Incorrect/incorrect - tulen.jpg");
        incorrectAr.add("Incorrect/incorrect - catBath.jpg");
        incorrectAr.add("Incorrect/dogIncorrect.jpg");
        incorrectAr.add("Incorrect/incorrectBro.jpg");
        incorrectAr.add("Incorrect/incorrectTeach.jpg");
    }

    public void createSubmitButtonAndTextField() {
        rd = new Random();
        int xNew;
        solvingRn = true;
        fillCorrectIncorrect();
        answerField = new JTextField();
        answerField.setBackground(Color.LIGHT_GRAY);
        answerField.setBounds(0, 500, 120, 40);
        xNew = centerATextFieldX(answerField);
        answerField.setBounds(xNew, 500, 120, 40);
        answerField.setVisible(true);
        submitButton = new JButton();
        submitButton.setBackground(Color.BLACK);
        createButton("Submit", 0, 555, submitButton, true);
        this.add(submitButton);
        this.add(answerField);
        randomIndex = rd.nextInt(correctAr.size() - 1);
        submitButton.addActionListener(e -> {
            String answer = answerField.getText();
            if (Objects.equals(this.answer.toLowerCase().replaceAll("\\s+", ""), answer.toLowerCase().replaceAll("\\s+", ""))) {
                this.removeAll();
                createBackground(correctAr.get(randomIndex));
                solved = true;
                chessPuzzle = null;
                riddle = null;
                repaint();
            } else {
                this.removeAll();
                createBackground(incorrectAr.get(randomIndex));
                solved = false;
                chessPuzzle = null;
                riddle = null;
                repaint();
            }
        });
    }
}