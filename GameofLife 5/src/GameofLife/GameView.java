package GameofLife;

import javax.swing.*;
import java.awt.*;

/**
 * Main view component for the game
 * This class extends JFrame and serves as the primary user interface
 * It sets up the game board, control buttons, and manages interactions between the user and the game.
 */

public class GameView extends JFrame {
    private GameBoard board;
    private GameController controller;
    private JPanel boardPanel;
    private JLabel generationLabel;
    private int generationCount = 0;

    /**
     * Constructs the main game window and initializes all components.
     */
    public GameView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLayout(new BorderLayout());

        boardPanel = new JPanel();
        add(boardPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        JButton startButton = new JButton("START THE GAME!");
        JButton stopButton = new JButton("STOP");
        JButton sizeButton = new JButton("SET GAME BOARD SIZE");
        JButton resetButton = new JButton("RESET");

        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        controlPanel.add(sizeButton);
        controlPanel.add(resetButton);

        add(controlPanel, BorderLayout.SOUTH);
        generationLabel = new JLabel("Generation: 0");
        generationLabel.setFont(new Font("Arial", Font.BOLD, 26));
        add(generationLabel, BorderLayout.NORTH);

        // Initialize GameController and GameBoard
        board = new GameBoard(15, 15); // Assuming default size of the game board to be 15
        controller = new GameController(this, board);
        registerController(controller);

        startButton.addActionListener(e -> controller.startGame());
        stopButton.addActionListener(e -> controller.stopGame());
        sizeButton.addActionListener(e -> promptBoardSize());
        resetButton.addActionListener(e -> controller.resetGame());

        drawBoard(board);
        setVisible(true); // Make the JFrame visible

    }

    /**
     * Draws the game board with cells represented by CellView components.
     *
     * @param board The GameBoard to draw.
     */

    public void drawBoard(GameBoard board) {
        boardPanel.removeAll();
        boardPanel.setLayout(new GridLayout(board.getBoardLength(), board.getBoardWidth()));
        for (int i = 0; i < board.getBoardLength(); i++) {
            for (int j = 0; j < board.getBoardWidth(); j++) {
                CellView cell = new CellView(i, j, board.getCellState(i, j), controller);
                boardPanel.add(cell);
            }
        }
        boardPanel.validate();
        boardPanel.repaint();
    }

    /**
     * Updates the display of a specific cell on the board.
     *
     * @param x     the x-coordinate of the cell to update
     * @param y     the y-coordinate of the cell to update
     * @param state the new state of the cell
     */
    public void updateCellDisplay(int x, int y, CellState state) {
        CellView cell = (CellView) boardPanel.getComponent(x * board.getBoardWidth() + y);
        cell.setState(state);
    }

    /**
     * Prompts the user to set the dimensions of the game board.
     */
    public void promptBoardSize() {
        String length = JOptionPane.showInputDialog(this, "Enter Length of the GameBoard:");
        String width = JOptionPane.showInputDialog(this, "Enter Width of the GameBoard:");
        if (length != null && width != null) {
            controller.changeBoardSize(Integer.parseInt(length), Integer.parseInt(width));
        }
    }

    /**
     * Displays the current generation count on the UI.
     *
     * @param generation the current generation number
     */
    public void displayGeneration(int generation) {
        generationLabel.setText("Generation: " + generation);
    }

    /**
     * Registers the game controller with this view.
     *
     * @param controller the GameController to register
     */
    public void registerController(GameController controller) {
        this.controller = controller;
    }

    public static void main(String[] args) {
        new GameView();
    }
}

