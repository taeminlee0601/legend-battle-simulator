package GameData;

import java.util.ArrayList;
public class Move 
{
    
    private String moveName;
    private int power;
    private int accuracy;

    public Move (String moveName, int power, int accuracy)
    {
        this.moveName=moveName;
        this.power=power;
        this.accuracy=accuracy;
    }

    public String getMoveName()
    {
        return moveName;
    }

    public int getPower()
    {
        return power;
    }

    public int getAccuracy()
    {
        return accuracy;
    }

    public static void heal(Legends legend)
    {
        legend.getStats().setHP(legend.getStats().getHP()+75);
    }

    public static void buffAttack(Legends legend)
    {
        legend.getStats().setAttack(legend.getStats().getAttack()+25);
    }

    public static void buffSpeed(Legends legend)
    {
        legend.getStats().setSpeed(legend.getStats().getSpeed()+25);
    }

    public static void buffDefense(Legends legend)
    {
        legend.getStats().setDefense(legend.getStats().getDefense()+25);
    }

    public static ArrayList<Move> addMoveToMoveset(Move move1, Move move2, Move move3)
    {
        ArrayList <Move> arr = new ArrayList<Move>();
        arr.add(move1);
        arr.add(move2);
        arr.add(move3);
        return arr;
    }



}
