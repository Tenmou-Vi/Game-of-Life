package GameofLife;

import javax.swing.Timer;

/**
 * Controls the game logic and handles interactions between the game model and view.
 * GameController is responsible for managing the game's state progression, responding to
 * user interactions, and updating the visual representation of the game through the GameView.
 */
public class GameController {
    private GameBoard board;
    private GameView view;
    private Timer timer;
    private int generationCount = 0;


    /**
     * Constructor for GameController with a specified view and board.
     *
     * @param view  The game view that displays the game's state.
     * @param board The game board that represents the game's logical state.
     */
    public GameController(GameView view, GameBoard board) {
        this.view = view;
        this.board = board;
        this.view.registerController(this);
    }

    /**
     * Starts the game or resumes it from a paused state by initiating the timer
     * that updates the game board at regular intervals.
     */
    public void startGame() {
        if (timer == null) {
            timer = new Timer(800, e -> updateBoard()); // update the game board every 0.8 second
        }
        timer.start();
    }


    /**
     * Stops the game by stopping the timer, effectively pausing the game and halting
     * the automatic progression of generations.
     */

    public void stopGame() {
        if (timer != null) {
            timer.stop();
        }
    }

    /**
     * Updates the game board to its next generation state and requests the view
     * to redraw the board.
     */

    public void updateBoard() {
        board.computeNextGen();
        generationCount++;
        view.displayGeneration(generationCount);
        view.drawBoard(board);
    }

    /**
     * Responds to user clicks on a cell by toggling the cell's state and updating the display.
     *
     * @param x The x-coordinate of the clicked cell.
     * @param y The y-coordinate of the clicked cell.
     */
    public void cellClicked(int x, int y) {
        Cell cell = board.getCell(x, y);
        cell.switchState();
        view.updateCellDisplay(x, y, cell.getState());
    }

    /**
     * Changes the size of the game board and updates the display to reflect the new size.
     *
     * @param length The new length of the game board.
     * @param width  The new width of the game board.
     */

    public void changeBoardSize(int length, int width) {
        board.resizeBoard(length, width);
        view.drawBoard(board);
    }

    public void resetGame() {
        generationCount = 0;  // Reset the generation count
        board.resetBoard();   // Reset the game board
        view.displayGeneration(generationCount);  // Update the generation display
        view.drawBoard(board);  // Redraw the board
    }

}
