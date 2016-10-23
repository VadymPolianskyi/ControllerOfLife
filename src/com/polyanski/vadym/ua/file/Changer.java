package com.polyanski.vadym.ua.file;

/**
 * Created by vadym on 27.09.16.
 */
public interface Changer {
    public void changeManiFiles(String newProgramName, String newTime, String lastProgramName, String lastTime);
    public void changeAnotherFile(String newOtherProgramName, String lastOtherProgramName);
    public void deleteMainData(String programName, String time);
    public void deleteOtherData(String otherPorgramName);

}
