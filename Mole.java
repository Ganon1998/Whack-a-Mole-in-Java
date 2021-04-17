import javax.swing.*;
import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class Mole
{
    public static int spotForPoints = 0;
    public ImageIcon moleIcon;
    public Deque<Integer> recordDeque = new LinkedList<>();

    public Mole() { }
    public Icon drawMole(){

        moleIcon = new ImageIcon("resources/image/mole.png");
        Image resize = moleIcon.getImage();
        Image resizedMole = resize.getScaledInstance(100,100, Image.SCALE_REPLICATE);
        moleIcon = new ImageIcon(resizedMole);
        return moleIcon;
    }
    public int startMole()
    {
        Random rn = new Random();
        int spot = rn.nextInt(8) + 1;

        if (recordDeque.size() >= 2) {
            while (spot != recordDeque.getFirst() || spot != recordDeque.peek())
                spot = rn.nextInt(8) + 1;
        }

        //System.out.printf("Got a spot!!!!: %d ", spot);

        //GameBoard.setMole(spot);
        spotForPoints = spot;
        System.out.printf("Found a spot!! %d\n", spotForPoints);

        return spot;
    }

    public int play(int moleIndex)
    {

        recordDeque.addFirst(moleIndex);
        int lastSpot = 0;
        int firstSpot = 0;
        Random rn = new Random();

        // generate next spot to go to, newSpot is the index in the Jlabel array
        int newSpot = rn.nextInt(8) + 1;

        // get previous spot where Mole was whacked
        if (!recordDeque.isEmpty()) {
            lastSpot = recordDeque.peek();
            firstSpot = recordDeque.getFirst();
        }

        // if for some reason the newSpot is identical to the previously selected spot
        while (newSpot != lastSpot || newSpot != firstSpot)
            newSpot = rn.nextInt(8) + 1;

        // this will pop the deque from the back if true, pop from the front otherwise
        if (newSpot > 4)
            recordDeque.pop();
        else
            recordDeque.removeFirst();


        recordDeque.push(newSpot);
        spotForPoints = newSpot;

        // returns index of new spot to go to in Jlabel array
        return newSpot;

    }

}
