package ActionListeners.GamePanelActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import GamePanels.GamePanel;

public class BuffActionListeners implements ActionListener {
    private GamePanel currentPanel;
    private JButton buffHP;
    private JButton buffDef;
    private JButton buffAtt;
    private JButton buffSpeed;
    private JButton attackButton;
    private JButton buffButton;
    private JButton swapButton;


    public BuffActionListeners(GamePanel currentPanel) {
        this.currentPanel= currentPanel;

        this.buffHP = currentPanel.getBuffHPButton();
        this.buffDef = currentPanel.getBuffDefButton();
        this.buffAtt = currentPanel.getBuffAttButton();
        this.buffSpeed = currentPanel.getBuffSpeedButton();

        this.attackButton = currentPanel.getAttackButton();
        this.buffButton = currentPanel.getBuffButton();
        this.swapButton = currentPanel.getSwapButton();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        buffHP.setVisible(true);
        buffAtt.setVisible(true);
        buffDef.setVisible(true);
        buffSpeed.setVisible(true);

        attackButton.setVisible(false);
        buffButton.setVisible(false);
        swapButton.setVisible(false);
    }
    
}
