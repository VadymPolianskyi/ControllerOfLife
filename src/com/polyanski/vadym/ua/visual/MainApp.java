package com.polyanski.vadym.ua.visual;

import com.polyanski.vadym.ua.file.FileDataChanger;
import com.polyanski.vadym.ua.file.OtherProgramFileManageImpl;
import com.polyanski.vadym.ua.file.ProgramFileManageImpl;
import com.polyanski.vadym.ua.file.TimeFileManageImpl;
import com.polyanski.vadym.ua.visual.data.OtherProgramData;
import com.polyanski.vadym.ua.visual.data.ProgramData;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by vadym on 22.09.16.
 */
public class MainApp extends Application {

    private String message;
    Stage dialogStage;
    public MainApp(){}
    public MainApp(String[] args) {
        startApplication(args);
    }

    ProgramFileManageImpl programFileManage = new ProgramFileManageImpl();
    TimeFileManageImpl timeFileManage = new TimeFileManageImpl();
    OtherProgramFileManageImpl otherProgramFileManager = new OtherProgramFileManageImpl();

    private  ObservableList<OtherProgramData> otherProgramDatas = FXCollections.observableArrayList();
    private  ObservableList<ProgramData> programDatas = FXCollections.observableArrayList();
    MainController mainController;
    FileDataChanger fileDataChanger = new FileDataChanger();


    private Stage primaryStage;
    public void makeListOfOtherProgramData() {
        Iterator<String> otherProgramIterator = otherProgramFileManager.getAllStrings().iterator();
        otherProgramDatas.clear();
        while (otherProgramIterator.hasNext()){
            otherProgramDatas.add(new OtherProgramData(otherProgramIterator.next()));
        }
    }

    public void makeListOfProgramData() {
            Iterator<String> programFileIterator = programFileManage.getAllStrings().iterator();
            Iterator<String> timeFileIterator = timeFileManage.getAllStrings().iterator();

            programDatas.clear();
            while (programFileIterator.hasNext() && timeFileIterator.hasNext()) {
                String program = programFileIterator.next();
                programDatas.add(new ProgramData(program, timeFileIterator.next()));
            }
    }

//  get list with program datas
    public ObservableList<OtherProgramData> getOtherProgramDatas() {
        makeListOfOtherProgramData();
        return otherProgramDatas;
    }

    public ObservableList<ProgramData> getProgramDatas() {
        makeListOfProgramData();
        return programDatas;
    }
// start application
    public void startApplication(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = (Parent) fxmlLoader.load(getClass().getResourceAsStream("main.fxml"));
        mainController = fxmlLoader.getController();
        this.primaryStage.setResizable(false);
        this.primaryStage.setTitle("Controller of life");
        this.primaryStage.setScene(new Scene(root));
        this.primaryStage.getIcons().add(new Image("pictures/mainIcon.png"));
        MainController controller = fxmlLoader.getController();
        controller.setMain(this);
        this.primaryStage.show();

    }



    public void showDialog(String programName, String title) {

        try {
            FXMLLoader loader  = new FXMLLoader();
            Parent root = (Parent) loader.load(getClass().getResourceAsStream(programName));

            dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.setResizable(false);
            dialogStage.getIcons().add(new Image("pictures/mainIcon.png"));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            if (programName.equals("dataEdit.fxml")) {
                DataEditController controller = loader.getController();
                controller.setMain(this);
            } else if(programName.equals("optionPane.fxml")) {
                InformationDialogController controller = loader.getController();
                dialogStage.getIcons().add(new Image("pictures/informationIcon.png"));
                controller.setMain(message);
            }

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exit() {
        Platform.exit();
    }

    public void showInformationDialog(String message) {
        this.message = message;
        showDialog("optionPane.fxml", "Information");
    }
}
