import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;

import GameofLife.GameView;
import GameofLife.GameBoard;
import GameofLife.GameController;
import GameofLife.CellState;

public class GameViewTest {

    private GameView gameView;
    private GameBoard board;
    private GameController controller;

    @Before
    public void setUp() {
        // Mock the controller to avoid initializing the full game logic
        controller = mock(GameController.class);
        gameView = new GameView();
        board = new GameBoard(10, 10);
        gameView.registerController(controller);
    }

    @Test
    public void testButtonInteractions() {
        JButton startButton = findButton(gameView, "START THE GAME!");
        JButton stopButton = findButton(gameView, "STOP");
        JButton sizeButton = findButton(gameView, "SET GAME BOARD SIZE");
        JButton resetButton = findButton(gameView, "RESET");

        assertNotNull(startButton);
        assertNotNull(stopButton);
        assertNotNull(sizeButton);
        assertNotNull(resetButton);

        // Simulate button clicks and verify controller actions
        startButton.doClick();
        verify(controller, times(1)).startGame();

        stopButton.doClick();
        verify(controller, times(1)).stopGame();

        sizeButton.doClick();
        verify(controller, times(0)).changeBoardSize(anyInt(), anyInt()); // verify no call as it requires user input

        resetButton.doClick();
        verify(controller, times(1)).resetGame();
    }

    @Test
    public void testDisplayGeneration() {
        // Directly test the display update method
        gameView.displayGeneration(5);
        assertEquals("Generation: 5", ((JLabel) gameView.getContentPane().getComponent(2)).getText());
    }

    private JButton findButton(Container container, String text) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                if (text.equals(button.getText())) {
                    return button;
                }
            } else if (comp instanceof Container) {
                JButton button = findButton((Container) comp, text);
                if (button != null) return button;
            }
        }
        return null;
    }
}

