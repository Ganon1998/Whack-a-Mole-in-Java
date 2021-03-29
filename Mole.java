import java.util.Stack;

public class Mole
{

    public Stack<UserInputGrid> recordStack;

    public Mole() { }

    public void play(UserInputGrid userInput, boolean inSpot)
    {
        if (!inSpot)
        {
            // make mole reappear somewhere else
            return;
        }
        else{

            // generate next spot to go to
            double newX = Math.random() * 3;
            double newY = Math.random() * 3;

            // get previous spot where Mole was whacked
            UserInputGrid lastSpot = recordStack.peek();

            // if the new mole goto coordinates are identical (for whatever reason) to the previous input
            while (lastSpot.X == newX && lastSpot.Y == newY)
            {
                newX = Math.random() * 3;
                newY = Math.random() * 3;
            }

            // graphic to make mole show up in the new x and y coordinates

            recordStack.push(userInput);
        }
    }

}

