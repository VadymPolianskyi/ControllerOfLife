package com.polyanski.vadym.ua.time;

import com.polyanski.vadym.ua.controlerException.killException.KillException;
import com.polyanski.vadym.ua.killer.KillerOfProgramsImpl;
import com.polyanski.vadym.ua.manage.BackManager;
import com.polyanski.vadym.ua.visual.MainApp;
import com.sun.javafx.tk.Toolkit;
import javafx.concurrent.Task;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by vadym on 27.08.2016.
 */
public class TimeAlarmClockImpl implements TimeAlarmClock,Runnable {
    public static boolean flag = true;
    KillerOfProgramsImpl killer = new KillerOfProgramsImpl();
    int hours,minutes;
    String programNameToInfo;
    MainApp mainApp;


    private List<String> programNames;
    Calendar calendar = new GregorianCalendar();

        public TimeAlarmClockImpl(int hours, int minutes, List<String> programNames, String programNameToInfo, MainApp mainApp) {
        this.programNames = programNames;
        this.hours = hours;
        this.minutes = minutes;
        this.programNameToInfo = programNameToInfo;
        this.mainApp = mainApp;
    }

    public void newThread() {
        new Thread(new TimeAlarmClockImpl(hours,minutes,programNames,programNameToInfo, mainApp)).start();
    }

    @Override
    public int getHours() {
        calendar = new GregorianCalendar();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    @Override
    public int getMinutes() {
        calendar = new GregorianCalendar();
        return calendar.get(Calendar.MINUTE);
    }


    @Override
    public void run() {
        System.out.println("Wait: " + programNameToInfo);
        while (true) {


            int hour = getHours();
            int minute = getMinutes();



            if (hours == hour && minutes == minute){
                JOptionPane.showMessageDialog (null, "You must open " + programNameToInfo + " !", "Wake up", JOptionPane.INFORMATION_MESSAGE);
                try {
                    killer.mainAllKill(programNames);
                    BackManager.isTime = true;
                } catch (KillException e) {
                    System.out.println("Can't kill program");
                }
                try {
                    Runtime.getRuntime().exec(programNameToInfo.substring(programNameToInfo.length()-4, programNameToInfo.length()-1));
                } catch (IOException e) {
                    System.out.println("Can't open program");
                }

                if (flag) {
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                        System.out.println("Can't stop time. Sorry...");
                    }
                    BackManager backManager = new BackManager();
                    System.out.println("On again: ");
                    backManager.on();
                }

                break;
            }
        }
    }
}
