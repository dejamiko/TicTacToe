package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * A main class of the application.
 *
 * @author mikolajdeja
 * @version 2021.04.25
 */
public class Main extends Application {

    /**
     * Start the application.
     *
     * @param primaryStage The primary stage of the application.
     * @throws Exception Exceptions related to loading from FXML.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TicTacToe.fxml")));
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.show();
    }

    /**
     * The main method.
     *
     * @param args The arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
