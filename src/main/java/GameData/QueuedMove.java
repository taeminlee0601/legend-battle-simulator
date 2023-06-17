package GameData;

/*
 * This class stores information about the moves that the players pick
 */
public class QueuedMove {
    // Create instance variables
    private int moveType;
    private int moveSelected;
    private int statBuffed;

    /**
     * This is a constructor for the attacking and buffing moves
     * 
     * moveType = 1 (Attacking move)
     * moveType = 2 (Buffing move)
     * 
     * (If the move is an attacking move)
     * optionChosen = 1 (Move 1)
     * optionChosen = 2 (Move 2)
     * optionChosen = 3 (Move 3)
     * 
     * (If the move is an buffing move)
     * optionChosen = 1 (Buff HP)
     * optionChosen = 2 (Buff Def)
     * optionChosen = 3 (Buff Attack) 
     * optionChosen = 4 (Buff Speed)
     * 
     * Preconditions: Takes in the moveType (which move they chose) and optionChosen (which option of the move they chose)
     * Postconditions: Sets the instance variables with the parameters given
     * @param moveType - int
     * @param optionChosen - int
     */
    public QueuedMove(int moveType, int optionChosen) {
        this.moveType = moveType;
        
        if (this.moveType == 1) {
            this.moveSelected = optionChosen;
        } else {
            this.statBuffed = optionChosen;
        }
    }

    // For swapping
    /**
     * This is a constructor for swapping
     * 
     * moveType == 3
     * 
     * Preconditions: Takes in the moveType (Swapping = 3)
     * Postconditions: Sets the instance variables with the parameters given
     * @param moveType
     */
    public QueuedMove(int moveType) {
        this.moveType = moveType;
    }

    /**
     * Returns the moveType (1,2,3)
     * @return moveType - int
     */
    public int getMoveType() {
        return moveType;
    }

    /**
     * Return the moveSelected (1,2,3)
     * @return moveSelected - int
     */
    public int getMoveSelected() {
        return moveSelected;
    }

    /**
     * Returns the statBuffed (1,2,3)
     * @return statBuffed - int
     */
    public int getStatBuffed() {
        return statBuffed;
    }

}
