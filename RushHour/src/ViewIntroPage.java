/**
 * Created by Brandon, Natasha, Martha, and Charlie on 2/26/2016.
 *
 * Class to create the Introduction page when you first open the game. This is also the page that you run
 * to start the game
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewIntroPage extends Application {
    //Create instances of setup and controller pages
    ViewSetup setup = new ViewSetup();
    Controller control = new Controller();

    /**
     * Method to compile elements of start page and launch application
     * @param primaryStage - instance of Stage used to generate a generic window
     */
    @Override
    public void start(Stage primaryStage){
        //sets page number to 0 (identifier used for back button)
        control.setPage(0);
        BorderPane root = makeRoot(primaryStage);
        Scene scene = new Scene(root, 840, 840);
        scene.getStylesheets().add("ViewStyle.css");
        primaryStage.setTitle("Welcome to Rush Hour!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Method to create the elements of the introduction page. This was factored out for clarity
     * @param primaryStage - instance of stage used to generate Intro Page
     * @return the pane with the necessary elements added
     */
    public BorderPane makeRoot(Stage primaryStage){
        Text byLine = new Text("by Anderson, Durrett, Flowers, and Hagans");
        byLine.getStyleClass().add("text");
        Node ByPane = setup.addText(byLine, FontWeight.NORMAL, 20);

        Button startbtn = setup.addButton("Start");
        startbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control.goToLevelSelect(primaryStage);
            }
        });

        //Populate intro page with info and images
        BorderPane root = new BorderPane();
        BorderPane gifAndTitle = new BorderPane();
        gifAndTitle.getStyleClass().add("pane");
        root.getStyleClass().add("pane");
        gifAndTitle.setTop(setup.makeTitle());
        gifAndTitle.setLeft(setup.createTaxiGif());
        root.setTop(gifAndTitle);
        root.setCenter(startbtn);
        root.setBottom(ByPane);

        return root;
    }
}