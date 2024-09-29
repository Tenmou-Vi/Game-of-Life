import static org.junit.Assert.*;
import org.junit.Test;
import GameofLife.Cell;
import GameofLife.CellState;

public class CellTest {

    @Test
    public void testInitialState() {
        Cell cell = new Cell(CellState.ALIVE);
        assertEquals(CellState.ALIVE, cell.getState());
    }

    @Test
    public void testSwitchState() {
        Cell cell = new Cell(CellState.DEAD);
        cell.switchState();
        assertEquals(CellState.ALIVE, cell.getState());
        cell.switchState();
        assertEquals(CellState.DEAD, cell.getState());
    }

}

