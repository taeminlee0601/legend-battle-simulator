package GamePanels;

// Import required packages
import java.awt.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;
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

/**
 * This panel is where the game is played
 * Inherits ParentPanel
 */
public class GamePanel extends ParentPanel {
    // Create necessary instance variables
    private Image battleBackground; // Image of the battlefield

    private ArrayList<Legends> player1 = new ArrayList<Legends>(); // ArrayList of player1 legends
    private ArrayList<Legends> player2 = new ArrayList<Legends>(); // ArrayList of player2 Legends

    private ArrayList<QueuedMove> moveQueue = new ArrayList<QueuedMove>(); // ArrayList of the moves queued

    private JButton attackButton = new JButton(); // JButton of the Attack button
    private JButton buffButton = new JButton(); // JButton of the buff button
    private JButton swapButton = new JButton(); // JButton of the swapButton

    private JButton swapCharacter1 = new JButton(); // Button to swap to first character
    private JButton swapCharacter2 = new JButton(); // Button to swap to second character

    private JButton buffHP = new JButton(); // Button to buff HP
    private JButton buffDef = new JButton(); // Button to buff Def
    private JButton buffAtt = new JButton(); // Button to buff Att
    private JButton buffSpeed = new JButton(); // Button to buff Speed

    private JButton move1Button = new JButton(); // Button to use move 1
    private JButton move2Button = new JButton(); // Button to use move 2
    private JButton move3Button = new JButton(); // Button to use move 3

    private int[] countNumBuffs = new int[2]; // int array counting the number of buffs done

    private JLabel textArea = new JLabel(); // Label to show the text (What is happening in the game)
    private JLabel playerChoosing = new JLabel(); // Label to show who is choosing their move

    private Image[] playerImage = new Image[3]; // Image array to show the player image
    private ImageIcon tempIcon; // Image Icon that is used to store temporary ImageIcons

    private Legends currentPlayer1; // Active Legend for player 1
    private Legends currentPlayer2; // Active Legend for player 2

    private JLabel[] displayName = new JLabel[2]; // Name of active legend

    private HashMap<Legends, JProgressBar> player1HealthMap = new HashMap<Legends, JProgressBar>(); // Health bar of legend in player 1
    private HashMap<Legends, JProgressBar> player2HealthMap = new HashMap<Legends, JProgressBar>(); // Health bar of legend in player 2

    private int[] turn = { 1 }; // Int array to store whose turn it is (array is used for memory reference)

