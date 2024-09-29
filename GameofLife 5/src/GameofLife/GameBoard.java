package GameofLife;

public class GameBoard {
    private Cell[][] board;
    private int length;
    private int width;

// Constructor to create a gameboard with a given length and width
public GameBoard(int length, int width) {
    this.length = length;
    this.width = width;
    initializeBoard();
}

    private void initializeBoard() {
        this.board = new Cell[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = new Cell(CellState.DEAD);
            }
        }
    }
    //method to count the number of live neighbors of a GameofLife.cell with given coordinate
    public int countLiveNeighbors(int x, int y) {
        int neighborNum = 0;
        for (int i = -1; i <= 1; i++) { // i indicate the distance from the given GameofLife.cell in horizontal direction
            for (int j = -1; j <= 1; j++) {  // j indicate the distance from the given GameofLife.cell in vertical direction
                if (i == 0 && j == 0) continue; // if i = 0 and j = 0, it's the current GameofLife.cell so skip it
                int nx = x + i;
                int ny = y + j;
                // make sure the counting process stay within the game board
                if (nx >= 0 && nx < length && ny >= 0 && ny < width && board[nx][ny].getState() == CellState.ALIVE) {
                    neighborNum++;
                }
            }
        }
        return neighborNum;
    }

    /**
     * This method apply the rule of conway's GameofLife
     * The game progresses from one generation to the next according to the following rules:
     * 1. A creature that has two or three neighbors will continue live in the next generation.
     * 2. A creature that has more than 3 neighbors will die . Its GameofLife.cell will be
     * empty in the next generation.
     * 3. A creature that has less than 2 neighbors will die.
     * 4. A new creature born in an empty GameofLife.cell that has exactly 3 neighbors.
     */
    public void computeNextGen() {
        Cell[][] nextBoard = new Cell[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                int liveNeighbors = countLiveNeighbors(i, j);
                CellState nextState = board[i][j].getState();

                // A creature that has more than 3 neighbors or less than 2 will die. Its GameofLife.cell will be
                //  empty in the next generation.
                if (board[i][j].getState() == CellState.ALIVE && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    nextState = CellState.DEAD;
                }
                //  A new creature born in an empty GameofLife.cell that has exactly 3 neighbors.
                else if (board[i][j].getState() == CellState.DEAD && liveNeighbors == 3) {
                    nextState = CellState.ALIVE;
                }

                nextBoard[i][j] = new Cell(nextState);
            }
        }

        this.board = nextBoard;
    }

    public void resizeBoard(int newLength, int newWidth) {
        this.length = newLength;
        this.width = newWidth;
        initializeBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < getBoardLength(); i++) {
            for (int j = 0; j < getBoardWidth(); j++) {
                getCell(i, j).setState(CellState.DEAD);
            }
        }
    }


    public Cell getCell(int x, int y) {
        return board[x][y];
    }

    // Getter method to get the state of a GameofLife.cell with a given coordinate value x and y
    public CellState getCellState(int x, int y) {
        return board[x][y].getState();
    }

    public void setCellState(int x, int y, CellState state) {
        board[x][y].setState(state);
    }


    public int getBoardLength() {
        return length;
    }

    public int getBoardWidth() {
        return width;
    }






}
