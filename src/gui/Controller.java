package gui;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Board;
import model.Player;
import model.State;
import model.Turn;

/**
 * A controller class for the tic-tac-toe gui.
 *
 * @author mikolajdeja
 * @version 2021.04.26
 */
public class Controller {
    @FXML
    private Label stateLabel;
    @FXML
    private ImageView image00;
    @FXML
    private ImageView image01;
    @FXML
    private ImageView image02;
    @FXML
    private ImageView image10;
    @FXML
    private ImageView image11;
    @FXML
    private ImageView image12;
    @FXML
    private ImageView image20;
    @FXML
    private ImageView image21;
    @FXML
    private ImageView image22;
    @FXML
    private GridPane gridPane;

    private ImageView[][] images;
    private Board board;
    private Player player;
    private Turn starting;
    private boolean computerBeginning;

    /**
     * The constructor for the controller.
     */
    public Controller() {
        board = new Board();
        starting = board.getTurn();
        player = new Player(board);
        computerBeginning = false;
    }

    /**
     * Initialise the elements of the GUI.
     */
    @FXML
    private void initialize() {
        images = new ImageView[][]{
                {image00, image01, image02},
                {image10, image11, image12},
                {image20, image21, image22}
        };

        for (ImageView[] image : images)
            for (ImageView imageView : image) imageView.setOnMouseClicked(this::mouseClicked);

        Platform.runLater(() -> {
            Stage stage = ((Stage) image00.getScene().getWindow());
            ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
                drawBoard();
            };
            stage.widthProperty().addListener(stageSizeListener);
            stage.heightProperty().addListener(stageSizeListener);
        });

        drawBoard();
        updateState();
    }

    /**
     * Make the move when a square is clicked.
     *
     * @param mouseEvent The mouse event that occurred.
     */
    private void mouseClicked(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        int row = -1, col = -1;
        for (int i = 0; i < images.length && row < 0; i++)
            for (int j = 0; j < images[i].length; j++)
                if (images[i][j].equals(imageView)) {
                    row = i;
                    col = j;
                    break;
                }

        if (board.makeMove(row, col)) {
            drawBoard();
            updateState();
            // Only make a move if the game hasn't finished yet
            if (board.getState() == State.UNFINISHED) {
                int[] move = player.findMove();
                board.makeMove(move[0], move[1]);
                drawBoard();
                updateState();
            }
        }
    }

    /**
     * Update the state label.
     */
    private void updateState() {
        String text = "";
        if (board.getState() == State.UNFINISHED)
            text = board.getTurn().toString();
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (board.getState() == State.DRAW) {
                alert.setHeaderText("The game ended in a draw.");
            } else {
                alert.setHeaderText("Congratulations!");
                alert.setContentText(board.getState().toString() + " won the game!");
            }
            alert.showAndWait();
            disableButtons();
        }
        stateLabel.setText(text);
    }

    /**
     * Draw the board.
     */
    private void drawBoard() {
        for (int i = 0; i < images.length; i++) {
            for (int j = 0; j < images[i].length; j++) {
                images[i][j].setImage(board.getMark(i, j).getImage());
                images[i][j].setFitHeight(gridPane.getHeight() / gridPane.getRowCount());
                images[i][j].setFitWidth(gridPane.getWidth() / gridPane.getColumnCount());
            }
        }
    }

    /**
     * Disable clicking on squares (change the setOnMouseClicked)
     */
    private void disableButtons() {
        for (ImageView[] image : images) {
            for (ImageView imageView : image) {
                imageView.setOnMouseClicked(e -> System.out.println("game is finished lol"));
            }
        }
    }

    /**
     * Restart the game.
     */
    @FXML
    private void restartGame() {
        board = new Board(starting);
        player = new Player(board);
        initialize();
        if (computerBeginning) {
            int[] move = player.findMove();
            board.makeMove(move[0], move[1]);
            drawBoard();
            updateState();
        }
    }

    /**
     * Switch the sides (cross and nought).
     */
    @FXML
    private void switchSides() {
        if (starting == Turn.CROSS)
            starting = Turn.NOUGHT;
        else
            starting = Turn.CROSS;
        restartGame();
    }

    /**
     * Switch who's starting the game.
     */
    @FXML
    private void switchStarting() {
        computerBeginning = !computerBeginning;
        restartGame();
    }
}