    public GamePanel(ArrayList<Legends> player1, ArrayList<Legends> player2) {
        // Create new objects (don't change the values of the original character)
        for (int a = 0; a < player1.size(); a++) {
            Legends temp = player1.get(a);
            this.player1.add(new Legends(temp.getName(), temp.getDescription(), temp.getMoveset(), temp.getStats(),
                    temp.getType()));
            this.player1.get(a).setImageFile(temp.getImageFile());
            this.player1.get(a).setFaceImageFile(temp.getFaceImageFile());

            // Initalize the settings of the health bar and add to hashMap
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
            this.player2.add(new Legends(temp.getName(), temp.getDescription(), temp.getMoveset(), temp.getStats(),
                    temp.getType()));
            this.player2.get(a).setImageFile(temp.getImageFile());
            this.player2.get(a).setFaceImageFile(temp.getFaceImageFile());

            // Initalize the settings of the health bar and add to hashMap
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
        setSize(900, 600);
        setLayout(null);

        // Creates the font
        createFont();

        // Sets the active players
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
        buffDef.setText("Buff Defense");
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

        // Initialize the move buttons
        move1Button.setBounds(500, 400, 400, 55);
        move1Button.setVisible(false);

        move2Button.setBounds(500, 455, 400, 55);
        move2Button.setVisible(false);

        move3Button.setBounds(500, 510, 400, 55);
        move3Button.setVisible(false);

        // Initialize the text area
        textArea.setBounds(10, 400, 500, 115);
        textArea.setText("Player 1 chooses first then Player 2!");
        textArea.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialize the player choosing text area
        playerChoosing.setBounds(0, 515, 475, 50);
        playerChoosing.setText("Player 1 is Choosing...");
        playerChoosing.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialize the display names
        displayName[0] = new JLabel();
        displayName[0].setFont(customFont.deriveFont(20f));
        displayName[0].setForeground(Color.WHITE);
        displayName[0].setBounds(150, 25, 220, 50);
        displayName[0].setText(player1.get(0).getName());
        displayName[0].setHorizontalAlignment(SwingConstants.CENTER);

        displayName[1] = new JLabel();
        displayName[1].setFont(customFont.deriveFont(20f));
        displayName[1].setForeground(Color.WHITE);
        displayName[1].setBounds(495, 25, 220, 50);
        displayName[1].setText(player2.get(0).getName());
        displayName[1].setHorizontalAlignment(SwingConstants.CENTER);

        // Initialize the attack button
        attackButton.setBounds(500, 400, 400, 55);
        attackButton.setText("Attack Opponent");

        // Initialize the buff button
        buffButton.setBounds(500, 455, 400, 55);
        buffButton.setText("Buff Yourself");

        // Initialize the swap button
        swapButton.setBounds(500, 510, 400, 55);
        swapButton.setText("Swap Legends");

        // Add action listeners to the swap buttons
        swapButton.addActionListener(new SwapButtonActionListeners(this));
        swapCharacter1.addActionListener(new SwapOptionActionListener(this, 1));
        swapCharacter2.addActionListener(new SwapOptionActionListener(this, 2));

        // Add action listeners to the buff buttons
        buffButton.addActionListener(new BuffActionListeners(this));
        buffHP.addActionListener(new BuffOptionActionListeners(1, this));
        buffDef.addActionListener(new BuffOptionActionListeners(2, this));
        buffAtt.addActionListener(new BuffOptionActionListeners(3, this));
        buffSpeed.addActionListener(new BuffOptionActionListeners(4, this));

        // Add action listeners to the attack and move buttons
        attackButton.addActionListener(new AttackButtonActionListeners(this));
        move1Button.addActionListener(new AttackOptionActionListeners(1, this));
        move2Button.addActionListener(new AttackOptionActionListeners(2, this));
        move3Button.addActionListener(new AttackOptionActionListeners(3, this));

        // Add the buttons, labels, progress bars to the screen
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
        add(playerChoosing);
        add(displayName[0]);
        add(displayName[1]);

        for (int a = 0; a < 3; a++) {
            add(player1HealthMap.get(player1.get(a)));
        }

        for (int a = 0; a < 3; a++) {
            add(player2HealthMap.get(player2.get(a)));
        }
    }

    /**
     * This method will run the moves selected by each player
     * Precondition: Player 1 and 2 both chose a move
     * Postcondition: Runs the move and updates the game correspondingly
     */
    public void initiateMoves() {
        // Create some local variables
        boolean hasWinner = false; // boolean to check if there is a winner
        int winner = 0; // player number of winner
        String description1 = ""; // description of what happened during the first move
        String description2 = ""; // description of what happened during the second move
        String description3 = ""; // description of who won
        Legends currentPlayer1 = player1.get(0); // active legend for player 1
        Legends currentPlayer2 = player2.get(0); // active legend for player 2

        // Checks the speed of the Legends
        // Whoever is faster goes first
        if (Stats.checkSpeed(currentPlayer1, currentPlayer2)) { // player 1 is faster
            // Get the description of the first move
            description1 = playerMoves(0);

            // Get the description of the second move
            // Check if the slower player has not fainted (thus swapped)
            if (player2.get(0) == currentPlayer2) {
                description2 = playerMoves(1);
            }
        } else { // player 2 is faster
            // Get the description of the first move
            description1 = playerMoves(1);

            // Get the description of the second move
            // Check if the slower player has not fainted (thus swapped)
            if (player1.get(0) == currentPlayer1) {
                description2 = playerMoves(0);
            }
        }

        // If player 1 doesn't have any players that are alive, set it so that it shows that player 2 wins
        if (!hasAlive(1)) {
            description3 = "Player 2 Wins!";
            winner = 2;
            hasWinner = true;
        }

        // If player 2 doesn't have any players that are alive, set it so that it shows that player 1 wins
        if (!hasAlive(2)) {
            description3 = "Player 1 Wins!";
            winner = 1;
            hasWinner = true;
        }

        // Add the descriptions to the textArea label ("<html>" is used for wrapping and "<br>" is used for next line)
        textArea.setText("<html>" + description1 + "<br>" + description2 + "<br>" + description3 + "</html>");

        // If there is a winner, go to the GameOverPanel
        if (hasWinner) {
            // Create new GameOverPanel
            GameOverPanel nextPanel = new GameOverPanel(winner);
            
            // Set the frame
            nextPanel.setFrame(frame);
            
            // Set the font
            try {
                nextPanel.setFontFile(Paths.get(getClass().getResource("/assets/BreatheFireIii-PKLOB.ttf").toURI()).toFile());
            } catch (URISyntaxException f) {
                f.printStackTrace();
            }

            // Add the elements in the panel
            nextPanel.createPanel();
            // Set the current panel to be not visible
            this.setVisible(false);
            // Add the nextpanel to the frame
            frame.add(nextPanel);
        }        

        // Set the text to player 1 is choosing
        playerChoosing.setText("Player 1 is Choosing...");
        // Clear the moveQueue
        moveQueue.clear();

        // Repaint the panel to add updated characters images
        repaint();
    }

    /**
     * String method that will run the mechanism of the moves
     * Preconditions: Takes in index (0 = Player 1, 1 = Player 2)
     * Postconditions: Runs the mechanisms of the move and returns the description of the move 
     * @param index - int
     * @return description - String
     */
    public String playerMoves(int index) {
        // Create local variables
        ArrayList<Legends> player = new ArrayList<Legends>(); // ArrayList of the player performing the move
        ArrayList<Legends> opponent = new ArrayList<Legends>(); // ArrayList of the opponent
        HashMap<Legends, JProgressBar> opponentHealthMap = new HashMap<Legends, JProgressBar>(); // Health bar of the opponent
        HashMap<Legends, JProgressBar> playerHealthMap = new HashMap<Legends, JProgressBar>(); // Health bar of the player
        String description = ""; // description of the move

        // Get the corresponding information based on the index from the parameter
        // 0 = player 1 is performing the move
        // 1 = player 2 is performing the move
        if (index == 0) {
            player = player1;
            opponent = player2;
            playerHealthMap = player1HealthMap;
            opponentHealthMap = player2HealthMap;
        } else {
            player = player2;
            opponent = player1;
            playerHealthMap = player2HealthMap;
            opponentHealthMap = player1HealthMap;
        }

        // Get the QueuedMove object from the moveQueue ArrayList
        QueuedMove playerMove = moveQueue.get(index);

        // Check the moveType of the move
        // 1 = Attacking Move
        // 2 = Buffing
        // 3 = Swapping
        if (playerMove.getMoveType() == 1) { // Attacking move
            // Set the base description of which move is used
            description = "Player " + (index + 1) + " used "
                    + player.get(0).getMoveset().get(playerMove.getMoveSelected() - 1).getMoveName() + "! ";

            // Check if the move hits
            if (Legends.accuracyCheck(player.get(0).getMoveset().get(playerMove.getMoveSelected() - 1).getAccuracy())) { // Move hits
                // Get the damage that the legend will do to opponent
                int damage = Legends.calcDamage(playerMove.getMoveSelected() - 1, player.get(0), opponent.get(0));
                // Set the hp of the opponenet
                opponent.get(0).getStats().setHP(opponent.get(0).getStats().getHP() - damage);
                // Add to the description of how much damage is done
                description = description + "The move hit! It did " + damage + " damage! ";
            } else { // Move missed
                // Add to the description that the move missed
                description = description + " The move missed! ";
            }
            
            // Update the health bar of the opponent
            opponentHealthMap.get(opponent.get(0)).setValue(opponent.get(0).getStats().getHP());

        } else if (playerMove.getMoveType() == 2) { // Buff move

            // Check if the number of buffs used by the player is less than 5
            if (countNumBuffs[index] < 5) {

                // Set the base description
                description = "Player " + (index + 1) + " buffed " + player.get(0).getName();

                // Check which stat is buffed
                // 1 = HP
                // 2 = Def
                // 3 = Att
                // 4 = Speed
                if (playerMove.getStatBuffed() == 1) { // HP is buffed

                    // Buff the HP
                    Move.heal(player.get(0));

                    //Check if the player is at max health (cannot exceed the maximum health)
                    if (player.get(0).getStats().getHP() > playerHealthMap.get(player.get(0)).getMaximum()) {
                        player.get(0).getStats().setHP(playerHealthMap.get(player.get(0)).getMaximum());
                    }

                    // Update the health bar of the active player
                    playerHealthMap.get(player.get(0)).setValue(player.get(0).getStats().getHP());

                    // Add to the description
                    description = description + "'s HP! It is now " + player.get(0).getStats().getHP() + " HP! ";

                } else if (playerMove.getStatBuffed() == 2) { // Def is buffed
                    // Buff the def
                    Move.buffDefense(player.get(0));

                    // Add to the description
                    description = description + "'s Defense! It is now " + player.get(0).getStats().getDefense()
                            + " Defense! ";

                } else if (playerMove.getStatBuffed() == 3) { // Attack is buffed
                    // Buff the attack
                    Move.buffAttack(player.get(0));

                    // Add to the description
                    description = description + "'s Attack! It is now " + player.get(0).getStats().getAttack()
                            + " Attack! ";
                } else { // Speed is buffed
                    // Buff the speed
                    Move.buffSpeed(player.get(0));

                    // Add to the description
                    description = description + "'s Speed! It is now " + player.get(0).getStats().getSpeed()
                            + " Speed! ";
                }
                
                // Increase the number of buffs done
                countNumBuffs[index]++;

                // Show how many buffs are remaining
                description = description + " Number of Buffs remaining: " + (5 - countNumBuffs[index]);
            }

        } else { // Swapping move
            // Update the image to the new legend that is active
            playerImage[index] = FileFunctions.resizeImage(player.get(0).getImageFile(), 200, 250);

            // Show the health bar of the swapped character + hide health bar od old character
            for (int a = 0; a < player.size(); a++) {
                playerHealthMap.get(player.get(a)).setVisible(false);
            }

            playerHealthMap.get(player.get(0)).setVisible(true);
            
            // Update the display name to show the swapped legend's name
            displayName[index].setText(player.get(0).getName());

            // Add the description
            description = "Player " + (index + 1) + " swapped to " + player.get(0).getName() + "! ";
        }

        // Check if the opponent's legend is alive
        // If fainted, swap to the next legend that is alive
        if (!opponent.get(0).isAlive()) {
            // Add to the description
            description = description + "<br>" + opponent.get(0).getName() + " has fainted! Swapping to ";

            // Swap to the next legend that is alive
            for (int a = 0; a < opponent.size(); a++) {
                if (opponent.get(a).isAlive()) {
                    Legends temp = opponent.remove(a);
                    Legends current = opponent.remove(0);

                    opponent.add(0, temp);
                    opponent.add(current);

                    break;
                }
            }

            // Update the image to the alive legend
            if (index == 0) {
                playerImage[1] = FileFunctions.resizeImage(opponent.get(0).getImageFile(), 200, 250);
            } else {
                playerImage[0] = FileFunctions.resizeImage(opponent.get(0).getImageFile(), 200, 250);
            }

            // Update the health bar to show the alive legend's health bar
            for (int a = 0; a < player.size(); a++) {
                opponentHealthMap.get(opponent.get(a)).setVisible(false);
            }

            opponentHealthMap.get(opponent.get(0)).setVisible(true);

            // Add to the description
            description = description + opponent.get(0).getName() + "! ";

        }

        // Return the description
        return description;
    }

    /**
     * Boolean method that checks if the player's legends are alive
     * Preconditions: Takes in the index (0 = player1, 1 = player2)
     * Postconditions: Return true if player has characters that are alive or false if all are fainted
     * @param index - int
     * @return true/false - boolean
     */
    public boolean hasAlive(int index) {
        // Creates local variable of the player lists
        ArrayList<Legends> player = new ArrayList<Legends>();

        // Gets the corresponding player list
        if (index == 1) {
            player = player1;
        } else {
            player = player2;
        }

        // Int that counts number of fainted legends
        int count = 0;

        // Iterates through the players ArrayList
        for (int a = 0; a < player1.size(); a++) {
            // Check if the legend is alive
            // If fainted, increase count
            if (player.get(0).getStats().getHP() <= 0) {
                count++;
            }
        }

        // If all legends are fainted, return false
        if (count == 3) {
            return false;
        }

        // If has at least 1 legend that is alive, return true
        return true;
    }

    /**
     * Return JButton attackButton
     * @return attackButton - JButton
     */
    public JButton getAttackButton() {
        return attackButton;
    }

    /**
     * Return JButton swapButton
     * @return swapButton - JButton
     */
    public JButton getSwapButton() {
        return swapButton;
    }

    /**
     * Returns JButton buffButton
     * @return buffButton - JButton
     */
    public JButton getBuffButton() {
        return buffButton;
    }

    /**
     * Returns player1 ArrayList
     * @return player1 - ArrayList<Legends>
     */
    public ArrayList<Legends> getPlayer1() {
        return player1;
    }

    /**
     * Returns player1Image 
     * @return player1Image - Image
     */
    public Image getPlayer1Image() {
        return playerImage[0];
    }

    /**
     * Returns player2 ArrayList
     * @return player2 - ArrayList<Legends>
     */
    public ArrayList<Legends> getPlayer2() {
        return player2;
    }

    /**
     * Returns player2Image 
     * @return player2Image - Image
     */
    public Image getPlayer2Image() {
        return playerImage[1];
    }

    /**
     * Returns textLabel Label
     * @return textArea - JLabel
     */
    public JLabel getTextLabel() {
        return textArea;
    }

    /**
     * Returns turn array
     * @return turn - int[]
     */
    public int[] getTurn() {
        return turn;
    }

    /**
     * Sets the player image
     * Preconditions: Gets the image to set it to
     * Postcondtions: Sets the player image
     * @param newPlayerImage
     */
    public void setPlayerImage(Image newPlayerImage) {
        // Sets the image based on the turn
        if (turn[0] > 0) {
            playerImage[0] = newPlayerImage;
        } else {
            playerImage[1] = newPlayerImage;
        }
    }

    /**
     * Changes the turn
     * Postconditions: Mulitplies the turn by -1
     */
    public void changeTurn() {
        turn[0] *= -1;
    }

    /**
     * Returns swapCharacter1 JButton
     * @return swapCharacter1 - JButton
     */
    public JButton getSwapCharacter1() {
        return swapCharacter1;
    }

    /**
     * Returns swapCharacter2 JButton
     * @return swapCharacter2 - JButton
     */
    public JButton getSwapCharacter2() {
        return swapCharacter2;
    }

    /**
     * Returns moveQueue ArrayList
     * @return moveQueue - ArrayList<QueuedMove>
     */
    public ArrayList<QueuedMove> getMoveQueue() {
        return moveQueue;
    }

    /**
     * Return the current active legend of the player
     * Preconditions: Takes in the player number (1,2)
     * Postconditions: Return the current active legend of the player
     * @param playerNum - int
     * @return currentPlayer1/currentPlayer2 - Legends
     */
    public Legends getCurrentCharacter(int playerNum) {
        if (playerNum == 1) {
            return currentPlayer1;
        }

        return currentPlayer2;
    }

    /**
     * Sets the current character of the player
     * Preconditions: Takes in the new current player and the player number
     * Postconditions: Sets the current character of the player
     * @param current
     * @param playerNum
     */
    public void setCurrentCharacter(Legends current, int playerNum) {
        if (playerNum == 1) {
            currentPlayer1 = current;
        } else {
            currentPlayer2 = current;
        }
    }

    /**
     * Returns the buffHPButton JButton
     * @return buffHPButton - JButton
     */
    public JButton getBuffHPButton() {
        return buffHP;
    }

    /**
     * Returns buffDefButton JButton
     * @return buffDefButton - JButton
     */
    public JButton getBuffDefButton() {
        return buffDef;
    }

    /**
     * Returns buffAttButton JButton
     * @return buffAttButton - JButton
     */
    public JButton getBuffAttButton() {
        return buffAtt;
    }

    /**
     * Returns buffSpeedButton JButton
     * @return buffSpeedButton - JButton
     */
    public JButton getBuffSpeedButton() {
        return buffSpeed;
    }

    /**
     * Returns move1Button JButton
     * @return move1Button - JButton
     */
    public JButton getMove1Button() {
        return move1Button;
    }

    /**
     * Returns move2Button JButton
     * @return move2Button - JButton
     */
    public JButton getMove2Button() {
        return move2Button;
    }

    /**
     * Returns move3Button JButton
     * @return move3Button - JButton
     */
    public JButton getMove3Button() {
        return move3Button;
    }

    /**
     * Returns countNumBuffs array
     * @return countNumBuffs - int[]
     */
    public int[] getCountNumBuffs() {
        return countNumBuffs;
    }

    /**
     * Returns playerChoosing JLabel
     * @return playerChoosing - JLabel
     */
    public JLabel getPlayerChoosingLabel() {
        return playerChoosing;
    }

    /**
     * Returns displayName array
     * @return displayName - JLabel[]
     */
    public JLabel[] getDisplayNameLabels() {
        return displayName;
    }

    /**
     * This method paints the images and shapes in the panel
     * Preconditions: GamePanel object is initialized
     * Postconditions: Paints the images and shapes in the panel
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Paints the battlefield image
        g.drawImage(battleBackground, 0, 0, this);

        // Draw white rectangles 
        g.setColor(Color.WHITE);
        g.fillRect(150, 75, 220, 270);
        g.fillRect(495, 75, 220, 270);

        // Draws the images of the active legends
        g.drawImage(playerImage[0], 160, 85, this);
        g.drawImage(playerImage[1], 505, 85, this);
    }

}
