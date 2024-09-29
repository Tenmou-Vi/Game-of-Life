package GameofLife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Represents the graphical user interface component for a cell in Conway's Game of Life.
 * This class extends JPanel and is responsible for displaying the cell's state
 * and handling mouse click events that trigger state changes via the game controller.
 */

public class CellView extends JPanel {
    private int x, y;
    private CellState state;
    private GameController controller;

    /**
     * Constructs a CellView with specified coordinates, initial state, and controller.
     *
     * @param x the x-coordinate of the cell in the grid
     * @param y the y-coordinate of the cell in the grid
     * @param state the initial state of the cell, either ALIVE or DEAD
     * @param controller the game controller responsible for managing game actions
     */
    public CellView(int x, int y, CellState state, GameController controller) {
        this.x = x;
        this.y = y;
        this.state = state;
        this.controller = controller;  // Initialize the controller
        setBackground(state == CellState.ALIVE ? Color.BLACK : Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.cellClicked(x, y);
            }
        });
    }

    /**
     * Sets the state of this cell and updates its background color accordingly.
     *
     * @param newState the new state of the cell, either ALIVE or DEAD
     */
    public void setState(CellState newState) {
        this.state = newState;
        setBackground(newState == CellState.ALIVE ? Color.BLACK : Color.WHITE);
        repaint();  // Ensure the panel is repainted immediately
    }
}