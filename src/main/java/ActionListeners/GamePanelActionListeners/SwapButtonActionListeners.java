package ActionListeners.GamePanelActionListeners;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import GameData.Legends;
import GamePanels.GamePanel;

public class SwapButtonActionListeners implements ActionListener {
    private ArrayList<Legends> player;
    private GamePanel currentPanel;
    private JButton option1;
    private JButton option2;
    private JButton attackButton;
    private JButton swapButton;
    private JButton buffButton;
    private int[] turn;
    private int[] count = {0};

    public SwapButtonActionListeners(GamePanel currentPanel) {
        this.currentPanel = currentPanel;
        this.attackButton = currentPanel.getAttackButton();
        this.swapButton = currentPanel.getSwapButton();
        this.buffButton = currentPanel.getBuffButton();
        this.turn = currentPanel.getTurn();
        this.option1 = currentPanel.getSwapCharacter1();
        this.option2 = currentPanel.getSwapCharacter2();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (turn[0] > 0) {
            this.player = currentPanel.getPlayer1();
        } else {
            this.player = currentPanel.getPlayer2();
        }

        option1.setText("Swap to " + player.get(1).getName());
        option1.setVisible(true);

        option2.setText("Swap to " + player.get(2).getName());
        option2.setVisible(true);

        attackButton.setVisible(false);
        swapButton.setVisible(false);
        buffButton.setVisible(false);
    }
    
}
