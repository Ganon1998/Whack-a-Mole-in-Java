import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;


public class GameBoard extends JFrame {

    private static JLabel[] holeDesgin = new JLabel[9];
    private static int nextSpotforMole;
    public static int score;
    public static int RoundCount;
    public GameBoard() {

        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setBounds(150,150,660,770); // set frame bounds
        setTitle("WHACK-A-MOLE");
        setVisible( true ); // display frame
        //set menu panel
        JPanel startMenu = new JPanel();

        //set buttons
        JButton startGame = new JButton("Start");
        JButton rules = new JButton("Rules");
        JButton leaderboard = new JButton("Leaderboard");
        JButton quit = new JButton("Quit");

        //create starting menu, this button will act as the "go to main menu button"
        JButton toStart = new JButton("Main Menu");
        startMenu.setBackground(new Color(0,0,0));
        startMenu.add(toStart);
        setContentPane(startMenu);


        //this class draws the ovals around the holeDesign jpanels
        class DrawGameBoardHoles extends JPanel{
            @Override
            public void paintComponent(Graphics g) {


                super.paintComponent(g);
                g.setColor(Color.RED);
                g.drawOval(50,150,100,100);
                g.drawOval(250,150,100,100);
                g.drawOval(450,150,100,100);
                g.drawOval(50,300,100,100);
                g.drawOval(250,300,100,100);
                g.drawOval(450,300,100,100);
                g.drawOval(50,450,100,100);
                g.drawOval(250,450,100,100);
                g.drawOval(450,450,100,100);




                setBackground(new Color(20,80,0));



            }
        }




        //go to main menu when button pressed
        toStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //repaint panel
                startMenu.removeAll();
                revalidate();
                repaint();

                startMenu.setLayout(new BoxLayout(startMenu,BoxLayout.PAGE_AXIS));
                startMenu.setBackground(new Color(65,45,20));

                //set title header
                Font headFont = new Font(Font.SANS_SERIF,Font.BOLD,50);
                JLabel header = new JLabel("Whack-A-Mole!");
                header.setForeground(new Color (0,0,0));
                header.setFont(headFont);
                add(header);
                header.setAlignmentX(Component.CENTER_ALIGNMENT);


                //create an invisible components to space out the buttons
                Dimension minSize = new Dimension(10,50);
                Dimension prefSize = new Dimension(10,50);
                Dimension maxSize = new Dimension(Short.MAX_VALUE,100);

                add(new Box.Filler(minSize,prefSize,maxSize));
                add(startGame);
                add(new Box.Filler(minSize,prefSize,maxSize));
                add(rules);
                add(new Box.Filler(minSize,prefSize,maxSize));
                add(leaderboard);
                add(new Box.Filler(minSize,prefSize,maxSize));
                add(quit);

                setContentPane(startMenu);
            }
        });

        //Start game, this will draw the board
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //repaint panel
                startMenu.removeAll();
                revalidate();
                repaint();

                //calls the class DrawGameBoardHoles to create the hole drawings
                DrawGameBoardHoles draw = new DrawGameBoardHoles();
                startMenu.add(draw);

                //set the header font and text
                Font headFont = new Font(Font.SANS_SERIF,Font.BOLD,30);
                JLabel header = new JLabel("Whack The Moles!!");
                header.setForeground(new Color (0,0,0));
                header.setFont(headFont);
                add(header);
               // header.setAlignmentX(Component.TOP_ALIGNMENT);

                Font font = new Font (Font.DIALOG, Font.BOLD, 20);

                JLabel scoreText = new JLabel("Score: ");
                JLabel timerText = new JLabel("Time: ");
                JLabel secondsText = new JLabel("20");
                JLabel scoreNumText = new JLabel("0");
                
                /*for (int i=30; i>0; i--){
                    secondsText = new JLabel(String.valueOf(i));
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }*/
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
                toStart.setAlignmentX(Component.CENTER_ALIGNMENT);
                header.setAlignmentX(Component.CENTER_ALIGNMENT);


                setContentPane(startMenu);

                //create holes, add to specific locations
                for (int i=0; i<9; i++){
                    holeDesgin[i] = new JLabel("HOLE" + i);
                    holeDesgin[i].setName("HOLE" + i);

                    if(i==0){
                        holeDesgin[i].setBounds(80,150,100,100);
                    }
                    if(i==1){
                        holeDesgin[i].setBounds(280,150,100,100);
                    }
                    if(i==2){
                        holeDesgin[i].setBounds(480,150,100,100);
                    }
                    if(i==3){
                        holeDesgin[i].setBounds(80,300,100,100);
                    }
                    if(i==4){
                        holeDesgin[i].setBounds(280,300,100,100);
                    }
                    if(i==5){
                        holeDesgin[i].setBounds(480,300,100,100);
                    }
                    if(i==6){
                        holeDesgin[i].setBounds(80,450,100,100);
                    }
                    if(i==7){
                        holeDesgin[i].setBounds(280,450,100,100);
                    }
                    if(i==8){
                        holeDesgin[i].setBounds(480,450,100,100);
                    }
                    add(holeDesgin[i]);
                }

            }
        });

        rules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //repaint screen
                startMenu.removeAll();
                revalidate();
                repaint();

                //set invisible spacing dimensions
                Dimension minSize = new Dimension(10,600);
                Dimension prefSize = new Dimension(10,600);
                Dimension maxSize = new Dimension(Short.MAX_VALUE,600);


                startMenu.setBackground(new Color(2,45,20));

                //set header text
                Font headFont = new Font(Font.SANS_SERIF,Font.BOLD,50);
                JLabel header = new JLabel("Rules");
                header.setForeground(new Color (0,0,0));
                header.setFont(headFont);
                add(header);

                //////ADD TEXT HERE TO EXPLAIN RULES ETC////////


                //add button
                add(new Box.Filler(minSize,prefSize,maxSize));
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
                Dimension minSize = new Dimension(10,600);
                Dimension prefSize = new Dimension(10,600);
                Dimension maxSize = new Dimension(Short.MAX_VALUE,600);

                startMenu.setBackground(new Color(100,0,0));

                //set header text
                Font headFont = new Font(Font.SANS_SERIF,Font.BOLD,50);
                JLabel header = new JLabel("Leaderboard");
                header.setForeground(new Color (0,0,0));
                header.setFont(headFont);
                add(header);

                //add button
                add(new Box.Filler(minSize,prefSize,maxSize));
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
    public static void setMole(int nextSpot){ nextSpotforMole = nextSpot; }
    public static int getMole(){ return nextSpotforMole; }
}


