package GamePanels;

import java.awt.*;
import javax.swing.*;

import ActionListeners.GamePanelActionListeners.AttackButtonActionListeners;
import ActionListeners.GamePanelActionListeners.AttackOptionActionListeners;
import ActionListeners.GamePanelActionListeners.BuffActionListeners;
import ActionListeners.GamePanelActionListeners.BuffOptionActionListeners;
import ActionListeners.GamePanelActionListeners.SwapButtonActionListeners;
import ActionListeners.GamePanelActionListeners.SwapOptionActionListener;

import java.util.ArrayList;
import java.util.HashMap;

import GameData.Legends;
import GameData.Move;
import GameData.QueuedMove;
import GameData.Stats;
import MainGameFrame.FileFunctions;

public class GamePanel extends ParentPanel {
    private Image battleBackground;

    private ArrayList<Legends> player1 = new ArrayList<Legends>();
    private ArrayList<Legends> player2 = new ArrayList<Legends>();

    private ArrayList<QueuedMove> moveQueue = new ArrayList<QueuedMove>();

    private JButton attackButton = new JButton();
    private JButton buffButton = new JButton();
    private JButton swapButton = new JButton();

    private JButton swapCharacter1 = new JButton();
    private JButton swapCharacter2 = new JButton();

    private JButton buffHP = new JButton();
    private JButton buffDef = new JButton();
    private JButton buffAtt = new JButton();
    private JButton buffSpeed = new JButton();

    private JButton move1Button = new JButton();
    private JButton move2Button = new JButton();
    private JButton move3Button = new JButton();

    private int[] countNumBuffs = new int[2];

    private JLabel textArea = new JLabel();

    private Image[] playerImage = new Image[3];
    private ImageIcon tempIcon;

    private Legends currentPlayer1;
    private Legends currentPlayer2;

    private HashMap<Legends, JProgressBar> player1HealthMap = new HashMap<Legends, JProgressBar>();
    private HashMap<Legends, JProgressBar> player2HealthMap = new HashMap<Legends, JProgressBar>();

    private int[] turn = {1};
    
