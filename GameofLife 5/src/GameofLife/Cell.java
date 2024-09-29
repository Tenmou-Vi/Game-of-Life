package GameofLife;

/**
 * Represents a single cell in Conway's Game of Life.
 * Each cell in the game can be in one of two states: ALIVE or DEAD.
 * The state of the cell can change from generation to generation based on the rule of this game
 */

public class Cell {
    private CellState state;

    /**
     * Constructs a new Cell with the specified initial state.
     *
     * @param state the initial state of the cell, either ALIVE or DEAD
     */
    public Cell(CellState state) {
        this.state = state;
    }


    /**
     * Returns the current state of the cell.
     *
     * @return the current state of the cell, either ALIVE or DEAD
     */
    public void setState(CellState state) {
        this.state = state;
    }

    // Getter method to get the state of a cell
    public CellState getState() {
        return this.state;
    }

    /**
     * This method toggles the cell's state from DEAD to ALIVE or vice versa
     */
    public void switchState() {
        if (this.state == CellState.ALIVE) {
            this.state = CellState.DEAD;
        } else {
            this.state = CellState.ALIVE;
        }
    }


}
