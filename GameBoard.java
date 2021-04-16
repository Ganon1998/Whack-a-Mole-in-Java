import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import javax.swing.Timer;


public class GameBoard extends JFrame {


    int counter = 0; //only redraw gameboard when it is 0;
    private static JLabel[] holeDesgin = new JLabel[9];
    ///////////////////// instantiate mouse ///////////////////////////////////

    JDesktopPane pane = new JDesktopPane();
    JPanel startMenu = new JPanel();
    JInternalFrame MoveablePFrame = new JInternalFrame("Moveable Picture Frame", true, true, true, true);
    JLabel scoreText = new JLabel("Score: ");
    JLabel timerText = new JLabel("Time: ");
    JLabel secondsText = new JLabel("20");
    JButton toStart = new JButton("Main Menu");
    JLabel header = new JLabel("Whack-A-Mole!");
    private static int nextSpotforMole;
    int location;
    public static int score;
    public static int BonusTimeModifier;
    public static ArrayList<Integer> ListOfScores = new ArrayList<>();

    private static Timer timer;

    int timerSecs = 20;
    boolean firstTime = true;
    private Object drawMole;
    Mole moleClass = new Mole();

    public static JLabel scoreNumText;

    class DrawGameBoardHoles extends JPanel {

        class MouseDrag extends MouseAdapter {
            private boolean clickedMole = false;
            private Point last;

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent m) {
                System.out.println("Mouse clicked");
                last = m.getPoint();

                // this will check if the mole has been clicked
                clickedMole = isInsideCell(m.getPoint());
                if (clickedMole == false) {
                    // decrement the score
                    GameBoard.score--;
                    GameBoard.BonusTimeModifier = 0;
                    GameBoard.scoreNumText.setText("" + GameBoard.score);
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            revalidate();
                            repaint();
                        }
                    });
                } else {
                    // increment the round as well as the score
                    GameBoard.score += 2 + GameBoard.BonusTimeModifier;
                    GameBoard.BonusTimeModifier = 0;
                    GameBoard.scoreNumText.setText("" + GameBoard.score);

                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            revalidate();
                            repaint();
                        }
                    });

                }
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent m) {
                last = null;
                clickedMole = false;
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        }

        private GameBoard.DrawGameBoardHoles.MouseDrag dragger;
        // private mouseDrag dragger;
        public DrawGameBoardHoles() {
            dragger = new GameBoard.DrawGameBoardHoles.MouseDrag();
            //setBackground(Color.WHITE);
            addMouseListener(dragger);
            addMouseMotionListener(dragger);
        }
        public void paintComponent(Graphics g) {
            setBackground(new Color(20, 80, 0));
            MoveablePFrame.add(scoreText);
            MoveablePFrame.add(scoreNumText);
            MoveablePFrame.add(timerText);
            MoveablePFrame.add(secondsText);

            scoreText.setAlignmentX(Component.CENTER_ALIGNMENT);
            scoreNumText.setAlignmentX(Component.CENTER_ALIGNMENT);
            timerText.setAlignmentX(Component.CENTER_ALIGNMENT);
            secondsText.setAlignmentX(Component.CENTER_ALIGNMENT);


            scoreText.setAlignmentY(Component.TOP_ALIGNMENT);
            scoreNumText.setAlignmentY(Component.TOP_ALIGNMENT);
            timerText.setAlignmentY(Component.TOP_ALIGNMENT);
            secondsText.setAlignmentY(Component.TOP_ALIGNMENT);


            toStart.setAlignmentX(Component.CENTER_ALIGNMENT);
            header.setAlignmentX(Component.CENTER_ALIGNMENT);
            MoveablePFrame.repaint();
            MoveablePFrame.revalidate();
            //REDRAW OVALS
            //super.paintComponent(g);
            //startMenu.removeAll();
            // revalidate();
            //repaint();


            //JLabel mole = new JLabel("mole");
            g.setColor(Color.RED);
            g.drawOval(50, 150, 100, 100);
            g.drawOval(250, 150, 100, 100);
            g.drawOval(450, 150, 100, 100);
            g.drawOval(50, 300, 100, 100);
            g.drawOval(250, 300, 100, 100);
            g.drawOval(450, 300, 100, 100);
            g.drawOval(50, 450, 100, 100);
            g.drawOval(250, 450, 100, 100);
            g.drawOval(450, 450, 100, 100);



        }



    }

    public GameBoard() {
        MoveablePFrame.setBounds(25,25,200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(150, 150, 660, 770); // set frame bounds
        setTitle("WHACK-A-MOLE");
        setVisible(true); // display frame
        //set menu panel


        //set buttons
        JButton startGame = new JButton("Start");
        JButton rules = new JButton("Rules");
        JButton leaderboard = new JButton("Leaderboard");
        JButton quit = new JButton("Quit");
        JButton startMoles = new JButton("Start Game");

        //create starting menu, this button will act as the "go to main menu button"

        startMenu.setBackground(new Color(0, 0, 0));
        startMenu.add(toStart);
        setContentPane(startMenu);


        //go to main menu when button pressed
        toStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                //   timerSecs = 20;
                //repaint panel
                startMenu.removeAll();
                revalidate();
                repaint();

                startMenu.setLayout(new BoxLayout(startMenu, BoxLayout.PAGE_AXIS));
                startMenu.setBackground(new Color(65, 45, 20));

                //set title header
                Font headFont = new Font(Font.SANS_SERIF, Font.BOLD, 50);

                header.setForeground(new Color(0, 0, 0));
                header.setFont(headFont);
                add(header);
                header.setAlignmentX(Component.CENTER_ALIGNMENT);


                //create an invisible components to space out the buttons
                Dimension minSize = new Dimension(10, 50);
                Dimension prefSize = new Dimension(10, 50);
                Dimension maxSize = new Dimension(Short.MAX_VALUE, 100);

                add(new Box.Filler(minSize, prefSize, maxSize));
                add(startGame);
                add(new Box.Filler(minSize, prefSize, maxSize));
                add(rules);
                add(new Box.Filler(minSize, prefSize, maxSize));
                add(leaderboard);
                add(new Box.Filler(minSize, prefSize, maxSize));
                add(quit);


                //Start game, this will draw the board
                startGame.addActionListener(new ActionListener() {

                    //  ClickGameBoard clicker = new ClickGameBoard();


                    @Override
                    public void actionPerformed(ActionEvent e) {

                        //repaint panel
                        startMenu.removeAll();
                        revalidate();
                        repaint();


                        //DISPLAY THE GAMEBOARD FOR THE FIRST TIME, BEFORE "START" IS CLICKED


                        //set the header font and text
                        Font headFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
                        JLabel header = new JLabel("Whack The Moles!!");
                        header.setForeground(new Color(0, 0, 0));
                        header.setFont(headFont);
                        add(header);
                        // header.setAlignmentX(Component.TOP_ALIGNMENT);

                        Font font = new Font(Font.DIALOG, Font.BOLD, 20);


                        scoreNumText = new JLabel("0");
                        timerSecs = 20;

                        scoreText.setForeground(Color.RED);
                        timerText.setForeground(Color.RED);
                        secondsText.setForeground(Color.RED);
                        scoreNumText.setForeground(Color.RED);

                        scoreText.setFont(font);
                        timerText.setFont(font);
                        secondsText.setFont(font);
                        scoreNumText.setFont(font);

                        add(scoreText);
                        add(scoreNumText);
                        add(timerText);
                        add(secondsText);


                        scoreText.setAlignmentX(Component.CENTER_ALIGNMENT);
                        scoreNumText.setAlignmentX(Component.CENTER_ALIGNMENT);
                        timerText.setAlignmentX(Component.CENTER_ALIGNMENT);
                        secondsText.setAlignmentX(Component.CENTER_ALIGNMENT);


                        scoreText.setAlignmentY(Component.TOP_ALIGNMENT);
                        scoreNumText.setAlignmentY(Component.TOP_ALIGNMENT);
                        timerText.setAlignmentY(Component.TOP_ALIGNMENT);
                        secondsText.setAlignmentY(Component.TOP_ALIGNMENT);


                        add(toStart);
                        add(startMoles);

                        toStart.setAlignmentX(Component.CENTER_ALIGNMENT);
                        startMoles.setAlignmentX(Component.CENTER_ALIGNMENT);
                        header.setAlignmentX(Component.CENTER_ALIGNMENT);




                        //WHEN "START" IS CLICKED, GO TO ACTION LISTENER AND START TIMER
                        startMoles.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                pane.remove(MoveablePFrame);

                                //holeDesgin[moleClass.startMole()].setIcon(moleClass.drawMole());
                                MoveablePFrame.setBackground(new Color(2, 80, 20));
                                setBackground(new Color(2, 80, 20));
                                MoveablePFrame.add(scoreText);
                                MoveablePFrame.add(scoreNumText);
                                MoveablePFrame.add(timerText);
                                MoveablePFrame.add(secondsText);
                                MoveablePFrame.setSize(700,700);

                                DrawGameBoardHoles gbHoles = new DrawGameBoardHoles();

                                MoveablePFrame.add(gbHoles);
                                MoveablePFrame.setVisible(true);

                                pane.add(MoveablePFrame);
                                setContentPane(pane);


                                timer.start();

                            }


                        });


                        //EVERY SECOND, REDRAW THE TIMER AND SCORE LABELS, WITH THE TIMER REDRAWN AS TIMERSECS-1
                        timer = new Timer(1000, new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {

                                for (int j=0; j<9;j++){
                                    if (holeDesgin[j]!=null){
                                        MoveablePFrame.remove(holeDesgin[j]);
                                    }

                                }
                                // MoveablePFrame.revalidate();
                                // MoveablePFrame.repaint();

                                //MoveablePFrame.removeAll();

                                // MoveablePFrame.revalidate();
                                // MoveablePFrame.repaint();

                              /*  for (int i = 0; i < 9; i++) {
                                    holeDesgin[i].addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            JLabel lbl = (JLabel) e.getSource();
                                            Point id = new Point();
                                            Point point = id.getLocation();
                                            ClickGameBoard mouse = new ClickGameBoard();
                                            mouse.getMousePosition();
                                        }
                                    });
                                }*/
                                for (int i = 0; i < 9; i++) {
                                    holeDesgin[i] = new JLabel();
                                    if(i==0){
                                        holeDesgin[i].setBounds(50,150,100,100);
                                    }
                                    if (i == 1) {
                                        holeDesgin[i].setBounds(250, 150, 100, 100);

                                    }
                                    if (i == 2) {
                                        holeDesgin[i].setBounds(450, 150, 100, 100);

                                    }
                                    if (i == 3) {
                                        holeDesgin[i].setBounds(50, 300, 100, 100);

                                    }
                                    if (i == 4) {
                                        holeDesgin[i].setBounds(250, 300, 100, 100);

                                    }
                                    if (i == 5) {
                                        holeDesgin[i].setBounds(450, 300, 100, 100);

                                    }
                                    if (i == 6) {
                                        holeDesgin[i].setBounds(50, 450, 100, 100);

                                    }
                                    if (i == 7) {
                                        holeDesgin[i].setBounds(250, 450, 100, 100);

                                    }

                                    if (i == 8) {
                                        holeDesgin[i].setBounds(480, 450, 100, 100);

                                    }


                                    MoveablePFrame.add(holeDesgin[i]);


                                }




                                holeDesgin[moleClass.startMole()].setIcon(moleClass.drawMole());

                                firstTime = false;
                                // MoveablePFrame.add(new JLabel(moleClass.drawMole()),holeDesgin[moleClass.startMole()]);


                                //holeDesgin[moleClass.startMole()].setIcon(moleClass.drawMole());

                                //add the drawgameboardholes class, this next block of code is what happens after the timer delays 1000 millisecs
                                //randomize the mole location each second


                                BonusTimeModifier++;


                                secondsText.setText("" + timerSecs--);

                                secondsText.setText("" + timerSecs);

                               /* MoveablePFrame.getContentPane().setBackground(new Color(2, 80, 20));
                                MoveablePFrame.add(scoreText);
                                MoveablePFrame.add(scoreNumText);
                                MoveablePFrame.add(timerText);
                                MoveablePFrame.add(secondsText);
                                MoveablePFrame.setSize(700,700);
                                DrawGameBoardHoles gbHoles = new DrawGameBoardHoles();
                                setContentPane(pane);
                                MoveablePFrame.add(gbHoles);
                                MoveablePFrame.setVisible(true);*/

                                // MoveablePFrame.removeAll();
                                MoveablePFrame.revalidate();
                                MoveablePFrame.repaint();

                                holeDesgin[GameBoard.getMole()].setIcon(null);
                                // MoveablePFrame.add((Component) moleClass.drawMole());






                                // creates score board screen
                                if (timerSecs == 0) {
                                    timer.stop();

                                    // insert current score into the array
                                    if (ListOfScores.size() <= 6)
                                        ListOfScores.add(score);
                                    else {
                                        // if there are more than 6 scores incoming, remove the oldest score
                                        ListOfScores.set(0, 0);
                                        for (int i = 0; i < ListOfScores.size() - 1; i++) {
                                            ListOfScores.set(i, ListOfScores.get(++i));
                                        }
                                        ListOfScores.set(ListOfScores.size() - 1, score);
                                    }

                                    //repaint screen

                                    MoveablePFrame.setVisible(false);

                                    startMenu.removeAll();
                                    revalidate();
                                    repaint();

                                    //set invisible spacing dimensions
                                    Dimension minSize = new Dimension(10, 600);
                                    Dimension prefSize = new Dimension(10, 600);
                                    Dimension maxSize = new Dimension(Short.MAX_VALUE, 600);

                                    startMenu.setBackground(new Color(100, 0, 0));

                                    //set header text
                                    Font headFont = new Font(Font.SANS_SERIF, Font.BOLD, 50);
                                    JLabel header = new JLabel("Leaderboard");
                                    header.setForeground(new Color(0, 0, 0));
                                    header.setFont(headFont);
                                    add(header);

                                    // adds information about scores

                                    for (int i = 0; i < ListOfScores.size(); i++) {

                                        JLabel scoreLabel = new JLabel("Score: " + GameBoard.score);
                                        add(scoreLabel);

                                        scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
                                        scoreLabel.setAlignmentY(CENTER_ALIGNMENT);
                                    }


                                    //add button
                                   // add(new Box.Filler(minSize, prefSize, maxSize));

                                    startMenu.setBackground(new Color(2, 80, 20));
                                    startMenu.add(toStart);
                                    setContentPane(startMenu);
                                }



                            }

                        });

                        setContentPane(startMenu);
                    }

                });

                setContentPane(startMenu);
            }
        });


        rules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                startMenu.setBackground(new Color(2, 45, 20));
                //repaint screen
                startMenu.removeAll();
                revalidate();
                repaint();
                //set header text
                Font headFont = new Font(Font.SANS_SERIF, Font.BOLD, 50);
                JLabel header = new JLabel("Rules");
                header.setForeground(new Color(0, 0, 0));
                header.setFont(headFont);
                add(header);
                add(toStart);
                //////ADD TEXT HERE TO EXPLAIN RULES ETC////////
                Font descriptionFont = new Font(Font.DIALOG, Font.BOLD, 20);
                JLabel write = new JLabel("Whack the Moles!");
                JLabel write2 = new JLabel("To start the game, click on the 'Start' button located on the main menu, ");
                JLabel write3 = new JLabel("then click 'Start Game'. ");
                JLabel write4 = new JLabel("Get ready! The moles will begin to appear and it is ");
                JLabel write5 = new JLabel("up to you to whack them! ");
                JLabel write6 = new JLabel("When you see a mole appear, click on it to earn a point, when the ");
                JLabel write7 = new JLabel("timer runs out, you will be automatically redirected to the scoreboard ");
                JLabel write8 = new JLabel("to view your score.");
                write2.setForeground(new Color(0, 0, 0));
                write2.setFont(descriptionFont);
                write.setForeground(new Color(0, 0, 0));
                write.setFont(descriptionFont);
                write3.setForeground(new Color(0, 0, 0));
                write3.setFont(descriptionFont);
                write3.setForeground(new Color(0, 0, 0));
                write3.setFont(descriptionFont);
                write4.setForeground(new Color(0, 0, 0));
                write4.setFont(descriptionFont);
                write5.setForeground(new Color(0, 0, 0));
                write5.setFont(descriptionFont);
                write6.setForeground(new Color(0, 0, 0));
                write6.setFont(descriptionFont);
                write7.setForeground(new Color(0, 0, 0));
                write7.setFont(descriptionFont);
                write8.setForeground(new Color(0, 0, 0));
                write8.setFont(descriptionFont);

                add(write);
                add(write2);
                add(write3);
                add(write4);
                add(write5);
                add(write6);
                add(write7);
                add(write8);

                //set invisible spacing dimensions
                Dimension minSize = new Dimension(10, 300);
                Dimension prefSize = new Dimension(10, 300);
                Dimension maxSize = new Dimension(Short.MAX_VALUE, 600);

                add(new Box.Filler(minSize, prefSize, maxSize));
                add(toStart);
                toStart.setAlignmentX(Component.CENTER_ALIGNMENT);
                header.setAlignmentX(Component.CENTER_ALIGNMENT);

                setContentPane(startMenu);
            }
        });

        leaderboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //repaint screen
                startMenu.removeAll();
                revalidate();
                repaint();

                //set invisible spacing dimensions
                Dimension minSize = new Dimension(10, 600);
                Dimension prefSize = new Dimension(10, 600);
                Dimension maxSize = new Dimension(Short.MAX_VALUE, 600);

                startMenu.setBackground(new Color(100, 0, 0));

                //set header text
                Font headFont = new Font(Font.SANS_SERIF, Font.BOLD, 50);
                JLabel header = new JLabel("Leaderboard");
                header.setForeground(new Color(0, 0, 0));
                header.setFont(headFont);
                add(header);

                //add button
                add(new Box.Filler(minSize, prefSize, maxSize));
                add(toStart);
                toStart.setAlignmentX(Component.CENTER_ALIGNMENT);
                header.setAlignmentX(Component.CENTER_ALIGNMENT);
                setContentPane(startMenu);
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static JLabel[] getHoleDesign() {
        return holeDesgin;
    }

    public static void setMole(int nextSpot) {
        nextSpotforMole = nextSpot;
    }

    public static int getMole() {
        return nextSpotforMole;
    }

    public static Timer getTime() {
        return timer;
    }

   /* public void setCLickHoles() {
        for (int i=0; i<9; i++) {
            holeDesgin[i] = new JLabel();
            if (i == 1) {
                holeDesgin[i].setBounds(280, 150, 100, 100);
            }
            if (i == 2) {
                holeDesgin[i].setBounds(480, 150, 100, 100);
            }
            if (i == 3) {
                holeDesgin[i].setBounds(80, 300, 100, 100);
            }
            if (i == 4) {
                holeDesgin[i].setBounds(280, 300, 100, 100);
            }
            if (i == 5) {
                holeDesgin[i].setBounds(480, 300, 100, 100);
            }
            if (i == 6) {
                holeDesgin[i].setBounds(80, 450, 100, 100);
            }
            if (i == 7) {
                holeDesgin[i].setBounds(280, 450, 100, 100);
            }
            if (i==8){
                holeDesgin[i].setBounds(480, 450, 100, 100);
            }
            add(holeDesgin[i]);
        }
    }*/


    // mouse event class



    // checks to see if mouse click is inside a cell or not returns true if so, returns false otherwise
    public boolean isInsideCell(Point point) {
        boolean inCell = false;
        JLabel[] Jarray = GameBoard.getHoleDesign();
        MoveablePFrame.remove(holeDesgin[2]);
        for (int i = 0; i < Jarray.length; i++) {
            // if any of the mouse inputs are within the coordinates of the cell and it matches the mole's locaiton
            if ((Jarray[i].getX() <= point.x && Jarray[i].getY() <= point.y) && i == GameBoard.getMole()) {
                System.out.println("Hit!!!!!!!!!!!");
                inCell = true;

                Mole mole = new Mole();
                // get the next mole location
                GameBoard.setMole(mole.play(i));
                return inCell;
            }

        }
        GameBoard.setMole((int) (Math.random() * 9));
        return inCell;
    }


    /*@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }*/

}
