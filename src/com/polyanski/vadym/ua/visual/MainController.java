package com.polyanski.vadym.ua.visual;

import com.polyanski.vadym.ua.file.OtherProgramFileManageImpl;
import com.polyanski.vadym.ua.file.ProgramFileManageImpl;
import com.polyanski.vadym.ua.file.TimeFileManageImpl;
import com.polyanski.vadym.ua.killer.KillerOfProgramsImpl;
import com.polyanski.vadym.ua.manage.BackManager;
import com.polyanski.vadym.ua.time.StringUtilities;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Calendar;

/**
 * Created by vadym on 21.09.16.
 */
public class MainController {


    @FXML
    private TextField programName;

    @FXML
    private ComboBox<String> hours;
    @FXML
    private ComboBox<String> minutes;
    @FXML
    private ToggleButton onOff;
    @FXML
    private Label timeLabel;
    @FXML
    private Label programNameLabel;

    private MainApp mainApp;
    KillerOfProgramsImpl killerOfPrograms = new KillerOfProgramsImpl();


    public MainController() {
    }

    public void setMain(MainApp mainApp){
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {
        hours.getItems().addAll(makeString(24));
        minutes.getItems().addAll(makeString(60));
        bindToTime();
        String process = BackManager.programNowWork;
        if (process != null) {
            programNameLabel.setText("On: " + BackManager.programNowWork);
        } else {
            programNameLabel.setText("Off");
        }




    }

    private void bindToTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override public void handle(ActionEvent actionEvent) {
                                Calendar time = Calendar.getInstance();
                                String hourString = StringUtilities.pad(2, ' ', time.get(Calendar.HOUR_OF_DAY) == 0 ? "12" : time.get(Calendar.HOUR_OF_DAY) + "");
                                String minuteString = StringUtilities.pad(2, '0', time.get(Calendar.MINUTE) + "");
                                String secondString = StringUtilities.pad(2, '0', time.get(Calendar.SECOND) + "");
//                                String ampmString = time.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
                                timeLabel.setText("|   Time: " +hourString + ":" + minuteString + ":" + secondString);
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void clickerAddButton() throws IOException {

        if (killerOfPrograms.isProcessRunging(programName.getText())){
//            System.out.println("We add some elements!");

            ProgramFileManageImpl programFileManage = new ProgramFileManageImpl();
            TimeFileManageImpl timeFileManage = new TimeFileManageImpl();

            timeFileManage.writeStringAndAddToList(hours.getValue(),minutes.getValue());
            programFileManage.addString(programName.getText());
            System.out.println("Program name: " + programName.getText());
            System.out.println("Hours: " + hours.getValue() + "\nMinutes: " + minutes.getValue());
        }
        else {
            mainApp.showInformationDialog("Please run the program, or enter the correct process name!");
        }
    }




    public void clickerToggleButton() {
        BackManager backManager = new BackManager();
        if (onOff.isSelected()) {
            backManager.setMainApp(mainApp);
            System.out.println("On");
            backManager.on();
            programNameLabel.setText("On: " + BackManager.programNowWork);
        } else {
            System.out.println("Off");
            programNameLabel.setText("Off");
            backManager.off();
        }
    }

    public String[] makeString(int size){
        String allArray[] = new String[size];
        for (int i = 0; i < 10; i++){
            allArray[i] = "0" + i;
        }

        for (int j = 10; j < size; j++) {
            allArray[j] = "" + j;
        }
        return allArray;
    }

    public void clearItemClicker() {
        OtherProgramFileManageImpl otherProgramFileManage = new OtherProgramFileManageImpl();
        ProgramFileManageImpl programFileManage = new ProgramFileManageImpl();
        TimeFileManageImpl timeFileManage = new TimeFileManageImpl();

        programFileManage.clearAll();
        timeFileManage.clearAll();
        otherProgramFileManage.clearAll();


        System.out.println("Clear all.");
    }

    public void editItemClicker() {
        mainApp.showDialog("dataEdit.fxml", "Edit data");
    }

    public void aboutItemClicker() {
        System.out.println("About");

        mainApp.showDialog("about.fxml", "About");
    }

    public void exitItemClicker() {
        mainApp.exit();
    }
}
