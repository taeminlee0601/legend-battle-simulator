package GameData;

public class Player {
    
    private Legends legend1;
    private Legends legend2;
    private Legends legend3;
    private Legends[] character = {legend1,legend2,legend3};
    
    public Player()
    {
        legend1=null;
        legend2=null;
        legend3=null;
    }

    public static int getIndiceInCharacter(Legends[] character,Legends legend)
    {
        for(int i=0;i<character.length;i++)
        {
            if(character[i]==legend)
            {
                return i;
            }
        }
        return -1;
    }

    

    public Legends getCharacter(int i)
    {
        return character[i];
    }

    public Legends[] getCharacter()
    {
        return character;
    }

    public void setCharacter(int num, Legends legend)
    {
        character[num]=legend;
    }

    public static void showLegendListPlayer(Player player)
    {
        System.out.println("1. " + player.getCharacter(0).getName());
        System.out.println("2. " + player.getCharacter(1).getName());
        System.out.println("3. " + player.getCharacter(2).getName());
    }

    public String toString()
    {
        return character[0].getName() + "\n" + character[1].getName() + "\n" + character[2].getName() + "\n";
    }

    
}