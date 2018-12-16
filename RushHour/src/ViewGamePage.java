/**
 * Created by Brandon, Natasha, Martha, and Charlie on 2/26/2016.
 *
 * This is the view of the main game page, and it changes its vehicle configuration depending on the
 * difficulty selected.
 *
 * Vehicle images from ThinkFun's RushHour App
 */

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ViewGamePage extends Application {
    String level;
    Stage currentStage;
    ViewSetup setup = new ViewSetup();
    Controller control = new Controller();

    /**
     * Function to start the game page, with different available configurations
     * @param primaryStage - instance of Stage used to generate game page
     */
    @Override
    public void start(Stage primaryStage) {
        control.setPage(2);
        currentStage = primaryStage;
        BorderPane root = new BorderPane();

        //Set up the different components of the BorderPane
        Node centerPane = SetUpBoard(primaryStage);

        Node buttonPane = setup.addGlobalNavigation(primaryStage);
        Node titlePane = setup.makeTitle();

        // Set nodes to appropriate locations in the border pane
        root.setTop(titlePane);
        root.setCenter(centerPane);
        root.setBottom(buttonPane);

        //Set the scene and its attributes
        primaryStage.setScene(new Scene(root, 840, 840));
        primaryStage.setResizable(false);

        root.getStylesheets().add("ViewStyle.css");
        root.getStyleClass().add("pane");
        primaryStage.setTitle("Rush Hour");

        primaryStage.show();
    }

    //Function to set the game level
    public void SetLevel(String level){
        this.level = level;
    }

    //Gets the level of the gamePage, necessary for the level configuration
    private String getLevel(){ return this.level; }

    /**
     * Sets up border pane the contains visual border and exit sign
     * This pane contains the game pane
     * @param primaryStage - instance of stage used to generate the game board
     * @return - returns background pane that contains the game pane
     */
    private Node SetUpBoard(Stage primaryStage) {

        //Set up the pane & style it
        BorderPane background = new BorderPane();
        background.setMaxSize(640, 640);
        background.setPrefSize(640, 640);
        background.setMinSize(640, 640);

        Image backingImage = new Image(getClass().getResourceAsStream("greyBackgroundWithExit.png"));
        BackgroundImage backingGrid = new BackgroundImage(backingImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        background.setBackground(new Background(backingGrid));

        Node gamePane = SetUpGame(primaryStage);

        background.setCenter(gamePane);

        return background;
    }

    /**
     * Sets up the actual game pane, including the grid
     * Determines which level to configure and calls methods that populate the grid with the appropriate vehicle configuration
     * @param primaryStage - instance of stage used to generate the complete game page
     * @return - returns appropriately configured gridpane, depending on the level selected by the user
     */
    private Node SetUpGame(Stage primaryStage) {

        //Set up the grid and style it
        GridPane gridpane = new GridPane();
        gridpane.setMaxSize(600, 600);
        gridpane.setPrefSize(600, 600);
        gridpane.setMinSize(600, 600);
        gridpane.setStyle("-fx-background-color: lightgrey");
        gridpane.setGridLinesVisible(true);

        final int numCols = 6;
        final int numRows = 6;
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints(100);
            gridpane.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints(100);
            gridpane.getRowConstraints().add(rowConst);
        }

        //Gets the level chosen by the user and calls the appropriate controller method to configure this level
        if (getLevel().equals("easy")){
        gridpane = control.makeEasyConfig(primaryStage, gridpane);}

        if (getLevel().equals("medium")){
            gridpane = control.makeMediumConfig(primaryStage, gridpane);}

        if (getLevel().equals("hard")){
            gridpane = control.makeHardConfig(primaryStage, gridpane);}

        return gridpane;
    }

}