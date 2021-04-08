import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        // gets the mole started
        Mole mole = new Mole();

        GameBoard game = new GameBoard();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(800,800);
        game.setMole(mole.startMole());
        game.setVisible(true);

    }
}
