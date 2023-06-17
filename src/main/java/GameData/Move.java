package GameData;

// Import required packages
import java.util.ArrayList;

/**
 * The Move class represents the move a legend has.
 * A move has a move name, its move power, and its move accuracy.
 */
public class Move 
{
    //  Instances variables
    private String moveName; //  name of the move
    private int power; //  power of the move
    private int accuracy; //  accuracy of the move

    /**
     * Constructs a Move object with the specified name, power, and accuracy
     * Precondition - Move object must take String moveName, int power, int accuracy
     * Postcondition - Instance variables are initialized
     * @param moveName - name of the move
     * @param power - power of the move
     * @param accuracy - accuracy of the move
     */
    public Move (String moveName, int power, int accuracy)
    {
        this.moveName=moveName;
        this.power=power;
        this.accuracy=accuracy;
    }

    /**
     * Returns the String moveName of a given Move object
     * Precondition: Move object must be initialized
     * Postcondition: Returns String moveName accessed from the Move object
     * @return moveName - name of the Move object
     */
    public String getMoveName()
    {
        return moveName;
    }

    /**
     * Returns the int power of a given Move object
     * Precondition: Move object must be initialized
     * Postcondition: Returns int power accessed from the Move object
     * @return power - power of the Move object
     */
    public int getPower()
    {
        return power;
    }

    /**
     * Returns the int accuracy of a given Move object
     * Precondition: Move object must be initialized
     * Postcondition: Returns int accuracy accessed from the Move object
     * @return accuracy - accuracy of the Move object
     */
    public int getAccuracy()
    {
        return accuracy;
    }

    /**
     * increase the hp stat of the Legends object
     * Precondition: method has to take a Legends object to execute
     * Postcondition: the hp stat of the Legends object is updated and incremented by a set value
     * @param legend - Legends object
     */
    public static void heal(Legends legend)
    {
        legend.getStats().setHP(legend.getStats().getHP()+75);
    }

    /**
     * increase the attack stat of the Legends object
     * Precondition: method has to take a Legends object to execute
     * Postcondition: the attack stat of the Legends object is updated and incremented by a set value
     * @param legend - Legends object
     */
    public static void buffAttack(Legends legend)
    {
        legend.getStats().setAttack(legend.getStats().getAttack()+25);
    }

    /**
     * increase the speed stat of the Legends object
     * Precondition: method has to take a Legends object to execute
     * Postcondition: the speed stat of the Legends object is updated and incremented by a set value
     * @param legend - Legends object
     */
    public static void buffSpeed(Legends legend)
    {
        legend.getStats().setSpeed(legend.getStats().getSpeed()+25);
    }

    /**
     * increase the defense stat of the Legends object
     * Precondition: method has to take a Legends object to execute
     * Postcondition: the defense stat of the Legends object is updated and incremented by a set value
     * @param legend - Legends object
     */
    public static void buffDefense(Legends legend)
    {
        legend.getStats().setDefense(legend.getStats().getDefense()+25);
    }

    /**
     * this method adds the 3 Move objects in a ArrayList "arr"
     * Precondition: method has to take in 3 Move objects, they must be different
     * Postcondition: the 3 Move objects will be added to the ArrayList "arr"
     * @param move1 - first move of the moveset
     * @param move2 - second move of the moveset
     * @param move3 - third move of the moveset
     * @return arr - the ArrayList that stores the moveset
     */
    public static ArrayList<Move> addMoveToMoveset(Move move1, Move move2, Move move3)
    {
        ArrayList <Move> arr = new ArrayList<Move>();
        arr.add(move1);
        arr.add(move2);
        arr.add(move3);
        return arr;
    }
}
