import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import javax.swing.*;
import java.awt.*;
import GameofLife.CellView;
import GameofLife.CellState;
import GameofLife.GameController;

public class CellViewTest {

    private CellView cellView;
    private GameController controller;

    @Before
    public void setUp() {
        controller = new GameController(null, null); // Mock controller
        cellView = new CellView(0, 0, CellState.DEAD, controller);
    }

    @Test
    public void testInitialState() {
        assertEquals(Color.WHITE, cellView.getBackground());
    }

    @Test
    public void testSetState() {
        cellView.setState(CellState.ALIVE);
        assertEquals(Color.BLACK, cellView.getBackground());
        cellView.setState(CellState.DEAD);
        assertEquals(Color.WHITE, cellView.getBackground());
    }

    @Test
    public void testMouseClick() {
        // Simulate mouse click
        cellView.dispatchEvent(new MouseEvent(cellView, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false));
        assertEquals(Color.BLACK, cellView.getBackground());
    }
}