    public GamePanel(ArrayList<Legends> player1, ArrayList<Legends> player2) {
        // Create new objects (don't change the values of the original character)
        for (int a = 0; a < player1.size(); a++) {
            Legends temp = player1.get(a);
            this.player1.add(new Legends(temp.getName(), temp.getDescription(), temp.getMoveset(), temp.getStats(), temp.getType()));
            this.player1.get(a).setImageFile(temp.getImageFile());
            this.player1.get(a).setFaceImageFile(temp.getFaceImageFile());

            player1HealthMap.put(this.player1.get(a), new JProgressBar(0, player1.get(a).getStats().getHP()));
            player1HealthMap.get(this.player1.get(a)).setValue(player1.get(a).getStats().getHP());
            player1HealthMap.get(this.player1.get(a)).setForeground(Color.GREEN);
            player1HealthMap.get(this.player1.get(a)).setStringPainted(true);
            player1HealthMap.get(this.player1.get(a)).setBounds(150, 345, 220, 25);
            player1HealthMap.get(this.player1.get(a)).setVisible(false);
        }

        // Create new objects (don't change the values of the original character)
        for (int a = 0; a < player2.size(); a++) {
            Legends temp = player2.get(a);
            this.player2.add(new Legends(temp.getName(), temp.getDescription(), temp.getMoveset(), temp.getStats(), temp.getType()));
            this.player2.get(a).setImageFile(temp.getImageFile());
            this.player2.get(a).setFaceImageFile(temp.getFaceImageFile());

            player2HealthMap.put(this.player2.get(a), new JProgressBar(0, player2.get(a).getStats().getHP()));
            player2HealthMap.get(this.player2.get(a)).setValue(player2.get(a).getStats().getHP());
            player2HealthMap.get(this.player2.get(a)).setForeground(Color.GREEN);
            player2HealthMap.get(this.player2.get(a)).setStringPainted(true);
            player2HealthMap.get(this.player2.get(a)).setBounds(495, 345, 220, 25);
            player2HealthMap.get(this.player2.get(a)).setVisible(false);
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

        // Create the starting character images
        playerImage[0] = FileFunctions.resizeImage(currentPlayer1.getImageFile(), 200, 250);
        playerImage[1] = FileFunctions.resizeImage(currentPlayer2.getImageFile(), 200, 250);

        // Set health bar to be visible
        player1HealthMap.get(currentPlayer1).setVisible(true);
        player2HealthMap.get(currentPlayer2).setVisible(true);

        // Initialize the swap option 1 button
        swapCharacter1.setBounds(500, 400, 400, 82);
        swapCharacter1.setVisible(false);

        // Initialize the swap option 2 button
        swapCharacter2.setBounds(500, 482, 400, 83);
        swapCharacter2.setVisible(false);

        // Initialize buff hp button
        buffHP.setText("Buff HP");
        buffHP.setBounds(500, 400, 200, 82);
        buffHP.setVisible(false);

        // Initalize buff def button
        buffDef.setText("Buff Defence");
        buffDef.setBounds(700, 400, 200, 82);
        buffDef.setVisible(false);

        // Initialize buff attack button
        buffAtt.setText("Buff Attack");
        buffAtt.setBounds(500, 482, 200, 83);
        buffAtt.setVisible(false);

        // Initialize buff speed button
        buffSpeed.setText("Buff Speed");
        buffSpeed.setBounds(700, 482, 200, 83);
        buffSpeed.setVisible(false);

        move1Button.setBounds(500, 400, 400, 55);
        move1Button.setVisible(false);

        move2Button.setBounds(500,455,400, 55);
        move2Button.setVisible(false);

        move3Button.setBounds(500, 510, 400, 55);
        move3Button.setVisible(false);

        // Initialize the text area
        textArea.setBounds(0,400,500,165);
        textArea.setText("Test");
        textArea.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Initialize the attack button
        attackButton.setBackground(Color.GREEN);
        attackButton.setBounds(500,400,400,55);
        attackButton.setText("Attack Opponent");

        // Initialize the buff button
        buffButton.setBackground(Color.RED);
        buffButton.setBounds(500,455,400,55);
        buffButton.setText("Buff Yourself");

        // Initialize the swap button
        swapButton.setBackground(Color.MAGENTA);
        swapButton.setBounds(500,510,400,55);
        swapButton.setText("Swap Legends");

        // Add action listeners to the swap buttons
        swapButton.addActionListener(new SwapButtonActionListeners(this));
        swapCharacter1.addActionListener(new SwapOptionActionListener(this, 1));
        swapCharacter2.addActionListener(new SwapOptionActionListener(this, 2));

        buffButton.addActionListener(new BuffActionListeners(this));
        buffHP.addActionListener(new BuffOptionActionListeners(1, this));
        buffDef.addActionListener(new BuffOptionActionListeners(2, this));
        buffAtt.addActionListener(new BuffOptionActionListeners(3, this));
        buffSpeed.addActionListener(new BuffOptionActionListeners(4, this));

        attackButton.addActionListener(new AttackButtonActionListeners(this));
        move1Button.addActionListener(new AttackOptionActionListeners(1, this));
        move2Button.addActionListener(new AttackOptionActionListeners(2, this));
        move3Button.addActionListener(new AttackOptionActionListeners(3, this));

        add(textArea);
        add(attackButton);
        add(buffButton);
        add(swapButton);
        add(swapCharacter1);
        add(swapCharacter2);
        add(buffHP);
        add(buffDef);
        add(buffAtt);
        add(buffSpeed);
        add(move1Button);
        add(move2Button);
        add(move3Button);

        for (int a = 0; a < 3; a++) {
            add(player1HealthMap.get(player1.get(a)));
        }

        for (int a = 0; a < 3; a++) {
            add(player2HealthMap.get(player2.get(a)));
        }
    }

    public void initiateMoves() {
        if (Stats.checkSpeed(currentPlayer1, currentPlayer2)) {
            playerMoves(0);
            playerMoves(1);
        } else {
            playerMoves(1);
            playerMoves(0);
        }

        moveQueue.clear();

        repaint();
    }

    public void playerMoves(int index) {
        ArrayList<Legends> player = new ArrayList<Legends>();
        ArrayList<Legends> opponent = new ArrayList<Legends>();
        HashMap<Legends, JProgressBar> opponentHealthMap = new HashMap<Legends, JProgressBar>();

        if (index == 0) {
            player = player1;
            opponent = player2;
            opponentHealthMap = player2HealthMap;
        } else {
            player = player2;
            opponent = player1;
            opponentHealthMap = player1HealthMap;
        }

        QueuedMove playerMove = moveQueue.get(index);

        if (playerMove.getMoveType() == 1) {

            if (playerMove.getMoveSelected() == 1 && Legends.accuracyCheck(player.get(0).getMoveset().get(0).getAccuracy())) {
                
                opponent.get(0).getStats().setHP(opponent.get(0).getStats().getHP() - Legends.calcDamage(0, player.get(0), opponent.get(0)));

            } else if (playerMove.getMoveSelected() == 2 && Legends.accuracyCheck(player.get(0).getMoveset().get(1).getAccuracy())) {

                opponent.get(0).getStats().setHP(opponent.get(0).getStats().getHP() - Legends.calcDamage(1, player.get(0), opponent.get(0)));

            } else if (playerMove.getMoveSelected() == 3 && Legends.accuracyCheck(player.get(0).getMoveset().get(2).getAccuracy())) {

                opponent.get(0).getStats().setHP(opponent.get(0).getStats().getHP() - Legends.calcDamage(2, player.get(0), opponent.get(0)));

            } else {
                System.out.println("miss");
            }

            opponentHealthMap.get(opponent.get(0)).setValue(opponent.get(0).getStats().getHP());

        } else if (playerMove.getMoveType() == 2) {

            if (countNumBuffs[index] < 5) {
                if (playerMove.getStatBuffed() == 1) {
                    Move.heal(player.get(0));
                } else if (playerMove.getStatBuffed() == 2) {
                    Move.buffDefense(player.get(0));
                } else if (playerMove.getStatBuffed() == 3) {
                    Move.buffAttack(player.get(0));
                } else {
                    Move.buffSpeed(player.get(0));
                }
                countNumBuffs[index]++;
            }

        } else {
            playerImage[index] = FileFunctions.resizeImage(player.get(0).getImageFile(), 200, 250);
        }

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
        return playerImage[0];
    }

    public ArrayList<Legends> getPlayer2() {
        return player2;
    }

    public Image getPlayer2Image() {
        return playerImage[1];
    }

    public JLabel getTextLabel() {
        return textArea;
    }

    public int[] getTurn() {
        return turn;
    }

    public void setPlayerImage(Image newPlayerImage) {
        if (turn[0] > 0) {
            playerImage[0] = newPlayerImage;
        } else {
            playerImage[1] = newPlayerImage;
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

    public ArrayList<QueuedMove> getMoveQueue() {
        return moveQueue;
    }

    public Legends getCurrentCharacter(int playerNum) {
        if (playerNum == 1) {
            return currentPlayer1;
        }

        return currentPlayer2;
    }

    public void setCurrentCharacter(Legends current, int playerNum) {
        if (playerNum == 1) {
            currentPlayer1 = current;
        } else {
            currentPlayer2 = current;
        }
    }

    public JButton getBuffHPButton() {
        return buffHP;
    }

    public JButton getBuffDefButton() {
        return buffDef;
    }

    public JButton getBuffAttButton() {
        return buffAtt;
    }

    public JButton getBuffSpeedButton() {
        return buffSpeed;
    }

    public JButton getMove1Button() {
        return move1Button;
    }

    public JButton getMove2Button() {
        return move2Button;
    }

    public JButton getMove3Button() {
        return move3Button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(battleBackground, 0, 0, this);

        g.setColor(Color.WHITE);
        g.fillRect(150,75, 220,270);
        g.fillRect(495, 75, 220, 270);

        g.drawImage(playerImage[0], 160, 85, this);
        g.drawImage(playerImage[1], 505, 85, this);
    }

}
