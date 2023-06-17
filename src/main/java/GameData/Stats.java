package GameData;

/**
 * The Stats class represent the stats of the legend
 * Stats has a hp, attack, speed, and defense
 */
public class Stats
{
    private int hp;
    private int attack;
    private int speed;
    private int defense;

    /**
     * Constructs a Stats object with the specified hp, attack, speed, defense
     * Precondition - Stats object must take int hp, int attack, int speed, and int defense
     * Postcondition - Instance variables are initialized 
     * @param hp - hp of the legend stat
     * @param attack - attack of legend stat
     * @param speed - speed of the legend stat
     * @param defense - defense of the legend stat
     */
    public Stats(int hp, int attack, int speed, int defense)
    {
        this.hp=hp;
        this.attack=attack;
        this.speed=speed;
        this.defense=defense;
    }

    /**
     * Returns the int hp of a given Stats object
     * Precondition: Stats object must be initialized
     * Postcondition: Returns int hp accessed from the Stats object
     * @return hp - hp of the Stats object
     */
    public int getHP()
    {
        return hp;
    }

    /**
     * Setter method: sets the hp of the Stats object with a new int hp
     * Precondition: method must take an int hp
     * Postcondition: hp has been set to the new hp
     * @param hp - hp of the Stats object
     */
    public void setHP(int hp)
    {
        this.hp=hp;
    }

    /**
     * Returns the int attack of a given Stats object
     * Precondition: Stats object must be initialized
     * Postcondition: Returns int attack accessed from the Stats object
     * @return attack - attack of the Stats object
     */
    public int getAttack()
    {
        return attack;
    }

    /**
     * Setter method: sets the attack of the Stats object with a new int attack
     * Precondition: method must take an int attack
     * Postcondition: attack has been set to the new attack
     * @param attack - attack of the Stats object
     */
    public void setAttack(int attack)
    {
        this.attack=attack;
    }

    /**
     * Returns the int speed of a given Stats object
     * Precondition: Stats object must be initialized
     * Postcondition: Returns int speed accessed from the Stats object
     * @return speed - speed of the Stats object
     */
    public int getSpeed()
    {
        return speed;
    }

    /**
     * Setter method: sets the speed of the Stats object with a new int speed
     * Precondition: method must take an int speed
     * Postcondition: speed has been set to the new speed
     * @param speed - speed of the Stats object
     */
    public void setSpeed(int speed)
    {
        this.speed=speed;
    }

    /**
     * Returns the int defense of a given Stats object
     * Precondition: Stats object must be initialized
     * Postcondition: Returns int defense accessed from the Stats object
     * @return defense - defense of the Stats object
     */
    public int getDefense()
    {
        return defense;
    }

    /**
     * Setter method: sets the defense of the Stats object with a new int defense
     * Precondition: method must take an int defense
     * Postcondition: defense has been set to the new defense
     * @param defense - defense of the Stats object
     */
    public void setDefense(int defense)
    {
        this.defense=defense;
    }

    /**
     * The method compares the speed stat of the team legend and the opponent legend
     * Precondition: the method must take two Legends object, one is the team legend and the other is opponent legend
     * Postcondition: returns true or false depending on the condition
     * @param legend - Legends object (team)
     * @param otherLegend - Legends object (opponent)
     * @return true or false
     */
    public static boolean checkSpeed(Legends legend, Legends otherLegend)
    {
        if(legend.getStats().getSpeed()>otherLegend.getStats().getSpeed())
        {
            return true;
        }
        else if(legend.getStats().getSpeed()==otherLegend.getStats().getSpeed())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    
} 
