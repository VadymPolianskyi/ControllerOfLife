package com.polyanski.vadym.ua.file;

import com.polyanski.vadym.ua.controlerException.fileException.EmptyTimeException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadym on 23.08.2016.
 */
public class TimeFileManageImpl implements FileManage {
    public static final String ADRESS = "ListOfTimes.txt";
    private Path listOfTimes;
    List<String> timeList = new ArrayList<String>();

    public TimeFileManageImpl() {
        listOfTimes = Paths.get(ADRESS);
        timeList = getAllStrings();
    }

    @Override
    public void addString(String time) throws IOException {

        if (time == null || time.isEmpty()){
            throw new EmptyTimeException("Not entered time. Write time...");
        }
        timeList.add(time);
        addToFile();
    }


    @Override
    public List<String> getAllStrings()  {
        try {
            return Files.readAllLines(listOfTimes);
        } catch (IOException e) {
            System.out.println("GetALlString in TimeFileManage exception.");
        }
        return new ArrayList<String>();
    }

    @Override
    public void addToFile() throws IOException {
        Files.write(listOfTimes, timeList, Charset.forName("UTF-8"));

    }

    @Override
    public void clearAll() {
        try {
            Files.delete(listOfTimes);
            timeList = new ArrayList<String>();
            System.out.println("We worked2");
        } catch (IOException e) {
            System.out.println("fileOfTimes is empty");
        }
    }

    //time data add to list
    public void writeStringAndAddToList(String hours, String minutes) throws IOException {
        String time = hours + "." + minutes;
        addString(time);
    }
}
