package GameData;

import java.util.ArrayList;
import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class Legends
{
    private String name;
    private String description;
    private ArrayList<Move> movesets;
    private Stats statistic;
    private String type;
    private File imageFile;
    private File faceImageFile;
    
    
    public Legends(String name, String description, ArrayList<Move> movesets, Stats statistic,String type)
    {
        this.name=name;
        this.description=description;
        this.movesets=movesets;
        this.statistic=statistic;
        this.type=type;
    }

    public ArrayList<Move> getMoveset()
    {
        return movesets;
    }

    public Stats getStats()
    {
        return statistic;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setFaceImageFile(File faceImageFile) {
        this.faceImageFile = faceImageFile;
    }

    public File getFaceImageFile() {
        return faceImageFile;
    }

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

    public String getType()
    {

        return type;

    }

    public static void showMoveset(ArrayList<Move> moveset)
    {
        System.out.println("Move 1 name: "+ moveset.get(0).getMoveName());
        System.out.println("Move 1 power: "+ moveset.get(0).getPower());
        System.out.println("Move 1 accuracy: "+ moveset.get(0).getAccuracy());

        System.out.println();

        System.out.println("Move 2 name: "+ moveset.get(1).getMoveName());
        System.out.println("Move 2 power: "+ moveset.get(1).getPower());
        System.out.println("Move 2 accuracy: "+ moveset.get(1).getAccuracy());

        System.out.println();

        System.out.println("Move 3 name: "+ moveset.get(2).getMoveName());
        System.out.println("Move 3 power: "+ moveset.get(2).getPower());
        System.out.println("Move 3 accuracy: "+ moveset.get(2).getAccuracy());

        System.out.println();

    }

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

    public static void swapLegend(Player player,Legends legend)
    {   
        boolean validInput=true;
        Scanner input = new Scanner(System.in);
        while(validInput)
        {
            Player.showLegendListPlayer(player);
            System.out.println();
            System.out.println("Player's current legend is " + legend.getName());
            System.out.println();
            System.out.print("Who do you want to switch to? ");
            String who = input.next();
            System.out.println();
            Legends desiredLegend = null;
            
            for (int i = 0; i < player.getCharacter().length; i++) {
                if (who.equals(player.getCharacter(i).getName())) {
                    validInput=false;
                    desiredLegend = player.getCharacter(i);
                    int legendIndex = Player.getIndiceInCharacter(player.getCharacter(), legend);
                    int desiredLegendIndex = Player.getIndiceInCharacter(player.getCharacter(), desiredLegend);
                    player.setCharacter(legendIndex, desiredLegend);
                    player.setCharacter(desiredLegendIndex, legend);
                    Player.showLegendListPlayer(player);
                    System.out.println();
                    break;
                }
            }
            if(validInput)
            {
                System.out.println("Invalid legend name. Please try again.");
            }
        }
    }

    public boolean isAlive() {
        if (statistic.getHP() > 0) {
            return true;
        }

        return false;
    }
}
