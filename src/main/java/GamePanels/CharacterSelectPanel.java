package GamePanels;

// import required packages
import javax.swing.*;
import ActionListeners.CharacterSelectPanelActionListeners.CharacterSelectButtonActionListener;
import ActionListeners.CharacterSelectPanelActionListeners.SelectLeftButtonActionListener;
import ActionListeners.CharacterSelectPanelActionListeners.SelectRightButtonActionListener;
import java.awt.*;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import GameData.*;
import MainGameFrame.FileFunctions;

/*
 * This class creates the character select panel
 * Inhertes ParentPanel
 */
public class CharacterSelectPanel extends ParentPanel {
    // Create the instance variables needed in this class
    // Create the button that will show the options on the left
    private JButton leftButton = new JButton();
    // Create the button that will show the options on the right
    private JButton rightButton = new JButton();
    // Create the button array that will have the image of the characters
    private JButton[] buttonArray = new JButton[3];
    // Create the label that displays the type
    private JLabel typeTitle = new JLabel();
    // Create the label array that will have the name of characters displayed
    private JLabel[] nameArray = new JLabel[3];
    // Create the label array that will display the descriptions
    private JLabel[] descriptionArray = new JLabel[3];
    // Create the Legends array that contains the legends that are currently displayed
    private Legends[] currentDisplayed = new Legends[3];
    // Create a HashMap that has a string key (type of the characters) and the ArrayList<Legends> elements (legends of the type)
    private HashMap<String, ArrayList<Legends>> legendsMap = new HashMap<String, ArrayList<Legends>>();
    // Create an ArrayList<String> that contains all the type of the characters
    private ArrayList<String> legendType = new ArrayList<String>();
    // Create an ArrayList<Legends> that contains the legends that player 1 selected
    private ArrayList<Legends> player1 = new ArrayList<Legends>();
    // Create an ArrayList<Legends> that contains the legends that player 2 selected
    private ArrayList<Legends> player2 = new ArrayList<Legends>();
    // Create a boolean array that contains 1 element (needed as "var = array" stores the same memory)
    private boolean[] hasPopupOpened = {false};
    // Create a JLabel to show the characters selected by player1
    private JLabel player1Label = new JLabel();
    // Create a JLabel to show the characters selected by player2
    private JLabel player2Label = new JLabel();
    // Create an Image array to for the characters that are chosen by player1
    private ArrayList<Image> player1Image = new ArrayList<Image>();
    // Create an Image array to for the characters that are chosen by player2
    private ArrayList<Image> player2Image = new ArrayList<Image>();
    
