package GamePanels;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import GameData.Legends;

public class GamePanel extends ParentPanel {
    private Image battleBackground;
    private ArrayList<Legends> player1;
    private ArrayList<Legends> player2;
    private JLabel test = new JLabel();
    
    public GamePanel(ArrayList<Legends> player1, ArrayList<Legends> player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void createPanel() {
        // Initializes the panel setting
        setVisible(true);
        setSize(900,600);
        setLayout(null);

        // Creates the font 
        createFont();

        // Create the place where the characters will be 
        ImageIcon tempImage = new ImageIcon(getClass().getResource("/assets/BattleBackground.jpg"));
        battleBackground = tempImage.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);

        String temp = "<html>" + player1.get(0).getDescription() + "<html>";

        test.setBounds(300, 300, 200, 100);
        test.setText(temp);

        add(test);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(battleBackground, 0, 0, this);
    }

}
