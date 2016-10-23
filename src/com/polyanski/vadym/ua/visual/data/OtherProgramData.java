package com.polyanski.vadym.ua.visual.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by vadym on 26.09.16.
 */
public class OtherProgramData {

    private final StringProperty otherProgramName;

    public String getOtherProgramName() {
        return otherProgramName.get();
    }

    public StringProperty otherProgramNameProperty() {
        return otherProgramName;
    }

    public void setOtherProgramName(String otherProgramName) {
        this.otherProgramName.set(otherProgramName);
    }


    public OtherProgramData(String otherProgramName) {
        this.otherProgramName = new SimpleStringProperty(otherProgramName);
    }
}
