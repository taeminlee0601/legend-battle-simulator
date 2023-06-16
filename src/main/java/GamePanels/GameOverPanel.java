package GamePanels;

import javax.swing.*;

import ActionListeners.StartPanelActionListeners.ExitButtonActionListener;
import ActionListeners.StartPanelActionListeners.PlayButtonActionListener;

import java.awt.*;

public class GameOverPanel extends ParentPanel {
    private int winner;
    private JLabel winnerMessage = new JLabel();
    private JButton playButton = new JButton();
    private JButton exitButton = new JButton();

    public GameOverPanel(int winner) {
        this.winner = winner;
    }

    public void createPanel() {
        setVisible(true);
        setSize(900, 600);
        setLayout(null);

        createFont();

        // Set the font of the buttons as the custom font
        playButton.setFont(customFont);
        exitButton.setFont(customFont);

        // Initialize the settings of the play button
        // Set the text to "Play"
        playButton.setText("Play Again");
        // Make the button transparent
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        // Make the border of the button not visible
        playButton.setBorderPainted(false);
        // Set the location and the size of but button
        playButton.setBounds(getWidth()/2 - 250, getHeight()/2, 300, 100);

        // Initialize the settings of the exit button
        // Set the text to "Exit"
        exitButton.setText("Exit");
        // Make the button transparent
        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        // Make the border of the butotn not visible
        exitButton.setBorderPainted(false);
        // Set the location and size of the button
        exitButton.setBounds(getWidth()/2 + 50, getHeight()/2, 200, 100);

        // Add an action listener to both buttons
        playButton.addActionListener(new PlayButtonActionListener(this, frame));
        exitButton.addActionListener(new ExitButtonActionListener());

        winnerMessage.setText("Player " + winner + " Won!");
        // Set custom font
        winnerMessage.setFont(customFont.deriveFont(70f));
        // Set the location and size of the button
        winnerMessage.setBounds(getWidth()/2 - 350, getHeight()/2 - 100, 700, 100);
        winnerMessage.setHorizontalAlignment(SwingConstants.CENTER);

        add(playButton);
        add(exitButton);
        add(winnerMessage);
    }

    /**
     * Paints the background of the panel
     */
    @Override
    public void paintComponent(Graphics g) {
        // Create a paint component
        super.paintComponent(g);

        // Set the background image of the panel
        g.drawImage(background, 0, 0, this);
    }

}
