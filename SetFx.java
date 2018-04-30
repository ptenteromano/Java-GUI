/*
 * Philip Tenteromano
 * 4/20/2018
 * Int set and Summable set with FX
 * Java Programming
 *
 * JavaFX implementation and Main program
 * 
 */

// Note to self:
// Use Artifacts for JAR files with IntelliJ IDE
// File -> Project Structure -> Artifacts
package setfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SetFx extends Application {
    
    private static SummableSet sumSet = new SummableSet();
    private static boolean found = false;
    private static String titleMsg, labelMsg;
   
    @Override
    public void start(Stage primaryStage) {
        // three primary layout panes 
        BorderPane root = new BorderPane();
        VBox btm = new VBox();      
        HBox menu = new HBox(200);
        HBox menu2 = new HBox(200);
        
        // add 6 buttons
        Button addNum = new Button();
        Button srch = new Button();
        Button rmv = new Button();
        Button clr = new Button();
        Button size = new Button();
        Button sum = new Button();
        Button sortInc = new Button();
        Button sortDec = new Button();
        Button maxNum = new Button();
        Button minNum = new Button();
        
        // initalize read-only text area
        TextArea txta = new TextArea();
            txta.setMaxSize(400,250);
            txta.setEditable(false);
            txta.setMouseTransparent(true);
            txta.setFocusTraversable(false);
            
        // initalize title and menu bar
        Text title = new Text("Summable Set Using JavaFX");
            title.setFont(Font.font("Arial",FontWeight.BOLD, 20));
        Label caption = new Label("Selection Menu for Summable Set");
            caption.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
            
        // set BOTTOM layout          
        menu.setSpacing(20);
        menu2.setSpacing(20);
        menu.setPadding(new Insets(10,10,10,10));
        menu2.setPadding(new Insets(10,10,10,10));
        caption.setPadding(new Insets(10, 10, 10, 10));
        
        // customize buttons
        addNum.setText("Insert");
        srch.setText("Search");
        rmv.setText("Remove");
        clr.setText("Clear");
        size.setText("Size");
        sum.setText("Sum Set");
        sortInc.setText("Sort Increasing");
        sortDec.setText("Sort Decreasing");
        maxNum.setText("Get Max");
        minNum.setText("Get Min");
        
        // add buttons to menu bar, stack caption on top in a VBOX
        menu.getChildren().addAll(addNum, srch, rmv, clr, size, sum);
        menu2.getChildren().addAll(sortInc, sortDec, maxNum, minNum);
        btm.getChildren().addAll(caption, menu, menu2);
        btm.setAlignment(Pos.CENTER);
        menu.setAlignment(Pos.CENTER);
        menu2.setAlignment(Pos.CENTER);
        
        // put title at the top of BorderPane
        root.setTop(title);
        root.setAlignment(title, Pos.CENTER);
        root.setMargin(title, new Insets(40,40,40,40));
        
        // put textarea in the center
        root.setCenter(txta);
        root.setAlignment(txta, Pos.CENTER);
        root.setMargin(txta, new Insets(40,80,40,80));
        
        // add menu vbox (with caption) to bottom
        root.setBottom(btm);
        root.setAlignment(btm, Pos.CENTER);
        root.setMargin(btm, new Insets(40,40,40,40));
        
        // button configurations:
        // insert, write to textarea
        addNum.setOnAction(e -> {
                displayAlert(1);
                if(!sumSet.emptySet())
                    txta.setText(sumSet.vectToString());
                });
        // search
        srch.setOnAction(e -> displayAlert(2));
        // remove, write to textarea if found
        rmv.setOnAction(e -> {
                displayAlert(3);
                if(found)
                    if(sumSet.emptySet())
                        txta.clear();
                    else
                        txta.setText(sumSet.vectToString());
                });
        // clear the set, output result
        clr.setOnAction(e -> {
                found = sumSet.clrVect();
                newMsg(4,0);
                if(found)
                    txta.clear();
                });
        // show current size of set
        size.setOnAction(e -> {
                newMsg(5,0);
                });
        // show summation of current set
        sum.setOnAction(e -> {
                newMsg(6,0);
                });
        // sort in Increasing order
        sortInc.setOnAction(e -> {
                if(!sumSet.emptySet())
                    txta.setText(sumSet.sortInOrder());
                else
                    newMsg(8,0);
                });

        sortDec.setOnAction(e -> {
                if(!sumSet.emptySet())
                    txta.setText(sumSet.sortPostOrder());
                else
                    newMsg(8,0);
                });
        maxNum.setOnAction(e -> {
                if(!sumSet.emptySet())
                    newMsg(9,0);
                else
                    newMsg(8,0);
                });
        minNum.setOnAction(e -> {
                if(!sumSet.emptySet())
                    newMsg(10,0);
                else
                    newMsg(8,0);
                });


        // implement the scene window using BorderPane
        Scene scene = new Scene(root, 600, 400);
        // set title, min size, and show the Stage
        primaryStage.setTitle("Program by Phil Tenteromano");
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(600); 
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    // switch function to manipulate a single Button Function
    public static void switchBtn(int param, int num) {
        // cases are parameters passed by button calls
        switch(param) {
            // error for non-integer value
            case 1:      
                titleMsg = "Error";
                labelMsg = "Please Enter an Integer";
                break;
            // search message
            case 2:
                titleMsg = "Search";
                if(found)
                    labelMsg = num + " found!!";
                else
                    labelMsg = num + " could not be found";
                break;
            // remove message
            case 3:
                titleMsg = "Removal";
                if(found)
                    labelMsg = num + " was removed!";
                else
                    labelMsg = num + " could not be removed";
                break;
            // clear set message
            case 4:
                titleMsg = "Clear Set";
                if(sumSet.emptySet()) 
                    labelMsg = "Set was cleared!";
                else
                    labelMsg = "Set was not cleared";
                break; 
            // size message
            case 5:
                titleMsg = "Number of Elements";
                labelMsg = "There are " + sumSet.sizeof() + " Elements";
                break;
            // summation message
            case 6:
                titleMsg = "Summation";
                labelMsg = "Sum = " + sumSet.Sum();
                break;
            // duplicate on insertion
            case 7:
                titleMsg = "Duplicate found";
                labelMsg = "Duplicate found!";
                break;
            // error for empty sets
            case 8:
                titleMsg = "Error";
                labelMsg = "No items in set";
                break;
            // max value message
            case 9:
                titleMsg = "Max Value";
                labelMsg = "Max: " + sumSet.getMax();
                break;
            // min value message
            case 10:
                titleMsg = "Min Value";
                labelMsg = "Min: " + sumSet.getMin();
                break;
            // defaulting for debugging
            default:
                titleMsg = "Error";
                labelMsg = "Error";
        }
    }
    
    // auxilary window showing results of button callf
    public static void newMsg(int param, int num) {
        Stage stageBox = new Stage();
        VBox msg = new VBox();
            msg.setAlignment(Pos.CENTER);
            msg.setSpacing(12);
        Label error = new Label();
        Button okay = new Button();
        
        // find out which button was pressed - switch function
        switchBtn(param, num);
        
        Scene alert = new Scene(msg, 200, 200);
        msg.getChildren().addAll(error,okay);

        // pass switch variables, set and show stage
        okay.setText("Okay");
        error.setText(labelMsg);
        stageBox.setTitle(titleMsg);
        stageBox.initModality(Modality.APPLICATION_MODAL);
        stageBox.setMinWidth(150);
        stageBox.setX(400); stageBox.setY(250);
        stageBox.setScene(alert);
        // close window
        okay.setOnAction(r -> stageBox.close());
        
        stageBox.showAndWait();
    }
    
    // interactive button call function for integer passing
    public static void displayAlert(int param) {
        Stage stageBox = new Stage();
        VBox input = new VBox();
            input.setAlignment(Pos.CENTER);
            input.setSpacing(12);
        Label captionAlert = new Label("Enter an Integer:");
        TextField answer = new TextField("Enter Integer Value");
        Button submit = new Button();
        
        // set the layout
        input.getChildren().addAll(captionAlert, answer, submit);
        Scene alert = new Scene(input, 200, 200);
        submit.setText("Submit");
        
        // set the scene
        stageBox.initModality(Modality.APPLICATION_MODAL);
        stageBox.setTitle("Integer Value");
        stageBox.setMinWidth(200);
        stageBox.setX(500); stageBox.setY(200);
        stageBox.setScene(alert);
        
        // reset found on every call
        found = false;
        // try-catch on the textfield, must input integer
        submit.setOnAction(r -> {
            try{
                Integer x = Integer.parseInt(answer.getText());
                if(param == 1)
                    // inserting
                    found = sumSet.insert(x);
                    if(found)
                        // output error if duplicate found
                        newMsg(7, 0);
                else if(param == 2) {
                    // searching - newMsg shows result
                    found = sumSet.search(x);
                    newMsg(param, x);
                }
                else if(param ==3) {
                    // removal - newMsg shows result
                    found = sumSet.removeNum(x);
                    newMsg(param, x);
                }
                stageBox.close();
            } catch(NumberFormatException nfe) {
                newMsg(1,0);    // error if not integer
            }
        });
        stageBox.showAndWait();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}

