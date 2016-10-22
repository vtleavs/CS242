/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dice;

import javafx.scene.canvas.Canvas;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Ben
 */
public class Main extends Application
{
    /*
        Initialize Animation Stuff
    */
    Timeline rollAnimation;
    LoadedDie animationDie;
    
    /*
        Initialize JavaFX controls
    */
    Canvas dieFaceCanvas = new Canvas(110, 110);
    
    Spinner<Integer> numSidesSpinner = new Spinner<>(4, 32767, 6, 1);
    CheckBox setLoadedCheck = new CheckBox("Loaded Die?");
    Slider loadFactorSlider = new Slider(0, 100, 1);
    Spinner<Integer> loadedSideSpinner = new Spinner<>(1, 32767, 1, 1);
    Label winLabel = new Label();
    
    Label numSidesLabel = new Label("Number of Sides:");
    Label loadSideLabel = new Label("Loaded Side:");
    Label loadFactLabel = new Label("Load Factor:");
    
    /*
        Initialize Dice
    */
    Die fairDie;
    LoadedDie loadedDie;
    
    /*
        Initialize other data
    */
    int cycle = 0;
    int lastResult = 1;
    int point = -1;
           
    /**
     * Starts the application
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) 
    {
        /*
            Set initial states of controls
        */
        loadFactorSlider.setDisable(true);
        loadFactorSlider.showTickLabelsProperty().setValue(Boolean.TRUE);
        loadFactorSlider.showTickMarksProperty().setValue(Boolean.TRUE);
        loadedSideSpinner.setEditable(true);
        loadedSideSpinner.setDisable(true);
        numSidesSpinner.setEditable(true);
        loadSideLabel.setDisable(true);
        loadFactLabel.setDisable(true);
        
        /*
            Run animation
        */
        rollAnimation = new Timeline(new KeyFrame(
                Duration.millis(40), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                if(animationDie.getLoadFactor() < 100)
                {    
                    animationDie.setLoadFactor(animationDie.getLoadFactor() + 1);
                    dieFace(dieFaceCanvas.getGraphicsContext2D(), animationDie.roll());
                    cycle++;
                    if(cycle >= rollAnimation.getCycleCount())
                    {
                        enableControls(true);
                        gameResultCheck();
                    }
                }
            }
        }));
        
        /*
            Set action of checkbox
        */
        setLoadedCheck.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                if(setLoadedCheck.isSelected())
                {    
                    loadedSideSpinner.setDisable(false);
                    loadFactorSlider.setDisable(false);
                    loadSideLabel.setDisable(false);
                    loadFactLabel.setDisable(false);
                }
                else
                {
                    loadedSideSpinner.setDisable(true);
                    loadFactorSlider.setDisable(true);
                    loadSideLabel.setDisable(true);
                    loadFactLabel.setDisable(true);
                }
            }
        });
        
        /*
            Set action of loadSide Spinner
        */
        loadedSideSpinner.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) 
            {
                if(loadedSideSpinner.getValue() >= numSidesSpinner.getValue())
                {
                    loadedSideSpinner.decrement(loadedSideSpinner.getValue() 
                            - numSidesSpinner.getValue());
                }
            }
        });
        
        /*
            Set action of clicking on die face
        */
        dieFaceCanvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) 
            {
                enableControls(false);
                cycle = 0;
                rollAnimation.setCycleCount(100);
                
                if(setLoadedCheck.isSelected())
                {
                    loadedDie = new LoadedDie(numSidesSpinner.getValue(), 
                            loadedSideSpinner.getValue(), (int)loadFactorSlider.getValue());
                    lastResult = loadedDie.roll();
                }
                else 
                {
                    fairDie = new Die(numSidesSpinner.getValue());
                    lastResult = fairDie.roll();
                }
                animationDie = new LoadedDie(numSidesSpinner.getValue(), lastResult, 0);
                rollAnimation.play();
            }
        });
        
        // Paint the die face
        dieFace(dieFaceCanvas.getGraphicsContext2D(), 1);
        
        // Add all pane structure to root
        VBox root = new VBox();
        Label title = new Label("Die Game");
        title.setFont(Font.font(50));
        root.getChildren().add(title);
        root.getChildren().add(new HBox(numSidesLabel, numSidesSpinner));
        root.getChildren().add(setLoadedCheck);
        root.getChildren().add(new HBox(loadSideLabel, loadedSideSpinner));
        root.getChildren().add(new HBox(loadFactLabel, loadFactorSlider));
        root.getChildren().add(dieFaceCanvas);
        root.getChildren().add(winLabel);
        
        Scene scene = new Scene(root, 500, 700);
        
        scene.getStylesheets().add("dice/Main.css");
        scene.getStylesheets().add( getClass().getResource("Main.css").toExternalForm() );
        
        primaryStage.setTitle("Die Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * The main method...
     * @param args 
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    /**
     * Draw the top face of the Die
     * @param gc the graphics context
     * @param faceValue the value of the die face to display
     */
    public void dieFace(GraphicsContext gc, int faceValue)
    {
        gc.setFill(new Color(.5,.5,.5,1));
        gc.fillRoundRect(0, 0, 110, 110, 30, 30);
        gc.setFill(new Color(.6,.6,.6,1));
        gc.fillRoundRect(1, 1, 108, 108, 30, 30);
        gc.setFill(new Color(.7,.7,.7,1));
        gc.fillRoundRect(2, 2, 106, 106, 30, 30);
        gc.setFill(new Color(.8,.8,.8,1));
        gc.fillRoundRect(3, 3, 104, 104, 30, 30);
        gc.setFill(new Color(.9,.9,.9,1));
        gc.fillRoundRect(4, 4, 102, 102, 30, 30);
        gc.setFill(new Color(1,1,1,1));
        gc.fillRoundRect(5, 5, 100, 100, 30, 30);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        if(faceValue < 1000)
            gc.setFont(Font.font("Roboto", FontWeight.BOLD,50));
        else
            gc.setFont(Font.font("Roboto", FontWeight.BOLD,30));
        gc.setFill(new Color(0,0,0, 1));
        gc.fillText("" + faceValue,
            Math.round(110 / 2), 
            Math.round(110 / 2));
    }
    
    /**
     * Set whether controls are enabled or no
     * @param disable true if enabled
     */
    void enableControls(boolean disable)
    {
        numSidesSpinner.setDisable(!disable);
        setLoadedCheck.setDisable(!disable);
        loadedSideSpinner.setDisable(!disable);
        loadFactorSlider.setDisable(!disable);
    }
    
    /**
     * Check whether game is won or lost
     */
    void gameResultCheck()
    {
        if(lastResult == 1)
        {
            winLabel.setText("YOU LOSE!  Reroll to play again...");
        }
        else if(point == -1)
        {
            point = lastResult;
            winLabel.setText("Roll " + point + " again to win!");
        }
        else if(lastResult == point)
        {
            winLabel.setText("YOU WIN!  Reroll to play again...");
            point = -1;
        }
    }
}
