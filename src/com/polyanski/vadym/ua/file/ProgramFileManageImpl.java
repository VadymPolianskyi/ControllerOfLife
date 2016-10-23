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
 * Created by vadym on 09.08.2016.
 */
public class ProgramFileManageImpl implements FileManage {
    public static final String ADRESS = "ListOfPrograms.txt";
    private Path listOfPrograms;
    List<String> programNames = new ArrayList<String>();


    public ProgramFileManageImpl(){
        listOfPrograms = Paths.get(ADRESS);
        programNames = getAllStrings();
    }
    @Override
    public void addString(String programName) throws IOException {
        MainApp mainApp = new MainApp();

        if (programName == null || programName.isEmpty()){
            mainApp.showInformationDialog("Not entered program name. Write name...");
            throw new EmptyNameExeption("Not entered program name. Write name...");
        }
        programNames.add(programName);
        addToFile();
    }


    @Override
    public List<String> getAllStrings() {
        try {
            return Files.readAllLines(listOfPrograms);
        } catch (IOException e) {
            System.out.println("GetAllStrings in ProgramFileManage exception");
        }
        return new ArrayList<String>();
    }

    @Override
    public void addToFile() throws IOException {
        Files.write(listOfPrograms, programNames, Charset.forName("UTF-8"));
    }

    @Override
    public void clearAll() {
        try {
            Files.delete(listOfPrograms);
            programNames = new ArrayList<String>();
            System.out.println("We worked");
        } catch (IOException e) {
            System.out.println("fileOfPrograms is empty");
        }
    }
}
