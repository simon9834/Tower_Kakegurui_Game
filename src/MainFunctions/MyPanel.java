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
    private Graphics2D g2D;
    private Additions.MusicPlayer mp = new Additions.MusicPlayer();

    /**
     * this method creates a setup for the first floor which is:
     * creating five buttons.
     * adding them to arrayList of buttons.
     * adding all the buttons to my panel.
     * creating an image background later used in componentPaint method.
     * creates a player image later used in componentPaint method.
     * check who called this method to be sure if I should reassign the value of PANEL_WIDTH_OLD and PANEL_HEIGHT_OLD.
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
     * this method creates a setup for the second floor which is:
     * creating four buttons.
     * adding them to arrayList of buttons.
     * adding all the buttons to my panel.
     * creating an image background later used in componentPaint method.
     * creates a player image later used in componentPaint method.
     * check who called this method to be sure if I should reassign the value of PANEL_WIDTH_OLD and PANEL_HEIGHT_OLD.
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
     * this method creates a setup for the third floor which is:
     * creating four buttons.
     * adding them to arrayList of buttons.
     * adding all the buttons to my panel.
     * creating an image background later used in componentPaint method.
     * creates a player image later used in componentPaint method.
     * check who called this method to be sure if I should reassign the value of PANEL_WIDTH_OLD and PANEL_HEIGHT_OLD.
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
     * this method creates a setup for the fourth/final floor which is:
     * creating one button.
     * adding it to the arrayList of buttons.
     * add the button to my panel.
     * creating an image background later used in componentPaint method.
     * creates a player image later used in componentPaint method.
     * check who called this method to be sure if I should reassign the value of PANEL_WIDTH_OLD and PANEL_HEIGHT_OLD.
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
     * this method checks the caller of the method that called this method.
     * in other words, if I call this method from wherever il know who called the method that is on rn.
     *
     * @param methodName for what method should I look for when searching for the previous method
     *                   based on comparing the method names, it does recalculate() change buttons pos without changing PANEL_WIDTH_OLD and PANEL_HEIGHT_OLD
     *                   or moveButtons() which recalculates the buttons while changing the PANEL_WIDTH_OLD and PANEL_HEIGHT_OLD.
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
     * this method changes all the button positions in the arrayList
     * without changing the PANEL_WIDTH_OLD and PANEL_HEIGHT_OLD.
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
     * this method adds all the buttons from my buttons arrayList to myPanel.
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
     * this method moves all the buttons from button arrayList, moves answerField,
     * moves chessPuzzle image and changes PANEL_WIDTH_OLD and PANEL_HEIGHT_OLD.
     * it changes its positions based on resizing the panel (PANEL_WIDTH and PANEL_HEIGHT).
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
     * this method creates an image called playerIcon based on the path that is passed in to the method.
     *
     * @param filePath a filepath that is used to find the right image that should be in an instance called playerImg.
     */
    public void createPlayer(String filePath) {
        playerImg = new ImageIcon(filePath).getImage();
        playerImg = playerImg.getScaledInstance(52, 52, Image.SCALE_SMOOTH);
        playerImg = new ImageIcon(playerImg).getImage();
    }

    /**
     * this method creates an image called backgroundImg based on the path that is passed in to the method.
     *
     * @param filePath a filepath that is used to find the right image that should be in an instance called backgroundImg.
     */
    public void createBackground(String filePath) {
        backgroundImg = new ImageIcon(filePath).getImage();
        backgroundImg = backgroundImg.getScaledInstance(PANEL_WIDTH, PANEL_HEIGHT, Image.SCALE_SMOOTH);
        backgroundImg = new ImageIcon(backgroundImg).getImage();
    }

    /**
     * this method calls MusicPlayer class and loads all the music going to be played in the game.
     */
    public void loadMusicPlayer() {
        mp.load("bckgroundMusic", "music/backgroundElevator.wav");
        mp.load("failMusic", "music/SpongebobFail.wav");
        mp.load("winMusic", "music/victory.wav");
        mp.load("thinkingMusic", "music/questionThinking.wav");
        mp.load("susFailMusic", "music/Huh.wav");
    }

    /**
     * this is the constructor of myPanel class, and it sets the basics
     * for the panel and adds componentResized method.
     */
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

    /**
     * this method is just to load images in to an arrayList and to call layoutSetting for further settings.
     */
    public void start() {
        fillCorrectIncorrect();
        loadChessPuzzle();
        loadRiddle();
        layoutSetting();
    }

    /**
     * This method creates three buttons that are being added to the buttons arrayList and
     * adds them to the panel.
     * Also check for the caller to make sure (when resizing myPanel)
     * that the position values are being set rightly.
     * And calls repaint to paint the icons (playerIcons).
     */
    public void myOneTimePlayerChange() {
        buttonsAr.clear();
        createButton("Girl, left", PANEL_WIDTH * 2 / 6 + 32 - 80, PANEL_HEIGHT * 2 / 3, leftPlayer, false);
        createButton("Boy, mid", PANEL_WIDTH * 3 / 6 + 32 - 80, PANEL_HEIGHT * 2 / 3, middlePlayer, false);
        createButton("Vlc, right", PANEL_WIDTH * 4 / 6 + 32 - 80, PANEL_HEIGHT * 2 / 3, rightPlayer, false);
        buttonsAr.add(leftPlayer);
        buttonsAr.add(middlePlayer);
        buttonsAr.add(rightPlayer);
        addButtonsToMyPanel();
        checkCaller("layoutSetting");
        this.setVisible(true);
        repaint();
    }

    /**
     * This method for being sure removes everything left from the former settings
     * and decides what floor will be the next one set and displayed.
     * Plays the background music.
     */
    public void layoutSetting() {
        this.removeAll();
        mp.play("bckgroundMusic");
        switch (mf.getCurrentFloor()) {
            case 1 -> my1floorPanel();
            case 2 -> my2floorPanel();
            case 3 -> my3floorPanel();
            case 4 -> my4floorPanel();
            default -> System.out.println("all the floors didnt fit you in ig");
        }
    }

    /**
     * this method calls methods for resizing background, buttons, playerIcon,
     * submit button and text field when the panel size is changed.
     *
     * @param width  sets the new width of the panel that's being stored in PANEL_WIDTH.
     * @param height sets the new height of the panel that's being stored in PANEL_HEIGHT.
     */
    private void resizeGame(int width, int height) {
        PANEL_WIDTH = width;
        PANEL_HEIGHT = height;
        moveButtons();
        repaint();
    }

    /**
     * this method sets a timer to new time.
     *
     * @param milliSecs the time that's the timer being set to.
     */
    public void timerSetting(int milliSecs) {
        timer = new Timer(milliSecs, this);
    }

    /**
     * method for creating all the buttons easily.
     *
     * @param text      this is the text displayed on the button.
     * @param x         this is the x position that's being used if the centeredX is false.
     * @param y         this is the y position.
     * @param button    this is the button that's being created.
     * @param centeredX this calls a method for setting the x to be in the middle,
     *                  according to buttons length if true.
     *                  If false, nothing happens.
     */
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

    /**
     * this method is being used to center a buttons xPosition based on the panel width and buttons width.
     *
     * @param button that's the button that's being centered.
     * @return returns the position that should be right for the centered button.
     */
    public Integer centerAButtonX(JButton button) {
        return (int) ((PANEL_WIDTH - button.getBounds().getWidth()) / 2);
    }

    /**
     * this method is being used to center a text fields x position based on the panel width and text fields width.
     *
     * @param textField this is the text field that is being centered (by x).
     * @return returns the x value in the center (where the textField should be placed to be in the middle.
     */
    public Integer centerATextFieldX(JTextField textField) {
        return (int) (PANEL_WIDTH - textField.getBounds().getWidth()) / 2;
    }

    /**
     * this method resolves the length of a string in pixels and calculates
     * where the string should be placed to be in the middle (x position only)
     * thanks to the centerAString method.
     *
     * @param string   the string that's being measured.
     * @param fontSize the font size that's being currently used.
     * @return returns an x position where the string should be placed to be in the middle of the panel.
     */
    public Integer setCenteredWidth(String string, int fontSize) {
        myFont = new Font("Arial", Font.BOLD, fontSize);
        FontMetrics fm1 = g2D.getFontMetrics(myFont);
        flexibleString = string;
        myFont = new Font("Arial", Font.BOLD, 8);
        return centerAString(fm1.stringWidth(flexibleString));
    }


    /**
     * a special method used to do the same as createPlayer just with a bigger width and height.
     *
     * @param fileName the fileName that's deciding what picture will I use.
     */
    public void playerPick(String fileName) {
        playerImg = new ImageIcon(fileName).getImage();
        playerImg = playerImg.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        playerImg = new ImageIcon(playerImg).getImage();
    }

    /**
     * this method is used to create every visual thing in the game
     * except things like buttons and textField.
     *
     * @param g the <code>Graphics</code> object to protect this is the graphic tool that I use.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2D = (Graphics2D) g;
        Font myFont1 = new Font("Arial", Font.BOLD, 20);
        Font myFont = new Font("Arial", Font.BOLD, 12);
        if (oneTime == 0) {
            xCenteredValue = setCenteredWidth("Pick a playerIcon", 20);
            g2D.setFont(myFont1);
            g2D.drawString("Pick a playerIcon", xCenteredValue, PANEL_HEIGHT / 10);
            playerPick("Players/girlPlayer.png");
            g2D.drawImage(playerImg, PANEL_WIDTH * 2 / 6 - 80, PANEL_HEIGHT / 3, this);
            playerPick("Players/playerIcon.png");
            g2D.drawImage(playerImg, PANEL_WIDTH * 3 / 6 - 80, PANEL_HEIGHT / 3, this);
            playerPick("Players/vlcPlayer.png");
            g2D.drawImage(playerImg, PANEL_WIDTH * 4 / 6 - 80, PANEL_HEIGHT / 3, this);
        } else if ((chessPuzzle != null || riddle != null) && !lastRiddle) {
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
            wallDone = true;
            pauseExecution(2000, true);
        } else if (solvingRn && !lastRiddle) {
            g2D.drawImage(backgroundImg, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, this);
            actionWhere = null;
            pauseExecution(6000, true);
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
        } else {
            g2D.drawImage(backgroundImg, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, this);
            if (playerImg != null) {
                g2D.drawString("You", startX + 13, startY - 5);
                g2D.drawImage(playerImg, startX, startY, null);
            }
        }
    }

    /**
     * This method is a helping method for setCenteredWidth.
     * This method calculates what's the best position for the string to be placed,
     * to be in the center of the panel.
     *
     * @param pixelLength this is the pixel length of the string calculated.
     * @return this method returns the centered x position for the string.
     */
    public Integer centerAString(int pixelLength) {
        return (PANEL_WIDTH - pixelLength) / 2;
    }

    /**
     * This method is used to decide whether a player moves to the next floor
     * or stays at the floor he is rn.
     * Also, the method contains an if to decide
     * if the player should go to the fourth floor from the first floor.
     */
    public void nextFloorOrBackToFloor() {
        if (Objects.equals(actionWhere, "centerDoor") && !wallDone) {
            mp.stop("thinkingMusic");
            isWall();
            if (displayWall) {
                repaint();
                return;
            } else {
                if(solved){
                    mf.setCurrentFloor(4);
                }
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

    /**
     * this method sets all values used to move playerIcon and sets timer for a specified time.
     *
     * @param startX thi is the value x where the player icon is rn.
     * @param startY this is the value y where the player icon is rn.
     * @param endX   this is the value x where the player will be moving to.
     * @param endY   this is the value y where the player will be moving to.
     */
    public void animate(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        timerSetting(animationDuration / numFrames);
        timer.start();
    }

    /**
     * this method is used as a string rulesForChess.
     *
     * @return returns the string rulesForChess.
     */
    public String rulesForChessPuzzles() {
        return "Rules: In chess puzzles you need to enter either " +
                "the figure that moves and the placement where it would move, or the " +
                "number of moves that it would take to win";
    }

    /**
     * this method is used as a string coz why use normal strings right?
     *
     * @return returns the string.
     */
    public String endMessageString() {
        return "You LOST!";
    }

    /**
     * This method loads all the chess puzzles to a puzzle arrayList.
     * Here is the link to all the puzzles:
     * https://chessfox.com/chess-puzzles-for-intermediate-players/
     */
    public void loadChessPuzzle() {
        puzzles.add(new Additions.Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle1.png").getImage(), "where will you get material advantage (white on turn)", "Rf7"));
        puzzles.add(new Additions.Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle2.png").getImage(), "where will you get material advantage threatening a mate (black on turn)", "Dh5"));
        puzzles.add(new Additions.Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle3.png").getImage(), "how many moves till a mate? (white on turn)", "2"));
        puzzles.add(new Additions.Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle4.png").getImage(), "whats the best move here? (black on turn)", "Dh4"));
        puzzles.add(new Additions.Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle5.png").getImage(), "where will you move the bishop? (black on turn)", "Bb2"));
        puzzles.add(new Additions.Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle6.png").getImage(), "how many moves till mate? (white on move)", "2"));
        puzzles.add(new Additions.Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle7.png").getImage(), "how many moves till mate? (white on move)", "2"));
        puzzles.add(new Additions.Puzzles(new ImageIcon("ChessPuzzleImg/chessPuzzle8.png").getImage(), "can you take the knight on e4 without any further problems? (yes/no)", "yes"));
    }

    /**
     * This method reassigns the randomIndex to get a random chess puzzle from the arrayList
     * and set the riddle and answer based on the chosen puzzle.
     * This method calls repaint for repainting the puzzle
     */
    public void getChessPuzzle() {
        rd = new Random();
        puzzle = true;
        randomIndex = rd.nextInt(puzzles.size() - 1);
        chessPuzzle = puzzles.get(randomIndex).getImg().getScaledInstance(PANEL_HEIGHT - 200, PANEL_HEIGHT - 200, Image.SCALE_SMOOTH);
        chessPuzzle = new ImageIcon(chessPuzzle).getImage();
        riddle = puzzles.get(randomIndex).getQuestion();
        answer = puzzles.get(randomIndex).getAnswer();
        repaint();
    }

    /**
     * This method is used to get a riddle from the riddle arrayList
     * and set riddle and answer.
     * After that, I call repaint to paint all of this.
     */
    public void getRiddle() {
        rd = new Random();
        puzzle = false;
        chessPuzzle = new ImageIcon("AdditionalPics/sherlock.png").getImage();
        randomIndex = rd.nextInt(riddles.size() - 2);
        riddle = riddles.get(randomIndex).getRiddle();
        answer = riddles.get(randomIndex).getAnswer();
        repaint();
    }

    /**
     * This method is used to set the last and probably the hardest riddle.
     * Begins with setting riddle and answer.
     * After that, call the repaint to paint everything.
     */
    public void getUltimateRiddle() {
        createBackground("ChessPuzzleImg\\finalBackground.jpg");
        riddle = riddles.get(riddles.size() - 1).getRiddle();
        answer = riddles.get(riddles.size() - 1).getAnswer();
        lastRiddle = true;
        repaint();
    }

    /**
     * This method is used to load all the riddles to the arrayList.
     */
    public void loadRiddle() {
        riddles.add(new Additions.Riddles("What is something that you earn, but can also save and spend?", "money"));
        riddles.add(new Additions.Riddles("What is something that can grow over time, but needs to be managed carefully?", "investments"));
        riddles.add(new Additions.Riddles("What is something that can protect you from unexpected financial emergencies?", "insurance"));
        riddles.add(new Additions.Riddles("What is something that you must do regularly to keep track of your income and expenses?", "budgeting"));
        riddles.add(new Additions.Riddles("What is something that can help you achieve your financial goals, but requires patience and discipline?", "saving"));
        riddles.add(new Additions.Riddles(theUltimateRiddle, "24"));
    }

    /**
     * This method is used to check if the player has enough stamina to keep playing.
     * If a player doesn't have enough, the game will end.
     *
     * @return returns false if the players stamina is good
     */
    public boolean check() {
        ps.setStamina(ps.getStamina() + 1);
        return ps.getStamina() >= 70;
    }

    /**
     * This method listens to the mouse, and if the mouse clicks at any button displayed,
     * the button press will be processed here.
     *
     * @param e the event to be processed.
     *          At each button, there's some executable event.
     *          After the execution, there is an animation and deciding if
     *          the thing displayed will be a puzzle riddle or something else.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int deltaX = 0;
        int deltaY = 0;
        if (e.getSource() == leftDoor) {
            if (check()){
                System.exit(0);
            }
            actionWhere = "leftDoor";
            animate(startX, startY, leftDoor.getX(), leftDoor.getY());
        } else if (e.getSource() == leftPlayer) {
            filePath = "Players/girlPlayer.png";
            start();
            oneTime++;
            return;
        } else if (e.getSource() == middlePlayer) {
            filePath = "Players/playerIcon.png";
            start();
            oneTime++;
            return;
        } else if (e.getSource() == rightPlayer) {
            filePath = "Players/vlcPlayer.png";
            start();
            oneTime++;
            return;
        } else if (e.getSource() == rightDoor) {
            if (check()){
                System.exit(0);
            }
            actionWhere = "rightDoor";
            animate(startX, startY, rightDoor.getX(), rightDoor.getY());
        } else if (e.getSource() == leftVent) {
            if (check()){
                System.exit(0);
            }
            actionWhere = "leftVent";
            animate(startX, startY, leftVent.getX(), leftVent.getY());
        } else if (e.getSource() == rightVent) {
            if (check()){
                System.exit(0);
            }
            actionWhere = "rightVent";
            animate(startX, startY, rightVent.getX(), rightVent.getY());
        } else if (e.getSource() == centerDoor) {
            if (check()){
                System.exit(0);
            }
            actionWhere = "centerDoor";
            animate(startX, startY, centerDoor.getX(), centerDoor.getY());
        } else if (e.getSource() == lastQuest) {
            if (check()){
                System.exit(0);
            }
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

    /**
     * This method decides if it is the fifth tenth or fifteenth turn from the start,
     * to allow going to the last floor from the first one.
     * And stops the music that plays in the backgroundd and playes susFailMusic
     */
    public void isWall() {
        if (!((wallCount % 5) == 0)) {
            displayWall = true;
            createBackground("AdditionalPics/wall.jpg");
            mp.stop("bckgroundMusic");
            mp.play("susFailMusic");
        } else {
            displayWall = false;
        }
    }

    /**
     * This method is used to fill the correct and incorrect arrayList with images loaded from the file.
     */
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

    /**
     * This method creates a submitButton and a textField.
     * Also listens to the text field and decides whether the answer
     * that player provided is right or wrong.
     * If it is the last question or not and based
     * on that executes some event.
     */
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
            if (Objects.equals(actionWhere, "centerDoor")) {
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

    /**
     * this method is used to pause everything (except music) and then execute some event.
     *
     * @param milliseconds this is the time that the player will have to wait.
     */
    public void pauseExecution(int milliseconds, boolean goOn) {
        Timer timer = new Timer(milliseconds, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(goOn){
                    mp.stop("winMusic");
                    mp.stop("failMusic");
                    mp.stop("susFailMusic");
                    nextFloorOrBackToFloor();
                }
                solvingRn = false;
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * used for test
     *
     * @return returns rightDoor
     */
    public JButton getRightDoor() {
        return rightDoor;
    }

    /**
     * used for test
     *
     * @return returns leftDoor
     */
    public JButton getLeftDoor() {
        return leftDoor;
    }

    /**
     * used for test
     *
     * @return returns centerDoor
     */
    public JButton getCenterDoor() {
        return centerDoor;
    }
    public StackTraceElement getCaller(){
        return caller;
    }
    public boolean getSolvingRn(){
        return solvingRn;
    }
    public void setPanel_Width(int PANEL_WIDTH) {
        this.PANEL_WIDTH = PANEL_WIDTH;
    }
    public ArrayList getButtonsAr(){
        return buttonsAr;
    }
}