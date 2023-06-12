package GameData;

public class Stats
{
    private int hp;
    private int attack;
    private int speed;
    private int defense;

//constructors

    public Stats(int hp, int attack, int speed, int defense)
    {
        this.hp=hp;
        this.attack=attack;
        this.speed=speed;
        this.defense=defense;
    }

    public int getHP()
    {
        return hp;
    }

    public void setHP(int hp)
    {
        this.hp=hp;
    }

    public int getAttack()
    {
        return attack;
    }

    public void setAttack(int attack)
    {
        this.attack=attack;
    }

    public int getSpeed()
    {
        return speed;
    }

    public void setSpeed(int speed)
    {
        this.speed=speed;
    }

    public int getDefense()
    {
        return defense;
    }

    public void setDefense(int defense)
    {
        this.defense=defense;
    }

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
