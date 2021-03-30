import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameBoard extends JFrame {

    JLabel[] holeDesgin = new JLabel[9];
    public GameBoard(){
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
            JLabel hi = new JLabel("hi");
            startMenu.setBackground(new Color(65,45,20));

            for (int i=0; i<9; i++){
                holeDesgin[i] = new JLabel("HOLE" + i);
                holeDesgin[i].setName("HOLE" + i);
                if(i==0){
                    holeDesgin[i].setBounds(0,300,150,150);
                }
                add(holeDesgin[i]);
            }


            setContentPane(startMenu);

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
}
