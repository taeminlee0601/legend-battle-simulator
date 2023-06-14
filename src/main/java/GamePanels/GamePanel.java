package GamePanels;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import GameData.Legends;

public class GamePanel extends ParentPanel {
    private Image battleBackground;
    private ArrayList<Legends> player1;
    private ArrayList<Legends> player2;
    private JButton attackButton = new JButton();
    private JButton buffButton = new JButton();
    private JButton swapButton = new JButton();
    private JLabel textArea = new JLabel();
    private Image player1Image;
    private Image player2Image;
    private ImageIcon tempIcon;
    private Legends currentPlayer1;
    private Legends currentPlayer2;
    
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
        tempIcon = new ImageIcon(getClass().getResource("/assets/BattleBackground.jpg"));
        battleBackground = tempIcon.getImage().getScaledInstance(900, 400, Image.SCALE_DEFAULT);

        tempIcon = new ImageIcon(getClass().getResource("/assets/Characters/zeus.jpg"));
        player1Image = tempIcon.getImage().getScaledInstance(200, 250, Image.SCALE_DEFAULT);

        textArea.setBounds(0,400,700,165);
        textArea.setText("Test");
        textArea.setHorizontalAlignment(SwingConstants.CENTER);
        
        attackButton.setBackground(Color.GREEN);
        attackButton.setBounds(700,400,200,55);
        attackButton.setText("Attack Opponent");

        buffButton.setBackground(Color.RED);
        buffButton.setBounds(700,455,200,55);
        buffButton.setText("Buff Yourself");

        swapButton.setBackground(Color.MAGENTA);
        swapButton.setBounds(700,510,200,55);
        swapButton.setText("Swap Legends");

        add(textArea);
        add(attackButton);
        add(buffButton);
        add(swapButton);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(battleBackground, 0, 0, this);

        g.setColor(Color.WHITE);
        g.fillRect(150,75, 220,270);
        g.fillRect(495, 75, 220, 270);

        g.drawImage(player1Image, 160, 85, this);
    }

}
