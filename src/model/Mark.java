package model;

import javafx.scene.image.Image;

import java.util.Objects;

/**
 * An enum responsible for different marks on the board.
 *
 * @author mikolajdeja
 * @version 2021.04.26
 */
public enum Mark {
    CROSS(new Image(Objects.requireNonNull(Mark.class.getResource("../images/cross.png")).toExternalForm())),
    NOUGHT(new Image(Objects.requireNonNull(Mark.class.getResource("../images/nought.png")).toExternalForm())),
    EMPTY(new Image(Objects.requireNonNull(Mark.class.getResource("../images/empty.png")).toExternalForm()));

    private final Image image;

    /**
     * A constructor for the enum items.
     *
     * @param image The image stored.
     */
    Mark(Image image){
        this.image = image;
    }

    /**
     * Get the image corresponding with an enum.
     *
     * @return The image corresponding with an enum.
     */
    public Image getImage() {
        return image;
    }
}
