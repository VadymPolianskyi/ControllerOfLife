package com.polyanski.vadym.ua.manage;

import com.polyanski.vadym.ua.controlerException.fileException.FileException;
import com.polyanski.vadym.ua.file.ProgramFileManageImpl;
import com.polyanski.vadym.ua.file.TimeFileManageImpl;
import com.polyanski.vadym.ua.time.Time;
import com.polyanski.vadym.ua.time.TimeAlarmClockImpl;
import com.polyanski.vadym.ua.visual.MainApp;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by vadym on 31.08.2016.
 **/
public class BackManager {
    public static boolean isTime = true;
    public static String programNowWork;
    HashMap<String, String> programsData = new HashMap<String,String>();
    List<String> programsToClose;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    MainApp mainApp;

    public void addAllLists() throws IOException {
        ProgramFileManageImpl programFile = new ProgramFileManageImpl();
        TimeFileManageImpl timeFile = new TimeFileManageImpl();

        Iterator<String> programValue = programFile.getAllStrings().iterator();
        Iterator<String> timeKey = timeFile.getAllStrings().iterator();

        while (programValue.hasNext() && timeKey.hasNext()){
            programsData.put(timeKey.next(), programValue.next());
        }
    }

    public void on(){
        try {
            addAllLists();
        } catch (IOException e) {
        }

            Time time = new Time();
            String workTime = null;
            try {
                workTime = time.timeSearchNow(new TimeFileManageImpl());
            } catch (FileException e) {
                JOptionPane.showMessageDialog(null, "Problam with 'ListOfTimes.txt'! Meybe you haven't write time..");
            } catch(IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Problam with time...So sorry.");
            }
//                System.out.println(workTime);
//                System.out.println(programsData);


            try {
                programsToClose = makeProgramList(programsData.get(workTime), programsData);
            } catch (NullPointerException e) {
                System.out.println("Time is empty");
            }

            try {
                System.out.println(isTime);
                TimeAlarmClockImpl.flag = true;
                TimeAlarmClockImpl timeAlarmClock = new TimeAlarmClockImpl(returnHours(workTime),returnMinutes(workTime),
                        programsToClose,programsData.get(workTime), mainApp);
                timeAlarmClock.newThread();
                programNowWork = programsData.get(workTime);
            } catch (NumberFormatException e) {
                System.out.println("Have problem with time");
            }

    }

    public void off(){
        TimeAlarmClockImpl.flag = false;
    }

    public List<String> makeProgramList(String workName, HashMap<String, String> allNamesAndTimes) {
        List<String> programList = new ArrayList<String>();

        for (String programName: allNamesAndTimes.values()){
            if(!programName.equals(workName)) {
                    programList.add(programName);
            }
        }
        return programList;
    }

    public int returnMinutes(String workTime){
        String minutes = "";
        char time[] = workTime.toCharArray();
        for(int i = 3; i < time.length; i++){
            minutes += time[i];
        }
        return Integer.parseInt(minutes);
    }

    public int returnHours(String workTime){
        String minutes = "";
        char time[] = workTime.toCharArray();
        for(int i = 0; i < 2; i++){
            minutes += time[i];
        }
        return Integer.parseInt(minutes);
    }

}