    /**
     * Creates the CharacterSelectPanel object and adds the elements into the legendsMap
     */
    public CharacterSelectPanel() {
        try {
            setCharacterHashMap();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the instance variables of the given parameters and sets the legendsType arraylist to the beginning
     * @param player1 - (ArrayList<Legends>) contains the choices of player 1
     * @param hasPopupOpened - (boolean[]) contains if the popup has been opened
     * @param legendsMap - (HashMap<String,ArrayList<Legends>>) contains the legends with the types as keys
     * @param legendsType - (ArrayList<String>) contains the type of legends
     */
    public CharacterSelectPanel(ArrayList<Legends> player1, boolean[] hasPopupOpened, HashMap<String,ArrayList<Legends>> legendsMap,
            ArrayList<String> legendsType, ArrayList<Image> player1Image) {
        // Sets the instance variables from the given parameters
        this.player1 = player1;
        this.hasPopupOpened = hasPopupOpened;
        this.legendsMap = legendsMap;
        this.legendType = legendsType;
        this.player1Image = player1Image;

        // Sets "Outer God" as the first element in list
        setToStartTypeList();
    }

    /**
     * This will set the background file and font file
     * @param backgroundFile - Type: File (File of the Background Image)
     * @param fontFile - Type: File (File of the font)
     */
    public CharacterSelectPanel(File backgroundFile, File fontFile) {
        super(backgroundFile, fontFile);
    }

    /**
     * Adds the elements of the CharacterSelectPanel
     * Postcondition: Creates and adds the elements to the panel
     */
    public void createPanel() {
        // Initializes the panel setting
        setVisible(true);
        setSize(900,600);
        setLayout(null);
        
        // Creates the font 
        createFont();

        // Initializes the settings of the buttons in buttonArray and the labels in NameArray
        for (int a = 0; a < buttonArray.length; a++) {  
            // Sets the currentDisplayed legend at index a        
            currentDisplayed[a] = legendsMap.get(legendType.get(0)).get(a);

            // Creates a new button in the buttonArray at index a
            // Initialize the settings of the button
            buttonArray[a] = new JButton();
            buttonArray[a].setBounds(75+(a*253), 115,228,275);
            // Sets the image of the button
            // Resizes the image of the legend and adds the image to the button
            buttonArray[a].setIcon(new ImageIcon(FileFunctions.resizeImage(currentDisplayed[a].getImageFile(), 228, 275)));

            // Creates a new label in the nameArray at index a
            // Initialize the settings of the label
            nameArray[a] = new JLabel();
            nameArray[a].setFont(customFont.deriveFont(30f));
            nameArray[a].setForeground(Color.WHITE);
            nameArray[a].setText(currentDisplayed[a].getName());
            nameArray[a].setHorizontalAlignment(SwingConstants.CENTER);
            nameArray[a].setBounds(75+(a*253), 375, 228, 100);

            // Creates a new label in the descriptionArray at index a
            // Initializse the seetings of the label
            descriptionArray[a] = new JLabel();
            descriptionArray[a].setFont(customFont.deriveFont(20f));
            descriptionArray[a].setText(setDescription(currentDisplayed[a].getDescription()));
            descriptionArray[a].setHorizontalAlignment(SwingConstants.CENTER);
            descriptionArray[a].setForeground(Color.WHITE);
            descriptionArray[a].setBounds(75+(a*253), 425, 228, 125);
        }

        // Initializes the settings of the label
        typeTitle.setFont(customFont);
        typeTitle.setText(legendType.get(0));
        typeTitle.setBounds(getWidth()/2 - 255, 25, 500, 65);
        typeTitle.setHorizontalAlignment(SwingConstants.CENTER);
        typeTitle.setForeground(Color.WHITE);

        // Set the font of the left and right button to custom font with the size of 30
        leftButton.setFont(customFont.deriveFont(30f));
        rightButton.setFont(customFont.deriveFont(30f));

        // Initialize the settings of the left button
        leftButton.setText("<");
        leftButton.setOpaque(false);
        leftButton.setContentAreaFilled(false);
        leftButton.setBorderPainted(false);
        leftButton.setBounds(0, 0, 50, 600);
        leftButton.setForeground(Color.WHITE);

        // Initializes the settings of the right button
        rightButton.setText(">");
        rightButton.setOpaque(false);
        rightButton.setContentAreaFilled(false);
        rightButton.setBorderPainted(false);
        rightButton.setBounds(835, 0, 50, 600);
        rightButton.setForeground(Color.WHITE);

        //Initializes the settings of the player 1 label
        player1Label.setFont(customFont.deriveFont(10f));
        player1Label.setText("Player 1");
        player1Label.setBounds(75, 15, 165, 50);
        player1Label.setHorizontalAlignment(SwingConstants.CENTER);
        player1Label.setVerticalAlignment(SwingConstants.CENTER);
        player1Label.setForeground(Color.WHITE);

        //Initializes the settings of the player 1 label
        player2Label.setFont(customFont.deriveFont(10f));
        player2Label.setText("Player 2");
        player2Label.setBounds(645, 15, 165, 50);
        player2Label.setHorizontalAlignment(SwingConstants.CENTER);
        player2Label.setVerticalAlignment(SwingConstants.CENTER);
        player2Label.setForeground(Color.WHITE);

        // Add an action listener to the left and right button
        leftButton.addActionListener(new SelectLeftButtonActionListener(buttonArray, legendType, legendsMap, typeTitle, 
            nameArray, currentDisplayed, descriptionArray));
        rightButton.addActionListener(new SelectRightButtonActionListener(buttonArray, legendType, legendsMap, typeTitle,
            nameArray, currentDisplayed, descriptionArray));

        // Add an action listener to each of the buttons displaying the character's image
        buttonArray[0].addActionListener(new CharacterSelectButtonActionListener(currentDisplayed, 0, player1, player2, frame, 
            hasPopupOpened, this, legendsMap, legendType, player1Image, player2Image));
        buttonArray[1].addActionListener(new CharacterSelectButtonActionListener(currentDisplayed, 1, player1, player2, frame, 
            hasPopupOpened, this, legendsMap, legendType, player1Image, player2Image));
        buttonArray[2].addActionListener(new CharacterSelectButtonActionListener(currentDisplayed, 2, player1, player2, frame, 
            hasPopupOpened, this, legendsMap, legendType, player1Image, player2Image));

        // Add the left button, right button, and the type label to the panel
        add(leftButton);
        add(rightButton);
        add(typeTitle);
        add(player1Label);
        add(player2Label);

        // Add the buttons displaying the character's images to the panel
        for (int a = 0; a < buttonArray.length; a++) {
            add(buttonArray[a]);
            add(nameArray[a]);
            add(descriptionArray[a]);
        }
    }

    /**
     * This method will set the character hash map contain the legends
     * Preconditions: CharacterSelectPanel is created
     * Postcondtions: Sets the legendsMaps hashMap (keys = type of character, values = arraylist of legends) and 
     * the legendType arraylist
     * @throws URISyntaxException
     */
    public void setCharacterHashMap() throws URISyntaxException {
        // Adds the type of legends to the legendType arraylist
        legendType.add("Outer Gods");
        legendType.add("Meme Gods");
        legendType.add("Norse Gods");
        legendType.add("Egyptian Gods");
        legendType.add("Olympus Gods");

        // Sets the legendType elements as the keys of the legendMap hashMap
        for (int a = 0; a < legendType.size(); a++) {
            legendsMap.put(legendType.get(a), new ArrayList<Legends>());
        }

        // Creates a new LegendsInfo object
        LegendsInfo legendsInfo = new LegendsInfo();

        // Gets the legendlist
        ArrayList<Legends> legendList = legendsInfo.getLegendsList();

        // Set count to zero
        int count = 0;

        // Add the legends to the legendsMap
        for (int a = 0; a < legendList.size(); a++) {
            // Goes to the next type ever 3 legends added to the arraylist
            if (a % 3 == 0 && a > 0) {
                count++;
            }
            
            legendsMap.get(legendType.get(count)).add(legendList.get(a));
        }
    }
    
    /**
     * This method will set the legendType so that the Outer Gods type is in the first element
     * Preconditions: Player 1 or 2 click "No" in the confirm box popup
     * Postconditions: Sets the legendType arraylist first element to be Outer Gods 
     */
    public void setToStartTypeList() {
        while (!legendType.get(0).equals("Outer Gods")) {
            String temp = legendType.remove(0);
            legendType.add(temp);
        }
    }

    /**
     * This method modifies the description string so that the string has "<html>" in front and "</html>" at the end
     * Preconditions: Takes in the description string
     * Postconditions: Returns a string with "<html>" in front and "</html>" at the end (Needed for wrapping text in JLabel)
     * @param description - String
     * @return description - String
     */
    public String setDescription(String description) {
        return "<html>" + description + "</html>";
    }

    /**
     * This method paints images and shapes on the panel
     * Preconditions: CharacterSelectPanel Object is created
     * Postconditions: Paints the images and shapes at the location stated
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Paints the background
        g.drawImage(background, 0, 0, this);

        // Draws the rectangles in top corners (Faces are then painted there)
        for (int a = 0; a < 3; a++) {
            g.drawRect(75+(a*55), 50, 50, 50);
            g.drawRect(647+(a*55),50,50,50);
        }

        // Paint the player1 faces
        for (int a = 0; a < player1Image.size(); a++) {
            g.drawImage(player1Image.get(a), 75+(a*55), 50, this);
        }

        // Paint the player2 faces
        for (int a = 0; a < player2Image.size(); a++) {
            g.drawImage(player2Image.get(a), 645+(a*55), 50, this);
        }
    }
}
