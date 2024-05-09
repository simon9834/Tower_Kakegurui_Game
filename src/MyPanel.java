import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BaseMultiResolutionImage;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class MyPanel extends JPanel implements ActionListener {
    private JButton rightVent = new JButton(),
            leftVent = new JButton(), rightDoor = new JButton(), leftDoor = new JButton();
    private JFrame frame;
    private JPanel floorPanel;
    private JPanel puzzlePanel;
    private JLabel floorLabel;
    private JLabel puzzleLabel;
    private JTextField answerField;
    private JButton submitButton;
    private Font myFont = new Font("Arial", Font.BOLD, 8);
    private final int PANEL_WIDTH = 1000, PANEL_HEIGHT = 600;
    private ArrayList<Riddles> riddles = new ArrayList<>();
    private ArrayList<Puzzles> puzzles = new ArrayList<>();
    private ArrayList<String> correctAr = new ArrayList<>();
    private ArrayList<String> incorrectAr = new ArrayList<>();
    private String actionWhere;
    private Image playerImg, backgroundImg, chessPuzzle, correct, incorrect;
    private Timer timer;
    private int endX, endY, randomIndex, startX = 700, startY = 500;
    private String riddle, answer, puzzleText;
    private PlayerStats ps = new PlayerStats();
    private final int numFrames = 50 + (100 - ps.getStamina());
    private MainFunctions mf;

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
    public Image createImage(Image img, int width, int height, String filePath){
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

        if (chessPuzzle != null) {
            this.removeAll();
            int xPos = 100;
            int yPos = 50;
            g2D.drawString(puzzles.get(randomIndex).getQuestion(), xPos + 150, yPos - 30);
            g2D.drawImage(chessPuzzle, xPos, yPos, null);
            chessPuzzle = null;
            createSwing();
        } else if (riddle != null) {
            this.removeAll();

            int xPos = 100;
            int yPos = 50;
            g2D.drawString(riddle, xPos, yPos);
            riddle = null;
            createSwing();
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

    public void loadChessPuzzle() {
        puzzles.add(new Puzzles(new ImageIcon("chessPuzzle1.png").getImage(), "Question1", "Answer1"));
        puzzles.add(new Puzzles(new ImageIcon("chessPuzzle2.png").getImage(), "Question2", "Answer2"));
        puzzles.add(new Puzzles(new ImageIcon("chessPuzzle3.png").getImage(), "Question3", "Answer3"));
        puzzles.add(new Puzzles(new ImageIcon("chessPuzzle4.png").getImage(), "Question4", "Answer4"));
        puzzles.add(new Puzzles(new ImageIcon("chessPuzzle5.png").getImage(), "Question5", "Answer5"));
        puzzles.add(new Puzzles(new ImageIcon("chessPuzzle6.png").getImage(), "Question6", "Answer6"));
        puzzles.add(new Puzzles(new ImageIcon("chessPuzzle7.png").getImage(), "Question7", "Answer7"));
        puzzles.add(new Puzzles(new ImageIcon("chessPuzzle8.png").getImage(), "Question8", "Answer8"));
    }

    public void getChessPuzzle() {
        loadChessPuzzle();
        Random rd = new Random();
        randomIndex = rd.nextInt(puzzles.size() - 1);
        chessPuzzle = puzzles.get(randomIndex).getImg().getScaledInstance(PANEL_HEIGHT - 200, PANEL_HEIGHT - 200, Image.SCALE_SMOOTH);
        chessPuzzle = new ImageIcon(chessPuzzle).getImage();
        puzzleText = puzzles.get(randomIndex).getQuestion();
        repaint();
    }

    public void getRiddle() {
        loadRiddle();
        Random rd = new Random();
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
    public void fillCorrectIncorrect(){
        correctAr.add("\"C:\\Users\\HP EliteBook 830 G5\\Downloads\\correct\\correct - obama.htm\"");
        correctAr.add("\"C:\\Users\\HP EliteBook 830 G5\\Downloads\\correct\\correct - profesor.jpg\"");
        correctAr.add("\"C:\\Users\\HP EliteBook 830 G5\\Downloads\\correct\\correct - pidižvík.jpg\"");
        correctAr.add("\"C:\\Users\\HP EliteBook 830 G5\\Downloads\\correct\\correct - minion.jpg\"");
        correctAr.add("\"C:\\Users\\HP EliteBook 830 G5\\Downloads\\correct\\correct - cat.png\"");
        correctAr.add("\"C:\\Users\\HP EliteBook 830 G5\\Downloads\\correct\\correct - simpson.jpg\"");
        correctAr.add("\"C:\\Users\\HP EliteBook 830 G5\\Downloads\\correct\\correct - sigmaCat.png\"");
        correctAr.add("\"C:\\Users\\HP EliteBook 830 G5\\Downloads\\correct\\correct - competition.jpg\"");

        incorrectAr.add("\"C:\\Users\\HP EliteBook 830 G5\\Downloads\\incorrect\\incorrect - face.jpg\"");
        incorrectAr.add("\"C:\\Users\\HP EliteBook 830 G5\\Downloads\\incorrect\\incorrect - boss.jpg\"");
        incorrectAr.add("\"C:\\Users\\HP EliteBook 830 G5\\Downloads\\incorrect\\incorrect - sigma.png\"");
        incorrectAr.add("\"C:\\Users\\HP EliteBook 830 G5\\Downloads\\incorrect\\incorrect - what da hail.jpg\"");
        incorrectAr.add("\"C:\\Users\\HP EliteBook 830 G5\\Downloads\\incorrect\\incorrect - obama.jpg\"");
        incorrectAr.add("\"C:\\Users\\HP EliteBook 830 G5\\Downloads\\incorrect\\incorrect - trump.jpg\"");
        incorrectAr.add("\"C:\\Users\\HP EliteBook 830 G5\\Downloads\\incorrect\\incorrect - competition\"");
        incorrectAr.add("\"C:\\Users\\HP EliteBook 830 G5\\Downloads\\incorrect\\incorrect - tulen.jpg\"");
        incorrectAr.add("\"C:\\Users\\HP EliteBook 830 G5\\Downloads\\incorrect\\incorrect - catBath.jpg\"");
    }
    public void createSwing() {
        fillCorrectIncorrect();
        answerField = new JTextField();
        answerField.setBackground(Color.CYAN);
        Dimension dimForTextField = new Dimension(520, 500);
        answerField.setBounds((int)dimForTextField.getWidth(), (int)dimForTextField.getHeight(), 100, 40);
        submitButton = new JButton();
        this.add(createButton("Submit", 510, 500, submitButton));
        this.add(answerField);
        submitButton.addActionListener(e -> {
            String answer = answerField.getText();
            if (Objects.equals(this.answer, answer)) {
                this.removeAll();
                createBackground(correctAr.get(randomIndex));
            } else {
                this.removeAll();
                createBackground(incorrectAr.get(randomIndex));
            }
        });

    }
}