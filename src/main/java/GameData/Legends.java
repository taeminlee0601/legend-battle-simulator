package GameData;

// Import required packages
import java.util.ArrayList;
import java.io.File;
import java.util.Random;

/**
 * The Legends class represents a legend in a game.
 * Legends have a name, description, movesets, statistics, type, and an image file.
 */
public class Legends
{

    //  Instance variables
    private String name; //  name of the legend
    private String description; //  description of the legend
    private ArrayList<Move> movesets; //  moveset of the legend (3 moves in a moveset)
    private Stats statistic; //  stats of the legend
    private String type; // type of the legend
    private File imageFile; //  image file of the legend
    private File faceImageFile; // image file of the face of the legend
    
    /**
     * Constructs a Legends object with the specified name, description, movesets, statistics, and type.
     * Precondition - Legends object must take a String name, String description, ArrayList<Move> movesets, Stats statistic, and String type
     * Postcondition - Instance variables are initialized
     * @param name - the name of the legend
     * @param description - description of the legend
     * @param movesets - moveset of the legend (3 moves in a moveset)
     * @param statistic - stats of the legend
     * @param type - type of the legend
     */
    public Legends(String name, String description, ArrayList<Move> movesets, Stats statistic,String type)
    {
        this.name=name;
        this.description=description;
        this.movesets=movesets;
        this.statistic=statistic;
        this.type=type;
    }

    /**
     * Returns the ArrayList<Move> movesets of a given Legends object
     * Precondition: Legends object must be initialized
     * Postcondition: Returns ArrayList<Move> movesets accessed from the Legends object
     * @return movesets - movesets of the Legends object
     */
    public ArrayList<Move> getMoveset()
    {
        return movesets;
    }

    /**
     * Returns the Stats statistic of a given Legends object
     * Precondition: Legends object must be initialized
     * Postcondition: Returns Stats statistic accessed from the Legends object
     * @return statistic - stats pf the Legends object
     */
    public Stats getStats()
    {
        return statistic;
    }

    /**
     * Returns the ArrayList<Move> movesets of a given Legends object
     * Precondition: Legends object must be initialized
     * Postcondition: Returns String name accessed from the Legends object
     * @return name - name of the Legends object
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the String description of a given Legends object
     * Precondition: Legends object must be initialized
     * Postcondition: Returns String description accessed from the Legends object
     * @return description - description of the Legends object
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * setter method that sets the File imageFile
     * Precondition: legends object has to exist
     * Postcondition: imageFile will be set to the new File object
     * @param imageFile - new image file of the legends object you want to set
     */
    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    /**
     * Returns the File imageFile of a given Legends object
     * Precondition: Legends object must be initialized
     * Postcondition: return File imageFile accessed from Legends object
     * @return imageFile - image file of the Legends object
     */
    public File getImageFile() {
        return imageFile;
    }

    /**
     * setter method that sets the File faceImageFile
     * Precondition: legends object has to exist
     * Postcondition: faceImageFile will be set to the new File object
     * @param faceImageFile - new image file of the face of the legends object you want to set
     */
    public void setFaceImageFile(File faceImageFile) {
        this.faceImageFile = faceImageFile;
    }

    /**
     * Returns the File faceImageFile of a given Legends object
     * Precondition: Legends object must be initialized
     * Postcondition: return File faceImageFile accessed from Legends object
     * @return faceImageFile - image file of the face of the Legends object
     */
    public File getFaceImageFile() {
        return faceImageFile;
    }

    /**
     * Calculates the damage a move will do, using the formula: power of move*attack of legend/defense of otherLegend
     * Precondition 1: method must take int whichMove, Legends legend, and Legends otherLegend
     * Precondition 2: effectiveness on legend types, refer to game guide for more details
     * Postcondition: returns an int that indicates the dmg the move will do
     * @param whichMove - String object to refer to which move the player picked
     * @param legend - Legends object to refer to the player legend
     * @param otherLegend - Legends object to refer to the opponent legend
     * @return damageDone - an int that indicates the dmg the move will do
     */
    public static int calcDamage (int whichMove, Legends legend, Legends otherLegend)
    {

        int damageDone = (legend.getMoveset().get(whichMove).getPower() *  legend.getStats().getAttack() /  otherLegend.getStats().getDefense());
        
        if(legend.getType().equals("Outer")&&otherLegend.getType().equals("Olympus"))
        {
            damageDone*=1.2;
        }
        else if(legend.getType().equals("Olympus")&&otherLegend.getType().equals("Outer"))
        {
            damageDone*=0.8;
        }
        
        else if(legend.getType().equals("Meme")&&otherLegend.getType().equals("Egyptian"))
        {
            damageDone*=1.2;
        }
        else if(legend.getType().equals("Egyptian")&&otherLegend.getType().equals("Meme"))
        {
            damageDone*=0.8;
        }

        else if(legend.getType().equals("Egyptian")&&otherLegend.getType().equals("Outer"))
        {
            damageDone*=1.2;
        }
        else if(legend.getType().equals("Outer")&&otherLegend.getType().equals("Egyptian"))
        {
            damageDone*=0.8;
        }

        else if(legend.getType().equals("Olympus")&&otherLegend.getType().equals("Meme"))
        {
            damageDone*=1.2;
        }
        else if(legend.getType().equals("Meme")&&otherLegend.getType().equals("Olympus"))
        {
            damageDone*=0.8;
        }
        
        return damageDone;
    }

    /**
     * Returns the String type of a given Legends object
     * Precondition: 
     * Postcondition: returns the String type accessed from Legends object
     * @return type - String object that refers to the legend type
     */
    public String getType()
    {

        return type;

    }

    /**
     * boolean method that checks if the move passes the accuracy check
     * Precondition 1: method must take int accuracy
     * Precondition 2: if the random number is less or equal to the accuracy of the move, it passes true, otherwise false
     * Postcondition: return true or false depending on the condition
     * @param accuracy - int object that refers to the move's accuracy
     * @return return true or false depending on the condition
     */
    public static boolean accuracyCheck (int accuracy)
    {
        Random random = new Random();

        int randomNumber = random.nextInt(101);

        if (randomNumber <= accuracy)
        {
            return true;
        }   
        else
        {
            return false;
        }
    }

    /**
     * Boolean method to check if the player is alive
     * @return true/false - boolean
     */
    public boolean isAlive() {
        if (statistic.getHP() > 0) {
            return true;
        }

        return false;
    }
}
