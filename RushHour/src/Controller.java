
/**
 * Created by Martha, Charlie, Natasha, and Brandon on 3/7/16.
 *
 * Class: Controller
 *
 * Handles interaction between Model and View classes
 */

import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Controller {
    //Controller class for the intro page to pass logic on to model and to view

    Model model = new Model();
    GridPane newPane = new GridPane();

    /**
     * Controller method activating the model's goToLevelSelect method
     * @param primaryStage - instance of Stage to generate page
     */
    public void goToLevelSelect(Stage primaryStage){
            model.goTolevelSelect(primaryStage);
    }

    //Calls model to go to Easy Level page
    public void goToEasyGame(Stage primaryStage){
        model.goToEasyGame(primaryStage);
    }

    //Calls model to go to Medium Level page
    public void goToMediumGame(Stage primaryStage){
        model.goToMediumGame(primaryStage);
    }

    //Calls model to go to Hard Level page
    public void goToHardGame(Stage primaryStage){
        model.goToHardGame(primaryStage);
    }

    //configures easy page
    public GridPane makeEasyConfig(Stage primaryStage, GridPane gridpane){
        newPane = model.makeEasyConfig(primaryStage, gridpane);
        return newPane;
    }

    //configures medium page
    public GridPane makeMediumConfig(Stage primaryStage, GridPane gridpane){
        newPane = model.makeMediumConfig(primaryStage, gridpane);
        return newPane;
    }

    //configures hard page
    public GridPane makeHardConfig(Stage primaryStage, GridPane gridpane){
        newPane = model.makeHardConfig(primaryStage, gridpane);
        return newPane;
    }

    /**
     * Controller method activating the model's goBack method
     * @param primaryStage - instance of Stage to generate page
     */
    public void goBack(Stage primaryStage){
        model.goBack(primaryStage);
    }

    /**
     * Controller method activating the model's setPage method
     * @param pageNumber - integer reference to the current page
     */
    public void setPage(int pageNumber){
        model.setPage(pageNumber);
    }

    /**
     * Controller method telling model to open the help page
     * @param primaryStage - instance of Stage that generates the help page
     */
    public void goToHelpPage(Stage primaryStage){
        model.goToHelpPage(primaryStage);

    }

}


