import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import GameofLife.GameBoard;
import GameofLife.CellState;

public class GameBoardTest {
    private GameBoard board;

    @Before
    public void setUp() {
        board = new GameBoard(3, 3); // A small board for simple tests
    }

    @Test
    public void testCountLiveNeighbors() {
        // Set up a specific board state
        board.setCellState(0, 0, CellState.ALIVE);
        board.setCellState(1, 1, CellState.ALIVE);
        board.setCellState(2, 2, CellState.ALIVE);

        assertEquals(1, board.countLiveNeighbors(0, 0));
        assertEquals(2, board.countLiveNeighbors(1, 1));
        assertEquals(1, board.countLiveNeighbors(2, 2));
    }

    @Test
    public void testNextGeneration() {
        // An initial pattern known to change
        board.setCellState(0, 1, CellState.ALIVE);
        board.setCellState(1, 1, CellState.ALIVE);
        board.setCellState(2, 1, CellState.ALIVE);

        board.computeNextGen();

        // Test the toggling from vertical line to horizontal line
        assertEquals(CellState.ALIVE, board.getCellState(1, 0));
        assertEquals(CellState.ALIVE, board.getCellState(1, 1));
        assertEquals(CellState.ALIVE, board.getCellState(1, 2));
        assertEquals(CellState.DEAD, board.getCellState(0, 1));
        assertEquals(CellState.DEAD, board.getCellState(2, 1));
    }
}

