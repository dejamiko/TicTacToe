package model;

/**
 * A class responsible for the AI player.
 *
 * @author mikolajdeja
 * @version 2021.04.27
 */
public class Player {
    private final Board board;
    private Turn side;

    /**
     * Constructor for the player with a given board.
     *
     * @param board The board the player is playing on.
     */
    public Player(Board board) {
        this.board = board;
    }

    /**
     * The minimax algorithm for evaluating positions, using
     * alpha-beta pruning.
     * CROSS is minimiser, NOUGHT maximiser
     *
     * @return The evaluation of the position.
     */
    public int minimax(int alpha, int beta) {
        if (board.getState().getTurn() == side)
            return 100;
        if (board.getState() == State.DRAW)
            return 0;
        if (board.getState() != State.UNFINISHED)
            return -100;

        int best = board.getTurn() == side ? -1000 : 1000;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getMark(i, j) == Mark.EMPTY) {
                    board.makeMove(i, j);
                    if (board.getTurn() != side) {
                        best = Math.max(best, minimax(alpha, beta));
                        alpha = Math.max(alpha, best);
                    } else {
                        best = Math.min(best, minimax(alpha, beta));
                        beta = Math.min(best, beta);
                    }
                    board.undoMove(i, j);
                    if (alpha >= beta)
                        return best;
                }
            }
        }
        return best;
    }

    /**
     * Find the best move in a given position.
     *
     * @return The best move in a given position.
     */
    public int[] findMove() {
        int max = -1000;
        int[] ans = new int[]{0, 0};
        side = board.getTurn();

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board.getMark(i, j) == Mark.EMPTY) {
                    board.makeMove(i, j);
                    int moveVal = minimax(-1000, 1000);
                    board.undoMove(i, j);

                    if (moveVal > max) {
                        ans[0] = i;
                        ans[1] = j;
                        max = moveVal;
                    }
                }
        return ans;
    }
}
