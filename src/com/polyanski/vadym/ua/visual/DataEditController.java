package com.polyanski.vadym.ua.visual;

import com.polyanski.vadym.ua.controlerException.killException.KillRungingException;
import com.polyanski.vadym.ua.file.FileDataChanger;
import com.polyanski.vadym.ua.file.OtherProgramFileManageImpl;
import com.polyanski.vadym.ua.killer.KillerOfProgramsImpl;
import com.polyanski.vadym.ua.visual.data.OtherProgramData;
import com.polyanski.vadym.ua.visual.data.ProgramData;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

/**
 * Created by vadym on 26.09.16.
 */
public class DataEditController {

    @FXML
    private TableView<ProgramData> firstProgramTableView;
    @FXML
    private TableView<OtherProgramData> secondProgramTableView;
    @FXML
    private TableColumn<ProgramData, String> firstProgramNameColumn;
    @FXML
    private TableColumn<OtherProgramData, String> secondProgramNameColumn;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField secondNameField;
    @FXML
    private ComboBox<String> hoursComboBox;
    @FXML
    private ComboBox<String> minutesComboBox;
    @FXML
    private Button firstCancelButton;

    KillerOfProgramsImpl killerOfPrograms = new KillerOfProgramsImpl();

    ProgramData programData;
    OtherProgramData otherProgramData;
    FileDataChanger fileDataChanger = new FileDataChanger();

    MainController mainController = new MainController();
    OtherProgramFileManageImpl otherProgramFileManage = new OtherProgramFileManageImpl();

    private MainApp mainApp;



    public void setMain(MainApp mainApp){
        this.mainApp = mainApp;

        firstProgramTableView.setItems(mainApp.getProgramDatas());

    }

    @FXML
    private void initialize() {

        firstProgramNameColumn.setCellValueFactory(cellData -> cellData.getValue().programNameProperty());
        secondProgramNameColumn.setCellValueFactory(cellData -> cellData.getValue().otherProgramNameProperty());

        showFirstSelectedData(null);
        showSecondSelectedData(null);

        firstProgramTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showFirstSelectedData(newValue)));
        secondProgramTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showSecondSelectedData(newValue)));
    }

    public void showFirstSelectedData(ProgramData programData) {
        if(programData == null){
        } else {
            this.programData = programData;
            firstNameField.setText(programData.getProgramName());
            changeComboBoxes(programData.getTime());
        }

    }

    public void showSecondSelectedData(OtherProgramData otherProgramData) {
        if (otherProgramData == null) {
        } else {
            this.otherProgramData = otherProgramData;
            secondNameField.setText(otherProgramData.getOtherProgramName());
        }
    }



    public void cancelButtonClicker(){
        firstCancelButton.getScene().getWindow().hide();
    }


    public void deleteButtonClicker(){
        fileDataChanger.deleteMainData(firstNameField.getText(), makeTime());
    }

    public void deleteSecondButtonClicker(){
        fileDataChanger.deleteOtherData(secondNameField.getText());
    }

    public void changeButtonClicker() {
        boolean correctName = firstNameField.getText().equals(programData.getProgramName());

        try {
            if (correctName){

                fileDataChanger.changeManiFiles(firstNameField.getText(),
                        makeTime(), programData.getProgramName(), programData.getTime());
            } else {
                if (killerOfPrograms.isProcessRunging(firstNameField.getText())) {
                    fileDataChanger.changeManiFiles(firstNameField.getText(),
                            makeTime(), programData.getProgramName(), programData.getTime());
                } else {
                    mainApp.showInformationDialog("Entered incorrectly process name!");
                }
            }
        } catch (KillRungingException e) {
            e.printStackTrace();
        }
        firstProgramTableView.setItems(mainApp.getProgramDatas());

    }

    public void changeSecondButtonClicker() {
        try {
            if (killerOfPrograms.isProcessRunging(secondNameField.getText())) {
                fileDataChanger.changeAnotherFile(secondNameField.getText(), otherProgramData.getOtherProgramName());
            } else {
                mainApp.showInformationDialog("Entered incorrectly process name!");
            }
        } catch (KillRungingException e) {
            e.printStackTrace();
        }
        secondProgramTableView.setItems(mainApp.getOtherProgramDatas());
    }
    public void addButtonClicker() throws IOException {
        try {
            if (killerOfPrograms.isProcessRunging(secondNameField.getText())) {
                otherProgramFileManage.addString(secondNameField.getText());
            } else {
                mainApp.showInformationDialog("Entered incorrectly process name!");
            }
        } catch (KillRungingException e) {
            e.printStackTrace();
        }

    }

    public void selectedSecondTab() {
        secondProgramTableView.setItems(mainApp.getOtherProgramDatas());
        secondNameField.setText("");

    }

    public void changeComboBoxes(String time) {

        hoursComboBox.getItems().clear();
        minutesComboBox.getItems().clear();
        hoursComboBox.getItems().addAll(mainController.makeString(24));
        minutesComboBox.getItems().addAll(mainController.makeString(60));

        String minutes = "";
        String hours = "";
        boolean flag = true;
        for(char c : time.toCharArray()) {

            if(c=='.') {
                flag = false;
                continue;
            }
            if(flag) {hours += c;} else {minutes+= c;}
        }

        hoursComboBox.setValue(hours);
        minutesComboBox.setValue(minutes);
    }

    public String makeTime(){

        String hoursData = hoursComboBox.getValue();
        String minutesData = minutesComboBox.getValue();

        if (hoursData == null) {
            hoursData = "00";
        }
        if (minutesData == null) {
            minutesData = "00";
        }
        return hoursData + "." + minutesData;
    }
}
