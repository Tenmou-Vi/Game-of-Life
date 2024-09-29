import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import GameofLife.GameController;
import GameofLife.GameView;
import GameofLife.GameBoard;

public class GameControllerTest {
    private GameController controller;
    private GameBoard board;
    private GameView view;

    @Before
    public void setUp() {
        board = new GameBoard(5, 5);
        view = new GameView();  // Assuming it has a constructor that doesn't require parameters
        controller = new GameController(view, board);
    }

    @Test
    public void testCellClicked() {
        // Simulate a click on a dead cell
        controller.cellClicked(2, 2);
        assertEquals(CellState.ALIVE, board.getCellState(2, 2));

        // Simulate another click to toggle back
        controller.cellClicked(2, 2);
        assertEquals(CellState.DEAD, board.getCellState(2, 2));
    }

    @Test
    public void testResetGame() {
        // Set some cells to alive
        board.setCellState(0, 0, CellState.ALIVE);
        board.setCellState(1, 1, CellState.ALIVE);
        board.setCellState(2, 2, CellState.ALIVE);

        controller.resetGame();
        // Check all cells are reset to DEAD
        for (int i = 0; i < board.getBoardLength(); i++) {
            for (int j = 0; j < board.getBoardWidth(); j++) {
                assertEquals(CellState.DEAD, board.getCellState(i, j));
            }
        }
    }
}