// mouse event class
class ClickGameBoard extends JPanel
{
    private final class MouseDrag extends MouseAdapter {
        private boolean clickedMole = false;
        private Point last;

        @Override
        public void mousePressed(MouseEvent m) {
            last = m.getPoint();
            clickedMole = isInsideCell(last);

            if (clickedMole == false) {
                GameBoard.score--;
                GameBoard.setMole((int) Math.random() * 9);
            }
            else
                GameBoard.score+=3;

            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent m) {
            last = null;
            clickedMole = false;
            repaint();
        }
    }

    private int x = 0;
    private int y = 0;

    private MouseDrag mouseDrag;

    public ClickGameBoard() {
        setBackground(Color.WHITE);
        mouseDrag = new MouseDrag();
        addMouseListener(mouseDrag);
        addMouseMotionListener(mouseDrag);
    }

    // checks to see if mouse click is inside a cell or not returns true if so, returns false otherwise
    public boolean isInsideCell(Point point)
    {
        boolean inCell = false;
        JLabel[] Jarray = GameBoard.getHoleDesign();

        for (int i = 0; i < Jarray.length; i++)
        {
            // if any of the mouse inputs are within the coordinates of the cell and it matches the mole's locaiton
            if ((Jarray[i].getX() <= point.x && Jarray[i].getY() <= point.y) && i == GameBoard.getMole())
            {
                inCell = true;
                Mole mole = new Mole();

                // get the next mole location
                GameBoard.setMole(mole.play(i));
                break;
            }

        }
        return inCell;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}

