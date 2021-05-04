package model;

/**
 * A class responsible for holding information about the board.
 *
 * @author mikolajdeja
 * @version 2021.04.25
 */
public class Board {
    private final Mark[][] board;
    private Turn turn;
    private int size;

    /**
     * Initialise the board.
     */
    public Board(int size) {
        this.size = size;
        board = new Mark[size][size];
        clearBoard();
        turn = Turn.CROSS;
    }

    /**
     * Initialise the board with a given starting side.
     *
     * @param starting The side that starts the game.
     */
    public Board(Turn starting) {
        size = 3;
        board = new Mark[size][size];
        clearBoard();
        turn = starting;
    }

    /**
     * Clear the board.
     */
    private void clearBoard() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                board[i][j] = Mark.EMPTY;
    }

    /**
     * Make a move.
     *
     * @param row The row where the new mark is placed.
     * @param col The column where the new mark is placed.
     * @return True if the move was made.
     */
    public boolean makeMove(int row, int col) {
        if (board[row][col] == Mark.EMPTY) {
            board[row][col] = turn.getMark();
            switchTurn();
            return true;
        }
        return false;
    }

    /**
     * Switch the turn.
     */
    private void switchTurn() {
        if (turn == Turn.CROSS)
            turn = Turn.NOUGHT;
        else
            turn = Turn.CROSS;
    }

    /**
     * Get the state of the board.
     *
     * @return the state of the board.
     */
    public State getState() {
        if (won(Mark.CROSS))
            return State.CROSS_WON;
        if (won(Mark.NOUGHT))
            return State.NOUGHT_WON;
        if (isDraw())
            return State.DRAW;
        return State.UNFINISHED;
    }

    /**
     * Check if the player with a given mark won.
     *
     * @param mark The mark of the player.
     * @return True if the player won, false otherwise.
     */
    private boolean won(Mark mark) {
        // check rows
        for (Mark[] row : board) {
            boolean works = true;
            for (Mark value : row) {
                if (value != mark) {
                    works = false;
                    break;
                }
            }
            if (works)
                return true;
        }
        // check columns
        for (int i = 0; i < board[0].length; i++) {
            boolean works = true;
            for (Mark[] column : board) {
                if (column[i] != mark) {
                    works = false;
                    break;
                }
            }
            if (works)
                return true;
        }
        // check diagonals (assume a 3x3 board)
        boolean works = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i][i] != mark) {
                works = false;
                break;
            }
        }
        if (works)
            return true;
        works = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i][board.length - 1 - i] != mark) {
                works = false;
                break;
            }
        }
        return works;
    }

    /**
     * Check if the game is a draw.
     *
     * @return True if the game is a draw, false otherwise.
     */
    private boolean isDraw() {
        for (Mark[] row : board) {
            for (Mark mark : row) {
                if (mark == Mark.EMPTY)
                    return false;
            }
        }
        return true;
    }

    /**
     * Get the string representation of the board.
     *
     * @return The string representation of the board.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Mark[] row : board) {
            for (Mark mark : row)
                stringBuilder.append(mark).append(" ");
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Get the mark in the given row and column.
     *
     * @param row The row of interest.
     * @param col The column of interest.
     * @return The mark in the given row and column.
     */
    public Mark getMark(int row, int col) {
        return board[row][col];
    }

    /**
     * Get the current player.
     *
     * @return The current player.
     */
    public Turn getTurn() {
        return turn;
    }

    /**
     * Undo a move in a given row and column.
     *
     * @param row The row.
     * @param col The column.
     */
    public void undoMove(int row, int col) {
        board[row][col] = Mark.EMPTY;
        switchTurn();
    }
}
