package model;

/**
 * An enum responsible for different states in the game.
 *
 * @author mikolajdeja
 * @version 2021.04.26
 */
public enum State {
    CROSS(Mark.CROSS),
    NOUGHT(Mark.NOUGHT),
    DRAW(Mark.EMPTY),
    UNFINISHED(Mark.EMPTY);

    private final Mark mark;

    /**
     * The constructor for the enum objects.
     *
     * @param mark The mark associated with a given state.
     */
    State(Mark mark) {
        this.mark = mark;
    }

    /**
     * Get the mark associated with a given state.
     *
     * @return The mark associated with a given state.
     */
    public Mark getMark() {
        return mark;
    }
}
