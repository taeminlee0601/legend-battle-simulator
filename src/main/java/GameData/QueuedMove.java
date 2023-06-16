package GameData;

public class QueuedMove {
    private int moveType;
    private int moveSelected;
    private int statBuffed;

    // For attacking and buffing
    public QueuedMove(int moveType, int optionChosen) {
        this.moveType = moveType;
        
        if (this.moveType == 1) {
            this.moveSelected = optionChosen;
        } else {
            this.statBuffed = optionChosen;
        }
    }

    // For swapping
    public QueuedMove(int moveType) {
        this.moveType = moveType;
    }

    public int getMoveType() {
        return moveType;
    }

    public int getMoveSelected() {
        return moveSelected;
    }

    public int getStatBuffed() {
        return statBuffed;
    }

}
