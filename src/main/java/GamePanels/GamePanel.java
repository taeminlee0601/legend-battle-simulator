package GamePanels;

import java.awt.*;
import javax.swing.*;

import ActionListeners.GamePanelActionListeners.SwapButtonActionListeners;
import ActionListeners.GamePanelActionListeners.SwapOptionActionListener;

import java.util.ArrayList;
import GameData.Legends;
import MainGameFrame.FileFunctions;

public class GamePanel extends ParentPanel {
    private Image battleBackground;
    private ArrayList<Legends> player1 = new ArrayList<Legends>();
    private ArrayList<Legends> player2 = new ArrayList<Legends>();
    private JButton attackButton = new JButton();
    private JButton buffButton = new JButton();
    private JButton swapButton = new JButton();
    private JButton swapCharacter1 = new JButton();
    private JButton swapCharacter2 = new JButton();
    private JLabel textArea = new JLabel();
    private Image player1Image;
    private Image player2Image;
    private ImageIcon tempIcon;
    private Legends currentPlayer1;
    private Legends currentPlayer2;
    private int[] turn = {1};
    
    public GamePanel(ArrayList<Legends> player1, ArrayList<Legends> player2) {
        // Create new objects (don't change the values of the original character)
        for (int a = 0; a < player1.size(); a++) {
            Legends temp = player1.get(a);
            this.player1.add(new Legends(temp.getName(), temp.getDescription(), temp.getMoveset(), temp.getStats(), temp.getType()));
            this.player1.get(a).setImageFile(temp.getImageFile());
            this.player1.get(a).setFaceImageFile(temp.getFaceImageFile());
        }

        // Create new objects (don't change the values of the original character)
        for (int a = 0; a < player2.size(); a++) {
            Legends temp = player2.get(a);
            this.player2.add(new Legends(temp.getName(), temp.getDescription(), temp.getMoveset(), temp.getStats(), temp.getType()));
            this.player2.get(a).setImageFile(temp.getImageFile());
            this.player2.get(a).setFaceImageFile(temp.getFaceImageFile());
        }
    }

    public void createPanel() {
        // Initializes the panel setting
        setVisible(true);
        setSize(900,600);
        setLayout(null);

        // Creates the font 
        createFont();

        currentPlayer1 = player1.get(0);
        currentPlayer2 = player2.get(0);

        // Create the place where the characters will be 
        tempIcon = new ImageIcon(getClass().getResource("/assets/BattleBackground.jpg"));
        battleBackground = tempIcon.getImage().getScaledInstance(900, 400, Image.SCALE_DEFAULT);

        player1Image = FileFunctions.resizeImage(currentPlayer1.getImageFile(), 200, 250);
        player2Image = FileFunctions.resizeImage(currentPlayer2.getImageFile(), 200, 250);

        swapCharacter1.setBounds(500, 400, 400, 82);
        swapCharacter1.setVisible(false);

        swapCharacter2.setBounds(500, 482, 400, 83);
        swapCharacter2.setVisible(false);

        textArea.setBounds(0,400,500,165);
        textArea.setText("Test");
        textArea.setHorizontalAlignment(SwingConstants.CENTER);
        
        attackButton.setBackground(Color.GREEN);
        attackButton.setBounds(500,400,400,55);
        attackButton.setText("Attack Opponent");

        buffButton.setBackground(Color.RED);
        buffButton.setBounds(500,455,400,55);
        buffButton.setText("Buff Yourself");

        swapButton.setBackground(Color.MAGENTA);
        swapButton.setBounds(500,510,400,55);
        swapButton.setText("Swap Legends");

        swapButton.addActionListener(new SwapButtonActionListeners(this));
        swapCharacter1.addActionListener(new SwapOptionActionListener(this, 1));
        swapCharacter2.addActionListener(new SwapOptionActionListener(this, 2));

        add(textArea);
        add(attackButton);
        add(buffButton);
        add(swapButton);
        add(swapCharacter1);
        add(swapCharacter2);
    }

    public JButton getAttackButton() {
        return attackButton;
    }

    public JButton getSwapButton() {
        return swapButton;
    }

    public JButton getBuffButton() {
        return buffButton;
    }

    public ArrayList<Legends> getPlayer1() {
        return player1;
    }

    public Image getPlayer1Image() {
        return player1Image;
    }

    public ArrayList<Legends> getPlayer2() {
        return player2;
    }

    public Image getPlayer2Image() {
        return player2Image;
    }

    public JLabel getTextLabel() {
        return textArea;
    }

    public int[] getTurn() {
        return turn;
    }

    public void setPlayerImage(Image playerImage) {
        if (turn[0] > 0) {
            player1Image = playerImage;
        } else {
            player2Image = playerImage;
        }
    }

    public void changeTurn() {
        turn[0] *= -1;
    }

    public JButton getSwapCharacter1() {
        return swapCharacter1;
    }

    public JButton getSwapCharacter2() {
        return swapCharacter2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(battleBackground, 0, 0, this);

        g.setColor(Color.WHITE);
        g.fillRect(150,75, 220,270);
        g.fillRect(495, 75, 220, 270);

        g.drawImage(player1Image, 160, 85, this);
        g.drawImage(player2Image, 505, 85, this);
    }

}
