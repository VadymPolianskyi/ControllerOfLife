package com.polyanski.vadym.ua.killer;

import com.polyanski.vadym.ua.controlerException.killException.KillException;
import com.polyanski.vadym.ua.controlerException.killException.KillRungingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

/**
 * Created by vadym on 10.08.2016.
 */
public class KillerOfProgramsImpl implements KillerOfPrograms {
    private static final String TASKLIST = "tasklist";
    private static final String KILL = "taskkill /IM ";


    @Override
    public void killProcess(String serviceName) throws KillException {
        try {
            System.out.print(isProcessRunging(serviceName));
            if (isProcessRunging(serviceName)) {
                Runtime.getRuntime().exec(KILL + serviceName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new KillException("Some problams in killProcess");
        }
    }

    @Override
    public boolean isProcessRunging(String serviceName) throws KillRungingException {

        Process p = null;
        try {
            p = Runtime.getRuntime(). exec(TASKLIST);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains(serviceName)) {
                    System.out.println(line);
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            throw new KillRungingException("Some problams in procerRunging");
        }

    }

    @Override
    public void mainAllKill(List<String> fileNames) throws KillException {
        Iterator<String> fileNamesIterator = fileNames.iterator();

        while (fileNamesIterator.hasNext()){
            killProcess(fileNamesIterator.next());
        }
    }
}
