package com.polyanski.vadym.ua.visual;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Created by vadym on 28.09.16.
 */
public class InformationDialogController {
    @FXML
    private Button okButton;
    @FXML
    private Label information;

    @FXML
    private void initialize() {}

    public void setMain(String message) {
        information.setText(message);
    }


    public void okClicker() {
        okButton.getScene().getWindow().hide();
    }

}
