/**
 * Created by Brandon, Natasha, Martha, and Charlie on 2/26/2016.
 *
 * This class is responsible for the creation of the level select page. It allows the user to select the
 * difficulty and takes the user to the game page with the difficulty's configuration.
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewLevelSelection {
    //Create instances of setup and controller pages
    ViewSetup setup = new ViewSetup();
    Controller control = new Controller();

    /**
     * Start method generates Level selection scene
     * @param primaryStage - instance of Stage used to generate Levels page
     */
    public void start(Stage primaryStage){
        //sets page number to 1 (identifier used for back button)
        control.setPage(1);
        Node buttonPane = makeLevelButtonPane(primaryStage);

        BorderPane root = new BorderPane();
        root.setTop(setup.makeTitle());
        root.setCenter(buttonPane);
        root.setBottom(setup.addGlobalNavigation(primaryStage));
        root.getStyleClass().add("pane");

        Scene scene = new Scene(root, 840, 840);
        scene.getStylesheets().add("ViewStyle.css");
        primaryStage.setTitle("Rush Hour!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Adds Buttons for Easy, Medium, and Hard Games and returns them in a pane
     * @param primaryStage - instance of Stage used to generate pane containing level selection buttons
     * @return - returns pane containing buttons for all of level selection buttons
     */
    private Node makeLevelButtonPane(Stage primaryStage) {
        GridPane buttonPane = new GridPane();
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setVgap(15);


        // Create the three buttons and add them to the grid
        Button easy = setup.addButton("Easy Game");
        easy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control.goToEasyGame(primaryStage);
            }
        });
        buttonPane.add(easy, 0, 0);

        Button medium = setup.addButton("Medium Game");
        medium.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control.goToMediumGame(primaryStage);
            }
        });
        buttonPane.add(medium, 0, 1);

        Button hard = setup.addButton("Hard Game");
        hard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control.goToHardGame(primaryStage);
            }
        });
        buttonPane.add(hard, 0, 2);

        return buttonPane;
    }
}