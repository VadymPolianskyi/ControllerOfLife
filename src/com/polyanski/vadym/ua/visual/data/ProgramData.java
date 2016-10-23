package com.polyanski.vadym.ua.visual.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by vadym on 26.09.16.
 */
public class ProgramData {



    private final StringProperty programName;
    public final StringProperty time;


    public ProgramData(){this(null,null);}

    public ProgramData(String programName, String time) {
        this.programName = new SimpleStringProperty(programName);
        this.time = new SimpleStringProperty(time);
    }


    public StringProperty timeProperty() {
        return time;
    }

    public StringProperty programNameProperty() {
        return programName;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public void setProgramName(String programName) {
        this.programName.set(programName);
    }

    public String getProgramName() {
        return programName.get();
    }

    public String getTime() {
        return time.get();
    }
}
