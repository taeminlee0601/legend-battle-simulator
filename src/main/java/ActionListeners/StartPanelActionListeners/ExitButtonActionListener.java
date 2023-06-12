package ActionListeners.StartPanelActionListeners;

// Import required packages
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * This class will end the game when the exit button is clicked
 * Implements ActionListener
 */
public class ExitButtonActionListener implements ActionListener {

    /**
     * Overrides the actionPerformed method
     * Postconditions: ends the code when the button is clicked
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Exiting");
        System.exit(0);
    }
    
}
