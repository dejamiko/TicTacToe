package model;

/**
 * A class responsible for the AI player.
 *
 * @author mikolajdeja
 * @version 2021.04.27
 */
public class Player {
    private Mark[][] board;
    private Board b;

    public Player(Board board) {
        this.b = board;
    }

    /**
     * Set the current board state.
     * @param board
     */
    public void setBoard(Mark[][] board) {
        this.board = board;
    }

    // Cross is minimiser, nought maximiser
    public int minimax(Mark[][] position, int depth) {
        Board pos = new Board(position);
        if (pos.getState() == State.CROSS)
            return -100;
        if (pos.getState() == State.NOUGHT)
            return 100;
        if (pos.getState() == State.DRAW)
            return 0;
        int best;
        if (pos.getTurn() == State.NOUGHT) {
            best = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (position[i][j] == Mark.EMPTY) {
                        // make move
                        pos.makeMove(i, j);
                        best = Math.max(best, minimax(pos.getBoard(), depth + 1));
                        // undo move
                        pos.undoMove(i, j);
                    }
                }
            }
        } else {
            best = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (position[i][j] == Mark.EMPTY) {
                        // make move
                        pos.makeMove(i, j);
                        best = Math.min(best, minimax(pos.getBoard(), depth + 1));
                        // undo move
                        pos.undoMove(i, j);
                    }
                }
            }
        }
        return best;
    }


    public int[] findMove() {
        int max = -1000;
        int[] ans = new int[]{0, 0};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Mark.EMPTY) {
                    board[i][j] = Mark.NOUGHT;
                    int moveVal = minimax(board, 0);
                    board[i][j] = Mark.EMPTY;

                    if (moveVal > max) {
                        ans[0] = i;
                        ans[1] = j;
                        max = moveVal;
                    }
                }
            }
        }
        return ans;
    }
}
