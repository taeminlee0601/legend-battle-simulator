package GameData;

import java.util.ArrayList;
import java.util.Scanner;
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

    public static void buff(Legends legend)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("The current hp of your legend is " + legend.getStats().getHP());
        System.out.println("The current attack of your legend is " + legend.getStats().getAttack());
        System.out.println("The current speed of your legend is " + legend.getStats().getSpeed());
        System.out.println("The current defense of your legend is " + legend.getStats().getDefense());
        System.out.println();
        System.out.println("Which stat do you want to buff? ( Health Points [1], Attack[2], Speed[3], Defense[4] )");
        int choice = input.nextInt();
        boolean validInput = true;
        while(validInput)
        {
            if(choice==1)
            {
                heal(legend);
                validInput=false;
            }
            else if(choice==2)
            {
                buffAttack(legend);
                validInput=false;
            }
            else if(choice==3)
            {
                buffSpeed(legend);
                validInput=false;
            }
            else if(choice==4)
            {
                buffDefense(legend);
                validInput=false;
            }
            else
            {
                System.out.println("Invalid stat name, please try again.");
            }
        }
        System.out.println();
        System.out.println("The updated hp of your legend is " + legend.getStats().getHP());
        System.out.println("The updated attack of your legend is " + legend.getStats().getAttack());
        System.out.println("The updated speed of your legend is " + legend.getStats().getSpeed());
        System.out.println("The updated defense of your legend is " + legend.getStats().getDefense());
        
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
