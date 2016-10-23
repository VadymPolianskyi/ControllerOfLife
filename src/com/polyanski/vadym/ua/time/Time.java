package com.polyanski.vadym.ua.time;

import com.polyanski.vadym.ua.file.TimeFileManageImpl;

import java.io.IOException;
import java.util.*;


/**
 * Created by vadym on 05.09.2016.
 */
public class Time {

    List<String> time = new ArrayList<String>();

    Calendar calendar = new GregorianCalendar();
    double timeMemory;
    public Time() {}

    public String timeSearchNow(TimeFileManageImpl fileManageer) throws IOException {
        time = fileManageer.getAllStrings();
        Iterator<String> timeIterator = time.iterator();

        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        double timeMemory = 0, difference, newMinutes = minute;
        double timeNow = hours + (newMinutes/100);

        difference = 23.69;
        while (timeIterator.hasNext()){
            double timeAgain = convertToDouble(timeIterator.next());

            if(timeAgain - timeNow < difference && !((timeAgain - timeNow) < 0)){
                difference = timeAgain - timeNow;
                timeMemory = timeAgain;

            }
        }
        String strTimeMemory  = "" + timeMemory;;

        if (strTimeMemory.length() == 4) {
            strTimeMemory += "0";
        }

       return strTimeMemory;
    }

    public double convertToDouble(String timeData){
        return Double.parseDouble(timeData);
    }


}
