/**
 * Created by Brandon, Natasha, Martha, and Charlie on 2/26/2016.
 *
 * Class: Vehicle
 * Handles construction and movement of vehicles; identifies wins
 *
 * Click & drag code based on example found @web http://java-buddy.blogspot.com/
 */

import javafx.event.EventHandler;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Vehicle {
    private int xGridPosition;
    private int yGridPosition;
    private Image image;
    private int length;
    private int width;
    private String orient;
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    private Boolean checkRed;

    // Vehicle constructor
    public Vehicle(Image image, int width, int length, String orient, int startXGridPosition, int startYGridPosition, Boolean checkRed) {
        this.xGridPosition = startXGridPosition;
        this.yGridPosition = startYGridPosition;
        this.image = image;
        this.length = length;
        this.width = width;
        this.orient = orient;
        this.checkRed = checkRed;
    }

    Model model = new Model();

    //Getter and setter methods for the position of the vehicle on the grid
    public int getxGridPosition() {
        return xGridPosition;
    }

    public int getyGridPosition() {
        return yGridPosition;
    }


    /**
     * Sets the attributes of a basic vehicle object
     * @return rectangle object
     */
    public Rectangle setVehicleAttributes() {
        Rectangle rectangle = new Rectangle();
        rectangle.setCursor(Cursor.HAND);
        rectangle.setHeight(length);
        rectangle.setWidth(width);
        rectangle.setFill(new ImagePattern(image));

        //Set up the alignment of the vehicles based on their orientations
        if (orient.equals("h")) {
            GridPane.setValignment(rectangle, VPos.CENTER);
        }

        else {
            GridPane.setValignment(rectangle, VPos.TOP);
            GridPane.setHalignment(rectangle, HPos.CENTER);
        }
        return rectangle;
    }

    /**
     * Handles vehicle movement using Mouse Events
     * @param block - reference to the vehicle the user is moving
     * @param rectangleList - list of all vehicles in grid
     * @param gridBound - size of the grid
     * @param primaryStage - instance of Stage
     */
    public void setRectangleEvents(Rectangle block, ArrayList<Rectangle> rectangleList, BoundingBox gridBound, Stage primaryStage) {
        //Set up how the rectangles handle being clicked and dragged
        block.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                orgSceneX = t.getSceneX();
                orgSceneY = t.getSceneY();
                orgTranslateX = ((Rectangle) (t.getSource())).getTranslateX();
                orgTranslateY = ((Rectangle) (t.getSource())).getTranslateY();
            }
        });

        //Handles drag movement by checking for bound and vehicle collisions
        //Calls checkCollisions() method
        block.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {

                double prevTranslateX = block.getTranslateX();
                double prevTranslateY = block.getTranslateY();

                if (orient.equals("h")) {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double newTranslateX = orgTranslateX + offsetX;
                    ((Rectangle) (t.getSource())).setTranslateX(newTranslateX);
                }
                else {
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateY = orgTranslateY + offsetY;
                    ((Rectangle) (t.getSource())).setTranslateY(newTranslateY);
                }
                checkCollisions(block, rectangleList, gridBound, t, prevTranslateY, prevTranslateX);
            }
        });

        //Handles mouse release by snapping vehicle into a designated grid spot
        block.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                //Snap to grid
                Bounds bounds = block.localToScene(block.getBoundsInLocal());
                double minX = bounds.getMinX();
                double minY = bounds.getMinY();

                double orgAbsStartX = ((Rectangle) (t.getSource())).getLayoutX();
                double orgAbsStartY = ((Rectangle) (t.getSource())).getLayoutY();

                //Locates closest grid block and snaps the vehicle to that block on mouse release
                double modMinX = minX % 100;
                double modMinY = minY % 100;

                if (orient.equals("h")) {
                    if (modMinX < 50) {
                        block.setTranslateX(minX - modMinX - orgAbsStartX - 100);
                    } else {
                        block.setTranslateX(minX - modMinX - orgAbsStartX);
                    }

                    //run the sendToWinPage() method if the red car hits the right edge of the grid
                    if (checkRed) {
                        Bounds redCarBounds = block.localToScene(block.getBoundsInLocal());
                        Double maxX = redCarBounds.getMaxX();
                        if (maxX > 700) {
                            sendToWinPage(primaryStage);
                            block.setOnMouseDragged(null);
                        }
                    }
                } else {
                    if (modMinY < 50) {
                        block.setTranslateY(minY - modMinY - orgAbsStartY - 100);
                    } else {
                        block.setTranslateY(minY - modMinY - orgAbsStartY);
                    }
                }
            }
        });
    }

    /**
     * Checks for and prevents collisions with the edge of the grid other cars
     * This method handles errors (i.e. cars in impossible places) by limiting user input
     * @param block - reference to the vehicle the user is moving
     * @param rectangleList - list of all vehicles in grid
     * @param gridBound - size of the grid
     * @param t - instance of MouseEvent
     * @param prevTranslateY - previous y-coordinate of moving car
     * @param prevTranslateX - previous x-coordinate of moving car
     */
    private void checkCollisions(Rectangle block, ArrayList<Rectangle> rectangleList, BoundingBox gridBound, MouseEvent t, double prevTranslateY, double prevTranslateX) {
        boolean collisionDetected = false;
        //checks for collision with grid bound
        if (!gridBound.contains(block.getBoundsInParent())){
            collisionDetected = true;
        }
        //checks for collision with other vehicles
        for (Rectangle staticBlocks : rectangleList) {
            if (staticBlocks != block) {
                if (block.getBoundsInParent().intersects(staticBlocks.getBoundsInParent())) {
                    collisionDetected = true;
                }
            }

            if (collisionDetected) {
                ((Rectangle) (t.getSource())).setTranslateY(prevTranslateY);
                ((Rectangle) (t.getSource())).setTranslateX(prevTranslateX);

            }
        }
    }

    /**
     * Communicates with Model that the user has won and generates a new stage for Model to use
     * @param primaryStage - instance of Stage used to open a new window
     */
    private void sendToWinPage(Stage primaryStage){
        primaryStage.close();
        Stage newStage = new Stage();
        model.goToWinPage(newStage);
    }
}