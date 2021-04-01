import java.util.Deque;
import java.util.Stack;

public class Mole
{

    public Deque<Integer> recordDeque;

    public Mole() { }

    public int startMole()
    {
        return (int) Math.random() * 9;
    }

    public int play(int moleIndex)
    {
        recordDeque.addFirst(moleIndex);
        int lastSpot = 0;
        int firstSpot = 0;

        // generate next spot to go to, newSpot is the index in the Jlabel array
        int newSpot = (int) Math.random() * 9;

        // get previous spot where Mole was whacked
        if (!recordDeque.isEmpty()) {
            lastSpot = recordDeque.peek();
            firstSpot = recordDeque.getFirst();
        }

        // if for some reason the newSpot is identical to the previously selected spot
        while (newSpot != lastSpot || newSpot != firstSpot)
            newSpot = (int) Math.random() * 9;

        // this will pop the deque from the back if true, pop from the front otherwise
        if (newSpot > 4)
            recordDeque.pop();
        else
            recordDeque.removeFirst();


        recordDeque.push(newSpot);

        // returns index of new spot to go to in Jlabel array
        return newSpot;

    }

}

