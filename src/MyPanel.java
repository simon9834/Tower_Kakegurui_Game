import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BaseMultiResolutionImage;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class MyPanel extends JPanel implements ActionListener {
    private JButton rightVent = new JButton(),
            leftVent = new JButton(), rightDoor = new JButton(), leftDoor = new JButton();
    private JTextField answerField;
    private JButton submitButton;
    private boolean solvingRn;
    private Font myFont = new Font("Arial", Font.BOLD, 8);
    private final int PANEL_WIDTH = 1000, PANEL_HEIGHT = 600;
    private ArrayList<Riddles> riddles = new ArrayList<>();
    private ArrayList<Puzzles> puzzles = new ArrayList<>();
    private ArrayList<String> correctAr = new ArrayList<>();
    private ArrayList<String> incorrectAr = new ArrayList<>();
    private String actionWhere;
    private Image playerImg, backgroundImg, chessPuzzle;
    private Timer timer;
    private int endX, endY, randomIndex, startX = 700, startY = 500;
    private String riddle, answer, puzzleText;
    private PlayerStats ps = new PlayerStats();
    private final int numFrames = 50 + (100 - ps.getStamina());
    private MainFunctions mf;
    private Random rd;

    public void my1floorPanel() {
        leftDoor = createButton("Left Door", 40, 350, leftDoor);
        rightDoor = createButton("Right Door", 900, 250, rightDoor);
        leftVent = createButton("Left Vent", 200, 436, leftVent);
        rightVent = createButton("Right Vent", 632, 147, rightVent);
        addButtons(leftDoor, leftVent, rightDoor, rightVent);
        createBackground("1stFloor.jpg");
        createPlayer();
        this.setVisible(true);
        repaint();
    }//is finished

    public void my2floorPanel() {
        leftDoor = createButton("Left Door", 40, 350, leftDoor);
        rightDoor = createButton("Right Door", 900, 250, rightDoor);
        leftVent = createButton("Left Vent", 200, 436, leftVent);
        rightVent = createButton("Right Vent", 632, 147, rightVent);
        addButtons(leftDoor, leftVent, rightDoor, rightVent);
        createBackground("1stFloor.jpg");
        createPlayer();
        this.setVisible(true);
        repaint();
    }//is finished

    public void my3floorPanel() {
        changePosition(leftDoor, 626, 349);
        changePosition(rightDoor, 969, 397);
        changePosition(leftVent, 36, 470);
        changePosition(rightVent, 747, 478);
        createBackground("2stFloor.png");
        createPlayer();
        this.setVisible(true);
        repaint();
    }//isnt finished

    public void my4floorPanel() {
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

    public void addButtons(JButton button1, JButton button2, JButton button3, JButton button4) {
        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);
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

    public Image createImage(Image img, int width, int height, String filePath) {
        img = new ImageIcon(filePath).getImage();
        img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        img = new ImageIcon(img).getImage();
        return img;
    }

    public MyPanel() {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        mf = new MainFunctions();
        switch (mf.getCurrentFloor()) {
            case 1 -> my1floorPanel();
            case 2 -> my2floorPanel();
            case 3 -> my3floorPanel();
            case 4 -> my4floorPanel();
            case 5 -> my5floorPanel();
        }
        int animationDuration = 1000;
        timer = new Timer(animationDuration / numFrames, this);
    }

    private JButton createButton(String text, int x, int y, JButton button) {
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
        button.setBounds(x, y, 70, 30);
        button.addActionListener(this);
        button.setVisible(true);
        return button;
    }

    private void changePosition(JButton button, int x, int y) {
        button.setBounds(x, y, 70, 30);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        Font myFont = new Font("Arial", Font.BOLD, 20);
        if (chessPuzzle != null) {
            this.removeAll();
            int xPos = 100;
            int yPos = 50;
            g2D.drawString(rulesForChessPuzzles(), 135, 15);
            g2D.setFont(myFont);
            g2D.drawString(puzzles.get(randomIndex).getQuestion(), xPos + 310, yPos + 15);
            g2D.drawImage(chessPuzzle, xPos + 200, yPos + 30, null);
            chessPuzzle = null;
            createSubmitButtonAndTextField();
        } else if (riddle != null) {
            this.removeAll();
            g2D.setFont(myFont);
            g2D.drawString(riddle, 0, 55);
            riddle = null;
            createSubmitButtonAndTextField();
        } else if (solvingRn) {
            g2D.drawImage(backgroundImg, 0, 0, null);
        } else {
            g2D.drawImage(backgroundImg, 0, 0, null);
            g2D.drawString("You", startX + 13, startY - 5);
            g2D.drawImage(playerImg, startX, startY, null);
        }
    }

    public void hideButtons() {
        leftDoor.setVisible(false);
        leftVent.setVisible(false);
        rightVent.setVisible(false);
        rightDoor.setVisible(false);
    }

    public void animate(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
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
        puzzles.add(new Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle3.png").getImage(), "how many moves tilla mate? (white on turn)", "2"));
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
        puzzleText = puzzles.get(randomIndex).getQuestion();
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
        riddles.add(new Riddles("What is something that you earn, but can" + "\n" + " also save and spend?", "money"));
        riddles.add(new Riddles("What is something that can grow over time," + "\n" + " but needs to be managed carefully?", "investments"));
        riddles.add(new Riddles("What is something that can protect you from" + "\n" + " unexpected financial emergencies?", "insurance"));
        riddles.add(new Riddles("What is something that you must do regularly" + "\n" + " to keep track of your income and expenses?", "budgeting"));
        riddles.add(new Riddles("What is something that can help you achieve" + "\n" + " your financial goals, but requires patience and discipline?", "saving"));
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

    public String getActionWhere() {
        return actionWhere;
    }

    public void setActionWhere(String actionWhere) {
        this.actionWhere = actionWhere;
    }

    public void fillCorrectIncorrect() {
        correctAr.add("Correct/correct - obama.htm");
        correctAr.add("Correct/correct - profesor.jpg");
        correctAr.add("Correct/correct - pidižvík.jpg");
        correctAr.add("Correct/correct - minion.jpg");
        correctAr.add("Correct/correct - cat.png");
        correctAr.add("Correct/correct - simpson.jpg");
        correctAr.add("Correct/correct - sigmaCat.png");
        correctAr.add("Correct/correct - competition.jpg");
        correctAr.add("Correct/ok boomer.jpg");

        incorrectAr.add("Incorrect/incorrect - face.jpg");
        incorrectAr.add("Incorrect/incorrect - boss.jpg");
        incorrectAr.add("Incorrect/incorrect - sigma.png");
        incorrectAr.add("Incorrect/incorrect - what da hail.jpg");
        incorrectAr.add("Incorrect/incorrect - obama.jpg");
        incorrectAr.add("Incorrect/incorrect - trump.jpg");
        incorrectAr.add("Incorrect/incorrect - competition");
        incorrectAr.add("Incorrect/incorrect - tulen.jpg");
        incorrectAr.add("Incorrect/incorrect - catBath.jpg");
    }

    public void createSubmitButtonAndTextField() {
        rd = new Random();
        solvingRn = true;
        fillCorrectIncorrect();
        answerField = new JTextField();
        answerField.setBackground(Color.LIGHT_GRAY);
        Dimension dimForTextField = new Dimension(450, 500);
        answerField.setBounds((int) dimForTextField.getWidth(), (int) dimForTextField.getHeight(), 100, 40);
        submitButton = new JButton();
        submitButton.setBackground(Color.BLACK);
        this.add(createButton("Submit", 465, 555, submitButton));
        this.add(answerField);
        randomIndex = rd.nextInt(correctAr.size()-1);
        submitButton.addActionListener(e -> {
            String answer = answerField.getText();
            if (Objects.equals(this.answer, answer)) {
                this.removeAll();
                createBackground(correctAr.get(randomIndex));
                repaint();
            } else {
                this.removeAll();
                createBackground(incorrectAr.get(randomIndex));
                repaint();
            }
        });
    }
}