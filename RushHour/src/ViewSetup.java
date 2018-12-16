/**
 * Created by Brandon, Natasha, Martha, and Charlie on 2/26/2016.
 *
 * This is class containing view methods that occur on multiple pages, allowing us to refactor them out,
 * and to be called directly from this class.
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewSetup {
    //This is for setting the size of the button
    public static final double MIN_BUTTON_WIDTH = 200;
    //Make an instance of controller
    Controller control = new Controller();

    /**
     * Creates and returns a button
     * @param text - text of the button
     * @return - returns a button
     */
    public Button addButton(String text) {
        Button button = new Button(text);
        button.setMinWidth(MIN_BUTTON_WIDTH);
        button.getStyleClass().add("button");
        return button;
    }

    /**
     * Method to return a pane that contains text, used to create the title and help page info
     * @param text - text to go on the pane
     * @param fontWeight
     * @param fontSize
     * @return - returns pane containing text
     */
    public Node addText(Text text, FontWeight fontWeight, int fontSize) {
        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setPrefHeight(100);
        flowPane.setMinHeight(100);
        flowPane.setMaxHeight(100);
        text.setFont(Font.font("Times New Roman", fontWeight, fontSize));
        flowPane.getChildren().add(text);
        return flowPane;
    }

    /**
     * Method that returns a button pane that contains our quit, back, and help buttons
     * Used on most pages in the project
     * Calls button creator methods (addHelp, addQuit, addBack)
     * @param primaryStage - instance of Stage used to generate a button pane containing the global navigation
     * @return - returns button pane containing the global navigation (back, quit, and help)
     */
    public Node addGlobalNavigation(Stage primaryStage){
        //create buttons
        Button quit = addQuit(primaryStage);
        Button help = addHelp(primaryStage);
        Button back = addBack(primaryStage);

        //populate button pane
        FlowPane buttonPane = new FlowPane();
        buttonPane.setPrefHeight(100);
        buttonPane.setMinHeight(100);
        buttonPane.setMaxHeight(100);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setHgap(20);
        buttonPane.getChildren().add(back);
        buttonPane.getChildren().add(quit);
        buttonPane.getChildren().add(help);

        return buttonPane;
    }

    /**
     * Creates a help button that sends you to the help page
     * @param primaryStage - instance of Stage used to generate the help page
     * @return - help button
     */
    public Button addHelp(Stage primaryStage){
        ViewHelpPage helpPage = new ViewHelpPage();
        Button help = addButton("Help");
        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control.goToHelpPage(primaryStage);
            }
        });
        return help;
    }

    /**
     * Creates a quit button that quits the program
     * @param primaryStage - instance of Stage used to quit the program
     * @return - quit button
     */
    public Button addQuit(Stage primaryStage){
        Button quit = addButton("Quit");
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        return quit;
    }

    /**
     * Creates a back button that quits the program
     * @param primaryStage - instance of Stage used to generate the previous page
     * @return - back button
     */
    public Button addBack(Stage primaryStage){
        Button back = addButton("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               control.goBack(primaryStage);
            }
        });
        return back;
    }

    /**
     * Creates taxi gif
     * Code modified from this website:
     * https://docs.oracle.com/javafx/2/api/javafx/scene/image/ImageView.html
     * @return - returns taxi gif
     */
    public ImageView createTaxiGif(){
        Image image = new Image("taxi.gif");
        ImageView taxiGif = new ImageView();
        taxiGif.setImage(image);
        taxiGif.setFitWidth(840);
        taxiGif.setPreserveRatio(false);
        return taxiGif;
    }

    /**
     * Method to make the Rush Hour title for each page
     * @return the pane containing the title
     */
    public Node makeTitle(){
        Text title = new Text("Rush Hour");
        title.getStyleClass().add("title");
        Node titlePane = addText(title, FontWeight.BOLD, 5);
        titlePane.getStyleClass().add("pane");
        return titlePane;
    }
}