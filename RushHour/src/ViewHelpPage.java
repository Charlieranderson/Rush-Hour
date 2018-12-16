/**
 * Created by Brandon, Natasha, Martha, and Charlie on 2/26/2016.
 *
 * This class is responsible for creating the help page with instructions and a helpful gif. This is called
 * when the help button is pressed.
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewHelpPage {
    //Create instances of the setup page
    ViewSetup setup = new ViewSetup();

    /**
     * Start method generates scene for help page
     * @param primaryStage - instance of Stage used to generate Help Page
     */
    public void start(Stage primaryStage){
        Stage newStage = new Stage();
        //Help page info
        Text helpInfo = new Text("To Play: Move the blocking cars and trucks in their lanes — up and down " +
                "or left and right — until the path is clear for the red car to escape. To move a vehicle, " +
                "click and drag the vehicle. Vehicles can only slide forward & backward, not sideways." +
                "In order to win the game, you must drag and release the red car at the exit.");
        helpInfo.setWrappingWidth(500);
        BorderPane infoPane = new BorderPane();
        infoPane.getStyleClass().add("pane");
        infoPane.getStyleClass().add("text");
        infoPane.setCenter(helpInfo);

        //Adds instructional gif to help page
        Image image = new Image("instructions.gif");
        ImageView instructionsGif = new ImageView();
        instructionsGif.setImage(image);
        instructionsGif.setFitWidth(400);
        instructionsGif.setPreserveRatio(true);
        infoPane.setAlignment(instructionsGif, Pos.CENTER);
        infoPane.setTop(instructionsGif);

        //Add quit button
        Button quit = setup.addQuit(newStage);

        //Sets up necessary buttons for help page (couldn't use global navigation because we don't want a help button
        //while we are on the help page)
        GridPane buttonPane = new GridPane();
        buttonPane.getStyleClass().add("pane");
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setPrefHeight(100);
        buttonPane.setMinHeight(100);
        buttonPane.setMaxHeight(100);
        buttonPane.setHgap(20);
        buttonPane.add(quit, 0, 0);

        //populates Help Page with info
        BorderPane root = new BorderPane();
        root.setTop(setup.makeTitle());
        root.setCenter(infoPane);
        root.setBottom(buttonPane);
        root.getStyleClass().add("pane");
        Scene scene = new Scene(root, 700, 720);
        scene.getStylesheets().add("ViewStyle.css");

        newStage.setTitle("Rush Hour!");
        newStage.setScene(scene);
        newStage.show();
    }
}