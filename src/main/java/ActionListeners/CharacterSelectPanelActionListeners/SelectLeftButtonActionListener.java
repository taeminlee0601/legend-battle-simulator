package ActionListeners.CharacterSelectPanelActionListeners;

// Import required packages
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import GameData.Legends;
import MainGameFrame.FileFunctions;

/*
 * This class is the action listener for the left button in the character select panel
 */
public class SelectLeftButtonActionListener implements ActionListener{
    // Create the necessary instance variables
    private JButton[] buttonArray;
    private ArrayList<String> legendType;
    private HashMap<String, ArrayList<Legends>> legendsMap;
    private JLabel typeTitle;
    private JLabel[] nameArray;
    private Legends[] currentDisplayed;
    private JLabel[] descriptionArray;

    /**
     * This is the constructor of the class
     * Preconditions: Takes in the parameters
     * Postconditions: Sets the corresponding instance variables with the parameters
     * @param buttonArray - JButton[]
     * @param legendType - ArrayList<String>
     * @param legendsMap - HashMap<String, ArrayList<Legends>>
     * @param typeTitle - JLabel
     * @param nameArray - JLabel[]
     * @param currentDisplayed - Legends[]
     * @param descriptionArray - JLabel[]
     */
    public SelectLeftButtonActionListener(JButton[] buttonArray, ArrayList<String> legendType, 
            HashMap<String,ArrayList<Legends>> legendsMap, JLabel typeTitle, JLabel[] nameArray, Legends[] currentDisplayed,
            JLabel[] descriptionArray) {
        this.buttonArray = buttonArray;
        this.legendType = legendType;
        this.legendsMap = legendsMap;
        this.typeTitle = typeTitle;
        this.nameArray = nameArray;
        this.currentDisplayed = currentDisplayed;
        this.descriptionArray = descriptionArray;
    }

    /**
     * This method will change the images in the button, and the text in the labels
     * Precondition: Button must be clicked
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Gets the current type
        String currentType = legendType.remove(0);

        // Sets the text of the label to the current type
        typeTitle.setText(currentType);

        // Iterates throught the for loop and changes the image, and the labels
        for (int a = 0; a < buttonArray.length; a++) {
            currentDisplayed[a] = legendsMap.get(currentType).get(a);
            buttonArray[a].setIcon(new ImageIcon(FileFunctions.resizeImage(currentDisplayed[a].getImageFile(),228, 275)));
            nameArray[a].setText(currentDisplayed[a].getName());
            descriptionArray[a].setText(setDescription(currentDisplayed[a].getDescription()));
        }

        // Adds the current type to the end of the list
        legendType.add(currentType);
    }

    /**
     * This method will add "<html>" in the front and "</html>" at the back
     * Preconditions: Need a string in the parameter
     * Postconditions: Returns a modified string
     * @param text
     * @return String
     */
    public String setDescription(String text) {
        return "<html>" + text + "</html>";
    }
}
