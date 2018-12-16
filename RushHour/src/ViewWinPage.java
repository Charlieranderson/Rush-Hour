/**
 * Created by Brandon, Natasha, Martha, and Charlie on 2/26/2016.
 *
 * This class is responsible for creating the win page at the end of the game upon victory. The main stage is closed
 * and this new window replaces it, allowing the user to either quit the game or return to the level selection.
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class ViewWinPage {
    //Create instances of intro and setup pages
    ViewLevelSelection levelPage = new ViewLevelSelection();
    ViewSetup setup = new ViewSetup();

    /**
     * Start method generates scene for win page
     * @param primaryStage - instance of Stage used to generate the win page
     */
    public void start(Stage primaryStage){
        Stage newStage = new Stage();

        //adds gif of smiling car :)
        Image image = new Image("happycar.gif");
        ImageView carGif = new ImageView();
        carGif.setImage(image);
        carGif.setFitWidth(400);
        carGif.setPreserveRatio(true);

        //Win page message
        Text winMessage = new Text("You win! Congratulations!");
        BorderPane textpane = new BorderPane();
        textpane.getStyleClass().add("pane");
        textpane.getStyleClass().add("text");
        textpane.setCenter(winMessage);
        textpane.setAlignment(carGif, Pos.CENTER);
        textpane.setTop(carGif);

        Button quit = setup.addQuit(newStage);
        Button playAgain = setup.addButton("Play again?");
        playAgain.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newStage.close();
                levelPage.start(primaryStage);
            }
        });

        //Sets up necessary buttons for win page (couldn't use global navigation because we don't want a help button)
        GridPane buttonPane = new GridPane();
        buttonPane.getStyleClass().add("pane");
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setPrefHeight(100);
        buttonPane.setMinHeight(100);
        buttonPane.setMaxHeight(100);
        buttonPane.setHgap(20);
        buttonPane.add(quit, 0, 0);
        buttonPane.add(playAgain, 1, 0);

        //Populates win page with info
        BorderPane root = new BorderPane();
        root.setTop(setup.makeTitle());
        root.setCenter(textpane);
        root.setBottom(buttonPane);
        root.getStyleClass().add("pane");
        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add("ViewStyle.css");

        //generates car beep when you win
        String beep = "beep.mp3";
        Media sound = new Media(new File(beep).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();

        newStage.setTitle("Rush Hour!");
        newStage.setScene(scene);
        newStage.show();
    }
}
