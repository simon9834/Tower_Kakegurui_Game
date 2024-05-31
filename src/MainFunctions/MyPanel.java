package MainFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class MyPanel extends JPanel implements ActionListener {
    private JButton rightVent = new JButton(),
            leftVent = new JButton(), rightDoor = new JButton(),
            leftPlayer = new JButton(), rightPlayer = new JButton(),
            middlePlayer = new JButton(), leftDoor = new JButton(),
            centerDoor = new JButton(), lastQuest = new JButton();
    private JTextField answerField;
    private JButton submitButton;
    private boolean solvingRn, solved, playerIsDone, lastRiddle, puzzle,
            displayWall,
            wallDone = false;
    private Font myFont = new Font("Arial", Font.BOLD, 8);
    private int PANEL_WIDTH = 1000;
    private int PANEL_WIDTH_OLD = 1000;
    private int PANEL_HEIGHT_OLD = 600;
    private int PANEL_HEIGHT = 600;
    private final int animationDuration = 1000;
    private int oneTime = 0, wallCount = 1;
    private String filePath;
    private ArrayList<Additions.Riddles> riddles = new ArrayList<>();
    private ArrayList<Additions.Puzzles> puzzles = new ArrayList<>();
    private ArrayList<String> correctAr = new ArrayList<>();
    private ArrayList<String> incorrectAr = new ArrayList<>();
    private ArrayList<JButton> buttonsAr = new ArrayList<>();
    private String actionWhere, theUltimateRiddle = """
            A man has 20 pairs of socks in his drawer:\n
            6 blue pairs, 9 black pairs and 5 red pairs.\n 
            The lights are out and he is completely in the dark.\n
            How many socks must he take out to make 100 percent certain \n
            he has at least one pair of black socks?
            """;

    private Image playerImg,
            backgroundImg, chessPuzzle;
    private Timer timer;
    private int endX, endY, randomIndex, startX = 700, startY = 500, xCenteredValue;
    private String riddle, answer, flexibleString;
    private Additions.PlayerStats ps = new Additions.PlayerStats();
    private int numFrames = 75 - ps.getStamina();
    private MainFunctions mf = new MainFunctions();
    private Random rd;
    private StackTraceElement caller;
    private Additions.MusicPlayer mp = new Additions.MusicPlayer();

    /**
     * this method creates a setup for the first floor which is:
     * creating 5 buttons
     * adding them to arrayList of buttons
     * adding all the buttons to my panel
     * creating an image background later used in componentPaint method
     * creates a player image later used in componentPaint method
     * checks who called this method to be sure if I should reassign the value of PANEL_WIDTH_OLD and PANEL_HEIGHT_OLD
     */
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
        addButtonsToMyPanel();
        createBackground("Floors/1stFloor.jpg");
        createPlayer(filePath);
        checkCaller("layoutSetting");
        this.setVisible(true);
        repaint();
    }

    /**
     *  this method creates a setup for the second floor which is:
     *  creating 4 buttons
     *  adding them to arrayList of buttons
     *  adding all the buttons to my panel
     *  creating an image background later used in componentPaint method
     *  creates a player image later used in componentPaint method
     *  checks who called this method to be sure if I should reassign the value of PANEL_WIDTH_OLD and PANEL_HEIGHT_OLD
     */
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
        addButtonsToMyPanel();
        createBackground("Floors/2stFloor.png");
        createPlayer(filePath);
        checkCaller("layoutSetting");
        this.setVisible(true);
        repaint();
    }
    /**
     *  this method creates a setup for the third floor which is:
     *  creating 4 buttons
     *  adding them to arrayList of buttons
     *  adding all the buttons to my panel
     *  creating an image background later used in componentPaint method
     *  creates a player image later used in componentPaint method
     *  checks who called this method to be sure if I should reassign the value of PANEL_WIDTH_OLD and PANEL_HEIGHT_OLD
     */
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
        addButtonsToMyPanel();
        createBackground("Floors/3thFloor.png");
        createPlayer(filePath);
        checkCaller("layoutSetting");
        this.setVisible(true);
        repaint();
    }
    /**
     *  this method creates a setup for the fourth/final floor which is:
     *  creating 1 button
     *  adding it to the arrayList of buttons
     *  adding the button to my panel
     *  creating an image background later used in componentPaint method
     *  creates a player image later used in componentPaint method
     *  checks who called this method to be sure if i should reassign the value of PANEL_WIDTH_OLD and PANEL_HEIGHT_OLD
     */
    public void my4floorPanel() {
        buttonsAr.clear();
        createButton("LastRiddle", 460, 438, lastQuest, false);
        buttonsAr.add(lastQuest);
        addButtonsToMyPanel();
        createBackground("Floors/4thFloor.png");
        createPlayer(filePath);
        checkCaller("layoutSetting");
        this.setVisible(true);
        repaint();
    }

    /**
     * this method checks the caller of the method that called this method
     * other words if i call this method from wherever il know who called the method that is on rn
     * @param methodName for what method should i look for when searching for the previous method
     *                   based on comparing the method names it does recalculate() change buttons pos without changing PANEL_WIDTH_OLD and PANEL_HEIGHT_OLD
     *                   or moveButtons() which recalculates the buttons while changing the PANEL_WIDTH_OLD and PANEL_HEIGHT_OLD
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

    /**
     * this method changes all the buttons positions in the arrayList without changing the PANEL_WIDTH_OLD and PANEL_HEIGHT_OLD
     */
    public void recalculate() {
        int x;
        int y;
        for (JButton jButton : buttonsAr) {
            x = (int) (((double) jButton.getX() / 1000) * PANEL_WIDTH);
            y = (int) (((double) jButton.getY() / 600) * PANEL_HEIGHT);
            jButton.setLocation(x, y);
        }
    }

    /**
     * this method adds all the buttons from my buttons arrayList to myPanel
     */
    public void addButtonsToMyPanel() {
            int i = 0;
            for (JButton ignored : buttonsAr) {
                this.add(buttonsAr.get(i));
                buttonsAr.get(i).setLocation(buttonsAr.get(i).getX(), buttonsAr.get(i).getY());
                i++;
            }
    }

    /**
     * this method moves all the buttons from button arrayList, moves answerField, moves chessPuzzle image and changes PANEL_WIDTH_OLD and PANEL_HEIGHT_OLD
     * it changes its positions based on resizing the panel (PANEL_WIDTH and PANEL_HEIGHT)
     */
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

    /**
     *
     * @param filePath
     */
    public void createPlayer(String filePath) {
        playerImg = new ImageIcon(filePath).getImage();
        playerImg = playerImg.getScaledInstance(52, 52, Image.SCALE_SMOOTH);
        playerImg = new ImageIcon(playerImg).getImage();
    }

    public void createBackground(String filePath) {
        backgroundImg = new ImageIcon(filePath).getImage();
        backgroundImg = backgroundImg.getScaledInstance(PANEL_WIDTH, PANEL_HEIGHT, Image.SCALE_SMOOTH);
        backgroundImg = new ImageIcon(backgroundImg).getImage();
    }
    public void loadMusicPlayer(){
        mp.load("bckgroundMusic","music/backgroundElevator.wav");
        mp.load("failMusic", "music/SpongebobFail.wav");
        mp.load("winMusic", "music/victory.wav");
        mp.load("thinkingMusic", "music/questionThinking.wav");
        mp.load("susFailMusic", "music/Huh.wav");
    }

    public MyPanel() {
        loadMusicPlayer();
        this.setLayout(null);
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension newSize = getSize();
                if (newSize.width != PANEL_WIDTH_OLD || newSize.height != PANEL_HEIGHT_OLD) {
                    resizeGame(newSize.width, newSize.height);
                }
            }
        });
        myOneTimePlayerChange();
    }
    public void start(){
        fillCorrectIncorrect();
        layoutSetting();
    }
    public void myOneTimePlayerChange(){
        buttonsAr.clear();
        createButton("Girl, left", PANEL_WIDTH*2/6 + 32 - 80, PANEL_HEIGHT*2/3, leftPlayer, false);
        createButton("Boy, mid", PANEL_WIDTH*3/6 + 32 - 80, PANEL_HEIGHT*2/3, middlePlayer, false);
        createButton("Vlc, right", PANEL_WIDTH*4/6 + 32 - 80, PANEL_HEIGHT*2/3, rightPlayer, false);
        buttonsAr.add(leftPlayer);
        buttonsAr.add(middlePlayer);
        buttonsAr.add(rightPlayer);
        addButtonsToMyPanel();
        checkCaller("layoutSetting");
        this.setVisible(true);
        repaint();
    }

    public void layoutSetting() {
        this.removeAll();
        mp.play("bckgroundMusic");
        switch (mf.getCurrentFloor()) {
            case 1 -> my1floorPanel();
            case 2 -> my2floorPanel();
            case 3 -> my3floorPanel();
            case 4 -> my4floorPanel();
        }
    }

    private void resizeGame(int width, int height) {
        PANEL_WIDTH = width;
        PANEL_HEIGHT = height;
        moveButtons();
        repaint();
    }

    public void timerSetting(int milliSecs) {
            timer = new Timer(milliSecs, this);
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
    public void playerPick(String fileName){
        playerImg = new ImageIcon(fileName).getImage();
        playerImg = playerImg.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        playerImg = new ImageIcon(playerImg).getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2D = (Graphics2D) g;
        Font myFont1 = new Font("Arial", Font.BOLD, 20);
        Font myFont = new Font("Arial", Font.BOLD, 12);
        Font myEndFont = new Font("Arial", Font.PLAIN, 50);
        if(oneTime == 0){
            xCenteredValue = setCenteredWidth("Pick a playerIcon", 20);
            g2D.setFont(myFont1);
            g2D.drawString("Pick a playerIcon", xCenteredValue, PANEL_HEIGHT/10);
            playerPick("Players/girlPlayer.png");
            g2D.drawImage(playerImg, PANEL_WIDTH*2/6-80, PANEL_HEIGHT/3, this);
            playerPick("Players/playerIcon.png");
            g2D.drawImage(playerImg, PANEL_WIDTH*3/6-80, PANEL_HEIGHT/3, this);
            playerPick("Players/vlcPlayer.png");
            g2D.drawImage(playerImg, PANEL_WIDTH*4/6-80, PANEL_HEIGHT/3, this);
        }else if ((chessPuzzle != null || riddle != null) && !lastRiddle) {
            this.removeAll();
            g2D.setFont(myFont1);
            g2D.setColor(Color.ORANGE);
            g2D.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT - (5 * PANEL_HEIGHT / 6));
            g2D.setColor(Color.BLACK);
            xCenteredValue = setCenteredWidth(riddle, 20);
            g2D.drawString(flexibleString, xCenteredValue, 55);
            g2D.drawImage(chessPuzzle, (PANEL_WIDTH - chessPuzzle.getWidth(this)) / 2, PANEL_HEIGHT / 7, null);
            createSubmitButtonAndTextField();
            if (puzzle) {
                g2D.setFont(myFont);
                xCenteredValue = setCenteredWidth(rulesForChessPuzzles(), 12);
                g2D.drawString(rulesForChessPuzzles(), xCenteredValue, PANEL_HEIGHT / 40);
            }
        } else if (displayWall) {
            this.removeAll();
            g2D.drawImage(backgroundImg, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, this);
            pauseExecution(4000);
            wallDone = true;
        } else if (solvingRn && !lastRiddle) {
            g2D.drawImage(backgroundImg, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, this);
            actionWhere = null;
            pauseExecution(6000);
        } else if (lastRiddle) {
            this.removeAll();
            g2D.drawImage(backgroundImg, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, this);
            g2D.setFont(myFont1);
            g2D.setColor(Color.white);
            String[] lines = riddle.split("\n");
            int lineHeight = g2D.getFontMetrics().getHeight();
            int y = 50;
            for (String line : lines) {
                g2D.drawString(line, PANEL_WIDTH / 100, y);
                y += lineHeight;
            }
            createSubmitButtonAndTextField();
        } else if (playerIsDone) {
            g2D.setFont(myEndFont);
            g2D.setColor(Color.BLACK);
            g2D.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
            g2D.setColor(Color.green);
            xCenteredValue = setCenteredWidth(endMessageString(), 40);
            g2D.drawString(endMessageString(), xCenteredValue, PANEL_HEIGHT / 2);
        } else {
            g2D.drawImage(backgroundImg, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, this);
            if(playerImg != null) {
                g2D.drawString("You", startX + 13, startY - 5);
                g2D.drawImage(playerImg, startX, startY, null);
            }
        }
    }

    public Integer centerAString(int pixelLength) {
        return (PANEL_WIDTH - pixelLength) / 2;
    }

    public void nextFloorOrBackToFloor() {
        if(Objects.equals(actionWhere, "centerDoor") && !wallDone){
            mp.stop("thinkingMusic");
            isWall();
            if(displayWall){
                repaint();
                return;
            }else{
                mf.setCurrentFloor(4);
            }
        }
        wallDone = false;
        displayWall = false;
        if (solved) {
            mf.setCurrentFloor(mf.getCurrentFloor() + 1);
        }
        wallCount++;
        layoutSetting();
    }

    public void animate(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        timerSetting(animationDuration / numFrames);
        timer.start();
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
        puzzles.add(new Additions.Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle1.png").getImage(), "where will you get material advantage (white on turn)", "Rf7"));
        puzzles.add(new Additions.Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle2.png").getImage(), "where will you get material advantage threatening a mate (black on turn)", "Dh5"));
        puzzles.add(new Additions.Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle3.png").getImage(), "how many moves till a mate? (white on turn)", "2"));
        puzzles.add(new Additions.Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle4.png").getImage(), "whats the best move here? (black on turn)", "Dh4"));
        puzzles.add(new Additions.Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle5.png").getImage(), "where will you move the bishop? (black on turn)", "Bb2"));
        puzzles.add(new Additions.Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle6.png").getImage(), "how many moves till mate? (white on move)", "2"));
        puzzles.add(new Additions.Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle7.png").getImage(), "how many moves till mate? (white on move)", "2"));
        puzzles.add(new Additions.Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle8.png").getImage(), "can you take the knight on e4 without any further problems? (yes/no)", "yes"));
    }

    public void getChessPuzzle() {
        rd = new Random();
        loadChessPuzzle();
        puzzle = true;
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
        puzzle = false;
        chessPuzzle = new ImageIcon("AdditionalPics/sherlock.png").getImage();
        randomIndex = rd.nextInt(riddles.size() - 1);
        riddle = riddles.get(randomIndex).getRiddle();
        answer = riddles.get(randomIndex).getAnswer();
        repaint();
    }

    public void getUltimateRiddle() {
        riddles.clear();
        loadUltimateRiddle();
        createBackground("ChessPuzzleImg\\finalBackground.jpg");
        riddle = riddles.get(0).getRiddle();
        answer = riddles.get(0).getAnswer();
        lastRiddle = true;
        repaint();
    }

    public void loadRiddle() {
        riddles.add(new Additions.Riddles("What is something that you earn, but can also save and spend?", "money"));
        riddles.add(new Additions.Riddles("What is something that can grow over time, but needs to be managed carefully?", "investments"));
        riddles.add(new Additions.Riddles("What is something that can protect you from unexpected financial emergencies?", "insurance"));
        riddles.add(new Additions.Riddles("What is something that you must do regularly to keep track of your income and expenses?", "budgeting"));
        riddles.add(new Additions.Riddles("What is something that can help you achieve your financial goals, but requires patience and discipline?", "saving"));
    }

    public void loadUltimateRiddle() {
        riddles.add(new Additions.Riddles(theUltimateRiddle, "24"));
    }

    public boolean check() {
        if (ps.getStamina() >= 70) {
            mp.stop("bckgroundMusic");
            mp.play("susFailMusic");
            this.removeAll();
            playerIsDone = true;
            repaint();
        }
        ps.setStamina(ps.getStamina()+3);
        return ps.getStamina() >= 70;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int deltaX = 0;
        int deltaY = 0;
        if (e.getSource() == leftDoor) {
            if (check()) return;
            actionWhere = "leftDoor";
            animate(startX, startY, leftDoor.getX(), leftDoor.getY());
        } else if(e.getSource() == leftPlayer){
            filePath = "Players/girlPlayer.png";
            start();
            oneTime++;
            return;
        } else if(e.getSource() == middlePlayer){
            filePath = "Players/playerIcon.png";
            start();
            oneTime++;
            return;
        } else if(e.getSource() == rightPlayer){
            filePath = "Players/vlcPlayer.png";
            start();
            oneTime++;
            return;
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
        } else if (e.getSource() == centerDoor) {
            if (check()) return;
            actionWhere = "centerDoor";
            animate(startX, startY, centerDoor.getX(), centerDoor.getY());
        } else if (e.getSource() == lastQuest) {
            if (check()) return;
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
            timer.stop();
            mp.stop("bckgroundMusic");
            mp.play("thinkingMusic");
            Random random = new Random();
            boolean randomBoolean = random.nextBoolean();
            if (Objects.equals(actionWhere, "lastQuest")) {
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
    public void isWall(){
        if(!((wallCount %5) == 0)){
            displayWall = true;
            createBackground("AdditionalPics/wall.jpg");
            mp.stop("bckgroundMusic");
            mp.play("susFailMusic");
        }else{
            displayWall = false;
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
            chessPuzzle = null;
            puzzle = false;
            riddle = null;
            lastRiddle = false;
            if(Objects.equals(actionWhere, "centerDoor")){//might cause problems?
                nextFloorOrBackToFloor();
                return;
            }
            if (Objects.equals(this.answer.toLowerCase().replaceAll("\\s+", ""), answer.toLowerCase().replaceAll("\\s+", ""))) {
                this.removeAll();
                mp.stop("thinkingMusic");
                mp.play("winMusic");
                createBackground(correctAr.get(randomIndex));
                if (Objects.equals(actionWhere, "lastQuest")) {
                    createBackground("AdditionalPics/victory.jpeg");
                    playerImg = null;
                    repaint();
                    return;
                }

                solved = true;
                solvingRn = true;
                repaint();
            } else {
                this.removeAll();
                mp.stop("thinkingMusic");
                mp.play("failMusic");
                createBackground(incorrectAr.get(randomIndex));
                if (Objects.equals(actionWhere, "lastQuest")) {
                    createBackground("AdditionalPics/defeat.jpeg");
                    playerImg = null;
                    repaint();
                    return;
                }
                solved = false;
                solvingRn = true;
                repaint();
            }
        });
    }
    public void pauseExecution(int milliseconds) {
        Timer timer = new Timer(milliseconds, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mp.stop("winMusic");
                mp.stop("failMusic");
                mp.stop("susFailMusic");
                nextFloorOrBackToFloor();
                solvingRn = false;
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    public JButton getRightDoor() {
        return rightDoor;
    }

    public JButton getLeftDoor() {
        return leftDoor;
    }

    public JButton getCenterDoor() {
        return centerDoor;
    }
}