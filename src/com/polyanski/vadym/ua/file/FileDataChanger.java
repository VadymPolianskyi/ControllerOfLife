package com.polyanski.vadym.ua.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by vadym on 27.09.16.
 */
public class FileDataChanger implements Changer {

    private ProgramFileManageImpl programFileManage = new ProgramFileManageImpl();
    private TimeFileManageImpl timeFileManage = new TimeFileManageImpl();
    private OtherProgramFileManageImpl otherProgramFileManage = new OtherProgramFileManageImpl();
    private List<String> programNamesList = new ArrayList<String>();
    private List<String> timesList = new ArrayList<String>();
    private List<String> otherProgramNamesList = new ArrayList<String>();
    private Iterator<String> programNameIterator;
    private Iterator<String> timeIterator;
    private Iterator<String> otherProgramIterator;


    @Override
    public void changeManiFiles(String newProgramName, String newTime, String lastProgramName, String lastTime) {
        programNamesList = programFileManage.getAllStrings();
        timesList = timeFileManage.getAllStrings();

        programNameIterator = programNamesList.iterator();
        timeIterator = timesList.iterator();

        programFileManage.clearAll();
        timeFileManage.clearAll();
        while (programNameIterator.hasNext() && timeIterator.hasNext()) {
            String programName = programNameIterator.next();
            String time = timeIterator.next();
            if(programName.equals(lastProgramName)&& time.equals(lastTime)){
                addingData(newProgramName,newTime);
                continue;
            }

            addingData(programName,time);
        }

    }

    public void addingData(String progrName, String time) {
        try {
            programFileManage.addString(progrName);
            timeFileManage.addString(time);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addingOtherProgramName(String programName) {
        try{
            otherProgramFileManage.addString(programName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeAnotherFile(String newOtherProgramName, String lastOtherProgramName) {
        otherProgramNamesList = otherProgramFileManage.getAllStrings();

        otherProgramIterator = otherProgramNamesList.iterator();

        otherProgramFileManage.clearAll();

        while (otherProgramIterator.hasNext()) {
            String programName = otherProgramIterator.next();
            if (programName.equals(lastOtherProgramName)){
                addingOtherProgramName(newOtherProgramName);
                continue;
            }

            addingOtherProgramName(programName);
        }
    }

    @Override
    public void deleteMainData(String programName, String time) {
        programNamesList = programFileManage.getAllStrings();
        timesList = timeFileManage.getAllStrings();

        programNameIterator = programNamesList.iterator();
        timeIterator = timesList.iterator();

        programFileManage.clearAll();
        timeFileManage.clearAll();
        while (programNameIterator.hasNext() && timeIterator.hasNext()) {
            String newProgramName = programNameIterator.next();
            String newTime = timeIterator.next();
            if(newProgramName.equals(programName)&& newTime.equals(time)){
                continue;
            }

            addingData(newProgramName,newTime);
        }
    }

    @Override
    public void deleteOtherData(String otherPorgramName) {
        otherProgramNamesList = otherProgramFileManage.getAllStrings();
        otherProgramIterator = otherProgramNamesList.iterator();

        otherProgramFileManage.clearAll();

        while (otherProgramIterator.hasNext()) {
            String programName = otherProgramIterator.next();
            if (programName.equals(otherPorgramName)){
                continue;
            }

            addingOtherProgramName(programName);
        }
    }
}
