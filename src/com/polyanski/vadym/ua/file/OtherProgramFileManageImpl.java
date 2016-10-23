package com.polyanski.vadym.ua.file;

import com.polyanski.vadym.ua.controlerException.fileException.EmptyNameExeption;
import com.polyanski.vadym.ua.visual.MainApp;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadym on 23.09.16.
 */
public class OtherProgramFileManageImpl implements FileManage {

    public static final String ADRESS = "ListOfOtherPrograms.txt";
    private Path listOfOtherPrograms;
    List<String> otherProgramNames = new ArrayList<String>();


    public OtherProgramFileManageImpl(){
        listOfOtherPrograms = Paths.get(ADRESS);
        otherProgramNames = getAllStrings();
    }

    @Override
    public void addString(String programName) throws IOException {
        MainApp mainApp = new MainApp();
        if (programName == null || programName.isEmpty()){
            mainApp.showInformationDialog("Not entered program name. Write name...");
            throw new EmptyNameExeption("Not entered program name. Write name...");
        }
        otherProgramNames.add(programName);
        addToFile();
    }


    @Override
    public List<String> getAllStrings(){
        try {
            return Files.readAllLines(listOfOtherPrograms);
        } catch (IOException e) {
            System.out.println("GetAllStrings in OtherProgramFileManage exception");
        }
        return new ArrayList<String>();
    }

    @Override
    public void addToFile() throws IOException {
        Files.write(listOfOtherPrograms, otherProgramNames, Charset.forName("UTF-8"));
    }

    @Override
    public void clearAll(){
        try {
            Files.delete(listOfOtherPrograms);
            otherProgramNames = new ArrayList<String>();
        } catch (IOException e) {
            System.out.println("fileOfOtherPrograms is empty");
        }
    }
}
