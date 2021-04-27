package model;

/**
 * An enum responsible for turns in the game.
 *
 * @author mikolajdeja
 * @version 2021.04.27
 */
public enum Turn {
    CROSS(Mark.CROSS),
    NOUGHT(Mark.NOUGHT),
    NEITHER(Mark.EMPTY);


    private final Mark mark;

    /**
     * The constructor for the enum objects.
     *
     * @param mark The mark associated with a given turn.
     */
    Turn(Mark mark) {
        this.mark = mark;
    }

    /**
     * Get the mark associated with a given turn.
     *
     * @return The mark associated with a given turn.
     */
    public Mark getMark() {
        return mark;
    }
}
