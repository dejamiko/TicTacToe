package model;

/**
 * An enum responsible for different states in the game.
 *
 * @author mikolajdeja
 * @version 2021.04.26
 */
public enum State {
    CROSS_WON(Turn.CROSS),
    NOUGHT_WON(Turn.NOUGHT),
    DRAW(Turn.NEITHER),
    UNFINISHED(Turn.NEITHER);

    Turn turn;

    /**
     * A constructor for the state enum.
     *
     * @param turn The turn associated with a given state.
     */
    State(Turn turn){
        this.turn = turn;
    }

    /**
     * Get the turn associated with a given state.
     *
     * @return The turn associated with a given state.
     */
    public Turn getTurn() {
        return turn;
    }
}
