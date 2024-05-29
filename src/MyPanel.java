import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class MyPanel extends JPanel implements ActionListener {
    private JButton rightVent = new JButton(),
            leftVent = new JButton(), rightDoor = new JButton(),
            leftDoor = new JButton(), centerDoor = new JButton(),
            restart = new JButton(), lastQuest = new JButton();
    private JTextField answerField;
    private JButton submitButton;
    private boolean solvingRn, solved, playerIsDone, lastRiddle;
    private Font myFont = new Font("Arial", Font.BOLD, 8);
    private int PANEL_WIDTH = 1000;
    private int PANEL_WIDTH_OLD = 1000;
    private int PANEL_HEIGHT_OLD = 600;
    private int PANEL_HEIGHT = 600;
    private final int animationDuration = 1000;
    private ArrayList<Riddles> riddles = new ArrayList<>();
    private ArrayList<Puzzles> puzzles = new ArrayList<>();
    private ArrayList<String> correctAr = new ArrayList<>();
    private ArrayList<String> incorrectAr = new ArrayList<>();
    private ArrayList<JButton> buttonsAr = new ArrayList<>();
    private String actionWhere, theUltimateRiddle = "A man has 20 pairs of socks in his drawer: " +
            "6 blue pairs, 9 black pairs and 5 red pairs. The lights are " +
            "out and he is completely in the dark. How many socks must he take out to make " +
            "100 percent certain he has at least one pair of black socks?";
    private Image playerImg, backgroundImg, chessPuzzle;
    private Timer timer;
    private int endX, endY, randomIndex, startX = 700, startY = 500, xCenteredValue;
    private String riddle, answer, flexibleString;
    private PlayerStats ps = new PlayerStats();
    private int numFrames = 75 - ps.getStamina();
    private MainFunctions mf = new MainFunctions();
    private Random rd;
    private StackTraceElement caller;


    public void my1floorPanel() {
        buttonsAr.clear();
        createButton("Left Door", 80, 350, leftDoor, false);
        createButton("Right Door", 900, 260, rightDoor, false);
        createButton("Left Vent", 185, 450, leftVent, false);
        createButton("Right Vent", 632, 150, rightVent, false);
        createButton("Center Door", 550, 420, centerDoor, false);
        buttonsAr.add(leftDoor);
        buttonsAr.add(rightDoor);
        buttonsAr.add(leftVent);
        buttonsAr.add(rightVent);
        buttonsAr.add(centerDoor);
        addButtonsToFrame();
        createBackground("Floors/1stFloor.jpg");
        createPlayer();
        checkCaller("layoutSetting");
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
        addButtonsToFrame();
        createBackground("Floors/2stFloor.png");
        createPlayer();
        checkCaller("layoutSetting");
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
        addButtonsToFrame();
        createBackground("Floors/3thFloor.png");
        createPlayer();
        checkCaller("layoutSetting");
        this.setVisible(true);
        repaint();
    }

    public void my4floorPanel() {
        buttonsAr.clear();
        createButton("LastRiddle", 460, 438, lastQuest, false);
        createBackground("2stFloor.png");
        buttonsAr.add(lastQuest);
        addButtonsToFrame();
        createBackground("Floors/4thFloor.png");
        createPlayer();
        checkCaller("layoutSetting");
        this.setVisible(true);
        repaint();
    }//isnt finished

    /*public void finalFloor() {
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

    public void checkCaller(String methodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length > 3) {
            caller = stackTrace[3];
        }
        if (caller.getMethodName().equals(methodName)) {
            recalculate();
        } else {
            moveButtons();
        }
    }

    public void recalculate() {
        int x;
        int y;
        for (JButton jButton : buttonsAr) {
            x = (int) (((double) jButton.getX() / 1000) * PANEL_WIDTH);
            y = (int) (((double) jButton.getY() / 600) * PANEL_HEIGHT);
            jButton.setLocation(x, y);
        }
    }

    public void addButtonsToFrame() {
        int i = 0;
        for (JButton ignored : buttonsAr) {
            this.add(buttonsAr.get(i));
            buttonsAr.get(i).setLocation(buttonsAr.get(i).getX(), buttonsAr.get(i).getY());
            i++;
        }
    }


    public void moveButtons() {
        int x;
        int y;
        for (JButton jButton : buttonsAr) {
            x = (int) (((double) jButton.getX() / PANEL_WIDTH_OLD) * PANEL_WIDTH);
            y = (int) (((double) jButton.getY() / PANEL_HEIGHT_OLD) * PANEL_HEIGHT);
            jButton.setLocation(x, y);
        }
        startX = (int) (((double) startX / PANEL_WIDTH_OLD) * PANEL_WIDTH);
        startY = (int) (((double) startY / PANEL_HEIGHT_OLD) * PANEL_HEIGHT);

        if (answerField != null) {
            x = (int) (((double) answerField.getX() / PANEL_WIDTH_OLD) * PANEL_WIDTH);
            y = (int) (((double) answerField.getY() / PANEL_HEIGHT_OLD) * PANEL_HEIGHT);
            answerField.setLocation(x, y);
        }
        if (chessPuzzle != null) {
            x = (int) (((double) chessPuzzle.getWidth(this) / PANEL_WIDTH_OLD) * PANEL_WIDTH);
            y = (int) (((double) chessPuzzle.getHeight(this) / PANEL_HEIGHT_OLD) * PANEL_HEIGHT);
            chessPuzzle = chessPuzzle.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        }
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
        layoutSetting(true);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension newSize = getSize();
                if (newSize.width != PANEL_WIDTH_OLD || newSize.height != PANEL_HEIGHT_OLD) {
                    resizeGame(newSize.width, newSize.height);
                }
            }
        });
    }

    public void layoutSetting(boolean gameRestart) {
        if (gameRestart) {
            ps.setStamina(0);
            this.removeAll();
            playerIsDone = false;
        }
        this.setLayout(null);
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        switch (mf.getCurrentFloor()) {
            case 1 -> my1floorPanel();
            case 2 -> my2floorPanel();
            case 3 -> my3floorPanel();
            case 4 -> my4floorPanel();
            //case 5 -> my5floorPanel();
        }
    }

    private void resizeGame(int width, int height) {
        PANEL_WIDTH = width;
        PANEL_HEIGHT = height;
        moveButtons();
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

    private void createButton(String text, int x, int y, JButton button, boolean centeredX) {
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
        if (centeredX) {
            int xNew = centerAButtonX(button);
            button.setBounds(xNew, y, 85, 35);
        }
    }

    public Integer centerAButtonX(JButton button) {
        return (int) ((PANEL_WIDTH - button.getBounds().getWidth()) / 2);
    }

    public Integer centerATextFieldX(JTextField textField) {
        return (int) (PANEL_WIDTH - textField.getBounds().getWidth()) / 2;
    }

    public Integer setCenteredWidth(String string, int fontSize) {
        myFont = new Font("Arial", Font.BOLD, fontSize);
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
        Font myFont = new Font("Arial", Font.BOLD, 12);
        Font myEndFont = new Font("Arial", Font.PLAIN, 50);

        if (chessPuzzle != null) {
            this.removeAll();
            g2D.setFont(myFont);
            g2D.setColor(Color.ORANGE);
            g2D.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT - (5 * PANEL_HEIGHT / 6));
            g2D.setColor(Color.BLACK);
            xCenteredValue = setCenteredWidth(rulesForChessPuzzles(), 12);
            g2D.drawString(rulesForChessPuzzles(), xCenteredValue, PANEL_HEIGHT / 40);
            g2D.setFont(myFont1);
            g2D.setColor(Color.DARK_GRAY);
            xCenteredValue = setCenteredWidth(riddle, 20);
            g2D.drawString(flexibleString, xCenteredValue, PANEL_HEIGHT / 9);
            g2D.drawImage(chessPuzzle, (PANEL_WIDTH - chessPuzzle.getWidth(this)) / 2, PANEL_HEIGHT / 7, null);
            createSubmitButtonAndTextField();
        } else if (riddle != null && !lastRiddle) {
            this.removeAll();
            g2D.setFont(myFont1);
            xCenteredValue = setCenteredWidth(riddle, 20);
            g2D.drawString(flexibleString, xCenteredValue, 55);
            createSubmitButtonAndTextField();
        } else if (solvingRn) {
            this.removeAll();
            g2D.drawImage(backgroundImg, 0, 0, null);
            waiting(2799);
            solvingRn = false;
        }else if(lastRiddle){
            this.removeAll();
            g2D.setFont(myFont1);
            g2D.drawImage(backgroundImg, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, this);
            g2D.drawString(riddles.get(0).getRiddle(), 0, PANEL_HEIGHT/2);
            createSubmitButtonAndTextField();
        } else if (playerIsDone) {
            this.removeAll();
            g2D.setFont(myEndFont);
            g2D.setColor(Color.BLACK);
            g2D.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
            g2D.setColor(Color.green);
            xCenteredValue = setCenteredWidth(endMessageString(), 40);
            g2D.drawString(endMessageString(), xCenteredValue, PANEL_HEIGHT / 2);
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
        }
        layoutSetting(false);
    }

    public void endTheGame() {
        playerIsDone = true;
        this.removeAll();
        buttonsAr.clear();
        repaint(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setCenteredWidth("Restart", 12);
        createButton("Restart", 0, PANEL_HEIGHT * 2 / 3, restart, true);
        buttonsAr.add(restart);
        addButtonsToFrame();
    }

    public void animate(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        timerSetting(animationDuration / numFrames, false);
        timer.start();
        ps.setStamina(ps.getStamina() + 5);
    }

    public String rulesForChessPuzzles() {
        return "Rules: In chess puzzles you need to enter either " +
                "the figure that moves and the placement where it would move, or the " +
                "number of moves that it would take to win";
    }

    public String endMessageString() {
        return "You LOST!";
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
        riddle = puzzles.get(randomIndex).getQuestion();
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
    public void getUltimateRiddle(){
        riddles.clear();
        loadUltimateRiddle();
        createBackground("ChessPuzzleImg/finalBackground.jpg");
            riddle = riddles.get(0).getRiddle();
            answer = riddles.get(0).getAnswer();
        lastRiddle = true;
        repaint();
    }

    public void loadRiddle() {
        riddles.add(new Riddles("What is something that you earn, but can also save and spend?", "money"));
        riddles.add(new Riddles("What is something that can grow over time, but needs to be managed carefully?", "investments"));
        riddles.add(new Riddles("What is something that can protect you from unexpected financial emergencies?", "insurance"));
        riddles.add(new Riddles("What is something that you must do regularly to keep track of your income and expenses?", "budgeting"));
        riddles.add(new Riddles("What is something that can help you achieve your financial goals, but requires patience and discipline?", "saving"));
    }
    public void loadUltimateRiddle(){
        riddles.add(new Riddles(theUltimateRiddle, "24"));
    }

    public boolean check() {
        if (ps.getStamina() > 75) {
            endTheGame();
        }
        return ps.getStamina() > 75;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int deltaX = 0;
        int deltaY = 0;
        if (e.getSource() == leftDoor) {
            if (check()) return;
            actionWhere = "leftDoor";
            animate(startX, startY, leftDoor.getX(), leftDoor.getY());
        } else if (e.getSource() == rightDoor) {
            if (check()) return;
            actionWhere = "rightDoor";
            animate(startX, startY, rightDoor.getX(), rightDoor.getY());
        } else if (e.getSource() == leftVent) {
            if (check()) return;
            actionWhere = "leftVent";
            animate(startX, startY, leftVent.getX(), leftVent.getY());
        } else if (e.getSource() == rightVent) {
            if (check()) return;
            actionWhere = "rightVent";
            animate(startX, startY, rightVent.getX(), rightVent.getY());
        } else if (e.getSource() == restart) {
            actionWhere = "restartButton";
            layoutSetting(true);
            return;
        } else if (e.getSource() == centerDoor) {
            if (check()) return;
            actionWhere = "centerDoor";
            animate(startX, startY, centerDoor.getX(), centerDoor.getY());
        }else if(e.getSource() == lastQuest){
            if(check()) return;
            actionWhere = "lastQuest";
            animate(startX, startY, lastQuest.getX(), lastQuest.getY());
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
            if(Objects.equals(actionWhere, "lastQuest")){
                getUltimateRiddle();
                return;
            }
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
        answerField.setSize(120, 40);
        xNew = centerATextFieldX(answerField);
        answerField.setBounds(xNew, PANEL_HEIGHT - 100, 120, 40);
        answerField.setVisible(true);
        submitButton = new JButton();
        submitButton.setBackground(Color.BLACK);
        createButton("Submit", 0, PANEL_HEIGHT - 45, submitButton, true);
        buttonsAr.add(submitButton);
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
                lastRiddle = false;
                repaint();
            } else {
                this.removeAll();
                createBackground(incorrectAr.get(randomIndex));
                solved = false;
                chessPuzzle = null;
                riddle = null;
                lastRiddle = false;
                repaint();
            }
        });
    }
}