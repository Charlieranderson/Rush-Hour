/**
 * Created by Martha, Charlie, Natasha, and Brandon on 3/6/16.
 * This class essentially handles the logic of the entire program, and it called exclusively from the Controller
 * which is handling the user input.
 */

import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Model {
    ViewIntroPage introPage;
    ViewGamePage gamePage;
    ViewLevelSelection levelPage;
    ViewHelpPage helpPage;
    ViewWinPage winPage;
    static int currentPage = 0;

    /**
     * Method to take user to the introduction page
     * @param primaryStage - instance of Stage used to generate introduction page
     */
    public void goToIntroPage(Stage primaryStage){
        introPage = new ViewIntroPage();
        introPage.start(primaryStage);
    }

    /**
     * Method to take the user to the level selection page
     * @param primaryStage - instance of Stage used to generate level selection page
     */
    public void goTolevelSelect(Stage primaryStage){
        levelPage = new ViewLevelSelection();
        levelPage.start(primaryStage);
    }

    /**
     * Method to take user to the game page with an easy configuration
     * @param primaryStage - instance of Stage used to generate easy level page
     */
    public void goToEasyGame(Stage primaryStage){
        gamePage = new ViewGamePage();
        gamePage.SetLevel("easy");
        gamePage.start(primaryStage);
    }

    /**
     * Method to take user to the game page with a medium configuration
     * @param primaryStage - instance of Stage used to generate medium level page
     */
    public void goToMediumGame(Stage primaryStage){
        gamePage = new ViewGamePage();
        gamePage.SetLevel("medium");
        gamePage.start(primaryStage);
    }

    /**
     * Method to take user to the game page with a hard configuration
     * @param primaryStage - instance of Stage used to generate hard level page
     */
    public void goToHardGame(Stage primaryStage){
        gamePage = new ViewGamePage();
        gamePage.SetLevel("hard");
        gamePage.start(primaryStage);
    }

    /**
     * Method that activates when the help button is pressed, creates a new window with useful instructions
     * @param primaryStage - instance of page used to generate help page
     */
    public void goToHelpPage(Stage primaryStage){
        helpPage = new ViewHelpPage();
        helpPage.start(primaryStage);
    }

    /**
     * Method that activates when the user wins the game, creates a new window with win message and buttons to quit or play again.
     * @param primaryStage - instance of Stage used to generate win page
     */
    public void goToWinPage(Stage primaryStage){
        winPage = new ViewWinPage();
        winPage.start(primaryStage);
    }

    /**
     * Method that activates when back button is pressed, returns user to the previous page
     * @param primaryStage - instance of Stage used to generate previous page
     */
    public void goBack(Stage primaryStage){
        introPage = new ViewIntroPage();
        levelPage = new ViewLevelSelection();
        if (currentPage == 1){
            goToIntroPage(primaryStage);
        } else {
            goTolevelSelect(primaryStage);
        }
    }

    /**
     * Method to set the current page of the program
     * Necessary for keeping track for the back button, also useful for other future applications
     *
     * @param pageNum - integer reference to current page
     */
    public void setPage(int pageNum){
        currentPage = pageNum;
    }

    /**
     * Sets up an easy level configuration
     * @param primaryStage - instance of Stage used to create easy game page
     * @param gridPane - reference to GridPane object used to generate easy game
     * @return - returns gridpane populated with vehicles for easy game
     */
    public GridPane makeEasyConfig(Stage primaryStage, GridPane gridPane){
        //Create the vehicles
        ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();

        Image hRedCar = new Image(getClass().getResourceAsStream("HRedCar.png"));
        vehicleList.add(new Vehicle(hRedCar, 200, 98, "h", 3, 2, true));

        Image hLightGreenCar = new Image(getClass().getResourceAsStream("HLightGreenCar.png"));
        vehicleList.add(new Vehicle(hLightGreenCar, 200, 98, "h", 3, 1, false));

        Image hBlueTruck = new Image(getClass().getResourceAsStream("HBlueTruck.png"));
        vehicleList.add(new Vehicle(hBlueTruck, 300, 98, "h", 0, 3, false));

        Image hDarkGoldenrodTruck = new Image(getClass().getResourceAsStream("HDarkGoldenrodTruck.png"));
        vehicleList.add(new Vehicle(hDarkGoldenrodTruck, 300, 98, "h", 2, 0, false));

        Image vMagentaTruck = new Image(getClass().getResourceAsStream("VMagentaTruck.png"));
        vehicleList.add(new Vehicle(vMagentaTruck, 98, 300, "v", 5, 0, false));

        Image vOrangeCar = new Image(getClass().getResourceAsStream("VOrangeCar.png"));
        vehicleList.add(new Vehicle(vOrangeCar, 98, 200, "v", 3, 3, false));

        Image vLightBlueCar = new Image(getClass().getResourceAsStream("VLightBlueCar.png"));
        vehicleList.add(new Vehicle(vLightBlueCar, 98, 200, "v", 0, 4, false));

        Image hGreenTruck = new Image(getClass().getResourceAsStream("HGreenTruck.png"));
        vehicleList.add(new Vehicle(hGreenTruck, 300, 98, "h", 1, 5, false));

        Image hPinkCar = new Image(getClass().getResourceAsStream("HPinkCar.png"));
        vehicleList.add(new Vehicle(hPinkCar, 200, 98, "h", 4, 5, false));

        gridPane = setVehicleProperties(vehicleList, gridPane, primaryStage);

        return gridPane;
    }

    /**
     * Sets up a medium level configuration
     * @param primaryStage - instance of Stage used to generate medium game
     * @param gridPane - instance of GridPane used to generate medium page
     * @return - returns gridpane populated with vehicles for medium game
     */
    public GridPane makeMediumConfig(Stage primaryStage, GridPane gridPane){
        //Create the vehicles
        ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();

        Image hRedCar = new Image(getClass().getResourceAsStream("HRedCar.png"));
        vehicleList.add(new Vehicle(hRedCar, 200, 98, "h", 0, 2, true));

        Image vLightGreenCar = new Image(getClass().getResourceAsStream("VLightGreenCar.png"));
        vehicleList.add(new Vehicle(vLightGreenCar, 98, 200, "v", 0, 0, false));

        Image hOrangeCar = new Image(getClass().getResourceAsStream("HOrangeCar.png"));
        vehicleList.add(new Vehicle(hOrangeCar, 200, 98, "h", 1, 0, false));

        Image vLightBlueCar = new Image(getClass().getResourceAsStream("VLightBlueCar.png"));
        vehicleList.add(new Vehicle(vLightBlueCar, 98, 200, "v", 3, 0, false));

        Image vPinkCar = new Image(getClass().getResourceAsStream("VPinkCar.png"));
        vehicleList.add(new Vehicle(vPinkCar, 98, 200, "v", 4, 0, false));

        Image vPurpleCar = new Image(getClass().getResourceAsStream("VPurpleCar.png"));
        vehicleList.add(new Vehicle(vPurpleCar, 98, 200, "v", 2, 1, false));

        Image hDarkGoldenrodTruck = new Image(getClass().getResourceAsStream("HDarkGoldenrodTruck.png"));
        vehicleList.add(new Vehicle(hDarkGoldenrodTruck, 300, 98, "h", 1, 3, false));

        Image hTurquoiseCar = new Image(getClass().getResourceAsStream("HTurquoiseCar.png"));
        vehicleList.add(new Vehicle(hTurquoiseCar, 200, 98, "h", 4, 3, false));

        Image hMagentaTruck = new Image(getClass().getResourceAsStream("HMagentaTruck.png"));
        vehicleList.add(new Vehicle(hMagentaTruck, 300, 98, "h", 0, 4, false));

        Image hSeaGreenCar = new Image(getClass().getResourceAsStream("HSeaGreenCar.png"));
        vehicleList.add(new Vehicle(hSeaGreenCar, 200, 98, "h", 3, 4, false));

        Image vGoldCar = new Image(getClass().getResourceAsStream("VGoldCar.png"));
        vehicleList.add(new Vehicle(vGoldCar, 98, 200, "v", 5, 4, false));

        gridPane = setVehicleProperties(vehicleList, gridPane, primaryStage);

        return gridPane;
    }

    /**
     * Sets up a hard level configuration
     * @param primaryStage - instance of Stage used to generate hard game
     * @param gridPane - instance of GridPane used to generate hard page
     * @return - returns gridpane populated with vehicles for medium game
     */
    public GridPane makeHardConfig(Stage primaryStage, GridPane gridPane){

        ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();

        Image hRedCar = new Image(getClass().getResourceAsStream("HRedCar.png"));
        vehicleList.add(new Vehicle(hRedCar, 200, 98, "h", 2, 2, true));

        Image hOrangeCar = new Image(getClass().getResourceAsStream("HOrangeCar.png"));
        vehicleList.add(new Vehicle(hOrangeCar, 200, 98, "h", 4, 0, false));

        Image vLightGreenCar = new Image(getClass().getResourceAsStream("VLightGreenCar.png"));
        vehicleList.add(new Vehicle(vLightGreenCar, 98, 200, "v", 3, 0, false));

        Image vLightBlueCar = new Image(getClass().getResourceAsStream("VLightBlueCar.png"));
        vehicleList.add(new Vehicle(vLightBlueCar, 98, 200, "v", 0, 1, false));

        Image hPinkCar = new Image(getClass().getResourceAsStream("HPinkCar.png"));
        vehicleList.add(new Vehicle(hPinkCar, 200, 98, "h", 0, 3, false));

        Image hPurpleCar = new Image(getClass().getResourceAsStream("HPurpleCar.png"));
        vehicleList.add(new Vehicle(hPurpleCar, 200, 98, "h", 2, 3, false));

        Image vTurquoiseCar = new Image(getClass().getResourceAsStream("VTurquoiseCar.png"));
        vehicleList.add(new Vehicle(vTurquoiseCar, 98, 200, "v", 0, 4, false));

        Image vSeaGreenCar = new Image(getClass().getResourceAsStream("VSeaGreenCar.png"));
        vehicleList.add(new Vehicle(vSeaGreenCar, 98, 200, "v", 2, 4, false));

        Image vGoldCar = new Image(getClass().getResourceAsStream("VGoldCar.png"));
        vehicleList.add(new Vehicle(vGoldCar, 98, 200, "v", 3, 4, false));

        Image hYellowCar = new Image(getClass().getResourceAsStream("HYellowCar.png"));
        vehicleList.add(new Vehicle(hYellowCar, 200, 98, "h", 4, 4, false));

        Image hBrownCar = new Image(getClass().getResourceAsStream("HBrownCar.png"));
        vehicleList.add(new Vehicle(hBrownCar, 200, 98, "h", 4, 5, false));

        Image vMagentaTruck = new Image(getClass().getResourceAsStream("VMagentaTruck.png"));
        vehicleList.add(new Vehicle(vMagentaTruck, 98, 300, "v", 5, 1, false));

        Image vDarkGoldenrodTruck = new Image(getClass().getResourceAsStream("VDarkGoldenrodTruck.png"));
        vehicleList.add(new Vehicle(vDarkGoldenrodTruck, 98, 300, "v", 4, 1, false));

        gridPane = setVehicleProperties(vehicleList, gridPane, primaryStage);

        return gridPane;
    }

    /**
     * Loops through list of all vehicles
     * Calls method in Vehicle class that turns each instance of Vehicle into a usable Rectangle object for the GridPane
     * @param vehicleList - ArrayList of all the vehicles on the grid
     * @param gridPane - instance of gridpane used to generate game page
     * @param primaryStage - instance of stage used to generate game page
     * @return - returns gridPane populated with Rectangle objects
     */
    public GridPane setVehicleProperties(ArrayList<Vehicle> vehicleList, GridPane gridPane, Stage primaryStage){
        BoundingBox gridBound = new BoundingBox(0, 0, 600, 600);

        ArrayList<Rectangle> rectangleList = new ArrayList<Rectangle>();

        for (int i = 0; i < vehicleList.size(); i++){
            Vehicle vehicle = vehicleList.get(i);
            Rectangle rectangle = vehicle.setVehicleAttributes();
            rectangleList.add(rectangle);
            gridPane.add(rectangle,vehicle.getxGridPosition(),vehicle.getyGridPosition());
        }

        for (int i = 0; i < vehicleList.size(); i++){
            Vehicle vehicle = vehicleList.get(i);
            Rectangle rectangle = rectangleList.get(i);
            vehicle.setRectangleEvents(rectangle,rectangleList,gridBound, primaryStage);
        }

        return gridPane;

    }

}

